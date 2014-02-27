package JunitTests;

import static org.junit.Assert.*;

import Model.Model.PieceClasses.Chancellor;
import Model.Model.PieceClasses.Pawn;
import org.junit.Before;
import org.junit.Test;
import Model.Model.BoardClasses.*;


public class ChancellorTest {
    private Board testBoard;
    private Chancellor testChancellor;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(false, false);
        testChancellor = new Chancellor(4,4,"black");

        Pawn testPawn = new Pawn(3,2,"white");
        Pawn testPawn1 = new Pawn(4,0,"white");

        testBoard.placePiece(4,4,testChancellor,"black");
        testBoard.placePiece(3,2,testPawn,"white");
        testBoard.placePiece(4,0,testPawn1,"white");

    }

    @Test
    public void testCanMove() throws Exception {
        assertTrue(testBoard.getPieceOnSquare(4,4) instanceof Chancellor);

        //Try making the archbishop move like a knight
        assertTrue(testChancellor.move(3, 2, testBoard));
        assertTrue(testChancellor.move(4, 4, testBoard));

        //make sure that it ate a unit
        assertFalse(testBoard.getChessBoardSquare(3, 2).isOccupied());

        //Make sure can move like a Rook
        assertTrue(testChancellor.move(4,0, testBoard));

        //Test to make sure that it cannot move off the board
        assertFalse(testChancellor.move(4,8, testBoard));

        //Test to make sure cannot move like a Bishop
        assertFalse(testChancellor.move(7,3, testBoard));

    }
}
