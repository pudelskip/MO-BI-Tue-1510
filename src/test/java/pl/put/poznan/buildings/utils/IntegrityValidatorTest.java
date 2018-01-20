package pl.put.poznan.buildings.utils;

import org.junit.Test;
import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class IntegrityValidatorTest {

    @Test
    public void testWithoutDup() {
        List<Building> model = getModelWithoutIdDup();
        assertTrue(!IntegrityValidator.doesModelContainsIdDuplicates(model));
    }

    @Test
    public void testNewId() {
        List<Building> model = getModelWithoutIdDup();
        assertEquals(IntegrityValidator.getNewId(model), new Integer(91));
        assertEquals(IntegrityValidator.getNewId(null), new Integer(0));

    }

    @Test
    public void testIdDup() {
        List<Building> model = getModelWithoutIdDup();
        assertTrue(IntegrityValidator.isIdUnique(model, 0));
        for (Building building : model) {
            verify(building, times(1)).getId();
            for (Floor floor : building.getFloorList()) {
                verify(floor, times(1)).getId();
            }
        }
        assertTrue(!IntegrityValidator.isIdUnique(model, 1));
        assertTrue(!IntegrityValidator.isIdUnique(model, 90));
        assertTrue(IntegrityValidator.isIdUnique(model, 91));
    }

    private List<Building> getModelWithoutIdDup() {
        List<Building> result = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Building building = mock(Building.class);
            when(building.getId()).thenReturn(i);
            Floor floor = mock(Floor.class);
            List<Floor> floorList = new ArrayList<>();
            floorList.add(floor);
            when(floor.getId()).thenReturn(i * 10);
            when(building.getFloorList()).thenReturn(floorList);
            result.add(building);
        }
        return result;
    }

}