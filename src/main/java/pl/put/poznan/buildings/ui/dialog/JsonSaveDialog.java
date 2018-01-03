package pl.put.poznan.buildings.ui.dialog;

import com.google.gson.Gson;
import pl.put.poznan.buildings.model.Building;
import pl.put.poznan.buildings.utils.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/*
 * Dialog used to export an external json with generated data
 */
public class JsonSaveDialog extends AbstractDialog {

    private JTextArea filePathArea;
    private ImportResultListener listener;

    private List<Building> buildingList;

    public JsonSaveDialog(ImportResultListener listener, List<Building> data) {
        super(null, Constants.EXPORT_EXTERNAL_JSON);
        this.listener = listener;
        buildingList = data;
        pack();
    }

    @Override
    public void addElements() {
        JPanel mainPanel = new JPanel();
        JLabel filePathDesc = new JLabel(Constants.JSON_SAVE_DESC);
        filePathArea = new JTextArea(1, 20);
        filePathArea.setText("/home/szopenowski/studiaspace/jsontest.json");
        mainPanel.add(filePathDesc);
        mainPanel.add(filePathArea);
        JButton acceptButton = new JButton(Constants.SAVE_DESC);
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
                if (buildingList == null || buildingList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, Constants.EXPORT_NO_DATA);
                    return;
                }
                String filePath = filePathArea.getText();
                if (filePath != null && filePath.length() > 0) {
                    File jsonFile = new File(filePath);
                    if (jsonFile.exists()) {
                        boolean delete = jsonFile.delete();
                        if (!delete) {
                            JOptionPane.showMessageDialog(null, Constants.DELETE_OLD_FILE_FAIL);
                            return;
                        }
                    }
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(buildingList);
                    saveFile(jsonFile, jsonString);
                    setVisible(false);
                }
                break;
            case (ACTION_CANCEL):
                setVisible(false);
                break;
        }
    }

    private void saveFile(File file, String content) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print(content);
            writer.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, Constants.EXPORT_FAILED);
        }
    }
}
