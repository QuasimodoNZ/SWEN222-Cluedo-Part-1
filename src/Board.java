import java.awt.Point;
import java.util.*;

public class Board {
	private Location[][] locations;
	private List<Player> players;
	private Weapon weaponSolution;
	private RoomName roomSolution;
	private Character characterSolution;

	// The possible murder weapons
	public enum Weapon {
		ROPE, CANDLESTICK, KNIFE, PISTOL, BASEBALL_BAT, DUMBBELL, TROPHY, POISON, AXE;
		@Override
		public String toString() {
			switch (this) {
			case ROPE:
				return "Rope";
			case CANDLESTICK:
				return "Candlestick";
			case KNIFE:
				return "Knife";
			case PISTOL:
				return "Pistol";
			case BASEBALL_BAT:
				return "Baseball Bat";
			case DUMBBELL:
				return "Dumbbell";
			case TROPHY:
				return "Trophy";
			case POISON:
				return "Posion";
			case AXE:
				return "Axe";
			default:
				throw new IllegalArgumentException();
			}
		}

		/**
		 * Returns all the possible values of Board.RoomName as a list.
		 * 
		 * @return List<Weapon>
		 */
		public static List<Weapon> toList() {
			return Arrays.asList(Weapon.values());
		}
	}

	// The Rooms in the game
	public enum RoomName {
		SPA, THEATRE, LIVING_ROOM, OBSERVATORY, PATIO, SWIMMING_POOL, HALL, KITCHEN, DINING_ROOM, GUEST_HOUSE;
		@Override
		public String toString() {
			switch (this) {
			case SPA:
				return "Spa";
			case THEATRE:
				return "Theatre";
			case OBSERVATORY:
				return "Observatory";
			case PATIO:
				return "Patio";
			case SWIMMING_POOL:
				return "Swimming Pool";
			case HALL:
				return "Hall";
			case KITCHEN:
				return "Kitchen";
			case DINING_ROOM:
				return "Dining Room";
			case GUEST_HOUSE:
				return "Guest House";
			default:
				throw new IllegalArgumentException();
			}
		}

		/**
		 * Returns all the possible values of Board.RoomName as a list.
		 * 
		 * @return List<RoomName>
		 */
		public static List<RoomName> toList() {
			return Arrays.asList(RoomName.values());
		}
	}

	// The characters available to play
	public enum Character {
		JACK_MUSTARD, KASSANDRA_SCARLETT, DIANA_WHITE, JACOB_GREEN, ELEANOR_PEACOCK, VICTOR_PLUM;
		@Override
		public String toString() {
			switch (this) {
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
			default:
				throw new IllegalArgumentException();
			}
		}

		/**
		 * Returns all the possible values of Board.Character as a list.
		 * 
		 * @return List<Character>
		 */
		public static List<Character> toList() {
			return Arrays.asList(Character.values());
		}

		public static Character toEnum(String name)
				throws IllegalArgumentException {
			switch (name) {
			case "kasandra scarlett":
				return Character.KASSANDRA_SCARLETT;
			case "jack mustard":
				return Character.JACK_MUSTARD;
			case "diane white":
				return Character.DIANA_WHITE;
			case "jacob white":
				return Character.JACOB_GREEN;
			case "eleanor peacock":
				return Character.ELEANOR_PEACOCK;
			case "victor plum":
				return Character.VICTOR_PLUM;
			default:
				throw new IllegalArgumentException();
			}
		}

	}

	// Checks all of the players to see if they already have that Character
	public boolean unselected(Character character) {
		for (Player p : players) {
			if (p.getCharacter().equals(character)) {
				return false;
			}
		}
		return true;
	}

	/**
	 *********** Board Constructor ***********
	 */
	public Board() {
		locations = new Location[25][25];
		players = new LinkedList<Player>();
		List<Weapon> weaponCards = Weapon.toList();
		List<RoomName> roomCards = RoomName.toList();
		List<Character> characterCards = Character.toList();

		Collections.shuffle(weaponCards);
		Collections.shuffle(roomCards);
		Collections.shuffle(characterCards);

		weaponSolution = weaponCards.remove(0);
		roomSolution = roomCards.remove(0);
		characterSolution = characterCards.remove(0);

		// Loops through all the players adding cards until all the lists are
		// empty
		int i = 0;
		while (true) {
			if (i > players.size() - 1) {
				i = 0;
			}
			if (!weaponCards.isEmpty()) {
				players.get(i).getWeaponCards().add(weaponCards.remove(0));
				i++;
			} else if (!roomCards.isEmpty()) {
				players.get(i).getRoomCards().add(roomCards.remove(0));
				i++;
			} else if (!characterCards.isEmpty()) {
				players.get(i).getCharacterCards()
						.add(characterCards.remove(0));
				i++;
			} else {
				System.out.println("Cards dealt");
				break;
			}
		}

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
				while (num > 0) {
					System.out.println("Enter Player " + num + "'s character:");
					String name = inputReader.next().toLowerCase();
					try {
						Character character = Character.toEnum(name);
						if (unselected(character)) {
							Player p = new Player(character);
							players.add(p);
						}
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid Character Name");
					}

				}
			} else {
				System.out.println("Invalid number of players");
			}
		} else {
			System.out.println("Invalid input");
		}
		inputReader.close();
	}

	public String getOptions(Player player) {
		// Gets the location of the current players character
		// Location location = player.getLocation();

		// Computes options of that location

		// TODO Finish Method
		String options = null;

		return options;
	}

	/**
	 * Runs a game until a player wins
	 */
	public void playGame() {
		Scanner inputReader = new Scanner(System.in);
		while (players.size() > 1) {
			for (Player player : players) {
				int movesLeft = 1 + (int) (Math.random() * 12);
				while (movesLeft > 0) {
					System.out.println(getOptions(player));
					if (inputReader.hasNext()) {// needs to check outcome of an
												// accuse
						movesLeft -= playTurn(player, inputReader.next());
					}
				}
			}
		}
		inputReader.close();
	}

	/**
	 * Returns the location that is in the spot next to the player in specified
	 * by the direction
	 * 
	 * @param player
	 * @param direction
	 * @return
	 */
	private Location move(Player player, String direction) {
		Point point = player.getLocation().getPoint();

		// returns the location in the direction specified
		switch (direction) {
		case "north":
			if (point.y == 0)
				return null;
			return locations[point.x][point.y - 1];
		case "south":
			if (point.y == 24)
				return null;
			return locations[point.x][point.y + 1];
		case "east":
			if (point.x == 24)
				return null;
			return locations[point.x + 1][point.y];
		case "west":
			if (point.x == 0)
				return null;
			return locations[point.x - 1][point.y];
		default:
			return null;
		}

	}

	/**
	 * Executes the move specified by the player
	 * <p>
	 * returns 1 if the move is moving a step or going through a door or passage
	 * <p>
	 * returns 0 if the move is invalid and will usually print out a reason why
	 * <p>
	 * returns 12 if the player executes a suggestion or an accusation
	 * 
	 * @param player
	 * @param move
	 * @return
	 */
	private int playTurn(Player player, String move) {
		if (move.startsWith("move")) {
			Location nextLocation = move(player, move.substring(5).trim());
			if (nextLocation != null) {
				player.moveLocation(nextLocation);
				return 1;
			}
		}
		if (move.startsWith("door")) {
			return 1;
			// get availabe doors and compare selection
			// if available initiate player.moveLocation( other rooms possible
			// location)
			// else print out that door is not available
		}
		if (move.startsWith("accuse")) {
			return 12;
			// checks if the player has won, return a special integer based on
			// outcome for playGame() to deal with
		}
		if (move.startsWith("suggest")) {
			// compare every players hand with the suggestion and if found,
			// print out that it has been
			return 12;
		}
		System.out
				.println("That is an invalid move. Your available moves are:\n");
		return 0;
	}

	public static void main(String[] args) {
		Board board = new Board();
		board.playGame();
	}
}
