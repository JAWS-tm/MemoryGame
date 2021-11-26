import com.formdev.flatlaf.FlatLightLaf;
import controller.App;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        App app = App.getInstance();
    }
}
