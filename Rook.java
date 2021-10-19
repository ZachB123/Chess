import java.util.ArrayList;

public class Rook extends Piece{
	
	//returns what piece it is for getting correct image
	public String getIdentifier()
	{
		return "R";
	}
	
	Rook(String color, Coordinate c, Board b)
	{
		//create a pawn with color coordinate and board
		super(color,c,b);
	}
	
	//returns an array of all of the possible coordinates that this piece could move to takes into account if pieces are in the way but NOT if it will result in check for the person who is moving turn
	public ArrayList<Coordinate> getMoves()
	{
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		//first we will check the bounds looping through each time
		//then if the space is clear we will add it to moves
		//if there is a piece there thats not the same color add it then break
		
		boolean keepGoing = true;
		
		int x = location.getX();
		int y = location.getY();
		
		//up x--
		while(keepGoing)
		{
			x--;
			//checking bounds
			if(x >= 0 && x <= 7)
			{
				//checking if the tile is clear
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					moves.add(new Coordinate(x,y));
				}
				else
				{
					//in here there is something on the tile so if it is not the same color we will add it then break
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x,y));
					}
					break;
				}
			}
			else
			{
				//we have gone out of bounds so break the loop
				break;
			}
		}
		
		keepGoing = true;
		
		x = location.getX();
		y = location.getY();
		
		//down x++
		while(keepGoing)
		{
			x++;
			//checking bounds
			if(x >= 0 && x <= 7)
			{
				//checking if the tile is clear
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					moves.add(new Coordinate(x,y));
				}
				else
				{
					//in here there is something on the tile so if it is not the same color we will add it then break
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x,y));
					}
					break;
				}
			}
			else
			{
				//we have gone out of bounds so break the loop
				break;
			}
		}
		
		keepGoing = true;
		
		x = location.getX();
		y = location.getY();
		
		//left y--
		while(keepGoing)
		{
			y--;
			//checking bounds
			if(y >= 0 && y <= 7)
			{
				//checking if the tile is clear
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					moves.add(new Coordinate(x,y));
				}
				else
				{
					//in here there is something on the tile so if it is not the same color we will add it then break
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x,y));
					}
					break;
				}
			}
			else
			{
				//we have gone out of bounds so break the loop
				break;
			}
		}
		
		keepGoing = true;
		
		x = location.getX();
		y = location.getY();
		
		//right y++
		while(keepGoing)
		{
			y++;
			//checking bounds
			if(y >= 0 && y <= 7)
			{
				//checking if the tile is clear
				if(board.getBoard()[x][y].piece.getColor().equals(""))
				{
					moves.add(new Coordinate(x,y));
				}
				else
				{
					//in here there is something on the tile so if it is not the same color we will add it then break
					if(!(board.getBoard()[x][y].piece.getColor().equals(color)))
					{
						moves.add(new Coordinate(x,y));
					}
					break;
				}
			}
			else
			{
				//we have gone out of bounds so break the loop
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
