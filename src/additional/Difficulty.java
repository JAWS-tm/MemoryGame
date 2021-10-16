package additional;

public class Difficulty{

    public static class Easy extends AbstractDifficulty{
        private static final int ROWS = 4;
        private static final int COLS = 3;
        private static final int TIMER = 0;
        private static final String ICON_DIR = "easy";

        public Easy() {
            super(ROWS, COLS, TIMER, ICON_DIR);
        }
    }

    /*public Difficulty(Difficulty diff){

    }*/

}
/*
abstract class AbstractDifficulty {
    private int rowsNumber;
    private int colsNumber;


    public boolean isValidGridSize(int rows, int cols) {
        return (rows * cols) % 2 == 0;
    }

    public void setGridSize(int rowsNb, int colsNb) {
        if (isValidGridSize(rowsNb, colsNb)){
            this.rowsNumber = rowsNumber;
            this.colsNumber = colsNumber;
        }
    }
    public void setColsNumber(int colsNumber) {
        if (isValidGridSize(this.rowsNumber, colsNumber))
            this.colsNumber = colsNumber;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColsNumber() {
        return colsNumber;
    }
}
*/