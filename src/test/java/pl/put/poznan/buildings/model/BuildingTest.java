package pl.put.poznan.buildings.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BuildingTest {

    private static final Float NORM_TEST_VALUE = 10f;
    private static final Float PENALTY_TEST_VALUE = 10f;
    private Building building;

    @Before
    public void init() throws Exception {
        building = new Building(1, "testBuilding");
        for (int i = 0; i < 10; i++) {
            Floor floor = mock(Floor.class);
            when(floor.calculateArea()).thenReturn(1f);
            when(floor.calculateVolume()).thenReturn(1f);
            when(floor.calculateEnergyToVolumeConsumption()).thenReturn(1f);
            when(floor.calculateLightToAreaConsumption()).thenReturn(1f);
            when(floor.calculatePenaltyForNorm(anyFloat(), anyFloat())).thenReturn(1f);
            try {
                building.addFloor(floor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test(expected = Exception.class)
    public void addInvalidFloor() throws Exception {
        building.addFloor(null);
    }

    @Test
    public void testEveryFloorCheckedArea() {
        List<Floor> floorList = building.getFloorList();
        building.calculateArea();
        for (Floor floor : floorList) {
            verify(floor, times(1)).calculateArea();
        }
    }

    @Test
    public void testEveryFloorCheckedVolume() {
        List<Floor> floorList = building.getFloorList();
        building.calculateVolume();
        for (Floor floor : floorList) {
            verify(floor, times(1)).calculateVolume();
        }
    }

    @Test
    public void testEveryFloorCheckedEnergy() {
        List<Floor> floorList = building.getFloorList();
        building.calculateEnergyToVolumeConsumption();
        for (Floor floor : floorList) {
            verify(floor, times(1)).calculateEnergyToVolumeConsumption();
        }
    }

    @Test
    public void testEveryFloorCheckedLight() {
        List<Floor> floorList = building.getFloorList();
        building.calculateLightToAreaConsumption();
        for (Floor floor : floorList) {
            verify(floor, times(1)).calculateLightToAreaConsumption();
        }
    }

    @Test
    public void testPenalty() {
        List<Floor> floorList = building.getFloorList();
        Float result = building.calculatePenaltyForNorm(NORM_TEST_VALUE, PENALTY_TEST_VALUE);
        assertTrue(result.equals(10f));
        for (Floor floor : floorList) {
            verify(floor, times(1)).calculatePenaltyForNorm(NORM_TEST_VALUE, PENALTY_TEST_VALUE);
        }
    }
}