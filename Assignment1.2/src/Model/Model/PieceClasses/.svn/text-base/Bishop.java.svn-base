package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;

public class Bishop extends Piece
{

    public Bishop(int x, int y, String team)
    {
        super(x,y,team);
    }

    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard) {

        //returns if diagonal and anything between you and if endspot is held by an enemy or empty
        return(theBoard.isDiagonal(x,y,currX,currY) && theBoard.otherTeamOccupiesOrEmpty(x,y,this.team));

    }
}
