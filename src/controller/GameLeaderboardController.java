package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import display.GameConfigPanel;
import display.GameLeaderboardPanel;
import display.MainPanel;

public class GameLeaderboardController implements ActionListener {
	
	GameLeaderboardPanel view;
	
	public GameLeaderboardController(GameLeaderboardPanel view){
        this.view = view;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
			if(source == view.getClose())
				App.getInstance().changeView(new MainPanel());
	}
}
