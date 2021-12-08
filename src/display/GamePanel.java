package display;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import additional.AbstractDifficulty;
import additional.AppException;
import additional.AppView;
import additional.GameConfig;
import controller.App;
import controller.GameController;

public class GamePanel extends AppView {
    private GameController controller;
    private ArrayList<MemoryCard> cards = new ArrayList<>();
    private JWindow endGameWindow;
    private JPanel cardContainer;
    private GameConfig config;

    public GamePanel(GameConfig config) throws AppException {
        super();
        
        this.controller = new GameController(this, config );
        this.config = config;

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
        int rowsNb = config.getDifficulty().getRowsNumber();
        int colsNb = config.getDifficulty().getColsNumber();

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

    
    private JButton saveScore, quitter;
    
    public void openEndFrame() {
        cardContainer.setVisible(false);
        
        endGameWindow = new JWindow(App.getInstance().getFrame());
        endGameWindow.setVisible(true);
        endGameWindow.setSize(250, 250);
        endGameWindow.setLocationRelativeTo(null);
        Point location = endGameWindow.getLocation();
        location.y += 100;
        endGameWindow.setLocation(location);
        //endGameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        endGameWindow.requestFocus();
        endGameWindow.setBackground(Color.BLACK);

        JPanel container = new JPanel();
        endGameWindow.setContentPane(container);
        container.setLayout(new GridLayout(2, 1));
        container.setBackground(Color.black);
       

        JLabel mainText = new JLabel("Félicitation !", JLabel.CENTER);
        mainText.setForeground(Color.WHITE);
        mainText.setFont(new Font(mainText.getFont().getFontName(), Font.BOLD, 20));
        container.add(mainText);
       
        JPanel flowLayoutBtn = new JPanel();
        flowLayoutBtn.setLayout(new FlowLayout());
        flowLayoutBtn.setBackground(Color.black);
        container.add(flowLayoutBtn);
      
        
        saveScore = new JButton("Enregistrer mon score");
        saveScore.addActionListener(controller);
        flowLayoutBtn.add(saveScore);
        
        quitter = new JButton("Retour au menu principal");
        quitter.addActionListener(controller);
        flowLayoutBtn.add(quitter);
    }


	public JButton getSaveScore() {
		return saveScore;
	}

	public JButton getQuitter() {
		return quitter;
	}
    
    public JWindow getEndGameWindow() {
    	return endGameWindow;
    }
}
