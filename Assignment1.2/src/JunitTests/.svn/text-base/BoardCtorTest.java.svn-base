package JunitTests;

import static org.junit.Assert.*;

import Model.Model.BoardClasses.Board;
import Model.Model.PieceClasses.*;
import org.junit.Before;
import org.junit.Test;


public class BoardCtorTest {
    Board testBoard;

    @Before
    public void setUp() throws Exception {
        testBoard = new Board(true, false);
    }


    @Test
    /**
     * A function that tests whether the board was initialized properly
     */
    public void ctorTester()
    {

        //Go through the whole board and make sure all squares properly initialized and hold right piece
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                assertNotNull(testBoard.getChessBoardSquare(i,j));
                assertEquals(testBoard.getChessBoardSquare(i,j).getPosition().getX(), i);
                assertEquals(testBoard.getChessBoardSquare(i,j).getPosition().getY(), j);

                if(j==1)
                {
                    assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Pawn);
                    assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("black"));
                    assertFalse(((Pawn) testBoard.getPieceOnSquare(i, j)).getHasMoved());
                }
                else if(j==6)
                {
                    assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Pawn);
                    assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("white"));
                }
                else if(j==0)
                {
                    if(i==0 || i==7)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Rook);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("black"));
                    }
                    if(i==1 || i==6)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Knight);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("black"));
                    }
                    if(i==2 || i==5)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Bishop);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("black"));
                    }
                    if(i==4)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof King);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("black"));
                    }
                    if(i==3)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Queen);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("black"));
                    }
                }
                else if(j==7)
                {
                    if(i==0 || i==7)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Rook);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("white"));
                    }
                    if(i==1 || i==6)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Knight);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("white"));
                    }
                    if(i==2 || i==5)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Bishop);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("white"));
                    }
                    if(i==4)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof King);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("white"));
                    }
                    if(i==3)
                    {
                        assertTrue(testBoard.getPieceOnSquare(i,j) instanceof Queen);
                        assertTrue(testBoard.getChessBoardSquareGetTeamOnSquare(i,j).equals("white"));
                    }
                }
            }
        }
        //Check to make sure that the arrays of the teams have the correct number
        assertTrue(testBoard.getTeamArray("white").size()==16);
        assertTrue(testBoard.getTeamArray("black").size()==16);

    }

    @Test
    /**
     * A function that tests whether the fairy pieces are being placed correctly
     */
    public void testPlaceAllPieces(){
        Board specialTestBoard = new Board(true, true);

        assertTrue(specialTestBoard.getPieceOnSquare(4,5) instanceof Chancellor);
        assertTrue(specialTestBoard.getChessBoardSquareGetTeamOnSquare(4,5).equals("white"));

        assertTrue(specialTestBoard.getPieceOnSquare(4,2) instanceof Chancellor);
        assertTrue(specialTestBoard.getChessBoardSquareGetTeamOnSquare(4,2).equals("black"));

        assertTrue(specialTestBoard.getPieceOnSquare(3,5) instanceof Wareagle);
        assertTrue(specialTestBoard.getChessBoardSquareGetTeamOnSquare(4,5).equals("white"));

        assertTrue(specialTestBoard.getPieceOnSquare(3,2) instanceof Wareagle);
        assertTrue(specialTestBoard.getChessBoardSquareGetTeamOnSquare(4,2).equals("black"));

        //Check to make sure that the arrays of the teams have the correct number
        assertTrue(specialTestBoard.getTeamArray("white").size()==18);
        assertTrue(specialTestBoard.getTeamArray("black").size()==18);
    }


    @Test
    /**
     * A function that tests whether a team occupies the square or its an empty square
     */
    public void testOtherTeamOccupiesOrEmpty() {
        //If white looks at black piece
        assertTrue(testBoard.otherTeamOccupiesOrEmpty(3,1,"white"));
        //If white looks at white piece
        assertFalse(testBoard.otherTeamOccupiesOrEmpty(3, 6, "white"));
        //If you look off the board
        assertFalse(testBoard.otherTeamOccupiesOrEmpty(3, 8, "white"));
        //If the String is bad
        assertFalse(testBoard.otherTeamOccupiesOrEmpty(3, 1, "helloworld"));
    }

    @Test
    /**
     * tests to see if a piece has a direct line of sight to a square in a column
     * or row
     */
    public void testIsCollumnRow() {
        //test the case where direct line of sight and a piece there
        assertTrue(testBoard.isColumnRow(4, 1, 4, 6));
        //test the case where direct line of sight and not a piece there
        assertTrue(testBoard.isColumnRow(4, 2, 4, 6));
        //test the case where not direct line of sight
        assertFalse(testBoard.isColumnRow(4, 1, 4, 7));
        //test the case where target off board
        assertFalse(testBoard.isColumnRow(4, 8, 4, 7));
        //test the case where source off the board
        assertFalse(testBoard.isColumnRow(4, 1, 4, 8));
        //test the case where not same column or row
        assertFalse(testBoard.isColumnRow(5, 1, 4, 7));

    }

    @Test
    /**
     * tests to see if a piece has a direct line of sight to a square in a column
     * or row
     */
    public void testIsDiagonal() {
        //test the case where direct line of sight and a piece there
        assertTrue(testBoard.isDiagonal(1, 1, 6, 6));
        //test the case where direct line of sight and not a piece there
        assertTrue(testBoard.isDiagonal(2, 2, 6, 6));
        //test the case where not direct line of sight
        assertFalse(testBoard.isDiagonal(1, 1, 7, 7));
        //test the case where target off board
        assertFalse(testBoard.isDiagonal(-1, -1, 6, 6));
        //test the case where source off the board
        assertFalse(testBoard.isDiagonal(1, 1, 8, 8));
        //test the case where not same column or row
        assertFalse(testBoard.isDiagonal(5, 1, 4, 7));

    }

    @Test
    /**
     * A function that tests to see if checkmate works correctly
     */
    public void testCheckMate() {
        Board theBoard = new Board(false, false);
        Piece king = new King(4,0,"white");
        theBoard.setKingSquare(4,0, "white");
        theBoard.setKing(true, "white");
        Piece rook1 = new Rook(0,0,"black");
        Piece rook2 = new Rook(0,1,"black");
        theBoard.getTeamArray("white").add(king);
        theBoard.getTeamArray("black").add(rook1);
        theBoard.getTeamArray("black").add(rook2);

        theBoard.placePiece(0,0,rook1,"black");
        theBoard.placePiece(0,1,rook2,"black");
        theBoard.placePiece(4,0,king,"white");


        assertTrue(theBoard.isCheckMate("white"));
        assertFalse(theBoard.isCheckMate("black"));

        assertFalse(testBoard.isCheckMate("white"));
        assertFalse(testBoard.isCheckMate("black"));
        //For more tests see the manual test for the GUI
    }

    @Test
    /**
     * A function that tests whether a piece is making check with its move
     */
    public void testMakesCheck() {
        Board theBoard = new Board(false, false);
        Piece king = new King(4,0,"white");
        theBoard.setKingSquare(4,0, "white");
        theBoard.setKing(true, "white");

        Piece rook1 = new Rook(0,4,"black");
        Piece rook2 = new Rook(1,7,"black");

        theBoard.placePiece(0,4,rook1,"black");
        theBoard.placePiece(1,7,rook2,"black");
        theBoard.placePiece(4,0,king,"white");


        theBoard.getTeamArray("white").add(king);
        theBoard.getTeamArray("black").add(rook1);
        theBoard.getTeamArray("black").add(rook2);

        //Check real case
        assertTrue(rook1.makesCheck(0,0,king.getPosition().getX(), king.getPosition().getY(), theBoard));
        //Check false case
        assertFalse(rook1.makesCheck(0, 1, king.getPosition().getX(), king.getPosition().getY(), theBoard));
        //Check when the Piece is off the board
        assertFalse(rook1.makesCheck(4, 8, king.getPosition().getX(), king.getPosition().getY(), theBoard));


    }

    @Test
    /**
     * A function that tests to see if check works correctly
     */
    public void testIsKingInCheck() {
        Board theBoard = new Board(false, false);
        Piece king = new King(4,0,"white");
        theBoard.setKingSquare(4,0, "white");
        theBoard.setKing(true, "white");

        Piece rook1 = new Rook(0,0,"black");
        Piece rook2 = new Rook(1,6,"white");

        theBoard.getTeamArray("white").add(king);
        theBoard.getTeamArray("black").add(rook1);
        theBoard.getTeamArray("black").add(rook2);

        theBoard.placePiece(0,0,rook1,"black");
        theBoard.placePiece(1,6,rook2,"black");
        theBoard.placePiece(4,0,king,"white");


        //checks that the king is in check if not blocked
        assertFalse(rook2.move(1, 6, theBoard));
        //checks that the king is not in check if blocked
        assertTrue(rook2.move(1, 0, theBoard));
        //checks that the king will be in check if the piece moves out of the way
        assertFalse(rook2.move(1, 1, theBoard));
        //checks to make sure that returns if bad input
        assertFalse(rook2.move(1,-1,theBoard));
    }

 }
