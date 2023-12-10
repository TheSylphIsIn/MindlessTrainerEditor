package Test;

import Model.PartySet;
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

            PartySet testSet = new PartySet();
            testSet.initFromJson(partyGroup);

            FileWriter testOutput = new FileWriter("src/data/test_output.h");
            testSet.writePartyToFile(testOutput, (String) trainer.get("label"));

            testOutput.flush();

            System.out.println("Tried output.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
