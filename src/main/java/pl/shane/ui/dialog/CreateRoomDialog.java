package pl.shane.ui.dialog;

import pl.shane.model.Room;
import pl.shane.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * Dialog that works as a form for creating a room
 */
public class CreateRoomDialog extends AbstractDialog {

    private JTextArea idArea;
    private JTextArea nameArea;
    private JTextArea areaArea;
    private JTextArea volumeArea;
    private JTextArea heatArea;
    private JTextArea lightArea;

    public CreateRoomDialog(DialogResultListener listener) {
        super(listener, Constants.CREATE_ROOM_TITLE);
        pack();
    }

    @Override
    public void addElements() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2));
        JLabel idDesc = new JLabel(Constants.ID_DESC);
        JLabel nameDesc = new JLabel(Constants.NAME_DESC);
        JLabel areaDesc = new JLabel(Constants.AREA_DESC);
        JLabel volumeDesc = new JLabel(Constants.VOLUME_DESC);
        JLabel heatDesc = new JLabel(Constants.HEAT_DESC);
        JLabel lightDesc = new JLabel(Constants.LIGHT_DESC);
        idArea = new JTextArea(1, 20);
        nameArea = new JTextArea(1, 20);
        areaArea = new JTextArea(1, 20);
        volumeArea = new JTextArea(1, 20);
        heatArea = new JTextArea(1, 20);
        lightArea = new JTextArea(1, 20);
        mainPanel.add(idDesc);
        mainPanel.add(nameDesc);
        mainPanel.add(idArea);
        mainPanel.add(nameArea);
        mainPanel.add(areaDesc);
        mainPanel.add(volumeDesc);
        mainPanel.add(areaArea);
        mainPanel.add(volumeArea);
        mainPanel.add(heatDesc);
        mainPanel.add(lightDesc);
        mainPanel.add(heatArea);
        mainPanel.add(lightArea);
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
                String area = areaArea.getText();
                String volume = volumeArea.getText();
                String light = lightArea.getText();
                String heat = heatArea.getText();
                if (id != null && id.length() > 0 && name != null && name.length() > 0) {
                    name = name.trim();
                    int idInteger;
                    float areaFloat;
                    float volumeFloat;
                    float lightFloat;
                    float heatFloat;
                    try {
                        idInteger = Integer.parseInt(id);
                        areaFloat = Float.parseFloat(area);
                        volumeFloat = Float.parseFloat(volume);
                        lightFloat = Float.parseFloat(light);
                        heatFloat = Float.parseFloat(heat);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, Constants.INVALID_DATA_MESSAGE);
                        return;
                    }
                    Room result = new Room(idInteger, name, areaFloat, volumeFloat, heatFloat, lightFloat);
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
