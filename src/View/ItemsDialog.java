package View;

import Data.Item;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ItemsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private ArrayList<String> newItems;

    public ItemsDialog(ArrayList<String> oldItems) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Editing Trainer's usable items");

        newItems = oldItems;

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

        DefaultComboBoxModel model1 = new DefaultComboBoxModel<>();
        model1.addAll(getAllItems());
        comboBox1.setModel(model1);
        comboBox1.setSelectedItem(oldItems.get(0));
        DefaultComboBoxModel model2 = new DefaultComboBoxModel<>();
        model2.addAll(getAllItems());
        comboBox2.setModel(model2);
        comboBox2.setSelectedItem(oldItems.get(1));
        DefaultComboBoxModel model3 = new DefaultComboBoxModel<>();
        model3.addAll(getAllItems());
        comboBox3.setModel(model3);
        comboBox3.setSelectedItem(oldItems.get(2));
        DefaultComboBoxModel model4 = new DefaultComboBoxModel<>();
        model4.addAll(getAllItems());
        comboBox4.setModel(model4);
        comboBox4.setSelectedItem(oldItems.get(3));
    }

    private void onOK() {
        newItems = new ArrayList<>();
        newItems.add((String) comboBox1.getSelectedItem());
        newItems.add((String) comboBox2.getSelectedItem());
        newItems.add((String) comboBox3.getSelectedItem());
        newItems.add((String) comboBox4.getSelectedItem());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private ArrayList<String> getAllItems() {
        ArrayList<String> items = new ArrayList<>();
        for (Item name : Item.values())
        {
            items.add(name.name());
        }

        return items;
    }

    public ArrayList<String> showAndWait() {
        setVisible(true);
        return newItems;
    }
}
