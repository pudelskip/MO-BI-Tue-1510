package pl.put.poznan.buildings.ui.dialog;

import javax.swing.*;
import java.awt.event.ActionListener;

/*
 * AbstractDialog for accept/cancel choice
 * Any additional Ui elements are added with addElements() method
 * In addition AbstractDialog takes DialogResultListener as a param to return model of form filled by user
 * Title for dialog must be provided in constructor
 */
abstract class AbstractDialog extends JDialog implements ActionListener {
    static final String ACTION_ACCEPT = "accept";
    static final String ACTION_CANCEL = "cancel";

    DialogResultListener listener;

    AbstractDialog(DialogResultListener listener, String title) {
        this.listener = listener;
        setDialogTitle(title);
        addElements();
        setVisible(true);
        pack();
    }

    abstract void addElements();

    private void setDialogTitle(String title) {
        setTitle(title);
    }
}
