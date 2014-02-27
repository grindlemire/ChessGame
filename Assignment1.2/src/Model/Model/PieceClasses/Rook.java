package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;

public class Rook extends Piece
{
    public Rook(int x, int y, String team)
    {
        super(x,y,team);
    }


    //A general function to see if possible to move the rook
    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard) {

        //returns if collumn or row and anything between you and if endspot is held by an enemy or empty
        return(theBoard.isColumnRow(x, y, currX, currY) && theBoard.otherTeamOccupiesOrEmpty(x,y,this.team));


    }



}
