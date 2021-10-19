import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessDisplay extends JPanel{
	
	//the board with all the pieces to be displayed
	private Tile[][] board;
	
	//constructor takes in the board then updates the JPanel
	ChessDisplay(Tile[][] board)
	{
		this.board = board;
		update();

	}
	
	//changes the board to be displayed
	public void setBoard(Tile[][] b)
	{
		board = b;
	}
	
	public void update()
	{
		//the JPanel layout is first set to be a gridlayout
		setLayout(new GridLayout(8, 8));
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				//for each element in the 2d array the preferred size is set to 80X80 then it is added to the panel
				board[i][j].setPreferredSize(new Dimension(80,80));
				add(board[i][j]);
			}
		}
		

	}
	
	public void updateOpposite()
	{
		removeAll();
		setLayout(new GridLayout(8, 8));
		for(int i = 7; i >= 0; i--)
		{
			for(int j = 7; j >= 0; j--)
			{
				//for each element in the 2d array the preferred size is set to 80X80 then it is added to the panel
				board[i][j].setPreferredSize(new Dimension(80,80));
				add(board[i][j]);
			}
		}
	}
	

	
}
