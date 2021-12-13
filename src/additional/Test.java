package additional;

import static org.junit.jupiter.api.Assertions.*;
import additional.GameConfig;
import controller.App;

class Test {
	
	@org.junit.jupiter.api.Test
	void testMode() {
		GameConfig GameConfigTest = new GameConfig(2, new Difficulty.Easy(), "Hugo", null);
		assertEquals(1, GameConfigTest.getMode());
	}
	
	/*void saveTest() {
		App appTest = new App();
		assertTrue();
	}*/

}
