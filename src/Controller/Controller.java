package Controller;

import Data.Ability;
import Model.MainModel;
import View.MainView;
import View.StarterDependentPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class Controller {
    private MainModel model;
    private MainView view;
    private JFrame frame;

    public Controller(MainModel m, MainView v, JFrame parentFrame)
    {
        model = m;
        view = v;
        frame = parentFrame;

        view.getStarterDependentCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getStarterDependentCheckBox().isSelected())
                {
                    view.getPartiesPane().setComponentAt(0, view.getStarterDependentPanels().get(0).getMainPanel());
                    view.getPartiesPane().setComponentAt(1, view.getStarterDependentPanels().get(1).getMainPanel());
                    view.getPartiesPane().setComponentAt(2, view.getStarterDependentPanels().get(2).getMainPanel());
                    frame.pack();
                }
                else
                {
                    view.getPartiesPane().setComponentAt(0, view.getNormPartyTab());
                    view.getPartiesPane().setComponentAt(1, view.getHardPartyTab());
                    view.getPartiesPane().setComponentAt(2, view.getUnfairPartyTab());
                    frame.pack();
                }
            }
        });

        SetComboBoxModel(view.getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon2AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon3AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon2AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon3AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon2AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon3AbilBox(), getAllAbilities());

    }

    private void SetComboBoxModel(JComboBox box, ArrayList<String> contents) {
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel<>();
        boxModel.addAll(contents);
        box.setModel(boxModel);
        box.setSelectedIndex(0);
    }

    private ArrayList<String> getAllAbilities() {
        ArrayList<String> abilities = new ArrayList<>();
        for (Ability a : Ability.values())
        {
            abilities.add(a.name());
        }
        abilities.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        abilities.remove(Ability.NONE);
        abilities.add(0, Ability.NONE.name());
        return abilities;
    }
}
