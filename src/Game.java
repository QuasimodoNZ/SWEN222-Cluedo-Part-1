public class Game {
	private String currentTurn;

	public Game() {
		Board board = new Board();

		// Ask for number of players
		// Create all players, list of players?
		Player player = new Player("Jack Mustard");

		// While dice roll is zero or more (must include zero as they can still
		// accuse)

		String options = board.getOptions(player); // Gets all potential options for
												// the player

		String selctedOption = player.presentOptions(options); // Presents all
																// the options
																// to the player

		play(selectedOption); // Makes the selected play

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game obj = new Game();
	}

}
