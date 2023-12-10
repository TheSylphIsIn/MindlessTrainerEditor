package Controller;

import Data.*;
import Model.MainModel;
import View.MainView;
import View.StarterDependentPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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

        initComboBoxes();
    }

    private void initComboBoxes() {
        SetComboBoxModel(view.getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getHardAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getUnfairAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon2AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon3AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon2AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon3AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getAbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon2AbilBox(), getAllAbilities());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon3AbilBox(), getAllAbilities());

        SetComboBoxModel(view.getSpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getHardSpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getUnfairSpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getSpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon2SpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon3SpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getSpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon2SpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon3SpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getSpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon2SpeciesBox(), getAllSpecies());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon3SpeciesBox(), getAllSpecies());

        SetComboBoxModel(view.getNatureBox(), getAllNatures());
        SetComboBoxModel(view.getHardNatureBox(), getAllNatures());
        SetComboBoxModel(view.getUnfairNatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getNatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon2NatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon3NatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getNatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon2NatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon3NatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getNatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon2NatureBox(), getAllNatures());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon3NatureBox(), getAllNatures());

        SetComboBoxModel(view.getItemBox(), getAllItems());
        SetComboBoxModel(view.getHardItemBox(), getAllItems());
        SetComboBoxModel(view.getUnfairItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon2ItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon3ItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon2ItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon3ItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon2ItemBox(), getAllItems());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon3ItemBox(), getAllItems());

        SetComboBoxModel(view.getBallBox(), getBalls());
        SetComboBoxModel(view.getHardBallBox(), getBalls());
        SetComboBoxModel(view.getUnfairBallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getBallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon2BallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(0).getMon3BallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getBallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon2BallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(1).getMon3BallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getBallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon2BallBox(), getBalls());
        SetComboBoxModel(view.getStarterDependentPanels().get(2).getMon3BallBox(), getBalls());
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

        // Alphabetize abilities
        abilities.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        // After sorting, put NONE in the first slot
        abilities.remove(Ability.NONE);
        abilities.add(0, Ability.NONE.name());
        return abilities;
    }

    private ArrayList<String> getAllSpecies() {
        ArrayList<String> species = new ArrayList<>();
        for (Species s : Species.values())
        {
            species.add(s.name());
        }

        return species;
    }

    private ArrayList<String> getAllNatures() {
        ArrayList<String> natures = new ArrayList<>();
        for (Nature n : Nature.values())
            natures.add(n.name());

        return natures;
    }

    private ArrayList<String> getAllItems() {
        ArrayList<String> items = new ArrayList<>();
        for (Item i : Item.values())
            items.add(i.name());

        items.remove(Item.LAST_BALL.name());

        return items;
    }

    private ArrayList<String> getBalls() {
        ArrayList<String> balls = new ArrayList<>();
        for (Item i : Item.values())
        {
            if (i == Item.LAST_BALL)
                break;
            balls.add(i.name());
        }

        return balls;
    }

    private ArrayList<String> getAllMoves() {
        ArrayList<String> moves = new ArrayList<>();
        for (Move m : Move.values())
            moves.add(m.name());

        return moves;
    }
}
