package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
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
    private ArrayList<PartySet> parties;

    public Trainer() {}

    public Trainer(String id, String name, String label, ArrayList<String> aiFlags, ArrayList<String> items,
                   String trainerClass, String encounterMusic, String pic, Boolean female, Boolean doubleBattle,
                   Boolean starterDependent, ArrayList<PartySet> parties) {
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
        starterDependent = (Boolean) object.get("starter_dependent");

        parties = new ArrayList<>();

        for (Object set : partiesData)
        {
            PartySet partySet = new PartySet();
            partySet.initFromJson((JSONObject) set);
            parties.add(partySet);
        }
    }

    public void writeTrainerToFile(FileWriter output) {
        int starterNum, setNum;

        for (setNum = 0; setNum < 1; setNum++)
        {
            for (starterNum = 0; starterNum < 3; starterNum++)
            {
                parties.get(starterNum + setNum).writePartyToFile(output, label + "Set" + setNum + "Choice" + starterNum);
            }
        }
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
