package pl.put.poznan.buildings.ui.dialog;


import pl.put.poznan.buildings.model.Location;

/*
 * Listener used in AbstractDialog used to perform action after filling the form by user
 */
public interface DialogResultListener {
    public <T extends Location> void onDialogResult(T resultModel);
}
