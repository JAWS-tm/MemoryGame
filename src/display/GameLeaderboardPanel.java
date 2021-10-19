package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import additional.AppView;
import controller.GameLeaderboardController;
import controller.MainController;

public class GameLeaderboardPanel extends AppView {


	private GameLeaderboardController controller;
	
	private JPanel leaderboardFlowLayout, difficulty, difficultySize, centerDifficultySize, wrapSoloDuoInBL;
	private JLabel leaderboard, duo, number1, number2, number3, number4, number5;
	private JButton play, validerPersonnaliser, annulerPersonnaliser;
	
	
	@Override
	protected void generatePanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.YELLOW);
		
		
		////	creation du conteneur des boutons des modes
	
		
		JPanel premierVide = new JPanel();
		premierVide.setPreferredSize(new Dimension(0, 200));
		premierVide.setBackground(new Color(0,0,0,0));
		
		
		
		wrapSoloDuoInBL =new JPanel();
		wrapSoloDuoInBL.setLayout(new BorderLayout());
	
		
		leaderboardFlowLayout = new JPanel(); 
		leaderboardFlowLayout.setLayout(new FlowLayout());
		leaderboardFlowLayout.add(premierVide);
		
		
		leaderboard = new JLabel("LEADERBOARD", JLabel.CENTER);
		leaderboard.setFont(new Font(leaderboard.getFont().getFontName(), Font.ROMAN_BASELINE, 40));
		leaderboard.setForeground(Color.DARK_GRAY);
		leaderboardFlowLayout.add(leaderboard);
		leaderboardFlowLayout.setBackground(new Color(0,0,0,0));
		
		
		//wrapSoloDuoInBL.add(leaderboardFlowLayout, BorderLayout.CENTER);
		this.add(leaderboardFlowLayout, BorderLayout.NORTH);
	
		
		//leaderboard.setPreferredSize(new Dimension(200,50));
		
		JPanel videWrapSoloDuoInBL = new JPanel();
		videWrapSoloDuoInBL.setPreferredSize(new Dimension(100,100));
		wrapSoloDuoInBL.add(videWrapSoloDuoInBL, BorderLayout.NORTH);
	
		
		////creation du conteneur des boutons difficulté
		
		centerDifficultySize = new JPanel(); // premier sous-conteneur
		centerDifficultySize.setLayout(new BorderLayout());
		centerDifficultySize.setBackground(new Color(0,0,0,0));
		
		difficultySize = new JPanel(); // deuxieme sous-conteneur
		difficultySize.setBackground(new Color(0,0,0,0));
		
		difficulty = new JPanel(); // troisieme sous-conteneur
		GridLayout difficulty2 = new GridLayout(5,1);
		difficulty2.setVgap(20);
		difficulty.setLayout(difficulty2);
		difficulty.setBackground(new Color(0,0,0,0));
		
		
		//JPanel number1Panel, number2Panel, number3Panel, number4Panel, number5Panel;
		
		for(int i =1; i<=5; i++) {
			JLabel number1 = new JLabel("1", JLabel.CENTER);
			JPanel number1Panel = new JPanel();
			JPanel test = new JPanel();
			test.setBackground(new Color(0,0,0,0));
			JButton lala = new JButton("test");
			lala.setPreferredSize(new Dimension(200,30));
			number1Panel.setBackground(Color.WHITE);
			number1.setForeground(Color.ORANGE);
			number1.setPreferredSize(new Dimension(20,20));
			number1Panel.add(number1);
			test.add(number1Panel);
			test.add(lala);
			difficulty.add(test);
		}
		
		
		
		//encapsulation
		difficultySize.add(difficulty);
		
		centerDifficultySize.add(difficultySize, BorderLayout.CENTER);
		

		this.add(centerDifficultySize);
		
		// creation du conteneurdu bouton play 
		
		JPanel wrapPlayInBL = new JPanel();
		wrapPlayInBL.setLayout(new BorderLayout());
		
		JPanel videNordWrapPlayInBL = new JPanel();
		videNordWrapPlayInBL.setPreferredSize(new Dimension(100,120));
		
		wrapPlayInBL.add(videNordWrapPlayInBL, BorderLayout.SOUTH);
		
		play = new JButton("Play");
		JPanel playFlowLayout = new JPanel();
		playFlowLayout.setLayout(new FlowLayout());
		play.setPreferredSize(new Dimension(300,60));
		
		playFlowLayout.add(play);
		
		wrapPlayInBL.add(playFlowLayout, BorderLayout.CENTER);
		
		
		
		this.add(wrapPlayInBL, BorderLayout.SOUTH);
		
		
		
		play.addActionListener(controller);
		
	}
	
	
	public GameLeaderboardPanel() {
        super();
        this.controller = new GameLeaderboardController(this);
        generatePanel();
    }
	
	public JButton getPlay() {
		return play;
	}
}
