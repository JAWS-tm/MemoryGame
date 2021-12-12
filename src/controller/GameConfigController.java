package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import additional.AbstractDifficulty;
import additional.AppException;
import additional.Difficulty;
import additional.GameConfig;
import display.GameConfigPanel;
import display.GamePanel;
import display.MainPanel;

/**
 * Class qui gère le fonctionnement de la fenetre de configuration de la partie
 *
 */
public class GameConfigController implements ActionListener{
	private GameConfigPanel view;

	private JToggleButton modeSelected;
	private AbstractDifficulty difficultySelected;
	/**
	 * Constructeur de la class GameConfigController
	 * @param view	fenetre de configuration
	 */
	public GameConfigController(GameConfigPanel view) {
		this.view = view;
	}
	
	/**
	 * Gestion des actionsListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == view.getPersonalizedDifficultyBtn()) {
			if(view.getPersonalizedPopUp() != null)
				view.getPersonalizedPopUp().setVisible(true);
			else
				view.openPersonalizedPopUp();
		}
		
		if(source == view.getPersonalizedDifficulty_validate()) {
			try {
				int cols = Integer.parseInt(view.getLargeurInput().getText());
				int rows = Integer.parseInt(view.getHauteurInput().getText());
				String iconsStyle = (String) view.getChoixImages().getSelectedItem();

				if(cols < 2 || cols > 10 || rows < 2 || rows > 10) {
					JOptionPane.showMessageDialog(view, "Veuillez entrer une largeur/hauteur entre 2 et 10 !", "Erreur: Taille", JOptionPane.PLAIN_MESSAGE);
					view.getPersonalizedPopUp().setVisible(true);
				}
				else if(view.getChoixImages().getSelectedItem() == "") {
					JOptionPane.showMessageDialog(view, "Choisissez un style d'image !", "Erreur: STYLE", JOptionPane.PLAIN_MESSAGE);
					view.getPersonalizedPopUp().setVisible(true);
				}
				else {
					view.getPersonalizedPopUp().setVisible(false);
					view.getPersonalizedDifficultyBtn().setSelected(true);
					if (!Difficulty.isValidGridSize(rows, cols)){
						JOptionPane.showMessageDialog(view,
								"Les dimensions séléctionnées ne forment pas une grille valide !\n" +
										"La grille doit contenir un nombre pair de case (ex : 3*4)", "Erreur: Taille", JOptionPane.PLAIN_MESSAGE);

						view.getPersonalizedPopUp().setVisible(true);
						return;
					} //TODO : nombre d'images suffisant

					difficultySelected = new Difficulty.Personalized(rows, cols, 0, iconsStyle);
				}
			}catch(NumberFormatException erreur){
				JOptionPane.showMessageDialog(view, "Remplissez tous les champs !", "Erreur: valeur manquante", JOptionPane.PLAIN_MESSAGE);
				view.getPersonalizedPopUp().setVisible(true);
			}
			return;
		}

		else if(source == view.getPersonalizedDifficulty_cancel())
			view.getPersonalizedPopUp().setVisible(false);

		
		else if(source == view.getSoloModeBtn()) {
			view.getDuoModeBtn().setSelected(false);
			modeSelected = (JToggleButton) source;
		} else if(source == view.getDuoModeBtn()) {
			view.getSoloModeBtn().setSelected(false);
			modeSelected = (JToggleButton) source;
		}
		
		
		if(source == view.getEasyDifficultyBtn() || source == view.getClassicDifficultyBtn() || source == view.getHardDifficultyBtn() || source == view.getExtremeDifficultyBtn() || source == view.getPersonalizedDifficultyBtn()) {
			view.getEasyDifficultyBtn().setSelected(source == view.getEasyDifficultyBtn());
			view.getClassicDifficultyBtn().setSelected(source == view.getClassicDifficultyBtn());
			view.getHardDifficultyBtn().setSelected(source == view.getHardDifficultyBtn());
			view.getExtremeDifficultyBtn().setSelected(source == view.getExtremeDifficultyBtn());
			view.getPersonalizedDifficultyBtn().setSelected(source == view.getPersonalizedDifficultyBtn());

			if (source == view.getEasyDifficultyBtn()) {
				difficultySelected = new Difficulty.Easy();
			}else if (source == view.getClassicDifficultyBtn()) {
				difficultySelected = new Difficulty.Classic();
			}else if (source == view.getHardDifficultyBtn()) {
				difficultySelected = new Difficulty.Hard();
			}else if (source == view.getExtremeDifficultyBtn()) {
				difficultySelected = new Difficulty.Extreme();
			}
		}
		
		if (source == view.getValidate()) {
			try {
				if(view.getSoloModeBtn().isSelected())
					if(view.getName1().getText().length() > 12)
						JOptionPane.showMessageDialog(view, "Ne depassez pas 12 caracteres !", "Erreur: TAILLE", JOptionPane.PLAIN_MESSAGE);
					else if(view.getName1().getText().equals(""))
						JOptionPane.showMessageDialog(view, "Entrez un nom !", "Erreur: NOM", JOptionPane.PLAIN_MESSAGE);
					else {
						App.getInstance().changeView(new GamePanel(new GameConfig(1, difficultySelected, view.getName1().getText(), null)));
						view.getNameFrame().dispatchEvent(new WindowEvent(view.getNameFrame(), WindowEvent.WINDOW_CLOSING));
					}
				
				else
					if(view.getName1().getText().length() > 12 || view.getName2().getText().length() > 12)
						JOptionPane.showMessageDialog(view, "Ne depassez pas 12 caracteres !", "Erreur: TAILLE", JOptionPane.PLAIN_MESSAGE);
					else if(view.getName1().getText().equals("") || view.getName2().getText().equals(""))
						JOptionPane.showMessageDialog(view, "Entrez un nom !", "Erreur: NOM", JOptionPane.PLAIN_MESSAGE);
					else {
						App.getInstance().changeView(new GamePanel(new GameConfig(2, difficultySelected, view.getName1().getText(), view.getName2().getText() )));
						view.getNameFrame().dispatchEvent(new WindowEvent(view.getNameFrame(), WindowEvent.WINDOW_CLOSING));
					}
				
			} catch (AppException exception){
				if (exception.getErrorType() == AppException.Type.VIEW_LOADING_FAILED)
					App.getInstance().changeView(new MainPanel());
			}
		}
		
		if(source == view.getPlayBtn()) {
			if(modeSelected == null || !modeSelected.isSelected())
				JOptionPane.showMessageDialog(view, "Selectionnez le mode !", "Erreur: MODE", JOptionPane.PLAIN_MESSAGE);
			else if(difficultySelected == null)
				JOptionPane.showMessageDialog(view, "Selectionnez le niveau !", "Erreur: NIVEAU", JOptionPane.PLAIN_MESSAGE);
			else {
				if(view.getSoloModeBtn().isSelected())
					view.openNameFrame(1);
				else
					view.openNameFrame(2);
				
			}
		}
		
	}

}
