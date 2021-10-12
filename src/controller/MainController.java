package controller;

import display.GameConfigPanel;
import display.MainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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
            System.out.println("highscores");
        else if (source == view.getQuitGameBtn())
            appInstance.getAppFrame().dispatchEvent(new WindowEvent(appInstance.getAppFrame(), WindowEvent.WINDOW_CLOSING));
    }
}
