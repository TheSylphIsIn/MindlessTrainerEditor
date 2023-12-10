package Test;

import Model.PartySet;
import Model.Trainer;
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

            Trainer testTrainer = new Trainer();
            testTrainer.initFromJson(trainer);

            FileWriter trainerTest = new FileWriter("src/data/trainers.h");
            FileWriter partyTest = new FileWriter("src/data/trainer_parties.h");
            FileWriter starterTest = new FileWriter("src/data/starter_dependent_parties.h");
            if (testTrainer.getStarterDependent())
                testTrainer.writeTrainerToFile(trainerTest, starterTest);
            else
                testTrainer.writeTrainerToFile(trainerTest, partyTest);

            trainerTest.flush();
            partyTest.flush();
            starterTest.flush();

            System.out.println("Tried output.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
