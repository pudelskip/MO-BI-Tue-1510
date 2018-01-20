package pl.put.poznan.buildings.ui.mainwindow;

import pl.put.poznan.buildings.utils.Constants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static pl.put.poznan.buildings.ui.mainwindow.MainWindowController.*;


/*
 * mainwindow works as a root container for views
 * It main task is to display ui and views and fetch actions performed by user
 */
public class MainWindow extends JFrame implements ActionListener, ListSelectionListener, ControllerChangeListener, FocusListener {

    private static final float HEIGHT_SCREEN_RATIO = 0.9f;
    private static final float WIDTH_SCREEN_RATIO = 0.9f;

    private int height;
    private int width;

    private JList buildingList;
    private JList floorList;
    private JList roomList;

    private MainWindowController controller;

    public MainWindow() {
        initController();
        setupFrame();
        addLists();
        addFunctionPane();
        setVisible(true);
        pack();
    }

    private void initController() {
        controller = new MainWindowController(this);

    }

    private void setupFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Constants.APP_TITLE);
        height = screenSize.height;
        width = screenSize.width;
        setSize((int) (height * HEIGHT_SCREEN_RATIO), (int) (width * WIDTH_SCREEN_RATIO));
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 3));
    }

    private void addLists() {
        Container container = getContentPane();
        buildingList = generateList(
                container,
                ACTION_ADD_BUILDING,
                ACTION_REMOVE_BUILDING,
                controller.getBuildingListModel());
        floorList = generateList(container,
                ACTION_ADD_FLOOR,
                ACTION_REMOVE_FLOOR,
                controller.getFloorListModel());
        roomList = generateList(container,
                ACTION_ADD_ROOM,
                ACTION_REMOVE_ROOM,
                controller.getRoomListModel());


    }

    private JList generateList(Container container, String positiveButtonAction, String negativeButtonAction, DefaultListModel<String> listModel) {
        JPanel layout = new JPanel();
        layout.setLayout(new GridLayout(2, 1));
        JList list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setSize(100, 300);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(this);
        list.addFocusListener(this);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 300));
        layout.add(listScroller);
        JPanel buttonLayout = new JPanel();
        buttonLayout.setLayout(new GridLayout(1, 2));
        buttonLayout.setSize(100, 10);

        JButton addButton = new JButton("+");
        addButton.setActionCommand(positiveButtonAction);
        addButton.addActionListener(this);

        buttonLayout.add(addButton);
        JButton removeButton = new JButton("-");
        removeButton.setActionCommand(negativeButtonAction);
        removeButton.addActionListener(this);

        buttonLayout.add(removeButton);
        layout.add(buttonLayout);
        container.add(layout);
        return list;
    }

    private void addFunctionPane() {
        Container contentPane = getContentPane();
        JPanel layout = new JPanel();
        layout.setLayout(new GridLayout(5, 1));
        addButton(Constants.FUN_AREA_DESC, ACTION_FUN_AREA, layout);
        addButton(Constants.FUN_VOLUME_DESC, ACTION_FUN_VOLUME, layout);
        addButton(Constants.FUN_HEAT_DESC, ACTION_FUN_HEAT, layout);
        addButton(Constants.FUN_LIGHT_DESC, ACTION_FUN_LIGHT, layout);
        addButton(Constants.FUN_ALERT_DESC, ACTION_FUN_ALERT, layout);
        addButton(Constants.FUN_PENALTY_DESC, ACTION_PENALTY, layout);
        addButton(Constants.FUN_IMPORT_JSON_DESC, ACTION_IMPORT_JSON, layout);
        addButton(Constants.FUN_EXPORT_JSON_DESC, ACTION_EXPORT_JSON, layout);
        contentPane.add(layout);
    }

    private void addButton(String description, String action, Container layout) {
        JButton button = new JButton(description);
        button.setActionCommand(action);
        button.addActionListener(this);
        layout.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.actionPerformed(actionEvent,
                buildingList.getSelectedIndex(),
                floorList.getSelectedIndex(),
                roomList.getSelectedIndex());
        this.invalidate();
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        Object source = listSelectionEvent.getSource();
        if (source == buildingList) {
            controller.updateBuilding(buildingList.getSelectedIndex());
            controller.clearRoomListModel();
            floorList.clearSelection();
            roomList.clearSelection();
        }
        if (source == floorList) {
            controller.updateFloor(buildingList.getSelectedIndex(), floorList.getSelectedIndex());
            roomList.clearSelection();
        }
        this.invalidate();
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        Object source = focusEvent.getSource();
        if (source == buildingList) {
            floorList.clearSelection();
            roomList.clearSelection();
            controller.clearRoomListModel();
        }
        if (source == floorList) {
            roomList.clearSelection();
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }

    @Override
    public void onDataInvalidate(int buildingIndex, int floorIndex, int roomIndex) {
        this.invalidate();
        buildingList.setSelectedIndex(buildingIndex);
        floorList.setSelectedIndex(floorIndex);
        roomList.setSelectedIndex(roomIndex);
    }
}
