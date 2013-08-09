import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		 * Returns all the possible values of Board.Weapon as a list.
		 * 
		 * @return List<Weapon>
		 */
		public static List<Weapon> toList() {
			return new LinkedList<Weapon>(Arrays.asList(Weapon.values()));
		}

		public static Weapon toEnum(String name) {
			if (name.equals("rope"))
				return ROPE;
			if (name.equals("candlestick"))
				return CANDLESTICK;
			if (name.equals("knife"))
				return KNIFE;
			if (name.equals("pistol"))
				return PISTOL;
			if (name.equals("baseball bat"))
				return BASEBALL_BAT;
			if (name.equals("dumbbell"))
				return DUMBBELL;
			if (name.equals("trophy"))
				return TROPHY;
			if (name.equals("poison"))
				return POISON;
			if (name.equals("axe"))
				return AXE;

			throw new IllegalArgumentException();
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
			return new LinkedList<RoomName>(Arrays.asList(RoomName.values()));
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
			return new LinkedList<Character>(Arrays.asList(Character.values()));
		}

		public static Character toEnum(String name)
				throws IllegalArgumentException {
			if (name.equals("kasandra scarlett"))
				return Character.KASSANDRA_SCARLETT;
			if (name.equals("jack mustard"))
				return Character.JACK_MUSTARD;
			if (name.equals("diane white"))
				return Character.DIANA_WHITE;
			if (name.equals("jacob white"))
				return Character.JACOB_GREEN;
			if (name.equals("eleanor peacock"))
				return Character.ELEANOR_PEACOCK;
			if (name.equals("victor plum"))
				return Character.VICTOR_PLUM;

			throw new IllegalArgumentException();
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
							Player p = new Player(character, true);
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

		if (players.size() < 6) {
			// Adds the unused characters
			boolean match = false;
			for (Character c : Character.toList()) {
				for (Player p : players) {
					if (c.equals(p.getCharacter())) {
						match = true;
					}
				}
				if (!match) {
					players.add(new Player(c, false));
					match = false;
				}
			}
		}

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
	 * by the direction.
	 * <p>
	 * Returns null if that is not an available direction such as off the board.
	 * 
	 * @param player
	 * @param direction
	 * @return
	 */
	private Location move(Player player, String direction) {
		Point point = player.getLocation().getPoint();

		// returns the location in the direction specified
		if (direction.equalsIgnoreCase("north")) {
			if (point.y == 0)
				return null;
			return locations[point.x][point.y - 1];
		}
		if (direction.equalsIgnoreCase("south")) {
			if (point.y == 24)
				return null;
			return locations[point.x][point.y + 1];
		}
		if (direction.equalsIgnoreCase("east")) {
			if (point.x == 24)
				return null;
			return locations[point.x + 1][point.y];
		}
		if (direction.equalsIgnoreCase("west")) {
			if (point.x == 0)
				return null;
			return locations[point.x - 1][point.y];
		}

		return null;

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
			Location nextLocation = move(player, move.substring(4).trim());
			if (nextLocation != null) {
				player.moveLocation(nextLocation);
				return 1;
			}
			System.out.println("That move is unavailable");
		}
		if (move.startsWith("door")) {
			for (Door d : player.getLocation().getDoors())
				if (d.toString().equals(move.substring(4).trim()))
					if (d.getFirstList().contains(player.getLocation())) {
						for (Location l : d.getSecondList())
							if (l.getCharacter() == null) {
								player.moveLocation(l);
								return 1;
							}
					} else {
						for (Location l : d.getFirstList())
							if (l.getCharacter() == null) {
								player.moveLocation(l);
								return 1;
							}
					}
			System.out.println("That door is not available");
		}
		if (move.startsWith("accuse")) {
			// TODO

			return 12;
			// checks if the player has won, return a special integer based on
			// outcome for playGame() to deal with
		}
		if (move.startsWith("suggest")) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				// Initates the suggestion
				// Reads in the
				System.out.println("Who do you think did it?");
				for (Character c : Character.toList())
					System.out.println("\t" + c.toString());
				Character suggestedCharacter = Character.toEnum(input
						.readLine());
				System.out.println("What did they use?");
				for (Weapon c : Weapon.toList())
					System.out.println("\t" + c.toString());
				Weapon suggestedWeapon = Weapon.toEnum(input.readLine());
				Room suggestedRoom = player.getLocation().getRoom();

				// Moves the suggested player to the current room
				if (suggestedRoom == null)
					throw new IllegalArgumentException();
				for (Player p : players) {
					if (p.getCharacter() == suggestedCharacter
							&& p.getLocation().getRoom() != suggestedRoom) {
						p.moveRoom(suggestedRoom);
						break;
					}
				}

				// Searchs through every player's hand. If a card is found that
				// refutes the suggestion then only one card will be displayed
				// (if the player has another card that can refute the
				// suggestion it will not be announced)
				boolean characterRefuted = false;
				boolean roomRefuted = false;
				boolean weaponRefuted = false;
				for (Player p : players) {
					if (p != player && p.isControlled()) {
						boolean foundSuggestion = false;
						if (!characterRefuted
								&& p.getCharacterCards().contains(
										suggestedCharacter)) {
							foundSuggestion = true;
							characterRefuted = true;
						}
						if (!foundSuggestion && !roomRefuted
								&& p.getRoomCards().contains(suggestedRoom)) {
							foundSuggestion = true;
							roomRefuted = true;
						}
						if (!foundSuggestion && !weaponRefuted
								&& p.getWeaponCards().contains(suggestedWeapon)) {
							weaponRefuted = true;
						}
					}
				}

				// Displays whether the suggestions were refuted or not.
				if (characterRefuted)
					System.out.printf("Character %s has been refuted\n",
							suggestedCharacter);
				else
					System.out.printf("Character %s was not refuted\n",
							suggestedCharacter);

				if (roomRefuted)
					System.out.printf("Room %s has been refuted\n",
							suggestedRoom);
				else
					System.out.printf("Room %s was not refuted\n",
							suggestedRoom);

				if (weaponRefuted)
					System.out.printf("Weapon %s has been refuted\n",
							suggestedWeapon);
				else
					System.out.printf("Weapon %s was not refuted\n",
							suggestedWeapon);
				// Returns 12 so that the player has no more moves left
				return 12;
			} catch (IllegalArgumentException e) {
				System.out
						.println("Sorry, that is not an option. Maybe you spelt it wrong.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out
						.println("Sorry, something seems to have gone wrong.");
				e.printStackTrace();
			}

			// returns 0 because an exception was thrown on the options
			// selected, granting the player another try
			return 0;
		}
		System.out.println("Your available moves are:\n");
		return 0;
	}

	public void drawBoard() {
		for (int i = 0; i < locations.length; i++) {
			System.out.print(" _");
		}
		System.out.println();
		for (int y = 0; y < locations.length; y++) {
			for (int x = 0; x < locations[0].length; x++) {
				if (x == 0) {
					System.out.printf("|%s ", locations[x][y].toString());
				} else if (x == locations.length - 1) {
					System.out.printf("%s|", locations[x][y].toString());
				} else {
					System.out.printf("%s ", locations[x][y].toString());
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Board board = new Board();
		board.playGame();
	}
}
