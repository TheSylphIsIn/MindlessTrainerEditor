package View;

import Model.Trainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScriptDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea introTextBox;
    private JTextArea defeatTextBox;
    private JTextArea postBattleTextBox;
    private JLabel introCharsLabel;
    private JLabel defeatCharsLabel;
    private JLabel postCharsLabel;
    private Trainer data;

    public ScriptDialog(Trainer data) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Editing " + data.getLabel() + "'s simple script...");

        introTextBox.setText(data.getIntroText());
        defeatTextBox.setText(data.getDefeatText());
        postBattleTextBox.setText(data.getPostBattleText());
        setScriptCharsLabel(introTextBox, introCharsLabel);
        setScriptCharsLabel(defeatTextBox, defeatCharsLabel);
        setScriptCharsLabel(postBattleTextBox, postCharsLabel);

        this.data = data;

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

        introTextBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                setScriptCharsLabel(introTextBox, introCharsLabel);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        introTextBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                setScriptCharsLabel(introTextBox, introCharsLabel);
            }
        });

        defeatTextBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                setScriptCharsLabel(defeatTextBox, defeatCharsLabel);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        defeatTextBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                setScriptCharsLabel(defeatTextBox, defeatCharsLabel);
            }
        });

        postBattleTextBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                setScriptCharsLabel(postBattleTextBox, postCharsLabel);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        postBattleTextBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                setScriptCharsLabel(postBattleTextBox, postCharsLabel);
            }
        });
    }

    private void onOK() {
        data.setIntroText(introTextBox.getText());
        data.setDefeatText(defeatTextBox.getText());
        data.setPostBattleText(postBattleTextBox.getText());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void setScriptCharsLabel(JTextArea textBox, JLabel label) {
        String[] lines = textBox.getText().split("\n");
        int length = 28;
        for (String line : lines)
        {
            if (line.length() > length) {
                label.setText("" + line.length());
                if (line.length() > 36)
                    label.setForeground(Color.red);
                else if (line.length() > 30)
                    label.setForeground(Color.orange);
                else
                    label.setForeground(Color.black);
                length = line.length();
            }
        }
        if (length <= 28)
            label.setText("");
    }
}
