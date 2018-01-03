package pl.put.poznan.buildings.visitor;

import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Location;
import pl.put.poznan.buildings.model.Room;

import java.util.List;

public class LocationVisitor implements Visitor {

    private Float calculationResult;
    private List<Room> roomsAboveNorm;
    private Float normValue;

    public LocationVisitor(){
        calculationResult = null;
        roomsAboveNorm = null;
    }


    public LocationVisitor(Float normValue){
        calculationResult = null;
        roomsAboveNorm = null;
        this.normValue = normValue;
    }

    @Override
    public void visit(List<Building> buildingList, Integer id, VisitorAction action) {
        if(buildingList == null && buildingList.isEmpty()){
            return;
        }
        for(Building building : buildingList){
            building.acceptVisitor(this, id, action);
            if(calculationResult != null || roomsAboveNorm != null)
                return;
        }
    }

    @Override
    public void visit(Building building, Integer id, VisitorAction action) {
        if (building.getId().equals(id)) {
            calculateResult(building, action);
            return;
        }
        List<Floor> floorList = building.getFloorList();
        if(floorList != null && !floorList.isEmpty())
            for(Floor floor : floorList) {
                if(calculationResult != null || roomsAboveNorm != null)
                    return;
                floor.acceptVisitor(this, id, action);
            }
    }

    @Override
    public void visit(Floor floor, Integer id, VisitorAction action) {
        if (floor.getId().equals(id)) {
            calculateResult(floor, action);
            return;
        }
        List<Room> roomList = floor.getRoomList();
        if(roomList != null && !roomList.isEmpty())
            for(Room room : roomList) {
                if(calculationResult != null || roomsAboveNorm != null)
                    return;
                room.acceptVisitor(this, id, action);
            }

    }

    @Override
    public void visit(Room room, Integer id, VisitorAction action) {
        calculateResult(room, action);
    }

    private void calculateResult(Location location, VisitorAction action){
        switch (action){
            case AREA:
                calculationResult = location.calculateArea();
                break;
            case VOLUME:
                calculationResult = location.calculateVolume();
                break;
            case LIGHT_TO_AREA:
                calculationResult = location.calculateLightToAreaConsumption();
                break;
            case ENERGY_TO_VOLUME:
                calculationResult = location.calculateEnergyToVolumeConsumption();
                break;
            case ABOVE_NORM:
                roomsAboveNorm = location.getRoomListAboveNorm(normValue);
        }
    }

    public Float getCalculationResult() {
        return calculationResult;
    }

    public List<Room> getRoomsAboveNorm() {
        return roomsAboveNorm;
    }
}
