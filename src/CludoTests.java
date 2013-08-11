import static org.junit.Assert.*;

import org.junit.Test;

public class CludoTests {

	@Test
	public void testGetOptions() {
		Board b = new Board();
		Player p = new Player(Board.Character.JACK_MUSTARD, true);
		assertEquals(b.getOptions(p, 1), "Move North\nMove East\nEnd Turn\n");
	}

}
