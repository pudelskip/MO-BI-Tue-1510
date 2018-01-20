package pl.put.poznan.buildings.visitor;

import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Location;
import pl.put.poznan.buildings.model.Room;

import java.util.List;

/**
 * Class that implements Visitor interface, used to get info from location model
 *
 * @see Visitor
 * VisitorAction interface is used for holding actions possible to be performed by visitor
 * @see VisitorAction
 */
public class LocationVisitor implements Visitor {

    /**
     * variable that stores result of performed action as Float
     */
    private Float calculationResult;
    /**
     * list of room stored from a result of ABOVE_NORM action
     */
    private List<Room> roomsAboveNorm;
    /**
     * variable that holds provided norm value, is optional if visitor does not use function to find rooms above norm
     */
    private Float normValue;
    /**
     * variable that holds provided penalty value, is optional if visitor does not use function to calculate penalty
     */
    private Float penaltyValue;
    /**
     * variable that holds id of location that is chosen for a report
     */
    private Integer id;
    /**
     * variable that holds action selected to be performed by LocationVisitor
     */
    private VisitorAction action;

    /**
     * Basic constructor for class
     *
     * @param id     of location on which action is about to be performed
     * @param action chosen action from VisitorAction interface that is about to be performed
     */
    public LocationVisitor(Integer id, VisitorAction action) {
        this.id = id;
        this.action = action;
        calculationResult = null;
        roomsAboveNorm = null;
    }


    /**
     * Extended constructor for class, used for finding rooms above norm
     *
     * @param id        of location on which action is about to be performed
     * @param action    chosen action from VisitorAction interface that is about to be performed
     * @param normValue norm value for a room
     */
    public LocationVisitor(Integer id, VisitorAction action, Float normValue) {
        calculationResult = null;
        roomsAboveNorm = null;
        this.normValue = normValue;
        this.id = id;
        this.action = action;
    }

    /**
     * Extended constructor for class, used for calculating penalty
     *
     * @param id           of location on which action is about to be performed
     * @param action       chosen action from VisitorAction interface that is about to be performed
     * @param normValue    norm value for a room
     * @param penaltyValue value of penalty
     */
    public LocationVisitor(Integer id, VisitorAction action, Float normValue, Float penaltyValue) {
        calculationResult = null;
        roomsAboveNorm = null;
        this.normValue = normValue;
        this.penaltyValue = penaltyValue;
        this.id = id;
        this.action = action;
    }

    /**
     * Function used to perform a visitation on selected location
     *
     * @param buildingList list of buildings to visit
     */
    @Override
    public void visit(List<Building> buildingList) {
        if (buildingList == null || buildingList.isEmpty()) {
            return;
        }
        for (Building building : buildingList) {
            building.acceptVisitor(this);
            if (calculationResult != null || roomsAboveNorm != null)
                return;
        }
    }

    /**
     * Function used to perform a visitation on selected location
     *
     * @param building to visit
     */
    @Override
    public void visit(Building building) {
        if (building.getId().equals(id)) {
            calculateResult(building);
            return;
        }
        List<Floor> floorList = building.getFloorList();
        if (floorList != null && !floorList.isEmpty())
            for (Floor floor : floorList) {
                if (calculationResult != null || roomsAboveNorm != null)
                    return;
                floor.acceptVisitor(this);
            }
    }

    /**
     * Function used to perform a visitation on selected location
     *
     * @param floor to visit
     */
    @Override
    public void visit(Floor floor) {
        if (floor.getId().equals(id)) {
            calculateResult(floor);
            return;
        }
        List<Room> roomList = floor.getRoomList();
        if (roomList != null && !roomList.isEmpty())
            for (Room room : roomList) {
                if (calculationResult != null || roomsAboveNorm != null)
                    return;
                room.acceptVisitor(this);
            }

    }

    /**
     * Function used to perform a visitation on selected location
     *
     * @param room to visit
     */
    @Override
    public void visit(Room room) {
        calculateResult(room);
    }

    /**
     * Private function used to calculate a result for selected location and action
     *
     * @param location that visitation is performed on
     */
    private void calculateResult(Location location) {
        switch (action) {
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
                break;
            case PENALTY:
                calculationResult = location.calculatePenaltyForNorm(normValue, penaltyValue);
        }
    }

    /**
     * Public function to fetch a result from visitation
     *
     * @return result as a float, used for basic reporting actions, if visitation was not yet performed function returns 0
     */
    public Float getCalculationResult() {
        return calculationResult;
    }

    /**
     * Public function to fetch rooms that were above provided norm
     *
     * @return a list of room that were above provided norm, returns empty list if visitation was not yet performed
     */
    public List<Room> getRoomsAboveNorm() {
        return roomsAboveNorm;
    }
}
