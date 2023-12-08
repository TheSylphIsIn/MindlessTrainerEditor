import View.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        JFrame frame = new JFrame();
        frame.setContentPane(view.getMainPanel());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Hello world!");
    }
}