package View;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AiFlagsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox CHECK_BAD_MOVECheckBox;
    private JCheckBox TRY_TO_FAINTCheckBox;
    private JCheckBox CHECK_VIABILITYCheckBox;
    private JCheckBox SETUP_FIRST_TURNCheckBox;
    private JCheckBox RISKYCheckBox;
    private JCheckBox PREFER_STRONGEST_MOVECheckBox;
    private JCheckBox PREFER_BATON_PASSCheckBox;
    private JCheckBox HP_AWARECheckBox;
    private JCheckBox NEGATE_UNAWARECheckBox;
    private JCheckBox WILL_SUICIDECheckBox;
    private JCheckBox HELP_PARTNERCheckBox;
    private JCheckBox PREFER_STATUS_MOVESCheckBox;
    private JCheckBox STALLCheckBox;
    private JCheckBox SCREENERCheckBox;
    private JCheckBox SMART_SWITCHINGCheckBox;
    private JCheckBox ACE_POKEMONCheckBox;
    private JCheckBox OMNISCIENTCheckBox;
    private ArrayList<String> newFlags;
    private JCheckBox[] boxes = new JCheckBox[]{CHECK_BAD_MOVECheckBox, TRY_TO_FAINTCheckBox, CHECK_VIABILITYCheckBox,
            SETUP_FIRST_TURNCheckBox, RISKYCheckBox, PREFER_STRONGEST_MOVECheckBox, PREFER_BATON_PASSCheckBox,
            HP_AWARECheckBox, NEGATE_UNAWARECheckBox, WILL_SUICIDECheckBox, HELP_PARTNERCheckBox, PREFER_STATUS_MOVESCheckBox,
            STALLCheckBox, SCREENERCheckBox, SMART_SWITCHINGCheckBox, ACE_POKEMONCheckBox, OMNISCIENTCheckBox};

    public AiFlagsDialog(ArrayList<String> oldFlags) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Editing AI Flags...");

        newFlags = oldFlags;

        for (JCheckBox box : boxes)
            box.setSelected(oldFlags.contains(box.getText()));

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
    }

    private void onOK() {
        newFlags = new ArrayList<>();

        for (JCheckBox box : boxes)
        {
            if (box.isSelected())
                newFlags.add(box.getText());
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public ArrayList<String> showAndWait() {
        setVisible(true);
        return newFlags;
    }
}
