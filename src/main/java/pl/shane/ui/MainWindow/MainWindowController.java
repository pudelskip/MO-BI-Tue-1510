package pl.shane.ui.MainWindow;

import pl.shane.model.Building;
import pl.shane.model.Floor;
import pl.shane.model.Location;
import pl.shane.model.Room;
import pl.shane.ui.dialog.*;
import pl.shane.utils.Constants;
import pl.shane.utils.IntegrityValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
/*
 * Controller that handles events from MainWindow, takes care of UiModel
 * In addition controller uses ControllerChangeListener to inform Ui of changes in model
 */

public class MainWindowController {

    static final String ACTION_ADD_BUILDING = "addBuilding";
    static final String ACTION_REMOVE_BUILDING = "removeBuilding";
    static final String ACTION_ADD_FLOOR = "addFloor";
    static final String ACTION_REMOVE_FLOOR = "removeFloor";
    static final String ACTION_ADD_ROOM = "addRoom";
    static final String ACTION_REMOVE_ROOM = "removeRoom";
    static final String ACTION_IMPORT_JSON = "importJson";
    static final String ACTION_EXPORT_JSON = "exportJson";
    static final String ACTION_EXIT_APP = "exit";

    static final String ACTION_FUN_AREA = "funArea";
    static final String ACTION_FUN_HEAT = "funHeat";
    static final String ACTION_FUN_VOLUME = "funVolume";
    static final String ACTION_FUN_LIGHT = "funLight";
    static final String ACTION_FUN_ALERT = "funAlert";

    private UiModel model;
    private ControllerChangeListener listener;


    MainWindowController(ControllerChangeListener listener) {
        model = new UiModel();
        this.listener = listener;
    }

    public DefaultListModel<String> getBuildingListModel() {
        return model.getBuildingListModel();
    }

    public DefaultListModel<String> getFloorListModel() {
        return model.getFloorListModel();
    }

    public DefaultListModel<String> getRoomListModel() {
        return model.getRoomListModel();
    }

    void updateBuilding(int index) {
        model.buildingSelected(index);
    }

    void updateFloor(int buildingIndex, int floorIndex) {
        model.floorSelected(buildingIndex, floorIndex);
    }

    void clearBuildingListMode() {
        getBuildingListModel().clear();
    }

    void clearFloorListModel() {
        getFloorListModel().clear();
    }

    void clearRoomListModel() {
        getRoomListModel().clear();
    }


    public void actionPerformed(ActionEvent actionEvent, int buildingIndex, int floorIndex, int roomIndex) {
        String command = actionEvent.getActionCommand();
        Boolean isActionConsumed = handleAddRemoveCommand(command, buildingIndex, floorIndex, roomIndex);
        if (!isActionConsumed)
            handleFunctionCommand(command, buildingIndex, floorIndex, roomIndex);

    }

    /*
     * Function handles actions from "+" and "-" buttons
     */
    private Boolean handleAddRemoveCommand(String command, int buildingIndex, int floorIndex, int roomIndex) {
        Boolean result = false;
        switch (command) {
            case (ACTION_ADD_BUILDING):
                result = true;
                new CreateBuildingDialog(new DialogResultListener() {
                    @Override
                    public <T extends Location> void onDialogResult(T resultModel) {
                        if (resultModel != null) {
                            if (IntegrityValidator.isIdUnique(model.getBuildingList(), ((Building) resultModel).getId()))
                                model.addBuilding(((Building) resultModel));
                        }
                    }
                });
                break;

            case (ACTION_REMOVE_BUILDING):
                result = true;
                if (buildingIndex == -1)
                    break;
                model.removeBuilding(buildingIndex);
                break;

            case (ACTION_ADD_FLOOR):
                result = true;
                if (buildingIndex == -1) {
                    break;
                }
                new CreateFloorDialog(new DialogResultListener() {
                    @Override
                    public <T extends Location> void onDialogResult(T resultModel) {
                        if (resultModel != null) {
                            if (IntegrityValidator.isIdUnique(model.getBuildingList(), ((Floor) resultModel).getId()))
                                model.addFloor(((Floor) resultModel), buildingIndex);
                        }
                    }
                });
                break;

            case (ACTION_REMOVE_FLOOR):
                result = true;
                if (buildingIndex == -1 || floorIndex == -1)
                    break;
                model.removeFloor(buildingIndex, floorIndex);
                break;

            case (ACTION_ADD_ROOM):
                if (buildingIndex == -1 || floorIndex == -1)
                    break;
                result = true;
                new CreateRoomDialog(new DialogResultListener() {
                    @Override
                    public <T extends Location> void onDialogResult(T resultModel) {
                        if (resultModel != null) {
                            if (IntegrityValidator.isIdUnique(model.getBuildingList(), ((Room) resultModel).getId()))
                                model.addRoom(((Room) resultModel), buildingIndex, floorIndex);
                        }
                    }
                });
                break;

            case (ACTION_REMOVE_ROOM):
                result = true;
                if (buildingIndex == -1 || floorIndex == -1 || roomIndex == -1)
                    break;
                model.removeRoom(buildingIndex, floorIndex, roomIndex);
                break;


        }
        listener.onDataInvalidate(buildingIndex, floorIndex, roomIndex);
        return result;
    }

    /*
     * Function handles events from right pane buttons
     */
    private void handleFunctionCommand(String command, int buildingIndex, int floorIndex, int roomIndex) {
        if (command.equals(ACTION_IMPORT_JSON)) {
            handleImport();
            return;
        }
        if (command.equals(ACTION_EXPORT_JSON)) {
            handleExport();
            return;
        }
        if (buildingIndex == -1)
            return;
        switch (command) {
            case (ACTION_FUN_AREA):
                handleAreaFun(buildingIndex, floorIndex, roomIndex);
                break;
            case (ACTION_FUN_VOLUME):
                handleVolumeFun(buildingIndex, floorIndex, roomIndex);
                break;
            case (ACTION_FUN_HEAT):
                handleHeatFun(buildingIndex, floorIndex, roomIndex);
                break;
            case (ACTION_FUN_LIGHT):
                handleLightFun(buildingIndex, floorIndex, roomIndex);
                break;
            case (ACTION_FUN_ALERT):
                handleAlertFun(buildingIndex, floorIndex, roomIndex);
                break;
        }
    }

    private void handleAreaFun(int buildingIndex, int floorIndex, int roomIndex) {
        List<Building> buildingList = model.getBuildingList();
        Float areaSum = 0f;
        if (roomIndex != -1)
            areaSum = buildingList
                    .get(buildingIndex).getFloorList().get(floorIndex)
                    .getRoomList().get(roomIndex).calculateArea();
        else if (floorIndex != -1)
            areaSum = buildingList.get(buildingIndex).getFloorList().get(floorIndex).calculateArea();
        else if (buildingIndex != -1)
            areaSum = buildingList.get(buildingIndex).calculateArea();
        JOptionPane.showMessageDialog(null, Constants.FUN_AREA_RESULT_DESC + areaSum);
    }

    private void handleVolumeFun(int buildingIndex, int floorIndex, int roomIndex) {
        List<Building> buildingList = model.getBuildingList();
        Float volumeSum = 0f;
        if (roomIndex != -1)
            volumeSum = buildingList
                    .get(buildingIndex).getFloorList().get(floorIndex)
                    .getRoomList().get(roomIndex).calculateVolume();
        else if (floorIndex != -1)
            volumeSum = buildingList.get(buildingIndex).getFloorList().get(floorIndex).calculateVolume();
        else if (buildingIndex != -1)
            volumeSum = buildingList.get(buildingIndex).calculateVolume();
        JOptionPane.showMessageDialog(null, Constants.FUN_VOLUME_RESULT_DESC + volumeSum);
    }

    private void handleHeatFun(int buildingIndex, int floorIndex, int roomIndex) {
        List<Building> buildingList = model.getBuildingList();
        Float heatRatio = 0f;
        if (roomIndex != -1)
            heatRatio = buildingList
                    .get(buildingIndex).getFloorList().get(floorIndex)
                    .getRoomList().get(roomIndex).calculateEnergyToVolumeConsumption();
        else if (floorIndex != -1)
            heatRatio = buildingList.get(buildingIndex).getFloorList().get(floorIndex).calculateEnergyToVolumeConsumption();
        else if (buildingIndex != -1)
            heatRatio = buildingList.get(buildingIndex).calculateEnergyToVolumeConsumption();
        JOptionPane.showMessageDialog(null, Constants.FUN_HEAT_RESULT_DESC + heatRatio);
    }

    private void handleLightFun(int buildingIndex, int floorIndex, int roomIndex) {
        List<Building> buildingList = model.getBuildingList();
        Float lightRatio = 0f;
        if (roomIndex != -1)
            lightRatio = buildingList
                    .get(buildingIndex).getFloorList().get(floorIndex)
                    .getRoomList().get(roomIndex).calculateLightToAreaConsumption();
        else if (floorIndex != -1)
            lightRatio = buildingList.get(buildingIndex).getFloorList().get(floorIndex).calculateLightToAreaConsumption();
        else if (buildingIndex != -1)
            lightRatio = buildingList.get(buildingIndex).calculateLightToAreaConsumption();
        JOptionPane.showMessageDialog(null, Constants.FUN_LIGHT_RESULT_DESC + lightRatio);
    }

    private void handleAlertFun(int buildingIndex, int floorIndex, int roomIndex) {
        new AlertDialogInit(new NormResultListener() {
            @Override
            public void onNormProvided(Float norm) {
                List<Building> buildingList = model.getBuildingList();
                List<Room> roomListResult = new ArrayList<>();
                if (roomIndex != -1)
                    roomListResult = buildingList
                            .get(buildingIndex).getFloorList().get(floorIndex)
                            .getRoomList().get(roomIndex).getRoomListAboveNorm(norm);
                else if (floorIndex != -1)
                    roomListResult = buildingList.get(buildingIndex).getFloorList().get(floorIndex).getRoomListAboveNorm(norm);
                else if (buildingIndex != -1)
                    roomListResult = buildingList.get(buildingIndex).getRoomListAboveNorm(norm);
                new AlertResultWindow(roomListResult, norm);
            }
        });

    }

    private void handleImport() {
        new JsonLoadDialog(new ImportResultListener() {
            @Override
            public void onImportFinish(List<Building> buildingList) {
                if (buildingList == null)
                    JOptionPane.showMessageDialog(null, Constants.IMPORT_FAILED);
                else
                    model.clearAndImportBuildings(buildingList);
            }
        });
    }

    private void handleExport() {
        new JsonSaveDialog(null, model.getBuildingList());
    }
}
