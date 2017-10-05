package snake;

public class Point 
{	
	private int x;
	private int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point clone()
	{
		return new Point(this.x, this.y);
	}
	
	public void add(Point point)
	{
		this.x += point.x;
		this.y += point.y;
	}
}
