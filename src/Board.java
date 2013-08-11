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
			if (name.equalsIgnoreCase("rope"))
				return ROPE;
			if (name.equalsIgnoreCase("candlestick"))
				return CANDLESTICK;
			if (name.equalsIgnoreCase("knife"))
				return KNIFE;
			if (name.equalsIgnoreCase("pistol"))
				return PISTOL;
			if (name.equalsIgnoreCase("baseball bat"))
				return BASEBALL_BAT;
			if (name.equalsIgnoreCase("dumbbell"))
				return DUMBBELL;
			if (name.equalsIgnoreCase("trophy"))
				return TROPHY;
			if (name.equalsIgnoreCase("poison"))
				return POISON;
			if (name.equalsIgnoreCase("axe"))
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
			case LIVING_ROOM:
				return "Living Room";
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

		public static RoomName toEnum(String name) {
			if (name.equalsIgnoreCase("spa"))
				return SPA;
			if (name.equalsIgnoreCase("theatre"))
				return THEATRE;
			if (name.equalsIgnoreCase("observatory"))
				return OBSERVATORY;
			if (name.equalsIgnoreCase("patio"))
				return PATIO;
			if (name.equalsIgnoreCase("swimming pool"))
				return SWIMMING_POOL;
			if (name.equalsIgnoreCase("hall"))
				return HALL;
			if (name.equalsIgnoreCase("kitchen"))
				return KITCHEN;
			if (name.equalsIgnoreCase("dining room"))
				return DINING_ROOM;
			if (name.equalsIgnoreCase("guest house"))
				return GUEST_HOUSE;

			throw new IllegalArgumentException();
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
				return "Eleanor Peacock";
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
			if (name.equalsIgnoreCase("kassandra scarlett"))
				return Character.KASSANDRA_SCARLETT;
			if (name.equalsIgnoreCase("jack mustard"))
				return Character.JACK_MUSTARD;
			if (name.equalsIgnoreCase("diana white"))
				return Character.DIANA_WHITE;
			if (name.equalsIgnoreCase("jacob green"))
				return Character.JACOB_GREEN;
			if (name.equalsIgnoreCase("eleanor peacock"))
				return Character.ELEANOR_PEACOCK;
			if (name.equalsIgnoreCase("victor plum"))
				return Character.VICTOR_PLUM;

			throw new IllegalArgumentException();
		}

		public String toBoardString() {
			switch (this) {
			case JACK_MUSTARD:
				return "Y";
			case KASSANDRA_SCARLETT:
				return "R";
			case DIANA_WHITE:
				return "W";
			case JACOB_GREEN:
				return "G";
			case ELEANOR_PEACOCK:
				return "B";
			case VICTOR_PLUM:
				return "P";
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
		players = new LinkedList<Player>();
	}

	public void startBoard() {
		// Asks user for the number of players
		System.out.println("Welcome to Cludo");
		System.out.println("Please enter the number of players (3-6)");
		Scanner inputReader = new Scanner(System.in);
		while (inputReader.hasNext()) {
			int num = inputReader.nextInt();
			inputReader.nextLine();
			// Checks for a valid number
			if (!(3 <= num && num <= 6)) {
				System.out.println("Invaild player range");
				continue;
			}
			// Adds human players
			System.out.println("Choose from these players:");
			for (Character c : Character.toList())
				System.out.println("\t" + c.toString());
			System.out.println();
			while (num > 0) {
				System.out.println("Enter Player " + num + "'s character:");
				if (addPlayer(inputReader.nextLine().toLowerCase(), true)) {
					num--;
				} else {
					System.out.println("Invalid Player Entry");
				}
			}
			break;
		}

		if (players.size() < 6) {
			// Adds the unused characters
			for (Character c : Character.toList()) {
				addPlayer(c.toString(), false);
			}
			System.out.println("Added Unused characters");
		}

		dealCards();

		locations = newBoard();
	}

	public boolean addPlayer(String name, boolean iC) {
		try {
			Character character = Character.toEnum(name);
			if (unselected(character)) {
				Player p = new Player(character, iC);
				players.add(p);
				System.out.println(character.name() + " Added");
				return true;
			} else {
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

	public void dealCards() {
		List<Weapon> weaponCards = Weapon.toList();
		List<RoomName> roomCards = RoomName.toList();
		for (int i = 0; i < roomCards.size(); i++)
			if (roomCards.get(i) == RoomName.SWIMMING_POOL) {
				roomCards.remove(i);
				break;
			}

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
		while (true)
			for (Player p : players)
				if (p.isControlled()) {
					if (weaponCards.size() > 0)
						p.getWeaponCards().add(weaponCards.remove(0));
					else if (characterCards.size() > 0)
						p.getCharacterCards().add(characterCards.remove(0));
					else if (roomCards.size() > 0)
						p.getRoomCards().add(roomCards.remove(0));
					else
						return;
				}

	}

	private Location[][] newBoard() {
		Location[][] board = new Location[27][29];

		// Initialise room to be null
		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board[0].length; y++)
				board[x][y] = new Location(new Point(x, y), null);

		// Intitialises all the rooms by marking the locations that are owned by
		// this room, marking the locations that the players will be on when in
		// the room and the doors within this room (not the passageways)

		// The Spa
		List<Location> roomLocations = new ArrayList<Location>(6);
		Room room = new Room(RoomName.SPA, roomLocations);
		for (int x = 0; x < 5; x++)
			for (int y = 0; y < 8; y++)
				board[x][y] = new Location(new Point(x, y), room);
		for (int y = 0; y < 6; y++)
			board[5][y] = new Location(new Point(5, y), room);

		for (int x = 2; x < 4; x++)
			for (int y = 2; y < 5; y++)
				roomLocations.add(board[x][y]);

		List<Location> firstList = new LinkedList<Location>();
		firstList.add(board[5][6]);
		List<Location> secondList = new LinkedList<Location>();
		secondList.addAll(board[4][5].getRoom().getLocations());
		new Door("spa door", firstList, secondList);

		// The Theatre and its locations
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.THEATRE, roomLocations);

		for (int x = 8; x < 13; x++)
			for (int y = 0; y < 8; y++)
				board[x][y] = new Location(new Point(x, y), room);

		firstList = new LinkedList<Location>();
		firstList.add(board[10][8]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[10][7].getRoom().getLocations());
		new Door("theatre door", firstList, secondList);

		// The Living Room
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.LIVING_ROOM, roomLocations);

		for (int x = 14; x < 20; x++)
			for (int y = 0; y < 8; y++)
				board[x][y] = new Location(new Point(x, y), room);
		for (int x = 15; x < 18; x++)
			board[x][8] = new Location(new Point(x, 9), room);

		for (int x = 15; x < 18; x++)
			for (int y = 3; y < 5; y++)
				roomLocations.add(board[x][y]);

		firstList = new LinkedList<Location>();
		firstList.add(board[16][9]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[16][8].getRoom().getLocations());
		new Door("living room door", firstList, secondList);

		// The Observatory
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.OBSERVATORY, roomLocations);
		for (int x = 22; x < 27; x++)
			for (int y = 0; y < 9; y++)
				board[x][y] = new Location(new Point(x, y), room);

		for (int x = 23; x < 25; x++)
			for (int y = 3; y < 6; y++)
				roomLocations.add(board[x][y]);

		firstList = new LinkedList<Location>();
		firstList.add(board[21][8]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[22][8].getRoom().getLocations());
		new Door("observatory door", firstList, secondList);

		// The Patio
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.PATIO, roomLocations);
		for (int x = 0; x < 4; x++)
			for (int y = 10; y < 19; y++)
				board[x][y] = new Location(new Point(x, y), room);
		for (int x = 4; x < 8; x++)
			for (int y = 11; y < 18; y++)
				board[x][y] = new Location(new Point(x, y), room);

		for (int x = 2; x < 5; x++)
			for (int y = 13; y < 15; y++)
				roomLocations.add(board[x][y]);

		firstList = new LinkedList<Location>();
		firstList.add(board[5][10]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[4][14].getRoom().getLocations());
		new Door("north patio door", firstList, secondList);
		firstList = new LinkedList<Location>();
		firstList.add(board[8][12]);
		new Door("northeast patio door", firstList, secondList);
		firstList = new LinkedList<Location>();
		firstList.add(board[8][16]);
		new Door("southeast patio door", firstList, secondList);
		firstList = new LinkedList<Location>();
		firstList.add(board[5][18]);
		new Door("south patio door", firstList, secondList);

		// The Swimming Pool
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.SWIMMING_POOL, roomLocations);
		for (int x = 10; x < 18; x++)
			for (int y = 11; y < 17; y++)
				board[x][y] = new Location(new Point(x, y), room);

		for (int x = 13; x < 16; x++)
			for (int y = 13; y < 15; y++)
				roomLocations.add(board[x][y]);

		firstList = new LinkedList<Location>();
		firstList.add(board[14][10]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[14][14].getRoom().getLocations());
		new Door("north swimming pool door", firstList, secondList);
		firstList = new LinkedList<Location>();
		firstList.add(board[10][17]);
		new Door("southwest swimming pool door", firstList, secondList);
		firstList = new LinkedList<Location>();
		firstList.add(board[17][17]);
		new Door("southeast swimming pool door", firstList, secondList);

		// The Hall
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.HALL, roomLocations);
		for (int x = 19; x < 27; x++)
			for (int y = 11; y < 18; y++)
				board[x][y] = new Location(new Point(x, y), room);

		for (int x = 22; x < 24; x++)
			for (int y = 14; y < 16; y++)
				roomLocations.add(board[x][y]);

		firstList = new LinkedList<Location>();
		firstList.add(board[22][10]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[22][14].getRoom().getLocations());
		new Door("north hall door", firstList, secondList);
		firstList = new LinkedList<Location>();
		firstList.add(board[18][14]);
		new Door("west hall door", firstList, secondList);

		// The Kitchen
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.KITCHEN, roomLocations);
		for (int x = 0; x < 7; x++)
			for (int y = 21; y < 29; y++)
				board[x][y] = new Location(new Point(x, y), room);
		board[6][21] = new Location(new Point(6, 21), null);

		for (int x = 2; x < 5; x++)
			for (int y = 24; y < 26; y++)
				roomLocations.add(board[x][y]);

		firstList = new LinkedList<Location>();
		firstList.add(board[6][21]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[6][22].getRoom().getLocations());
		new Door("kitchen door", firstList, secondList);

		// The Dining Room
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.DINING_ROOM, roomLocations);

		for (int x = 9; x < 17; x++)
			for (int y = 23; y < 29; y++)
				board[x][y] = new Location(new Point(x, y), room);
		for (int x = 10; x < 16; x++)
			for (int y = 19; y < 23; y++)
				board[x][y] = new Location(new Point(x, y), room);

		for (int x = 12; x < 14; x++)
			for (int y = 22; y < 25; y++)
				roomLocations.add(board[x][y]);

		firstList = new LinkedList<Location>();
		firstList.add(board[12][18]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[12][22].getRoom().getLocations());
		new Door("north dining room door", firstList, secondList);
		firstList = new LinkedList<Location>();
		firstList.add(board[16][21]);
		new Door("east dining room door", firstList, secondList);

		// The Guest House
		roomLocations = new ArrayList<Location>(6);
		room = new Room(RoomName.GUEST_HOUSE, roomLocations);

		for (int x = 20; x < 27; x++)
			for (int y = 20; y < 29; y++)
				board[x][y] = new Location(new Point(x, y), room);

		for (int x = 23; x < 25; x++)
			for (int y = 23; y < 26; y++)
				roomLocations.add(board[x][y]);

		board[20][20] = new Location(new Point(20, 20), null);

		firstList = new LinkedList<Location>();
		firstList.add(board[20][20]);
		secondList = new LinkedList<Location>();
		secondList.addAll(board[22][22].getRoom().getLocations());
		new Door("guest house door", firstList, secondList);

		// Secret passage way between the spa and guest house
		firstList = new LinkedList<Location>();
		firstList.addAll(board[4][5].getRoom().getLocations());
		secondList = new LinkedList<Location>();
		secondList.addAll(board[22][22].getRoom().getLocations());
		new Door("secret passageway", firstList, secondList);

		// Secret passage way between the observatory and kitchen
		firstList = new LinkedList<Location>();
		firstList.addAll(board[22][8].getRoom().getLocations());
		secondList = new LinkedList<Location>();
		secondList.addAll(board[6][22].getRoom().getLocations());
		new Door("secret passageway", firstList, secondList);

		for (Player p : players)
			switch (p.getCharacter()) {
			case JACK_MUSTARD:
				p.moveLocation(board[7][28]);
				break;
			case KASSANDRA_SCARLETT:
				p.moveLocation(board[18][28]);
				break;
			case DIANA_WHITE:
				p.moveLocation(board[0][19]);
				break;
			case JACOB_GREEN:
				p.moveLocation(board[0][9]);
				break;
			case ELEANOR_PEACOCK:
				p.moveLocation(board[6][0]);
				break;
			case VICTOR_PLUM:
				p.moveLocation(board[20][0]);
				break;
			}

		return board;
	}

	public String getOptions(Player player, int movesLeft) {
		// Gets the location of the current players character
		// Location location = player.getLocation();
		String options = "";
		Location loc = player.getLocation();
		Room room = loc.getRoom();
		if (room == null) {
			// The player is in the Hallway
			if (movesLeft > 0) {
				if (move(player, "north") != null) {
					options = options + "\tMove North\n";
				}
				if (move(player, "east") != null) {
					options = options + "\tMove East\n";
				}
				if (move(player, "south") != null) {
					options = options + "\tMove South\n";
				}
				if (move(player, "west") != null) {
					options = options + "\tMove West\n";
				}
			}
		}
		if (movesLeft > 0 && loc.getDoors() != null) {
			for (Door door : loc.getDoors())
				options += "\t" + door.toString() + "\n";

		}
		if (room != null) {
			// The player is inside another room
			if (room.toString().equals(RoomName.SWIMMING_POOL.toString()))
				options += "\tAccuse\n";
			else
				options += "\tSuggest\n";
		}
		options = options + "\tEnd Turn\n";
		return options;
	}

	/**
	 * Runs a game until a player wins
	 */
	public void playGame() {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(
				System.in));
		boolean showedCards = false;
		try {
			while (true) {

				for (Player player : players) {
					// only asks a human controlled player for input
					if (player.isControlled()) {

						if (!showedCards) {
							System.out
									.println("Your cards are:\n\n- Characters:");
							for (Character c : player.getCharacterCards())
								System.out.println("\t" + c.toString());

							System.out.println("\n- Rooms:");
							for (RoomName r : player.getRoomCards())
								System.out.println("\t" + r.toString());
							System.out.println("\n- Weapons:");
							for (Weapon w : player.getWeaponCards())
								System.out.println("\t" + w.toString());

						}
						// Rolls the dice
						int movesLeft = 2 + (int) (Math.random() * 11);
						while (movesLeft >= 0) {
							drawBoard();
							System.out
									.printf("%s turn with %d turns left\nYour available moves are:\n",
											player.toString(), movesLeft);
							System.out.println(getOptions(player, movesLeft));
							String move = inputReader.readLine();
							movesLeft -= playTurn(player, move);
							// }
						}
					}
				}
				showedCards = true;
			}
		} catch (GameWonException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.print("The inputReader crashed");
			e.printStackTrace();
		}
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
	public Location move(Player player, String direction) {
		Point point = player.getLocation().getPoint();

		// returns the location in the direction specified
		if (direction.equalsIgnoreCase("north")) {
			if (point.y == 0)
				return null;
			Location next = locations[point.x][point.y - 1];
			return (next.getRoom() != null) ? null : next;
		}
		if (direction.equalsIgnoreCase("south")) {
			if (point.y == locations[0].length - 1)
				return null;
			Location next = locations[point.x][point.y + 1];
			return (next.getRoom() != null) ? null : next;

		}
		if (direction.equalsIgnoreCase("east")) {
			if (point.x == locations.length - 1)
				return null;
			Location next = locations[point.x + 1][point.y];
			return (next.getRoom() != null) ? null : next;

		}
		if (direction.equalsIgnoreCase("west")) {
			if (point.x == 0)
				return null;
			Location next = locations[point.x - 1][point.y];
			return (next.getRoom() != null) ? null : next;

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
	private int playTurn(Player player, String move) throws GameWonException {
		if (move.equalsIgnoreCase("end turn"))
			return 12;
		if (move.startsWith("move")) {
			Location nextLocation = move(player, move.substring(4).trim());
			if (nextLocation != null) {
				player.moveLocation(nextLocation);
				return 1;
			}
			System.out.println("That move is unavailable");
		}
		if (move.startsWith("accuse")
				&& player.getLocation().getRoom().toString()
						.equals("Swimming Pool")) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));

			try {
				System.out.println("Who do you think did it?");
				for (Character c : Character.toList())
					System.out.println("\t" + c.toString());
				Character character = Character.toEnum(input.readLine());

				System.out.println("What did they use?");
				for (Weapon c : Weapon.toList())
					System.out.println("\t" + c.toString());
				Weapon weapon = Weapon.toEnum(input.readLine());

				System.out.println("Where did they do it?");
				for (RoomName c : RoomName.toList())
					System.out.println("\t" + c.toString());
				RoomName room = RoomName.toEnum(input.readLine());

				if (room == roomSolution && weapon == weaponSolution
						&& character == characterSolution) {
					throw new GameWonException(player.toString()
							+ " has won the game!");
				} else {
					System.out
							.println(player.toString()
									+ " made a wrong accusation and has been kicked from the game");
					player.setControl(false);

					int numOfPlayersLeft = 0;
					Player winningPlayer = null;
					for (Player p : players)
						if (p.isControlled()) {
							numOfPlayersLeft++;
							winningPlayer = p;
						}
					if (numOfPlayersLeft == 1)
						throw new GameWonException(winningPlayer.toString()
								+ " has won the game!");

					System.out.println("These are their cards\nCharacter's\n");
					for (Character c : player.getCharacterCards())
						System.out.println(c.toString());
					System.out.println("Room's\n");
					for (RoomName rn : player.getRoomCards())
						System.out.println(rn.toString());
					System.out.println("Weapon's\n");
					for (Weapon w : player.getWeaponCards())
						System.out.println(w.toString());
				}
			} catch (IOException e) {
				System.out.println("Sorry, something has gone wrong.");
				// e.printStackTrace();
				return 0;
			} catch (IllegalArgumentException e) {
				System.out
						.println("Sorry, that is not an option. Maybe you spelt it wrong.");
				// e.printStackTrace();
				return 0;
			}

			return 12;
			// checks if the player has won, return a special integer based on
			// outcome for playGame() to deal with
		}
		if (move.startsWith("suggest")
				&& player.getLocation().getRoom() != null) {
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

		for (Door d : player.getLocation().getDoors())
			if (d.toString().equals(move.trim()))
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
		System.out.println("Your available moves are:\n");
		return 0;
	}

	public void drawBoard() {
		String s = "";
		for (int i = 0; i < locations.length; i++) {
			s += " _";
		}
		s += "\n";
		for (int y = 0; y < locations[0].length; y++) {
			for (int x = 0; x < locations.length; x++) {
				if (x == 0) {
					s += String.format("|%s ", locations[x][y].toString());
				} else if (x == locations.length - 1) {
					s += String.format("%s|", locations[x][y].toString());
				} else {
					s += String.format("%s ", locations[x][y].toString());
				}
			}
			s += "\n";
		}
		System.out.println(s);
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public static void main(String[] args) {
		Board board = new Board();
		board.startBoard();
		board.playGame();
	}

	private class GameWonException extends Exception {
		public GameWonException() {
			super();
		}

		public GameWonException(String message) {
			super(message);
		}
	}
}
