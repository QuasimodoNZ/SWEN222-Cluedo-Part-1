import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Location {
	// Whether this location is in a room.
	private Room room;

	// Stores the door that is on this location, otherwise null
	private List<Door> doors;

	// The point that this location is on the 2d array from board
	private Point point;

	// If a character is on this location it will be stored here as well, it
	// needs to be changed back to null when the player moves
	private Board.Character character;

	public Location(Point p, Room r) {
		point = p;
		room = r;

		doors = new LinkedList<Door>();
	}

	/**
	 * Sets the character field so that the location can be linked to a
	 * location. When the player moves away, this should be set back to null.
	 * 
	 * @param c
	 *            Board.Character
	 */
	public void setCharacter(Board.Character c) {
		character = c;
	}

	/**
	 * Gets the character at this location. Returns null if there is no
	 * character.
	 * 
	 * @return Board.Character
	 */
	public Board.Character getCharacter() {
		return character;
	}

	/**
	 * Returns the point on the board that the location is based on, (0, 0) is
	 * at the top left of the board.
	 * 
	 * @return Point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * Gives this location access to the door that is passed in
	 */
	public void addDoor(Door d) {
		doors.add(d);
	}

	/**
	 * Returns a list of doors that the location has access to.
	 * 
	 * @return
	 */
	public List<Door> getDoors() {
		return doors;
	}

	/**
	 * This is what will be drawn on board at this locations grid point
	 */
	@Override
	public String toString() {
		if (character != null)
			return "" + character.toBoardString();
		else if (room != null)
			return "X";
		else if (doors.size() > 0)
			return "D";
		else
			return " ";
	}

	public Room getRoom() {
		return room;
	}
}
