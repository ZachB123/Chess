import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

public class ChessRunner extends JFrame{
	
	ChessRunner()
	{
		setSize(640,640);                                 
		setTitle("Chess");                                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		Board b = new Board(this);
		add(b.boardPanel);
		pack();              
		setVisible(true);                                 
	}
	
	public void update()
	{
		revalidate();
	}

	public static void main(String[] args) {
		/*
		//frame creating for chess
		JFrame frame = new JFrame();
		frame.setSize(640,640);
		frame.setTitle("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creates the board which initializes the tiles and stores them
		Board b = new Board();
		//the board has an instance variable of type ChessDisplay which is a JPanel
		//boardPanel has put all of the chess tiles into a grid
		frame.add(b.boardPanel);
		frame.pack();
		frame.setVisible(true);
		*/
		ChessRunner c = new ChessRunner();
	}

}
