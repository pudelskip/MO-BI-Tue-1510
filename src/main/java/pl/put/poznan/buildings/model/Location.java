package pl.put.poznan.buildings.model;

import pl.put.poznan.buildings.visitor.Visitor;
import pl.put.poznan.buildings.visitor.VisitorAction;

import java.util.List;

public interface Location {

    public abstract Float calculateArea();

    public abstract Float calculateVolume();

    public abstract Float calculateLightToAreaConsumption();

    public abstract Float calculateEnergyToVolumeConsumption();

    public abstract List<Room> getRoomListAboveNorm(Float normValue);

    public abstract void acceptVisitor(Visitor visitor, Integer id, VisitorAction action);

}
