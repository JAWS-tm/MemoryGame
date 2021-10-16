package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import additional.AbstractDifficulty;
import additional.Difficulty;
import display.GameConfigPanel;
import display.GamePanel;

public class GameConfigController implements ActionListener{
	private GameConfigPanel view;

	private  JToggleButton levelSelected, modeSelected;
	private AbstractDifficulty level;

	public GameConfigController(GameConfigPanel local) {
		this.view = local;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == view.getPersonalizedDifficultyBtn()) {
			if(view.getPersonnaliserPopUp() != null)
				view.getPersonnaliserPopUp().setVisible(true);
			else
				view.openPersonnaliserPopUp();
		}
		
		if(source == view.getPersonalizedDifficulty_validate()) {
			try {
				if(Double.parseDouble(view.getLargeurInput().getText()) < 2 || Double.parseDouble(view.getLargeurInput().getText()) > 10) {
					JOptionPane.showMessageDialog(view, "entrez une largeur entre 2 et 10 !", "Erreur: TAILLE", JOptionPane.PLAIN_MESSAGE);
					view.getPersonnaliserPopUp().setVisible(true);
				}	
				else if(Double.parseDouble(view.getHauteurInput().getText()) < 2 || Double.parseDouble(view.getHauteurInput().getText()) > 10) {
					JOptionPane.showMessageDialog(view, "entrez une hauteur entre 2 et 10 !", "Erreur: TAILLE", JOptionPane.PLAIN_MESSAGE);
					view.getPersonnaliserPopUp().setVisible(true);
				}
				else if(view.getChoixImages().getSelectedItem() == "") {
					JOptionPane.showMessageDialog(view, " choisissez un style d'image !", "Erreur: STYLE", JOptionPane.PLAIN_MESSAGE);
					view.getPersonnaliserPopUp().setVisible(true);
				}
				else {
					view.getPersonnaliserPopUp().setVisible(false);
					view.getPersonalizedDifficultyBtn().setSelected(true);
				}
			}catch(NumberFormatException erreur){
				JOptionPane.showMessageDialog(view, "Remplissez tous les champs !", "Erreur: valeur manquante", JOptionPane.PLAIN_MESSAGE);
				view.getPersonnaliserPopUp().setVisible(true);
				return;
			}
		}

		if(source == view.getPersonalizedDifficulty_cancel())
			view.getPersonnaliserPopUp().setVisible(false);

		
		if(source == view.getSoloModeBtn()) {
			view.getDuoModeBtn().setSelected(false);
			modeSelected = (JToggleButton) source;
		} else if(source == view.getDuoModeBtn()) {
			view.getSoloModeBtn().setSelected(false);
			modeSelected = (JToggleButton) source;
		}
		
		
		if(source == view.getEasyDifficultyBtn() || source == view.getClassicDifficultyBtn() || source == view.getHardDifficultyBtn() || source == view.getExtremeDifficultyBtn() || source == view.getPersonalizedDifficultyBtn()) {
			if(source!=view.getEasyDifficultyBtn())
				view.getEasyDifficultyBtn().setSelected(false);
			if(source!=view.getClassicDifficultyBtn())
				view.getClassicDifficultyBtn().setSelected(false);
			if(source!=view.getHardDifficultyBtn())
				view.getHardDifficultyBtn().setSelected(false);
			if(source!=view.getExtremeDifficultyBtn())
				view.getExtremeDifficultyBtn().setSelected(false);
			if(source!=view.getPersonalizedDifficultyBtn())
				view.getPersonalizedDifficultyBtn().setSelected(false);

			if (source == view.getEasyDifficultyBtn()) {
				level = new Difficulty.Easy();
			}
			levelSelected = (JToggleButton) source;

		}
		
		if(source == view.getPlayBtn()) {
			if(modeSelected == null || !modeSelected.isSelected())
				JOptionPane.showMessageDialog(view, "Selectionnez le mode !", "Erreur: MODE", JOptionPane.PLAIN_MESSAGE);
			else if(levelSelected == null || !levelSelected.isSelected())
				JOptionPane.showMessageDialog(view, "Selectionnez le niveau !", "Erreur: NIVEAU", JOptionPane.PLAIN_MESSAGE);
			else {
				GamePanel gamePanel = new GamePanel(level);
				//gamePanel.getController().setRowsNumber(3);
				//gamePanel.getController().setColsNumber(3);
				//gamePanel.getController().setDifficulty(level);
				//gamePanel.getController().setRowsNumber(level.getRowsNumber());
				//gamePanel.getController().setColsNumber(level.getColsNumber());
				//gamePanel.generatePanel();
				App.getInstance().changeView(gamePanel);
			}

		}
		
	}

}
