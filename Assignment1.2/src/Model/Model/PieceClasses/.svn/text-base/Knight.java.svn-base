package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;

public class Knight extends Piece
{
    public Knight(int x, int y, String team)
    {
        super(x,y,team);
    }

    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard) {

        if(currX < 0 || currY<0 || currX > 7 || currY > 7)
            return false;

        int Xdiff = Math.abs(currX-x);
        int Ydiff = Math.abs(currY-y);


        //if the difference is an L shape for the knight
        return ((Xdiff == 2 && Ydiff == 1) || (Xdiff == 1 && Ydiff == 2)) && theBoard.otherTeamOccupiesOrEmpty(x, y, this.team);

    }
}
