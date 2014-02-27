package Model.Model.BoardClasses;


/** A class that will define how a coordinate works in the chess game **/
public class Point
{	
	private int x;
	private int y;


    /**Default Constructor for Point */
    public Point()
    {
        x=-1;
        y=-1;
    }

    public Point(int x, int y)
    {
        this.x=x;
        this.y=y;
    }


    /**
     * A function that gets the y-coordinate of the point
     * @return the y-coordinate of the point
     */
	public int getY() {
		return y;
	}

    /**
     * A function that gets the x-coordinate of the point
     * @return the x-coordinate of the point
     */
	public int getX() {
		return x;
	}

    /**
     * A function that sets the x and y coordinates of the point
     * @param x the new x-coordinate of the point
     * @param y the new y-coordinate of the point
     */
    public void setPoint(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}