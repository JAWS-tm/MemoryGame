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

	
	private JPanel soloDuoFlowLayout, difficulty, difficultySize, centerDifficultySize, wrapSoloDuoInBL;
	private JToggleButton solo, duo, facile, normal, difficile, extreme, personnaliser;
	private JButton play, validerPersonnaliser, annulerPersonnaliser;

	public GameConfigPanel(){
		super();
		this.controller = new GameConfigController(this);
		generatePanel();
	}
	
	@Override
	protected void generatePanel() {
//	 	creation du conteneur principal
			this.setLayout(new BorderLayout());
			
			
			////	creation du conteneur des boutons des modes
		
			wrapSoloDuoInBL =new JPanel();
			wrapSoloDuoInBL.setLayout(new BorderLayout());
		
			
			soloDuoFlowLayout = new JPanel(); 
			soloDuoFlowLayout.setLayout(new FlowLayout());
			
			solo = new JToggleButton("Solo");
			soloDuoFlowLayout.add(solo);
			
			
			duo = new JToggleButton("Duo");
			soloDuoFlowLayout.add(duo);
			
			wrapSoloDuoInBL.add(soloDuoFlowLayout, BorderLayout.CENTER);
			this.add(wrapSoloDuoInBL, BorderLayout.NORTH);
		
			
			solo.setPreferredSize(new Dimension(150,50));
			duo.setPreferredSize(new Dimension(150,50));
			
			JPanel videWrapSoloDuoInBL = new JPanel();
			videWrapSoloDuoInBL.setPreferredSize(new Dimension(100,100));
			wrapSoloDuoInBL.add(videWrapSoloDuoInBL, BorderLayout.NORTH);
		
			
			////creation du conteneur des boutons difficulté
			
			centerDifficultySize = new JPanel(); // premier sous-conteneur
			centerDifficultySize.setLayout(new BorderLayout());
			
			difficultySize = new JPanel(); // deuxieme sous-conteneur
			
			difficulty = new JPanel(); // troisieme sous-conteneur
			GridLayout difficulty2 = new GridLayout(5,1);
			difficulty2.setVgap(20);
			difficulty.setLayout(difficulty2);
			
			
			
			
			facile = new JToggleButton("Facile");
			facile.setPreferredSize(new Dimension(180,33));
			difficulty.add(facile);
			
			normal = new JToggleButton("Normal");
			difficulty.add(normal);
			
			difficile = new JToggleButton("Difficile");
			difficulty.add(difficile);
			
			extreme = new JToggleButton("Extrem");
			difficulty.add(extreme);
			
			personnaliser = new JToggleButton("Personnaliser");
			difficulty.add(personnaliser);
			
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
			videNordWrapPlayInBL.setPreferredSize(new Dimension(100,120));
			
			wrapPlayInBL.add(videNordWrapPlayInBL, BorderLayout.SOUTH);
			
			play = new JButton("Play");
			JPanel playFlowLayout = new JPanel();
			playFlowLayout.setLayout(new FlowLayout());
			play.setPreferredSize(new Dimension(300,60));
			
			playFlowLayout.add(play);
			
			wrapPlayInBL.add(playFlowLayout, BorderLayout.CENTER);
			
			
			
			this.add(wrapPlayInBL, BorderLayout.SOUTH);
			
			solo.addActionListener(controller);
			duo.addActionListener(controller);
			
			facile.addActionListener(controller);
			normal.addActionListener(controller);
			difficile.addActionListener(controller);
			extreme.addActionListener(controller);
			personnaliser.addActionListener(controller);
			
			play.addActionListener(controller);
		
	}
	
	private JTextField largeurInput;
	private JTextField hauteurInput;
	private JFrame personnaliserPopUp;
	private JComboBox<String> choixImages;
	
	public void popUp() {
		personnaliserPopUp = new JFrame();
		personnaliserPopUp.setVisible(true);
		personnaliserPopUp.setSize(300,250);
		personnaliserPopUp.setLocationRelativeTo(null);
		personnaliserPopUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		JPanel contentPopUp = new JPanel();
		contentPopUp.setLayout(new GridLayout(8,1));
		personnaliserPopUp.setContentPane(contentPopUp);
		
	
		JLabel largeur = new JLabel(" Largeur :");
		contentPopUp.add(largeur);
		
		largeurInput = new JTextField();
		contentPopUp.add(largeurInput);
		
		
		JLabel hauteur = new JLabel(" Hauteur :");
		contentPopUp.add(hauteur);
		
		hauteurInput = new JTextField();
		contentPopUp.add(hauteurInput);
		
		String[] styles = {"", "jouets", "emojis"};
		choixImages = new JComboBox<>(styles);
		
	
		JLabel TextChoix = new JLabel(" Selectionnez votre style :");
		contentPopUp.add(TextChoix);
		
		
		contentPopUp.add(choixImages);
		
		
		JPanel espace = new JPanel();
		contentPopUp.add(espace);
		
		
		
		validerPersonnaliser = new JButton("  OK  ");
		annulerPersonnaliser = new JButton("Annuler");
		
		JPanel wrapValiderPersonnaliser = new JPanel();
		wrapValiderPersonnaliser.setLayout(new FlowLayout());
		wrapValiderPersonnaliser.add(annulerPersonnaliser);
		wrapValiderPersonnaliser.add(validerPersonnaliser);
		contentPopUp.add(wrapValiderPersonnaliser);
		
		
		
		validerPersonnaliser.addActionListener(controller);
		annulerPersonnaliser.addActionListener(controller);
		
	}

	
	// Getters
	
	public JToggleButton getExtreme() {
		return extreme;
	}

	public void setExtreme(JToggleButton extreme) {
		this.extreme = extreme;
	}

	public JToggleButton getSolo() {
		return solo;
	}

	public JToggleButton getDuo() {
		return duo;
	}

	public JToggleButton getFacile() {
		return facile;
	}

	public JToggleButton getNormal() {
		return normal;
	}

	public JToggleButton getDifficile() {
		return difficile;
	}

	public JToggleButton getPersonnaliser() {
		return personnaliser;
	}

	public JButton getPlay() {
		return play;
	}

	public JButton getValiderPersonnaliser() {
		return validerPersonnaliser;
	}

	public JButton getAnnulerPersonnaliser() {
		return annulerPersonnaliser;
	}

	public JTextField getLargeurInput() {
		return largeurInput;
	}

	public JTextField getHauteurInput() {
		return hauteurInput;
	}

	public JComboBox<String> getChoixImages() {
		return choixImages;
	}

	public JFrame getPersonnaliserPopUp() {
		return personnaliserPopUp;
	}
	
	

	
	
	
}
