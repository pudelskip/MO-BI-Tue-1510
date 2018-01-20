package pl.put.poznan.buildings.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class FloorTest {

    private static Floor model;

    @BeforeClass
    public static void resetModel() {
        if (model == null)
            return;
        for (Room room : model.getRoomList()) {
            reset(room);
        }
    }

    @Before
    public void init() {
        model = new Floor(1, "FloorTest");
        List<Room> roomList = new ArrayList<>();
        Room room = mock(Room.class);
        when(room.calculateArea()).thenReturn(10f);
        when(room.calculateVolume()).thenReturn(5f);
        when(room.getArea()).thenReturn(10f);
        when(room.getCubeVolume()).thenReturn(5f);
        when(room.getHeating()).thenReturn(10f);
        when(room.getLightPower()).thenReturn(1f);
        when(room.calculateLightToAreaConsumption()).thenReturn(0.1f);
        when(room.calculateEnergyToVolumeConsumption()).thenReturn(2f);
        roomList.add(room);

        room = mock(Room.class);
        when(room.calculateArea()).thenReturn(2f);
        when(room.calculateVolume()).thenReturn(3f);
        when(room.getArea()).thenReturn(2f);
        when(room.getCubeVolume()).thenReturn(3f);
        when(room.getHeating()).thenReturn(6f);
        when(room.getLightPower()).thenReturn(2f);
        when(room.calculateLightToAreaConsumption()).thenReturn(1f);
        when(room.calculateEnergyToVolumeConsumption()).thenReturn(2f);
        roomList.add(room);

        model.setRoomList(roomList);
    }


    @Test
    public void calculateAreaTest() {
        assertTrue(model.calculateArea().equals(12f));
        List<Room> roomList = model.getRoomList();
        assertTrue(roomList != null);
        assertTrue(!roomList.isEmpty());
        for (Room room : roomList) {
            verify(room, times(1)).calculateArea();
        }
    }

    @Test
    public void calculateVolumeTest() {
        assertTrue(model.calculateVolume().equals(8f));
        List<Room> roomList = model.getRoomList();
        assertTrue(roomList != null);
        assertTrue(!roomList.isEmpty());
        for (Room room : roomList) {
            verify(room, times(1)).calculateVolume();
        }
    }

    @Test
    public void calculateLightTest() {
        Float aFloat = model.calculateLightToAreaConsumption();
        System.out.println(aFloat);
        assertTrue(model.calculateLightToAreaConsumption().equals(0.25f));
        List<Room> roomList = model.getRoomList();
        assertTrue(roomList != null);
        assertTrue(!roomList.isEmpty());
    }

    @Test
    public void calculateEnergyTest() {
        assertEquals(model.calculateEnergyToVolumeConsumption(), 2f, 0.0001);
        List<Room> roomList = model.getRoomList();
        assertTrue(roomList != null);
        assertTrue(!roomList.isEmpty());
    }

}