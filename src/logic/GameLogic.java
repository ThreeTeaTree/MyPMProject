package logic;

public class GameLogic {
	
	public enum STATE {
		Menu , Game , Help
	}
	private static STATE gameState = STATE.Menu ;
	
	public GameLogic() {
		
	}
	
	public static STATE getGameState() {
		return gameState ;
	}
	
	public static void setGameState(STATE gameState) {
		GameLogic.gameState = gameState ;
	}

}
