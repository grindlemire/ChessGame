package JunitTests;

import Model.Model.BoardClasses.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class RookTest {
    Board testBoard;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(true, false);
    }


    @Test
    public void testCanMove() throws Exception {
        //Move pawn 3 out
        testBoard.getPieceOnSquare(0,6).move(0,4,testBoard);
        testBoard.getPieceOnSquare(0,4).move(0,3,testBoard);

        //Checks to see if Rook can move through own units
        assertFalse(testBoard.getPieceOnSquare(0, 7).move(0, 2, testBoard));

        //Move black pawn out one
        testBoard.getPieceOnSquare(0,1).move(0,2,testBoard);

        //Check to see if Rook can move through all units
        assertFalse(testBoard.getPieceOnSquare(0, 7).move(0, 1, testBoard));
        assertFalse(testBoard.getPieceOnSquare(0, 7).move(0, 2, testBoard));

        //Check to see if Rook can move regularly in collumns and rows
        assertTrue(testBoard.getPieceOnSquare(0, 7).move(0, 4, testBoard));
        assertTrue(testBoard.getPieceOnSquare(0, 4).move(7, 4, testBoard));

        //Check to make sure the Rook can't move off the board
        assertFalse(testBoard.getPieceOnSquare(7, 4).move(8, 4, testBoard));

    }
}
