package Model;

import Data.EncounterMusic;
import Data.Item;
import Data.TrainerClass;
import Data.TrainerPic;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Trainer {
    private String id;
    private String name;
    private String label;
    private ArrayList<String> aiFlags;
    private ArrayList<String> items;
    private String trainerClass;
    private String encounterMusic;
    private String pic;
    private Boolean female;
    private Boolean doubleBattle;
    private Boolean starterDependent;
    private Boolean difficulty;
    private ArrayList<PartySet> parties;

    private static final ArrayList<String> EMPTY_ITEMS = new ArrayList<String>(Arrays.asList(Item.NONE.name(), Item.NONE.name(), Item.NONE.name(), Item.NONE.name()));

    public static final int NUM_STARTER_SETS = 1;
    public static final int NUM_STARTERS = 3;
    public Trainer() {
        id = "DUMMY";
        name = "Dummy";
        label = "Dummy";
        aiFlags = new ArrayList<>();
        items = new ArrayList<>();
        this.trainerClass = TrainerClass.HIKER.name();
        this.encounterMusic = EncounterMusic.MALE.name();
        this.pic = TrainerPic.BRENDAN.name();
        this.female = false;
        this.doubleBattle = false;
        this.starterDependent = false;
        this.difficulty = false;
        parties = new ArrayList<>();
        parties.add(new PartySet());
    }

    public Trainer(String label) {
        this.id = "DUMMY";
        this.name = label;
        this.label = label;
        this.aiFlags = new ArrayList<String>();
        this.items = new ArrayList<String>(Arrays.asList("NONE", "NONE", "NONE", "NONE"));
        this.trainerClass = TrainerClass.HIKER.name();
        this.encounterMusic = EncounterMusic.MALE.name();
        this.pic = TrainerPic.BRENDAN.name();
        this.female = false;
        this.doubleBattle = false;
        this.starterDependent = false;
        this.difficulty = false;
        this.parties = new ArrayList<PartySet>();
        parties.add(new PartySet());
    }

    public Trainer(String id, String name, String label, ArrayList<String> aiFlags, ArrayList<String> items,
                   String trainerClass, String encounterMusic, String pic, Boolean female, Boolean doubleBattle,
                   Boolean starterDependent, Boolean difficulty, ArrayList<PartySet> parties) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.aiFlags = aiFlags;
        this.items = items;
        this.trainerClass = trainerClass;
        this.encounterMusic = encounterMusic;
        this.pic = pic;
        this.female = female;
        this.doubleBattle = doubleBattle;
        this.starterDependent = starterDependent;
        this.difficulty = difficulty;
        this.parties = parties;
    }

    public void initFromJson(JSONObject object) {
        JSONArray itemsData = (JSONArray) object.get("items");
        JSONArray aiFlagsData = (JSONArray) object.get("ai");
        JSONArray partiesData = (JSONArray) object.get("parties");

        id = (String) object.get("id");
        name = (String) object.get("name");
        label = (String) object.get("label");
        aiFlags = new ArrayList<String>();
        for (Object flag : aiFlagsData)
            aiFlags.add((String) flag);
        items = new ArrayList<String>(Arrays.asList((String) itemsData.get(0), (String) itemsData.get(1),
                (String) itemsData.get(2), (String) itemsData.get(3)));
        trainerClass = (String) object.get("trainer_class");
        encounterMusic = (String) object.get("encounter_music");
        pic = (String) object.get("pic");
        female = (Boolean) object.get("female");
        doubleBattle = (Boolean) object.get("double_battle");
        difficulty = (Boolean) object.get("difficulty");
        starterDependent = (Boolean) object.get("starter_dependent");

        parties = new ArrayList<>();

        for (Object set : partiesData)
        {
            PartySet partySet = new PartySet();
            partySet.initFromJson((JSONObject) set);
            parties.add(partySet);
        }
    }

    public JSONObject writeToJson()
    {
        JSONObject object = new JSONObject();

        object.put("id", id);
        object.put("name", name);
        object.put("label", label);
        object.put("trainer_class", trainerClass);
        object.put("encounter_music", encounterMusic);
        object.put("pic", pic);
        object.put("female", female);
        object.put("double_battle", doubleBattle);
        object.put("difficulty", difficulty);
        object.put("starter_dependent", starterDependent);

        JSONArray aiFlagsData = new JSONArray();
        for (String flag : aiFlags)
        {
            aiFlagsData.add(flag);
        }
        object.put("ai", aiFlagsData);

        JSONArray itemsData = new JSONArray();
        for (String item : items)
            itemsData.add(item);
        object.put("items", itemsData);

        JSONArray partiesData = new JSONArray();
        for (PartySet partySet : parties)
            partiesData.add(partySet.writeToJson());
        object.put("parties", partiesData);

        return object;
    }

    public void writeTrainerToFile(FileWriter trainerOutput, FileWriter partyOutput) {
        int starterNum, setNum;

        try {
            // Array assignment
            trainerOutput.write("\t[TRAINER_" + id + "] =\n\t{\n");
            // Name
            trainerOutput.write("\t\t.name = _(\"" + name + "\"),\n");
            // Trainer Class
            trainerOutput.write("\t\t.trainerClass = TRAINER_CLASS_" + trainerClass + ",\n");
            // Trainer pic
            trainerOutput.write("\t\t.trainerPic = TRAINER_PIC_" + pic + ",\n");
            // Encounter music and gender
            trainerOutput.write("\t\t.encounterMusic_gender = ");
            if (female)
                trainerOutput.write("F_TRAINER_FEMALE | ");
            trainerOutput.write("TRAINER_ENCOUNTER_MUSIC_" + encounterMusic + ",\n");

            // AI flags
            trainerOutput.write("\t\t.aiFlags = ");

            Boolean firstVal = true;
            for (String flag : aiFlags)
            {
                if (!firstVal)
                    trainerOutput.write(" | ");
                firstVal = false;

                trainerOutput.write("AI_FLAG_" + flag);
            }
            trainerOutput.write(",\n");

            // Items
            trainerOutput.write("\t\t.items = { ");
            if (!items.equals(EMPTY_ITEMS)) {
                for (String item : items) {
                    trainerOutput.write("ITEM_" + item + ", ");
                }
            }
            trainerOutput.write("},\n");

            // Double battle
            if (doubleBattle)
                trainerOutput.write("\t\t.doubleBattle = TRUE,\n");
            else
                trainerOutput.write("\t\t.doubleBattle = FALSE,\n");

            // Party
            if (starterDependent)
            {
                trainerOutput.write("\t\t.party = TRAINER_PARTY_STARTER_DEPENDENT,\n");
            }
            else if (difficulty)
            {
                trainerOutput.write("\t\t.party = TRAINER_PARTY(" + label + "),\n");
            }
            else
            {
                trainerOutput.write("\t\t.party = TRAINER_PARTY_NO_DIFF(" + label + "),\n");
            }

            trainerOutput.write("\t},\n\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (starterDependent) {
            for (setNum = 0; setNum < NUM_STARTER_SETS; setNum++) {
                for (starterNum = 0; starterNum < NUM_STARTERS; starterNum++) {
                    parties.get(starterNum + setNum).writePartyToFile(partyOutput, label + "Set" + setNum + "Choice" + starterNum, difficulty);
                }
            }
        }
        else
        {
            parties.get(0).writePartyToFile(partyOutput, label, difficulty);
        }
    }

    public void prepForStarterSets() {
        if (parties.size() < NUM_STARTERS * NUM_STARTER_SETS)
        {
            // Add dummy parties until there is one for each slot
            for (int i = 0; i < NUM_STARTERS * NUM_STARTER_SETS; i++)
                parties.add(parties.get(0));
        }
    }

    public void copyFrom(Trainer src) {
        this.id = src.getId();
        this.name = src.getName();
        this.label = src.getLabel();
        this.aiFlags = src.getAiFlags();
        this.items = src.getItems();
        this.trainerClass = src.getTrainerClass();
        this.encounterMusic = src.getEncounterMusic();
        this.pic = src.getPic();
        this.female = src.getFemale();
        this.doubleBattle = src.getDoubleBattle();
        this.starterDependent = src.getStarterDependent();
        this.difficulty = src.getDifficulty();
        this.parties = new ArrayList<PartySet>();
        this.parties.add(new PartySet());
        this.parties.add(new PartySet());
        this.parties.add(new PartySet());

        for (int i = 0; i < this.parties.size() && i < src.getParties().size(); i++)
        {
            this.parties.get(i).copyFrom(src.getParties().get(i));
        }

    }

    public Boolean getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Boolean difficulty) {
        this.difficulty = difficulty;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAiFlags() {
        return aiFlags;
    }

    public void setAiFlags(ArrayList<String> aiFlags) {
        this.aiFlags = aiFlags;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public String getTrainerClass() {
        return trainerClass;
    }

    public void setTrainerClass(String trainerClass) {
        this.trainerClass = trainerClass;
    }

    public String getEncounterMusic() {
        return encounterMusic;
    }

    public void setEncounterMusic(String encounterMusic) {
        this.encounterMusic = encounterMusic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Boolean getFemale() {
        return female;
    }

    public void setFemale(Boolean female) {
        this.female = female;
    }

    public Boolean getDoubleBattle() {
        return doubleBattle;
    }

    public void setDoubleBattle(Boolean doubleBattle) {
        this.doubleBattle = doubleBattle;
    }

    public Boolean getStarterDependent() {
        return starterDependent;
    }

    public void setStarterDependent(Boolean starterDependent) {
        this.starterDependent = starterDependent;
    }

    public ArrayList<PartySet> getParties() {
        return parties;
    }

    public void setParties(ArrayList<PartySet> parties) {
        this.parties = parties;
    }
}
