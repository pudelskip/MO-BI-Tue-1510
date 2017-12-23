package pl.shane.ui.dialog;

import pl.shane.model.Location;

/*
 * Listener used in AbstractDialog used to perform action after filling the form by user
 */
public interface DialogResultListener {
    public <T extends Location> void onDialogResult(T resultModel);
}
