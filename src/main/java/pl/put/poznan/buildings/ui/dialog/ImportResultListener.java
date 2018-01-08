package pl.put.poznan.buildings.ui.dialog;


import pl.put.poznan.buildings.model.Building;

import java.util.List;

public interface ImportResultListener {
    void onImportFinish(List<Building> buildingList);
}
