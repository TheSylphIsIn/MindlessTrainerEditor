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
    private JLabel spriteLabel;
    private JLabel hardSpriteLabel;
    private JLabel unfairSpriteLabel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField labelField;
    private JButton aiFlagsButton;
    private JButton itemsButton;
    private JComboBox trainerClassBox;
    private JComboBox encounterMusicBox;
    private JComboBox trainerSpriteBox;
    private JCheckBox femaleCheckBox;
    private JPanel nestedTrainerPanel;
    private JCheckBox doubleBattleCheckBox;
    private JLabel trainerSpriteLabel;
    private JButton saveButton;
    private JButton discardButton;
    private JLabel statusLabel;
    private JLabel partySizeLabel;
    private JButton removeButton;
    private JLabel overviewSpriteLabel;
    private JLabel overviewClassLabel;
    private JComboBox overviewPartySelectorBox;
    private JLabel mon1Sprite;
    private JPanel overviewTab;
    private JLabel mon2Sprite;
    private JPanel overviewPanel;
    private JLabel mon4Sprite;
    private JLabel mon3Sprite;
    private JLabel mon5Sprite;
    private JLabel mon6Sprite;
    private JLabel mon1LevelLabel;
    private JLabel mon2LevelLabel;
    private JLabel mon3LevelLabel;
    private JLabel mon4LevelLabel;
    private JLabel mon5LevelLabel;
    private JLabel mon6LevelLabel;
    private JLabel mon1HardLevel;
    private JLabel mon2HardLevel;
    private JLabel mon3HardLevel;
    private JLabel mon4HardLevel;
    private JLabel mon5HardLevel;
    private JLabel mon6HardLevel;
    private JLabel mon1HardSprite;
    private JLabel mon2HardSprite;
    private JLabel mon3HardSprite;
    private JLabel mon4HardSprite;
    private JLabel mon5HardSprite;
    private JLabel mon6HardSprite;
    private JPanel normPartySummary;
    private JPanel hardPartySummary;
    private JPanel unfairPartySummary;
    private JLabel mon1UnfairLevel;
    private JLabel mon2UnfairLevel;
    private JLabel mon3UnfairLevel;
    private JLabel mon4UnfairLevel;
    private JLabel mon5UnfairLevel;
    private JLabel mon6UnfairLevel;
    private JLabel mon1UnfairSprite;
    private JLabel mon2UnfairSprite;
    private JLabel mon3UnfairSprite;
    private JLabel mon4UnfairSprite;
    private JLabel mon5UnfairSprite;
    private JLabel mon6UnfairSprite;
    private JCheckBox simpleScriptCheckBox;

    public JButton getSuggestedMusicButton() {
        return suggestedMusicButton;
    }

    public JButton getEditScriptButton() {
        return editScriptButton;
    }

    private JButton editScriptButton;
    private JButton suggestedMusicButton;
    private ArrayList<StarterDependentPanel> starterDependentPanels = new ArrayList<StarterDependentPanel>
            (Arrays.asList(new StarterDependentPanel(), new StarterDependentPanel(), new StarterDependentPanel()));

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JLabel getPartySizeLabel() {
        return partySizeLabel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDiscardButton() {
        return discardButton;
    }

    public JLabel getTrainerSpriteLabel() {
        return trainerSpriteLabel;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getLabelField() {
        return labelField;
    }

    public JButton getAiFlagsButton() {
        return aiFlagsButton;
    }

    public JButton getItemsButton() {
        return itemsButton;
    }

    public JComboBox getTrainerClassBox() {
        return trainerClassBox;
    }

    public JComboBox getEncounterMusicBox() {
        return encounterMusicBox;
    }

    public JComboBox getTrainerSpriteBox() {
        return trainerSpriteBox;
    }

    public JCheckBox getFemaleCheckBox() {
        return femaleCheckBox;
    }

    public JPanel getNestedTrainerPanel() {
        return nestedTrainerPanel;
    }

    public JCheckBox getDoubleBattleCheckBox() {
        return doubleBattleCheckBox;
    }

    public JLabel getSpriteLabel() {
        return spriteLabel;
    }

    public JLabel getHardSpriteLabel() {
        return hardSpriteLabel;
    }

    public JLabel getUnfairSpriteLabel() {
        return unfairSpriteLabel;
    }

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
    public JCheckBox getSimpleScriptCheckBox() {
        return simpleScriptCheckBox;
    }

    public JLabel getMon1HardLevel() {
        return mon1HardLevel;
    }

    public JLabel getMon2HardLevel() {
        return mon2HardLevel;
    }

    public JLabel getMon3HardLevel() {
        return mon3HardLevel;
    }

    public JLabel getMon4HardLevel() {
        return mon4HardLevel;
    }

    public JLabel getMon5HardLevel() {
        return mon5HardLevel;
    }

    public JLabel getMon6HardLevel() {
        return mon6HardLevel;
    }

    public JLabel getMon1HardSprite() {
        return mon1HardSprite;
    }

    public JLabel getMon2HardSprite() {
        return mon2HardSprite;
    }

    public JLabel getMon3HardSprite() {
        return mon3HardSprite;
    }

    public JLabel getMon4HardSprite() {
        return mon4HardSprite;
    }

    public JLabel getMon5HardSprite() {
        return mon5HardSprite;
    }

    public JLabel getMon6HardSprite() {
        return mon6HardSprite;
    }

    public JPanel getNormPartySummary() {
        return normPartySummary;
    }

    public JPanel getHardPartySummary() {
        return hardPartySummary;
    }

    public JPanel getUnfairPartySummary() {
        return unfairPartySummary;
    }

    public JLabel getMon1UnfairLevel() {
        return mon1UnfairLevel;
    }

    public JLabel getMon2UnfairLevel() {
        return mon2UnfairLevel;
    }

    public JLabel getMon3UnfairLevel() {
        return mon3UnfairLevel;
    }

    public JLabel getMon4UnfairLevel() {
        return mon4UnfairLevel;
    }

    public JLabel getMon5UnfairLevel() {
        return mon5UnfairLevel;
    }

    public JLabel getMon6UnfairLevel() {
        return mon6UnfairLevel;
    }

    public JLabel getMon1UnfairSprite() {
        return mon1UnfairSprite;
    }

    public JLabel getMon2UnfairSprite() {
        return mon2UnfairSprite;
    }

    public JLabel getMon3UnfairSprite() {
        return mon3UnfairSprite;
    }

    public JLabel getMon4UnfairSprite() {
        return mon4UnfairSprite;
    }

    public JLabel getMon5UnfairSprite() {
        return mon5UnfairSprite;
    }

    public JLabel getMon6UnfairSprite() {
        return mon6UnfairSprite;
    }

    public JLabel getMon1LevelLabel() {
        return mon1LevelLabel;
    }

    public JLabel getMon2LevelLabel() {
        return mon2LevelLabel;
    }

    public JLabel getMon3LevelLabel() {
        return mon3LevelLabel;
    }

    public JLabel getMon4LevelLabel() {
        return mon4LevelLabel;
    }

    public JLabel getMon5LevelLabel() {
        return mon5LevelLabel;
    }

    public JLabel getMon6LevelLabel() {
        return mon6LevelLabel;
    }

    public JLabel getOverviewSpriteLabel() {
        return overviewSpriteLabel;
    }

    public JLabel getOverviewClassLabel() {
        return overviewClassLabel;
    }

    public JComboBox getOverviewPartySelectorBox() {
        return overviewPartySelectorBox;
    }

    public JLabel getMon1Sprite() {
        return mon1Sprite;
    }

    public JPanel getOverviewTab() {
        return overviewTab;
    }

    public JLabel getMon2Sprite() {
        return mon2Sprite;
    }

    public JPanel getOverviewPanel() {
        return overviewPanel;
    }

    public JLabel getMon4Sprite() {
        return mon4Sprite;
    }

    public JLabel getMon3Sprite() {
        return mon3Sprite;
    }

    public JLabel getMon5Sprite() {
        return mon5Sprite;
    }

    public JLabel getMon6Sprite() {
        return mon6Sprite;
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
