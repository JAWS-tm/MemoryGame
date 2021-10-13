package display;

import javax.swing.*;
import java.awt.*;

public class MemoryCard extends JButton {
    private ImageIcon icon;
    private boolean iconVisible = false;
    private static ImageIcon backCard = new ImageIcon("images/back_card.png");

    public MemoryCard(ImageIcon icon) {
        super(backCard);

        this.icon = new ImageIcon(icon.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));

        setPreferredSize(new Dimension(100,100));
        addActionListener(new MemoryCardAction(this));
    }

    public void flipCard() {
        if (iconVisible) {
            setIcon(backCard);
        } else {
            setIcon(icon);

            /*Timer timer = new Timer();
            timer.
            timer.scheduleAtFixedRate(new TimerTask() {
                int i = 20;

                public void run() {

                    jLabel.setText("Time left: " + i);
                    i--;

                    if (i < 0) {
                        timer.cancel();
                        jLabel.setText("Time Over");
                    }
                }
            }, 0, 1000);*/
        }

        iconVisible = !iconVisible;
    }

    public boolean getIconVisible() {
        return iconVisible;
    }
}
