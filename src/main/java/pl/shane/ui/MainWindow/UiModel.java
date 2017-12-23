package pl.shane.ui.MainWindow;

import pl.shane.model.Building;
import pl.shane.model.Floor;
import pl.shane.model.Room;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Model that holds created/imported buildings and lists of strings for Ui Lists
 */
public class UiModel {
    private List<Building> buildingList;
    private DefaultListModel<String> buildingListModel;
    private DefaultListModel<String> floorListModel;
    private DefaultListModel<String> roomListModel;

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

    public void addBuilding(Building building) {
        buildingList.add(building);
        buildingListModel.addElement(building.toString());
    }

    public void removeBuilding(int position) {
        if (position >= 0 && position < buildingList.size()) {
            buildingList.remove(position);
            buildingListModel.remove(position);
            floorListModel.clear();
        }
    }

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

    public void addFloor(Floor floor, int buildingIndex) {
        Building building = buildingList.get(buildingIndex);
        try {
            building.addFloor(floor);
            floorListModel.addElement(floor.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFloor(int buildingIndex, int floorIndex) {
        Building building = buildingList.get(buildingIndex);
        List<Floor> floorList = building.getFloorList();
        floorList.remove(floorIndex);
        floorListModel.remove(floorIndex);
        roomListModel.clear();
    }

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

    public void removeRoom(int buildingIndex, int floorIndex, int roomIndex) {
        Building building = buildingList.get(buildingIndex);
        List<Floor> floorList = building.getFloorList();
        Floor floor = floorList.get(floorIndex);
        List<Room> roomList = floor.getRoomList();
        roomList.remove(roomIndex);
        roomListModel.remove(roomIndex);
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }
}
