package additional;

import display.GamePanel;

import java.util.TimerTask;
/**
 * Class qui gère le systeme de timer du jeu
 *
 */
public class GameTimer extends TimerTask {
    private int gameTimer;
    private boolean chronoMode;
    private GamePanel view;
    /**
     * Constructeur de la class GameTimer
     * @param gameDifficulty	Difficulté choisie
     * @param view	Fenetre ou s'affiche le timer pour pouvoir la modifier
     */
    public GameTimer(AbstractDifficulty gameDifficulty, GamePanel view) {
        chronoMode = (gameDifficulty.getTimerLength() == 0);
        this.view = view;
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
            // throw end of game
        }

        view.changeTimerText(gameTimer);
    }

    public int getGameTimer() {
        return gameTimer;
    }
}
