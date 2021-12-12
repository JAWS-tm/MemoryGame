package additional;

import controller.GameController;
import display.GamePanel;

import java.util.TimerTask;
/**
 * Class qui gère le systeme de timer du jeu
 *
 */
public class GameTimer extends TimerTask {
    private int gameTimer;
    private final boolean chronoMode;
    private final GameController controller;

    /**
     * Constructeur de la class GameTimer
     * @param gameDifficulty	Difficulté choisie
     * @param view	Fenetre ou s'affiche le timer pour pouvoir la modifier
     */
    public GameTimer(AbstractDifficulty gameDifficulty, GamePanel view) {
        chronoMode = (gameDifficulty.getTimerLength() == 0);
        this.controller = controller;
        if (chronoMode)
            gameTimer = 0;
        else
            gameTimer = gameDifficulty.getTimerLength();
    }
    
    /**
     * Fonction appellée à chaque seconde du timer pour modifier l'affichage du temps restant
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
            controller.endOfGame(GameController.LOSE_END_TYPE);
    }

    public int getGameTimer() {
        return gameTimer;
    }
}
