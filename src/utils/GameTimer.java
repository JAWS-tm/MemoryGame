package utils;

import controller.GameController;

import java.util.TimerTask;
/**
 * Class qui gère le système de timer du jeu
 * Utilisé pour l'affichage du temps restant / temps écoulé
 */
public class GameTimer extends TimerTask {
    private int timer;
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
            timer = 0;
        else
            timer = gameDifficulty.getTimerLength();
    }
    
    /**
     * Fonction appelée à chaque seconde du timer pour modifier l'affichage du temps restant
     */
    @Override
    public void run() {
        if(chronoMode)
            timer++;
        else if (timer > 0){
            timer--;
        }

        controller.getView().changeTimerText(timer);

        if (timer == 0) // Partie perdue
        	controller.endOfGame(GameController.LOSE_END_TYPE);
            
    }

    public int getTimer() {
        return timer;
    }
}
