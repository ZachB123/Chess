import java.util.ArrayList;

public class Bishop extends Piece{
	
	//returns what piece it is for getting correct image
	public String getIdentifier()
	{
		return "B";
	}
	
	Bishop(String color, Coordinate c, Board b)
	{
		//create a pawn with color coordinate and board
		super(color,c,b);
	}
	
	//returns an array of all of the possible coordinates that this piece could move to takes into account if pieces are in the way but NOT if it will result in check for the person who is moving turn
	public ArrayList<Coordinate> getMoves()
	{
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		//for each diagonal we need to loop through until we hit the wall or hit a piece
		//if the piece is not the same color then we add it to possible moves otherwise we don't
		
		boolean keepGoing = true;
		
		//location of the current piece will be incremented/decremented to test possible moves
		int x = location.getX();
		int y = location.getY();
		
		//up and to the right x - 1 y + 1
		while(keepGoing)
		{
			x--;
			y++;
			//first check bounds
			//if the tile is empty add it an keep searching
			//if it is not empty then if it is a different color add it and break the loop other wise break the loop
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7)
			{
				//in here the location is in bounds
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					//in here means the tile is empty
					moves.add(new Coordinate(x, y));
				}
				else
				{
					//there is a piece on the tile
					//if the piece has a different color than this piece add it to moves then break 
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x, y));
					}
					break;
				}
			}
			else
			{
				//here the location is out of bounds so we break the loop
				break;
			}
		}
		
		x = location.getX();
		y = location.getY();
		
		keepGoing = true;
		
		//down and to the right x + 1 y + 1
		while(keepGoing)
		{
			x++;
			y++;
			//first check bounds
			//if the tile is empty add it an keep searching
			//if it is not empty then if it is a different color add it and break the loop other wise break the loop
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7)
			{
				//in here the location is in bounds
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					//in here means the tile is empty
					moves.add(new Coordinate(x, y));
				}
				else
				{
					//there is a piece on the tile
					//if the piece has a different color than this piece add it to moves then break 
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x, y));
					}
					break;
				}
			}
			else
			{
				//here the location is out of bounds so we break the loop
				break;
			}
			
			
		}
		
		x = location.getX();
		y = location.getY();
		
		keepGoing = true;
		
		//down and to the left x + 1 y - 1
		while(keepGoing)
		{
			x++;
			y--;
			//first check bounds
			//if the tile is empty add it an keep searching
			//if it is not empty then if it is a different color add it and break the loop other wise break the loop
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7)
			{
				//in here the location is in bounds
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					//in here means the tile is empty
					moves.add(new Coordinate(x, y));
				}
				else
				{
					//there is a piece on the tile
					//if the piece has a different color than this piece add it to moves then break 
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x, y));
					}
					break;
				}
			}
			else
			{
				//here the location is out of bounds so we break the loop
				break;
			}
			
			
		}
		
		x = location.getX();
		y = location.getY();
		
		keepGoing = true;
		
		//up and to the left x - 1 y - 1
		while(keepGoing)
		{
			x--;
			y--;
			//first check bounds
			//if the tile is empty add it an keep searching
			//if it is not empty then if it is a different color add it and break the loop other wise break the loop
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7)
			{
				//in here the location is in bounds
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					//in here means the tile is empty
					moves.add(new Coordinate(x, y));
				}
				else
				{
					//there is a piece on the tile
					//if the piece has a different color than this piece add it to moves then break 
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x, y));
					}
					break;
				}
			}
			else
			{
				//here the location is out of bounds so we break the loop
				break;
			}
			
			
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
