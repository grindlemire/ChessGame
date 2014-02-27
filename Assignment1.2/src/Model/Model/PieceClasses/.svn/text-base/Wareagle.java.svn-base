package Model.Model.PieceClasses;

import Model.Model.BoardClasses.Board;


public class Wareagle extends Piece {

    public Wareagle(int x, int y, String team)
    {
        super(x,y,team);
    }



    @Override
    public boolean canMove(int x, int y, int currX, int currY, Board theBoard) {


        boolean returnValue = false;
        boolean otherTeam = false;
        int Xdiff = Math.abs(currX-x);
        int Ydiff = Math.abs(currY-y);

        if(team.equals("white") && theBoard.getChessBoardSquareGetOccupied(x, y) && theBoard.getChessBoardSquareGetTeamOnSquare(x, y).equals("black")) {
            otherTeam = true;
        }
        else if(team.equals("black") && theBoard.getChessBoardSquareGetOccupied(x, y) && theBoard.getChessBoardSquareGetTeamOnSquare(x, y).equals("white")) {
            otherTeam = true;
        }

        if( (Xdiff<=1 && Ydiff<=1) && otherTeam)
        {
           returnValue = true;
        }

        if((Xdiff<=3 && Ydiff<=3) && !(Xdiff<=1 && Ydiff<=1) && !theBoard.getChessBoardSquareGetOccupied(x,y))
            returnValue = true;

        return returnValue;


    }
}
