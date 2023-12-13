package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainModel {
    private ArrayList<Trainer> trainers;
    public final String outputPath = "C:/fandango/src/data";

    public MainModel() {
        initFromJson();
    }

    public void initFromJson() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/data/trainer_data.json");
            JSONArray data = (JSONArray) parser.parse(reader);
            trainers = new ArrayList<Trainer>();
            for (Object trainer : data)
            {
                Trainer add = new Trainer();
                add.initFromJson((JSONObject) trainer);
                trainers.add(add);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToJson()
    {
        JSONArray file = new JSONArray();

        for (Trainer trainer : trainers)
        {
            file.add(trainer.writeToJson());
        }

        try {
            FileWriter jsonOutput = new FileWriter("src/data/trainer_data.json");
            jsonOutput.write(file.toJSONString());

            jsonOutput.flush();
            jsonOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeModelToFile()
    {
        try {
            FileWriter trainerOutput = new FileWriter(outputPath + "/trainers.h");
            FileWriter partyOutput = new FileWriter(outputPath + "/trainer_parties.h");
            FileWriter starterOutput = new FileWriter(outputPath + "/starter_dependent_parties.h");

            trainerOutput.write("/* DO NOT EDIT! THESE FILES ARE OUTPUT BY THE MINDLESS TRAINER EDITOR." +
                    "MODIFICATIONS WILL BE LOST IF IT IS USED AGAIN. */\n\n" + "const struct Trainer gTrainers[] = {\n\n");
            starterOutput.write("/* DO NOT EDIT! THESE FILES ARE OUTPUT BY THE MINDLESS TRAINER EDITOR." +
                    "MODIFICATIONS WILL BE LOST IF IT IS USED AGAIN. */\n\n");

            for (Trainer trainer : trainers)
            {
                if (trainer.getStarterDependent())
                    trainer.writeTrainerToFile(trainerOutput, starterOutput);
                else
                    trainer.writeTrainerToFile(trainerOutput, partyOutput);
            }

            trainerOutput.write("\n};");

            // Normal
            starterOutput.write("/* DO NOT EDIT! THESE FILES ARE OUTPUT BY THE MINDLESS TRAINER EDITOR." +
                    "MODIFICATIONS WILL BE LOST IF IT IS USED AGAIN. */\n\n" + "static const struct TrainerMon * const sStarterDependentParties[STARTER_MON_COUNT][STARTER_DEPENDENT_PARTIES_COUNT] = {\n");
            for (int i = 0; i < 3; i++)
            {
                starterOutput.write("\t[STARTER_MON_" + i + "] = {\n");
                for (Trainer trainer : trainers)
                {
                    if (!trainer.getStarterDependent())
                        continue;
                    starterOutput.write("\t\t[TRAINER_" + trainer.getId() + " - FIRST_STARTER_DEPENDENT_INDEX] = sParty_" +
                            trainer.getLabel() + "Set0Choice" + i + ",\n");
                }
                starterOutput.write("\t},\n");
            }
            starterOutput.write("};\n\n");

            // Hard
            starterOutput.write("static const struct TrainerMon * const sStarterDependentPartiesHard[STARTER_MON_COUNT][STARTER_DEPENDENT_PARTIES_COUNT] = {\n");
            for (int i = 0; i < 3; i++)
            {
                starterOutput.write("\t[STARTER_MON_" + i + "] = {\n");
                for (Trainer trainer : trainers)
                {
                    if (!trainer.getStarterDependent())
                        continue;
                    starterOutput.write("\t\t[TRAINER_" + trainer.getId() + " - FIRST_STARTER_DEPENDENT_INDEX] = sParty_" +
                            trainer.getLabel() + "Set0Choice" + i + "Hard,\n");
                }
                starterOutput.write("\t},\n");
            }
            starterOutput.write("};\n\n");

            // Unfair
            starterOutput.write("static const struct TrainerMon * const sStarterDependentPartiesUnfair[STARTER_MON_COUNT][STARTER_DEPENDENT_PARTIES_COUNT] = {\n");
            for (int i = 0; i < 3; i++)
            {
                starterOutput.write("\t[STARTER_MON_" + i + "] = {\n");
                for (Trainer trainer : trainers)
                {
                    if (!trainer.getStarterDependent())
                        continue;
                    starterOutput.write("\t\t[TRAINER_" + trainer.getId() + " - FIRST_STARTER_DEPENDENT_INDEX] = sParty_" +
                            trainer.getLabel() + "Set0Choice" + i + "Unfair,\n");
                }
                starterOutput.write("\t},\n");
            }
            starterOutput.write("};\n\n");

            // party sizes
            starterOutput.write("static const u8 sStarterDependentPartySizes[3][STARTER_DEPENDENT_PARTIES_COUNT] = {\n");
            starterOutput.write("\t[OPTIONS_DIFFICULTY_NORMAL] = {\n");
            for (Trainer trainer : trainers)
            {
                if (!trainer.getStarterDependent())
                    continue;
                starterOutput.write("\t\t[TRAINER_" + trainer.getId() + " - FIRST_STARTER_DEPENDENT_INDEX] = ARRAY_COUNT(sParty_" +
                        trainer.getLabel() + "Set0Choice0),\n"); // set0choice0 assumes that each party will be the same size among the same "tier"
            }
            starterOutput.write("\t},\n");
            starterOutput.write("\t[OPTIONS_DIFFICULTY_HARD] = {\n");
            for (Trainer trainer : trainers)
            {
                if (!trainer.getStarterDependent())
                    continue;
                starterOutput.write("\t\t[TRAINER_" + trainer.getId() + " - FIRST_STARTER_DEPENDENT_INDEX] = ARRAY_COUNT(sParty_" +
                        trainer.getLabel() + "Set0Choice0Hard),\n");
            }
            starterOutput.write("\t},\n");
            starterOutput.write("\t[OPTIONS_DIFFICULTY_UNFAIR] = {\n");
            for (Trainer trainer : trainers)
            {
                if (!trainer.getStarterDependent())
                    continue;
                starterOutput.write("\t\t[TRAINER_" + trainer.getId() + " - FIRST_STARTER_DEPENDENT_INDEX] = ARRAY_COUNT(sParty_" +
                        trainer.getLabel() + "Set0Choice0Unfair),\n");
            }
            starterOutput.write("\t},\n");
            starterOutput.write("};\n\n");

            trainerOutput.flush();
            partyOutput.flush();
            starterOutput.flush();

            trainerOutput.close();
            partyOutput.close();
            starterOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
    }
}
