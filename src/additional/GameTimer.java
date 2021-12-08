package additional;

import display.GamePanel;

import java.util.TimerTask;

public class GameTimer extends TimerTask {
    private int gameTimer;
    private boolean chronoMode;
    private GamePanel view;

    public GameTimer(AbstractDifficulty gameDifficulty, GamePanel view) {
        chronoMode = (gameDifficulty.getTimerLength() == 0);
        this.view = view;
        if (chronoMode)
            gameTimer = 0;
        else
            gameTimer = gameDifficulty.getTimerLength();
    }

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
