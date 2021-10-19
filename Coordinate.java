public class Coordinate {
	
	//x and y locations
	private int x;
	private int y;
	
	Coordinate()
	{
		x = 0;
		y = 0;
	}
	
	Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
	
	public boolean equals(Object obj)
	{
		try
		{
			Coordinate c = (Coordinate) obj;
			if(c.getX() == x && c.getY() == y)
				return true;
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}