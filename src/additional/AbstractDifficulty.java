package additional;

public abstract class AbstractDifficulty {

    private int rowsNumber;
    private int colsNumber;
    private String iconDir;
    private int timer = 0; // 0 for no timer
    private int delay_card; // delais avant le retournement de la carte

    private int pairsNb;

    public AbstractDifficulty(int rows, int cols, int timer, int delay_card, String iconDir) {
        this.rowsNumber = rows;
        this.colsNumber = cols;
        this.timer = timer;
        this.delay_card = delay_card;
        this.iconDir = iconDir;

        pairsNb = (rows * cols) / 2;
    }

    public int getPairsNb() {
        return pairsNb;
    }

    public String getIconDir() {
        return iconDir;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColsNumber() {
        return colsNumber;
    }
    
    public int getDelayCard() {
    	return delay_card;
    }
}
