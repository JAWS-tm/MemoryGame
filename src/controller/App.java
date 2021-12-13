package controller;

import additional.AppView;
import display.MainPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

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

	/**
	 * Fonction d'enregistrement d'un nouveau score
	 * @param playerName	Nom du joueur
	 * @param score	Score réalisé par le joueur
	 * @param difficulty Difficulté de la partie (pour trier les scores par difficulté)
	 */
    public static void saveNewScore(String playerName, int score, int difficulty) {
        if (difficulty < 1 || difficulty > 4)
            return;

        try {
            if (!Files.exists(Paths.get("data")))
                Files.createDirectories(Paths.get("data"));

            // TODO: remplacer ancien score
            BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.get("data", "score").toFile(), true));
            bw.write(difficulty + "|" + score + "|" + playerName);
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Fonction de lecture du fichier où sont enregistrer les scores des joueurs
     * @param difficulty Difficulté pour afficher le classement de celle-ci
     * @return	La liste des scores des joueurs
     */

    public static HashMap<String, Integer> getHighScores(int difficulty) {
        if (difficulty < 1 || difficulty > 4)
            return new HashMap<>();

        try {
            HashMap<String, Integer> playersScores = new HashMap<>();

            if (!Files.exists(Paths.get("data")) || !Files.exists(Paths.get("data", "score")))
                return playersScores;

            BufferedReader br = new BufferedReader(new FileReader(Paths.get("data", "score").toFile()));

            String line;
            while( (line = br.readLine()) != null) {
                String[] splitStr = line.split("\\|");

                if (Integer.parseInt(splitStr[0]) == difficulty
                        && (!playersScores.containsKey(splitStr[2])
                            || (playersScores.containsKey(splitStr[2])
                            && playersScores.get(splitStr[2]) > Integer.parseInt(splitStr[1]))))
                    // On remplace si la nouvelle est plus petite que celle déjà stocké (si la clé existe déjà [soit le nom du joueur])
                    // Ou on ajoute si la clé n'existe pas
                    playersScores.put(splitStr[2], Integer.parseInt(splitStr[1]));
            }
            br.close();

            HashMap<String, Integer> returnHash = new LinkedHashMap<>();
            int i = 0;
            for (Map.Entry<String, Integer> entry : sortAscending(playersScores).entrySet()){
                if (i < 5) // On garde les 5 meilleurs scores
                    returnHash.put(entry.getKey(), entry.getValue());
                i++;
            }

            return returnHash;
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        return new HashMap<>();
    }

    private static HashMap<String, Integer> sortAscending(Map<String, Integer> unsortedMap)
    {
        LinkedList<Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        // Sorting the list based on values
        list.sort(Entry.comparingByValue());

        HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


}
