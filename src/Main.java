import controller.App;

import javax.swing.*;

/**
 *Class principale qui lance la premiere fenêtre de la class AppView 
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        }catch (Exception ignored) {}

        App app = App.getInstance();
    }
}
