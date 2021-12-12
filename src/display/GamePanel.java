package display;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import additional.AppException;
import additional.AppView;
import additional.GameConfig;
import controller.App;
import controller.GameController;
import display.elements.MemoryCard;

/**
 * Class qui étend d'AppView et qui gère l'affichage de la fenetre durant la partie
 *
 */
public class GamePanel extends AppView {
    private GameController controller;
    private ArrayList<MemoryCard> cards = new ArrayList<>();
    private JWindow endGameWindow;
    private JPanel cardContainer;
    private GameConfig config;
    private JLabel timerText, nbPairsFindedText;
    
    /**
     * Constructeur de la class GamePanel
     * @param config	Objet de type GameConfig contenant tous les paramètres de la partie
     * @throws AppException	Exception en cas d'erreur du changement de fenetre
     */
    public GamePanel(GameConfig config) throws AppException {
        super();
        
        this.controller = new GameController(this, config );
        this.config = config;

        generatePanel();
    }
    
    /**
	 * Génération du panel principale lors de l'affichage de la fenetre
	 */
    @Override
    protected void generatePanel() throws AppException {
        this.setLayout(new BorderLayout());

        JPanel northPadding = new JPanel();
        northPadding.setLayout(new BoxLayout(northPadding, BoxLayout.Y_AXIS));

        JPanel blankPadding = new JPanel();
        blankPadding.setPreferredSize(new Dimension(50, 50));
        northPadding.add(blankPadding);
        this.add(northPadding, BorderLayout.NORTH);

        // NORTH
        JPanel textContainer = new JPanel();
        textContainer.setLayout(new GridLayout(3, 1));
        ((GridLayout) textContainer.getLayout()).setHgap(50);

        JLabel bigText = new JLabel("Partie de memory", JLabel.CENTER);
        bigText.setFont(new Font(bigText.getFont().getFontName(), Font.BOLD, 35));
        textContainer.add(bigText);

        JLabel description = new JLabel("Retournez toutes les paires pour gagner !", JLabel.CENTER);
        textContainer.add(description);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 2));
        infoPanel.setPreferredSize(new Dimension(250, 50));
        textContainer.add(wrap(infoPanel));
        
        northPadding.add(textContainer);
        // TEMPS :
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new FlowLayout());

        ImageIcon timerIcon = new ImageIcon("images"+File.separator+"timer.png");
        JLabel timerImg = new JLabel(new ImageIcon(timerIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        timerPanel.add(timerImg);

        timerText = new JLabel("" + config.getDifficulty().getTimerLength(), JLabel.CENTER);
        timerText.setFont(new Font(timerText.getFont().getFontName(), Font.BOLD, timerText.getFont().getSize()));
        timerPanel.add(timerText);
        infoPanel.add(timerPanel);

        // PAIRES :
        JPanel pairsPanel = new JPanel();
        pairsPanel.setLayout(new FlowLayout());

        ImageIcon pairsIcon = new ImageIcon("images"+File.separator+"cards_pair.png");
        JLabel pairsImg = new JLabel(new ImageIcon(pairsIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        pairsPanel.add(pairsImg);

        nbPairsFindedText = new JLabel("0", JLabel.CENTER);
        nbPairsFindedText.setFont(new Font(nbPairsFindedText.getFont().getFontName(), Font.BOLD, nbPairsFindedText.getFont().getSize()));
        pairsPanel.add(nbPairsFindedText);
        infoPanel.add(pairsPanel);


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
    
    /**
     * Change le texte sur la fenetre, correspondant au temps restant donné par le timer
     * @param timer	temps en seconde
     */
    public void changeTimerText(int timer) {
        timerText.setText(String.valueOf(timer));
    }
    
    /**
     * Affiche le nombre de paires effectuées durant la partie
     * @param nb	Nombre de paires
     */
    public void changeNbPairsFindedText(int nb) {
        nbPairsFindedText.setText(String.valueOf(nb));
    }

    /**
     * Génère l'affichage d'une fenetre de fin lorsque la partie est finie
     */
    // TODO: replace by new panel
    private static final int LOSE_END_TYPE = 0;
    private static final int WIN_END_TYPE = 1;
    public void openEndFrame(final int endType) {
        if (endType != LOSE_END_TYPE && endType != WIN_END_TYPE)
            return;

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

        String mainString;
        if (endType == WIN_END_TYPE)
            mainString = "Félicitation !";
        else
            mainString = "Dommage ...";
        JLabel mainText = new JLabel(mainString, JLabel.CENTER);
        mainText.setForeground(Color.WHITE);
        mainText.setFont(new Font(mainText.getFont().getFontName(), Font.BOLD, 20));
        container.add(mainText);
       
        JPanel flowLayoutBtn = new JPanel();
        flowLayoutBtn.setLayout(new FlowLayout());
        flowLayoutBtn.setBackground(Color.black);
        container.add(flowLayoutBtn);
      
        if (config.getMode() == 1) {
            saveScore = new JButton("Enregistrer mon score");
            saveScore.addActionListener(controller);
            flowLayoutBtn.add(saveScore);
        }
        
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
