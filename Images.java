import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

//class with all of the image files loaded and a method for retrieving the correct one
public class Images {
	
	//Hashmap with all the photos
	private static HashMap<String, Image> images;
	
	static
	{
		images = new HashMap<String, Image>();
		try {
		//MISC
		images.put("yT", ImageIO.read(new File("ChessImages\\YellowTile.jpg")));
		images.put("bT", ImageIO.read(new File("ChessImages\\BlackTile.jpg")));
		images.put("wT", ImageIO.read(new File("ChessImages\\WhiteTile.jpg")));
		
		//White Tiles
		
		//White Pieces
		images.put("wPwT", ImageIO.read(new File("ChessImages\\wPwT.jpg")));
		images.put("wKNwT", ImageIO.read(new File("ChessImages\\wKNwT.jpg")));
		images.put("wBwT", ImageIO.read(new File("ChessImages\\wBwT.jpg")));
		images.put("wRwT", ImageIO.read(new File("ChessImages\\wRwT.jpg")));
		images.put("wQwT", ImageIO.read(new File("ChessImages\\wQwT.jpg")));
		images.put("wKwT", ImageIO.read(new File("ChessImages\\wKwT.jpg")));
		
		//Black Pieces
		images.put("bPwT", ImageIO.read(new File("ChessImages\\bPwT.jpg")));
		images.put("bKNwT", ImageIO.read(new File("ChessImages\\bKNwT.jpg")));
		images.put("bBwT", ImageIO.read(new File("ChessImages\\bBwT.jpg")));
		images.put("bRwT", ImageIO.read(new File("ChessImages\\bRwT.jpg")));
		images.put("bQwT", ImageIO.read(new File("ChessImages\\bQwT.jpg")));
		images.put("bKwT", ImageIO.read(new File("ChessImages\\bKwT.jpg")));
		
		//-----------------------------------------------------------------
		
		//Black Tiles
		
		//White Pieces
		images.put("wPbT", ImageIO.read(new File("ChessImages\\wPbT.jpg")));
		images.put("wKNbT", ImageIO.read(new File("ChessImages\\wKNbT.jpg")));
		images.put("wBbT", ImageIO.read(new File("ChessImages\\wBbT.jpg")));
		images.put("wRbT", ImageIO.read(new File("ChessImages\\wRbT.jpg")));
		images.put("wQbT", ImageIO.read(new File("ChessImages\\wQbT.jpg")));
		images.put("wKbT", ImageIO.read(new File("ChessImages\\wKbT.jpg")));
		
		//Black Pieces
		images.put("bPbT", ImageIO.read(new File("ChessImages\\bPbT.jpg")));
		images.put("bKNbT", ImageIO.read(new File("ChessImages\\bKNbT.jpg")));
		images.put("bBbT", ImageIO.read(new File("ChessImages\\bBbT.jpg")));
		images.put("bRbT", ImageIO.read(new File("ChessImages\\bRbT.jpg")));
		images.put("bQbT", ImageIO.read(new File("ChessImages\\bQbT.jpg")));
		images.put("bKbT", ImageIO.read(new File("ChessImages\\bKbT.jpg")));
		
		//-----------------------------------------------------------------
		
		//Yellow Tiles
		
		//White Pieces
		images.put("wPyT", ImageIO.read(new File("ChessImages\\wPyT.jpg")));
		images.put("wKNyT", ImageIO.read(new File("ChessImages\\wKNyT.jpg")));
		images.put("wByT", ImageIO.read(new File("ChessImages\\wByT.jpg")));
		images.put("wRyT", ImageIO.read(new File("ChessImages\\wRyT.jpg")));
		images.put("wQyT", ImageIO.read(new File("ChessImages\\wQyT.jpg")));
		images.put("wKyT", ImageIO.read(new File("ChessImages\\wKyT.jpg")));
		
		//Black Pieces
		images.put("bPyT", ImageIO.read(new File("ChessImages\\bPyT.jpg")));
		images.put("bKNyT", ImageIO.read(new File("ChessImages\\bKNyT.jpg")));
		images.put("bByT", ImageIO.read(new File("ChessImages\\bByT.jpg")));
		images.put("bRyT", ImageIO.read(new File("ChessImages\\bRyT.jpg")));
		images.put("bQyT", ImageIO.read(new File("ChessImages\\bQyT.jpg")));
		images.put("bKyT", ImageIO.read(new File("ChessImages\\bKyT.jpg")));
		
		//------------------------------------------------------------------
		}
		catch(IOException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	
	//returns the correct image based off the tile and the piece on that tile
	public static Image getImage(Tile t)
	{
		/*
		 * there are at most 3 parts to every image file
		 * color of piece
		 * piece identifier
		 * color of tile
		 * 
		 * if there is no piece on the tile the piece object will be a generic piece Piece p = new Piece();
		 * the getColor method of a piece returns the color when a default piece is created meaning no piece on the tile color is ""
		 * the generic piece identifier is "" this is overridden in children classes
		 * 
		 * If the tile is selected we want to display a yellow tile otherwise we display the tiles color
		 */
		return images.get(t.piece.getColor() + t.piece.getIdentifier() + (t.isSelected() ? "yT" : t.getColor()));
	}
	
}