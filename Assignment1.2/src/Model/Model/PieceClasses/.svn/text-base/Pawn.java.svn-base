package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;

public class Pawn extends Piece
{
    private boolean hasMoved;

    public Pawn(int x, int y, String team)
    {
        super(x,y,team);
        this.hasMoved = false;

    }

    public boolean getHasMoved()
    {
        return hasMoved;
    }

    public void setHasMoved(boolean newHasMoved) { hasMoved = newHasMoved; }


    //A function that checks if the pawn can move to the correct spot
    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard)
    {
        boolean returnValue = false;

        if(currX < 0 || currY<0 || currX > 7 || currY > 7)
            return false;
        //moving forward cases
        if(this.hasMoved)
        {
            //empty square in front case
            if(this.team.equals("black") && x == currX && y == currY+1 && !theBoard.getChessBoardSquareGetOccupied(x, y))
                returnValue = true;
            else if(this.team.equals("white") && x == currX && y == currY-1 && !theBoard.getChessBoardSquareGetOccupied(x, y))
                returnValue = true;
        }
        else
        {
            //Still check to see if 1 in front empty
            if(this.team.equals("black") && x == currX && y == currY+1 && !theBoard.getChessBoardSquareGetOccupied(x, y))
                returnValue = true;
            else if(this.team.equals("white") && x == currX && y == currY-1 && !theBoard.getChessBoardSquareGetOccupied(x, y))
                returnValue = true;

            //empty square 2 in front
            if(this.team.equals("black") && x == currX && (y == currY+1 || y == currY+2) && !theBoard.getChessBoardSquareGetOccupied(x, y))
                returnValue = true;
            else if(this.team.equals("white") && x == currX && (y == currY-1 || y == currY-2) && !theBoard.getChessBoardSquareGetOccupied(x, y))
                returnValue = true;
        }

        //Attacking cases
        if(this.team.equals("black") && (x == currX+1 || x == currX-1) && y == currY+1 && theBoard.getChessBoardSquareGetOccupied(x, y) && theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
            returnValue = true;
        else if( this.team.equals("white") && (x == currX+1 || x == currX-1) && y == currY-1 && theBoard.getChessBoardSquareGetOccupied(x, y) && theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
            returnValue = true;

        return returnValue;
    }

}
