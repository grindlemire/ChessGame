package JunitGameTests;

import static org.junit.Assert.*;

import Controller.Controller;
import org.junit.Before;
import org.junit.Test;
import Model.Model.BoardClasses.*;

import java.awt.*;


public class InitTest {
    private Board testBoard = new Board(true, false);
    Controller theGame;
    @Before
    public void setUp() throws Exception {
        theGame = new Controller();
        //testBoard.getPieceOnSquare(4,5);



    }

    @Test
    /**
     * A function that tests the initialization of the window instantiates everything
     * necessary
     */
    public void InitTester() throws Exception {
        //At this point due to the Constructor the chessboard should look like this: testCtor

        assertTrue(testBoard.getPieceOnSquare(4,6).move(4, 4, testBoard));
        //If you uncommment this line it should move one white pawn out two spaces as shown: Image Move

       assertTrue(testBoard.getPieceOnSquare(3,1).move(3, 3, testBoard));
       assertTrue(testBoard.getPieceOnSquare(3,3).move(4, 4, testBoard));
        //If you uncomment these two lines it should show one pawn eating another as shown in Image Attack

        //test that all the Buttons are instantiated
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                assertNotNull(Controller.getSquareButton(i,j));

        //Make sure the pause screen initialized
        assertNotNull(Controller.getWindow().getGlass(false));


    }

    @Test
    /**
     * A function that makes sure the resetBoard function completely resets all the colors of the bioard
     */
    public void testResetBoard() {

        for(int i=0; i<8; i++)
        {

            for(int j=0; j<8; j++)
            {
                assertTrue(Controller.getSquareButton(i, j).getBackground().equals(Controller.getSquareButton(i, j).getCorrectColor(i, j)));
                assertTrue(Controller.getSquareButton(i, j).isOpaque());

            }
        }
    }

    @Test
    /**
     * A function that switches the color passed to it
     */
    public void testSwitchColors(){
        Color black = new Color(131,58,0);
        Color white = new Color(255,200,155);

        assertTrue(Controller.getWindow().switchColors(white).equals(black));
        assertTrue(Controller.getWindow().switchColors(black).equals(white));
        assertNull(Controller.getWindow().switchColors(new Color(1,1,1)));
    }



}