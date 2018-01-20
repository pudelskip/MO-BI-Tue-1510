package pl.put.poznan.buildings.visitor;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class LocationVisitorTest {

    private List<Building> buildingList;

    @Before
    public void initModel() {
        buildingList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Building building = mock(Building.class);
            when(building.calculateArea()).thenReturn(Float.valueOf(i));
            for (int j = 1; j <= 5; j++) {
                Floor floor = mock(Floor.class);
                try {
                    building.addFloor(floor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int k = 1; k <= 5; k++) {
                    Room room = mock(Room.class);
                    try {
                        floor.addRoom(room);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            buildingList.add(building);
        }
    }

    @Test
    public void testIfBuildingsVisited() {
        LocationVisitor visitor = new LocationVisitor(0, VisitorAction.AREA);
        visitor.visit(buildingList);
        for (Building building : buildingList) {
            verify(building, times(1)).acceptVisitor(visitor);
        }
    }

    @Test
    public void testIfFloorsVisited() {
        LocationVisitor visitor = new LocationVisitor(0, VisitorAction.AREA);
        visitor.visit(buildingList);
        for (Building building : buildingList) {
            for (Floor floor : building.getFloorList()) {
                verify(floor, times(1)).acceptVisitor(visitor);
            }

        }
    }

    @Test
    public void testIfRoomsVisited() {
        LocationVisitor visitor = new LocationVisitor(1, VisitorAction.AREA);
        visitor.visit(buildingList);
        for (Building building : buildingList) {
            for (Floor floor : building.getFloorList()) {
                for (Room room : floor.getRoomList()) {
                    verify(room, times(1)).acceptVisitor(visitor);
                }
            }

        }
    }

}