package View;

import Data.Move;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MovesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton useLevelUpMovesetButton;
    private ArrayList<String> result;

    public MovesDialog(ArrayList<String> moves) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Editing moveset");

        result = moves;

        DefaultComboBoxModel newModel = new DefaultComboBoxModel();
        newModel.addAll(getAllMoves());

        comboBox1.setModel(newModel);
        comboBox1.setSelectedItem(moves.get(0));

        newModel = new DefaultComboBoxModel();
        newModel.addAll(getAllMoves());

        comboBox2.setModel(newModel);
        comboBox2.setSelectedItem(moves.get(1));

        newModel = new DefaultComboBoxModel();
        newModel.addAll(getAllMoves());

        comboBox3.setModel(newModel);
        comboBox3.setSelectedItem(moves.get(2));

        newModel = new DefaultComboBoxModel();
        newModel.addAll(getAllMoves());

        comboBox4.setModel(newModel);
        comboBox4.setSelectedItem(moves.get(3));

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

        useLevelUpMovesetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = new ArrayList<>(Arrays.asList("NONE", "NONE", "NONE", "NONE"));
                dispose();
            }
        });
    }

    private void onOK() {
        // add your code here
        ArrayList<String> newMoves = new ArrayList<>();
        newMoves.add((String) comboBox1.getSelectedItem());
        newMoves.add((String) comboBox2.getSelectedItem());
        newMoves.add((String) comboBox3.getSelectedItem());
        newMoves.add((String) comboBox4.getSelectedItem());
        result = newMoves;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public ArrayList<String> showAndWait() {
        setVisible(true);
        return result;
    }


    private ArrayList<String> getAllMoves() {
        ArrayList<String> moves = new ArrayList<>();
        for (Move m : Move.values())
            moves.add(m.name());

        return moves;
    }
}
