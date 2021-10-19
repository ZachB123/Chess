import java.util.ArrayList;

public class Pawn extends Piece{
	
	//returns what piece it is for getting correct image
	public String getIdentifier()
	{
		return "P";
	}
	
	Pawn(String color, Coordinate c, Board b)
	{
		//create a pawn with color coordinate and board
		super(color,c,b);
	}
	
	//returns an array of all of the possible coordinates that this piece could move to takes into account if pieces are in the way but NOT if it will result in check for the person who is moving turn
	public ArrayList<Coordinate> getMoves()
	{	
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		//these indices are reserved for special moves
		//first three are promotion moves
		//0 is promotion by capturing left
		//1 is promotion by moving forward one
		//2 is promotion by capturing right
		//3 is an en passant
		moves.add(null);
		moves.add(null);
		moves.add(null);
		moves.add(null);
		
		//first we need to know what color piece the pawn is
		if(color.equals("w"))
		{
			//in here the pawn is a white pawn
			//first lets see if the move directly above the pawn is available
			//first we make sure that if moving the pawn up one will not go out of bounds
			//then we check if the location above the pawn is empty
			if(location.getX()-1 >= 0 && board.boardAtIsEmpty(new Coordinate(location.getX()-1,location.getY())))
			{
				//now we need to check if the pawn moving up one is a promotion
				//if the row is 0 that is the top row and therefore a promotion
				if(location.getX() - 1 == 0)
				{
					//since in here is a promotion we need to set the array at index 1 
					moves.set(1, new Coordinate(location.getX()-1,location.getY()));
				}
				else
				{
					//since it is not a promotion we just add the coordinate to the end of the array
					moves.add(new Coordinate(location.getX()-1,location.getY()));
				}
			}
			//now we will check if the pawn can move two spaces as its first turn
			//first we must make the that the moveNumber is 0
			//then we need to check that the two tiles in front of the pawn are cleared
			if(moveNumber == 0 && board.boardAtIsEmpty(new Coordinate(location.getX()-1,location.getY())) && board.boardAtIsEmpty(new Coordinate(location.getX()-2,location.getY())))
			{
				//adding the move of two up from the current pawn location
				moves.add(new Coordinate(location.getX() - 2, location.getY()));
			}
			//checking if the piece can capture to the left or right
			//first to the left
			//first check for out of bounds stuff
			//then check to make sure up and right one has a black piece
			if(location.getX() - 1 >= 0 && location.getY() - 1 >= 0 && board.getBoard()[location.getX()-1][location.getY()-1].piece.getColor().equals("b"))
			{
				//checking if the capture results in a promotion
				if(location.getX()-1 == 0)
				{
					moves.set(0, new Coordinate(location.getX()-1, location.getY()-1));
				}
				else
				{
					moves.add(new Coordinate(location.getX()-1, location.getY()-1));
				}
			}
			//now checking if the pawn can capture up and to the right
			if(location.getX() - 1 >= 0 && location.getY() + 1 <= 7 && board.getBoard()[location.getX()-1][location.getY()+1].piece.getColor().equals("b"))
			{
				//checking if the capture results in a promotion
				if(location.getX()-1 == 0)
				{
					moves.set(2, new Coordinate(location.getX()-1, location.getY()+1));
				}
				else
				{
					moves.add(new Coordinate(location.getX()-1, location.getY()+1));
				}
			}
			//now for the en passant stuff
			//first check the left side
			//en passants can only happen when the white pawn is on row three
			//en passants can only happen to the left if the column is at least one
			//the piece to the left has to be black and a pawn
			//the piece to the left moveNumber has to be one
			//the piece to the left coordinate has to be the same coordinate as the coordinate of the last moved pawn
			if(location.getX() == 3 && location.getY() >= 1 && board.getBoard()[location.getX()][location.getY()-1].piece.getIdentifier().equals("P") && board.getBoard()[location.getX()][location.getY()-1].piece.getColor().equals("b") && board.getBoard()[location.getX()][location.getY()-1].piece.getMoveNumber() == 1 && board.getLastMove().getCords().equals(new Coordinate(location.getX(),location.getY()-1)))
			{
				moves.set(3, new Coordinate(location.getX()-1,location.getY()-1));
			}
			//now to the right
			if(location.getX() == 3 && location.getY() <= 6 && board.getBoard()[location.getX()][location.getY()+1].piece.getIdentifier().equals("P") && board.getBoard()[location.getX()][location.getY()+1].piece.getColor().equals("b") && board.getBoard()[location.getX()][location.getY()+1].piece.getMoveNumber() == 1 && board.getLastMove().getCords().equals(new Coordinate(location.getX(),location.getY()+1)))
			{
				moves.set(3, new Coordinate(location.getX()-1,location.getY()+1));
			}
		}
		else if(color.equals("b"))
		{
			//in here the pawn is a black pawn
			//first lets see if the move directly below the pawn is available
			//first we make sure that if moving the pawn down one will not go out of bounds
			//then we check if the location below the pawn is empty
			if(location.getX()+1 <= 7 && board.boardAtIsEmpty(new Coordinate(location.getX()+1,location.getY())))
			{
				//now we need to check if the pawn moving up one is a promotion
				//if the row is 7 that is the bottom row and therefore a promotion
				if(location.getX() + 1 == 7)
				{
					//since in here is a promotion we need to set the array at index 1 
					moves.set(1, new Coordinate(location.getX()+1,location.getY()));
				}
				else
				{
					//since it is not a promotion we just add the coordinate to the end of the array
					moves.add(new Coordinate(location.getX()+1,location.getY()));
				}
			}
			
			//now we will check if the pawn can move two spaces as its first turn
			//first we must make the that the moveNumber is 0
			//then we need to check that the two tiles below from the pawn are cleared
			if(moveNumber == 0 && board.boardAtIsEmpty(new Coordinate(location.getX()+1,location.getY())) && board.boardAtIsEmpty(new Coordinate(location.getX()+2,location.getY())))
			{
				//adding the move of two up from the current pawn location
				moves.add(new Coordinate(location.getX() + 2, location.getY()));
			}
			
			//checking if the piece can capture to the left or right
			//first to the left
			//first check for out of bounds stuff
			//then check to make sure down and left one has a black piece
			if(location.getX() + 1 <= 7 && location.getY() - 1 >= 0 && board.getBoard()[location.getX()+1][location.getY()-1].piece.getColor().equals("w"))
			{
				//checking if the capture results in a promotion
				if(location.getX()+1 == 7)
				{
					moves.set(0, new Coordinate(location.getX()+1, location.getY()-1));
				}
				else
				{
					moves.add(new Coordinate(location.getX()+1, location.getY()-1));
				}
			}
			//now checking if the pawn can capture down and to the right
			if(location.getX() + 1 <= 7 && location.getY() + 1 <= 7 && board.getBoard()[location.getX()+1][location.getY()+1].piece.getColor().equals("w"))
			{
				//checking if the capture results in a promotion
				if(location.getX()+1 == 7)
				{
					moves.set(2, new Coordinate(location.getX()+1, location.getY()+1));
				}
				else
				{
					moves.add(new Coordinate(location.getX()+1, location.getY()+1));
				}
			}
			
			//now for the en passant stuff
			//first check the left side
			//en passants can only happen when the black pawn is on row four
			//en passants can only happen to the left if the column is at least one
			//the piece to the left has to be black and a pawn
			//the piece to the left moveNumber has to be one
			//the piece to the left coordinate has to be the same coordinate as the coordinate of the last moved pawn
			if(location.getX() == 4 && location.getY() >= 1 && board.getBoard()[location.getX()][location.getY()-1].piece.getIdentifier().equals("P") && board.getBoard()[location.getX()][location.getY()-1].piece.getColor().equals("w") && board.getBoard()[location.getX()][location.getY()-1].piece.getMoveNumber() == 1 && board.getLastMove().getCords().equals(new Coordinate(location.getX(),location.getY()-1)))
			{
				moves.set(3, new Coordinate(location.getX()+1,location.getY()-1));
			}
			//now to the right
			if(location.getX() == 4 && location.getY() <= 6 && board.getBoard()[location.getX()][location.getY()+1].piece.getIdentifier().equals("P") && board.getBoard()[location.getX()][location.getY()+1].piece.getColor().equals("w") && board.getBoard()[location.getX()][location.getY()+1].piece.getMoveNumber() == 1 && board.getLastMove().getCords().equals(new Coordinate(location.getX(),location.getY()+1)))
			{
				moves.set(3, new Coordinate(location.getX()+1,location.getY()+1));
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
			if(c.equals(getMoves().get(0)) || c.equals(getMoves().get(1)) || c.equals(getMoves().get(2)))
			{
				board.selectedTile.setPiece(new Queen(color, location, board));
			}
			if(c.equals(getMoves().get(3)))
			{
				//we need to check whether there is a pawn above or below the pawn that just moved then remove it
				//checking below the piece
				if(board.getBoard()[getMoves().get(3).getX()+1][getMoves().get(3).getY()].piece.getIdentifier().equals("P"))
				{
					Coordinate lol = new Coordinate(getMoves().get(3).getX()+1,getMoves().get(3).getY());
					board.enPassant(lol);
					board.getBoard()[lol.getX()][lol.getY()].update();
				}
				//checking above the piece
				else if(board.getBoard()[getMoves().get(3).getX()-1][getMoves().get(3).getY()].piece.getIdentifier().equals("P"))
				{
					Coordinate lol = new Coordinate(getMoves().get(3).getX()-1,getMoves().get(3).getY());
					board.enPassant(lol);
					board.getBoard()[lol.getX()][lol.getY()].update();
				}
			}
			return true;
		}
		return false;
	}
		
}
