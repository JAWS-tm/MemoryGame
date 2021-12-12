package display;

import javax.swing.*;
import java.awt.*;
/**
 * Class qui étend de JButton et qui gère l'affichage de chaque carte dans le jeu, sous forme de bouton
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

    
    public boolean isPairFinded() {
        return pairFinded;
    }

    
    public void setPairFinded(boolean pairFinded) {
        this.pairFinded = pairFinded;
        this.setEnabled(!pairFinded);
    }

    public int getPairID() {
        return pairID;
    }

    public boolean getIconVisible() {
        return iconVisible;
    }
}
