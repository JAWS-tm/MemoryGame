package display;

import javax.swing.*;
import java.awt.*;

public class MemoryCard extends JButton {
    private ImageIcon memoryIcon;
    private int pairID;
    private boolean iconVisible = false;
    private static ImageIcon backCard = new ImageIcon("images/back_card.png");

    public MemoryCard(ImageIcon icon, int pairID) {
        super(backCard);

        this.memoryIcon = new ImageIcon(icon.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
        this.pairID = pairID;

        setPreferredSize(new Dimension(100,100));
    }

    public void flipCard() {
        if (iconVisible) {
            setIcon(backCard);
        } else {
            setIcon(memoryIcon);
        }

        iconVisible = !iconVisible;
    }

    public int getPairID() {
        return pairID;
    }

    public boolean getIconVisible() {
        return iconVisible;
    }
}
