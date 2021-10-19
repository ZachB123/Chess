import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
//the building block for the board
public class Tile extends JComponent{
	
	//color of the tile
	public static final String BLACK = "bT";
	public static final String WHITE = "wT";
	
	//the piece on the tile
	public Piece piece;
	
	//color of the tile 
	private String color;
	//if the user has clicked on the tile to make it yellow set to false in the constructor
	private boolean selected;
	
	//the board that the tile is a part of so it can make move requests and stuff
	private Board b;
	//the coordinate of this specific tile in relation to the board
	private Coordinate c;
	
	//main constructor takes a piece the color of the tile the board and coordinate of the tile
	Tile(Piece p, String color, Board b, Coordinate c)
	{
		piece = p;
		this.color = color;
		this.b = b;
		this.c = c;
		selected = false;
		addMouseListener(new Mouse());
	}
	//used when the tile is empty
	Tile(String color, Board b, Coordinate c)
	{
		this(new EmptyTile(c, b), color, b, c);
	}
	
	//changes the piece on the tile
	public void setPiece(Piece p)
	{
		piece = p;
	}
	
	//returns the piece on this tile
	public Piece getPiece()
	{
		return piece;
	}
	
	//paints the image of the correct piece onto the tile
	public void paintComponent(Graphics g)
	{
		g.drawImage(Images.getImage(this), 0, 0, null);
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public String getColor()
	{
		return color;
	}
	
	//used to repaint the tile
	public void update()
	{
		repaint();
	}
	
	//used change the selected boolean
	public void setSelected(boolean b)
	{
		selected = b;
	}
	
	//returns the coordinate of the tile
	public Coordinate getCords()
	{
		return c;
	}
	
	class Mouse implements MouseListener
	{
		
		public void mousePressed(MouseEvent e) {
			b.clickRequest(Tile.this);
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
}
