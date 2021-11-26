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
	private JLabel leaderboard;
	private JButton close, scoreSolo, scoreDuo;
	
	
	@Override
	protected void generatePanel() {
		//this.setLayout(new BorderLayout());
		//this.setBackground(Color.YELLOW);
		this.setLayout(new GridLayout(1,3));
		this.setBackground(Color.YELLOW);
		
		JPanel mainVideGauche = new JPanel();
		mainVideGauche.setBackground(Color.BLACK);
		
		JPanel mainVideDroit = new JPanel();
		mainVideDroit.setBackground(Color.BLACK);
		
		JPanel mainBorderLayout = new JPanel();
		mainBorderLayout.setLayout(new BorderLayout());
		mainBorderLayout.setBackground(new Color(0,0,0,0));
		
		JPanel premierVide = new JPanel();
		premierVide.setPreferredSize(new Dimension(0,110));
		premierVide.setBackground(new Color(0,0,0,0));
		
		
		wrapSoloDuoInBL =new JPanel();
		wrapSoloDuoInBL.setLayout(new BorderLayout());
		//wrapSoloDuoInBL.setBackground(new Color(0,0,0,0));//////////
		
		
		JPanel leaderboardANDbuttonsGrid = new JPanel();
		leaderboardANDbuttonsGrid.setLayout(new GridLayout(2,1));
		leaderboardANDbuttonsGrid.setBackground(new Color(0,0,0,0));
		
		
		leaderboardFlowLayout = new JPanel(); 
		leaderboardFlowLayout.setLayout(new FlowLayout());
		leaderboardFlowLayout.setBackground(new Color(0,0,0,0));
		
		JPanel buttonsFlowLayout = new JPanel();
		buttonsFlowLayout.setLayout(new FlowLayout());
		buttonsFlowLayout.setBackground(new Color(0,0,0,0));
		
		
		leaderboard = new JLabel("Tableau des Scores", JLabel.CENTER);
		leaderboard.setFont(new Font(leaderboard.getFont().getFontName(), Font.ROMAN_BASELINE, 40));
		leaderboard.setForeground(Color.DARK_GRAY);
		
		scoreDuo = new JButton("duo");
		scoreSolo = new JButton("solo");
		
		JPanel videWrapSoloDuoInBL = new JPanel();
		videWrapSoloDuoInBL.setPreferredSize(new Dimension(100,100));
		//videWrapSoloDuoInBL.setBackground(new Color(0,0,0,0));////////////////////////
		
		
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
		
		
		for(int i =1; i<=5; i++) {
			JLabel number1 = new JLabel("1", JLabel.CENTER);
			JPanel number1Panel = new JPanel();
			JPanel test = new JPanel();
			test.setBackground(new Color(0,0,0,0));
			JPanel lala = new JPanel();
			lala.setBackground(new Color(0,0,0,0));
			lala.setPreferredSize(new Dimension(200,30));
			number1Panel.setBackground(Color.WHITE);
			number1.setForeground(Color.ORANGE);
			number1.setPreferredSize(new Dimension(20,20));
			number1Panel.add(number1);
			test.add(number1Panel);
			test.add(lala);
			difficulty.add(test);
		}
		
		
		
		JPanel wrapPlayInBL = new JPanel();
		wrapPlayInBL.setLayout(new BorderLayout());
		wrapPlayInBL.setBackground(new Color(0,0,0,0));
		
		
		JPanel videNordWrapPlayInBL = new JPanel();
		videNordWrapPlayInBL.setPreferredSize(new Dimension(100,120));
		videNordWrapPlayInBL.setBackground(new Color(0,0,0,0));
		
		
		close = new JButton("Retour");
		
		JPanel playFlowLayout = new JPanel();
		playFlowLayout.setLayout(new FlowLayout());
		playFlowLayout.setBackground(new Color(0,0,0,0));
		close.setPreferredSize(new Dimension(150,40));
		
		
		// regrouppement des add :
		
		leaderboardFlowLayout.add(premierVide);
		leaderboardFlowLayout.add(leaderboard);
		buttonsFlowLayout.add(scoreSolo);
		buttonsFlowLayout.add(scoreDuo);
		leaderboardANDbuttonsGrid.add(leaderboardFlowLayout);
		leaderboardANDbuttonsGrid.add(buttonsFlowLayout);
		mainBorderLayout.add(leaderboardANDbuttonsGrid, BorderLayout.NORTH);
		wrapSoloDuoInBL.add(videWrapSoloDuoInBL, BorderLayout.NORTH);
		difficultySize.add(difficulty);
		centerDifficultySize.add(difficultySize);
		mainBorderLayout.add(centerDifficultySize, BorderLayout.CENTER);
		wrapPlayInBL.add(videNordWrapPlayInBL, BorderLayout.SOUTH);
		playFlowLayout.add(close);
		wrapPlayInBL.add(playFlowLayout, BorderLayout.CENTER);
		mainBorderLayout.add(wrapPlayInBL, BorderLayout.SOUTH);
		this.add(mainVideGauche);
		this.add(mainBorderLayout);
		this.add(mainVideDroit);
		close.addActionListener(controller);
		scoreSolo.addActionListener(controller);
		scoreDuo.addActionListener(controller);
		
	}
	
	
	public GameLeaderboardPanel() {
        super();
        this.controller = new GameLeaderboardController(this);
        generatePanel();
    }
	
	public JButton getClose() {
		return close;
	}
	
	public JButton getScoreSolo() {
		return scoreSolo;
	}
	
	public JButton getScoreDuo() {
		return scoreSolo;
	}
}
