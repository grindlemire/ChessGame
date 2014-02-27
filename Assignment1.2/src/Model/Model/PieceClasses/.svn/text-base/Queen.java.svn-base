package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;

public class Queen extends Piece
{

    public Queen(int x, int y, String team)
    {
        super(x,y,team);
    }
    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard)
    {
        //return true if you can move diagonal, in a collumn or row, and there is
        // no one between you, and the end is an empty square or other team
        return ((theBoard.isDiagonal(x,y,currX,currY) || theBoard.isColumnRow(x, y, currX, currY)) && theBoard.otherTeamOccupiesOrEmpty(x,y,this.team));
    }
}