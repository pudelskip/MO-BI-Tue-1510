package pl.put.poznan.buildings.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.buildings.visitor.Visitor;
import pl.put.poznan.buildings.visitor.VisitorAction;

import java.util.ArrayList;
import java.util.List;

public class Building implements Location {

    private List<Floor> floorList;
    private Integer id;
    private String name;
    private final Logger slf4jLogger = LoggerFactory.getLogger(Building.class);

    public Building() {
        floorList = new ArrayList<>();
    }

    public Building(Integer id, String name) {
        this.id = id;
        this.name = name;
        floorList = new ArrayList<>();
        slf4jLogger.debug("Empty building "+ this.id+" created");
    }

    public Building(List<Floor> floorList, Integer id, String name) {
        this.floorList = floorList;
        this.id = id;
        this.name = name;
        slf4jLogger.debug("Building "+ this.id+" with floors created");
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
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

    public void addFloor(Floor floor) throws Exception {
        if (floor == null)
            throw new Exception("Floor can not be a null object");
        slf4jLogger.debug("Floor "+ floor.getId()+" added to building "+this.id);
        floorList.add(floor);
    }

    public void removeFloor(Floor floor) {
        slf4jLogger.debug("Floor "+ floor.getId()+" removed from building "+this.id);
        floorList.remove(floor);
    }

    public void removeFloor(int position) throws Exception {
        int locationSize = floorList.size();
        if (position < 0 || position >= locationSize)
            throw new Exception("Building index is out of bounce");
    }

    @Override
    public Float calculateArea() {
        Float areaSum = 0f;
        if (floorList.isEmpty())
            return 0f;
        for (Location childLocation : floorList)
            areaSum += childLocation.calculateArea();
        return areaSum;
    }

    @Override
    public Float calculateVolume() {
        Float volumeSum = 0f;
        if (floorList.isEmpty())
            return 0f;
        for (Location childLocation : floorList)
            volumeSum += childLocation.calculateVolume();
        return volumeSum;
    }

    @Override
    public Float calculateLightToAreaConsumption() {
        Float energySum = 0f;
        if (floorList.isEmpty())
            return 0f;
        for (Location childLocation : floorList)
            energySum += childLocation.calculateLightToAreaConsumption();
        return energySum;
    }

    @Override
    public Float calculateEnergyToVolumeConsumption() {
        Float energySum = 0f;
        for (Floor floor : floorList)
            energySum += floor.calculateEnergyToVolumeConsumption();
        return energySum;
    }

    @Override
    public List<Room> getRoomListAboveNorm(Float normValue) {
        List<Room> aboveNormRoomList = new ArrayList<>();
        for (Floor floor : floorList) {
            List<Room> roomResultList = floor.getRoomListAboveNorm(normValue);
            if (roomResultList != null && !roomResultList.isEmpty())
                aboveNormRoomList.addAll(roomResultList);
        }
        return aboveNormRoomList;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        slf4jLogger.debug("Visitor Accepted in building "+this.id);
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
