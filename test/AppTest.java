import controller.App;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void saveNewScore() {
        App.saveNewScore("TEST", 15, 1);

        String lastLine = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("data", "score").toFile()));

            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
            br.close();
            assertEquals("1|15|TEST", lastLine);

        }catch (Exception ignored) {
            fail("erreur fonction");
        }

    }
}