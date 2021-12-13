package display.elements;

import javax.swing.*;
import java.awt.*;
/**
 * Class qui étend de {@link JButton} et qui crée une carte de jeu
 * 
 */
public class MemoryCard extends JButton {
    private ImageIcon memoryIcon;
    private int pairID;
    private boolean iconVisible = false;
    private boolean pairFinded = false;
    private static ImageIcon backCard = new ImageIcon("images/back_card.png");
    
    /**
     * Constructeur de la fonction MemoryCard
     * @param icon	Image à afficher sur la carte
     * @param pairID	Identifiant de la carte qui va avec sa paire
     */
    public MemoryCard(ImageIcon icon, int pairID) {
        super(backCard);

        this.memoryIcon = new ImageIcon(icon.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
        this.pairID = pairID;

        setPreferredSize(new Dimension(100,100));
    }

    /**
     * Fonction qui fait retourner la carte et affiche le dos de la carte
     */
    public void flipCard() {
        if (iconVisible) {
            setIcon(backCard);
        } else {
            setIcon(memoryIcon);
        }

        iconVisible = !iconVisible;
    }


    /**
     * Permet de savoir si la paire à laquelle la carte est associée a été trouvée
     * @return paire trouvée
     */
    public boolean isPairFinded() {
        return pairFinded;
    }

    /**
     * Permet de définir la carte comme trouvée
     */
    public void setPairFinded() {
        this.pairFinded = true;
        this.setEnabled(false);
    }

    public int getPairID() {
        return pairID;
    }

    public boolean getIconVisible() {
        return iconVisible;
    }
}
