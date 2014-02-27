package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;

public class King extends Piece
{

    public King(int x, int y, String team)
    {
        super(x,y,team);

    }

    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard) {

        boolean returnValue = false;

        if(currX < 0 || currY<0 || currX > 7 || currY > 7)
            return false;

        int Xdiff = Math.abs(currX - x);
        int Ydiff = Math.abs(currY - y);

        //check to make sure within one unit in any direction
        if( ((Xdiff==1 && Ydiff==1) || (Xdiff==1 && Ydiff==0) || (Xdiff==0 && Ydiff==1)) && theBoard.otherTeamOccupiesOrEmpty(x,y,this.team))
        {
            returnValue = true;
        }

//        //update king square
//        if(returnValue)
//        {
//            if(this.team.equals("black"))
//            {
//                theBoard.setKingSquare(x, y, "black");
//            }
//            else if(this.team.equals("white"))
//            {
//                theBoard.setKingSquare(x, y, "white");
//            }
//
//        }

        return returnValue;
    }
}
