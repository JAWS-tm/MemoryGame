package additional;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
/**
 * Class abstraite pour pouvoir avoir plusieurs fenêtres et les gérer facilement
 *
 */
public abstract class AppView extends JPanel {
    protected abstract void generatePanel() throws AppException;
    
    /**
     * Permet d'encapsuler un élément de type JPanel, JLabel ou autre, dans un nouveau Panel pour avoir plus
     * de flexibilité sur sa taille etc...
     * @param element	Objet que l'on souhaite encapsuler
     * @return Un JPanel contenant l'objet voulu
     */
    protected JPanel wrap(JComponent element) {
        JPanel wrapper = new JPanel();
        wrapper.add(element);
        return wrapper;
    }
    
    /**
     * Permet de pré-remplir une zonne de texte (JTextField par exemple) avec un texte en italique afin
     * d'indiquer la saisie attendue par l'utilisateur 
     * @param text	Texte que l'on souhaite afficher
     * @param component	Nom de l'objet JTextField en question
     */
    protected void setPlaceholderText(String text, JTextField component) {
    	component.setText("  " + text);
    	component.setFont(new Font(component.getFont().getFontName(), Font.ITALIC, component.getFont().getSize()));
		component.setForeground(Color.gray);
		component.addMouseListener(new MouseListener() {           
		    @Override
		    public void mouseReleased(MouseEvent e) {}         
		    @Override
		    public void mousePressed(MouseEvent e) {}
		    @Override
		    public void mouseExited(MouseEvent e) {}           
		    @Override
		    public void mouseEntered(MouseEvent e) {}          
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        JTextField textField = ((JTextField)e.getSource());
		        textField.setText("");
		        textField.setFont(new Font(textField.getFont().getFontName(), Font.PLAIN, textField.getFont().getSize()));
		        textField.setForeground(Color.black);
		        textField.removeMouseListener(this);
		    }
		});
    }
}
