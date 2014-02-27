/** this is part of the board description **/
package Model.Model.BoardClasses;


import Model.Model.PieceClasses.*;

import java.util.ArrayList;

/**
 * A class to manage all the board. It is filled with a 2D array of square objects
 */
public class Board
{
	//the board
	private Square[][] chessBoard;

    private ArrayList<Piece> whiteTeam = new ArrayList<Piece>();
    private ArrayList<Piece> blackTeam = new ArrayList<Piece>();

    private Square whiteKingSquare;
    private Square blackKingSquare;
    private boolean whiteCheck = false;
    private boolean blackCheck = false;
    private boolean isWhiteKing = false;
    private boolean isBlackKing = false;
    private boolean isWhiteCheckmate = false;
    private boolean isBlackCheckmate = false;


    /**
     * A basic constructor that will initialize the board
     * Will initialize all the squares to empty and give
     * them the correct coordinates.
     * @param populate whether or not to place pieces on the board or to keep it empty
     */
	public Board(boolean populate, boolean special)
	{
        chessBoard = new Square[8][8];
        //go through the board and initialize every square
		for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                chessBoard[i][j] = new Square();
                chessBoard[i][j].setPosition(i, j);
            }
        }
        if(populate)
            placeAllPieces(special);

    }


    /**
     * A private helper function that places all the pieces on the empty initialized board in their classic positions.
     */
    private void placeAllPieces(boolean special)
    {

        //Put on special pieces if they are needed.
        if(special)
        {
            Piece newPiece = new Wareagle(3,5,"white");
            placePiece(3,5, newPiece,"white");
            whiteTeam.add(newPiece);

            newPiece = new Wareagle(3,2,"black");
            placePiece(3, 2, newPiece, "black");
            blackTeam.add(newPiece);

            newPiece = new Chancellor(4,2,"black");
            placePiece(4,2,newPiece ,"black");
            blackTeam.add(newPiece);

            newPiece = new Chancellor(4,5,"white");
            placePiece(4,5,newPiece,"white");
            whiteTeam.add(newPiece);
        }


        //Put the Pawns on
        for(int i=0; i<8; i++)
        {
            Pawn newWhitePawn = new Pawn(i, 6, "white");
            Pawn newBlackPawn = new Pawn(i, 1, "black");

            Piece newPiece = newBlackPawn;
            String color = "black";
            int x = i;
            int y = 1;
            placePiece(x, y, newPiece, color); //initialize black pawn
            blackTeam.add(newPiece);

            color = "white";
            newPiece = newWhitePawn;
            y=6;
            placePiece(x, y, newPiece, color);//initialize white pawn
            whiteTeam.add(newPiece);
        }
        //Put the Rooks on
        for(int i=0; i<2; i++)
        {
            Rook newWhiteRook = new Rook(i*7, 7, "white");
            Rook newBlackRook = new Rook(i*7, 0, "black");

            Piece newPiece = newBlackRook;
            String color = "black";
            int x = i*7;
            int y = 0;
            placePiece(x, y, newPiece, color);//initialize black rook
            blackTeam.add(newPiece);

            color = "white";
            newPiece = newWhiteRook;
            y=7;
            placePiece(x, y, newPiece, color);//initialize white rook
            whiteTeam.add(newPiece);



        }
        //Put the Knights on
        for(int i=0; i<2; i++)
        {
            Knight newWhiteKnight = new Knight((i*5)+1, 7, "white");
            Knight newBlackKnight = new Knight((i*5)+1, 0, "black");

            Piece newPiece = newBlackKnight;
            String color = "black";
            int x = (i*5)+1;
            int y = 0;
            placePiece(x, y, newPiece, color);//initialize black knight
            blackTeam.add(newPiece);

            color = "white";
            newPiece = newWhiteKnight;
            y=7;
            placePiece(x, y, newPiece, color);//initialize white knight
            whiteTeam.add(newPiece);

        }
        //Put the Bishops on
        for(int i=0; i<2; i++)
        {
            Bishop newWhiteBishop = new Bishop((i*3)+2, 7, "white");
            Bishop newBlackBishop = new Bishop((i*3)+2, 0, "black");

            Piece newPiece = newBlackBishop;
            String color = "black";
            int x = (i*3)+2;
            int y = 0;
            placePiece(x, y, newPiece, color);//initialize black bishop
            blackTeam.add(newPiece);

            color = "white";
            newPiece = newWhiteBishop;
            y=7;
            placePiece(x, y, newPiece, color);//initialize white bishop
            whiteTeam.add(newPiece);
        }
        //Put the Queen on
        Queen newWhiteQueen = new Queen(3, 7, "white");
        Queen newBlackQueen = new Queen(3, 0, "black");

        Piece newPiece = newBlackQueen;
        String color = "black";
        int x = 3;
        int y = 0;
        placePiece(x, y, newPiece, color);//initialize black queen
        blackTeam.add(newPiece);

        color = "white";
        newPiece = newWhiteQueen;
        y=7;
        placePiece(x, y, newPiece, color);//initialize white queen
        whiteTeam.add(newPiece);


        //Put the King on
        King newWhiteKing = new King(4, 7, "white");
        King newBlackKing = new King(4, 0, "black");

        newPiece = newBlackKing;
        color = "black";
        x = 4;
        y = 0;
        placePiece(x, y, newPiece, color);//initialize black king
        this.setKingSquare(x, y, "black");
        this.setKing(true,"black");
        blackTeam.add(newPiece);

        color = "white";
        newPiece = newWhiteKing;
        y=7;
        placePiece(x, y, newPiece, color);//initialize white king
        this.setKingSquare(x, y, "white");
        this.setKing(true,"white");
        whiteTeam.add(newPiece);

	}

    /**
     * Getter function for if there is a king on the board
     * @param team of king looking for
     * @return whether there is a King of color team
     */
    public boolean isKing(String team) {
        if(team.equals("black"))
            return isBlackKing;
        else return isWhiteKing;
    }

    /**
     * A Setter function for setting  a king on the board
     *
     * @param isKing sets whether there will be a king
     * @param team of king you are setting
     *
     */
    public void setKing(boolean isKing, String team) {
        if(team.equals("white"))
            this.isWhiteKing = isKing;
        else if(team.equals("black"))
            this.isBlackKing = isKing;
    }

    /**
     * A function that gets a specific square on the chessboard
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return the Square that is at the specific x and y coordinates
     */
    public Square getChessBoardSquare(int x, int y)
    {
        return chessBoard[x][y];
    }


    /**
     * A function that sets the square to occupied or not
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @param changer the boolean of whether you want to set the square to occupied or empty
     */
    public void setChessBoardSquareSetOccupied(int x, int y, boolean changer)
    {
        if(x < 0 || y<0 || x > 7 || y > 7)
            return;
        chessBoard[x][y].setOccupied(changer);
    }

    /**
     * A function to see if a square is occupied or not
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return whether or not the square is occupied
     */
    public boolean getChessBoardSquareGetOccupied(int x, int y)
    {
        if(x < 0 || y<0 || x > 7 || y > 7)
            return false;
        return chessBoard[x][y].isOccupied();
    }

    /**
     * A function that sets what team is on the square
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @param team that is on the square
     */
    public void setChessBoardSquareSetTeamOnSquare(int x, int y, String team)
    {
        if(x < 0 || y<0 || x > 7 || y > 7)
            return;
        chessBoard[x][y].setTeamOnSquare(team);
    }

    /**
     * a function that checks what team is on a square
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return a string of what team is on the square
     */
    public String getChessBoardSquareGetTeamOnSquare(int x, int y)
    {
        if(x < 0 || y<0 || x > 7 || y > 7)
            return "none";
        return chessBoard[x][y].getTeamOnSquare();
    }

    /**
     * A function that sets what piece is on the square
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @param newPiece the piece that will be set on the square
     */
    public void setChessBoardPieceOnSquare(int x, int y, Piece newPiece)
    {
        if(x < 0 || y<0 || x > 7 || y > 7)
            return;
        chessBoard[x][y].setPieceOnSquare(newPiece);
    }

    /**
     * A function that gets what piece is on a square
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return the Piece that is on the specific square
     */
    public Piece getPieceOnSquare(int x, int y)
    {
        if(x < 0 || y<0 || x > 7 || y > 7)
            return null;
        return chessBoard[x][y].getPieceOnSquare();
    }


    /**
     * A function that gets the position of the King if he is on the board
     * @param team the team of the king
     * @return the coordinates of the king if found
     */
    public Point getKingSquarePos(String team)
    {
        if(team.equals("black"))
            return blackKingSquare.getPosition();
        else if(team.equals("white"))
            return whiteKingSquare.getPosition();
        else System.out.print("Wrong string passed into getKingSquare!!");
        return null;
    }

    /**
     * A function that sets the position of the king on the board
     * @param x the x-coordinate of the square to set the king
     * @param y the y-coordinate of the square to set the king
     * @param team that the king is with
     */
    public void setKingSquare(int x, int y, String team)
    {
        if(team.equals("white"))
            this.whiteKingSquare = chessBoard[x][y];
        else if(team.equals("black"))
            this.blackKingSquare = chessBoard[x][y];
        else System.out.print("Wrong string passed into setKingSquare!!");
    }

    /**
     * A function that sets whether the king is in check or not
     * @param isCheck a boolean that sets if a team is in check
     * @param team what team could be in check
     */
    public void setCheck(boolean isCheck, String team) {
        if(team.equals("white"))
            this.whiteCheck = isCheck;
        else if(team.equals("black")) {
            this.blackCheck = isCheck;
        }
        else System.out.print("Passed wrong team to setCheck!!");
    }

    public boolean isCheck(String team)
    {
        if(team.equals("white"))
            return whiteCheck;
        if(team.equals("black"))
            return blackCheck;
        return false;
    }


    /**
     * A function that says whether a specific square is empty or occupied by the other team
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @param team the team that is currently moving
     * @return a boolean whether the specific square in question is occupied by the other team or empty
     */
    public boolean otherTeamOccupiesOrEmpty(int x, int y, String team)
    {
        if(x < 0 || y<0 || x > 7 || y > 7)
            return false;

        if(team.equals("white") && getChessBoardSquareGetOccupied(x, y) && getChessBoardSquareGetTeamOnSquare(x, y).equals("black")) {
            return true;
        }
        else if(team.equals("black") && getChessBoardSquareGetOccupied(x, y) && getChessBoardSquareGetTeamOnSquare(x, y).equals("white")) {
            return true;
        }
        else if(!getChessBoardSquareGetOccupied(x, y)){
            return true;
        }
        else return false;
    }

    /**
     * A function that says whether another square is in the same row or column as the current square and if
     * anything is in between the two squares
     * @param x the x-coordinate of the square being looked at
     * @param y the y-coordinate of the square being looked at
     * @param currX the x-coordinate of the current square
     * @param currY the y-coordinate of the current square
     * @return
     */
    public boolean isColumnRow(int x, int y, int currX, int currY)
    {

        if(currX < 0 || currY<0 || currX > 7 || currY > 7)
            return false;

        if(x < 0 || y<0 || x > 7 || y > 7)
            return false;

        //Case where moving along X
        if(currX != x && currY == y)
        {
            //Moving Left
            int Xdiff = currX - x;
            if(Xdiff>0)
            {
                for(int i=currX-1; i>x; i--)
                    if(getChessBoardSquareGetOccupied(i, y))
                        return false;
            }
            //Moving Right
            else if(Xdiff<0)
            {
                for(int i=currX+1; i<x; i++)
                {
                    if(getChessBoardSquareGetOccupied(i, y))
                        return false;
                }
            }
        }
        //Case where moving along Y
        else if(currX == x && currY != y)
        {
            //Moving up
            int Ydiff = currY - y;
            if(Ydiff>0)
            {
                for(int i=currY-1; i>y; i--)
                    if(getChessBoardSquareGetOccupied(x, i))
                        return false;
            }
            //Moving down
            else if(Ydiff<0)
            {
                for(int i=currY+1; i<y; i++)
                {
                    if(getChessBoardSquareGetOccupied(x, i))
                        return false;
                }
            }
        }
        else if(currX != x )//&& currY != y)
            return false;


        return true;
    }

    /**
     * A function that says if a target square is in the same diagonal as the current square
     * and if anything is between the two squares
     * @param x the x-coordinate of the square being looked at
     * @param y the y-coordinate of the square being looked at
     * @param currX the x-coordinate of the current square
     * @param currY the y-coordinate of the current square
     * @return a boolean whether the target square is in the same diagonal and if there is nothing between the two squares.
     */
    public boolean isDiagonal(int x, int y, int currX, int currY)
    {

        int Xdiff = currX-x;
        int Ydiff = currY-y;

        if(currX < 0 || currY<0 || currX > 7 || currY > 7)
            return false;
        if(x < 0 || y<0 || x > 7 || y > 7)
            return false;

        //if it is not diagonal case
        if(Math.abs(Xdiff) != Math.abs(Ydiff))
            return false;

            //The four cases of checking if anything in between you and target
        else if(Xdiff>0 && Ydiff<0)
        {
            for(int i=1; i<Xdiff; i++)
            {
                if(getChessBoardSquareGetOccupied(currX - i, currY + i))
                    return false;
            }
        }
        else if(Xdiff>0 && Ydiff>0)
        {
            for(int i=1; i<Xdiff; i++)
            {
                if(getChessBoardSquareGetOccupied(currX - i, currY - i))
                    return false;
            }
        }
        else if(Xdiff<0 && Ydiff<0)
        {
            for(int i=1; i<Math.abs(Xdiff); i++)
            {
                if(getChessBoardSquareGetOccupied(currX + i, currY + i))
                    return false;
            }
        }
        else if(Xdiff<0 && Ydiff>0)
        {
            for(int i=1; i<Math.abs(Xdiff); i++)
            {
                if(getChessBoardSquareGetOccupied(currX + i, currY - i))
                    return false;
            }
        }
        return true;
    }

    /**
     * A function that places a specific piece somewhere on the board
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @param newPiece the specific piece being placed
     * @param team the team of the piece being placed
     */
    public void placePiece(int x, int y, Piece newPiece, String team)
    {
        if(newPiece instanceof King)
            setKingSquare(x,y, team);
        this.setChessBoardSquareSetOccupied(x, y, true);
        this.setChessBoardSquareSetTeamOnSquare(x, y, team);
        this.setChessBoardPieceOnSquare(x, y, newPiece);

    }

    /**
     * A function that picks up a piece from the board
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     */
    public void pickUpPiece(int x, int y)
    {
        setChessBoardSquareSetOccupied(x, y, false);
        setChessBoardSquareSetTeamOnSquare(x, y, "none");
        setChessBoardPieceOnSquare(x, y, null);
    }


    /**
     * A function that returns the array of all the pieces on a team
     * @param team the team that the array belongs to
     * @return the array of the correct team
     */
    public ArrayList<Piece> getTeamArray(String team)
    {
        if(team.equals("white"))
            return whiteTeam;
        if(team.equals("black"))
            return blackTeam;
        else return null;
    }


    public boolean isCheckMate(String team)
    {
        ArrayList<Piece> currTeamArray = getTeamArray(team);

        for(int k=0; k<currTeamArray.size(); k++)
        {
            for(int jt=0; jt<8; jt++)
                for(int it=0; it<8; it++)
                {
                    int currx = currTeamArray.get(k).getPosition().getX();
                    int curry = currTeamArray.get(k).getPosition().getY();
                    if(currTeamArray.get(k).canMove(it, jt, currx, curry, this) && !currTeamArray.get(k).isKingInCheckWrapper(it, jt, team, this) )
                         return false;
                }
        }
        if(team.equals("white"))
            isWhiteCheckmate = true;
        if(team.equals("black"))
            isBlackCheckmate = true;
        return true;

    }
    public boolean getTeamCheckMate(String team)
    {
        if(team.equals("white"))
            return isWhiteCheckmate;
        if(team.equals("black"))
            return isBlackCheckmate;
        else return false;
    }

    /**
     * A function that sets whether any team is in checkmate
     * @param team the team in checkmate
     */
    public void setTeamCheckMate(String team)
    {
        if(team.equals("white"))
            isWhiteCheckmate = true;
        if(team.equals("black"))
            isBlackCheckmate = true;
    }

    public void drawBoard()
    {
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                if(chessBoard[j][i].isOccupied())
                {
                    if(chessBoard[j][i].getTeamOnSquare().equals("white"))
                        System.out.print("w");
                    if(chessBoard[j][i].getTeamOnSquare().equals("black"))
                        System.out.print("b");
                }
                else System.out.print(".");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    public void clearArray()
    {
        whiteTeam.clear();
        blackTeam.clear();
    }
}