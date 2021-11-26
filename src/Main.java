import com.formdev.flatlaf.FlatLightLaf;
import controller.App;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        App app = App.getInstance();

        App.saveNewScore("Jules", 15, App.SOLO);
        System.out.println();

        HashMap<String, Integer> scores = App.getHighScores(App.SOLO);
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("clé: " + entry.getKey() + " | valeur: " + entry.getValue());
        }
    }
}
