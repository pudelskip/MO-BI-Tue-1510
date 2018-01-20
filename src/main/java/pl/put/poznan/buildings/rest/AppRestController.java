package pl.put.poznan.buildings.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Room;
import pl.put.poznan.buildings.visitor.LocationVisitor;
import pl.put.poznan.buildings.visitor.VisitorAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.put.poznan.buildings.rest.RestConstants.*;

/**
 * Class that works as a Rest Controller for a Spring framework
 * Contains end-points publicly available by simple http get request
 * Address is localhost:8080/api
 */
@RestController
@RequestMapping(value = RestConstants.BASE, method = RequestMethod.GET)
//@RequestMapping(value = {CALCULATE_AREA, CALCULATE_VOLUME, CALCULATE_ENERGY_TO_VOLUME, CALCULATE_LIGHT_TO_AREA})
public class AppRestController {

    /**
     * Logger used to log every request
     */
    private static final Logger logger = LoggerFactory.getLogger(AppRestController.class);

    /**
     * Function performed when a request for area appears
     *
     * @param jsonString json as a string for model on which calculation is performed. Param is mandatory
     * @param id         for a location on which calculation is performed. Param is mandatory
     * @return result of calculate area as a Float
     * @see RestConstants
     * @see pl.put.poznan.buildings.model.Location
     */
    @RequestMapping(CALCULATE_AREA)
    public Float getArea(@RequestParam(name = "json") String jsonString,
                         @RequestParam(name = "id") Integer id) {
        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor(id, VisitorAction.AREA);
        visitor.visit(buildingList);
        return visitor.getCalculationResult();
    }

    /**
     * Function performed when a request for volume appears
     *
     * @param jsonString json as a string for model on which calculation is performed. Param is mandatory
     * @param id         for a location on which calculation is performed. Param is mandatory
     * @return result of calculate volume as a Float
     * @see RestConstants
     * @see pl.put.poznan.buildings.model.Location
     */
    @RequestMapping(CALCULATE_VOLUME)
    public Float getVolume(@RequestParam(name = "json") String jsonString,
                           @RequestParam(name = "id") Integer id) {

        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor(id, VisitorAction.VOLUME);
        visitor.visit(buildingList);
        return visitor.getCalculationResult();
    }

    /**
     * Function performed when a request for light to area appears
     *
     * @param jsonString json as a string for model on which calculation is performed. Param is mandatory
     * @param id         for a location on which calculation is performed. Param is mandatory
     * @return result of calculate ratio as a Float
     * @see RestConstants
     * @see pl.put.poznan.buildings.model.Location
     */
    @RequestMapping(CALCULATE_LIGHT_TO_AREA)
    public Float getLightToArea(@RequestParam(name = "json") String jsonString,
                                @RequestParam(name = "id") Integer id) {

        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor(id, VisitorAction.LIGHT_TO_AREA);
        visitor.visit(buildingList);
        return visitor.getCalculationResult();
    }

    /**
     * Function performed when a request for energy to volume appears
     *
     * @param jsonString json as a string for model on which calculation is performed. Param is mandatory
     * @param id         for a location on which calculation is performed. Param is mandatory
     * @return result of calculate ratio as a Float
     * @see RestConstants
     * @see pl.put.poznan.buildings.model.Location
     */
    @RequestMapping(CALCULATE_ENERGY_TO_VOLUME)
    public Float getEnergyToVolume(@RequestParam(name = "json") String jsonString,
                                   @RequestParam(name = "id") Integer id) {

        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor(id, VisitorAction.ENERGY_TO_VOLUME);
        visitor.visit(buildingList);
        return visitor.getCalculationResult();
    }

    /**
     * Function performed when a request for rooms above norm appears
     *
     * @param jsonString json as a string for model on which calculation is performed. Param is mandatory
     * @param id         for a location on which calculation is performed. Param is mandatory
     * @param norm       value provided by the user
     * @return json as a string that contains every room above norm. Empty if not a single room breaks norm
     * @see RestConstants
     * @see pl.put.poznan.buildings.model.Location
     */
    @RequestMapping(GET_ROOMS_ABOVE_ROOM)
    public String getAboveNorm(@RequestParam(name = "json") String jsonString,
                               @RequestParam(name = "id") Integer id,
                               @RequestParam(name = "norm") Float norm) {

        logger.debug(jsonString);
        logger.debug(String.valueOf(id));

        Gson gson = new Gson();
        Building[] buildingArray = gson.fromJson(jsonString, Building[].class);
        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));

        LocationVisitor visitor = new LocationVisitor(id, VisitorAction.ABOVE_NORM, norm);
        visitor.visit(buildingList);
        List<Room> roomsAboveNorm = visitor.getRoomsAboveNorm();
        gson = new Gson();
        return gson.toJson(roomsAboveNorm);
    }
}
