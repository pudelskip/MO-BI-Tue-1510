package pl.shane.model;

import java.util.ArrayList;
import java.util.List;

public class Room implements Location {
    private Integer id;
    private String name;
    private Float area;
    private Float cubeVolume;
    private Float heating;
    private Float lightPower;

    public Room() {
    }

    public Room(Integer id, String name, Float area, Float cubeVolume, Float heating, Float lightPower) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cubeVolume = cubeVolume;
        this.heating = heating;
        this.lightPower = lightPower;
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

    @Override
    public Float calculateArea() {
        return area;
    }

    @Override
    public Float calculateVolume() {
        return cubeVolume;
    }

    @Override
    public Float calculateLightToAreaConsumption() {
        if (area == 0)
            return 0f;
        return lightPower / area;
    }

    @Override
    public Float calculateEnergyToVolumeConsumption() {
        if (cubeVolume == 0)
            return 0f;
        return heating / cubeVolume;
    }

    @Override
    public List<Room> getRoomListAboveNorm(Float normValue) {
        Float energyConsumption = calculateEnergyToVolumeConsumption();
        List<Room> result = new ArrayList<>();
        if (energyConsumption >= normValue)
            result.add(this);
        return result;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
