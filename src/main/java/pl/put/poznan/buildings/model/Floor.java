package pl.put.poznan.buildings.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.buildings.visitor.Visitor;
import pl.put.poznan.buildings.visitor.VisitorAction;

import java.util.ArrayList;
import java.util.List;

public class Floor implements Location {

    private List<Room> roomList;
    private Integer id;
    private String name;
    private final Logger slf4jLogger = LoggerFactory.getLogger(Floor.class);

    public Floor() {

    }

    public Floor(Integer id, String name) {
        this.id = id;
        this.name = name;
        roomList = new ArrayList<>();
        slf4jLogger.debug("Empty floor"+ this.id+" created");
    }

    public Floor(List<Room> roomList, Integer id, String name) {
        this.roomList = roomList;
        this.id = id;
        this.name = name;
        slf4jLogger.debug("Floor"+ this.id+" with rooms created");
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
        slf4jLogger.debug("Room"+ room.getId()+" added to floor "+this.id);
        roomList.add(room);
    }

    public void removeRoom(Room room) {
        if (room == null){
            slf4jLogger.debug("No rooms to removed from floor "+this.id);
            return;
        }
        slf4jLogger.debug("Room"+ room.getId()+" removed from floor "+this.id);
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
    public void acceptVisitor(Visitor visitor) {
        slf4jLogger.debug("Visitor Accepted on floor "+this.id);
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
