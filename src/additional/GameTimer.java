package additional;

import controller.GameController;
import display.GamePanel;

import java.util.TimerTask;
/**
 * Class qui gère le système de timer du jeu
 * Utilisé pour l'affichage du temps restant / temps écoulé
 */
public class GameTimer extends TimerTask {
    private int gameTimer;
    private final boolean chronoMode;
    private final GameController controller;

    /**
     * Constructeur de la class GameTimer
     * @param gameDifficulty Difficulté choisie
     * @param controller Fenêtre ou s'affiche le timer pour pouvoir la modifier
     */
    public GameTimer(AbstractDifficulty gameDifficulty, GameController controller) {
        chronoMode = (gameDifficulty.getTimerLength() == 0);
        this.controller = controller;
        if (chronoMode)
            gameTimer = 0;
        else
            gameTimer = gameDifficulty.getTimerLength();
    }
    
    /**
     * Fonction appelée à chaque seconde du timer pour modifier l'affichage du temps restant
     */
    @Override
    public void run() {
        if(chronoMode)
            gameTimer++;
        else if (gameTimer > 0){
            gameTimer--;
        }

        controller.getView().changeTimerText(gameTimer);

        if (gameTimer == 0) // Partie perdue
        	{
        	controller.endOfGame(GameController.LOSE_END_TYPE);
        	System.err.println("pas okk");
        	}
            
    }

    public int getGameTimer() {
        return gameTimer;
    }
}
