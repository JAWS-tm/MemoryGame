package additional;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public abstract class AppView extends JPanel {
    protected abstract void generatePanel() throws AppException;

    protected JPanel wrap(JComponent element) {
        JPanel wrapper = new JPanel();
        wrapper.add(element);
        return wrapper;
    }
    
    protected void setPlaceholderText(String text, JTextField component) {
    	component.setText(text);
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
		        JTextField texteField = ((JTextField)e.getSource());
		        texteField.setText("");
		        texteField.setFont(new Font(texteField.getFont().getFontName(), Font.PLAIN, texteField.getFont().getSize()));
		        texteField.setForeground(Color.black);
		        texteField.removeMouseListener(this);
		    }
		});
    }
}
