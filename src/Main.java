import com.formdev.flatlaf.FlatLightLaf;
import controller.App;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        App app = App.getInstance();
    }
}
