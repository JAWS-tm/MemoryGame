package controller;

import additional.*;
import display.GamePanel;
import display.MainPanel;
import display.elements.MemoryCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Timer;
/**
 * Class qui gère le fonctionnement de la fenetre de jeu
 *
 */
public class GameController implements ActionListener {
    private GamePanel view;

    private ArrayList<MemoryCard> cards = new ArrayList<>();
    private MemoryCard selectedPair = null;
    private Timer timer;
    private GameTimer gameTimer;
    private boolean timerIsStarted = false;
    private int nbPairFinded = 0;
    private GameConfig config;
    
    /**
     * Constructeur de la class GameController
     * @param view	Fenetre de jeu
     * @param config	Objet de type GameConfig qui contient tous les parametres de jeu
     */
    public GameController(GamePanel view, GameConfig config) {
        this.timer = new Timer();
        this.gameTimer = new GameTimer(config.getDifficulty(), this);
        this.view = view;
        this.config = config;

        this.timer.scheduleAtFixedRate(gameTimer, 1000, 1000);
    }
    
    /**
     * A REMPLIR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @return
     * @throws AppException
     */
    public ArrayList<MemoryCard> getCardsList() throws AppException {
        if (!cards.isEmpty())
            return cards;

        ArrayList<String> iconsPaths = getIconsPathList();
        for (int i = 0; i < 2; i++) {
            int pairID = 0;
            for (String str : iconsPaths) {
                MemoryCard card = new MemoryCard(new ImageIcon(str), pairID);
                card.addActionListener(this);
                cards.add(card);
                pairID++;

                Collections.shuffle(cards);
            }
            Collections.shuffle(cards);
        }

        return cards;
    }

    /**
     * Obtenir la liste des chemins des icons
     * @return Une {@code ArrayList<String>} contenant les chemins de chaque icon.
     *          Retourne {@code null} si une erreur s'est produite
     */
    private ArrayList<String> getIconsPathList() throws AppException {
        ArrayList<String> iconsPathList;

        try {
            // multi-platform path
            String localPath = "images" + File.separator + "icons" + File.separator + config.getDifficulty().getIconDir();
            Path path = Paths.get(System.getProperty("user.dir"), localPath);

            if (!Files.exists(path))
                throw new NullPointerException("Invalid path");

            File iconsDir = new File(path.toString());
            File[] iconsList = iconsDir.listFiles((dir, name) -> name.matches("icon_[0-9]+.png"));
            if (iconsList == null)
                throw new NullPointerException("Empty directory");

            iconsPathList = new ArrayList<>();
            int imagesToLoad = config.getDifficulty().getPairsNb();
            for (File file : iconsList) {
                if (imagesToLoad == 0)
                    break;
                // Add local path
                iconsPathList.add(file.toString());
                imagesToLoad--;
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(view, "<html>Une erreur a eu lieu lors du chargement des icons, veuillez réessayer.<br/><b>Message : </b>" + e.getMessage() + "</html>", "Erreur de chargement", JOptionPane.ERROR_MESSAGE);
            throw new AppException(AppException.Type.VIEW_LOADING_FAILED);
        }
        return iconsPathList;
    }

    private void setNewPairFunded(MemoryCard pair1, MemoryCard pair2){
        pair1.setPairFinded(true);
        pair2.setPairFinded(true);

        nbPairFinded++;
        view.changeNbPairsFindedText(nbPairFinded);
        if (nbPairFinded == config.getDifficulty().getPairsNb())
            this.endOfGame(WIN_END_TYPE);

    }

    public final static int LOSE_END_TYPE = 0;
    public final static int WIN_END_TYPE = 1;
    public void endOfGame(final int endType) {
        if (endType != LOSE_END_TYPE && endType != WIN_END_TYPE)
            return;

        timer.cancel();

        view.openEndFrame(endType);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof MemoryCard card && !timerIsStarted && cards.contains(card) && !card.getIconVisible() && !card.isPairFinded()) {
            card.flipCard();
            if (selectedPair == null) {
                selectedPair = card;
            } else {
                if (Objects.equals(selectedPair.getPairID(), card.getPairID())) {
                    // Bonne paire
                    setNewPairFunded(card, selectedPair);
                    selectedPair = null;
                } else {
                    // Mauvaise paire
                    timerIsStarted = true;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            timerIsStarted = false;

                            card.flipCard();
                            selectedPair.flipCard();

                            selectedPair = null;
                        }
                    }, config.getDifficulty().getDelayCard());
                }
            }
        }

        // Sur écran de fin :
        if(source == view.getSaveScore()) {
            int difficultyNb = 0;
            if (config.getDifficulty() instanceof Difficulty.Easy)
                difficultyNb = 1;
            else if (config.getDifficulty() instanceof Difficulty.Classic)
                difficultyNb = 2;
            else if (config.getDifficulty() instanceof Difficulty.Hard)
                difficultyNb = 3;
            else if (config.getDifficulty() instanceof Difficulty.Extreme)
                difficultyNb = 4;

            int score;
            if (config.getDifficulty().getTimerLength() != 0)
                score = config.getDifficulty().getTimerLength() - gameTimer.getGameTimer(); // Temps mit
            else
                score = gameTimer.getGameTimer();

            App.saveNewScore(config.getPlayerName1(), score, difficultyNb);

            view.getEndGameWindow().setVisible(false);
            App.getInstance().changeView(new MainPanel());
        }
        
        if(source == view.getQuitter()) {
        	view.getEndGameWindow().setVisible(false);
        	App.getInstance().changeView(new MainPanel());
        }
    }

    /**
     * @return Renvoie la vue (Utilisé pour le timer)
     */
    public GamePanel getView() {
        return view;
    }
}
