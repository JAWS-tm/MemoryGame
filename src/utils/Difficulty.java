package utils;

public class Difficulty{

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

    public static class Personalized extends AbstractDifficulty{
        public Personalized(int rows, int cols, int timer, String iconDir) {
            super(rows, cols, timer, 1500, iconDir);
        }
    }


    public static boolean isValidGridSize(int rows, int cols) {
        return (rows * cols) % 2 == 0;
    }
    /*public Difficulty(Difficulty diff){

    }*/

}