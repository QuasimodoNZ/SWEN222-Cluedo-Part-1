import java.util.*;

public class Board {
	private List<Player> players;
	private List<Weapon> weaponCards;
	private Weapon weaponSolution;
	private List<RoomName> roomCards;
	private RoomName roomSolution;
	private List<Character> characterCards;
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
		public List<Weapon> toList() {
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
		public List<RoomName> toList() {
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

	/**
	 * Checks all of the players to see if they already have that Character
	 */
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
		players = new LinkedList<Player>();
		weaponCards = new LinkedList<Weapon>();
		roomCards = new LinkedList<RoomName>();
		characterCards = new LinkedList<Character>();

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
	 * runs a game until a player wins
	 */
	public void playGame() {
		Scanner inputReader = new Scanner(System.in);
		while (players.size() > 1) {
			for (Player player : players) {
				int movesLeft = 1 + (int) (Math.random() * 12);
				while (movesLeft > 0) {
					System.out.println(getOptions(player));
					if (inputReader.hasNext()) {
						movesLeft -= playTurn(player, inputReader.next());
					}
				}
			}
		}
		inputReader.close();
	}

	private int playTurn(Player player, String next) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		Board board = new Board();
		board.playGame();
	}
}
