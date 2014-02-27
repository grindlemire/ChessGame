package Model.Model.BoardClasses;


import Model.Model.PieceClasses.Piece;

/** The data type that holds all the information on what is on each square **/
public class Square
{
		//the position of the square
        private Point position;

    //if the square is occupied
		private boolean occupied;
        //Holds the piece that is on it
        private Piece pieceOnSquare;
        //Specifies what team is on the square
        private String teamOnSquare;
        //Specifies if the king is on the square
        private boolean hasKing;

    /**
     * The Constructor of the square
     */
    public Square()
    {
        position = new Point();
        occupied = false;
        teamOnSquare = "none";
        pieceOnSquare = null;
    }


    /**
     * A function that gets the position of the Square
     * @return the point that the square occupies
     */
    public Point getPosition() {
            return position;
		}

    /**
     * A function that sets the position of a square
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     */
	public void setPosition(int x, int y)
    {
        if(x<0 || x>7 || y<0 || y>7)
            System.out.print("The Square is being initialized to a bad position!!");
        position.setPoint(x, y);
    }


    /**
     * A function that indicates if the square is occupied
     * @return whether the square is occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * A function that sets whether the square is occupied
     * @param newOccupied a boolean whether or not the square will be occupied
     */
    public void setOccupied(boolean newOccupied) {
       this.occupied = newOccupied;
    }


    /**
     * A function that gets the piece on that square if there is one
     * @return the piece on the square
     */
    public Piece getPieceOnSquare()
    {
        return pieceOnSquare;
    }

    /**
     * A function that sets what piece is on the square
     * @param newPieceOnSquare the piece that will be placed on the square
     */
    public void setPieceOnSquare(Piece newPieceOnSquare)
    {
        this.pieceOnSquare = newPieceOnSquare;
    }

    /**
     * A function that gets the team on the square
     * @return the team that is on the square
     */
    public String getTeamOnSquare()
    {
        return teamOnSquare;
    }

    /**
     * A function that sets the team on the square
     * @param newTeamOnSquare the new team on the square
     */
    public void setTeamOnSquare(String newTeamOnSquare)
    {
        this.teamOnSquare = newTeamOnSquare;
    }




}