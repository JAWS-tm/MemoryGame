package utils;
/**
 * Class abstraite qui permet de mettre en place plusieurs difficultés
 */
public abstract class AbstractDifficulty {

    private int rowsNumber;
    private int colsNumber;
    private String iconDir;
    private int timerLength = 0; // 0 for no timer
    private int delay_card; // delais avant le retournement de la carte

    private int pairsNb;
    
    /**
     * Constructeur de la classe Difficulty
     * @param rows	nombre de lignes
     * @param cols	nombre de colones
     * @param timerLength	temps maximum avant la fin de la partie, valeur 0 pour aucun timer
     * @param delay_card	temps de visibilté des cartes une fois cliquées avant qu'elles se retournent
     * @param iconDir	répertoire des images voulues
     */
    public AbstractDifficulty(int rows, int cols, int timerLength, int delay_card, String iconDir) {
        this.rowsNumber = rows;
        this.colsNumber = cols;
        this.timerLength = timerLength;
        this.delay_card = delay_card;
        this.iconDir = iconDir;

        pairsNb = (rows * cols) / 2;
    }

    // Getters
    public int getTimerLength() {
        return timerLength;
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
