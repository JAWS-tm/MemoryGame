import com.formdev.flatlaf.FlatLightLaf;
import controller.App;
/**
 *Class principale qui lance la premiere fenêtre de la class AppView 
 *
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        App app = App.getInstance();
    }
}
