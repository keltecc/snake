package snake;

public class Portal extends MapObject
{
	private Point destination;
	
	public Portal(Point position) 
	{
		super(position);
		destination = null;
	}
	
	public Portal(int x, int y)
	{
		super(x, y);
		destination = null;
	}

	public void interact(Level level)
	{
		if (destination == null)
			destination = findPosition(level.snake, level.map);
		
		Point head = level.snake.getTrace()[0];
		head.x = destination.x;
		head.y = destination.y;
	}
}
