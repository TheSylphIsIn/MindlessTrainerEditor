package View;

import Model.Trainer;
import Model.TrainerMon;

import javax.swing.*;
import java.awt.event.*;

public class AddRemoveMonDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox normalPartyCheckBox;
    private JCheckBox hardPartyCheckBox;
    private JCheckBox unfairPartyCheckBox;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JCheckBox copyFromCheckBox;
    private JCheckBox copyUnfair;
    private JCheckBox copyHard;
    private Trainer trainer;
    Boolean addRemove;

    public AddRemoveMonDialog(Trainer addTo, Boolean add) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        addRemove = add;

        trainer = addTo;


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

        if (addRemove)
            initForAdd();
        else
            initForRemove();
    }

    private void initForAdd() {
        setTitle("Add Mon to...");
        int i;

        if (trainer.getParties().get(0).getNormalParty().size() < 6) {
            DefaultComboBoxModel normBoxModel = new DefaultComboBoxModel();
            for (i = 0; i < trainer.getParties().get(0).getNormalParty().size(); i++) {
                normBoxModel.addElement("Mon " + i);
            }
            comboBox1.setModel(normBoxModel);
            comboBox1.setSelectedIndex(0);
        } else {
            normalPartyCheckBox.setEnabled(false);
            copyFromCheckBox.setEnabled(false);
        }

        if (trainer.getParties().get(0).getHardParty().size() < 6) {
            DefaultComboBoxModel hardBoxModel = new DefaultComboBoxModel();
            for (i = 0; i < trainer.getParties().get(0).getHardParty().size(); i++) {
                hardBoxModel.addElement("Mon " + i);
            }
            comboBox2.setModel(hardBoxModel);
            comboBox2.setSelectedIndex(0);
        } else {
            hardPartyCheckBox.setEnabled(false);
            copyHard.setEnabled(false);
        }

        if (trainer.getParties().get(0).getUnfairParty().size() < 6) {
            DefaultComboBoxModel unfairBoxModel = new DefaultComboBoxModel();
            for (i = 0; i < trainer.getParties().get(0).getUnfairParty().size(); i++) {
                unfairBoxModel.addElement("Mon " + i);
            }
            comboBox3.setModel(unfairBoxModel);
            comboBox3.setSelectedIndex(0);
        } else {
            unfairPartyCheckBox.setEnabled(false);
            copyUnfair.setEnabled(false);
        }

        copyFromCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox1.setEnabled(copyFromCheckBox.isSelected());
            }
        });
        copyHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox2.setEnabled(copyHard.isSelected());
            }
        });
        copyUnfair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox3.setEnabled(copyUnfair.isSelected());
            }
        });
    }

    private void initForRemove() {
        setTitle("Delete mon from...");
        int i;

        if (trainer.getParties().get(0).getNormalParty().size() > 1) {
            DefaultComboBoxModel normBoxModel = new DefaultComboBoxModel();
            for (i = 0; i < trainer.getParties().get(0).getNormalParty().size(); i++) {
                normBoxModel.addElement("Mon " + i);
            }
            comboBox1.setModel(normBoxModel);
            comboBox1.setEnabled(true);
            comboBox1.setSelectedIndex(0);
        } else {
            normalPartyCheckBox.setEnabled(false);
        }

        if (trainer.getParties().get(0).getHardParty().size() > 1) {
            DefaultComboBoxModel hardBoxModel = new DefaultComboBoxModel();
            for (i = 0; i < trainer.getParties().get(0).getHardParty().size(); i++) {
                hardBoxModel.addElement("Mon " + i);
            }
            comboBox2.setModel(hardBoxModel);
            comboBox2.setEnabled(true);
            comboBox2.setSelectedIndex(0);
        } else {
            hardPartyCheckBox.setEnabled(false);
        }

        if (trainer.getParties().get(0).getUnfairParty().size() > 1) {
            DefaultComboBoxModel unfairBoxModel = new DefaultComboBoxModel();
            for (i = 0; i < trainer.getParties().get(0).getUnfairParty().size(); i++) {
                unfairBoxModel.addElement("Mon " + i);
            }
            comboBox3.setModel(unfairBoxModel);
            comboBox3.setEnabled(true);
            comboBox3.setSelectedIndex(0);
        } else {
            unfairPartyCheckBox.setEnabled(false);
        }

        copyFromCheckBox.setVisible(false);
        copyHard.setVisible(false);
        copyUnfair.setVisible(false);
    }


    private void onOK() {
        if (addRemove)
            addMon();
        else
            removeMon();
        dispose();
    }

    private void addMon() {
        if (normalPartyCheckBox.isSelected())
        {
            if (trainer.getStarterDependent()) {
                for (int i = 0; i < Trainer.NUM_STARTER_SETS * Trainer.NUM_STARTERS; i++) {
                    TrainerMon mon = new TrainerMon();
                    if (copyFromCheckBox.isSelected())
                        mon.copyFrom(trainer.getParties().get(i).getNormalParty().get(comboBox1.getSelectedIndex()));
                    trainer.getParties().get(i).getNormalParty().add(mon);
                }
            } else {
                TrainerMon mon = new TrainerMon();
                if (copyFromCheckBox.isSelected())
                    mon.copyFrom(trainer.getParties().get(0).getNormalParty().get(comboBox1.getSelectedIndex()));
                trainer.getParties().get(0).getNormalParty().add(mon);
            }
        }
        if (hardPartyCheckBox.isSelected())
        {
            if (trainer.getStarterDependent()) {
                for (int i = 0; i < Trainer.NUM_STARTER_SETS * Trainer.NUM_STARTERS; i++) {
                    TrainerMon mon = new TrainerMon();
                    if (copyFromCheckBox.isSelected())
                        mon.copyFrom(trainer.getParties().get(i).getHardParty().get(comboBox2.getSelectedIndex()));
                    trainer.getParties().get(i).getHardParty().add(mon);
                }
            } else {
                TrainerMon mon = new TrainerMon();
                if (copyFromCheckBox.isSelected())
                    mon.copyFrom(trainer.getParties().get(0).getHardParty().get(comboBox2.getSelectedIndex()));
                trainer.getParties().get(0).getHardParty().add(mon);
            }
        }
        if (unfairPartyCheckBox.isSelected())
        {
            if (trainer.getStarterDependent()) {
                for (int i = 0; i < Trainer.NUM_STARTER_SETS * Trainer.NUM_STARTERS; i++) {
                    TrainerMon mon = new TrainerMon();
                    if (copyFromCheckBox.isSelected())
                        mon.copyFrom(trainer.getParties().get(i).getUnfairParty().get(comboBox3.getSelectedIndex()));
                    trainer.getParties().get(i).getUnfairParty().add(mon);
                }
            } else {
                TrainerMon mon = new TrainerMon();
                if (copyFromCheckBox.isSelected())
                    mon.copyFrom(trainer.getParties().get(0).getUnfairParty().get(comboBox3.getSelectedIndex()));
                trainer.getParties().get(0).getUnfairParty().add(mon);
            }
        }
    }

    private void removeMon() {
        if (normalPartyCheckBox.isSelected())
        {
            if (trainer.getStarterDependent()) {
                for (int i = 0; i < Trainer.NUM_STARTER_SETS * Trainer.NUM_STARTERS; i++) {
                    trainer.getParties().get(i).getNormalParty().remove(comboBox1.getSelectedIndex());
                }
            } else {
                trainer.getParties().get(0).getNormalParty().remove(comboBox1.getSelectedIndex());
            }
        }
        if (hardPartyCheckBox.isSelected())
        {
            if (trainer.getStarterDependent()) {
                for (int i = 0; i < Trainer.NUM_STARTER_SETS * Trainer.NUM_STARTERS; i++) {
                    trainer.getParties().get(i).getHardParty().remove(comboBox2.getSelectedIndex());
                }
            } else {
                trainer.getParties().get(0).getHardParty().remove(comboBox2.getSelectedIndex());
            }
        }
        if (unfairPartyCheckBox.isSelected())
        {
            if (trainer.getStarterDependent()) {
                for (int i = 0; i < Trainer.NUM_STARTER_SETS * Trainer.NUM_STARTERS; i++) {
                    trainer.getParties().get(i).getUnfairParty().remove(comboBox3.getSelectedIndex());
                }
            } else {
                trainer.getParties().get(0).getUnfairParty().remove(comboBox3.getSelectedIndex());
            }
        }
    }

    private void onCancel() {
        dispose();
    }
}
