package View;

import Model.Trainer;
import Model.TrainerMon;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CopyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox wholePartyCheckBox;
    private JComboBox srcTrainerBox;
    private JComboBox srcMonBox;
    private JComboBox destTrainerBox;
    private JComboBox destMonBox;
    private JRadioButton srcNormalButton;
    private JRadioButton srcHardButton;
    private JRadioButton srcUnfairButton;
    private JRadioButton destNormalButton;
    private JRadioButton destHardButton;
    private JRadioButton destUnfairButton;
    private JComboBox srcPartyBox;
    private JComboBox destPartyBox;
    private JCheckBox wholeSetCheckBox;
    private JButton selfNormalToHardButton;
    private JButton selfHardToUnfairButton;
    private ArrayList<Trainer> allTrainers;
    private int currentTrainer;

    public CopyDialog(ArrayList<Trainer> trainers, int selected) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Copying...");

        allTrainers = trainers;
        currentTrainer = selected;

        DefaultComboBoxModel model1 = new DefaultComboBoxModel();
        ArrayList<String> names = new ArrayList<>();
        for (Trainer t : trainers)
            names.add(t.getLabel());
        model1.addAll(names);
        srcTrainerBox.setModel(model1);
        srcTrainerBox.setSelectedIndex(0);
        DefaultComboBoxModel model5 = new DefaultComboBoxModel();
        model5.addAll(getAvailableParties(trainers.get(0)));
        srcPartyBox.setModel(model5);
        srcPartyBox.setSelectedIndex(0);
        DefaultComboBoxModel model6 = new DefaultComboBoxModel();
        model6.addAll(getAvailableParties(trainers.get(currentTrainer)));
        destPartyBox.setModel(model6);
        destPartyBox.setSelectedIndex(0);


        DefaultComboBoxModel model3 = new DefaultComboBoxModel();
        model3.addAll(names);
        destTrainerBox.setModel(model3);
        destTrainerBox.setSelectedIndex(currentTrainer);

        DefaultComboBoxModel model2 = new DefaultComboBoxModel();
        model2.addAll(getPartyMonsList(trainers.get(0).getParties().get(0).getNormalParty()));
        srcMonBox.setModel(model2);
        srcMonBox.setSelectedIndex(0);
        DefaultComboBoxModel model4 = new DefaultComboBoxModel();
        model4.addAll(getPartyMonsList(trainers.get(currentTrainer).getParties().get(0).getNormalParty()));
        destMonBox.setModel(model4);
        destMonBox.setSelectedIndex(0);

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

        wholePartyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srcMonBox.setEnabled(!wholePartyCheckBox.isSelected());
                destMonBox.setEnabled(!wholePartyCheckBox.isSelected());
            }
        });

        wholeSetCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srcMonBox.setEnabled(!wholeSetCheckBox.isSelected());
                destMonBox.setEnabled(!wholeSetCheckBox.isSelected());
                srcPartyBox.setEnabled(!wholeSetCheckBox.isSelected());
                destPartyBox.setEnabled(!wholeSetCheckBox.isSelected());

                if (wholeSetCheckBox.isSelected()) {
                    setStarterDependentModels();
                } else
                {
                    DefaultComboBoxModel model1 = new DefaultComboBoxModel();
                    ArrayList<String> names = new ArrayList<>();
                    for (Trainer t : trainers)
                        names.add(t.getLabel());
                    model1.addAll(names);
                    srcTrainerBox.setModel(model1);
                    srcTrainerBox.setSelectedIndex(0);

                    DefaultComboBoxModel model2 = new DefaultComboBoxModel();
                    model2.addAll(names);
                    destTrainerBox.setModel(model2);
                    destTrainerBox.setSelectedIndex(0);
                }
            }
        });

        srcTrainerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSrcBoxModels(srcTrainerBox.getSelectedIndex());
            }
        });

        srcPartyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSrcMonsBoxModel(srcTrainerBox.getSelectedIndex(), srcPartyBox.getSelectedIndex());
            }
        });

        destTrainerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetDestBoxModels(destTrainerBox.getSelectedIndex());
            }
        });

        destPartyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetDestMonsBoxModel(destTrainerBox.getSelectedIndex(), destPartyBox.getSelectedIndex());
            }
        });

        selfNormalToHardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srcNormalButton.setSelected(true);
                destHardButton.setSelected(true);
                if (allTrainers.get(currentTrainer).getStarterDependent()) {
                    wholeSetCheckBox.setSelected(true);
                    setStarterDependentModels();
                }
                srcTrainerBox.setSelectedItem(allTrainers.get(currentTrainer).getLabel());
                destTrainerBox.setSelectedItem(allTrainers.get(currentTrainer).getLabel());
                wholePartyCheckBox.setSelected(true);
                onOK();
            }
        });

        selfHardToUnfairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srcHardButton.setSelected(true);
                destUnfairButton.setSelected(true);
                if (allTrainers.get(currentTrainer).getStarterDependent()) {
                    wholeSetCheckBox.setSelected(true);
                    setStarterDependentModels();
                }
                srcTrainerBox.setSelectedItem(allTrainers.get(currentTrainer).getLabel());
                destTrainerBox.setSelectedItem(allTrainers.get(currentTrainer).getLabel());
                wholePartyCheckBox.setSelected(true);
                onOK();
            }
        });
    }

    private void setStarterDependentModels() {
        ArrayList<Trainer> starterDependentTrainers = new ArrayList<>();
        for (Trainer t : allTrainers) {
            if (t.getStarterDependent())
                starterDependentTrainers.add(t);
        }

        ArrayList<String> starterDependentNames = new ArrayList<>();
        for (Trainer t : starterDependentTrainers)
            starterDependentNames.add(t.getLabel());

        DefaultComboBoxModel srcModel = new DefaultComboBoxModel<>();
        srcModel.addAll(starterDependentNames);
        srcTrainerBox.setModel(srcModel);
        srcTrainerBox.setSelectedIndex(0);

        DefaultComboBoxModel destModel = new DefaultComboBoxModel<>();
        destModel.addAll(starterDependentNames);
        destTrainerBox.setModel(destModel);
        destTrainerBox.setSelectedIndex(0);
    }

    private void onOK() {
        int srcTrainer = srcTrainerBox.getSelectedIndex();
        int srcPartyIndex = srcPartyBox.getSelectedIndex(); // Trainer to copy from
        int srcMonIndex = srcMonBox.getSelectedIndex();
        ArrayList<TrainerMon> srcParty;

        if (srcUnfairButton.isSelected())
            srcParty = allTrainers.get(srcTrainer).getParties().get(srcPartyIndex).getUnfairParty();
        else if (srcHardButton.isSelected())
            srcParty = allTrainers.get(srcTrainer).getParties().get(srcPartyIndex).getHardParty();
        else
            srcParty = allTrainers.get(srcTrainer).getParties().get(srcPartyIndex).getNormalParty();

        int destTrainer = destTrainerBox.getSelectedIndex();
        int destPartyIndex = destPartyBox.getSelectedIndex(); // Trainer to copy from
        int destMonIndex = destMonBox.getSelectedIndex(); // Trainer to copy from
        ArrayList<TrainerMon> destParty;

        ArrayList<Trainer> starterDependentParties = new ArrayList<>();
        for (Trainer t : allTrainers)
        {
            if (t.getStarterDependent())
                starterDependentParties.add(t);
        }

        if (destUnfairButton.isSelected())
            destParty = allTrainers.get(destTrainer).getParties().get(destPartyIndex).getUnfairParty(); // Difficulty party to get
        else if (destHardButton.isSelected())
            destParty = allTrainers.get(destTrainer).getParties().get(destPartyIndex).getHardParty(); // Difficulty party to get
        else
            destParty = allTrainers.get(destTrainer).getParties().get(destPartyIndex).getNormalParty(); // Difficulty party to get

        if (wholeSetCheckBox.isSelected())
        {
            for (int i = 0; i < Trainer.NUM_STARTER_SETS * Trainer.NUM_STARTERS; i++)
            {
                if (destUnfairButton.isSelected()){
                    starterDependentParties.get(destTrainer).getParties().get(i).setUnfairParty(new ArrayList<>());
                    destParty = starterDependentParties.get(destTrainer).getParties().get(i).getUnfairParty();
                }
                else if (destHardButton.isSelected()) {
                    starterDependentParties.get(destTrainer).getParties().get(i).setHardParty(new ArrayList<>());
                    destParty = starterDependentParties.get(destTrainer).getParties().get(i).getHardParty();
                }
                else {
                    starterDependentParties.get(destTrainer).getParties().get(i).setNormalParty(new ArrayList<>());
                    destParty = starterDependentParties.get(destTrainer).getParties().get(i).getNormalParty();
                }

                if (srcUnfairButton.isSelected())
                {
                    srcParty = starterDependentParties.get(srcTrainer).getParties().get(i).getUnfairParty();
                } else if (srcHardButton.isSelected())
                {
                    srcParty = starterDependentParties.get(srcTrainer).getParties().get(i).getHardParty();
                } else
                {
                    srcParty = starterDependentParties.get(srcTrainer).getParties().get(i).getNormalParty();

                }

                for (TrainerMon trainerMon : srcParty) {
                    TrainerMon newMon = new TrainerMon();
                    newMon.copyFrom(trainerMon);
                    destParty.add(newMon);
                }
            }
        } else if (wholePartyCheckBox.isSelected())
        {
            for (int i = 0; i < destParty.size(); i++)
            {
                destParty.get(i).copyFrom(srcParty.get(i));
            }
        }
        else {
            destParty.get(destMonIndex).copyFrom(srcParty.get(srcMonIndex));
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private ArrayList<String> getAvailableParties(Trainer trainer)
    {
        ArrayList<String> parties = new ArrayList<>();

        for (int i = 0; i < trainer.getParties().size(); i++)
            parties.add("Party " + i);

        return parties;
    }

    private ArrayList<String> getPartyMonsList(ArrayList<TrainerMon> party)
    {
        ArrayList<String> mons = new ArrayList<>();

        for (TrainerMon mon : party)
        {
            mons.add(mon.getSpecies());
        }

        return mons;
    }

    private void resetSrcBoxModels(int index) {
        DefaultComboBoxModel model5 = new DefaultComboBoxModel();
        model5.addAll(getAvailableParties(allTrainers.get(index)));
        srcPartyBox.setModel(model5);
        srcPartyBox.setSelectedIndex(0);

        resetSrcMonsBoxModel(index, 0);

    }

    private void resetSrcMonsBoxModel(int trainerIndex, int monIndex) {
        DefaultComboBoxModel model2 = new DefaultComboBoxModel();
        if (srcNormalButton.isSelected())
            model2.addAll(getPartyMonsList(allTrainers.get(trainerIndex).getParties().get(monIndex).getNormalParty()));
        else if (srcHardButton.isSelected())
            model2.addAll(getPartyMonsList(allTrainers.get(trainerIndex).getParties().get(monIndex).getHardParty()));
        else
            model2.addAll(getPartyMonsList(allTrainers.get(trainerIndex).getParties().get(monIndex).getUnfairParty()));
        srcMonBox.setModel(model2);
        srcMonBox.setSelectedIndex(0);
    }

    private void resetDestBoxModels(int index) {
        DefaultComboBoxModel model6 = new DefaultComboBoxModel();
        model6.addAll(getAvailableParties(allTrainers.get(index)));
        destPartyBox.setModel(model6);
        destPartyBox.setSelectedIndex(0);

        resetDestMonsBoxModel(index, 0);

    }

    private void resetDestMonsBoxModel(int trainerIndex, int monIndex) {
        DefaultComboBoxModel model4 = new DefaultComboBoxModel();
        if (srcNormalButton.isSelected())
            model4.addAll(getPartyMonsList(allTrainers.get(trainerIndex).getParties().get(monIndex).getNormalParty()));
        else if (srcHardButton.isSelected())
            model4.addAll(getPartyMonsList(allTrainers.get(trainerIndex).getParties().get(monIndex).getHardParty()));
        else
            model4.addAll(getPartyMonsList(allTrainers.get(trainerIndex).getParties().get(monIndex).getUnfairParty()));
        destMonBox.setModel(model4);
        destMonBox.setSelectedIndex(0);
    }
}
