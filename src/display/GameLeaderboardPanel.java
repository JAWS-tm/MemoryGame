package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;

import additional.AppView;
import controller.App;
import controller.GameLeaderboardController;
import controller.MainController;

public class GameLeaderboardPanel extends AppView {
	private GameLeaderboardController controller;

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


		JPanel wrapSoloDuoInBL = new JPanel();
		wrapSoloDuoInBL.setLayout(new BorderLayout());
		//wrapSoloDuoInBL.setBackground(new Color(0,0,0,0));//////////
		
		
		JPanel leaderboardANDbuttonsGrid = new JPanel();
		leaderboardANDbuttonsGrid.setLayout(new GridLayout(2,1));
		leaderboardANDbuttonsGrid.setBackground(new Color(0,0,0,0));
		
		
		JPanel leaderboardFlowLayout = new JPanel(); 
		leaderboardFlowLayout.setLayout(new FlowLayout());
		leaderboardFlowLayout.setBackground(new Color(0,0,0,0));
		
		JPanel buttonsFlowLayout = new JPanel();
		buttonsFlowLayout.setLayout(new FlowLayout());
		buttonsFlowLayout.setBackground(new Color(0,0,0,0));
		
		
		JLabel leaderboard = new JLabel("Tableau des Scores", JLabel.CENTER);
		leaderboard.setFont(new Font(leaderboard.getFont().getFontName(), Font.ROMAN_BASELINE, 40));
		leaderboard.setForeground(Color.DARK_GRAY);
		
		scoreDuo = new JButton("duo");
		scoreSolo = new JButton("solo");
		
		JPanel videWrapSoloDuoInBL = new JPanel();
		videWrapSoloDuoInBL.setPreferredSize(new Dimension(100,100));
		//videWrapSoloDuoInBL.setBackground(new Color(0,0,0,0));////////////////////////


		JPanel centerDifficultySize = new JPanel(); // premier sous-conteneur
		centerDifficultySize.setLayout(new BorderLayout());
		centerDifficultySize.setBackground(new Color(0,0,0,0));


		JPanel difficultySize = new JPanel(); // deuxieme sous-conteneur
		difficultySize.setBackground(new Color(0,0,0,0));


		GridLayout scoresListLayout = new GridLayout(5,1);
		scoresListLayout.setVgap(20);
		JPanel scoresList = new JPanel(); // troisieme sous-conteneur
		scoresList.setLayout(scoresListLayout);
		scoresList.setBackground(new Color(0,0,0,0));

		HashMap<String, Integer> highScores = App.getHighScores(App.SOLO);

		int i = 1;
		for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
			JLabel number1 = new JLabel(String.valueOf(i++), JLabel.CENTER);
			number1.setForeground(Color.ORANGE);
			number1.setPreferredSize(new Dimension(20,20));

			JPanel nbPanel = new JPanel();
			nbPanel.setBackground(Color.WHITE);
			nbPanel.add(number1);

			JPanel scorePanel = new JPanel();
			scorePanel.setBackground(Color.WHITE);
			scorePanel.setPreferredSize(new Dimension(200,30));
			scorePanel.setLayout(new BorderLayout());
			scorePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			JLabel nameText = new JLabel(entry.getKey(), JLabel.CENTER);
			scorePanel.add(nameText, BorderLayout.WEST);
			nameText.setFont(new Font(nameText.getFont().getFontName(), Font.BOLD, 20));

			JLabel scoreText = new JLabel(String.valueOf(entry.getValue()), JLabel.CENTER);
			scorePanel.add(scoreText, BorderLayout.EAST);
			scoreText.setFont(new Font(scoreText.getFont().getFontName(), Font.BOLD, 20));

			JPanel lineContainer = new JPanel();
			lineContainer.setBackground(new Color(0,0,0,0));
			lineContainer.add(nbPanel);
			lineContainer.add(scorePanel);

			scoresList.add(lineContainer);
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
		difficultySize.add(scoresList);
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
