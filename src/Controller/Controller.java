package Controller;

import Data.*;
import Model.MainModel;
import Model.PartySet;
import Model.Trainer;
import Model.TrainerMon;
import View.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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

        if (model.getTrainers().get(0).getStarterDependent())
        {
            view.getPartiesPane().setComponentAt(0, view.getStarterDependentPanels().get(0).getMainPanel());
            view.getPartiesPane().setComponentAt(1, view.getStarterDependentPanels().get(1).getMainPanel());
            view.getPartiesPane().setComponentAt(2, view.getStarterDependentPanels().get(2).getMainPanel());
            view.getStarterDependentCheckBox().setSelected(true);
            frame.pack();
        }

        initComboBoxes();
        initTrainerList();
        initListeners();
    }

    /**
     * @return The trainer at the index currently selected on the Trainers JList
     */
    private Trainer getCurrentTrainer() {
        return model.getTrainers().get(view.getList1().getSelectedIndex());
    }

    /**
     * @return The party index currently selected in the partyIndex ComboBox.
     */
    private int getCurrentMonIndex()
    {
        return view.getPartyIndexBox().getSelectedIndex();
    }

    /**
     * Sets the lists of all the ComboBoxes on the view to the lists of their respective constants.
     */
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

        SetComboBoxModel(view.getTrainerClassBox(), getAllTrainerClasses());
        SetComboBoxModel(view.getTrainerSpriteBox(), getAllTrainerPics());
        SetComboBoxModel(view.getEncounterMusicBox(), getAllEncounterMusics());

        SetComboBoxModel(view.getPartyIndexBox(), new ArrayList<String>(Arrays.asList("Mon 0", "Mon 1", "Mon 2", "Mon 3", "Mon 4", "Mon 5")));
    }

    /**
     * Assigns a DefaultComboBoxModel to the given ComboBox with the contents as its items.
     * @param box The ComboBox to assign to.
     * @param contents The list of Strings that will make up the box's items.
     */
    private void SetComboBoxModel(JComboBox box, ArrayList<String> contents) {
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel<>();
        boxModel.addAll(contents);
        box.setModel(boxModel);
        box.setSelectedIndex(0);
    }

    /**
     * @return A list of all abilities (Enum.Ability), sorted alphabetically.
     */
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

    /**
     * @return A list of all species (Enum.Species)
     */
    private ArrayList<String> getAllSpecies() {
        ArrayList<String> species = new ArrayList<>();
        for (Species s : Species.values())
        {
            species.add(s.name());
        }

        return species;
    }

    /**
     * @return A list of all natures (Enum.Nature), sorted by stat.
     */
    private ArrayList<String> getAllNatures() {
        ArrayList<String> natures = new ArrayList<>();
        for (Nature n : Nature.values())
            natures.add(n.name());

        return natures;
    }

    /**
     * @return A list of all items (Enum.Item)
     */
    private ArrayList<String> getAllItems() {
        ArrayList<String> items = new ArrayList<>();
        for (Item i : Item.values())
            items.add(i.name());

        items.remove(Item.LAST_BALL.name());

        return items;
    }

    /**
     * @return A list of all Poke Balls (a subset of Enum.Item)
     */
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

    /**
     * @return A list of all trainer classes (Enum.TrainerClass)
     */
    private ArrayList<String> getAllTrainerClasses() {
        ArrayList<String> trainerClasses = new ArrayList<>();
        for (TrainerClass c : TrainerClass.values())
            trainerClasses.add(c.name());

        return trainerClasses;
    }

    /**
     * @return A list of all encounter musics (Enum.EncounterMusic)
     */
    private ArrayList<String> getAllEncounterMusics() {
        ArrayList<String> encounterMusics = new ArrayList<>();
        for (EncounterMusic e : EncounterMusic.values())
            encounterMusics.add(e.name());

        return encounterMusics;
    }

    /**
     * @return A list of all trainer pics (Enum.TrainerPic). Also used as the filename for the trainer sprite.
     */
    private ArrayList<String> getAllTrainerPics() {
        ArrayList<String> trainerPics = new ArrayList<>();
        for (TrainerPic p : TrainerPic.values())
            trainerPics.add(p.name());

        return trainerPics;
    }


    /**
     * Assigns "project/graphics/pokemon/species/front.png" as the image icon to the given label.
     * @param species The selected species whose sprite will be drawn.
     * @param label The label the icon will be drawn to.
     */
    private void drawMonIcon(String species, JLabel label) {
        if (species.equals(Species.NONE.name()))
            label.setIcon(null);
        else {
            try {
                BufferedImage sprite = ImageIO.read(new File("C:/fandango/graphics/pokemon/" + species.toLowerCase() + "/front.png"));
                label.setIcon(new ImageIcon(sprite));
            } catch (IOException e) {
                try { // dumb hack to catch mons that only have anim_front. blame expansion.
                    BufferedImage sprite = ImageIO.read(new File("C:/fandango/graphics/pokemon/" + species.toLowerCase() + "/anim_front.png"));
                    label.setIcon(new ImageIcon(sprite));
                    frame.pack();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    /**
     * Assigns "project/graphics/trainers/front_pics/pic.png" as the image icon to the given label.
     * @param pic The trainer pic that will be drawn.
     * @param label The label that the pic will be drawn to.
     */
    private void drawTrainerIcon(String pic, JLabel label) {
        try {
            BufferedImage sprite = ImageIO.read(new File("C:/fandango/graphics/trainers/front_pics/" + pic.toLowerCase() + ".png"));
            label.setIcon(new ImageIcon(sprite));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes trainerList's selection listener, which draws the selected trainer to the view.
     * Also writes the initial trainers to the list's items and selects index 0.
     */
    private void initTrainerList() {
        view.getList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (view.getList1().getSelectedIndex() != -1)
                    writeTrainerToView(view.getList1().getSelectedIndex());
            }
        });
        writeTrainersToList(0);
    }

    /**
     * Resets the trainerList's model, assigning the labels of all the model's Trainers as its items.
     * @param selection Which index to select by default after reassigning the model.
     */
    private void writeTrainersToList(int selection) {
        ArrayList<String> trainers = new ArrayList<>();
        DefaultListModel list = new DefaultListModel<>();


        for (Trainer trainer : model.getTrainers())
        {
            trainers.add(trainer.getLabel());
        }
        list.addAll(trainers);
        view.getList1().setModel(list);
        if (selection == -1)
            view.getList1().setSelectedIndex(view.getList1().getModel().getSize() - 1);
        else
            view.getList1().setSelectedIndex(selection);

    }

    /**
     * Assigns a list of moves to a JList, one move per line.
     * @param moves The list of moves to display.
     * @param list The JList to display them on.
     */
    private void writeMovesToList(ArrayList<String> moves, JList list) {
        DefaultListModel movesList = new DefaultListModel<>();
        movesList.addAll(moves);
        list.setModel(movesList);
    }

    /**
     * Writes the data of a Trainer to the view.
     * @param trainerIndex The index into model.trainers that will be written to the view.
     */
    private void writeTrainerToView(int trainerIndex) {
        Trainer data = model.getTrainers().get(trainerIndex);
        ArrayList<PartySet> parties = data.getParties();

        view.getIdField().setText(data.getId());
        view.getNameField().setText(data.getName());
        view.getLabelField().setText(data.getLabel());
        view.getTrainerClassBox().setSelectedItem(data.getTrainerClass());
        view.getTrainerSpriteBox().setSelectedItem(data.getPic());
        view.getEncounterMusicBox().setSelectedItem(data.getEncounterMusic());
        view.getFemaleCheckBox().setSelected(data.getFemale());
        view.getDoubleBattleCheckBox().setSelected(data.getDoubleBattle());
        view.getDifficultyCheckBox().setSelected((data.getDifficulty()));
        view.getStarterDependentCheckBox().setSelected(data.getStarterDependent());
        if (data.getStarterDependent())
            view.getDifficultyCheckBox().setEnabled(false);
        drawTrainerIcon(data.getPic(), view.getTrainerSpriteLabel());

        writeMonToView(parties, data.getStarterDependent(), getCurrentMonIndex());


    }

    /**
     * Writes Mon data to the view. If a party does not contain a mon at the selected index, that party's panel will be deactivated instead.
     * @param parties The set of parties that each mon will be drawn from.
     * @param starterDependent If the data should be written to the StarterDependentPanels instead of the normal JPanels.
     * @param monIndex Index into parties.
     */
    private void writeMonToView(ArrayList<PartySet> parties, Boolean starterDependent, int monIndex) {
        if (starterDependent)
        {
            ArrayList<StarterDependentPanel> panels = view.getStarterDependentPanels();

            for (int i = 0; i < 3; i++)
            {
                ArrayList<TrainerMon> mons = new ArrayList<>();
                for (int j = 0; j < Trainer.NUM_STARTERS; j++) {
                    TrainerMon mon = null;
                    TrainerMon hardMon = null;
                    TrainerMon unfairMon = null;


                    if (parties.get(j).getNormalParty().size() > monIndex)
                        mon = parties.get(j).getNormalParty().get(monIndex);
                    if (parties.get(j).getHardParty().size() > monIndex)
                        hardMon = parties.get(j).getHardParty().get(monIndex);
                    if (parties.get(j).getUnfairParty().size() > monIndex)
                        unfairMon = parties.get(j).getUnfairParty().get(monIndex);
                    mons.add(mon);
                    mons.add(hardMon);
                    mons.add(unfairMon);
                }

                // Deactivate the editing panel without updating it if the selected party slot is empty.
                if (mons.get(i) == null)
                {
                    setPanelEnabled(panels.get(i).getMon1Panel(), false);
                }
                else {
                    setPanelEnabled(panels.get(i).getMon1Panel(), true);
                    panels.get(i).getSpeciesBox().setSelectedItem(mons.get(i).getSpecies());
                    panels.get(i).getLevelSpinner().setValue(mons.get(i).getLevel());
                    panels.get(i).getItemBox().setSelectedItem(mons.get(i).getItem());
                    panels.get(i).getAbilBox().setSelectedItem(mons.get(i).getAbility());
                    panels.get(i).getNatureBox().setSelectedItem(mons.get(i).getNature());
                    panels.get(i).getEvLabel().setText("Total: " + Arrays.stream(mons.get(i).getEvs()).sum());
                    panels.get(i).getIvLabel().setText(getIvLabelText(mons.get(i).getIvs()));
                    panels.get(i).getFriendshipSpinner().setValue(mons.get(i).getFriendship());
                    panels.get(i).getNickField().setText(mons.get(i).getNickname());
                    panels.get(i).getBallBox().setSelectedItem(mons.get(i).getBall());
                    panels.get(i).getDefaultRadioButton().setSelected(mons.get(i).getGender().equals("DEFAULT"));
                    panels.get(i).getMaleRadioButton().setSelected(mons.get(i).getGender().equals("MALE"));
                    panels.get(i).getFemaleRadioButton().setSelected(mons.get(i).getGender().equals("FEMALE"));
                    panels.get(i).getShinyBox().setSelected(mons.get(i).getShiny());
                    drawMonIcon(mons.get(i).getSpecies(), panels.get(i).getSpriteLabel());
                    writeMovesToList(mons.get(i).getMoves(), panels.get(i).getMovesList());
                }

                if (mons.get(3 + i) == null)
                {
                    setPanelEnabled(panels.get(i).getMon2Panel(), false);
                }
                else {
                    setPanelEnabled(panels.get(i).getMon2Panel(), true);
                    panels.get(i).getMon2SpeciesBox().setSelectedItem(mons.get(3 + i).getSpecies());
                    panels.get(i).getMon2LevelBox().setValue(mons.get(3 + i).getLevel());
                    panels.get(i).getMon2ItemBox().setSelectedItem(mons.get(3 + i).getItem());
                    panels.get(i).getMon2AbilBox().setSelectedItem(mons.get(3 + i).getAbility());
                    panels.get(i).getMon2NatureBox().setSelectedItem(mons.get(3 + i).getNature());
                    panels.get(i).getMon2EVLabel().setText("Total: " + Arrays.stream(mons.get(3 + i).getEvs()).sum());
                    panels.get(i).getMon2IVLabel().setText(getIvLabelText(mons.get(3 + i).getIvs()));
                    panels.get(i).getMon2FriendshipSpinner().setValue(mons.get(3 + i).getFriendship());
                    panels.get(i).getMon2NickField().setText(mons.get(3 + i).getNickname());
                    panels.get(i).getMon2BallBox().setSelectedItem(mons.get(3 + i).getBall());
                    panels.get(i).getMon2DefaultButton().setSelected(mons.get(3 + i).getGender().equals("DEFAULT"));
                    panels.get(i).getMon2MaleButton().setSelected(mons.get(3 + i).getGender().equals("MALE"));
                    panels.get(i).getMon2FemaleButton().setSelected(mons.get(3 + i).getGender().equals("FEMALE"));
                    panels.get(i).getMon2ShinyCheckBox().setSelected(mons.get(3 + i).getShiny());
                    drawMonIcon(mons.get(3 + i).getSpecies(), panels.get(i).getMon2SpriteLabel());
                    writeMovesToList(mons.get(3 + i).getMoves(), panels.get(i).getMon2MovesList());
                }

                if (mons.get(6 + i) == null)
                {
                    setPanelEnabled(panels.get(i).getMon3Panel(), false);
                }
                else {
                    setPanelEnabled(panels.get(i).getMon3Panel(), true);
                    panels.get(i).getMon3SpeciesBox().setSelectedItem(mons.get(6 + i).getSpecies());
                    panels.get(i).getMon3LevelSpinner().setValue(mons.get(6 + i).getLevel());
                    panels.get(i).getMon3ItemBox().setSelectedItem(mons.get(6 + i).getItem());
                    panels.get(i).getMon3AbilBox().setSelectedItem(mons.get(6 + i).getAbility());
                    panels.get(i).getMon3NatureBox().setSelectedItem(mons.get(6 + i).getNature());
                    panels.get(i).getMon3EVLabel().setText("Total: " + Arrays.stream(mons.get(6 + i).getEvs()).sum());
                    panels.get(i).getMon3IVLabel().setText(getIvLabelText(mons.get(6 + i).getIvs()));
                    panels.get(i).getMon3FriendshipSpinner().setValue(mons.get(6 + i).getFriendship());
                    panels.get(i).getMon3NickField().setText(mons.get(6 + i).getNickname());
                    panels.get(i).getMon3BallBox().setSelectedItem(mons.get(6 + i).getBall());
                    panels.get(i).getMon3DefaultButton().setSelected(mons.get(6 + i).getGender().equals("DEFAULT"));
                    panels.get(i).getMon3MaleButton().setSelected(mons.get(6 + i).getGender().equals("MALE"));
                    panels.get(i).getMon3FemaleButton().setSelected(mons.get(6 + i).getGender().equals("FEMALE"));
                    panels.get(i).getMon3ShinyCheckBox().setSelected(mons.get(6 + i).getShiny());
                    drawMonIcon(mons.get(6 + i).getSpecies(), panels.get(i).getMon3SpriteLabel());
                    writeMovesToList(mons.get(6 + i).getMoves(), panels.get(i).getMon3MovesList());
                }
            }
        }
        else {
            TrainerMon mon = null;
            TrainerMon hardMon = null;
            TrainerMon unfairMon = null;

            if (parties.get(0).getNormalParty().size() > monIndex)
                mon = parties.get(0).getNormalParty().get(monIndex);
            if (parties.get(0).getHardParty().size() > monIndex)
                hardMon = parties.get(0).getHardParty().get(monIndex);
            if (parties.get(0).getUnfairParty().size() > monIndex)
                unfairMon = parties.get(0).getUnfairParty().get(monIndex);

            if (mon == null) {
                setPanelEnabled(view.getNormalPartyPanel(), false);
            } else {
                setPanelEnabled(view.getNormalPartyPanel(), true);
                view.getSpeciesBox().setSelectedItem(mon.getSpecies());
                view.getLevelSpinner().setValue(mon.getLevel());
                view.getItemBox().setSelectedItem(mon.getItem());
                view.getAbilBox().setSelectedItem(mon.getAbility());
                view.getNatureBox().setSelectedItem(mon.getNature());
                view.getEvLabel().setText("Total: " + Arrays.stream(mon.getEvs()).sum());
                view.getIvLabel().setText(getIvLabelText(mon.getIvs()));
                view.getFriendshipSpinner().setValue(mon.getFriendship());
                view.getNickField().setText(mon.getNickname());
                view.getBallBox().setSelectedItem(mon.getBall());
                view.getDefaultRadioButton().setSelected(mon.getGender().equals("DEFAULT"));
                view.getMaleRadioButton().setSelected(mon.getGender().equals("MALE"));
                view.getFemaleRadioButton().setSelected(mon.getGender().equals("FEMALE"));
                view.getShinyBox().setSelected(mon.getShiny());
                drawMonIcon(mon.getSpecies(), view.getSpriteLabel());
                writeMovesToList(mon.getMoves(), view.getMovesList());
            }

            if (hardMon == null) {
                setPanelEnabled(view.getHardPartyPanel(), false);
            } else {
                setPanelEnabled(view.getHardPartyPanel(), true);
                view.getHardSpeciesBox().setSelectedItem(hardMon.getSpecies());
                view.getHardLevelSpinner().setValue(hardMon.getLevel());
                view.getHardItemBox().setSelectedItem(hardMon.getItem());
                view.getHardAbilBox().setSelectedItem(hardMon.getAbility());
                view.getHardNatureBox().setSelectedItem(hardMon.getNature());
                view.getHardEVLabel().setText("Total: " + Arrays.stream(hardMon.getEvs()).sum());
                view.getHardIVLabel().setText(getIvLabelText(hardMon.getIvs()));
                view.getHardFriendshipSpinner().setValue(hardMon.getFriendship());
                view.getHardNickField().setText(hardMon.getNickname());
                view.getHardBallBox().setSelectedItem(hardMon.getBall());
                view.getHardDefaultButton().setSelected(hardMon.getGender().equals("DEFAULT"));
                view.getHardMaleButton().setSelected(hardMon.getGender().equals("MALE"));
                view.getHardFemaleButton().setSelected(hardMon.getGender().equals("FEMALE"));
                view.getHardShinyCheckBox().setSelected(hardMon.getShiny());
                drawMonIcon(hardMon.getSpecies(), view.getHardSpriteLabel());
                writeMovesToList(hardMon.getMoves(), view.getHardMovesList());
            }

            if (unfairMon == null) {
                setPanelEnabled(view.getUnfairPartyPanel(), false);
            } else
            {
                setPanelEnabled(view.getUnfairPartyPanel(), true);
                view.getUnfairSpeciesBox().setSelectedItem(unfairMon.getSpecies());
                view.getUnfairLevelSpinner().setValue(unfairMon.getLevel());
                view.getUnfairItemBox().setSelectedItem(unfairMon.getItem());
                view.getUnfairAbilBox().setSelectedItem(unfairMon.getAbility());
                view.getUnfairNatureBox().setSelectedItem(unfairMon.getNature());
                view.getUnfairEVLabel().setText("Total: " + Arrays.stream(unfairMon.getEvs()).sum());
                view.getUnfairIVLabel().setText(getIvLabelText(unfairMon.getIvs()));
                view.getUnfairFriendshipSpinner().setValue(unfairMon.getFriendship());
                view.getUnfairNickField().setText(unfairMon.getNickname());
                view.getUnfairBallBox().setSelectedItem(unfairMon.getBall());
                view.getUnfairDefaultButton().setSelected(unfairMon.getGender().equals("DEFAULT"));
                view.getUnfairMaleButton().setSelected(unfairMon.getGender().equals("MALE"));
                view.getUnfairFemaleButton().setSelected(unfairMon.getGender().equals("FEMALE"));
                view.getUnfairShinyBox().setSelected(unfairMon.getShiny());
                drawMonIcon(unfairMon.getSpecies(), view.getUnfairSpriteLabel());
                writeMovesToList(unfairMon.getMoves(), view.getUnfairMoveList());
            }
        }
    }

    /**
     * Enables or disables all the child components of the given JPanel.
     * @param panel Panel to operate on.
     * @param isEnabled Enable or disable components.
     */
    void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                setPanelEnabled((JPanel) component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
    }

    /**
     * Uses the given array to get the text that should be printed on an IV label.
     * @param ivs IVs to use for the label.
     * @return "Total: (sum of ivs), Avg: (average of ivs)
     */
    String getIvLabelText(int[] ivs) {
        return "Total: " + Arrays.stream(ivs).sum() +
                ", Avg: " + new BigDecimal(Arrays.stream(ivs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Assigns listeners to the editable components of the view. That is, the ones that can be used to edit mon and trainer data.
     * These listeners are responsible for syncing the data between Model and View.
     */
    private void initListeners() {
        initGeneralListeners();
        initTrainerListeners();

        // Redraws the mon icon and saves to model
        view.getSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMonIcon((String) view.getSpeciesBox().getSelectedItem(), view.getSpriteLabel());
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setSpecies((String) view.getSpeciesBox().getSelectedItem());
            }
        });
        view.getHardSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMonIcon((String) view.getHardSpeciesBox().getSelectedItem(), view.getHardSpriteLabel());
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setSpecies((String) view.getHardSpeciesBox().getSelectedItem());
            }
        });
        view.getUnfairSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMonIcon((String) view.getUnfairSpeciesBox().getSelectedItem(), view.getUnfairSpriteLabel());
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setSpecies((String) view.getUnfairSpeciesBox().getSelectedItem());
            }
        });

        // Don't ask me why this is necessary to add focus listeners to the spinners.
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) view.getLevelSpinner().getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    view.getLevelSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setLevel((int) view.getLevelSpinner().getValue());
            }
        });

        view.getLevelSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                view.getLevelSpinner().setValue((int) view.getLevelSpinner().getValue() - e.getWheelRotation());
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setLevel((int) view.getLevelSpinner().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) view.getHardLevelSpinner().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    view.getHardLevelSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setLevel((int) view.getHardLevelSpinner().getValue());
            }
        });

        view.getHardLevelSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                view.getHardLevelSpinner().setValue((int) view.getHardLevelSpinner().getValue() - e.getWheelRotation());
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setLevel((int) view.getHardLevelSpinner().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) view.getUnfairLevelSpinner().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    view.getUnfairLevelSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setLevel((int) view.getUnfairLevelSpinner().getValue());
            }
        });

        view.getUnfairLevelSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                view.getUnfairLevelSpinner().setValue((int) view.getUnfairLevelSpinner().getValue() - e.getWheelRotation());
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setLevel((int) view.getUnfairLevelSpinner().getValue());
            }
        });

        view.getNickField().addFocusListener(new FocusListener() {
                                                 @Override
                                                 public void focusGained(FocusEvent e) {

                                                 }

                                                 @Override
                                                 public void focusLost(FocusEvent e) {
                                                     getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setNickname(view.getNickField().getText());
                                                 }
                                             }
        );

        view.getHardNickField().addFocusListener(new FocusListener() {
                                                     @Override
                                                     public void focusGained(FocusEvent e) {

                                                     }

                                                     @Override
                                                     public void focusLost(FocusEvent e) {
                                                         getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setNickname(view.getHardNickField().getText());
                                                     }
                                                 }
        );

        view.getUnfairNickField().addFocusListener(new FocusListener() {
                                                       @Override
                                                       public void focusGained(FocusEvent e) {

                                                       }

                                                       @Override
                                                       public void focusLost(FocusEvent e) {
                                                           getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setNickname(view.getUnfairNickField().getText());
                                                       }
                                                   }
        );

        // Needs listeners:

        view.getEditMovesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovesDialog dialog = new MovesDialog(getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).getMoves());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setMoves(dialog.showAndWait());
                writeMovesToList(getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).getMoves(), view.getMovesList());
            }
        });

        view.getHardEditMovesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovesDialog dialog = new MovesDialog(getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).getMoves());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setMoves(dialog.showAndWait());
                writeMovesToList(getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).getMoves(), view.getHardMovesList());
            }
        });
        view.getUnfairMoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovesDialog dialog = new MovesDialog(getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).getMoves());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setMoves(dialog.showAndWait());
                writeMovesToList(getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).getMoves(), view.getUnfairMoveList());
            }
        });

        view.getItemBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setItem((String) view.getItemBox().getSelectedItem());
            }
        });
        view.getHardItemBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setItem((String) view.getHardItemBox().getSelectedItem());
            }
        });
        view.getUnfairItemBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setItem((String) view.getUnfairItemBox().getSelectedItem());
            }
        });

        view.getAbilBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setAbility((String) view.getAbilBox().getSelectedItem());
            }
        });
        view.getHardAbilBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setAbility((String) view.getHardAbilBox().getSelectedItem());
            }
        });
        view.getUnfairAbilBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setAbility((String) view.getUnfairAbilBox().getSelectedItem());
            }
        });

        view.getNatureBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setNature((String) view.getNatureBox().getSelectedItem());
            }
        });
        view.getHardNatureBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setNature((String) view.getHardNatureBox().getSelectedItem());
            }
        });
        view.getUnfairNatureBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setNature((String) view.getUnfairNatureBox().getSelectedItem());
            }
        });

        view.getBallBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setBall((String) view.getBallBox().getSelectedItem());
            }
        });
        view.getHardBallBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setBall((String) view.getHardBallBox().getSelectedItem());
            }
        });
        view.getUnfairBallBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setBall((String) view.getUnfairBallBox().getSelectedItem());
            }
        });

        view.getEvButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(true, getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).getEvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setEvs(dialog.showAndWait());
                view.getEvLabel().setText("Total: " + Arrays.stream(getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).getEvs()).sum());
            }
        });
        view.getHardEVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(true, getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).getEvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setEvs(dialog.showAndWait());
                view.getHardEVLabel().setText("Total: " + Arrays.stream(getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).getEvs()).sum());
            }
        });
        view.getUnfairEVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(true, getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).getEvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setEvs(dialog.showAndWait());
                view.getUnfairEVLabel().setText("Total: " + Arrays.stream(getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).getEvs()).sum());
            }
        });

        view.getIvButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(false, getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).getIvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setIvs(dialog.showAndWait());
                view.getIvLabel().setText(getIvLabelText(getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).getIvs()));
            }
        });
        view.getHardIVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(false, getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).getIvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setIvs(dialog.showAndWait());
                view.getHardIVLabel().setText(getIvLabelText(getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).getIvs()));
            }
        });
        view.getUnfairIVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(false, getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).getIvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setIvs(dialog.showAndWait());
                view.getUnfairIVLabel().setText(getIvLabelText(getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).getIvs()));
            }
        });

        editor = (JSpinner.DefaultEditor) view.getFriendshipSpinner().getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    view.getFriendshipSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setFriendship((int) view.getFriendshipSpinner().getValue());
            }
        });

        view.getFriendshipSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                view.getFriendshipSpinner().setValue((int) view.getFriendshipSpinner().getValue() - e.getWheelRotation());
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setFriendship((int) view.getFriendshipSpinner().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) view.getHardFriendshipSpinner().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    view.getHardFriendshipSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setFriendship((int) view.getHardFriendshipSpinner().getValue());
            }
        });

        view.getHardFriendshipSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                view.getHardFriendshipSpinner().setValue((int) view.getHardFriendshipSpinner().getValue() - e.getWheelRotation());
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setFriendship((int) view.getHardFriendshipSpinner().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) view.getUnfairFriendshipSpinner().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    view.getUnfairFriendshipSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setFriendship((int) view.getUnfairFriendshipSpinner().getValue());
            }
        });

        view.getUnfairFriendshipSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                view.getUnfairFriendshipSpinner().setValue((int) view.getUnfairFriendshipSpinner().getValue() - e.getWheelRotation());
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setFriendship((int) view.getUnfairFriendshipSpinner().getValue());
            }
        });

        view.getShinyBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setShiny(view.getShinyBox().isSelected());
            }
        });
        view.getHardShinyCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setShiny(view.getHardShinyCheckBox().isSelected());
            }
        });
        view.getUnfairShinyBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setShiny(view.getUnfairShinyBox().isSelected());
            }
        });

        ActionListener normRadioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGender;
                if (view.getMaleRadioButton().isSelected())
                    newGender = "MALE";
                else if (view.getFemaleRadioButton().isSelected())
                    newGender = "FEMALE";
                else
                    newGender = "DEFAULT";

                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setGender(newGender);
            }

        };
        view.getMaleRadioButton().addActionListener(normRadioButtonListener);
        view.getFemaleRadioButton().addActionListener(normRadioButtonListener);
        view.getDefaultRadioButton().addActionListener(normRadioButtonListener);
        ActionListener hardRadioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGender;
                if (view.getHardMaleButton().isSelected())
                    newGender = "MALE";
                else if (view.getHardFemaleButton().isSelected())
                    newGender = "FEMALE";
                else
                    newGender = "DEFAULT";

                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setGender(newGender);
            }

        };
        view.getHardMaleButton().addActionListener(hardRadioButtonListener);
        view.getHardFemaleButton().addActionListener(hardRadioButtonListener);
        view.getHardDefaultButton().addActionListener(hardRadioButtonListener);
        ActionListener unfairRadioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGender;
                if (view.getUnfairMaleButton().isSelected())
                    newGender = "MALE";
                else if (view.getUnfairFemaleButton().isSelected())
                    newGender = "FEMALE";
                else
                    newGender = "DEFAULT";

                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setGender(newGender);
            }

        };
        view.getUnfairMaleButton().addActionListener(normRadioButtonListener);
        view.getUnfairFemaleButton().addActionListener(normRadioButtonListener);
        view.getUnfairDefaultButton().addActionListener(normRadioButtonListener);

        setupStarterDependentListeners(view.getStarterDependentPanels().get(0), getCurrentTrainer().getParties().get(0).getNormalParty(),
                getCurrentTrainer().getParties().get(1).getNormalParty(), getCurrentTrainer().getParties().get(2).getNormalParty());
        setupStarterDependentListeners(view.getStarterDependentPanels().get(1), getCurrentTrainer().getParties().get(0).getHardParty(),
                getCurrentTrainer().getParties().get(1).getHardParty(), getCurrentTrainer().getParties().get(2).getHardParty());
        setupStarterDependentListeners(view.getStarterDependentPanels().get(2), getCurrentTrainer().getParties().get(0).getUnfairParty(),
                getCurrentTrainer().getParties().get(1).getUnfairParty(), getCurrentTrainer().getParties().get(2).getUnfairParty());
    }

    /**
     * Assigns listeners to the editable components of a StarterDependentPanel for changing the values in model.
     * @param panel The panel to assign listeners to.
     * @param mon1Party The model data for mon 1.
     * @param mon2Party The model data for mon 2.
     * @param mon3Party The model data for mon 3.
     */
    private void setupStarterDependentListeners(StarterDependentPanel panel, ArrayList<TrainerMon> mon1Party,
                                                ArrayList<TrainerMon> mon2Party, ArrayList<TrainerMon> mon3Party)
    {
        panel.getSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) panel.getSpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        panel.getSpriteLabel());
                mon1Party.get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        panel.getMon2SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) panel.getMon2SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        panel.getMon2SpriteLabel());
                mon2Party.get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        panel.getMon3SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) panel.getMon3SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        panel.getMon3SpriteLabel());
                mon3Party.get(getCurrentMonIndex()).setSpecies(species);
            }
        });

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) panel.getLevelSpinner().getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    panel.getLevelSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                mon1Party.get(getCurrentMonIndex()).setLevel((int) panel.getLevelSpinner().getValue());
            }
        });

        panel.getLevelSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panel.getLevelSpinner().setValue((int) panel.getLevelSpinner().getValue() - e.getWheelRotation());
                mon1Party.get(getCurrentMonIndex()).setLevel((int) panel.getLevelSpinner().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) panel.getMon2LevelBox().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    panel.getMon2LevelBox().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                mon2Party.get(getCurrentMonIndex()).setLevel((int) panel.getMon2LevelBox().getValue());
            }
        });

        panel.getMon2LevelBox().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panel.getMon2LevelBox().setValue((int) panel.getMon2LevelBox().getValue() - e.getWheelRotation());
                mon2Party.get(getCurrentMonIndex()).setLevel((int) panel.getMon2LevelBox().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) panel.getMon3LevelSpinner().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    panel.getMon3LevelSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                mon3Party.get(getCurrentMonIndex()).setLevel((int) panel.getMon3LevelSpinner().getValue());
            }
        });

        panel.getMon3LevelSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panel.getMon3LevelSpinner().setValue((int) panel.getMon3LevelSpinner().getValue() - e.getWheelRotation());
                mon3Party.get(getCurrentMonIndex()).setLevel((int) panel.getMon3LevelSpinner().getValue());
            }
        });

        panel.getNickField().addFocusListener(new FocusListener() {
                                                  @Override
                                                  public void focusGained(FocusEvent e) {

                                                  }

                                                  @Override
                                                  public void focusLost(FocusEvent e) {
                                                      mon1Party.get(getCurrentMonIndex()).setNickname(panel.getNickField().getText());
                                                  }
                                              }
        );

        panel.getMon2NickField().addFocusListener(new FocusListener() {
                                                      @Override
                                                      public void focusGained(FocusEvent e) {

                                                      }

                                                      @Override
                                                      public void focusLost(FocusEvent e) {
                                                          mon2Party.get(getCurrentMonIndex()).setNickname(panel.getMon2NickField().getText());
                                                      }
                                                  }
        );

        panel.getMon3NickField().addFocusListener(new FocusListener() {
                                                      @Override
                                                      public void focusGained(FocusEvent e) {

                                                      }

                                                      @Override
                                                      public void focusLost(FocusEvent e) {
                                                          mon3Party.get(getCurrentMonIndex()).setNickname(panel.getMon3NickField().getText());
                                                      }
                                                  }
        );

        panel.getEditMovesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovesDialog dialog = new MovesDialog(mon1Party.get(getCurrentMonIndex()).getMoves());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon1Party.get(getCurrentMonIndex()).setMoves(dialog.showAndWait());
                writeMovesToList(mon1Party.get(getCurrentMonIndex()).getMoves(), panel.getMovesList());
            }
        });

        panel.getMon2EditMovesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovesDialog dialog = new MovesDialog(mon2Party.get(getCurrentMonIndex()).getMoves());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon2Party.get(getCurrentMonIndex()).setMoves(dialog.showAndWait());
                writeMovesToList(mon2Party.get(getCurrentMonIndex()).getMoves(), panel.getMon2MovesList());
            }
        });
        panel.getMon3EditMovesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovesDialog dialog = new MovesDialog(mon3Party.get(getCurrentMonIndex()).getMoves());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon3Party.get(getCurrentMonIndex()).setMoves(dialog.showAndWait());
                writeMovesToList(mon3Party.get(getCurrentMonIndex()).getMoves(), panel.getMon3MovesList());
            }
        });

        panel.getItemBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon1Party.get(getCurrentMonIndex()).setItem((String) panel.getItemBox().getSelectedItem());
            }
        });
        panel.getMon2ItemBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon2Party.get(getCurrentMonIndex()).setItem((String) panel.getMon2ItemBox().getSelectedItem());
            }
        });
        panel.getMon3ItemBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon3Party.get(getCurrentMonIndex()).setItem((String) panel.getMon3ItemBox().getSelectedItem());
            }
        });

        panel.getAbilBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon1Party.get(getCurrentMonIndex()).setAbility((String) panel.getAbilBox().getSelectedItem());
            }
        });
        panel.getMon2AbilBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon2Party.get(getCurrentMonIndex()).setAbility((String) panel.getMon2AbilBox().getSelectedItem());
            }
        });
        panel.getMon3AbilBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon3Party.get(getCurrentMonIndex()).setAbility((String) panel.getMon3AbilBox().getSelectedItem());
            }
        });

        panel.getNatureBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon1Party.get(getCurrentMonIndex()).setNature((String) panel.getNatureBox().getSelectedItem());
            }
        });
        panel.getMon2NatureBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon2Party.get(getCurrentMonIndex()).setNature((String) panel.getMon2NatureBox().getSelectedItem());
            }
        });
        panel.getMon3NatureBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon3Party.get(getCurrentMonIndex()).setNature((String) panel.getMon3NatureBox().getSelectedItem());
            }
        });

        panel.getBallBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon1Party.get(getCurrentMonIndex()).setBall((String) panel.getBallBox().getSelectedItem());
            }
        });
        panel.getMon2BallBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon2Party.get(getCurrentMonIndex()).setBall((String) panel.getMon2BallBox().getSelectedItem());
            }
        });
        panel.getMon3BallBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon3Party.get(getCurrentMonIndex()).setBall((String) panel.getMon3BallBox().getSelectedItem());
            }
        });

        panel.getEvButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(true, mon1Party.get(getCurrentMonIndex()).getEvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon1Party.get(getCurrentMonIndex()).setEvs(dialog.showAndWait());
                panel.getEvLabel().setText("Total: " + Arrays.stream(mon1Party.get(getCurrentMonIndex()).getEvs()).sum());
            }
        });
        panel.getMon2EVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(true, mon2Party.get(getCurrentMonIndex()).getEvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon2Party.get(getCurrentMonIndex()).setEvs(dialog.showAndWait());
                panel.getMon2EVLabel().setText("Total: " + Arrays.stream(mon2Party.get(getCurrentMonIndex()).getEvs()).sum());
            }
        });
        panel.getMon3EVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(true, mon3Party.get(getCurrentMonIndex()).getEvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon3Party.get(getCurrentMonIndex()).setEvs(dialog.showAndWait());
                panel.getMon3EVLabel().setText("Total: " + Arrays.stream(mon3Party.get(getCurrentMonIndex()).getEvs()).sum());
            }
        });

        panel.getIvButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(false, mon1Party.get(getCurrentMonIndex()).getIvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon1Party.get(getCurrentMonIndex()).setIvs(dialog.showAndWait());
                panel.getIvLabel().setText(getIvLabelText(mon1Party.get(getCurrentMonIndex()).getIvs()));
            }
        });
        panel.getMon2IVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(false, mon2Party.get(getCurrentMonIndex()).getIvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon2Party.get(getCurrentMonIndex()).setIvs(dialog.showAndWait());
                panel.getMon2IVLabel().setText(getIvLabelText(mon2Party.get(getCurrentMonIndex()).getIvs()));
            }
        });
        panel.getMon3IVButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EVsDialog dialog = new EVsDialog(false, mon3Party.get(getCurrentMonIndex()).getIvs());
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                mon3Party.get(getCurrentMonIndex()).setIvs(dialog.showAndWait());
                panel.getMon3IVLabel().setText(getIvLabelText(mon3Party.get(getCurrentMonIndex()).getIvs()));
            }
        });

        editor = (JSpinner.DefaultEditor) panel.getFriendshipSpinner().getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    panel.getFriendshipSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                mon1Party.get(getCurrentMonIndex()).setFriendship((int) panel.getFriendshipSpinner().getValue());
            }
        });

        panel.getFriendshipSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panel.getFriendshipSpinner().setValue((int) panel.getFriendshipSpinner().getValue() - e.getWheelRotation());
                mon1Party.get(getCurrentMonIndex()).setFriendship((int) panel.getFriendshipSpinner().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) panel.getMon2FriendshipSpinner().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    panel.getMon2FriendshipSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                mon2Party.get(getCurrentMonIndex()).setFriendship((int) panel.getMon2FriendshipSpinner().getValue());
            }
        });

        panel.getMon2FriendshipSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panel.getMon2FriendshipSpinner().setValue((int) panel.getMon2FriendshipSpinner().getValue() - e.getWheelRotation());
                mon2Party.get(getCurrentMonIndex()).setFriendship((int) panel.getMon2FriendshipSpinner().getValue());
            }
        });

        editor = (JSpinner.DefaultEditor) panel.getMon3FriendshipSpinner().getEditor();
        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    panel.getMon3FriendshipSpinner().commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                mon3Party.get(getCurrentMonIndex()).setFriendship((int) panel.getMon3FriendshipSpinner().getValue());
            }
        });

        panel.getMon3FriendshipSpinner().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panel.getMon3FriendshipSpinner().setValue((int) panel.getMon3FriendshipSpinner().getValue() - e.getWheelRotation());
                mon3Party.get(getCurrentMonIndex()).setFriendship((int) panel.getMon3FriendshipSpinner().getValue());
            }
        });

        panel.getShinyBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon1Party.get(getCurrentMonIndex()).setShiny(panel.getShinyBox().isSelected());
            }
        });
        panel.getMon2ShinyCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon2Party.get(getCurrentMonIndex()).setShiny(panel.getMon2ShinyCheckBox().isSelected());
            }
        });
        panel.getMon3ShinyCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mon3Party.get(getCurrentMonIndex()).setShiny(panel.getMon3ShinyCheckBox().isSelected());
            }
        });

        ActionListener normMon1RadioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGender;
                if (panel.getMaleRadioButton().isSelected())
                    newGender = "MALE";
                else if (panel.getFemaleRadioButton().isSelected())
                    newGender = "FEMALE";
                else
                    newGender = "DEFAULT";

                mon1Party.get(getCurrentMonIndex()).setGender(newGender);
            }

        };
        panel.getMaleRadioButton().addActionListener(normMon1RadioListener);
        panel.getFemaleRadioButton().addActionListener(normMon1RadioListener);
        panel.getDefaultRadioButton().addActionListener(normMon1RadioListener);
        ActionListener normMon2RadioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGender;
                if (panel.getMon2MaleButton().isSelected())
                    newGender = "MALE";
                else if (panel.getMon2FemaleButton().isSelected())
                    newGender = "FEMALE";
                else
                    newGender = "DEFAULT";

                mon2Party.get(getCurrentMonIndex()).setGender(newGender);
            }

        };
        panel.getMon2MaleButton().addActionListener(normMon2RadioListener);
        panel.getMon2FemaleButton().addActionListener(normMon2RadioListener);
        panel.getMon2DefaultButton().addActionListener(normMon2RadioListener);
        ActionListener normMon3RadioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGender;
                if (panel.getMon3MaleButton().isSelected())
                    newGender = "MALE";
                else if (panel.getMon3FemaleButton().isSelected())
                    newGender = "FEMALE";
                else
                    newGender = "DEFAULT";

                mon3Party.get(getCurrentMonIndex()).setGender(newGender);
            }

        };
        panel.getMon3MaleButton().addActionListener(normMon1RadioListener);
        panel.getMon3FemaleButton().addActionListener(normMon1RadioListener);
        panel.getMon3DefaultButton().addActionListener(normMon1RadioListener);
    }

    /**
     * Assigns listeners to the fields in the Trainer tab to sync them with the model.
     */
    private void initTrainerListeners() {
        // id
        // name
        // label
        // items button
        // flags button
        // class box
        // pic box
        // music box
        // female
        // double battle

        // Clicking the checkbox swaps between the single or triple mon-editing panels and packs the frame
        view.getStarterDependentCheckBox().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                getCurrentTrainer().setStarterDependent(view.getStarterDependentCheckBox().isSelected());
                if (view.getStarterDependentCheckBox().isSelected())
                {
                    view.getPartiesPane().setComponentAt(0, view.getStarterDependentPanels().get(0).getMainPanel());
                    view.getPartiesPane().setComponentAt(1, view.getStarterDependentPanels().get(1).getMainPanel());
                    view.getPartiesPane().setComponentAt(2, view.getStarterDependentPanels().get(2).getMainPanel());
                    getCurrentTrainer().prepForStarterSets();
                    view.getPartyIndexBox().setSelectedIndex(0); // This redraws the mons
                    view.getDifficultyCheckBox().setSelected(true);
                    view.getDifficultyCheckBox().setEnabled(false);
                    frame.pack();
                }
                else
                {
                    view.getPartiesPane().setComponentAt(0, view.getNormPartyTab());
                    view.getPartiesPane().setComponentAt(1, view.getHardPartyTab());
                    view.getPartiesPane().setComponentAt(2, view.getUnfairPartyTab());
                    view.getDifficultyCheckBox().setEnabled(true);
                    view.getPartyIndexBox().setSelectedIndex(0);
                    frame.pack();
                }
            }
        });

        view.getDifficultyCheckBox().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                getCurrentTrainer().setDifficulty(view.getDifficultyCheckBox().isSelected());
                if (view.getDifficultyCheckBox().isSelected())
                {
                    view.getPartiesPane().setEnabledAt(1, true);
                    view.getPartiesPane().setEnabledAt(2, true);
                }
                else {
                    view.getPartiesPane().setSelectedIndex(0);
                    view.getPartiesPane().setEnabledAt(1, false);
                    view.getPartiesPane().setEnabledAt(2, false);

                }
            }
        });
    }

    /**
     * Assigns listeners to the non-data components, such as the Save button.
     */
    private void initGeneralListeners() {
        // Actually the sort button.
        view.getEditSelectedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SortDialog dialog = new SortDialog();
                dialog.setLocationRelativeTo(frame);
                dialog.setIconImage(frame.getIconImage());
                dialog.pack();
                String result = dialog.showAndWait();
                Boolean sorted = true;
                switch(result) {
                    case "Alphabetical":
                        model.getTrainers().sort(new Comparator<Trainer>() {
                            @Override
                            public int compare(Trainer o1, Trainer o2) {
                                return o1.getName().compareToIgnoreCase(o2.getName());
                            }
                        });
                        break;
                    case "Lead Mon Level":
                        model.getTrainers().sort(new Comparator<Trainer>() {
                            @Override
                            public int compare(Trainer o1, Trainer o2) {
                                return o1.getParties().get(0).getNormalParty().get(0).getLevel() - o2.getParties().get(0).getNormalParty().get(0).getLevel();
                            }
                        });
                        break;
                    case "Trainer Class":
                        model.getTrainers().sort(new Comparator<Trainer>() {
                            @Override
                            public int compare(Trainer o1, Trainer o2) {
                                return o1.getTrainerClass().compareToIgnoreCase(o2.getTrainerClass());
                            }
                        });
                        break;
                    case "Trainer Pic":
                        model.getTrainers().sort(new Comparator<Trainer>() {
                            @Override
                            public int compare(Trainer o1, Trainer o2) {
                                return o1.getPic().compareToIgnoreCase(o2.getPic());
                            }
                        });
                    case "Double Battles on bottom":
                        ArrayList<Trainer> newTrainers = new ArrayList<>();
                        for (Trainer t : model.getTrainers())
                        {
                            if (!t.getDoubleBattle())
                                newTrainers.add(t);
                        }
                        for (Trainer t : model.getTrainers())
                        {
                            if (t.getDoubleBattle())
                                newTrainers.add(t);
                        }

                        model.setTrainers(newTrainers);
                        break;
                    case "Starter Dependent on bottom":
                        ArrayList<Trainer> newTrainers2 = new ArrayList<>();
                        for (Trainer t : model.getTrainers())
                        {
                            if (!t.getDifficulty() && !t.getStarterDependent())
                                newTrainers2.add(t);
                        }
                        for (Trainer t : model.getTrainers())
                        {
                            if (t.getDifficulty() && !t.getStarterDependent())
                                newTrainers2.add(t);
                        }
                        for (Trainer t : model.getTrainers())
                        {
                            if (t.getStarterDependent())
                                newTrainers2.add(t);
                        }

                        model.setTrainers(newTrainers2);
                        break;
                    default:
                        sorted = false;
                        break;
                }

                if (sorted)
                {
                    writeTrainersToList(0);
                }
            }
        });

        // Redraws the relevant panels with the info of the mons in the selected index.
        view.getPartyIndexBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeMonToView(getCurrentTrainer().getParties(), getCurrentTrainer().getStarterDependent(),
                        getCurrentMonIndex());
            }
        });

        ActionListener resetStatusLabel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getStatusLabel().setText("");
            }
        };

        Timer resetStatus = new Timer(5000, resetStatusLabel);
        resetStatus.setRepeats(false);

        ActionListener saveData = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getStatusLabel().setText("Saving...");
                model.saveToJson();
                model.writeModelToFile();
                view.getStatusLabel().setText("Saved to files!");
                resetStatus.restart();
            }
        };

        // Allows saving with the Save button or CTRL + S.
        view.getMainPanel().registerKeyboardAction(saveData, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        view.getSaveButton().addActionListener(saveData);

        ActionListener discardData = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.initFromJson();
                view.getStatusLabel().setText("Reverted to last save.");
                writeTrainersToList(0);
                resetStatus.restart();
            }
        };

        view.getMainPanel().registerKeyboardAction(discardData, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        view.getDiscardButton().addActionListener(discardData);

        view.getAddNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> trainers = new ArrayList<>();
                for (Trainer trainer : model.getTrainers())
                {
                    trainers.add(trainer.getLabel());
                }
                AddNewDialog dialog = new AddNewDialog(trainers);
                dialog.setIconImage(frame.getIconImage());
                dialog.setLocationRelativeTo(frame);
                dialog.pack();
                ArrayList<String> result = dialog.showAndWait();

                if (!result.get(0).equals("NONE")) {
                    Trainer newTrainer = new Trainer(result.get(0));
                    if (!result.get(1).equals("NONE")){
                        int i;
                        for (i = 0; i < model.getTrainers().size(); i++)
                        {
                            if (model.getTrainers().get(i).getLabel().equals(result.get(1)))
                                break;
                        }
                        newTrainer.copyFrom(model.getTrainers().get(i));
                        newTrainer.setLabel(result.get(0));
                    }

                    model.getTrainers().add(newTrainer);
                    writeTrainersToList(-1);
                }
            }
        });

        view.getDeleteSelectedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmDialog dialog = new ConfirmDialog("Delete Trainer", "Delete Trainer " +
                        view.getList1().getSelectedValue() + "?");
                dialog.pack();
                dialog.setIconImage(frame.getIconImage());
                Boolean delete = dialog.showAndWait();

                if (delete)
                {
                    model.getTrainers().remove(view.getList1().getSelectedIndex());
                    writeTrainersToList(0);
                }
            }
        });

    }
}
