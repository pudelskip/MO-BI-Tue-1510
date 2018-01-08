package pl.put.poznan.buildings.utils;


import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Room;

import java.util.HashSet;
import java.util.List;

/**
 * Class with static methods used to make sure that model integrity is not broken
 */
public class IntegrityValidator {

    /**
     * Method checks if location has an unique id
     * @param data list of buildings provided as json string
     * @param id id of location to check
     * @return true if Id is unique, false otherwise
     */
    public static boolean isIdUnique(List<Building> data, Integer id) {
        if (data == null)
            return true;
        for (Building building : data) {
            if (building.getId().equals(id)) {
                return false;
            }
            List<Floor> floorList = building.getFloorList();
            if (floorList != null) {
                for (Floor floor : floorList) {
                    if (floor.getId().equals(id))
                        return false;
                    List<Room> roomList = floor.getRoomList();
                    if (roomList != null)
                        for (Room room : roomList)
                            if (room.getId().equals(id))
                                return false;
                }
            }
        }
        return true;
    }

    /**
     * Function checks if list of provided buildings contain a duplicate of id
     * @param data data of list of buildings provided as json string
     * @return boolean true when model contains duplicated id, false otherwise
     */
    public static boolean doesModelContainsIdDuplicates(List<Building> data) {
        if (data == null)
            return false;
        HashSet<Integer> idSet = new HashSet<>();
        for (Building building : data) {
            Integer id = building.getId();
            if (idSet.contains(id)) {
                return true;
            }
            idSet.add(id);
            List<Floor> floorList = building.getFloorList();
            if (floorList != null) {
                for (Floor floor : floorList) {
                    id = floor.getId();
                    if (idSet.contains(id))
                        return true;
                    idSet.add(id);
                    List<Room> roomList = floor.getRoomList();
                    if (roomList != null)
                        for (Room room : roomList) {
                            id = room.getId();
                            if (idSet.contains(id))
                                return true;
                            idSet.add(id);
                        }
                }
            }
        }
        return false;
    }

    /**
     * Function to find new unique id that is higher than any other in a model
     * @param data list of buildings provided as json string
     * @return an unique id for provided model
     */
    public static Integer getNewId(List<Building> data){
        Integer id = -1;
        if(data == null || data.isEmpty())
            return 0;
        for (Building building : data) {
            Integer idTmp = building.getId();
            if(idTmp > id)
                id = idTmp;
            List<Floor> floorList = building.getFloorList();
            if (floorList != null) {
                for (Floor floor : floorList) {
                    idTmp = floor.getId();
                    if(idTmp > id)
                        id = idTmp;
                    List<Room> roomList = floor.getRoomList();
                    if (roomList != null)
                        for (Room room : roomList) {
                            idTmp = room.getId();
                            if(idTmp > id)
                                id = idTmp;
                        }
                }
            }
        }
        id++;
        return id;
    }
}
