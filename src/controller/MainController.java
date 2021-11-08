package controller;

import display.GameConfigPanel;
import display.GameLeaderboardPanel;
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
        App appInstance = App.getInstance();

        if (source == view.getLaunchGameBtn())
            appInstance.changeView(new GameConfigPanel());
        else if (source == view.getShowHighscoresBtn())
        	appInstance.changeView(new GameLeaderboardPanel());
        else if (source == view.getQuitGameBtn())
            App.getInstance().closeApp();
    }
}
