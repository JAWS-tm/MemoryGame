package additional;

import javax.swing.*;

public abstract class AppView extends JPanel {
    protected abstract void generatePanel() throws AppException;

    protected JPanel wrap(JComponent element) {
        JPanel wrapper = new JPanel();
        wrapper.add(element);
        return wrapper;
    }
}
