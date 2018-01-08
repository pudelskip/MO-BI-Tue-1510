package pl.put.poznan.buildings.visitor;

import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Room;

import java.util.List;

public interface Visitor {
    void visit(List<Building> buildingList);
    void visit(Building building);
    void visit(Floor floor);
    void visit(Room room);
}
