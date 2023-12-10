package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PartySet {
    private ArrayList<TrainerMon> normalParty;
    private ArrayList<TrainerMon> hardParty;
    private ArrayList<TrainerMon> unfairParty;

    public PartySet() {
        normalParty = new ArrayList<>();
        hardParty = new ArrayList<>();
        unfairParty = new ArrayList<>();
    }

    public void initFromJson(JSONObject object) {
        JSONArray normalParty = (JSONArray) object.get("normal");
        JSONArray hardParty = (JSONArray) object.get("hard");
        JSONArray unfairParty = (JSONArray) object.get("unfair");

        int i;

        for (i = 0; i < normalParty.size(); i++)
        {
            TrainerMon mon = new TrainerMon();
            mon.initFromJson((JSONObject) normalParty.get(i));

            this.normalParty.add(mon);
        }
        for (i = 0; i < hardParty.size(); i++)
        {
            TrainerMon mon = new TrainerMon();
            mon.initFromJson((JSONObject) hardParty.get(i));

            this.hardParty.add(mon);
        }
        for (i = 0; i < unfairParty.size(); i++)
        {
            TrainerMon mon = new TrainerMon();
            mon.initFromJson((JSONObject) unfairParty.get(i));

            this.unfairParty.add(mon);
        }
    }

    public JSONObject writeToJson() {
        JSONArray normal = new JSONArray();
        JSONArray hard = new JSONArray();
        JSONArray unfair = new JSONArray();
        int i;

        for (TrainerMon mon : normalParty)
        {
            normal.add(mon.writeToJson());
        }

        for (TrainerMon mon : hardParty)
        {
            hard.add(mon.writeToJson());
        }

        for (TrainerMon mon : unfairParty)
        {
            unfair.add(mon.writeToJson());
        }

        JSONObject parties = new JSONObject();
        parties.put("normal", normal);
        parties.put("hard", hard);
        parties.put("unfair", unfair);

        return parties;
    }

    public void writePartyToFile(FileWriter output, String label) {
        try {
            output.write("static const struct TrainerMon sParty_" + label + "[] = {\n");
            for (TrainerMon mon : normalParty)
            {
                mon.writeMonToFile(output);
            }
            output.write("};\n\n");

            output.write("static const struct TrainerMon sParty_" + label + "Hard[] = {\n");
            for (TrainerMon mon : hardParty)
            {
                mon.writeMonToFile(output);
            }
            output.write("};\n\n");

            output.write("static const struct TrainerMon sParty_" + label + "Unfair[] = {\n");
            for (TrainerMon mon : unfairParty)
            {
                mon.writeMonToFile(output);
            }
            output.write("};\n\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
