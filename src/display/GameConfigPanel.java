package display;

import additional.AppView;
import controller.GameConfigController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
 * Class qui étend d'AppView et qui gère l'affichage de la fenetre de configuration de la partie
 *
 */
public class GameConfigPanel extends AppView {
	private GameConfigController controller;

	private JPanel cardsContainer, player2Pnl;
	private JSpinner widthInput, heightInput, timerInput;
	private JToggleButton soloModeBtn, duoModeBtn;
	private JToggleButton easyDifficultyBtn, classicDifficultyBtn, hardDifficultyBtn, extremeDifficultyBtn, personalizedDifficultyBtn;
	private JComboBox <String> stylePicker;
	private JButton validatePersonalised, launchGameBtn, returnBtn;
	private JCheckBox timerActivated;

	private JTextField playerName1, playerName2;

	/**
	 * Constructeur de la class GameConfigPanel
	 */
	public GameConfigPanel() {
		super();
		this.controller = new GameConfigController(this);

		generatePanel();
	}
	
	/**
	 * Génération du panel principal qui s'affiche sur la fenetre
	 */
	@Override
	protected void generatePanel() {
		this.setLayout(new BorderLayout());//new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel header = new JPanel();
		header.setBorder(new EmptyBorder(50, 0, 0, 0));
		this.add(header, BorderLayout.NORTH);

		JLabel menuName = new JLabel("Configuration");
		menuName.setFont(new Font(menuName.getFont().getFontName(), Font.BOLD, 35));
		header.add(menuName);

		JPanel centerCards = new JPanel();
		centerCards.setLayout(new GridBagLayout());
		this.add(centerCards, BorderLayout.CENTER);

		cardsContainer = new JPanel();
		cardsContainer.setLayout(new CardLayout());
		cardsContainer.add(modeCard());
		cardsContainer.add(difficultyCard(), "difficulty_card");
		cardsContainer.add(nameCard(), "name_card");
		cardsContainer.add(personalisedDifficultyConfigCard(), "personalised_card");
		centerCards.add(cardsContainer, new GridBagConstraints());


		JPanel returnPnl = new JPanel();
		returnPnl.setBorder(new EmptyBorder(0, 0, 50, 0));

		returnBtn = new JButton("Retour");
		returnBtn.setPreferredSize(new Dimension(300, 50));
		returnBtn.addActionListener(controller);
		returnPnl.add(returnBtn);
		this.add(returnPnl, BorderLayout.SOUTH);
	}

	/**
	 * Génère le panel pour choisir le mode de jeu (solo / duo)
	 * @return le panel généré
	 */
	private JPanel modeCard() {
		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBorder(new EmptyBorder(100, 0, 100, 0)); // tricky Y centering (not responsive)


		JLabel cardText = new JLabel("Choisissez le mode :");
		cardText.setAlignmentX(CENTER_ALIGNMENT);
		cardText.setFont(new Font(cardText.getFont().getFontName(), Font.BOLD, 20));
		cardText.setBorder(new EmptyBorder(0, 0, 20, 0));
		card.add(cardText);

		JPanel buttonsPnl = new JPanel();
		buttonsPnl.setBorder(new TitledBorder("Mode"));
		buttonsPnl.setLayout(new GridLayout(1, 2));
		((GridLayout) buttonsPnl.getLayout()).setHgap(20);
		card.add(buttonsPnl);

		soloModeBtn = new JToggleButton("Solo");
		duoModeBtn = new JToggleButton("Duo");
		soloModeBtn.setPreferredSize(new Dimension(100, 50));
		duoModeBtn.setPreferredSize(new Dimension(100, 50));
		soloModeBtn.addActionListener(controller);
		duoModeBtn.addActionListener(controller);

		buttonsPnl.add(wrap(soloModeBtn));
		buttonsPnl.add(wrap(duoModeBtn));

		return wrap(card);
	}

	/**
	 * Génère le panel permettant de choisir la difficulté du jeu
	 * @return le panel généré
	 */
	private JPanel difficultyCard() {
		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBorder(new EmptyBorder(80, 0, 100, 0));

		JLabel cardText = new JLabel("Choisissez la difficulté :");
		cardText.setAlignmentX(CENTER_ALIGNMENT);
		cardText.setFont(new Font(cardText.getFont().getFontName(), Font.BOLD, 20));
		cardText.setBorder(new EmptyBorder(0, 0, 20, 0));
		card.add(cardText);

		JPanel buttonsPnl = new JPanel();
		buttonsPnl.setBorder(new TitledBorder("Difficulté"));
		buttonsPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		card.add(buttonsPnl);

		easyDifficultyBtn = new JToggleButton("Facile");
		classicDifficultyBtn = new JToggleButton("Normale");
		hardDifficultyBtn = new JToggleButton("Difficile");
		extremeDifficultyBtn = new JToggleButton("Extrême");
		personalizedDifficultyBtn = new JToggleButton("Personnalisé");

		easyDifficultyBtn.setPreferredSize(new Dimension(150, 50));
		classicDifficultyBtn.setPreferredSize(new Dimension(150, 50));
		hardDifficultyBtn.setPreferredSize(new Dimension(150, 50));
		extremeDifficultyBtn.setPreferredSize(new Dimension(150, 50));
		personalizedDifficultyBtn.setPreferredSize(new Dimension(150, 50));

		easyDifficultyBtn.addActionListener(controller);
		classicDifficultyBtn.addActionListener(controller);
		hardDifficultyBtn.addActionListener(controller);
		extremeDifficultyBtn.addActionListener(controller);
		personalizedDifficultyBtn.addActionListener(controller);

		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0; c.gridy = 0;
		buttonsPnl.add(easyDifficultyBtn, c);
		c.gridx = 1; c.gridy = 0;
		buttonsPnl.add(classicDifficultyBtn, c);
		c.gridx = 0; c.gridy = 1;
		buttonsPnl.add(hardDifficultyBtn, c);
		c.gridx = 1; c.gridy = 1;
		buttonsPnl.add(extremeDifficultyBtn, c);
		c.gridx = 0; c.gridy = 2; c.gridwidth = 4;
		buttonsPnl.add(personalizedDifficultyBtn, c);

		return wrap(card);
	}

	private JPanel nameCard() {
		JPanel card = new JPanel();
		card.setLayout(new GridBagLayout());
		card.setBorder(new EmptyBorder(0, 0, 50, 0));

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5, 5);
		c.fill = GridBagConstraints.BOTH;

		JLabel cardText = new JLabel("Pseudo :");
		cardText.setAlignmentX(CENTER_ALIGNMENT);
		cardText.setFont(new Font(cardText.getFont().getFontName(), Font.BOLD, 20));
		cardText.setBorder(new EmptyBorder(0, 0, 10, 0));
		c.gridx = 0; c.gridy = 0;
		card.add(cardText, c);

		JPanel namesConfig = new JPanel();
		namesConfig.setLayout(new BoxLayout(namesConfig, BoxLayout.X_AXIS));
		c.gridx = 0; c.gridy = 1;
		card.add(namesConfig, c);

		JPanel player1Pnl = new JPanel();
		player1Pnl.setLayout(new BoxLayout(player1Pnl, BoxLayout.Y_AXIS));
		player1Pnl.setBorder(new TitledBorder("Joueur 1"));
		namesConfig.add(player1Pnl);

		playerName1 = new JTextField();
		playerName1.setPreferredSize(new Dimension(180, 30));
		setPlaceholderText("Entrez un pseudo", playerName1);
		player1Pnl.add(wrap(playerName1));


		player2Pnl = new JPanel();
		player2Pnl.setLayout(new BoxLayout(player2Pnl, BoxLayout.Y_AXIS));
		player2Pnl.setBorder(new TitledBorder("Joueur 2"));
		c.gridx = 1; c.gridy = 1;
		namesConfig.add(player2Pnl, c);

		playerName2 = new JTextField();
		playerName2.setPreferredSize(new Dimension(180, 30));
		setPlaceholderText("Entrez un pseudo", playerName2);
		player2Pnl.add(wrap(playerName2));


		launchGameBtn = new JButton("Lancer la partie");
		launchGameBtn.addActionListener(controller);
		launchGameBtn.setPreferredSize(new Dimension(0, 50));
		c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
		card.add(launchGameBtn, c);

		return card;
	}

	private JPanel personalisedDifficultyConfigCard() {
		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBorder(new EmptyBorder(0, 0, 100, 0));

		JLabel cardText = new JLabel("Configuration personnalisée :");
		cardText.setAlignmentX(CENTER_ALIGNMENT);
		cardText.setFont(new Font(cardText.getFont().getFontName(), Font.BOLD, 20));
		cardText.setBorder(new EmptyBorder(0, 0, 20, 0));
		card.add(cardText);


		/* Config taille */
		JPanel sizeConfigPnl = new JPanel();
		sizeConfigPnl.setLayout(new GridLayout(1, 2));
		sizeConfigPnl.setBorder(BorderFactory.createTitledBorder("Taille"));
		card.add(sizeConfigPnl);

		// Config largeur
		JPanel widthConfig = new JPanel();

		JLabel widthLbl = new JLabel("Largeur :");
		widthLbl.setAlignmentX(LEFT_ALIGNMENT);
		widthConfig.add(widthLbl);

		widthInput = new JSpinner(new SpinnerNumberModel(2, 2, 10, 1));
		widthInput.setAlignmentX(LEFT_ALIGNMENT);
		widthInput.setPreferredSize(new Dimension(50, 20));
		widthConfig.add(wrap(widthInput));

		sizeConfigPnl.add(widthConfig);

		// Config hauteur
		JPanel heightConfig = new JPanel();

		JLabel heightLbl = new JLabel("Hauteur :");
		heightLbl.setAlignmentX(LEFT_ALIGNMENT);
		heightConfig.add(heightLbl);

		heightInput = new JSpinner(new SpinnerNumberModel(2, 2, 4, 1));
		heightInput.setAlignmentX(LEFT_ALIGNMENT);
		heightInput.setPreferredSize(new Dimension(50, 20));
		heightConfig.add(wrap(heightInput));

		sizeConfigPnl.add(heightConfig);
		/* ------------------------------ */
		JPanel timerPnl = new JPanel();
		timerPnl.setLayout(new GridLayout(1, 2));
		timerPnl.setBorder(BorderFactory.createTitledBorder("Chrono"));
		timerPnl.setPreferredSize(new Dimension(0, 50));
		card.add(timerPnl);

		timerActivated = new JCheckBox("Limite de temps");
		timerActivated.setBorder(new EmptyBorder(0, 10, 0, 0));
		timerActivated.setAlignmentX(RIGHT_ALIGNMENT);
		timerActivated.addActionListener(controller);
		timerPnl.add(timerActivated);

		timerInput = new JSpinner(new SpinnerNumberModel(60, 5, 60*60, 10));
		timerInput.setVisible(false);
		timerInput.setPreferredSize(new Dimension(60, 20));
		timerPnl.add(wrap(timerInput));
		/* ------------------------------ */

		JPanel styleConfig = new JPanel();
		styleConfig.setLayout(new BoxLayout(styleConfig, BoxLayout.Y_AXIS));
		styleConfig.setBorder(BorderFactory.createTitledBorder("Style"));
		card.add(styleConfig);

		JLabel styleLbl = new JLabel("Selectionnez votre style :");
		styleConfig.add(wrap(styleLbl));

		String[] styles = {
				"",
				"objets",
				"emojis"
		};
		stylePicker = new JComboBox <>(styles);
		stylePicker.setPreferredSize(new Dimension(120, 20));
		styleConfig.add(wrap(stylePicker));

		validatePersonalised = new JButton("Valider la configuration");
		validatePersonalised.setAlignmentX(LEFT_ALIGNMENT);
		validatePersonalised.addActionListener(controller);
		validatePersonalised.setPreferredSize(new Dimension(300, 50));
		card.add(wrap(validatePersonalised));

		return wrap(card);
	}


	// Getters
	public JPanel getCardsContainer() {
		return cardsContainer;
	}

	public JPanel getPlayer2Pnl() {
		return player2Pnl;
	}

	public JToggleButton getSoloModeBtn() {
		return soloModeBtn;
	}

	public JToggleButton getDuoModeBtn() {
		return duoModeBtn;
	}

	public JToggleButton getEasyDifficultyBtn() {
		return easyDifficultyBtn;
	}

	public JToggleButton getClassicDifficultyBtn() {
		return classicDifficultyBtn;
	}

	public JToggleButton getHardDifficultyBtn() {
		return hardDifficultyBtn;
	}

	public JToggleButton getExtremeDifficultyBtn() {
		return extremeDifficultyBtn;
	}

	public JToggleButton getPersonalizedDifficultyBtn() {
		return personalizedDifficultyBtn;
	}

	public JButton getLaunchGameBtn() {
		return launchGameBtn;
	}

	public JButton getReturnBtn() {
		return returnBtn;
	}

	public JSpinner getWidthInput() {
		return widthInput;
	}

	public JSpinner getHeightInput() {
		return heightInput;
	}

	public JSpinner getTimerInput() {
		return timerInput;
	}

	public JCheckBox getTimerActivated() {
		return timerActivated;
	}

	public JComboBox <String> getStylePicker() {
		return stylePicker;
	}
	
	public JButton getValidatePersonalised() {
		return validatePersonalised;
	}
	
	public JTextField getPlayerName1() {
		return playerName1;
	}
	
	public JTextField getPlayerName2() {
		return playerName2;
	}
}