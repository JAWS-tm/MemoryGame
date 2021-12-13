package display;

import additional.AppView;
import controller.App;
import controller.GameLeaderboardController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Class qui étend de AppView et qui gère l'affichage de la fenetre du tableau des scores
 *
 */
public class GameLeaderboardPanel extends AppView {
	private GameLeaderboardController controller;

	private JButton close, scoreNormalBtn, scoreFacileBtn, scoreDifficileBtn, scoreExtremeBtn;
	private CardLayout leaderboardsStack;
	private JPanel leaderboardViews;
	private final Color yellowBackground = new Color(255,220,20);
	private final Color greyForeground = new Color(245, 245, 245);

	/**
	 * Génération du panel principale lors de l'affichage de la fenetre
	 */
	@Override
	protected void generatePanel() {
		this.setLayout(new GridLayout(1,3));
		this.setBackground(new Color(255, 225, 40));
		
		JPanel mainVideGauche = new JPanel();
		mainVideGauche.setBackground(greyForeground);
		JPanel mainVideDroit = new JPanel();
		mainVideDroit.setBackground(greyForeground);
		
		JPanel mainBorderLayout = new JPanel();
		mainBorderLayout.setLayout(new BorderLayout());
		mainBorderLayout.setBackground(yellowBackground);
		
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
		leaderboard.setFont(new Font(leaderboard.getFont().getFontName(), Font.PLAIN, 40));
		leaderboard.setForeground(Color.DARK_GRAY);

		scoreFacileBtn = new JButton("Facile");
		scoreNormalBtn = new JButton("Normal");
		scoreDifficileBtn = new JButton("Difficile");
		scoreExtremeBtn = new JButton("Extreme");

		JPanel videWrapSoloDuoInBL = new JPanel();
		videWrapSoloDuoInBL.setPreferredSize(new Dimension(100,100));
		//videWrapSoloDuoInBL.setBackground(new Color(0,0,0,0));////////////////////////

		JPanel centerDifficultySize = new JPanel(); // premier sous-conteneur
		centerDifficultySize.setLayout(new BorderLayout());
		centerDifficultySize.setBackground(new Color(0,0,0,0));


		leaderboardViews = new JPanel(); // deuxieme sous-conteneur
		leaderboardViews.setBackground(yellowBackground);
		leaderboardsStack = new CardLayout();
		leaderboardViews.setLayout(leaderboardsStack);


		for (int difficulty = 1 ; difficulty <= 4; difficulty++) {
			GridLayout scoresListLayout = new GridLayout(5,1);
			scoresListLayout.setVgap(20);

			JPanel scoresList = new JPanel(); // troisième sous-conteneur
			scoresList.setLayout(scoresListLayout);
			scoresList.setBackground(new Color(0,0,0,0));


			HashMap<String, Integer> highScores = App.getHighScores(difficulty);
			int i = 1;
			for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
				System.out.println(entry.getKey() + entry.getValue());

				JLabel rankingNb = new JLabel(String.valueOf(i++), JLabel.CENTER);
				rankingNb.setForeground(new Color(255, 160, 28));
				rankingNb.setFont(new Font(rankingNb.getFont().getFontName(), Font.BOLD, 15));
				rankingNb.setPreferredSize(new Dimension(20,19));

				JPanel nbPanel = new JPanel();
				nbPanel.setBackground(new Color(0,0,0,0));
				nbPanel.setPreferredSize(new Dimension(30,30));
				nbPanel.add(rankingNb);
				nbPanel.setBorder(new Border() {
					int radius = 15;
					@Override
					public Insets getBorderInsets(Component c) {
						return new Insets(0, 0, 0, 0);
					}

					@Override
					public boolean isBorderOpaque() { return true; }

					@Override
					public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
						Graphics2D graphics = (Graphics2D) g;
						graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

						Dimension arcs = new Dimension(radius,radius);

						graphics.setColor(yellowBackground);
						graphics.fillRect(0, 0, width, height);//, arcs.width, arcs.height);//paint background
						//paint border
						graphics.setColor(greyForeground);
						graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
					}
				});

				JPanel scorePanel = new JPanel();
				scorePanel.setBackground(Color.WHITE);
				scorePanel.setPreferredSize(new Dimension(200,30));
				scorePanel.setLayout(new BorderLayout());
				scorePanel.setBorder(new Border() {
					int radius = 15;
					@Override
					public Insets getBorderInsets(Component c) {
						return new Insets(0, 13, 1, 10);
					}

					@Override
					public boolean isBorderOpaque() { return true; }

					@Override
					public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
						Graphics2D graphics = (Graphics2D) g;
						graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

						Dimension arcs = new Dimension(radius,radius);

						graphics.setColor(yellowBackground);
						graphics.fillRect(0, 0, width, height);//, arcs.width, arcs.height);//paint background
						//paint border
						graphics.setColor(greyForeground);
						graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
					}
				});

				JLabel nameText = new JLabel(entry.getKey(), JLabel.CENTER);
				scorePanel.add(nameText, BorderLayout.WEST);
				nameText.setFont(new Font(nameText.getFont().getFontName(), Font.BOLD, 18));

				JLabel scoreText = new JLabel(String.valueOf(entry.getValue() + " s "), JLabel.CENTER);
				scoreText.setForeground(new Color(255, 160, 28));
				scoreText.setFont(new Font(scorePanel.getFont().getFontName(), Font.BOLD, 15));
				scorePanel.add(scoreText, BorderLayout.EAST);

				JPanel lineContainer = new JPanel();
				lineContainer.setBackground(new Color(0,0,0,0));
				lineContainer.add(nbPanel);
				lineContainer.add(scorePanel);

				scoresList.add(lineContainer);
			}
			leaderboardViews.add(scoresList, "difficulty_"+difficulty);
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
		buttonsFlowLayout.add(scoreFacileBtn);
		buttonsFlowLayout.add(scoreNormalBtn);
		buttonsFlowLayout.add(scoreDifficileBtn);
		buttonsFlowLayout.add(scoreExtremeBtn);
		leaderboardANDbuttonsGrid.add(leaderboardFlowLayout);
		leaderboardANDbuttonsGrid.add(buttonsFlowLayout);
		mainBorderLayout.add(leaderboardANDbuttonsGrid, BorderLayout.NORTH);
		wrapSoloDuoInBL.add(videWrapSoloDuoInBL, BorderLayout.NORTH);

		centerDifficultySize.add(leaderboardViews);
		mainBorderLayout.add(centerDifficultySize, BorderLayout.CENTER);
		wrapPlayInBL.add(videNordWrapPlayInBL, BorderLayout.SOUTH);
		playFlowLayout.add(close);
		wrapPlayInBL.add(playFlowLayout, BorderLayout.CENTER);
		mainBorderLayout.add(wrapPlayInBL, BorderLayout.SOUTH);
		this.add(mainVideGauche);
		this.add(mainBorderLayout);
		this.add(mainVideDroit);
		close.addActionListener(controller);
		scoreFacileBtn.addActionListener(controller);
		scoreNormalBtn.addActionListener(controller);
		scoreDifficileBtn.addActionListener(controller);
		scoreExtremeBtn.addActionListener(controller);
		
		
	}
	
	
	public GameLeaderboardPanel() {
        super();
        this.controller = new GameLeaderboardController(this);
        generatePanel();
    }
	
	public JButton getClose() {
		return close;
	}
	
	public JButton getScoreNormalBtn() {
		return scoreNormalBtn;
	}
	
	public JButton getScoreFacileBtn() {
		return scoreFacileBtn;
	}
	
	public JButton getScoreDificileBtn() {
		return scoreDifficileBtn;
	}
	
	public JButton getScoreExtremeBtn() {
		return scoreExtremeBtn;
	}

	public CardLayout getLeaderboardsStack() {
		return leaderboardsStack;
	}

	public JPanel getLeaderboardViews() {
		return leaderboardViews;
	}
}
