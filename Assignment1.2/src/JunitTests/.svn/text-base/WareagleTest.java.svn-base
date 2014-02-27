package JunitTests;

import static org.junit.Assert.*;

import Model.Model.PieceClasses.Pawn;
import Model.Model.PieceClasses.Wareagle;
import org.junit.Before;
import org.junit.Test;
import Model.Model.BoardClasses.*;


public class WareagleTest {
    private Board testBoard;
    private Wareagle testWareagle;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(false, false);
        testWareagle = new Wareagle(4,4,"black");

         Pawn testPawn = new Pawn(3,2,"white");
         Pawn testPawn1 = new Pawn(1,1,"white");

        testBoard.placePiece(4,4, testWareagle,"black");
        testBoard.placePiece(4,3,testPawn,"white");
        testBoard.placePiece(4,2,testPawn1,"white");


    }

    @Test
    public void testCanMove() throws Exception {
        assertTrue(testBoard.getPieceOnSquare(4,4) instanceof Wareagle);

        //Try making the wareagle move
        assertTrue(testWareagle.move(4, 3, testBoard));
        assertTrue(testWareagle.move(4, 5, testBoard));

        //make sure that it ate a unit
        assertFalse(testBoard.getChessBoardSquare(3, 2).isOccupied());

        //Make sure can't kill beyond one square away
        assertFalse(testWareagle.move(4, 2, testBoard));

        //Test to make sure that it cannot move off the board
        assertFalse(testWareagle.move(4, 8, testBoard));

        //Test to make sure it cant move more than 3 in a direction
        assertFalse(testWareagle.move(0,5, testBoard));



    }
}
