package pl.put.poznan.buildings.model;

import pl.put.poznan.buildings.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that is the lowest object (leaf) in Location composite
 * implements Location interface
 *
 * @see Location
 */
public class Room implements Location {
    private Integer id;
    private String name;
    private Float area;
    private Float cubeVolume;
    private Float heating;
    private Float lightPower;

    private final Logger slf4jLogger = LoggerFactory.getLogger(Room.class);

    /**
     * Default constructor without any parameters
     */
    public Room() {
    }

    /**
     * Second constructor that allows for fast one-line object creation
     * @param id Unique Integer value that identifies Location in structure
     * @param name Optional name for a Location
     * @param area Area of room measured in m^2
     * @param cubeVolume Volume of room measured in m^3
     * @param heating Level of heat energy consumption
     * @param lightPower Power of light used in room
     */
    public Room(Integer id, String name, Float area, Float cubeVolume, Float heating, Float lightPower) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cubeVolume = cubeVolume;
        this.heating = heating;
        this.lightPower = lightPower;
        slf4jLogger.debug("Room" +this.id+" created");
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

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Float getCubeVolume() {
        return cubeVolume;
    }

    public void setCubeVolume(Float cubeVolume) {
        this.cubeVolume = cubeVolume;
    }

    public Float getHeating() {
        return heating;
    }

    public void setHeating(Float heating) {
        this.heating = heating;
    }

    public Float getLightPower() {
        return lightPower;
    }

    public void setLightPower(Float lightPower) {
        this.lightPower = lightPower;
    }

    /**
     * Overridden function that returns area of room
     * @return Float value of area of room
     */
    @Override
    public Float calculateArea() {
        return area;
    }

    /**
     * Overridden function that returns volume of room
     * @return Float value of volume of room
     */
    @Override
    public Float calculateVolume() {
        return cubeVolume;
    }

    /**
     * Overridden function that calculates light to area consumption,
     * @return Float value, if area is equal to 0 function returns 0
     */
    @Override
    public Float calculateLightToAreaConsumption() {
        if (area == 0)
            return 0f;
        return lightPower / area;
    }

    /**
     * Overridden function that calculates energy to area consumption
     * @return Float value, if volume is equal to 0 function returns 0
     */
    @Override
    public Float calculateEnergyToVolumeConsumption() {
        if (cubeVolume == 0){
            slf4jLogger.warn("Room volume equals zero");
            return 0f;
        }
        return heating / cubeVolume;
    }

    /**
     *
     * @param normValue Float value of norm value for energy consumption
     * @return Empty list of rooms if energy consumption is lower or equal to given norm value
     *      otherwise returns a new list that contains this room
     */
    @Override
    public List<Room> getRoomListAboveNorm(Float normValue) {
        Float energyConsumption = calculateEnergyToVolumeConsumption();
        List<Room> result = new ArrayList<>();
        if (energyConsumption > normValue)
            result.add(this);
        return result;
    }

    @Override
    public Float calculatePenaltyForNorm(Float normValue, Float penaltyValue) {
        List<Room> roomListAboveNorm = getRoomListAboveNorm(normValue);
        if (roomListAboveNorm == null || roomListAboveNorm.isEmpty())
            return 0f;
        Float penalty = 0f;
        for (Room room : roomListAboveNorm) {
            Float normDifference = room.calculateEnergyToVolumeConsumption();
            Float area = room.calculateArea();
            penalty += (normDifference - normValue) * penaltyValue * area;
        }
        return penalty;
    }

    /**
     * Function that accept Visitor for reporting purposes
     *
     * @param visitor Object that implements Visitor interface
     */
    @Override
    public void acceptVisitor(Visitor visitor) {
        slf4jLogger.debug("Visitor Accepted in room "+this.id);
        visitor.visit(this);
    }

    /**
     * Overridden function to describe object by it's id and name
     * @return
     */
    @Override
    public String toString() {
        if (name == null)
            return String.valueOf(id);
        return id + ": " + name;
    }
}
