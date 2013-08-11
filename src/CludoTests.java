import static org.junit.Assert.*;
import java.awt.Point;
import java.util.*;
import org.junit.Test;

public class CludoTests {

	@Test
	public void testVaildGetOptions() {
		Board b = createBoard();
		Player p = b.getPlayers().get(0);
		assertEquals(b.getOptions(p, 1), "Move East\nMove South\nEnd Turn\n");
	}

	@Test
	public void testInvaildGetOptions() {
		Board b = createBoard();
		Player p = b.getPlayers().get(0);
		assertFalse(b.getOptions(p, 1).equals(
				"Move North\nMove East\nMove South\nMove West\nEnd Turn\n"));
	}

	@Test
	public void testValidMove() {
		Board b = createBoard();
		Player p1 = b.getPlayers().get(0);
		Player p2 = b.getPlayers().get(1);
		Location moveLoc = new Location(new Point(6, 1), null);

		b.move(p1, "north");
		assertEquals(p1.getLocation(), moveLoc);
	}

	/**
	 * Creates a new Board, adds two players, creates two locations and adds
	 * them to a hallway
	 * 
	 * @return
	 */
	public Board createBoard() {
		Board b = new Board();
		Player p1 = new Player(Board.Character.ELEANOR_PEACOCK, true);
		Player p2 = new Player(Board.Character.JACOB_GREEN, true);
		Location p1Loc = new Location(new Point(6, 0), null);
		Location p2Loc = new Location(new Point(0, 9), null);
		p1.moveLocation(p1Loc);
		p2.moveLocation(p2Loc);
		List<Location> locList = new ArrayList<Location>();
		locList.add(p1Loc);
		locList.add(p2Loc);
		// Room hallway = new Room(Board.RoomName.HALLWAY, locList);
		return b;
	}

}
