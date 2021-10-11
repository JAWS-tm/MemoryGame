package controller;

import display.GameConfigPanel;
import display.GamePanel;
import display.MainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    MainPanel view;

    public MainController(MainPanel view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == view.getLaunchGameBtn())
            App.getInstance().changeView(new GameConfigPanel());
        else if (source == view.getShowHighscoresBtn())
            System.out.println("highscores");
        else if (source == view.getQuitGameBtn())
            System.out.println("quit");
    }
}
