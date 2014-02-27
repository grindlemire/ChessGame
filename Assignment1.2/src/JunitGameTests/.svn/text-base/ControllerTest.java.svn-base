package JunitGameTests;

import Model.Model.BoardClasses.Board;
import GUI.SquareButton;
import Model.Model.PieceClasses.Piece;
import Controller.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;


public class ControllerTest {
    private Board testBoard;

    @Before
    public void setUp() throws Exception {
        new Controller();
        testBoard = Controller.getBoard();

    }

    /**
     * Make sure that the turns go back and forth
     * @throws Exception
     */
    @Test
    public void testNextTurn() throws Exception {

        Controller.nextTurn();
        assertTrue(Controller.getTurn() == 1);
        Controller.nextTurn();
        assertTrue(Controller.getTurn() == 0);
        Controller.nextTurn();
        assertTrue(Controller.getTurn() == 1);

    }

    /**
     * Tests if the save state can take back a move and take back an attack
     * @throws Exception
     */
    @Test
    public void testSaveState() throws Exception {
        Piece testPiece = testBoard.getPieceOnSquare(3,6);
        Piece testPieceB = testBoard.getPieceOnSquare(4,1);
        SquareButton buttonA  = Controller.getSquareButton(3, 6);
        SquareButton buttonB = Controller.getSquareButton(3,4);
        SquareButton buttonC = Controller.getSquareButton(4,3);
        //Save the state of the piece moving
        Controller.saveState(buttonA,buttonB);
        testPiece.move(3,4,testBoard);
        assertTrue(!Controller.getSquareWithButton(buttonA).isOccupied());
        assertTrue(Controller.getSquareWithButton(buttonB).isOccupied());
        //return to save state
        Controller.returnToSavedState();
        assertTrue(Controller.getSquareWithButton(buttonA).isOccupied());
        assertTrue(!Controller.getSquareWithButton(buttonB).isOccupied());
 
        testPiece.move(3,4,testBoard);
        testPieceB.move(4,3, testBoard);
        //save state and attack the black pawn
        Controller.saveState(buttonB, buttonC);
        testPiece.move(4,3,testBoard);
        //check to make sure that one pawn ate the other
        assertTrue(!Controller.getSquareWithButton(buttonB).isOccupied());
        assertTrue(Controller.getSquareWithButton(buttonC).isOccupied());
        assertTrue(Controller.getSquareWithButton(buttonC).getTeamOnSquare().equals("white"));

        //check to make sure after the return to saved state everything is back to normal
        Controller.returnToSavedState();
        assertTrue(Controller.getSquareWithButton(buttonB).isOccupied());
        assertTrue(Controller.getSquareWithButton(buttonC).isOccupied());
        assertTrue(Controller.getSquareWithButton(buttonC).getTeamOnSquare().equals("black"));
        assertTrue(Controller.getSquareWithButton(buttonB).getTeamOnSquare().equals("white"));




    }

    @Test
    /**
     * A function that tests whether all the squares have the correct color
     */
    public void testCorrectSquareColor() throws Exception {
       Color white1 = new Color(255,200,155);
       Color black1 = new Color(131,58,0);

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
            {
                if((i%2==0 && j%2==0) || (i%2!=0 && j%2!=0))
                {
                    assertTrue(Controller.getSquareButton(i, j).getBackground().equals(white1));
                }
                else
                {
                    assertTrue(Controller.getSquareButton(i, j).getBackground().equals(black1));
                }
            }

    }

    @Test
    /**
     * A function that tests that forfeit game works correctly
     */
    public void testForfeitGame() throws Exception {

        Controller.forfeitGame("white");

        assertTrue(Controller.getScore("white")==0);
        assertTrue(Controller.getScore("black")==1);

        Controller.forfeitGame("black");

        assertTrue(Controller.getScore("black")==1);
        assertTrue(Controller.getScore("white")==1);
    }

    @Test
    /**
     * A function that tests that a new game resets things
     */
    public void testMakeNewGame() throws Exception {
        Controller.makeNewGame();
        assertNull(Controller.getInitialSquare());
        assertEquals(0, Controller.getTurn());
    }

    @Test
    /**
     * A function that tests whether the setPause is working correctly
     */
    public void testSetPause() throws Exception{
        Controller.setPause();
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                assertFalse(Controller.getSquareButton(i,j).isEnabled());
        assertTrue(Controller.getWindow().getGlass(false).isVisible());

    }

    @Test
    /**
     * A function that tests whether the EndGame slate is working correctly
     */
    public  void testSetEndGame() throws Exception{
        Controller.getWindow().makeEndGameScreen("white");
        Controller.setEndGame();
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                assertFalse(Controller.getSquareButton(i,j).isEnabled());
        assertTrue(Controller.getWindow().getGlass(true).isVisible());
    }


}
