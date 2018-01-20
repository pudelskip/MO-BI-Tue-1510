package pl.put.poznan.buildings.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoomTest {

    private Float BASIC_AREA = 10f;
    private Float BASIC_VOLUME = 20f;
    private Float BASIC_LIGHT_CONSUMPTION = 30f;
    private Float BASIC_ENERGY_CONSUMPTION = 40f;

    private Room room;


    @Before
    public void init() {
        room = new Room();
        room.setArea(BASIC_AREA);
        room.setCubeVolume(BASIC_VOLUME);
        room.setLightPower(BASIC_LIGHT_CONSUMPTION);
        room.setHeating(BASIC_ENERGY_CONSUMPTION);
    }

    @Test
    public void calculateAreaTest() {
        assertEquals(room.calculateArea(), BASIC_AREA);
    }

    @Test
    public void calculateVolumeTest() {
        assertEquals(room.calculateVolume(), BASIC_VOLUME);
    }

    @Test
    public void calculateLightToAreaTest() {
        assertEquals(room.calculateLightToAreaConsumption(), new Float(3f));
    }

    @Test
    public void calculateEnergyToVolumeTest() {
        assertEquals(room.calculateEnergyToVolumeConsumption(), new Float(2f));
    }

    @Test
    public void roomAboveNormTest() {
        List<Room> roomListAboveNorm = room.getRoomListAboveNorm(4f);
        assertTrue(roomListAboveNorm.isEmpty());

        roomListAboveNorm = room.getRoomListAboveNorm(2f);
        assertTrue(roomListAboveNorm.isEmpty());

        roomListAboveNorm = room.getRoomListAboveNorm(1.9f);
        assertTrue(!roomListAboveNorm.isEmpty());
        assertTrue(roomListAboveNorm.get(0) == room);

        roomListAboveNorm = room.getRoomListAboveNorm(0f);
        assertTrue(!roomListAboveNorm.isEmpty());
        assertTrue(roomListAboveNorm.get(0) == room);


    }
}