import java.util.LinkedList;
import java.util.List;

public class Room {
	private Board.RoomName name;
	private List<Location> availableLocations;

	public Room(Board.RoomName rn, List<Location> locations) {
		name = rn;
		availableLocations = locations;
	}

	/**
	 * Returns a list of the available location in this room.
	 * <p>
	 * These locations are NOT all the locations that make up this room on the
	 * board but the locations that, if a player is in a room, then he should be
	 * on one of these locations.
	 * <p>
	 * These will ideally be in the center of the room.
	 * 
	 * @return List<Location>
	 */
	public List<Location> getLocations() {
		return availableLocations;
	}

	/**
	 * Returns the name of this room.
	 */
	@Override
	public String toString() {
		return name.toString();
	}
}
