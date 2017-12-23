package pl.shane.model;

import java.util.List;

public interface Location {

    public abstract Float calculateArea();

    public abstract Float calculateVolume();

    public abstract Float calculateLightToAreaConsumption();

    public abstract Float calculateEnergyToVolumeConsumption();

    public abstract List<Room> getRoomListAboveNorm(Float normValue);

}
