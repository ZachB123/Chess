import java.util.ArrayList;

public class Piece {

	//Strings for the color of the piece empty has no color
	public static final String BLACK = "b";
	public static final String WHITE = "w";
	public static final String EMPTY = "";
	
	//color of the piece
	protected String color;
	
	//how many times the piece has been moved
	//needed for pawns, kings and rooks
	protected int moveNumber;
	
	//the coordinate of the tile that the piece is currently on
	protected Coordinate location;
	
	//the board that the piece is a part of
	protected Board board;
	
	//creates a piece with a specific color
	Piece(String color, Coordinate c, Board b)
	{
		this.color = color;
		location = c;
		board = b;
		moveNumber = 0;
	}
	
	//default constructor creates an empty piece
	Piece(Coordinate c, Board b)
	{
		this(Piece.EMPTY, c, b);
	}
	
	//returns the color
	public String getColor()
	{
		return color;
	}
	
	public void setLocation(Coordinate c)
	{
		location = c;
	}
	
	//returns what piece it is for getting correct image
	public String getIdentifier()
	{
		//overridden in children just a piece has no identifier because there is no generic piece in chess
		return "";
	}
	
	//returns an array of all of the possible coordinates that this piece could move to takes into account if pieces are in the way but NOT if it will result in check for the person who is moving turn
	public ArrayList<Coordinate> getMoves()
	{
		return new ArrayList<Coordinate>();
	}
	
	//checks if getMoves contains a coordinate that is passed to the method
	public boolean getMovesContains(Coordinate c)
	{
		return true;
	}
	
	public void incrementMove()
	{
		moveNumber++;
	}
	
	public int getMoveNumber()
	{
		return moveNumber;
	}
	
	//returns the location of the piece
	public Coordinate getLocation()
	{
		return location;
	}
}
