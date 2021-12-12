package display;

import additional.AppView;
import controller.MainController;

import javax.swing.*;
import java.awt.*;
/**
 * Class qui étend d'AppView et qui gère l'affichage de la fenetre principale lors de l'ouverture du jeu
 *
 */
public class MainPanel extends AppView {
    private MainController controller;

    private JButton launchGameBtn;
    private JButton showHighscoresBtn;
    private JButton quitGameBtn;

    /**
     * Constructeur de la fonction MainPanel
     */
    public MainPanel() {
        super();
        this.controller = new MainController(this);
        generatePanel();
    }

    /**
     * Génération du panel d'affichage principale de la fenetre de lancement du jeu
     */
    protected void generatePanel() {
        this.setLayout(new GridLayout(2, 1));

        JLabel gameName = new JLabel("Memory", JLabel.CENTER);
        gameName.setFont(new Font(gameName.getFont().getFontName(), Font.BOLD, 35));



        JPanel buttonList = new JPanel();
        buttonList.setPreferredSize(new Dimension(250, 150));
        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(25);
        buttonList.setLayout(layout);

        launchGameBtn = new JButton("Jouer");
        launchGameBtn.addActionListener(controller);
        buttonList.add(launchGameBtn);

        showHighscoresBtn = new JButton("Afficher le classement");
        showHighscoresBtn.addActionListener(controller);
        buttonList.add(showHighscoresBtn);

        quitGameBtn = new JButton("Quitter le jeu");
        quitGameBtn.addActionListener(controller);
        buttonList.add(quitGameBtn);


        this.add(gameName);
        this.add(wrap(buttonList));
    }

    // Getters

    public JButton getLaunchGameBtn() {
        return launchGameBtn;
    }

    public JButton getShowHighscoresBtn() {
        return showHighscoresBtn;
    }

    public JButton getQuitGameBtn() {
        return quitGameBtn;
    }
}
