package additional;

public class GameConfig {
	
	
	private int mode;
	private String playerName1, playerName2;
	private AbstractDifficulty difficulty;
	
	public GameConfig(int mode, AbstractDifficulty difficulty, String playerName1, String playerName2) {
		
		this.mode = mode;
		if (difficulty == null) throw new NullPointerException("Game difficulty not instanced");
		this.difficulty = difficulty;
		this.playerName1 = playerName1;
		this.playerName2 = playerName2;
		
		
	}

	public int getMode() {
		return mode;
	}

	public String getPlayerName1() {
		return playerName1;
	}

	public String getPlayerName2() {
		return playerName2;
	}

	public AbstractDifficulty getDifficulty() {
		return difficulty;
	}
	
	
}
