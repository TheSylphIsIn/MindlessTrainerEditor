import Controller.Controller;
import Data.*;
import Model.MainModel;
import Model.TrainerMon;
import Test.OutputTest;
import View.MainView;
import View.StarterDependentPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainView view = new MainView();

        JFrame frame = new JFrame();
        Controller controller = new Controller(model, view, frame);
        frame.setContentPane(view.getMainPanel());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mindless Trainer Editor");
        try {
            BufferedImage sprite = ImageIO.read(new File("mte_assets/icon.png"));
            frame.setIconImage(sprite);
        } catch (IOException e) {
        }
    }
}