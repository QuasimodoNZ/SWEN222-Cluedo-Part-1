
public class Game {
	private String currentTurn;
	
	public Game(){
		Board board = new Board();
		
		//Ask for number of players
		// Create all players, list of players?
		Player player = new Player();
		
		//While dice roll is zero or more (must include zero as they can still accuse)
		
		board.getOptions(); // Gets all potential options for the player
		
		player.presentOptions(); // Presents all the options to the player
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game obj = new Game();
	}

}
