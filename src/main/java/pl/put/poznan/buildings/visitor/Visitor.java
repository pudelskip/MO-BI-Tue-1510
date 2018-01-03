package pl.put.poznan.buildings.visitor;

import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Room;

import java.util.List;

public interface Visitor {
    void visit(List<Building> buildingList, Integer id, VisitorAction action);
    void visit(Building building, Integer id, VisitorAction action);
    void visit(Floor floor, Integer id, VisitorAction action);
    void visit(Room room, Integer id, VisitorAction action);
}
