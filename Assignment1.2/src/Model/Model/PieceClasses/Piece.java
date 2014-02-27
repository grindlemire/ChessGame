
/** This file implements all the interfaces needed **/
package Model.Model.PieceClasses;


import Model.Model.BoardClasses.Board;
import Model.Model.BoardClasses.Point;

import java.util.ArrayList;

/**
 * A general piece class that will handle all the non specific piece properties
 */

public abstract class Piece
{
	protected String team;
    protected Point position;

    /**
     * A constructor for the Piece that assigns the team and the position
     * @param x the x-coordinate of the piece
     * @param y the y-coordinate of the piece
     * @param team the team of the piece
     */
    public Piece(int x, int y, String team)
    {
        //check to make sure that the team name is one of the two needed.
        if(!team.equals("white") && !team.equals("black"))
        {
            System.out.println("Piece Team name wrong!!");
            return;
        }

        this.team = team;
        this.position = new Point(x,y);
    }

    /**
     * The general move function that will make sure that a piece can move before it actually does
     * @param newX the new X coordinate that the piece is moving to
     * @param newY the new Y coordinate that the piece is moving to
     * @param theBoard the Board of pieces
     * @return true or false of whether the piece moved
     */
	public boolean move(int newX, int newY, Board theBoard)
    {
        //Check to see if going off board
        if(newX<0 || newX>7 || newY<0 || newY>7)
        {
            return false;
        }
        //Case where staying in same place
        if(newX ==position.getX() && newY ==position.getY())
        {
            System.out.println("Moving to the same space! Try again.");
            return false;
        }
        //Check if you are doing a valid move for your piece, if you are move there and update board
        else if(this.canMove(newX,newY, position.getX(), position.getY(), theBoard))
        {

            //check to see if this move will put you in check. If it will, do nothing
            if(isKingInCheckWrapper(newX, newY, this.team, theBoard))
            {
                System.out.println("King in check with that move! Try again.");
                return false;
            }
            //Checks to see if Other King in check from move
            if(this.makesCheck(newX, newY, this.getKing(getOppositeTeam(team), theBoard).getX(), this.getKing(getOppositeTeam(team), theBoard).getY(), theBoard))
                theBoard.setCheck(true, this.getOppositeTeam(team));

            if(theBoard.getPieceOnSquare(newX, newY)!=null)
                theBoard.getPieceOnSquare(newX, newY).setPosition(-1,-1);

            //Actually makes the move
            this.makeMove(newX, newY, theBoard);

            if(this instanceof Pawn)
                ((Pawn)this).setHasMoved(true);
            if(this instanceof King)
                theBoard.setKingSquare(newX, newY, team);

            //See if the other team is in checkmate
            if(theBoard.isCheckMate(this.getOppositeTeam(team)))
                theBoard.setTeamCheckMate(this.getOppositeTeam(team));

            //set piece to new position
            position.setPoint(newX, newY);
            return true;
        }
        else
        {
            System.out.println("piece team = " + this.team + " Position is " + this.position.getX() + " " + this.position.getY());
            System.out.println("piece team = " + theBoard.getChessBoardSquare(newX,newY).getTeamOnSquare() + " Position is " + newX + " " + newY + " Is Occupied = " + theBoard.getChessBoardSquare(newX,newY).isOccupied());
            System.out.println("Can't move that piece in that way!!");
            return false;
        }
    }

    /**
     * An overridden function on whether a specific piece can move
     * @param x the x-coordinate of the piece
     * @param y the y-coordinate of the piece
     * @param currX the current x-coordinate of the piece
     * @param currY the current y-coordinate of the piece
     * @param theBoard the Board of pieces
     * @return a boolean for whether or not the piece can physically move to the location
     */
    public abstract boolean canMove(int x, int y, int currX, int currY, Board theBoard);

    /**
     * A function to see if the movement of a piece will make check on the other team
     * @param x the x-coordinate of the piece
     * @param y the y-coordinate of the piece
     * @param kingX the x-coordinate of the king
     * @param kingY the y-coordinate of the king
     * @param theBoard the Board of pieces
     * @return whether or not the piece puts the other team in check
     */
    public boolean makesCheck(int x, int y, int kingX, int kingY, Board theBoard)
    {
        if(kingX <0 || kingY <0)
            return false;
        if(canMove(kingX, kingY, x, y, theBoard))
        {
            System.out.print("Check!\n");
            theBoard.setCheck(true, getOppositeTeam(team));
            return true;
        }
        theBoard.setCheck(false, getOppositeTeam(team));
        return false;

    }

    /**
     * A function that gets the position of the opposite color's king
     * @param currTeam the current team asking
     * @param theBoard the Board of pieces
     * @return the point of where the king is
     */
    public Point getKing(String currTeam, Board theBoard)
    {

        if(currTeam.equals("white"))
        {
            if(!theBoard.isKing("white"))
            {
                return new Point(-1,-1);
            }
            return theBoard.getKingSquarePos("white");
        }
        else if(currTeam.equals("black"))
        {
            if(!theBoard.isKing("black"))
            {
                return new Point(-1,-1);
            }
            return theBoard.getKingSquarePos("black");
        }
        else System.out.println("You passed the a null color into getOppositeKing!!");
        return null;
    }

    /**
     * A helper function that will get the opposite team as the piece
     * @param team the team you want to get the opposite of
     * @return the opposite team
     */
	public String getOppositeTeam(String team) {
		if(team.equals("white"))
            return "black";
        else if(team.equals("black"))
            return "white";
        else System.out.println("Passed wrong team into getOppositeTeam!!");
        return null;
	}

    /**
     * A function that just returns the team of the piece
     * @return the name of the team
     */
    public String getTeam()
    {
        return team;
    }
    /**
     * A helper function that literally takes on piece and moves it to the other
     * @param newX new x-coordinate for the piece
     * @param newY new y-coordinate for the piece
     * @param theBoard the Board of pieces
     */
    protected void makeMove(int newX, int newY, Board theBoard)
    {
        theBoard.pickUpPiece(position.getX(), position.getY());
        theBoard.placePiece(newX,newY,this, team);

        this.position.setPoint(newX, newY);
    }

    /**
     * A wrapper helper function that checks if the king is in check if a piece is moving
     * @param targetX the x coordinate of where the piece is moving to
     * @param targetY the y coordinate of where the piece is moving to
     * @param team the team of the piece that is moving
     * @param theBoard the Board of pieces
     * @return whether the piece moving puts its own king in check
     */
    public boolean isKingInCheckWrapper(int targetX, int targetY, String team, Board theBoard)
    {
        Piece tempPiece = null;
        boolean replace = false;
        boolean moved = false;
        //Pick up the piece you are moving
        theBoard.pickUpPiece(position.getX(), position.getY());

        if(canMove(targetX, targetY, position.getX(), position.getY(), theBoard))
        {
            //Place onto the new location and if there was a piece there record it
            if(theBoard.getChessBoardSquareGetOccupied(targetX, targetY))
            {
                replace = true;
                tempPiece = theBoard.getPieceOnSquare(targetX, targetY);
                tempPiece.setPosition(-1,-1);
            }
            theBoard.placePiece(targetX, targetY, this, team);
            moved = true;
        }

        //see if you are in check
        boolean returnValue = isKingInCheck(this.team, theBoard);

        //pick the piece you just moved back up if you moved it
        if(moved)
        {
            theBoard.pickUpPiece(targetX, targetY);
            theBoard.placePiece(position.getX(),position.getY(),this,team);
        }
        if(replace)
        {
            theBoard.placePiece(targetX, targetY, tempPiece, getOppositeTeam(team));
            tempPiece.setPosition(targetX, targetY);
        }

        //put piece back down
        theBoard.placePiece(position.getX(), position.getY(), this, this.team);

        return returnValue;
    }

    /**
     * A wrapper helper function that checks if the king is in check if a piece is moving
     *
     *
     * @param team the team of the piece that is moving
     * @param theBoard the Board of pieces
     * @return whether the piece moving puts its own king in check
     */
    public boolean isKingInCheck(String team, Board theBoard)
    {
        //first check if there is a king on the board
        if(!theBoard.isKing(team))
            return false;

        int kingX;
        int kingY;
        String oppTeam = this.getOppositeTeam(team);
        kingX = theBoard.getKingSquarePos(team).getX();
        kingY = theBoard.getKingSquarePos(team).getY();
        ArrayList<Piece> array = theBoard.getTeamArray(oppTeam);

        //Go through all the squares on the board, if it holds an enemy unit that
        //you are not killing this turn and it can kill the king then return true
        for(int i=0; i<array.size(); i++)
        {
            if(array.get(i).canMove(kingX, kingY, array.get(i).position.getX(), array.get(i).position.getY(), theBoard))
                return true;
        }


        return false;
    }

    /**
     * returns the point that the piece is currently sitting on
     * @return the position of the piece
     */
    public Point getPosition()
    {
        return position;
    }

    /**
     * A function that sets the location of the piece
     * @param x the x-coordinate of the piece
     * @param y the y-coordinate of the piece
     */
    public void setPosition(int x, int y)
    {
        position.setPoint(x,y);
    }

}
