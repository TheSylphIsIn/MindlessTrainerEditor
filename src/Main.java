import Controller.Controller;
import Model.MainModel;
import View.MainView;
import View.StarterDependentPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainView view = new MainView();

        JFrame frame = new JFrame();
        frame.setContentPane(view.getMainPanel());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Controller controller = new Controller(model, view, frame);
    }
}