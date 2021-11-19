package display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import additional.AppView;
import controller.GameConfigController;

public class GameConfigPanel extends AppView {
	private GameConfigController controller;

	private JToggleButton soloModeBtn, duoModeBtn;
	private JToggleButton easyDifficultyBtn, classicDifficultyBtn, hardDifficultyBtn, extremeDifficultyBtn, personalizedDifficultyBtn;
	private JButton playBtn, personalizedDifficulty_validate, personalizedDifficulty_cancel;

	public GameConfigPanel() {
		super();
		this.controller = new GameConfigController(this);
		generatePanel();
	}

	@Override
	protected void generatePanel() {
		//	 	creation du conteneur principal
		this.setLayout(new BorderLayout());

		////	creation du conteneur des boutons des modes

		JPanel wrapSoloDuoInBL = new JPanel();
		wrapSoloDuoInBL.setLayout(new BorderLayout());

		JPanel soloDuoFlowLayout = new JPanel();
		soloDuoFlowLayout.setLayout(new FlowLayout());

		soloModeBtn = new JToggleButton("Solo");
		soloDuoFlowLayout.add(soloModeBtn);

		duoModeBtn = new JToggleButton("Duo");
		soloDuoFlowLayout.add(duoModeBtn);

		wrapSoloDuoInBL.add(soloDuoFlowLayout, BorderLayout.CENTER);
		this.add(wrapSoloDuoInBL, BorderLayout.NORTH);

		soloModeBtn.setPreferredSize(new Dimension(150, 50));
		duoModeBtn.setPreferredSize(new Dimension(150, 50));

		JPanel videWrapSoloDuoInBL = new JPanel();
		videWrapSoloDuoInBL.setPreferredSize(new Dimension(100, 100));
		wrapSoloDuoInBL.add(videWrapSoloDuoInBL, BorderLayout.NORTH);

		////creation du conteneur des boutons difficulté

		JPanel centerDifficultySize = new JPanel(); // premier sous-conteneur
		centerDifficultySize.setLayout(new BorderLayout());

		JPanel difficultySize = new JPanel(); // deuxieme sous-conteneur

		JPanel difficulty = new JPanel(); // troisieme sous-conteneur
		GridLayout difficulty2 = new GridLayout(5, 1);
		difficulty2.setVgap(20);
		difficulty.setLayout(difficulty2);

		easyDifficultyBtn = new JToggleButton("Facile");
		easyDifficultyBtn.setPreferredSize(new Dimension(180, 33));
		difficulty.add(easyDifficultyBtn);

		classicDifficultyBtn = new JToggleButton("Normal");
		difficulty.add(classicDifficultyBtn);

		hardDifficultyBtn = new JToggleButton("Difficile");
		difficulty.add(hardDifficultyBtn);

		extremeDifficultyBtn = new JToggleButton("Extrem");
		difficulty.add(extremeDifficultyBtn);

		personalizedDifficultyBtn = new JToggleButton("Personnaliser");
		difficulty.add(personalizedDifficultyBtn);

		//encapsulation
		difficultySize.add(difficulty);

		centerDifficultySize.add(difficultySize, BorderLayout.CENTER);

		JPanel videTest = new JPanel();
		videTest.setPreferredSize(new Dimension(100, 100));
		centerDifficultySize.add(videTest, BorderLayout.NORTH);

		this.add(centerDifficultySize);

		// creation du conteneurdu bouton play

		JPanel wrapPlayInBL = new JPanel();
		wrapPlayInBL.setLayout(new BorderLayout());

		JPanel videNordWrapPlayInBL = new JPanel();
		videNordWrapPlayInBL.setPreferredSize(new Dimension(100, 120));

		wrapPlayInBL.add(videNordWrapPlayInBL, BorderLayout.SOUTH);

		playBtn = new JButton("Play");
		JPanel playFlowLayout = new JPanel();
		playFlowLayout.setLayout(new FlowLayout());
		playBtn.setPreferredSize(new Dimension(300, 60));

		playFlowLayout.add(playBtn);

		wrapPlayInBL.add(playFlowLayout, BorderLayout.CENTER);

		this.add(wrapPlayInBL, BorderLayout.SOUTH);

		soloModeBtn.addActionListener(controller);
		duoModeBtn.addActionListener(controller);

		easyDifficultyBtn.addActionListener(controller);
		classicDifficultyBtn.addActionListener(controller);
		hardDifficultyBtn.addActionListener(controller);
		extremeDifficultyBtn.addActionListener(controller);
		personalizedDifficultyBtn.addActionListener(controller);

		playBtn.addActionListener(controller);

	}

	private JTextField largeurInput;
	private JTextField hauteurInput;
	private JFrame personnaliserPopUp;
	private JComboBox < String > choixImages;

	public void openPersonalizedPopUp() {
		personnaliserPopUp = new JFrame();
		personnaliserPopUp.setVisible(true);
		personnaliserPopUp.setSize(300, 250);
		personnaliserPopUp.setLocationRelativeTo(null);
		personnaliserPopUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JPanel contentPopUp = new JPanel();
		contentPopUp.setLayout(new GridLayout(8, 1));
		personnaliserPopUp.setContentPane(contentPopUp);

		JLabel largeur = new JLabel(" Largeur :");
		contentPopUp.add(largeur);

		largeurInput = new JTextField();
		contentPopUp.add(largeurInput);

		JLabel hauteur = new JLabel(" Hauteur :");
		contentPopUp.add(hauteur);

		hauteurInput = new JTextField();
		contentPopUp.add(hauteurInput);

		String[] styles = {
				"",
				"objets",
				"emojis"
		};
		choixImages = new JComboBox < > (styles);

		JLabel TextChoix = new JLabel(" Selectionnez votre style :");
		contentPopUp.add(TextChoix);

		contentPopUp.add(choixImages);

		JPanel espace = new JPanel();
		contentPopUp.add(espace);

		personalizedDifficulty_validate = new JButton("  OK  ");
		personalizedDifficulty_cancel = new JButton("Annuler");

		JPanel wrapValiderPersonnaliser = new JPanel();
		wrapValiderPersonnaliser.setLayout(new FlowLayout());
		wrapValiderPersonnaliser.add(personalizedDifficulty_cancel);
		wrapValiderPersonnaliser.add(personalizedDifficulty_validate);
		contentPopUp.add(wrapValiderPersonnaliser);

		personalizedDifficulty_validate.addActionListener(controller);
		personalizedDifficulty_cancel.addActionListener(controller);

	}

	// Getters

	public JToggleButton getExtremeDifficultyBtn() {
		return extremeDifficultyBtn;
	}

	public void setExtremeDifficultyBtn(JToggleButton extremeDifficultyBtn) {
		this.extremeDifficultyBtn = extremeDifficultyBtn;
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

	public JToggleButton getPersonalizedDifficultyBtn() {
		return personalizedDifficultyBtn;
	}

	public JButton getPlayBtn() {
		return playBtn;
	}

	public JButton getPersonalizedDifficulty_validate() {
		return personalizedDifficulty_validate;
	}

	public JButton getPersonalizedDifficulty_cancel() {
		return personalizedDifficulty_cancel;
	}

	public JTextField getLargeurInput() {
		return largeurInput;
	}

	public JTextField getHauteurInput() {
		return hauteurInput;
	}

	public JComboBox < String > getChoixImages() {
		return choixImages;
	}

	public JFrame getPersonalizedPopUp() {
		return personnaliserPopUp;
	}

}