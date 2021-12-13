package controller;

import display.GamePanel;
import org.junit.jupiter.api.Test;
import utils.AbstractDifficulty;
import utils.AppException;
import utils.Difficulty;
import utils.GameConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GameControllerTest {

    @Test
    void getCardsList() {
        for (int i = 0; i < 5; i++) {
            AbstractDifficulty diff = switch (i) {
                case 0 -> new Difficulty.Easy();
                case 1 -> new Difficulty.Classic();
                case 2 -> new Difficulty.Hard();
                case 3 -> new Difficulty.Extreme();
                case 4 -> new Difficulty.Personalized(5, 4, 0, "emojis");
                default -> throw new IllegalStateException("Unexpected value: " + i);
            };

            try {
                GameConfig config = new GameConfig(1, diff, "test", "test2");
                GameController controller = new GameController(new GamePanel(config), config);

                assertEquals(diff.getColsNumber() * diff.getRowsNumber(), controller.getCardsList().size());
            } catch (AppException e) {
                e.printStackTrace();
                fail();
            }
        }
    }
}