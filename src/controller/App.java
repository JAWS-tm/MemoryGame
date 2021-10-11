package controller;

import additional.AppView;
import display.MainPanel;

import javax.swing.*;

public class App {
    private static App instance;
    private JFrame appView;

    private App() {
        appView = new JFrame();
        appView.setTitle("Jeu de memory");
        appView.setLocationRelativeTo(null);
        appView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appView.setContentPane(new MainPanel());
        appView.setVisible(true);
    }

    public static App getInstance() {
        if (App.instance == null) {
            App.instance = new App();
        }
        return App.instance;
    }

    public void changeView(AppView newView) {
        appView.setContentPane(newView);
        appView.validate();
    }

}
