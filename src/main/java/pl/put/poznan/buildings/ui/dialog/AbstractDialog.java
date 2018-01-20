package pl.put.poznan.buildings.ui.dialog;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * AbstractDialog for accept/cancel choice
 * Any additional Ui elements are added with addElements() method
 * In addition AbstractDialog takes DialogResultListener as a param to return model of form filled by user
 * Title for dialog must be provided in constructor
 */
abstract class AbstractDialog extends JDialog implements ActionListener {
    /**
     * Constant assigned to accept button in dialog
     */
    static final String ACTION_ACCEPT = "accept";
    /**
     *Constant assigned to cancel button in dialog
     */
    static final String ACTION_CANCEL = "cancel";

    /**
     * Listener used to perform declared action after accept or cancel button is pressed
     */
    DialogResultListener listener;

    /**
     * Constructor for Dialog,
     * @param listener listener used to perform action after dialog is disposed
     * @param title title for dialog window
     */
    AbstractDialog(DialogResultListener listener, String title) {
        this.listener = listener;
        setDialogTitle(title);
        addElements();
        setVisible(true);
        pack();
    }

    /**
     * Function that adds content to dialog in form of any kind of JComponent
     */
    abstract void addElements();

    /**
     * Private function that set-ups dialog title after creation
     * @param title
     */
    private void setDialogTitle(String title) {
        setTitle(title);
    }
}
