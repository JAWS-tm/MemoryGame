package display;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import additional.AbstractDifficulty;
import additional.AppException;
import additional.AppView;
import controller.App;
import controller.GameController;

public class GamePanel extends AppView {
    private GameController controller;
    private ArrayList<MemoryCard> cards = new ArrayList<>();
    private AbstractDifficulty difficulty;
    private JWindow endGameWindow;
    private JPanel cardContainer;

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
        int rowsNb = difficulty.getRowsNumber();
        int colsNb = difficulty.getColsNumber();

        cardContainer = new JPanel();
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

    public void openEndFrame() {
        cardContainer.setVisible(false);
        
        endGameWindow = new JWindow(App.getInstance().getFrame());
        endGameWindow.setVisible(true);
        endGameWindow.setSize(200, 200);
        endGameWindow.setLocationRelativeTo(null);
        //endGameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        endGameWindow.requestFocus();
        endGameWindow.setBackground(Color.BLACK);

        JPanel container = new JPanel();
        endGameWindow.setContentPane(container);
        container.setLayout(new GridLayout(3, 1));

        JPanel texts = new JPanel();
        texts.setLayout(new GridLayout(2, 1));
        texts.setBackground(Color.black);
        container.add(texts);

        JLabel mainText = new JLabel("Félicitation !", JLabel.CENTER);
        mainText.setForeground(Color.WHITE);
        mainText.setFont(new Font(mainText.getFont().getFontName(), Font.BOLD, 20));
        texts.add(mainText);
        JLabel instruction = new JLabel("Entrez votre nom ci-dessous", JLabel.CENTER);
        instruction.setForeground(Color.WHITE);
        texts.add(instruction);


        JTextField playerName = new JTextField();
        playerName.setPreferredSize(new Dimension(180, 30));
        container.add(wrap(playerName));

        JButton validate = new JButton("Valider");
        container.add(validate);
    }
}
