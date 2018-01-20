package pl.put.poznan.buildings.ui.mainwindow;

import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.model.Room;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model that holds created/imported buildings and lists of strings for GUI Lists
 */
public class UiModel {
    /**
     * List that contains the entire created model as a form of list of buildings
     */
    private List<Building> buildingList;
    /**
     * Set of list that contains names that are displayed in GUI
     */
    private DefaultListModel<String> buildingListModel;
    private DefaultListModel<String> floorListModel;
    private DefaultListModel<String> roomListModel;

    /**
     * Constructor for initialization, creates necessary lists
     */
    UiModel() {
        buildingList = new ArrayList<>();
        buildingListModel = new DefaultListModel<>();
        floorListModel = new DefaultListModel<>();
        roomListModel = new DefaultListModel<>();
    }

    public DefaultListModel<String> getBuildingListModel() {
        return buildingListModel;
    }

    public DefaultListModel<String> getFloorListModel() {
        return floorListModel;
    }

    public DefaultListModel<String> getRoomListModel() {
        return roomListModel;
    }

    /**
     * Function that manages GUI and logic after a building is selected from list
     *
     * @param index in list of buildings, indicates which building was selected
     */
    public void buildingSelected(int index) {
        if (index == -1)
            return;
        floorListModel.clear();
        Building building = buildingList.get(index);
        List<Floor> floorList = building.getFloorList();
        if (floorList == null || floorList.isEmpty())
            return;
        for (Floor floor : floorList)
            floorListModel.addElement(floor.toString());
    }

    /**
     * Function that manages GUI and logic after a floor is selected from list
     * @param buildingIndex in list of buildings, indicates to which building a selected floor is assigned
     * @param floorIndex in list of floors, indicates which floor was selected
     */
    public void floorSelected(int buildingIndex, int floorIndex) {
        if (buildingIndex == -1 || floorIndex == -1)
            return;
        roomListModel.clear();
        Building building = buildingList.get(buildingIndex);
        List<Floor> floorList = building.getFloorList();
        Floor floor = floorList.get(floorIndex);
        List<Room> roomList = floor.getRoomList();
        if (roomList == null || roomList.isEmpty())
            return;
        for (Room room : roomList) {
            roomListModel.addElement(room.toString());
        }
    }

    /**
     * Function that adds created building to model
     * @param building newly created building
     */
    public void addBuilding(Building building) {
        buildingList.add(building);
        buildingListModel.addElement(building.toString());
    }

    /**
     * Removes building from GUI and model
     * @param position position in list of selected building
     */
    public void removeBuilding(int position) {
        if (position >= 0 && position < buildingList.size()) {
            buildingList.remove(position);
            buildingListModel.remove(position);
            floorListModel.clear();
        }
    }

    /**
     * Function performed after importing model from external file to setup model and GUI
     * @param buildingList list of loaded buildings
     */
    public void clearAndImportBuildings(List<Building> buildingList) {
        if (buildingList == null)
            return;
        this.buildingList.clear();
        this.buildingList.addAll(buildingList);
        buildingListModel.clear();
        floorListModel.clear();
        roomListModel.clear();
        for (Building building : buildingList)
            buildingListModel.addElement(building.toString());
    }

    /**
     * Function adds floor to model
     * @param floor newly created floor
     * @param buildingIndex index of a building in list to which new floor is assigned
     */
    public void addFloor(Floor floor, int buildingIndex) {
        Building building = buildingList.get(buildingIndex);
        try {
            building.addFloor(floor);
            floorListModel.addElement(floor.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes floor from model and GUI
     * @param buildingIndex index of building to which selected floor is assigned
     * @param floorIndex index of floor selected for deletion
     */
    public void removeFloor(int buildingIndex, int floorIndex) {
        Building building = buildingList.get(buildingIndex);
        List<Floor> floorList = building.getFloorList();
        floorList.remove(floorIndex);
        floorListModel.remove(floorIndex);
        roomListModel.clear();
    }

    /**
     * Function that adds room to model and GUI
     * @param room newly created room
     * @param buildingIndex position of building in list to which room is assigned
     * @param floorIndex position of in list floor to which room is assigned
     */
    public void addRoom(Room room, int buildingIndex, int floorIndex) {
        Building building = buildingList.get(buildingIndex);
        List<Floor> floorList = building.getFloorList();
        Floor floor = floorList.get(floorIndex);
        try {
            floor.addRoom(room);
            roomListModel.addElement(room.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes floor from model and GUI
     * @param buildingIndex index of building in list to which room is assigned
     * @param floorIndex index of floor in list to which room is assigned
     * @param roomIndex index of room selected for deletion
     */
    public void removeRoom(int buildingIndex, int floorIndex, int roomIndex) {
        Building building = buildingList.get(buildingIndex);
        List<Floor> floorList = building.getFloorList();
        Floor floor = floorList.get(floorIndex);
        List<Room> roomList = floor.getRoomList();
        roomList.remove(roomIndex);
        roomListModel.remove(roomIndex);
    }

    /**
     * Public getter to get access to model
     * @return List of buildings, returns empty array if not a single building is in model
     */
    public List<Building> getBuildingList() {
        return buildingList;
    }
}
