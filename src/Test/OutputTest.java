package Test;

import Model.TrainerMon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OutputTest {

    public OutputTest() {
        try {
            TrainerMon testMon = new TrainerMon();
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/data/data.json");

            // Go deep in the weeds for the array of mons
            JSONArray data = (JSONArray) parser.parse(reader);
            JSONObject trainer = (JSONObject) data.get(0);
            JSONArray parties = (JSONArray) trainer.get("parties");
            JSONObject partyGroup = (JSONObject) parties.get(0);
            JSONArray party = (JSONArray) partyGroup.get("normal");
            testMon.initFromJson((JSONObject) party.get(0));
            FileWriter testOutput = new FileWriter("src/data/test_output.h");
            testMon.writeMonToFile(testOutput);

            party = (JSONArray) partyGroup.get("hard");
            testMon.initFromJson((JSONObject) party.get(0));
            testMon.writeMonToFile(testOutput);

            party = (JSONArray) partyGroup.get("unfair");
            testMon.initFromJson((JSONObject) party.get(0));
            testMon.writeMonToFile(testOutput);

            testOutput.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
