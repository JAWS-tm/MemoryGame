package display;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import additional.AppView;
import controller.GameController;

public class GamePanel extends AppView {
    private GameController controller;
    private ArrayList<MemoryCard> cards = new ArrayList<>();

    public GamePanel() {
        super();
        this.controller = new GameController(this);
        generatePanel();
    }

    protected void generatePanel() {
        this.setLayout(new BorderLayout());

        JPanel textContainer = new JPanel();
        textContainer.setLayout(new GridLayout(2, 1));
        ((GridLayout) textContainer.getLayout()).setHgap(50);

        JLabel bigText = new JLabel("Partie de memory", JLabel.CENTER);
        bigText.setFont(new Font(bigText.getFont().getFontName(), Font.BOLD, 35));
        textContainer.add(bigText);

        JLabel description = new JLabel("Retournez toutes les paires pour gagner !", JLabel.CENTER);
        textContainer.add(description);

        this.add(textContainer, BorderLayout.NORTH);

        int rowsNb = controller.getRowsNumber();
        int colsNb = controller.getColsNumber();

        JPanel cardContainer = new JPanel();
        cardContainer.setLayout(new GridLayout(rowsNb, colsNb));
        ((GridLayout) cardContainer.getLayout()).setHgap(20);
        ((GridLayout) cardContainer.getLayout()).setVgap(20);

        cards = controller.getCardsList();
        for(MemoryCard card : cards) {
            cardContainer.add(card);
            System.out.print("carte : " + card.getPairID() + "   /   ");
        }
        System.out.println();

        JPanel yCenter = new JPanel();
        yCenter.setLayout(new GridBagLayout());
        yCenter.add(wrap(cardContainer), new GridBagConstraints());

        this.add(yCenter, BorderLayout.CENTER);
    }
}
