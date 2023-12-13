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
            FileReader reader = new FileReader(outputPath + "/trainer_data.json");
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
            FileWriter jsonOutput = new FileWriter(outputPath + "/trainer_data.json");
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
            trainerOutput.write("\t[TRAINER_NONE] =\n\t{\n\t\t.trainerClass = TRAINER_CLASS_PKMN_TRAINER_1,\n\t\t" +
                    ".encounterMusic_gender = TRAINER_ENCOUNTER_MUSIC_MALE,\n\t\t.trainerPic = TRAINER_PIC_HIKER,\n\t\t" +
                    ".trainerName = _(\"\"),\n\t\t.items = {},\n\t\t.doubleBattle = FALSE,\n\t\t.aiFlags = 0,\n\t\t" +
                    ".partySize = 0,\n\t\t.party = NULL,\n\t},\n\n");

            starterOutput.write("/* DO NOT EDIT! THESE FILES ARE OUTPUT BY THE MINDLESS TRAINER EDITOR." +
                    "MODIFICATIONS WILL BE LOST IF IT IS USED AGAIN. */\n\n");

            partyOutput.write("static const struct TrainerMon sParty_Dummy[] = {\n" +
                    "    {\n" +
                    "    .iv = TRAINER_PARTY_IVS(31, 31, 31, 31, 31, 31),\n" +
                    "    .lvl = 6,\n" +
                    "    .species = SPECIES_DUBSNAKE,\n" +
                    "    .ability = ABILITY_ICE_BODY,\n" +
                    "    .moves = {MOVE_ACID, MOVE_ICE_SHARD, MOVE_BLOCK, MOVE_PERISH_SONG}\n" +
                    "    },\n};\n\n");

            for (Trainer trainer : trainers)
            {
                if (trainer.getStarterDependent())
                    trainer.writeTrainerToFile(trainerOutput, starterOutput);
                else
                    trainer.writeTrainerToFile(trainerOutput, partyOutput);
            }

            trainerOutput.write("\n};\n");

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
