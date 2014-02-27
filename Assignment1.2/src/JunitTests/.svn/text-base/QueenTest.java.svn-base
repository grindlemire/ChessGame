package JunitTests;

import Model.Model.BoardClasses.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class QueenTest {
    Board testBoard;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(true, false);
    }
    @Test
    public void testCanMove() throws Exception {
        //Move Pawn out of way and other pawn out
        testBoard.getPieceOnSquare(3,6).move(3,4,testBoard);
        testBoard.getPieceOnSquare(4,6).move(4,4,testBoard);
        testBoard.getPieceOnSquare(5,1).move(5,2,testBoard);
        testBoard.getPieceOnSquare(7,1).move(7,3,testBoard);

        //Test to make sure Queen cannot go through units
        assertFalse(testBoard.getPieceOnSquare(3,7).move(3,3,testBoard));

        //Test to make sure Queen can move normally in both diagonal and column/row
        assertTrue(testBoard.getPieceOnSquare(3,7).move(3,5,testBoard));
        assertTrue(testBoard.getPieceOnSquare(3,5).move(3,7,testBoard));

        //Test to see if can move diagonally and kill (and put in check at same time)
        assertTrue(testBoard.getPieceOnSquare(3,7).move(7,3,testBoard));
    }
}
