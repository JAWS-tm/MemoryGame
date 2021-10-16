package additional;

public abstract class AbstractDifficulty {

    private int rowsNumber;
    private int colsNumber;
    private String iconDir;
    private int timer = 0; // 0 for no timer

    public AbstractDifficulty(int rows, int cols, int timer, String iconDir) {
        this.rowsNumber = rows;
        this.colsNumber = cols;
        this.timer = timer;
        this.iconDir = iconDir;
    }

    /*public boolean isValidGridSize(int rows, int cols) {
        return (rows * cols) % 2 == 0;
    }

    public void setGridSize(int rowsNb, int colsNb) {
        if (isValidGridSize(rowsNb, colsNb)){
            this.rowsNumber = rowsNb;
            this.colsNumber = colsNb;
        }
    }*/


    public String getIconDir() {
        return iconDir;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColsNumber() {
        return colsNumber;
    }
}
