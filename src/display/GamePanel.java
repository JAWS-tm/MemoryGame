package display;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import additional.AbstractDifficulty;
import additional.AppException;
import additional.AppView;
import controller.GameController;

public class GamePanel extends AppView {
    private GameController controller;
    private ArrayList<MemoryCard> cards = new ArrayList<>();
    private AbstractDifficulty difficulty;
    private JFrame endGameFrame;

    public GamePanel(AbstractDifficulty difficulty) throws AppException {
        super();

        if (difficulty == null) throw new NullPointerException("Game difficulty not instanced");
        this.difficulty = difficulty;
        this.controller = new GameController(this, difficulty);

        generatePanel();
    }

    @Override
    protected void generatePanel() throws AppException {
        this.setLayout(new BorderLayout());

        // NORTH
        JPanel textContainer = new JPanel();
        textContainer.setLayout(new GridLayout(2, 1));
        ((GridLayout) textContainer.getLayout()).setHgap(50);

        JLabel bigText = new JLabel("Partie de memory", JLabel.CENTER);
        bigText.setFont(new Font(bigText.getFont().getFontName(), Font.BOLD, 35));
        textContainer.add(bigText);

        JLabel description = new JLabel("Retournez toutes les paires pour gagner !", JLabel.CENTER);
        textContainer.add(description);


        JPanel northPadding = new JPanel();
        northPadding.setLayout(new GridLayout(2,1));

        northPadding.add(new JPanel());
        northPadding.add(textContainer);
        this.add(northPadding, BorderLayout.NORTH);

        // CENTER
        /*int rowsNb = controller.getRowsNumber();
        int colsNb = controller.getColsNumber();*/
        int rowsNb = difficulty.getRowsNumber();
        int colsNb = difficulty.getColsNumber();

        JPanel cardContainer = new JPanel();
        cardContainer.setLayout(new GridLayout(rowsNb, colsNb));
        ((GridLayout) cardContainer.getLayout()).setHgap(20);
        ((GridLayout) cardContainer.getLayout()).setVgap(20);

        cards = controller.getCardsList();
        for(MemoryCard card : cards) {
            cardContainer.add(card);
        }

        JPanel yCenter = new JPanel();
        yCenter.setLayout(new GridBagLayout());
        yCenter.add(wrap(cardContainer), new GridBagConstraints());

        this.add(yCenter, BorderLayout.CENTER);
    }

    private void openEndFrame() {
        endGameFrame = new JFrame();
        endGameFrame.setVisible(true);
        endGameFrame.setSize(400, 300);
        endGameFrame.setLocationRelativeTo(null);
        endGameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3, 1));

        JLabel
    }
}
