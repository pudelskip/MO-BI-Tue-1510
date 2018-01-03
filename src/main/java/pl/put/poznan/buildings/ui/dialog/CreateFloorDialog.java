package pl.put.poznan.buildings.ui.dialog;

import pl.put.poznan.buildings.model.Floor;
import pl.put.poznan.buildings.utils.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;

/*
 * Dialog that works as a form for creating a floor
 */
public class CreateFloorDialog extends AbstractDialog {
    private JTextArea idArea;
    private JTextArea nameArea;

    public CreateFloorDialog(DialogResultListener listener) {
        super(listener, Constants.CREATE_FLOOR_TITLE);
        pack();
    }

    @Override
    public void addElements() {
        JPanel mainPanel = new JPanel();
        JLabel idDesc = new JLabel(Constants.ID_DESC);
        JLabel nameDesc = new JLabel(Constants.NAME_DESC);
        idArea = new JTextArea(1, 20);
        nameArea = new JTextArea(1, 20);
        mainPanel.add(idDesc);
        mainPanel.add(idArea);
        mainPanel.add(nameDesc);
        mainPanel.add(nameArea);
        JButton acceptButton = new JButton(Constants.ADD_DESC);
        JButton cancelButton = new JButton(Constants.GO_BACK_DESC);
        acceptButton.setActionCommand(ACTION_ACCEPT);
        acceptButton.addActionListener(this);
        cancelButton.setActionCommand(ACTION_CANCEL);
        cancelButton.addActionListener(this);
        mainPanel.add(acceptButton);
        mainPanel.add(cancelButton);
        this.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case (ACTION_ACCEPT):
                String id = idArea.getText();
                String name = nameArea.getText();
                if (id != null && id.length() > 0 && name != null && name.length() > 0) {
                    name = name.trim();
                    int idInteger;
                    try {
                        idInteger = Integer.parseInt(id);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, Constants.INVALID_DATA_MESSAGE);
                        return;
                    }
                    Floor result = new Floor(idInteger, name);
                    if (listener != null)
                        listener.onDialogResult(result);
                    setVisible(false);
                }
                break;
            case (ACTION_CANCEL):
                setVisible(false);
                break;
        }
    }
}
