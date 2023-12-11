package Controller;

import Data.*;
import Model.MainModel;
import Model.PartySet;
import Model.Trainer;
import Model.TrainerMon;
import View.MainView;
import View.SortDialog;
import View.StarterDependentPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
        view.getList1().setSelectedIndex(0);
        initListeners();


    }

    private Trainer getCurrentTrainer() {
        return model.getTrainers().get(view.getList1().getSelectedIndex());
    }

    private int getCurrentMonIndex()
    {
        return view.getPartyIndexBox().getSelectedIndex();
    }

    private void initListeners() {

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

        // Redraws the relevant panels with the info of the mons in the selected index.
        view.getPartyIndexBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeMonToView(getCurrentTrainer().getParties(), getCurrentTrainer().getStarterDependent(),
                        getCurrentMonIndex());
            }
        });

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
                drawMonIcon((String) view.getHardSpeciesBox().getSelectedItem(), view.getSpriteLabel());
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setSpecies((String) view.getHardSpeciesBox().getSelectedItem());
            }
        });
        view.getUnfairSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMonIcon((String) view.getUnfairSpeciesBox().getSelectedItem(), view.getSpriteLabel());
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
        // item
        // ability
        // nature
        // EV button
        // IV button
        // moves button
        // friendship
        // ball
        // shiny

        // The above but for the starter panel.
        // I have to repeat this stupid bullshit 9 times for every element on that panel........
        view.getStarterDependentPanels().get(0).getSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(0).getSpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(0).getSpriteLabel());
                getCurrentTrainer().getParties().get(0).getNormalParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        view.getStarterDependentPanels().get(0).getMon2SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(0).getMon2SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(0).getMon2SpriteLabel());
                getCurrentTrainer().getParties().get(1).getNormalParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        view.getStarterDependentPanels().get(0).getMon3SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(0).getMon3SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(0).getMon3SpriteLabel());
                getCurrentTrainer().getParties().get(2).getNormalParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });

        view.getStarterDependentPanels().get(1).getSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(1).getSpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(1).getSpriteLabel());
                getCurrentTrainer().getParties().get(0).getHardParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        view.getStarterDependentPanels().get(1).getMon2SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(1).getMon2SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(1).getMon2SpriteLabel());
                getCurrentTrainer().getParties().get(1).getHardParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        view.getStarterDependentPanels().get(1).getMon3SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(1).getMon3SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(1).getMon3SpriteLabel());
                getCurrentTrainer().getParties().get(2).getHardParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });

        view.getStarterDependentPanels().get(2).getSpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(2).getSpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(2).getSpriteLabel());
                getCurrentTrainer().getParties().get(0).getUnfairParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        view.getStarterDependentPanels().get(2).getMon2SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(2).getMon2SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(2).getMon2SpriteLabel());
                getCurrentTrainer().getParties().get(1).getUnfairParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });
        view.getStarterDependentPanels().get(2).getMon3SpeciesBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = (String) view.getStarterDependentPanels().get(2).getMon3SpeciesBox().getSelectedItem();
                drawMonIcon(species,
                        view.getStarterDependentPanels().get(2).getMon3SpriteLabel());
                getCurrentTrainer().getParties().get(2).getUnfairParty().get(getCurrentMonIndex()).setSpecies(species);
            }
        });
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

        SetComboBoxModel(view.getTrainerClassBox(), getAllTrainerClasses());
        SetComboBoxModel(view.getTrainerSpriteBox(), getAllTrainerPics());
        SetComboBoxModel(view.getEncounterMusicBox(), getAllEncounterMusics());

        SetComboBoxModel(view.getPartyIndexBox(), new ArrayList<String>(Arrays.asList("Mon 0", "Mon 1", "Mon 2", "Mon 3", "Mon 4", "Mon 5")));
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

    private ArrayList<String> getAllTrainerClasses()
    {
        ArrayList<String> trainerClasses = new ArrayList<>();
        for (TrainerClass c : TrainerClass.values())
            trainerClasses.add(c.name());

        return trainerClasses;
    }

    private ArrayList<String> getAllEncounterMusics() {
        ArrayList<String> encounterMusics = new ArrayList<>();
        for (EncounterMusic e : EncounterMusic.values())
            encounterMusics.add(e.name());

        return encounterMusics;
    }

    private ArrayList<String> getAllTrainerPics() {
        ArrayList<String> trainerPics = new ArrayList<>();
        for (TrainerPic p : TrainerPic.values())
            trainerPics.add(p.name());

        return trainerPics;
    }


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

    private void drawTrainerIcon(String pic, JLabel label) {
        try {
            BufferedImage sprite = ImageIO.read(new File("C:/fandango/graphics/trainers/front_pics/" + pic.toLowerCase() + ".png"));
            label.setIcon(new ImageIcon(sprite));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initTrainerList()
    {
        view.getList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (view.getList1().getSelectedIndex() != -1)
                    writeTrainerToView(view.getList1().getSelectedIndex());
            }
        });
        writeTrainersToList(0);
    }

    private void writeTrainersToList(int selection) {
        ArrayList<String> trainers = new ArrayList<>();
        DefaultListModel list = new DefaultListModel<>();


        for (Trainer trainer : model.getTrainers())
        {
            trainers.add(trainer.getLabel());
        }
        list.addAll(trainers);
        view.getList1().setModel(list);
        view.getList1().setSelectedIndex(selection);

    }

    private void writeMovesToList(ArrayList<String> moves, JList list)
    {
        DefaultListModel movesList = new DefaultListModel<>();
        movesList.addAll(moves);
        list.setModel(movesList);
    }

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
                    panels.get(i).getIvLabel().setText("Total: " + Arrays.stream(mons.get(i).getIvs()).sum() +
                            ", Avg: " + Arrays.stream(mons.get(i).getIvs()).average().getAsDouble());
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
                    panels.get(i).getMon2IVLabel().setText("Total: " + Arrays.stream(mons.get(3 + i).getIvs()).sum() +
                            ", Avg: " + Arrays.stream(mons.get(3 + i).getIvs()).average().getAsDouble());
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
                    panels.get(i).getMon3IVLabel().setText("Total: " + Arrays.stream(mons.get(6 + i).getIvs()).sum() +
                            ", Avg: " + Arrays.stream(mons.get(6 + i).getIvs()).average().getAsDouble());
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
                view.getIvLabel().setText("Total: " + Arrays.stream(mon.getIvs()).sum() +
                        ", Avg: " + Arrays.stream(mon.getIvs()).average().getAsDouble());
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
                view.getHardIVLabel().setText("Total: " + Arrays.stream(hardMon.getIvs()).sum() +
                        ", Avg: " + Arrays.stream(hardMon.getIvs()).average().getAsDouble());
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
                view.getUnfairIVLabel().setText("Total: " + Arrays.stream(unfairMon.getIvs()).sum() +
                        ", Avg: " + Arrays.stream(unfairMon.getIvs()).average().getAsDouble());
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
}
