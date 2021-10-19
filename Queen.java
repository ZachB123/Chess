import java.util.ArrayList;

public class Queen extends Piece{
	
	//returns what piece it is for getting correct image
	public String getIdentifier()
	{
		return "Q";
	}
	
	Queen(String color, Coordinate c, Board b)
	{
		//create a pawn with color coordinate and board
		super(color,c,b);
	}
	
	//returns an array of all of the possible coordinates that this piece could move to takes into account if pieces are in the way but NOT if it will result in check for the person who is moving turn
	public ArrayList<Coordinate> getMoves()
	{
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		//create a bishop and a rook get their moves then put the arrays together and return that
		
		Bishop b = new Bishop(color, location, board);
		Rook r = new Rook(color, location, board);
		
		moves.addAll(b.getMoves());
		moves.addAll(r.getMoves());
		
		return moves;
	}
	
	//checks if getMoves contains a coordinate that is passed to the method
	public boolean getMovesContains(Coordinate c)
	{
		//System.out.println(getMoves());
		if(getMoves().contains(c))
		{
			return true;
		}
		return false;
	}

}
