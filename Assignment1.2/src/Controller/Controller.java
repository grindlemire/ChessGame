package Controller;

import Model.Model.BoardClasses.*;
import GUI.SquareButton;
import GUI.Window;
import Model.Model.PieceClasses.Pawn;
import Model.Model.PieceClasses.Piece;

import java.awt.*;



public class Controller {

    private static Board theBoard;
    private static Square initialSquare;
    private static Window theWindow;
    private static int turn=0;

    private static int player1Score=0;
    private static int player2Score=0;

    static Piece savedMovedPiece=null;
    static Piece savedDeadPiece=null;

    static Square savedToSquare = null;
    static Square savedFromSquare = null;

    private static SquareButton[][] squareGrid;

    private static boolean isFairy = false;
    private static boolean pauseGame = false;

    /**
     * A constructor for the Controller that sets up the window
     */
    public Controller()
    {
       squareGrid = new SquareButton[8][8];
       theWindow = new Window();
       theBoard = new Board(true, isFairy);
       theWindow.updateGame(theBoard);



    }

    /**
     * A function that holds the game loop and runs the game
     */
    public void run() throws InterruptedException {
        while(true)
        {
            if(pauseGame)
            {
                theWindow.getGlass(false).setVisible(true);
            }
            theWindow.updateGame(theBoard);
            Thread.sleep(1);

        }
    }

    /**
     * A function that returns the current score of a certain player
     * @param team the team that the function is requesting the score of
     * @return the score of that team
     */
    public static int getScore(String team)
    {
        if(team.equals("white"))
            return player1Score;
        else return player2Score;
    }
    /**
     * A function that returns the Board being used
     * @return the Board being used
     */
    public static Board getBoard()
    {
        return theBoard;
    }

    /**
     * A function that returns the window being used
     * @return the window being used
     */
    public static Window getWindow()
    {
        return theWindow;
    }

    /**
     * A function that gets the initial Square that has been clicked on
     * @return the square that was clicked on first
     */
    public static Square getInitialSquare()
    {
        return initialSquare;
    }

    /**
     * A function that sets the first square clicked on
     * @param newSquare the square that was clicked on first
     */
    public static void setInitialSquare(Square newSquare)
    {
        initialSquare = newSquare;
    }

    /**
     * A function that gets the initial SquareButton that has been clicked on
     * @return the squareButton that was clicked on first
     */
    public static SquareButton getInitialSquareButton()
    {
        return squareGrid[initialSquare.getPosition().getX()][initialSquare.getPosition().getY()];
    }

    /**
     * A function that gets who's turn it is
     * @return whose turn it is
     */
    public static int getTurn()
    {
        return turn;
    }

    /**
     * A function that increments whose turn it is
     */
    public static void nextTurn()
    {
       turn =  (turn+1)%2;
    }

    /**
     * A function that forfeits the game for a certain team
     * @param team the team forfeiting
     */
    public static void forfeitGame(String team)
    {
        if(team.equals("white"))
            player2Score++;
        if(team.equals("black"))
            player1Score++;
        makeNewGame();
    }

    /**
     * A function that creates a new game and resets the board
     */
    public static void makeNewGame()
    {
        theBoard = new Board(true,isFairy);
        initialSquare=null;
        turn = 0;
        theWindow.resetBoard(player1Score, player2Score);
    }

    /**
     * A function that sets whether the game will be a fairy game
     * @param newNormal a boolean of whether the game will be a fairy game
     */
    public static void setIsFairy(boolean newNormal)
    {
        isFairy = newNormal;
    }


    /**
     * A function that sets whether the game is in the pause state
     */
    public static void setPause()
    {
        pauseGame = !pauseGame;
        theWindow.getGlass(false).setVisible(pauseGame);
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                squareGrid[i][j].setEnabled(!pauseGame);
    }

    /**
     * A function that sets whether we are at the end game state
     *
     */
    public static void setEndGame()
    {
        theWindow.getGlass(true).setVisible(true);
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                squareGrid[i][j].setEnabled(false);
    }
    public static SquareButton controllerGetSquareButton(int x, int y)
    {
        return squareGrid[x][y];
    }




    /**
     * A function that saves the state of the most recent moves
     * @param fromButton the button you are moving from
     * @param toButton the button you are moving to
     */
    public static void saveState(SquareButton fromButton, SquareButton toButton)
    {
        savedMovedPiece= getSquareWithButton(fromButton).getPieceOnSquare();
        savedDeadPiece = getSquareWithButton(toButton).getPieceOnSquare();

        savedFromSquare = getSquareWithButton(fromButton);
        savedToSquare = getSquareWithButton(toButton);
    }

    /**
     * A function that returns the board to a state before (one move)
     */
    public static void returnToSavedState()
    {
        if(savedFromSquare!=null)
            theBoard.pickUpPiece(savedFromSquare.getPosition().getX(), savedFromSquare.getPosition().getY());
        if(savedToSquare!=null)
            theBoard.pickUpPiece(savedToSquare.getPosition().getX(), savedToSquare.getPosition().getY());

        if(savedDeadPiece!=null && savedToSquare!=null)
        {
            savedDeadPiece.setPosition(savedToSquare.getPosition().getX(), savedToSquare.getPosition().getY());
            theBoard.placePiece(savedDeadPiece.getPosition().getX(), savedDeadPiece.getPosition().getY(), savedDeadPiece, savedDeadPiece.getTeam());

        }
        if(savedMovedPiece!=null && savedFromSquare!=null)
        {
            theBoard.placePiece(savedFromSquare.getPosition().getX(), savedFromSquare.getPosition().getY(), savedMovedPiece, savedMovedPiece.getTeam());
            savedMovedPiece.setPosition(savedFromSquare.getPosition().getX(), savedFromSquare.getPosition().getY());
        }
        if((savedMovedPiece instanceof Pawn) && savedFromSquare!=null && (savedFromSquare.getPosition().getY()==6 || savedFromSquare.getPosition().getY()==1 ))
            ((Pawn)savedMovedPiece).setHasMoved(false);

        nextTurn();
        theWindow.getUndo().setEnabled(false);


    }

    /**
     * A function that takes a Button and returns the Square associated with it
     * @param input the button to convert
     * @return the square associated with that button
     */
    public static Square getSquareWithButton(SquareButton input)
    {
        int x = input.getButtonX();
        int y = input.getButtonY();

        return theBoard.getChessBoardSquare(x,y);

    }

    /**
     * A function that gets a certain squareButton
     * @param x the x-coordinate of the squareButton
     * @param y the y-coordinate of the squareButton
     * @return the Button on that coordinate
     */
    public static SquareButton getSquareButton(int x, int y)
    {
        return squareGrid[x][y];
    }

    /**
     * A function that sets a certain squareButton's coordinates
     * @param x the new x-coordinate of the squareButton
     * @param y the new y-coordinate of the squareButton
     * @param button the button that is to be changed
     */
    public static void setSquareButton(int x, int y, SquareButton button)
    {
        squareGrid[x][y] = button;
    }

    /**
     * A function that governs all the logic for the Button presses. This includes moving and all the logistics
     * involved.
     * @param theButton the Button being pressed
     */
    public static void buttonAccessed(SquareButton theButton)
    {
        //if there is a previously picked square
        if(getInitialSquare()!=null)
        {
            Square oldSquare = getInitialSquare();
            SquareButton oldSquareButton = getInitialSquareButton();

            //if you have picked up a piece before
            if(oldSquare.getPieceOnSquare()!=null)
            {
                Piece thePiece = oldSquare.getPieceOnSquare();
                String pieceTeam = thePiece.getTeam();
                Model.Model.BoardClasses.Point oppositeKingPosition = thePiece.getKing(thePiece.getOppositeTeam(pieceTeam), theBoard);
                Model.Model.BoardClasses.Point kingPosition = thePiece.getKing(pieceTeam, theBoard);

                saveState(oldSquareButton, theButton);
                //if you can move to the position
                if(thePiece.move(theButton.getButtonX(), theButton.getButtonY(), theBoard))
                {
                    theBoard.drawBoard();
                    getWindow().getUndo().setEnabled(true);
                    //If you put them into check mate end the game
                    if(theBoard.isCheckMate(thePiece.getOppositeTeam(pieceTeam)))
                    {
                        controllerGetSquareButton(oppositeKingPosition.getX(), oppositeKingPosition.getY()).setBackground(Color.RED);
                        getWindow().makeEndGameScreen(thePiece.getOppositeTeam(pieceTeam));
                        setEndGame();
                        return;
                    }
                    //If you put them into check put up the visual marker
                    else if(theBoard.isCheck(thePiece.getOppositeTeam(pieceTeam)))
                        controllerGetSquareButton(oppositeKingPosition.getX(), oppositeKingPosition.getY()).setBackground(Color.CYAN);
                    //else update the king so he doesn't look in check
                    else
                    {
                        controllerGetSquareButton(kingPosition.getY(), kingPosition.getX()).setBackground(theButton.getCorrectColor(kingPosition.getY(), kingPosition.getX()));
                        correctSquareColors();
                    }
                    //update your background

                    nextTurn();



                }
                else
                {
                    oldSquareButton.setBackground(Color.blue);
                }
                setInitialSquare(null);

            }
        }
        //If you have not
        else if(getInitialSquare()==null && theBoard.getChessBoardSquareGetOccupied(theButton.getButtonX(), theButton.getButtonY()))
        {
            correctSquareColors();
            if((Controller.getTurn()== 0 && Controller.getBoard().getChessBoardSquareGetTeamOnSquare(theButton.getButtonX(), theButton.getButtonY()).equals("white"))
                    || (Controller.getTurn()== 1 && Controller.getBoard().getChessBoardSquareGetTeamOnSquare(theButton.getButtonX(), theButton.getButtonY()).equals("black")))
            {
                setInitialSquare(theBoard.getChessBoardSquare(theButton.getButtonX(), theButton.getButtonY()));
                theButton.setBackground(Color.MAGENTA);
            }

        }
    }

    /**
     * A function that corrects all the colors of the board so that they are not poorly colored.
     */
    public static void correctSquareColors()
    {
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                squareGrid[i][j].setBackground(squareGrid[i][j].getCorrectColor(i,j));
    }

    /**
     * The main of program
     * @param args some args
     */
    public static void main(String[] args)
    {
        Controller theGame= new Controller();
        try {
            theGame.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}






