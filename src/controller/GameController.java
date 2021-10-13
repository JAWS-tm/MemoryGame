package controller;

import display.GamePanel;
import display.MemoryCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Timer;

public class GameController implements ActionListener {
    private GamePanel view;

    private ArrayList<String> iconsPathList = new ArrayList<>();

    private ArrayList<MemoryCard> cards = new ArrayList<>();
    private MemoryCard selectedPair = null;
    private Timer pairTimer;
    private boolean timerIsStarted = false;
    private int nbPairFinded = 0;

    private int rowsNumber = 3;
    private int colsNumber = 4;

    public GameController(GamePanel view) {
        this.view = view;
        this.pairTimer = new Timer();
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }
    public void setColsNumber(int colsNumber) {
        this.colsNumber = colsNumber;
    }
    public int getRowsNumber() {
        return rowsNumber;
    }
    public int getColsNumber() {
        return colsNumber;
    }

    public ArrayList<MemoryCard> getCardsList() {
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
            }
            Collections.shuffle(cards);
        }
        Collections.shuffle(cards);

        return cards;
    }

    /**
     * Obtenir la liste des chemins des icons
     * @return Une {@code ArrayList<String>} contenant les chemins de chaque icon.
     *          Retourne {@code null} si une erreur s'est produite
     */
    private ArrayList<String> getIconsPathList() {
        if (iconsPathList.isEmpty()) {
            loadIconsPath();
        }
        return iconsPathList;
    }

    /**
     * Recherche tous les icons du dossier icons et les stockent dans {@code iconsPathList} contenant leurs chemins
     *
     */
    public void loadIconsPath() {
        try {
            String localPath = "images" + File.separator + "icons";
            Path path = Paths.get(System.getProperty("user.dir"), localPath);

            if (!Files.exists(path))
                throw new NullPointerException("Invalid path");

            File iconsDir = new File(path.toString());

            File[] iconsList = iconsDir.listFiles((dir, name) -> name.matches("icon_[0-9]+.png"));
            if (iconsList == null)
                throw new NullPointerException("Empty directory");

            iconsPathList = new ArrayList<>();
            for (File file : iconsList) {
                // Add local path
                iconsPathList.add(file.toString());
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(view, "<html>Une erreur a eu lieu lors du chargement des icons, veuillez réessayer.<br/><b>Message : </b>" + e.getMessage() + "</html>", "Erreur de chargement", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setNewPairFinded(MemoryCard pair1, MemoryCard pair2){
        pair1.setPairFinded(true);
        pair2.setPairFinded(true);

        nbPairFinded++;
        if (nbPairFinded == (rowsNumber * colsNumber)/2){
            JOptionPane.showMessageDialog(view, "Félicitation tu as gagné !", "Fin de partie", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof MemoryCard card && !timerIsStarted && cards.contains(card) && !card.getIconVisible() && !card.isPairFinded()) {
            card.flipCard();
            if (selectedPair == null){
                selectedPair = card;
            }else{
                if (Objects.equals(selectedPair.getPairID(), card.getPairID())){
                    // Bonne paire
                    setNewPairFinded(card, selectedPair);
                    selectedPair = null;
                }else {
                    // Mauvaise paire
                    timerIsStarted = true;
                    pairTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            timerIsStarted = false;

                            card.flipCard();
                            selectedPair.flipCard();

                            selectedPair = null;
                        }
                    }, 1500);
                }
            }

        }
    }
}
