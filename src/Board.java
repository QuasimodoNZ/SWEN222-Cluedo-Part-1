import java.util.*;

public class Board {

	// The characters available to play
	public enum Character {
		JACK_MUSTARD,
		KASSANDRA_SCARLETT,
		DIANA_WHITE,
		JACOB_GREEN,
		ELEANOR_PEACOCK,
		VICTOR_PLUM;
		
		public String toString(){
			switch (this){
			case JACK_MUSTARD:
				return "Jack Mustard";
			case KASSANDRA_SCARLETT:
				return "Kassandra Scarlett";
			case DIANA_WHITE:
				return "Diana White";
			case JACOB_GREEN:
				return "Jacob Green";
			case ELEANOR_PEACOCK:
				return "Eleanor Peackock";
			case VICTOR_PLUM:
				return "Victor Plum";
			default: throw new IllegalArgumentException();
			}
		}
	}

	public Board() {
		// Asks user for the number of players
		System.out.println("Welcome to Cludo");
		System.out.println("Please enter the number of players (3-6)");
		Scanner inputReader = new Scanner(System.in);
		
		// Checks for valid input
		if (inputReader.hasNextInt()) {
			int num = inputReader.nextInt();
			// Checks for a valid number
			if (3 <= num && num <= 6) {
				// Links players to characters
				while (num > 0){
					System.out.println("Enter Player "+num+"'s character:");
					String name = inputReader.next();
					if (validCharacter(name) && unselected(name)){
						
					}
				}
			} else {
				System.out.println("Invalid number of players");
			}
		} else {
			System.out.println("Invalid input");
		}
		// Create all players, list of players?
		Player player = new Player("Jack Mustard");

		// While dice roll is zero or more (must include zero as they can still
		// accuse)

		// Gets all potential options for the player
		String options = getOptions(player);

		// Presents all the options to the player
		while (true) {
			String selectedOption = player.presentOptions(options);

			if (play(selectedOption)) {
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
	 * 
	 * @param The
	 *            option requested
	 * @return True if successful, false otherwise
	 */
	public boolean play(String option) {
		return true;
	}

	public static void main(String[] args) {
		Board board = new Board();
	}
}
