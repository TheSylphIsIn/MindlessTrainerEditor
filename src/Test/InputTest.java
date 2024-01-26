package Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;

public class InputTest {

    public InputTest(String repoPath) {
        try {
            FileReader reader = new FileReader(repoPath + "/include/constants/fandango_opponents.h");
            ArrayList<String> opponents = new ArrayList<>();
            String inputStream = "";

            do {
                // Read up to the next constant label
                while (!inputStream.contains("#define ")) {
                    inputStream = inputStream.concat(String.valueOf((char) reader.read()));
                }

                // Prepare for reading the constant name
                inputStream = String.valueOf((char) reader.read());
                String addVal = "";

                // Read until the end of the constant name
                do {
                    addVal = addVal.concat(inputStream);
                    inputStream = String.valueOf((char) reader.read());
                } while (!inputStream.equals(" "));

                // Exit once you reach FIRST_STARTER_DEPENDENT_INDEX, the first non-opponent value in the file
                if (addVal.equals("FIRST_STARTER_DEPENDENT_INDEX"))
                    break;

                opponents.add(addVal);
            } while (true);

            System.out.println(opponents.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
