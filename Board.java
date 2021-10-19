import java.util.concurrent.TimeUnit;

public class Board{
	
	//The 2d array containing all the tiles for the board
	private Tile[][] board;
	//the JPanel used to display all the tiles
	public ChessDisplay boardPanel;
	
	//current turn, w is whites turn and b is blacks turn
	private String turn;
	
	//the tile that is currently selected and ready to move
	public Tile selectedTile;
	
	//this is the tile where the last piece that was moved went
	private Tile lastMove;
	
	//this is the big boi method called when any tile is clicked to determine what should happen
	public void clickRequest(Tile t)
	{
		//if there is no current tile selected and t can be selected then the tile passed into method will be selected
		//t can be selected if its piece color is the same as the turn
		if((selectedTile == null) && t.piece.getColor().equals(turn))
		{
			//in here means the tile can be selected and there are no selected tiles
			//we need to set the selected tile to t
			//set the selected boolean on t to be true
			//update t to repaint it
			selectedTile = t;
			t.setSelected(true);
			t.update();
		}
		//we need to check if a tile is already selected and if the click means to actually move the piece
		//checks if the selected piece, which is the piece we want to move, if its possible moves correspond with the location of the tile we just hit
		else if(selectedTile != null && !(moveResultsInCheck(t)) && selectedTile.piece.getMovesContains(t.getCords()))
		{
			//if we are in here that means that the tile just selected is a valid location for the selected tile
			//first we must make sure that if the move is made the king of the current turn will not be in check after that
			if(!moveResultsInCheck(t))
			{
				//in here the move does not result in check
				//so we move the piece and change turn
				move(t);
				changeTurn();
				c.update();
				//after we change turn we check if king of the opposite color that just moved is in checkmate or if the game is a draw
				if(mate())
				{
					System.out.println("Checkmate");
					//turn is set to goblygoop so you can't select any pieces effectively ending the game
					turn = "goblygoop";
				}
				else if(draw())
				{
					System.out.println("Draw");
					turn = "goblygoop";
				}
			}
			else
			{
				System.out.println("TURN RESULTS IN CHECK");
				//runs if the move results in check to reset what you clicked on
				resetTurn();
			}
		}
		//if we get to the else we have clicked on something funky and should reset the turn
		else
		{
			resetTurn();
		}
		
	}
	
	//returns true if the king of the current turn is in check
	public boolean check()
	{
		//we need to find the king then pass that location into underAttack
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				//iterating through the board
				if(board[i][j].piece.getColor().equals(turn) && board[i][j].piece.getIdentifier().equals("K"))
				{
					//in here we have found the king
					return underAttack(board[i][j], turn.equals("w") ? "b" : turn.equals("b") ? "w" : turn);
				}
			}
		}
		return false;
	}
	
	//returns true if the king of the current turn is in checkmate
	public boolean mate()
	{
		//first the king has to be in check
		if(check())
		{
			//now we need to go through all of the kings move to see if that would still result in a check
			//first we need to find the king
			Coordinate kingCoordinate= new Coordinate(-1,-1);
			boolean end = false;
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					if(board[i][j].piece.getIdentifier().equals("K") && board[i][j].piece.getColor().equals(turn))
					{
						kingCoordinate = new Coordinate(i,j);
						end = true;
						break;
					}
				}
				if(end)
				{
					break;
				}
			}

			//now for each of the kings possible moves we need to see if that square is under attack by the color opposite the turn
			boolean everySquareUnderAttack = true;
			//c is looping through all of the possible king moves
			for(Coordinate c : board[kingCoordinate.getX()][kingCoordinate.getY()].piece.getMoves())
			{
				if(c != null)
				{
					//we need to move the king to c
					//temp storage for the piece that was on c
					Piece pieceOnC = board[c.getX()][c.getY()].getPiece();
					//moving the piece
					board[c.getX()][c.getY()].setPiece(board[kingCoordinate.getX()][kingCoordinate.getY()].getPiece());
					board[kingCoordinate.getX()][kingCoordinate.getY()].setPiece(new Piece(new Coordinate(kingCoordinate.getX(),kingCoordinate.getY()), this));
					
					if(!underAttack(board[c.getX()][c.getY()], turn.equals("w") ? "b" : turn.equals("b") ? "w" : turn))
					{
						everySquareUnderAttack = false;
					}
					
					//moving everything back
					board[kingCoordinate.getX()][kingCoordinate.getY()].setPiece(board[c.getX()][c.getY()].getPiece());
					board[c.getX()][c.getY()].setPiece(pieceOnC);
				}
			}
			
			if(everySquareUnderAttack)
			{				
				//then we need to go though every other piece moves to make sure that the result is still a check
				//we need to take everypiece of the current turn
				//move them to every possible location
				//then see if the king is in check if it isn't set checkmate to false
				//then move the piece back
				boolean checkmate = true;
				
				for(int i = 0; i < 8; i++)
				{
					for(int j = 0; j < 8; j++)
					{
						if(board[i][j].piece.getColor().equals(turn) && !(board[i][j].piece.getIdentifier().equals("K")))
						{
							//in here we have the location of a piece of the current turn which is not the king
							//time to iterate through all of their possible moves
							for(Coordinate c : board[i][j].piece.getMoves())
							{
								if(c != null)
								{
									//we need to save the piece on c so we can put it back
									Piece pieceOnC = board[c.getX()][c.getY()].getPiece();
									//now moving the pieces
									board[c.getX()][c.getY()].setPiece(board[i][j].getPiece());
									board[i][j].setPiece(new Piece(new Coordinate(i,j), this));
									//ok piece is moved now lets see if the king is not in check and if it isn't then checkmate is false
									if(!check())
									{
										checkmate = false;
									}
									//now move everything back
									board[i][j].setPiece(board[c.getX()][c.getY()].getPiece());
									board[c.getX()][c.getY()].setPiece(pieceOnC);
								}
							}
						}
					}
				}
				
				if(checkmate)
				{
					return true;
				}

				
			}
			
		}
		return false;
	}
	
	//returns true if the game is drawn
	public boolean draw()
	{
		//types of draws
		//stalemate king is not in check but he has no legal moves for any of his pieces
		//insufficient material
		//king v king
		//king bishop v king
		//king knight v king
		//king bishop v king bishop both bishops same color
		
		//first we will check for insufficent material
		int turnKnights = 0;
		int turnBishops = 0;
		
		String bishopColor = "";
		
		int notTurnKnights = 0;
		int notTurnBishops = 0;
		
		String notBishopColor = "";
		
		boolean insufficientMaterial = true;
		boolean go = true;
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				//first check if it is any piece other than a knight bishop king or empty piece
				if(board[i][j].piece.getIdentifier().equals("Q") || board[i][j].piece.getIdentifier().equals("R") || board[i][j].piece.getIdentifier().equals("P"))
				{
					insufficientMaterial = false;
					go = false;
					break;
				}
				else if(true)
				{
					if(board[i][j].piece.getIdentifier().equals("B"))
					{
						if(board[i][j].piece.color.equals(turn))
						{
							turnBishops++;
							bishopColor = board[i][j].getColor();
						}
						if(!(board[i][j].piece.color.equals(turn)))
						{
							notTurnBishops++;
							notBishopColor = board[i][j].getColor();
						}
					}
					else if(board[i][j].piece.getIdentifier().equals("KN"))
					{
						if(board[i][j].piece.color.equals("turn"))
						{
							turnKnights++;
						}
						else
						{
							notTurnKnights++;
						}
					}
				}
			}
			if(!go)
			{
				break;
			}
		}
		
		if(insufficientMaterial)
		{
			//now to test for insufficient material
			//first king v king
			if(turnKnights + turnBishops + notTurnKnights + notTurnBishops == 0)
			{
				System.out.println("King V King Draw");
				return true;
			}
			//king and knight
			if(turnKnights + notTurnKnights == 1 && turnBishops + notTurnBishops == 0)
			{
				System.out.println("One Knight and a king draw");
				return true;
			}
			//king and bishop
			if(turnBishops + notTurnBishops == 1 && turnKnights + notTurnKnights == 0)
			{
				System.out.println("One Bishop and a king draw");
				return true;
			}
			//king and 2 bishops of same color
			if(turnBishops + notTurnBishops == 2 && turnKnights + notTurnKnights == 0 && bishopColor.equals(notBishopColor))
			{
				System.out.println("2 bishop on same color and king draw");
				return true;
			}
		}
		
		//ok now for stalemate
		
		//first the king cannot be in check
		boolean stalemate = true;
		if(!check())
		{
			Coordinate kingCoordinate = new Coordinate(-1,-1);
			//first we need to find the location of the king
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					//iterating through the board
					if(board[i][j].piece.getColor().equals(turn) && board[i][j].piece.getIdentifier().equals("K"))
					{
						//in here we have found the king
						kingCoordinate = new Coordinate(i,j);
					}
				}
			}
			
			//now we need to loop through all of the kings possible moves
			for(Coordinate c : board[kingCoordinate.getX()][kingCoordinate.getY()].piece.getMoves())
			{
				if(c != null)
				{
					//now we need to move the king to all of those locations then check if the king is in check if the king is not in check stalemate is false
					//we need to save the piece on c so we can put it back
					Piece pieceOnC = board[c.getX()][c.getY()].getPiece();
					//now moving the pieces
					board[c.getX()][c.getY()].setPiece(board[kingCoordinate.getX()][kingCoordinate.getY()].getPiece());
					board[kingCoordinate.getX()][kingCoordinate.getY()].setPiece(new Piece(new Coordinate(kingCoordinate.getX(),kingCoordinate.getY()), this));
					//ok piece is moved now lets see if the king is not in check and if it isn't then stalemate is false
					if(!check())
					{
						stalemate = false;
					}
					//now move everything back
					board[kingCoordinate.getX()][kingCoordinate.getY()].setPiece(board[c.getX()][c.getY()].getPiece());
					board[c.getX()][c.getY()].setPiece(pieceOnC);
				}
				
			}
			//ok so the king has no valid moves and is not in check 
			//now we need to see if there are any other valid moves for any of blacks pieces
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					//iterating through the board
					if(board[i][j].piece.getColor().equals(turn))
					{
						//here we have a piece on the same turn
						//now get the list of possible moves and if any of those moves are not null then stalemate = false
						for(Coordinate c : board[i][j].piece.getMoves())
						{
							if(c != null)
							{
								stalemate = false;
							}
						}
					}
				}
			}
		}
		else
		{
			stalemate = false;
		}
		
		if(stalemate)
		{
			System.out.println("STALEMATE");
			return true;
		}
		
		return false;
	}
	
	public boolean moveResultsInCheck(Tile t)
	{
		//the piece that is originally on T
		Piece pieceOnT = t.getPiece();
		
		//now doing the moving
		t.setPiece(selectedTile.getPiece());
		selectedTile.setPiece(new Piece(selectedTile.getCords(), this));
		
		boolean check = check();
		
		//reverting the board back
		selectedTile.setPiece(t.getPiece());
		t.setPiece(pieceOnT);
		return check;
	}
	
	//determines if any of the pieces of the color passed into this method can attack the coordinate of the tile passed in
	public boolean underAttack(Tile t, String color)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				//iterating through the board
				//will check the possible moves if the piece matches the color passed in
				if(board[i][j].piece.getColor().equals(color))
				{
					//checking if the pieces possible moves contains the coordinates of the tile
					if(board[i][j].piece.getMoves().contains(t.getCords()))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//this is a special method so when both kings can castle the program doesn't crash
	//its the same as underAttack except it doesn't check if the king can attack the tile
	public boolean underAttackK(Tile t, String color)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				//iterating through the board
				//will check the possible moves if the piece matches the color passed in and make sure that its not a king
				if(board[i][j].piece.getColor().equals(color) && !(board[i][j].piece.getIdentifier().equals("K")))
				{
					//checking if the pieces possible moves contains the coordinates of the tile
					if(board[i][j].piece.getMoves().contains(t.getCords()))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//moves the selectedTile piece to the Tile passed into this method
	public void move(Tile t)
	{
		selectedTile.piece.incrementMove();
		lastMove = t;
		t.setPiece(selectedTile.getPiece());
		t.piece.setLocation(t.getCords());
		selectedTile.setPiece(new Piece(selectedTile.getCords(), this));
		t.update();
	}
	
	public void castleRight(String color)
	{
		//if white we need to move the white rook to 7,5
		if(color.equals("w"))
		{
			board[7][5].setPiece(new Rook("w", new Coordinate(7, 5), this));
			board[7][7].setPiece(new Piece(new Coordinate(7, 7), this));
			board[7][5].update();
			board[7][7].update();
		}	
		//if black we need to move the black rook to 0,5
		else if(color.equals("b"))
		{
			board[0][5].setPiece(new Rook("b", new Coordinate(0, 5), this));
			board[0][7].setPiece(new Piece(new Coordinate(0, 7), this));
			board[0][5].update();
			board[0][7].update();
		}
	}
	
	public void castleLeft(String color)
	{
		//System.out.println("Castle left");
		//if white we need to move the white rook to 7,3
		if(color.equals("w"))
		{
			board[7][3].setPiece(new Rook("w", new Coordinate(7, 3), this));
			board[7][0].setPiece(new Piece(new Coordinate(7, 0), this));
			board[7][3].update();
			board[7][0].update();
		}
		//if black move the black rook to 0,3
		else if(color.equals("b"))
		{
			board[0][3].setPiece(new Rook("b", new Coordinate(0, 3), this));
			board[0][0].setPiece(new Piece(new Coordinate(0, 0), this));
			board[0][3].update();
			board[0][0].update();
		}
	}
	
	//this method is used to clear all selected tiles but NOT to change who's turn it is
	//used when user clicks something that doesn't make sense to reset everything
	public void resetTurn()
	{
		//since the selectedTile is being discarded we need to unselect it then update it
		//this if is only run if u hit an invalid move 
		if(selectedTile != null)
		{
			selectedTile.setSelected(false);
			selectedTile.update();
		}
		selectedTile = null;
	}
	
	//used to switch the turn from white to black and black to white
	public void changeTurn()
	{
		selectedTile.setSelected(false);
		selectedTile.update();
		selectedTile = null;
		turn = turn.equals("w") ? "b" : turn.equals("b") ? "w" : turn;
		if(turn.equals("w"))
		{
			boardPanel.update();
		}
		else
		{
			boardPanel.update();
		}
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				board[i][j].update();
			}
		}
	}
	
	public Tile[][] getBoard()
	{
		return board;
	}
	
	//returns true if the board at a given location has no piece
	public boolean boardAtIsEmpty(Coordinate c)
	{
		//if the identifier of a piece is "" there is no piece there
		if(board[c.getX()][c.getY()].piece.getIdentifier().equals(""))
			return true;
		return false;
	}
	
	public Tile getLastMove()
	{
		return lastMove;
	}
	
	//sets the tile at the coordinate to have no piece
	public void enPassant(Coordinate c)
	{
		board[c.getX()][c.getY()].setPiece(new Piece(c,this));
	}
	
	private ChessRunner c;
	Board(ChessRunner c)
	{
		this.c = c;
		//starts the game off with whites turn
		turn = "w";
		//selectedTile is null because nothing is selected at the start of the game
		selectedTile = null;
		
		//lastMove is null because nothing has moved yet
		lastMove = null;
		
		board = new Tile[8][8];
		//setting up all the tiles to be empty tiles with the correct colors
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				//default set-up is an empty tile and correctly set to white or black tiles then it passes in this board object and the coordinate based on location in 2d array
				board[i][j] = new Tile(new Piece(new Coordinate(i,j), this), ((i + j) % 2 == 0 ? Tile.WHITE : Tile.BLACK), this, new Coordinate(i,j));
			}
		}
		//setting up individual pieces
		board[0][0].setPiece(new Rook(Piece.BLACK, new Coordinate(0,0), this));
		board[0][1].setPiece(new Knight(Piece.BLACK, new Coordinate(0,1), this));
		board[0][2].setPiece(new Bishop(Piece.BLACK, new Coordinate(0,2), this));
		board[0][3].setPiece(new Queen(Piece.BLACK, new Coordinate(0,3), this));
		board[0][4].setPiece(new King(Piece.BLACK, new Coordinate(0,4), this));
		board[0][5].setPiece(new Bishop(Piece.BLACK, new Coordinate(0,5), this));
		board[0][6].setPiece(new Knight(Piece.BLACK, new Coordinate(0,6), this));
		board[0][7].setPiece(new Rook(Piece.BLACK, new Coordinate(0,7), this));
		
		
		board[1][0].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,0), this));
		board[1][1].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,1), this));
		board[1][2].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,2), this));
		board[1][3].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,3), this));
		board[1][4].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,4), this));
		board[1][5].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,5), this));
		board[1][6].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,6), this));
		board[1][7].setPiece(new Pawn(Piece.BLACK, new Coordinate(1,7), this));
		
		board[6][0].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,0), this));
		board[6][1].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,1), this));
		board[6][2].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,2), this));
		board[6][3].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,3), this));
		board[6][4].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,4), this));
		board[6][5].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,5), this));
		board[6][6].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,6), this));
		board[6][7].setPiece(new Pawn(Piece.WHITE, new Coordinate(6,7), this));
		
		board[7][0].setPiece(new Rook(Piece.WHITE, new Coordinate(7,0), this));
		board[7][1].setPiece(new Knight(Piece.WHITE, new Coordinate(7,1), this));
		board[7][2].setPiece(new Bishop(Piece.WHITE, new Coordinate(7,2), this));
		board[7][3].setPiece(new Queen(Piece.WHITE, new Coordinate(7,3), this));
		board[7][4].setPiece(new King(Piece.WHITE, new Coordinate(7,4), this));
		board[7][5].setPiece(new Bishop(Piece.WHITE, new Coordinate(7,5), this));
		board[7][6].setPiece(new Knight(Piece.WHITE, new Coordinate(7,6), this));
		board[7][7].setPiece(new Rook(Piece.WHITE, new Coordinate(7,7), this));
		//initializes the boardPanel then sends in this board with all the pieces in place to be displayed
		boardPanel = new ChessDisplay(board);
	}
}
