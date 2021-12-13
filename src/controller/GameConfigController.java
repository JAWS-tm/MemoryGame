package controller;

import additional.AbstractDifficulty;
import additional.AppException;
import additional.Difficulty;
import additional.GameConfig;
import display.GameConfigPanel;
import display.GamePanel;
import display.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Class qui gère le fonctionnement de la fenetre de configuration de la partie
 *
 */
public class GameConfigController implements ActionListener{
	private GameConfigPanel view;

	private int modeSelected;
	private AbstractDifficulty difficultySelected;
	private int currentCardIndex = 1; // 1 : mode | 2 : difficulty | 3 : name | 4 : personalised Diff

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

		if(source == view.getSoloModeBtn() || source == view.getDuoModeBtn()) {
			view.getSoloModeBtn().setSelected(source == view.getSoloModeBtn());
			view.getDuoModeBtn().setSelected(source == view.getDuoModeBtn());

			view.getPlayer2Pnl().setVisible(source == view.getDuoModeBtn());

			modeSelected = (source == view.getSoloModeBtn()) ? 1 : 2;
			((CardLayout) view.getCardsContainer().getLayout()).next(view.getCardsContainer());
			currentCardIndex = 2;
		}

		else if(source == view.getEasyDifficultyBtn() || source == view.getClassicDifficultyBtn() || source == view.getHardDifficultyBtn() || source == view.getExtremeDifficultyBtn() || source == view.getPersonalizedDifficultyBtn()) {
			view.getEasyDifficultyBtn().setSelected(source == view.getEasyDifficultyBtn());
			view.getClassicDifficultyBtn().setSelected(source == view.getClassicDifficultyBtn());
			view.getHardDifficultyBtn().setSelected(source == view.getHardDifficultyBtn());
			view.getExtremeDifficultyBtn().setSelected(source == view.getExtremeDifficultyBtn());
			view.getPersonalizedDifficultyBtn().setSelected(source == view.getPersonalizedDifficultyBtn());

			if (source == view.getEasyDifficultyBtn())
				difficultySelected = new Difficulty.Easy();
			else if (source == view.getClassicDifficultyBtn())
				difficultySelected = new Difficulty.Classic();
			else if (source == view.getHardDifficultyBtn())
				difficultySelected = new Difficulty.Hard();
			else if (source == view.getExtremeDifficultyBtn())
				difficultySelected = new Difficulty.Extreme();

			if (source == view.getPersonalizedDifficultyBtn()) {
				((CardLayout) view.getCardsContainer().getLayout()).show(view.getCardsContainer(), "personalised_card");
				currentCardIndex = 4;
			}else{
				((CardLayout) view.getCardsContainer().getLayout()).next(view.getCardsContainer());
				currentCardIndex = 3;
			}
		}

		else if(source == view.getLaunchGameBtn()) {
			if(view.getPlayerName1().getText().length() == 0 || (modeSelected == 1 && view.getPlayerName2().getText().length() == 0))
				JOptionPane.showMessageDialog(view, "Entrez un pseudo", "Erreur: PSEUDO", JOptionPane.ERROR_MESSAGE);

			else if (view.getPlayerName1().getText().length() > 12 || (modeSelected == 2 && view.getPlayerName2().getText().length() > 12))
				JOptionPane.showMessageDialog(view, "La taille du pseudo ne doit pas dépasser 12 caractères", "Erreur: PSEUDO", JOptionPane.ERROR_MESSAGE);

			else {
				try {
					App.getInstance().changeView(new GamePanel(new GameConfig(modeSelected, difficultySelected, view.getPlayerName1().getText(), view.getPlayerName2().getText())));
				} catch (AppException exception){
					if (exception.getErrorType() == AppException.Type.VIEW_LOADING_FAILED)
						App.getInstance().changeView(new MainPanel());
				}
			}
		}
		else if (source == view.getTimerActivated())
			view.getTimerInput().setVisible(view.getTimerActivated().isSelected());

		else if (source == view.getValidatePersonalised()) {
			try {
				int cols = (int) view.getWidthInput().getValue();
				int rows = (int) view.getHeightInput().getValue();
				String iconsStyle = (String) view.getStylePicker().getSelectedItem();

				if(Objects.equals(iconsStyle, "")) {
					JOptionPane.showMessageDialog(view, "Choisissez un style d'image !", "Erreur: STYLE", JOptionPane.ERROR_MESSAGE);
				}
				else if (!Difficulty.isValidGridSize(rows, cols))
					JOptionPane.showMessageDialog(view,
							"Les dimensions séléctionnées ne forment pas une grille valide !\n" +
									"La grille doit contenir un nombre pair de case (ex : 3*4)", "Erreur: Taille"
							, JOptionPane.ERROR_MESSAGE
					);
				//else if (pas assez d'images')					 //TODO : nombre d'images suffisant
				else {
					difficultySelected = new Difficulty.Personalized(rows, cols, view.getTimerActivated().isSelected() ? (int) view.getTimerInput().getValue() : 0, iconsStyle);
					((CardLayout) view.getCardsContainer().getLayout()).show(view.getCardsContainer(), "name_card");
					currentCardIndex = 3;
				}

			}catch(NumberFormatException erreur){
				JOptionPane.showMessageDialog(view, "Remplissez tous les champs !", "Erreur: valeur manquante", JOptionPane.PLAIN_MESSAGE);
			}
		}
		else if (source == view.getReturnBtn()) {
			if (currentCardIndex == 4) {
				currentCardIndex = 3;
				((CardLayout) view.getCardsContainer().getLayout()).show(view.getCardsContainer(), "difficulty_card");
			} else if (currentCardIndex == 3 && difficultySelected instanceof Difficulty.Personalized){
				((CardLayout) view.getCardsContainer().getLayout()).show(view.getCardsContainer(), "personalised_card");
				currentCardIndex= 4;
			} else if (currentCardIndex > 1){
				((CardLayout) view.getCardsContainer().getLayout()).previous(view.getCardsContainer());
				currentCardIndex--;
			}else
				App.getInstance().changeView(new MainPanel());
		}
		
	}

}
