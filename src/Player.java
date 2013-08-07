import java.util.*;

public class Player {
	private Board.Character character;
	private Location location;
	private List<Board.Weapon> weaponCards;
	private List<Board.RoomName> roomCards;
	private List<Board.Character> characterCards;

	public Player(Board.Character character) {
		this.character = character;
		this.weaponCards = new LinkedList<Board.Weapon>();
		this.roomCards = new LinkedList<Board.RoomName>();
		this.characterCards = new LinkedList<Board.Character>();

	}

	/**
	 * Returns the location that this player is on
	 * 
	 * @return Location
	 */
	public Location getLocation() {
		return location;
	}

	public Board.Character getCharacter() {
		return character;
	}

	public List<Board.Weapon> getWeaponCards() {
		return weaponCards;
	}

	public List<Board.RoomName> getRoomCards() {
		return roomCards;
	}

	public List<Board.Character> getCharacterCards() {
		return characterCards;
	}

	public String presentOptions(String options) {
		// TODO Finish Method
		String option = null;
		return option;
	}

	/**
	 * Removes its current location's reference to the player and moves it to
	 * the specified location
	 * 
	 * @param nextLocation
	 */
	public void moveLocation(Location nextLocation) {
		location.setCharacter(null);
		location = nextLocation;
	}
}
