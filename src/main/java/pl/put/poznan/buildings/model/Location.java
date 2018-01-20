package pl.put.poznan.buildings.model;

import pl.put.poznan.buildings.visitor.Visitor;

import java.util.List;

/**
 * Interface for a Location composite
 */
public interface Location {

    /**
     * Function that calculate area of location
     *
     * @return area in m^2 as float
     */
    public abstract Float calculateArea();

    /**
     * Function that calculate volume of location
     *
     * @return volume in m^3 as float
     */
    public abstract Float calculateVolume();

    /**
     * Function that calculates average light to area consumption for location
     *
     * @return light to area ratio as float
     */
    public abstract Float calculateLightToAreaConsumption();

    /**
     * Function that calculates average energy to volume consumption for location
     *
     * @return energy to volume ratio as float
     */
    public abstract Float calculateEnergyToVolumeConsumption();

    /**
     * Function that returns a list of rooms which energy to volume consumption is above norm provided by user
     *
     * @param normValue value of norm given as Float
     * @return List of Room that are above norm, returns empty list if not a single room is above norm
     */
    public abstract List<Room> getRoomListAboveNorm(Float normValue);

    /**
     * Function that accept visitor for report purpose
     *
     * @param visitor an object of visitor
     * @see Visitor
     */
    public abstract void acceptVisitor(Visitor visitor);

}
