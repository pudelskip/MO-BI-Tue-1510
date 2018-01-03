package pl.put.poznan.buildings.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Room;
import pl.put.poznan.buildings.visitor.LocationVisitor;
import pl.put.poznan.buildings.visitor.VisitorAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.put.poznan.buildings.rest.RestConstants.*;

@RestController
@RequestMapping("/{text}")
public class AppRestController {

    private static final Logger logger = LoggerFactory.getLogger(AppRestController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CALCULATE_AREA)
    public Float get(@PathVariable String text,
                      @RequestParam(value="json") String jsonString,
                      @RequestParam(value = "id") Integer id) {

        logger.debug(text);
        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor();
        visitor.visit(buildingList, id, VisitorAction.AREA);
        return visitor.getCalculationResult();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CALCULATE_VOLUME)
    public Float getArea(@PathVariable String text,
                     @RequestParam(value="json") String jsonString,
                     @RequestParam(value = "id") Integer id) {

        logger.debug(text);
        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor();
        visitor.visit(buildingList, id, VisitorAction.VOLUME);
        return visitor.getCalculationResult();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CALCULATE_LIGHT_TO_AREA)
    public Float getLightToArea(@PathVariable String text,
                     @RequestParam(value="json") String jsonString,
                     @RequestParam(value = "id") Integer id) {

        logger.debug(text);
        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor();
        visitor.visit(buildingList, id, VisitorAction.LIGHT_TO_AREA);
        return visitor.getCalculationResult();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CALCULATE_ENERGY_TO_VOLUME)
    public Float getEnergyToVolume(@PathVariable String text,
                     @RequestParam(value="json") String jsonString,
                     @RequestParam(value = "id") Integer id) {

        logger.debug(text);
        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor();
        visitor.visit(buildingList, id, VisitorAction.ENERGY_TO_VOLUME);
        return visitor.getCalculationResult();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CALCULATE_ENERGY_TO_VOLUME)
    public String getAboveNorm(@PathVariable String text,
                               @RequestParam(value="json") String jsonString,
                               @RequestParam(value = "id") Integer id,
                               @RequestParam(value = "norm") Float norm) {

        logger.debug(text);
        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor(norm);
        visitor.visit(buildingList, id, VisitorAction.ABOVE_NORM);
        List<Room> roomsAboveNorm = visitor.getRoomsAboveNorm();
        gson = new Gson();
        return gson.toJson(roomsAboveNorm);
    }
}
