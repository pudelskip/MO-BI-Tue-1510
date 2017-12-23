package pl.shane.model;

import java.util.ArrayList;
import java.util.List;

public class Floor implements Location {

    private List<Room> roomList;
    private Integer id;
    private String name;

    public Floor() {

    }

    public Floor(Integer id, String name) {
        this.id = id;
        this.name = name;
        roomList = new ArrayList<>();
    }

    public Floor(List<Room> roomList, Integer id, String name) {
        this.roomList = roomList;
        this.id = id;
        this.name = name;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRoom(Room room) throws Exception {
        if (room == null)
            throw new Exception("Room can not be null");
        roomList.add(room);
    }

    public void removeRoom(Room room) {
        if (room == null)
            return;
        roomList.remove(room);
    }

    public void removeRemove(int position) throws IndexOutOfBoundsException {
        if (position < 0 || position > roomList.size())
            throw new IndexOutOfBoundsException("Index out of bounce");
    }

    @Override
    public Float calculateArea() {
        Float areaSum = 0f;
        if (roomList.isEmpty())
            return 0f;
        for (Location childLocation : roomList)
            areaSum += childLocation.calculateArea();
        return areaSum;
    }

    @Override
    public Float calculateVolume() {
        Float volumeSum = 0f;
        if (roomList.isEmpty())
            return 0f;
        for (Location childLocation : roomList)
            volumeSum += childLocation.calculateVolume();
        return volumeSum;
    }

    @Override
    public Float calculateLightToAreaConsumption() {
        Float energySum = 0f;
        Float areaSum = 0f;
        if (roomList.isEmpty())
            return 0f;
        for (Room childLocation : roomList) {
            energySum += childLocation.getLightPower();
            areaSum += childLocation.getArea();
        }
        if (areaSum == 0)
            return 0f;
        return energySum / areaSum;
    }

    @Override
    public Float calculateEnergyToVolumeConsumption() {
        Float areaSum = calculateArea();
        Float energySum = 0f;
        for (Room childLocation : roomList) {
            energySum += childLocation.getHeating();
        }
        if (areaSum == 0)
            return 0f;
        return energySum / areaSum;
    }

    @Override
    public List<Room> getRoomListAboveNorm(Float normValue) {
        List<Room> aboveNormRoomList = new ArrayList<>();
        for (Room room : roomList) {
            List<Room> roomResultList = room.getRoomListAboveNorm(normValue);
            if (roomResultList != null && !roomResultList.isEmpty())
                aboveNormRoomList.addAll(roomResultList);
        }
        return aboveNormRoomList;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
