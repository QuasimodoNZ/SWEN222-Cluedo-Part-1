public class Board {

	public Board() {
		// Ask for number of players
		// Create all players, list of players?
		Player player = new Player("Jack Mustard");

		// While dice roll is zero or more (must include zero as they can still
		// accuse)

		// Gets all potential options for the player
		String options = getOptions(player); 

		// Presents all the options to the player
		while (true){
			String selectedOption = player.presentOptions(options); 
	
			if (play(selectedOption)){
				System.out.println("Successful Play");
				break;
			} 
		}
	}

	public String getOptions(Player player) {
		// Gets the location of the current players character
		Location location = player.getCharacter().getLocation();

		// Computes options of that location

		// TODO Finish Method
		String options = null;

		return options;
	}
	
	/**
	 * Makes the requested play, returns true if successful
	 * @param The option requested
	 * @return True if successful, false otherwise
	 */
	public boolean play(String option){
		return true;	
	}
	
	public static void main(String[] args) {
		Board board = new Board();
	}
}
