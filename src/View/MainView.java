package View;

import javax.swing.*;

public class MainView {
    private JList list1;
    private JTabbedPane overallPane;
    private JPanel trainerTab;
    private JPanel monsTab;
    private JCheckBox difficultyCheckBox;
    private JTabbedPane partiesPane;
    private JPanel normPartyTab;
    private JComboBox partyIndexBox;
    private JPanel mainPanel;
    private JButton addNewButton;
    private JButton editSelectedButton;
    private JButton deleteSelectedButton;
    private JTextField nickField;
    private JList movesList;
    private JButton evButton;
    private JButton ivButton;
    private JButton editMovesButton;
    private JComboBox speciesBox;
    private JComboBox itemBox;
    private JComboBox abilBox;
    private JSpinner levelSpinner;
    private JComboBox natureBox;
    private JSpinner friendshipSpinner;
    private JComboBox ballBox;
    private JCheckBox shinyBox;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton defaultRadioButton;
    private JButton copyButton;
    private JPanel normalPartyPanel;
    private JPanel hardPartyTab;
    private JComboBox hardSpeciesBox;
    private JComboBox hardItemBox;
    private JComboBox hardNatureBox;
    private JSpinner hardLevelBox;
    private JComboBox hardAbilBox;
    private JSpinner hardFriendshipSpinner;
    private JButton hardEVButton;
    private JButton hardIVButton;
    private JList hardMoveList;
    private JButton hardEditMovesButton;
    private JTextField hardNickField;
    private JRadioButton hardMaleButton;
    private JRadioButton hardFemaleButton;
    private JRadioButton hardDefaultButton;
    private JCheckBox hardShinyBox;
    private JComboBox hardBallBox;

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
