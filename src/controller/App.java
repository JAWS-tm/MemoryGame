package controller;

import additional.AbstractDifficulty;
import additional.AppView;
import display.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
	/**
	 * 
	 *
	 */
public class App {
    private static App instance;
    private JFrame appFrame;
    /**
     * Constructeur de la class App
     * Création de la premiere fenetre affichée lors du lancement du jeu
     */
    private App() {
        appFrame = new JFrame();
        appFrame.setTitle("Jeu de memory");
        appFrame.setLocationRelativeTo(null);
        appFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appFrame.setContentPane(new MainPanel());
        appFrame.setVisible(true);
    }
    
    /**
     *  
     * @return Retourne la fenetre
     */
    public static App getInstance() {
        if (App.instance == null) {
            App.instance = new App();
        }
        return App.instance;
    }
    
    /**
     * Permet de changer de fenetre
     * @param newView	fenetre suivante à afficher
     */
    public void changeView(AppView newView) {
        appFrame.setContentPane(newView);
        appFrame.validate();
    }
    /**
     * Fermeture du jeu
     */
    public void closeApp() {
        appFrame.dispatchEvent(new WindowEvent(appFrame, WindowEvent.WINDOW_CLOSING));
    }

    public JFrame getFrame() {
        return appFrame;
    }

    public static final int SOLO = 1;
    public static final int DUO = 2;
	/**
	 * Fonction d'enregistrement d'un nouveau score
	 * @param playerName	Nom du joueur
	 * @param score	Score réalisé par le joueur
	 * @param gameMode	Mode dans lequel il se trouvait
	 */
    public static void saveNewScore(String playerName, int score, int gameMode) {
        if (gameMode != SOLO && gameMode != DUO)
            return;

        try {
            if (!Files.exists(Paths.get("data")))
                Files.createDirectories(Paths.get("data"));

            BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.get("data", "score_" + gameMode).toFile(), true));
            bw.write(score + "/" + playerName);
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Fonction de lecture du fichier où sont enregistrer les scores des joueurs
     * @param difficulty	Difficulté pour afficher le classement de celle-ci
     * @return	La liste des scores des joueurs
     */
    public static HashMap<String, Integer> getHighScores(AbstractDifficulty difficulty) {
        try {
            HashMap<String, Integer> playersScores = new HashMap<>();
            if (!Files.exists(Paths.get("data")) || !Files.exists(Paths.get("data", "score")))
                return playersScores;

            BufferedReader br = new BufferedReader(new FileReader(Paths.get("data", "score_").toFile()));

            String line;
            int nbScores = 0;
            while( (line = br.readLine()) != null && nbScores < 5){
                String[] splitStr = line.split("/");
                System.out.println(nbScores + " //// " + Arrays.toString(splitStr));

                playersScores.put(splitStr[1], Integer.parseInt(splitStr[0]));

                nbScores++;
            }

            br.close();

            return playersScores;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}
