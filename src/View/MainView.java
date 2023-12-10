package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    private JButton addButton;
    private JLabel ivLabel;
    private JLabel evLabel;
    private JPanel hardPartyPanel;
    private JPanel unfairPartyTab;
    private JPanel unfairPartyPanel;
    private JCheckBox starterDependentCheckBox;
    private JComboBox hardAbilBox;
    private JComboBox unfairAbilBox;
    private JList hardMovesList;
    private JButton hardEVButton;
    private JButton hardIVButton;
    private JLabel hardEVLabel;
    private JLabel hardIVLabel;
    private JSpinner hardLevelSpinner;
    private JComboBox hardSpeciesBox;
    private JComboBox hardItemBox;
    private JComboBox hardNatureBox;
    private JButton hardEditMovesButton;
    private JSpinner hardFriendshipSpinner;
    private JCheckBox hardShinyCheckBox;
    private JTextField hardNickField;
    private JComboBox hardBallBox;
    private JRadioButton hardMaleButton;
    private JRadioButton hardFemaleButton;
    private JRadioButton hardDefaultButton;
    private JComboBox unfairSpeciesBox;
    private JSpinner unfairLevelSpinner;
    private JComboBox unfairItemBox;
    private JComboBox unfairNatureBox;
    private JLabel unfairEVLabel;
    private JLabel unfairIVLabel;
    private JButton unfairEVButton;
    private JButton unfairIVButton;
    private JList unfairMoveList;
    private JButton unfairMoveButton;
    private JSpinner unfairFriendshipSpinner;
    private JCheckBox unfairShinyBox;
    private JTextField unfairNickField;
    private JComboBox unfairBallBox;
    private JRadioButton unfairMaleButton;
    private JRadioButton unfairFemaleButton;
    private JRadioButton unfairDefaultButton;
    private ArrayList<StarterDependentPanel> starterDependentPanels = new ArrayList<StarterDependentPanel>
            (Arrays.asList(new StarterDependentPanel(), new StarterDependentPanel(), new StarterDependentPanel()));

    public ArrayList<StarterDependentPanel> getStarterDependentPanels() {
        return starterDependentPanels;
    }

    public JComboBox getHardAbilBox() {
        return hardAbilBox;
    }

    public JComboBox getUnfairAbilBox() {
        return unfairAbilBox;
    }

    public JList getHardMovesList() {
        return hardMovesList;
    }

    public JButton getHardEVButton() {
        return hardEVButton;
    }

    public JButton getHardIVButton() {
        return hardIVButton;
    }

    public JLabel getHardEVLabel() {
        return hardEVLabel;
    }

    public JLabel getHardIVLabel() {
        return hardIVLabel;
    }

    public JSpinner getHardLevelSpinner() {
        return hardLevelSpinner;
    }

    public JComboBox getHardSpeciesBox() {
        return hardSpeciesBox;
    }

    public JComboBox getHardItemBox() {
        return hardItemBox;
    }

    public JComboBox getHardNatureBox() {
        return hardNatureBox;
    }

    public JButton getHardEditMovesButton() {
        return hardEditMovesButton;
    }

    public JSpinner getHardFriendshipSpinner() {
        return hardFriendshipSpinner;
    }

    public JCheckBox getHardShinyCheckBox() {
        return hardShinyCheckBox;
    }

    public JTextField getHardNickField() {
        return hardNickField;
    }

    public JComboBox getHardBallBox() {
        return hardBallBox;
    }

    public JRadioButton getHardMaleButton() {
        return hardMaleButton;
    }

    public JRadioButton getHardFemaleButton() {
        return hardFemaleButton;
    }

    public JRadioButton getHardDefaultButton() {
        return hardDefaultButton;
    }

    public JComboBox getUnfairSpeciesBox() {
        return unfairSpeciesBox;
    }

    public JSpinner getUnfairLevelSpinner() {
        return unfairLevelSpinner;
    }

    public JComboBox getUnfairItemBox() {
        return unfairItemBox;
    }

    public JComboBox getUnfairNatureBox() {
        return unfairNatureBox;
    }

    public JLabel getUnfairEVLabel() {
        return unfairEVLabel;
    }

    public JLabel getUnfairIVLabel() {
        return unfairIVLabel;
    }

    public JButton getUnfairEVButton() {
        return unfairEVButton;
    }

    public JButton getUnfairIVButton() {
        return unfairIVButton;
    }

    public JList getUnfairMoveList() {
        return unfairMoveList;
    }

    public JButton getUnfairMoveButton() {
        return unfairMoveButton;
    }

    public JSpinner getUnfairFriendshipSpinner() {
        return unfairFriendshipSpinner;
    }

    public JCheckBox getUnfairShinyBox() {
        return unfairShinyBox;
    }

    public JTextField getUnfairNickField() {
        return unfairNickField;
    }

    public JComboBox getUnfairBallBox() {
        return unfairBallBox;
    }

    public JRadioButton getUnfairMaleButton() {
        return unfairMaleButton;
    }

    public JRadioButton getUnfairFemaleButton() {
        return unfairFemaleButton;
    }

    public JRadioButton getUnfairDefaultButton() {
        return unfairDefaultButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTabbedPane getPartiesPane() {
        return partiesPane;
    }

    public void setNormPartyTab(JPanel normPartyTab) {
        this.normPartyTab = normPartyTab;
    }

    public JCheckBox getStarterDependentCheckBox() {
        return starterDependentCheckBox;
    }

    public JList getList1() {
        return list1;
    }

    public void setList1(JList list1) {
        this.list1 = list1;
    }

    public JTabbedPane getOverallPane() {
        return overallPane;
    }

    public void setOverallPane(JTabbedPane overallPane) {
        this.overallPane = overallPane;
    }

    public JPanel getTrainerTab() {
        return trainerTab;
    }

    public void setTrainerTab(JPanel trainerTab) {
        this.trainerTab = trainerTab;
    }

    public JPanel getMonsTab() {
        return monsTab;
    }

    public void setMonsTab(JPanel monsTab) {
        this.monsTab = monsTab;
    }

    public JCheckBox getDifficultyCheckBox() {
        return difficultyCheckBox;
    }

    public void setDifficultyCheckBox(JCheckBox difficultyCheckBox) {
        this.difficultyCheckBox = difficultyCheckBox;
    }

    public void setPartiesPane(JTabbedPane partiesPane) {
        this.partiesPane = partiesPane;
    }

    public JPanel getNormPartyTab() {
        return normPartyTab;
    }

    public JComboBox getPartyIndexBox() {
        return partyIndexBox;
    }

    public void setPartyIndexBox(JComboBox partyIndexBox) {
        this.partyIndexBox = partyIndexBox;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JButton getAddNewButton() {
        return addNewButton;
    }

    public void setAddNewButton(JButton addNewButton) {
        this.addNewButton = addNewButton;
    }

    public JButton getEditSelectedButton() {
        return editSelectedButton;
    }

    public void setEditSelectedButton(JButton editSelectedButton) {
        this.editSelectedButton = editSelectedButton;
    }

    public JButton getDeleteSelectedButton() {
        return deleteSelectedButton;
    }

    public void setDeleteSelectedButton(JButton deleteSelectedButton) {
        this.deleteSelectedButton = deleteSelectedButton;
    }

    public JTextField getNickField() {
        return nickField;
    }

    public void setNickField(JTextField nickField) {
        this.nickField = nickField;
    }

    public JList getMovesList() {
        return movesList;
    }

    public void setMovesList(JList movesList) {
        this.movesList = movesList;
    }

    public JButton getEvButton() {
        return evButton;
    }

    public void setEvButton(JButton evButton) {
        this.evButton = evButton;
    }

    public JButton getIvButton() {
        return ivButton;
    }

    public void setIvButton(JButton ivButton) {
        this.ivButton = ivButton;
    }

    public JButton getEditMovesButton() {
        return editMovesButton;
    }

    public void setEditMovesButton(JButton editMovesButton) {
        this.editMovesButton = editMovesButton;
    }

    public JComboBox getSpeciesBox() {
        return speciesBox;
    }

    public void setSpeciesBox(JComboBox speciesBox) {
        this.speciesBox = speciesBox;
    }

    public JComboBox getItemBox() {
        return itemBox;
    }

    public void setItemBox(JComboBox itemBox) {
        this.itemBox = itemBox;
    }

    public JComboBox getAbilBox() {
        return abilBox;
    }

    public void setAbilBox(JComboBox abilBox) {
        this.abilBox = abilBox;
    }

    public JSpinner getLevelSpinner() {
        return levelSpinner;
    }

    public void setLevelSpinner(JSpinner levelSpinner) {
        this.levelSpinner = levelSpinner;
    }

    public JComboBox getNatureBox() {
        return natureBox;
    }

    public void setNatureBox(JComboBox natureBox) {
        this.natureBox = natureBox;
    }

    public JSpinner getFriendshipSpinner() {
        return friendshipSpinner;
    }

    public void setFriendshipSpinner(JSpinner friendshipSpinner) {
        this.friendshipSpinner = friendshipSpinner;
    }

    public JComboBox getBallBox() {
        return ballBox;
    }

    public void setBallBox(JComboBox ballBox) {
        this.ballBox = ballBox;
    }

    public JCheckBox getShinyBox() {
        return shinyBox;
    }

    public void setShinyBox(JCheckBox shinyBox) {
        this.shinyBox = shinyBox;
    }

    public JRadioButton getMaleRadioButton() {
        return maleRadioButton;
    }

    public void setMaleRadioButton(JRadioButton maleRadioButton) {
        this.maleRadioButton = maleRadioButton;
    }

    public JRadioButton getFemaleRadioButton() {
        return femaleRadioButton;
    }

    public void setFemaleRadioButton(JRadioButton femaleRadioButton) {
        this.femaleRadioButton = femaleRadioButton;
    }

    public JRadioButton getDefaultRadioButton() {
        return defaultRadioButton;
    }

    public void setDefaultRadioButton(JRadioButton defaultRadioButton) {
        this.defaultRadioButton = defaultRadioButton;
    }

    public JButton getCopyButton() {
        return copyButton;
    }

    public void setCopyButton(JButton copyButton) {
        this.copyButton = copyButton;
    }

    public JPanel getNormalPartyPanel() {
        return normalPartyPanel;
    }

    public void setNormalPartyPanel(JPanel normalPartyPanel) {
        this.normalPartyPanel = normalPartyPanel;
    }

    public JPanel getHardPartyTab() {
        return hardPartyTab;
    }

    public void setHardPartyTab(JPanel hardPartyTab) {
        this.hardPartyTab = hardPartyTab;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JLabel getIvLabel() {
        return ivLabel;
    }

    public void setIvLabel(JLabel ivLabel) {
        this.ivLabel = ivLabel;
    }

    public JLabel getEvLabel() {
        return evLabel;
    }

    public void setEvLabel(JLabel evLabel) {
        this.evLabel = evLabel;
    }

    public JPanel getHardPartyPanel() {
        return hardPartyPanel;
    }

    public void setHardPartyPanel(JPanel hardPartyPanel) {
        this.hardPartyPanel = hardPartyPanel;
    }

    public JPanel getUnfairPartyTab() {
        return unfairPartyTab;
    }

    public void setUnfairPartyTab(JPanel unfairPartyTab) {
        this.unfairPartyTab = unfairPartyTab;
    }

    public JPanel getUnfairPartyPanel() {
        return unfairPartyPanel;
    }

    public void setUnfairPartyPanel(JPanel unfairPartyPanel) {
        this.unfairPartyPanel = unfairPartyPanel;
    }
}
