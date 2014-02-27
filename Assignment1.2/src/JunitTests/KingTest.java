package JunitTests;

import Model.Model.BoardClasses.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KingTest {
    Board testBoard;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(true, false);
    }

    @Test
    public void testCanMove() throws Exception {
        //Move the pawn out of the way and get enemy pawn
        testBoard.getPieceOnSquare(4,6).move(4,4,testBoard);
        testBoard.getPieceOnSquare(5,1).move(5,3,testBoard);
        testBoard.getPieceOnSquare(5,3).move(4,4,testBoard);

        //Make sure king can move up
        assertTrue(testBoard.getPieceOnSquare(4, 7).move(4, 6, testBoard));

        //Make sure king cant move into check
        assertFalse(testBoard.getPieceOnSquare(4, 6).move(5, 5, testBoard));

        //Make sure King can kill a unit
        assertTrue(testBoard.getPieceOnSquare(4,6).move(4,5,testBoard));
    }
}
