package JunitTests;

import Model.Model.BoardClasses.Board;
import Model.Model.PieceClasses.Pawn;
import Model.Model.PieceClasses.Piece;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTest {
    Board testBoard;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(true, false);
    }
    @Test
    public void testCanMove() throws Exception {
        Piece testPawn = testBoard.getChessBoardSquare(0,6).getPieceOnSquare();
        testBoard.getPieceOnSquare(0,6).move(0,4,testBoard);
        testBoard.getPieceOnSquare(0,4).move(0,3,testBoard);

        //Check if Has moved changes properly
        assertTrue("Pawn HasMoved not changing when it should!!", ((Pawn) testBoard.getPieceOnSquare(0, 3)).getHasMoved());
        //Check to see if the Pawn can't move when blocked
        testBoard.getPieceOnSquare(0,1).move(0,2,testBoard);
        assertFalse(testPawn.canMove(0,2,0,3,testBoard));

        //check to make sure that the pawn can attack diagonally and moves there
        testBoard.getPieceOnSquare(1,1).move(1,2,testBoard);
        assertTrue(testPawn.canMove(1, 2, 0, 3, testBoard));
        testPawn.move(1,2,testBoard);
        assertTrue(testBoard.getPieceOnSquare(0,2).canMove(0,3,0,2,testBoard));

        //Check to make sure you cannot move off the board
        assertFalse(( testBoard.getPieceOnSquare(0, 2)).move(-1, 3, testBoard));



    }
}
