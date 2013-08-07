public class Player {
	private Board.Character character;
	private Location location;

	public Player(Board.Character character) {
		this.character = character;

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
