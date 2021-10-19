import java.util.ArrayList;

public class King extends Piece{
	
	//returns what piece it is for getting correct image
	public String getIdentifier()
	{
		return "K";
	}
	
	King(String color, Coordinate c, Board b)
	{
		//create a pawn with color coordinate and board
		super(color,c,b);
	}
	
	//returns an array of all of the possible coordinates that this piece could move to takes into account if pieces are in the way but NOT if it will result in check for the person who is moving turn
	public ArrayList<Coordinate> getMoves()
	{
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		//if index 0 is not null a castle is possible on the left side
		//if index 1 is not null a castle is possible on the right side
		moves.add(null);
		moves.add(null);
		
		//first will check for regular moves around the king
		//then will see if a castle is possible
		
		int x = location.getX();
		int y = location.getY();
		
		//up
		if(x - 1 >= 0 && x - 1 <=7 && y >= 0 && y <= 7 && !(board.getBoard()[x-1][y].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x-1,y));
		}
		
		//up right
		if(x - 1 >= 0 && x - 1 <=7 && y + 1 >= 0 && y + 1 <= 7 && !(board.getBoard()[x-1][y + 1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x-1,y+1));
		}
		
		//right
		if(x >= 0 && x <=7 && y + 1 >= 0 && y + 1 <= 7 && !(board.getBoard()[x][y + 1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x,y+1));
		}
		
		//down right
		if(x+1 >= 0 && x+1 <=7 && y + 1 >= 0 && y + 1 <= 7 && !(board.getBoard()[x+1][y + 1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x+1,y+1));
		}
		
		//down
		if(x+1 >= 0 && x+1 <=7 && y >= 0 && y <= 7 && !(board.getBoard()[x+1][y].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x+1,y));
		}
		
		//down left
		if(x+1 >= 0 && x+1 <=7 && y - 1 >= 0 && y - 1 <= 7 && !(board.getBoard()[x+1][y - 1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x+1,y-1));
		}
		
		//left
		if(x >= 0 && x <=7 && y - 1 >= 0 && y - 1 <= 7 && !(board.getBoard()[x][y - 1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x,y-1));
		}
		
		//up left
		if(x-1 >= 0 && x-1 <=7 && y - 1 >= 0 && y - 1 <= 7 && !(board.getBoard()[x-1][y - 1].piece.getColor().equals(color)))
		{
			moves.add(new Coordinate(x-1,y-1));
		}
		
		//now for castling
		//king cannot have previously moved - moveNumber == 0
		//rook cannot have previously moved - board[x[y-4 is a white rook with 0 moves board[x[y+3 is a white rook with 0 moves
		//no pieces between king and rook - all identifiers between are ""
		//king is not in check !board.check
		//king cannot pass through squares under attack underAttack board pass in the color of the king but flipped and the tile piece there
		//result cannot be check - done automatically in board class
		
		//first checking if castling to the left is possible
		if(moveNumber == 0 && board.getBoard()[location.getX()][location.getY()-4].piece.getIdentifier().equals("R") && board.getBoard()[location.getX()][location.getY()-4].piece.getColor().equals(color) && board.getBoard()[location.getX()][location.getY()-4].piece.getMoveNumber() == 0 && board.getBoard()[location.getX()][location.getY()-1].piece.getIdentifier().equals("") && board.getBoard()[location.getX()][location.getY()-2].piece.getIdentifier().equals("") && board.getBoard()[location.getX()][location.getY()-3].piece.getIdentifier().equals("") && !(board.underAttackK(board.getBoard()[location.getX()][location.getY()-1], color.equals("w") ? "b" : color.equals("b") ? "w" : color)) && !(board.underAttackK(board.getBoard()[location.getX()][location.getY()-2], color.equals("w") ? "b" : color.equals("b") ? "w" : color)))
		{
			//now a special algorithm is needed to see if this king is in check
			boolean inCheck = false;
			
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					//iterating through the board
					//will check the possible moves if the piece matches the color passed in
					if(board.getBoard()[i][j].piece.getColor().equals(color.equals("w") ? "b" : color.equals("b") ? "w" : color) && !(board.getBoard()[i][j].piece.getIdentifier().equals("K")))
					{
						//checking if the pieces possible moves contains the coordinates of the tile
						if(board.getBoard()[i][j].piece.getMoves().contains(location))
						{
							inCheck = true;
						}
					}
				}
			}
			
			if(!inCheck)
			{
				if(color.equals("w"))
				{
					//need to make sure that 6,1 6,2 6,3 6,4 do not have black pawns
					if(!((board.getBoard()[6][1].piece.getColor().equals("b") && board.getBoard()[6][1].piece.getIdentifier().equals("P")) || (board.getBoard()[6][2].piece.getColor().equals("b") && board.getBoard()[6][2].piece.getIdentifier().equals("P")) || (board.getBoard()[6][3].piece.getColor().equals("b") && board.getBoard()[6][3].piece.getIdentifier().equals("P")) || (board.getBoard()[6][4].piece.getColor().equals("b") && board.getBoard()[6][4].piece.getIdentifier().equals("P"))))
					{
						moves.set(0, new Coordinate(location.getX(),location.getY()-2));
					}
				}
				else if(color.equals("b"))
				{
					//need to make sure that 1,1 1,2 1,3 1,4 do not have white pawns
					if(!((board.getBoard()[1][1].piece.getColor().equals("w") && board.getBoard()[1][1].piece.getIdentifier().equals("P")) || (board.getBoard()[1][2].piece.getColor().equals("w") && board.getBoard()[1][2].piece.getIdentifier().equals("P")) || (board.getBoard()[1][3].piece.getColor().equals("w") && board.getBoard()[1][3].piece.getIdentifier().equals("P")) || (board.getBoard()[1][4].piece.getColor().equals("w") && board.getBoard()[1][4].piece.getIdentifier().equals("P"))))
					{
						moves.set(0, new Coordinate(location.getX(),location.getY()-2));
					}
				}

			}
		}
		
		//checking to the right
		if(moveNumber == 0 && board.getBoard()[location.getX()][location.getY()+3].piece.getIdentifier().equals("R") && board.getBoard()[location.getX()][location.getY()+3].piece.getColor().equals(color) && board.getBoard()[location.getX()][location.getY()+3].piece.getMoveNumber() == 0 && board.getBoard()[location.getX()][location.getY()+1].piece.getIdentifier().equals("") && board.getBoard()[location.getX()][location.getY()+2].piece.getIdentifier().equals("") && !(board.underAttackK(board.getBoard()[location.getX()][location.getY()+1], color.equals("w") ? "b" : color.equals("b") ? "w" : color)) && !(board.underAttackK(board.getBoard()[location.getX()][location.getY()+2], color.equals("w") ? "b" : color.equals("b") ? "w" : color)))
		{
			//now a special algorithm is needed to see if this king is in check
			boolean inCheck = false;
			
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					//iterating through the board
					//will check the possible moves if the piece matches the color passed in
					if(board.getBoard()[i][j].piece.getColor().equals(color.equals("w") ? "b" : color.equals("b") ? "w" : color) && !(board.getBoard()[i][j].piece.getIdentifier().equals("K")))
					{
						//checking if the pieces possible moves contains the coordinates of the tile
						if(board.getBoard()[i][j].piece.getMoves().contains(location))
						{
							inCheck = true;
						}
					}
				}
			}
			
			if(!inCheck)
			{
				if(color.equals("w"))
				{
					//checking that 6,4 6,5 6,6 do not have black pawns
					if(!((board.getBoard()[6][4].piece.getColor().equals("b") && board.getBoard()[6][4].piece.getIdentifier().equals("P")) || (board.getBoard()[6][5].piece.getColor().equals("b") && board.getBoard()[6][5].piece.getIdentifier().equals("P")) || (board.getBoard()[6][6].piece.getColor().equals("b") && board.getBoard()[6][6].piece.getIdentifier().equals("P"))))
					{
						moves.set(1, new Coordinate(location.getX(),location.getY()+2));
					}
				}
				else if(color.equals("b"))
				{
					//checking that 1,4 1,5 1,6 do not have white pawns
					if(!((board.getBoard()[1][4].piece.getColor().equals("w") && board.getBoard()[1][4].piece.getIdentifier().equals("P")) || (board.getBoard()[1][5].piece.getColor().equals("w") && board.getBoard()[1][5].piece.getIdentifier().equals("P")) || (board.getBoard()[1][6].piece.getColor().equals("w") && board.getBoard()[1][6].piece.getIdentifier().equals("P"))))
					{
						moves.set(1, new Coordinate(location.getX(),location.getY()+2));
					}
					
				}
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
			if(getMoves().get(0) != null)
			{
				if(c.getY() == 2)
				{
					board.castleLeft(color);
				}
			}
			if(getMoves().get(1) != null)
			{
				if(c.getY() == 6)
				{
					board.castleRight(color);
				}
			}
			return true;
		}
		return false;
	}

}
