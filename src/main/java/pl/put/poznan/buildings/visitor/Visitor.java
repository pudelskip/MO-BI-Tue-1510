package pl.put.poznan.buildings.visitor;

import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Room;

import java.util.List;

/**
 * Interface for Visitor, used in Location to report data
 *
 * @see pl.put.poznan.buildings.model.Location
 */
public interface Visitor {
    /**
     * function used to perform visitation on list of buildings
     * @param buildingList
     */
    void visit(List<Building> buildingList);

    /**
     * used to perform visitation on building
     * @param building
     */
    void visit(Building building);

    /**
     * used to perform visitation on floor
     * @param floor
     */
    void visit(Floor floor);

    /**
     * used to perform visitation on room
     * @param room
     */
    void visit(Room room);
}
