package assignment10.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import  org.junit.AfterClass;

public class BoardTest {

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	@Before
	public void setUpStreams()
	{
		System.setOut(new PrintStream(out));		
	}
	
	@Test
	public void testBoard_move() {
		Board test = new Board(8);
		test.Board_move(3, 3, test.white_pawn_1);
		assertEquals("Invalid move_1\n", out.toString());
	}

	@Test
	public void testBoard_can_move() {
		Board test = new Board(8);
		boolean result = test.Board_can_move(0, 4, Board.white_pawn_1);
		assertEquals(true, result); //white pawn can move
		boolean result_3 = test.Board_can_move(0, 5, Board.black_pawn_1);
		assertEquals(false, result_3); //white should move first
		boolean result_2 = test.Board_can_move(0, 5, Board.white_rook_1);
		assertEquals(false, result_2); //rook cannot over leap
		boolean result_4 = test.Board_can_move(2, 5, Board.white_knight_1);
		assertEquals(true, result_4); //knight can over leap
		boolean result_5 = test.Board_can_move(10, 10, Board.white_knight_1);
		assertEquals(false, result_5); //out of the board
		
	}

	@Test
	public void testWin() {
		Board test = new Board(8);
		test.Board_move(0, 4, test.white_pawn_1);
		test.Board_move(4, 3, test.black_pawn_5);
		test.Board_move(0, 5, test.white_rook_1);
		test.Board_move(4, 5, test.black_pawn_5);
		test.Board_move(4, 5, test.white_rook_1);
		test.Board_move(0, 2, test.black_pawn_1);
		test.Board_move(4, 0, test.white_rook_1);
		assertEquals("White Wins!\n", out.toString());
	}

	@After
	public void cleanUpStreams()
	{
		System.setOut(null);
	}
}
