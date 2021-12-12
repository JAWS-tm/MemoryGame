package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import display.GameConfigPanel;
import display.GameLeaderboardPanel;
import display.MainPanel;
/**
 * Class qui gère le fonctionnement de l'afficheur des scores
 *
 */
public class GameLeaderboardController implements ActionListener {
	
	GameLeaderboardPanel view;
	/**
	 * Constructeur de la class GameLeaderboardController
	 * @param view	Fentre de l'afficheur des scores
	 */
	public GameLeaderboardController(GameLeaderboardPanel view){
        this.view = view;
    }
	
	/**
	 * Gère les actionsListener
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == view.getClose())
			App.getInstance().changeView(new MainPanel());

		if (source == view.getScoreFacileBtn())
			view.getLeaderboardsStack().show(view.getLeaderboardViews(), "difficulty_"+1);
		else if (source == view.getScoreNormalBtn())
			view.getLeaderboardsStack().show(view.getLeaderboardViews(), "difficulty_"+2);
		else if (source == view.getScoreDificileBtn())
			view.getLeaderboardsStack().show(view.getLeaderboardViews(), "difficulty_"+3);
		else if (source == view.getScoreExtremeBtn())
			view.getLeaderboardsStack().show(view.getLeaderboardViews(), "difficulty_"+4);

	}

}
