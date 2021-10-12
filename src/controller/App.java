package controller;

import additional.AppView;
import display.MainPanel;

import javax.swing.*;

public class App {
    private static App instance;
    private JFrame appFrame;

    private App() {
        appFrame = new JFrame();
        appFrame.setTitle("Jeu de memory");
        appFrame.setLocationRelativeTo(null);
        appFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appFrame.setContentPane(new MainPanel());
        appFrame.setVisible(true);
    }

    public static App getInstance() {
        if (App.instance == null) {
            App.instance = new App();
        }
        return App.instance;
    }

    public void changeView(AppView newView) {
        appFrame.setContentPane(newView);
        appFrame.validate();
    }

    public JFrame getAppFrame() {
        return appFrame;
    }
}
