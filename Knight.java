import java.util.ArrayList;

public class Knight extends Piece{
	
	//returns what piece it is for getting correct image
	public String getIdentifier()
	{
		return "KN";
	}
	
	Knight(String color, Coordinate c, Board b)
	{
		//create a pawn with color coordinate and board
		super(color,c,b);
	}
	
	//returns an array of all of the possible coordinates that this piece could move to takes into account if pieces are in the way but NOT if it will result in check for the person who is moving turn
	public ArrayList<Coordinate> getMoves()
	{
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		//there are a total of 8 moves we need to check
		//for each we need to make sure that it does not go out of bounds
		//and make sure that we are not landing on a piece that has the same color as this knight
		
		// OO
		// O
		// X
		if(location.getX()-2 >= 0 && location.getY()+1 <= 7 && !(board.getBoard()[location.getX()-2][location.getY()+1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()-2,location.getY()+1));
		}
		// OOO
		// X
		if(location.getX()-1 >= 0 && location.getY()+2 <= 7 && !(board.getBoard()[location.getX()-1][location.getY()+2].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()-1,location.getY()+2));
		}
		// X
		// OOO
		if(location.getX()+1 <= 7 && location.getY()+2 <= 7 && !(board.getBoard()[location.getX()+1][location.getY()+2].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()+1,location.getY()+2));
		}
		// X
		// O
		// OO
		if(location.getX()+2 <= 7 && location.getY()+1 <= 7 && !(board.getBoard()[location.getX()+2][location.getY()+1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()+2,location.getY()+1));
		}
		//  X
		//  O
		// OO
		if(location.getX()+2 <= 7 && location.getY()-1 >= 0 && !(board.getBoard()[location.getX()+2][location.getY()-1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()+2,location.getY()-1));
		}
		// OOX
		// O
		if(location.getX()+1 <= 7 && location.getY()-2 >= 0 && !(board.getBoard()[location.getX()+1][location.getY()-2].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()+1,location.getY()-2));
		}
		// O
		// OOX
		if(location.getX()-1 >= 0 && location.getY()-2 >= 0 && !(board.getBoard()[location.getX()-1][location.getY()-2].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()-1,location.getY()-2));
		}
		// OO
		//  O
		//  X
		if(location.getX()-2 >= 0 && location.getY()-1 >= 0 && !(board.getBoard()[location.getX()-2][location.getY()-1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(location.getX()-2,location.getY()-1));
		}
		
		
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
