package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import display.GameConfigPanel;
import display.GamePanel;

public class GameConfigController implements ActionListener{
	private GameConfigPanel view;
	
	private  JToggleButton levelSelected, modeSelected;
	
	public GameConfigController(GameConfigPanel local) {
		this.view = local;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == view.getPersonnaliser()) {
			if(view.getPersonnaliserPopUp() != null)
				view.getPersonnaliserPopUp().setVisible(true);
			else
				view.popUp();
		}
		
		if(source == view.getValiderPersonnaliser()) {

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
						view.getPersonnaliser().setSelected(true);
					}
			}catch(NumberFormatException erreur){
				JOptionPane.showMessageDialog(view, "Remplissez tous les champs !", "Erreur: valeur manquante", JOptionPane.PLAIN_MESSAGE);
				view.getPersonnaliserPopUp().setVisible(true);
				return;
			}
			
		}
		if(source == view.getAnnulerPersonnaliser()) {
			view.getPersonnaliserPopUp().setVisible(false);
			view.getAnnulerPersonnaliser().setSelected(false
					);
		}
		
		if(source == view.getSolo()) {
			view.getDuo().setSelected(false);
			modeSelected = (JToggleButton) source;
		}
		else if(source == view.getDuo()) { 
			view.getSolo().setSelected(false);
			modeSelected = (JToggleButton) source;
		}
		
		
		if(source == view.getFacile() || source == view.getNormal() || source == view.getDifficile() || source == view.getExtreme() || source == view.getPersonnaliser()) {
			if(source!=view.getFacile())
				view.getFacile().setSelected(false);
			if(source!=view.getNormal())
				view.getNormal().setSelected(false);
			if(source!=view.getDifficile())
				view.getDifficile().setSelected(false);
			if(source!=view.getExtreme())
				view.getExtreme().setSelected(false);
			if(source!=view.getPersonnaliser())
				view.getPersonnaliser().setSelected(false);
			
			levelSelected = (JToggleButton) source;

		}
		
		if(source == view.getPlay()) {
			if(modeSelected == null || !modeSelected.isSelected())
				JOptionPane.showMessageDialog(view, "Selectionnez le mode !", "Erreur: MODE", JOptionPane.PLAIN_MESSAGE);
			else if(levelSelected == null || !levelSelected.isSelected())
				JOptionPane.showMessageDialog(view, "Selectionnez le niveau !", "Erreur: NIVEAU", JOptionPane.PLAIN_MESSAGE);
			else {
				System.out.println("test");

				
				App.getInstance().changeView(new GamePanel());
			}
				
		}
		
	}

}
