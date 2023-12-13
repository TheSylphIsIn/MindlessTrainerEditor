package View;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddTrainerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JCheckBox copyTrainerDataFromCheckBox;
    private JComboBox comboBox1;
    private ArrayList<String> returnValues = new ArrayList<String>();

    public AddTrainerDialog(ArrayList<String> trainerNames) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Add new Trainer?");

        int i = 0;
        String defaultLabel;
        do {
            i++;
            defaultLabel = "Nemo" + i;
        } while (trainerNames.contains(defaultLabel));

        returnValues.add(defaultLabel);

        DefaultComboBoxModel model1 = new DefaultComboBoxModel<>();
        model1.addAll(trainerNames);
        comboBox1.setModel(model1);
        comboBox1.setSelectedIndex(0);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        copyTrainerDataFromCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox1.setEnabled(copyTrainerDataFromCheckBox.isSelected());
            }
        });
    }

    private void onOK() {
        if (!textField1.getText().equals(""))
            returnValues.set(0, textField1.getText());
        if (copyTrainerDataFromCheckBox.isSelected())
            returnValues.add((String) comboBox1.getSelectedItem());
        else
            returnValues.add("NONE");
        dispose();
    }

    private void onCancel() {
        returnValues.set(0, "NONE");
        returnValues.add("NONE");
        dispose();
    }

    public ArrayList<String> showAndWait()
    {
        setVisible(true);
        return returnValues;
    }
}
