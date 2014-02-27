package JunitTests;

import Model.Model.BoardClasses.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BishopTest {
    Board testBoard;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(true, false);
    }

    @Test
    public void testCanMove() throws Exception {
        //Move Pawns out
        testBoard.getPieceOnSquare(4,6).move(4,4,testBoard);
        testBoard.getPieceOnSquare(2,6).move(2,4,testBoard);
        testBoard.getPieceOnSquare(1,1).move(1,3,testBoard);

        //Test to see if bishop can move bascially
        assertTrue(testBoard.getPieceOnSquare(5, 7).move(3, 5, testBoard));

        //Test to see if bishop can move through units
        assertFalse(testBoard.getPieceOnSquare(3,5).move(2, 4, testBoard));
        assertFalse(testBoard.getPieceOnSquare(3,5).move(1, 3, testBoard));

        //Move blocking white pawn ahead one
        testBoard.getPieceOnSquare(2,4).move(2,3,testBoard);

        //Test to see if bishop can take units
        assertTrue(testBoard.getPieceOnSquare(3, 5).move(1, 3, testBoard));

    }
}
