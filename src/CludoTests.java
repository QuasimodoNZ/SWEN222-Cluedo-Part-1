import static org.junit.Assert.*;
import java.awt.Point;
import java.io.*;
import java.util.*;

import org.junit.Test;

public class CludoTests {

	@Test
	public void testVaildAddPlayer() {
		Board b = new Board();
		assertEquals(b.addPlayer("Eleanor Peacock", true), true);
	}

	@Test
	public void testInvaildAddPlayer1() {
		Board b = new Board();
		assertEquals(b.addPlayer("jack", true), false);
	}

	@Test
	public void testInvaildAddPlayer2() {
		Board b = new Board();
		b.addPlayer("jack mustard", true);
		assertEquals(b.addPlayer("jack mustard", true), false);
	}

	@Test
	public void testVaildGetPlayer() {
		Board b = new Board();
		b.addPlayer("Eleanor Peacock", true);
		Player p = b.getPlayers().get(0);
		assertEquals(b.getPlayers().get(0).toString(), new Player(
				Board.Character.ELEANOR_PEACOCK, true).toString());
	}

}
