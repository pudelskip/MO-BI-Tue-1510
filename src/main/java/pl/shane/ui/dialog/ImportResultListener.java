package pl.shane.ui.dialog;

import pl.shane.model.Building;

import java.util.List;

public interface ImportResultListener {
    void onImportFinish(List<Building> buildingList);
}
