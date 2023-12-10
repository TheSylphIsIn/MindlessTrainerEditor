package Model;

import Data.Ability;
import Data.Nature;
import Data.Species;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TrainerMon {
    public static final int NUM_STATS = 6;
    private String species;
    private String item;
    private String ability;
    private int level;
    private int[] evs;
    private int[] ivs;
    private ArrayList<String> moves;
    private String nature;
    private String gender;
    private Boolean shiny;
    private String nickname;
    private String ball;

    // "Empty values" constants for prettifying file output
    private static final int[] EMPTY_EVS = {0, 0, 0, 0, 0, 0};
    private static final String NONE = "NONE";
    private static final ArrayList<String> EMPTY_MOVES = new ArrayList<>(Arrays.asList(NONE, NONE, NONE, NONE));

    public TrainerMon() {
        species = Species.NONE.name();
        item = "NONE";
        ability = Ability.NONE.name();
        level = 10;
        evs = new int[]{0, 0, 0, 0, 0, 0};
        ivs = new int[]{0, 0, 0, 0, 0, 0};
        moves = new ArrayList<String>();
        nature = Nature.NONE.name();
        gender = "DEFAULT";
        shiny = false;
        nickname = null;
        ball = "NONE";
    }

    public TrainerMon(String species, String item, String ability, int level,
                      int[] evs, int[] ivs, ArrayList<String> moves, String nature,
                      String gender, Boolean shiny, String nickname, String ball) {
        this.species = species;
        this.item = item;
        this.ability = ability;
        this.level = level;
        this.evs = evs;
        this.ivs = ivs;
        this.moves = moves;
        this.nature = nature;
        this.gender = gender;
        this.shiny = shiny;
        this.nickname = nickname;
        this.ball = ball;
    }

    public void initFromJson(JSONObject object)
    {
        JSONArray evs = (JSONArray) object.get("evs");
        JSONArray ivs = (JSONArray) object.get("ivs");
        JSONArray moves = (JSONArray) object.get("moves");

        this.species = (String) object.get("species");
        this.item = (String) object.get("item");
        this.ability = (String) object.get("ability");
        this.level = Math.toIntExact((Long) object.get("level"));
        this.evs = new int[]{Math.toIntExact((Long) evs.get(0)), Math.toIntExact((Long) evs.get(1)), Math.toIntExact((Long) evs.get(2)),
                Math.toIntExact((Long) evs.get(3)), Math.toIntExact((Long) evs.get(4)), Math.toIntExact((Long) evs.get(5))};
        this.ivs = new int[]{Math.toIntExact((Long) ivs.get(0)), Math.toIntExact((Long) ivs.get(1)), Math.toIntExact((Long) ivs.get(2)),
                Math.toIntExact((Long) ivs.get(3)), Math.toIntExact((Long) ivs.get(4)), Math.toIntExact((Long) ivs.get(5))};
        this.moves = new ArrayList<String>(Arrays.asList((String) moves.get(0),
                (String) moves.get(1), (String) moves.get(2), (String) moves.get(3)));
        this.nature = (String) object.get("nature");
        this.gender = (String) object.get("gender");
        this.shiny = (Boolean) object.get("shiny");
        this.nickname = (String) object.get("nickname");
        this.ball = (String) object.get("ball");
    }

    // TODO
    public JSONObject writeToJson()
    {
        JSONObject mon = new JSONObject();

        JSONArray evs = new JSONArray();
        JSONArray ivs = new JSONArray();
        JSONArray moves = new JSONArray();

        mon.put("species", this.species);
        mon.put("item", this.item);
        mon.put("ability", this.ability);
        mon.put("level", this.level);
        for (int i = 0; i < NUM_STATS; i++)
            evs.add(this.evs[i]);
        mon.put("evs", evs);
        for (int i = 0; i < NUM_STATS; i++)
            ivs.add(this.evs[i]);
        mon.put("ivs", ivs);
        for (int i = 0; i < this.moves.size(); i++)
            moves.add(this.moves.get(i));
        mon.put("moves", moves);
        mon.put("nature", this.nature);
        mon.put("gender", this.gender);
        mon.put("shiny", this.shiny);
        mon.put("nickname", this.nickname);
        mon.put("ball", this.ball);

        return mon;
    }

    public void writeMonToFile(FileWriter output) {
        try {
            output.write("\t{\n\t.species = SPECIES_" + species + ",\n");
            output.write("\t.lvl = " + level + ",\n");
            if (item.compareTo(NONE) != 0)
                output.write("\t.heldItem = ITEM_" + item + ",\n");
            if (ability.compareTo(NONE) != 0)
                output.write("\t.ability = ABILITY_" + ability + ",\n");
            if (!Arrays.equals(evs, EMPTY_EVS))
                output.write("\t.ev = TRAINER_PARTY_EVS(" +
                        evs[0] + ", " + evs[1] + ", " + evs[2] + ", " +
                        evs[3] + ", " + evs[4] + ", " + evs[5] + "),\n");
            if (!Arrays.equals(ivs, EMPTY_EVS))
                output.write("\t.iv = TRAINER_PARTY_IVS(" +
                        ivs[0] + ", " + ivs[1] + ", " + ivs[2] + ", " +
                        ivs[3] + ", " + ivs[4] + ", " + ivs[5] + "),\n");
            if (!moves.equals(EMPTY_MOVES)) {
                output.write("\t.moves = { ");
                for (String move : moves) {
                    output.write("MOVE_" + move + ", ");
                }
                output.write("},\n");
            }
            if (nature.compareTo(NONE) != 0)
                output.write("\t.nature = TRAINER_PARTY_NATURE(NATURE_"
                + nature + "),\n");
            if (gender.compareTo("DEFAULT") != 0)
                output.write("\t.gender = TRAINER_MON_" + gender + ",\n");
            if (shiny)
                output.write("\t.shiny = TRUE,\n");
            if (nickname.compareTo(NONE) != 0)
                output.write("\t.nickname = _(\"" + nickname + "\"),\n");
            if (ball.compareTo(NONE) != 0)
                output.write("\t.ball = ITEM_" + ball + ",\n");

            output.write("\t},\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int[] getEvs() {
        return evs;
    }

    public void setEvs(int[] evs) {
        this.evs = evs;
    }

    public int[] getIvs() {
        return ivs;
    }

    public void setIvs(int[] ivs) {
        this.ivs = ivs;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<String> moves) {
        this.moves = moves;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getShiny() {
        return shiny;
    }

    public void setShiny(Boolean shiny) {
        this.shiny = shiny;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }
}
