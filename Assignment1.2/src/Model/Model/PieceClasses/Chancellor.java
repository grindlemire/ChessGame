package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;


public class Chancellor extends Piece {
    public Chancellor(int x, int y, String team)
    {
        super(x,y,team);
    }


    //A general function to see if possible to move the rook
    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard) {

        int Xdiff = Math.abs(currX-x);
        int Ydiff = Math.abs(currY-y);

        //returns if collumn or row and anything between you and if endspot is held by an enemy or empty
        return( (theBoard.isColumnRow(x, y, currX, currY)&& theBoard.otherTeamOccupiesOrEmpty(x,y,this.team) )
                || ( (Xdiff == 2 && Ydiff == 1) || (Xdiff == 1 && Ydiff == 2)) && theBoard.otherTeamOccupiesOrEmpty(x, y, this.team));


    }


}
