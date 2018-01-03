package pl.put.poznan.buildings.ui.dialog;


import com.google.gson.Gson;
import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.utils.Constants;
import pl.put.poznan.buildings.utils.IntegrityValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Dialog used to import an external json with data
 */
public class JsonLoadDialog extends AbstractDialog {

    private JTextArea filePathArea;
    private ImportResultListener listener;

    public JsonLoadDialog(ImportResultListener listener) {
        super(null, Constants.IMPORT_EXTERNAL_JSON);
        this.listener = listener;
        pack();
    }

    @Override
    public void addElements() {
        JPanel mainPanel = new JPanel();
        JLabel filePathDesc = new JLabel(Constants.JSON_DESC);
        filePathArea = new JTextArea(1, 20);
        filePathArea.setText("/home/szopenowski/studiaspace/jsontest.json");
        mainPanel.add(filePathDesc);
        mainPanel.add(filePathArea);
        JButton acceptButton = new JButton(Constants.LOAD_DESC);
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
                String filePath = filePathArea.getText();
                if (filePath != null && filePath.length() > 0) {
                    File jsonFile = new File(filePath);
                    if (!jsonFile.exists()) {
                        JOptionPane.showMessageDialog(null, Constants.FILE_DOES_NOT_EXISTS);
                        return;
                    }
                    Building[] buildingArray = null;
                    try {
                        String json = readFile(filePath);
                        Gson gson = new Gson();
                        buildingArray = gson.fromJson(json, Building[].class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (buildingArray != null) {
                        List<Building> buildingList = new ArrayList<>(Arrays.asList(buildingArray));
                        boolean nonValid = IntegrityValidator.doesModelContainsIdDuplicates(buildingList);
                        if (nonValid)
                            JOptionPane.showMessageDialog(null, Constants.IMPORT_DATA_SET_NON_VALID);
                        else
                            listener.onImportFinish(buildingList);
                        setVisible(false);
                    } else
                        listener.onImportFinish(null);
                }
                break;
            case (ACTION_CANCEL):
                setVisible(false);
                break;
        }
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
