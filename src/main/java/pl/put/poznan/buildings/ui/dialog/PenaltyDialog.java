package pl.put.poznan.buildings.ui.dialog;

import pl.put.poznan.buildings.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PenaltyDialog extends AbstractDialog {


    private JTextArea normArea;
    private JTextArea penaltyArea;
    private NormAndPenaltyProvided resultListener;

    public PenaltyDialog(NormAndPenaltyProvided listener) {
        super(null, Constants.PENALTY_TITLE);
        this.resultListener = listener;
        pack();
    }

    @Override
    public void addElements() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));
        JLabel normDesc = new JLabel(Constants.ALERT_NORM_DESC);
        normArea = new JTextArea(1, 20);
        mainPanel.add(normDesc);
        mainPanel.add(normArea);
        JLabel penaltyDesc = new JLabel(Constants.PENALTY_DESC);
        penaltyArea = new JTextArea(1, 20);
        mainPanel.add(penaltyDesc);
        mainPanel.add(penaltyArea);
        JButton acceptButton = new JButton(Constants.CALCULATE_DESC);
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
                String normString = normArea.getText();
                String penaltyString = penaltyArea.getText();
                if (normString != null && normString.length() > 0 && penaltyString != null && penaltyString.length() > 0) {
                    normString = normString.trim();
                    penaltyString = penaltyString.trim();
                    float norm;
                    float penalty;
                    try {
                        norm = Float.parseFloat(normString);
                        penalty = Float.parseFloat(penaltyString);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, Constants.INVALID_DATA_MESSAGE);
                        return;
                    }
                    if (norm <= 0) {
                        JOptionPane.showMessageDialog(null, Constants.INVALID_DATA_MESSAGE);
                        return;
                    }
                    if (resultListener != null)
                        resultListener.onProvided(norm, penalty);
                    setVisible(false);
                }
                break;
            case (ACTION_CANCEL):
                setVisible(false);
                break;
        }
    }
}
