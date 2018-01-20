package pl.put.poznan.buildings.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.put.poznan.buildings.BuildingsApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BuildingsApplication.class)
@ContextConfiguration(classes = {TestContext.class, WebAppConfiguration.class})
@WebAppConfiguration
public class AppRestControllerTest {

    private static final String exampleJson = "[{\"id\" : 1,\"name\" : \"build1\",\"floorList\" : [{\"id\" : 2,\"name\" : \"floor1\",\"roomList\" : [{\"id\" : 3,\"name\" : \"room1\",\"area\" : 10,\"cubeVolume\" : 20,\"heating\" : 30,\"lightPower\" : 40}]}]}]";
//    private static final String exampleJson = "%5B%7B%0D%0A%22id%22%20%3A%201%2C%0D%0A%22name%22%20%3A%20%22build1%22%2C%0D%0A%22floorList%22%20%3A%20%5B%7B%0D%0A%20%20%22id%22%20%3A%202%2C%0D%0A%20%20%22name%22%20%3A%20%22floor1%22%2C%0D%0A%20%20%22roomList%22%20%3A%20%5B%0D%0A%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%22id%22%20%3A%203%2C%0D%0A%20%20%20%20%20%20%22name%22%20%3A%20%22room1%22%2C%0D%0A%20%20%20%20%20%20%22area%22%20%3A%2010%2C%0D%0A%20%20%20%20%20%20%22cubeVolume%22%20%3A%2020%2C%0D%0A%20%20%20%20%20%20%22heating%22%20%3A%2030%2C%0D%0A%20%20%20%20%20%20%22lightPower%22%20%3A%2040%0D%0A%20%20%20%20%7D%0D%0A%20%20%5D%0D%0A%20%20%7D%5D%0D%0A%7D%0D%0A%5D%0D%0A";

    @Autowired
    private AppRestController rest;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void init() {
        rest = new AppRestController();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void testArea() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("{base}/{action}?id={id}", RestConstants.BASE, RestConstants.CALCULATE_AREA, id))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("{base}/{action}?json={json}", RestConstants.BASE, RestConstants.CALCULATE_AREA, exampleJson))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("{base}/{action}?id={id}&json={json}", RestConstants.BASE, RestConstants.CALCULATE_AREA, id, exampleJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testEveryAction() throws Exception {
        Integer id = 1;
        Float norm = 2f;
        mockMvc.perform(get("{base}/{action}?id={id}&json={json}", RestConstants.BASE, RestConstants.CALCULATE_AREA, id, exampleJson))
                .andExpect(status().isOk());
        mockMvc.perform(get("{base}/{action}?id={id}&json={json}", RestConstants.BASE, RestConstants.CALCULATE_ENERGY_TO_VOLUME, id, exampleJson))
                .andExpect(status().isOk());
        mockMvc.perform(get("{base}/{action}?id={id}&json={json}", RestConstants.BASE, RestConstants.CALCULATE_LIGHT_TO_AREA, id, exampleJson))
                .andExpect(status().isOk());
        mockMvc.perform(get("{base}/{action}?id={id}&json={json}", RestConstants.BASE, RestConstants.CALCULATE_VOLUME, id, exampleJson))
                .andExpect(status().isOk());
        mockMvc.perform(get("{base}/{action}?id={id}&json={json}&norm={norm}", RestConstants.BASE, RestConstants.CALCULATE_VOLUME, id, exampleJson, norm))
                .andExpect(status().isOk());
    }
}