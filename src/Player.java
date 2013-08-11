import java.util.*;

public class Player {
	private Board.Character character;
	private Location location = null;
	private List<Board.Weapon> weaponCards;
	private List<Board.RoomName> roomCards;
	private List<Board.Character> characterCards;
	private boolean isControlled;

	public Player(Board.Character character, boolean iC) {
		this.character = character;
		this.weaponCards = new LinkedList<Board.Weapon>();
		this.roomCards = new LinkedList<Board.RoomName>();
		this.characterCards = new LinkedList<Board.Character>();
		isControlled = iC;
	}

	/**
	 * Returns whether this player is controlled by a player or not.
	 * 
	 * @return
	 */
	public boolean isControlled() {
		return isControlled;
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

	/**
	 * Removes its current location's reference to the player and moves it to
	 * the specified location
	 * 
	 * @param nextLocation
	 */
	public void moveLocation(Location nextLocation) {
		if (location != null)
			location.setCharacter(null);
		location = nextLocation;
		nextLocation.setCharacter(character);
	}

	public void moveRoom(Room suggestedRoom) {
		for (Location l : suggestedRoom.getLocations()) {
			if (l.getCharacter() == null) {
				moveLocation(l);
				return;
			}
		}
	}

	/**
	 * Sets the state of whether this player is controlled or not.
	 * 
	 * @param b
	 */
	public void setControl(boolean b) {
		isControlled = false;
	}

	public String toString() {
		return character.toString();
	}
}
