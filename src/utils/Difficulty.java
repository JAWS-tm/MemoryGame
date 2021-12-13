package utils;

/**
 * Classe qui contient les différentes difficultés
 */
public class Difficulty{

    /**
     * Difficulté facile
     */
    public static class Easy extends AbstractDifficulty{
        private static final int ROWS = 2;
        private static final int COLS = 3;
        private static final int TIMER = 0;
        private static final int CARD_DELAY = 1500;
        private static final String ICON_DIR = "objets";

        public Easy() {
            super(ROWS, COLS, TIMER, CARD_DELAY, ICON_DIR);
        }
    }

    /**
     * Difficulté normale
     */
    public static class Classic extends AbstractDifficulty{
        private static final int ROWS = 4;
        private static final int COLS = 4;
        private static final int TIMER = 0;
        private static final int CARD_DELAY = 1200;
        private static final String ICON_DIR = "objets";

        public Classic() {
            super(ROWS, COLS, TIMER, CARD_DELAY, ICON_DIR);
        }
    }

    /**
     * Difficulté dure
     */
    public static class Hard extends AbstractDifficulty{
        private static final int ROWS = 4;
        private static final int COLS = 7;
        private static final int TIMER = 0;
        private static final int CARD_DELAY = 800;
        private static final String ICON_DIR = "emojis";

        public Hard() {
            super(ROWS, COLS, TIMER, CARD_DELAY, ICON_DIR);
        }
    }

    /**
     * Difficulté extrême
     */
    public static class Extreme extends AbstractDifficulty{
        private static final int ROWS = 4;
        private static final int COLS = 9;
        private static final int TIMER = 200;
        private static final int CARD_DELAY = 500;
        private static final String ICON_DIR = "emojis";

        public Extreme() {
            super(ROWS, COLS, TIMER, CARD_DELAY, ICON_DIR);
        }
    }

    /**
     * Difficulté personnalisée
     */
    public static class Personalized extends AbstractDifficulty{
        public Personalized(int rows, int cols, int timer, String iconDir) {
            super(rows, cols, timer, 1500, iconDir);
        }
    }


    /**
     * Permet de savoir si la taille de grille est valide
     * @param rows nombre de lignes
     * @param cols nombre de colonnes
     * @return grille valide
     */
    public static boolean isValidGridSize(int rows, int cols) {
        return (rows * cols) % 2 == 0;
    }

}