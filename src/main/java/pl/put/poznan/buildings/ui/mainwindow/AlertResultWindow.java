package pl.put.poznan.buildings.ui.mainwindow;
import pl.put.poznan.buildings.model.Room;
import pl.put.poznan.buildings.utils.Constants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

import static pl.put.poznan.buildings.utils.Constants.*;


public class AlertResultWindow extends JFrame implements ListSelectionListener {

    private static final float HEIGHT_SCREEN_RATIO = 0.8f;
    private static final float WIDTH_SCREEN_RATIO = 0.8f;

    private DefaultListModel<String> listModel;
    private List<Room> data;
    private Float norm;
    private JList roomList;

    private JLabel idValue;
    private JLabel nameValue;
    private JLabel areaValue;
    private JLabel volumeValue;
    private JLabel lightValue;
    private JLabel heatValue;
    private JLabel normValue;
    private JLabel normCalValue;

    AlertResultWindow(List<Room> data, Float norm) {
        this.data = data;
        this.norm = norm;
        setupFrame();
        setupModelList();
        addLists();
        addRoomInfoPane();
        setVisible(true);
    }

    private void setupFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.ALERT_RESULT_TITLE);
        int height = screenSize.height;
        int width = screenSize.width;
        setSize((int) (height * HEIGHT_SCREEN_RATIO), (int) (width * WIDTH_SCREEN_RATIO));
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));
    }

    private void setupModelList() {
        listModel = new DefaultListModel<>();
        for (Room room : data)
            listModel.addElement(room.toString());
    }

    private void addLists() {
        Container contentPane = getContentPane();
        roomList = new JList<>(listModel);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        roomList.setLayoutOrientation(JList.VERTICAL_WRAP);
        roomList.setSize(100, 300);
        roomList.setVisibleRowCount(-1);
        roomList.addListSelectionListener(this);
        contentPane.add(roomList);
    }

    private void addRoomInfoPane() {
        JPanel layout = new JPanel(new GridLayout(8, 2));
        JLabel idDesc = new JLabel(ROOM_ID_DESC);
        JLabel nameDesc = new JLabel(ROOM_NAME_DESC);
        JLabel areaDesc = new JLabel(AREA_DESC);
        JLabel volumeDesc = new JLabel(VOLUME_DESC);
        JLabel lightDesc = new JLabel(LIGHT_DESC);
        JLabel heatDesc = new JLabel(HEAT_DESC);
        JLabel normDesc = new JLabel(NORM_DESC);
        JLabel normCalDesc = new JLabel(CALCULATED_NORM_DESC);
        idValue = new JLabel();
        nameValue = new JLabel();
        areaValue = new JLabel();
        volumeValue = new JLabel();
        lightValue = new JLabel();
        heatValue = new JLabel();
        normValue = new JLabel();
        normCalValue = new JLabel();
        layout.add(idDesc);
        layout.add(idValue);
        layout.add(nameDesc);
        layout.add(nameValue);
        layout.add(areaDesc);
        layout.add(areaValue);
        layout.add(volumeDesc);
        layout.add(volumeValue);
        layout.add(lightDesc);
        layout.add(lightValue);
        layout.add(heatDesc);
        layout.add(heatValue);
        layout.add(normDesc);
        layout.add(normValue);
        layout.add(normCalDesc);
        layout.add(normCalValue);
        Container contentPane = getContentPane();
        contentPane.add(layout);
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int selectedIndex = roomList.getSelectedIndex();
        Room room = data.get(selectedIndex);
        idValue.setText(String.valueOf(room.getId()));
        nameValue.setText(room.getName());
        areaValue.setText(String.valueOf(room.getArea()));
        volumeValue.setText(String.valueOf(room.getCubeVolume()));
        lightValue.setText(String.valueOf(room.getLightPower()));
        heatValue.setText(String.valueOf(room.getHeating()));
        normValue.setText(String.valueOf(norm));
        normCalValue.setText(String.valueOf(room.calculateEnergyToVolumeConsumption()));

    }
}
