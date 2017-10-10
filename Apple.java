package snake;

public class Apple implements EatingObject
{
	private Point location;
	
	public Apple(Point location)
	{
		this.location = location;
	}
	
	public EatingResult interact(Level level)
	{
		level.snake.adjustLength(1);
		level.map.removeFood(this);
		return new EatingResult(false);
	}
	
	public Point getLocation()
	{
		return location.clone();
	}
}
