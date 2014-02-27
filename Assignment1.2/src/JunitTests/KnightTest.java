package JunitTests;

import Model.Model.BoardClasses.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest {
    Board testBoard;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(true, false);
    }

    @Test
    public void testCanMove() throws Exception {
        //Move Pawn out to be killed
        assertTrue(testBoard.getPieceOnSquare(4, 1).move(4, 3, testBoard));

        //Test that Knight can move through units
        assertTrue(testBoard.getPieceOnSquare(6, 7).move(5, 5, testBoard));

        //Test that the Knight can kill a unit
        assertTrue(testBoard.getPieceOnSquare(5, 5).move(4, 3, testBoard));

    }
}
