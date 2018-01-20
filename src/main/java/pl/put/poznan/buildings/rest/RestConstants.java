package pl.put.poznan.buildings.rest;

/**
 * Interface for storing constants for RestController
 */
public interface RestConstants {

    /**
     * Base for rest api requests
     */
    String BASE = "/api";

    /**
     * end-point for calculating an area
     */
    String CALCULATE_AREA = "/area";
    /**
     * end-point for calculating a volume
     */
    String CALCULATE_VOLUME = "/volume";
    /**
     * end-point for calculating a light to area ratio
     */
    String CALCULATE_LIGHT_TO_AREA = "/light-area";
    /**
     * end-point for calculating an energy to volume ratio
     */
    String CALCULATE_ENERGY_TO_VOLUME = "/energy-volume";
    /**
     * end-point for finding rooms above provided norm
     */
    String GET_ROOMS_ABOVE_ROOM = "/norm-check";
    /**
     * end-point for calculating penalty for rooms above norm
     */
    String CALCULATE_PENALTY = "/penalty";
}
