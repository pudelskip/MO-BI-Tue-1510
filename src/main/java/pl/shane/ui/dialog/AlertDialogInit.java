package pl.shane.ui.dialog;

import pl.shane.utils.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AlertDialogInit extends AbstractDialog {

    private JTextArea normArea;
    private NormResultListener normListener;

    public AlertDialogInit(NormResultListener listener) {
        super(null, Constants.FIND_ALERT_TITLE);
        this.normListener = listener;
        pack();
    }

    @Override
    public void addElements() {
        JPanel mainPanel = new JPanel();
        JLabel normDesc = new JLabel(Constants.ALERT_NORM_DESC);
        normArea = new JTextArea(1, 20);
        mainPanel.add(normDesc);
        mainPanel.add(normArea);
        JButton acceptButton = new JButton(Constants.SEARCH_DESC);
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
                if (normString != null && normString.length() > 0) {
                    normString = normString.trim();
                    float norm;
                    try {
                        norm = Float.parseFloat(normString);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, Constants.INVALID_DATA_MESSAGE);
                        return;
                    }
                    if (norm <= 0) {
                        JOptionPane.showMessageDialog(null, Constants.INVALID_DATA_MESSAGE);
                        return;
                    }
                    if (normListener != null)
                        normListener.onNormProvided(norm);
                    setVisible(false);
                }
                break;
            case (ACTION_CANCEL):
                setVisible(false);
                break;
        }
    }
}
