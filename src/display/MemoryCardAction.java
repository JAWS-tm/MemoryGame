package display;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MemoryCardAction extends AbstractAction {
    private MemoryCard card;

    public MemoryCardAction(MemoryCard parent) {
        card = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == card && !card.getIconVisible()) {
            card.flipCard();
        }

    }
}
