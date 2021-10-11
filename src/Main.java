import com.formdev.flatlaf.FlatLightLaf;
import controller.App;
import controller.GameController;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        App app = App.getInstance();

        /*GameController gameManager = GameController.getInstance();

        GameFrame frameManager = new GameFrame();
        frameManager.setVisible(true);*/

    }
}
