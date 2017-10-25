package snake;

import java.util.Random;

public abstract class MapObject 
{
	private Point position;
	
	public MapObject(Point position)
	{
		setPosition(position);
	}
	
	public MapObject(int x, int y)
	{
		setPosition(new Point(x, y));
	}
	
	public Point getPosition()
	{
		return position.clone();
	}
	
	public void setPosition(Point position)
	{
		this.position = position;
	}
	
	public void interact(Level level) {}
	
	protected Point findPosition(Snake snake, GameMap map)
	{
		double expectedDistanceToSnake = 5;
		Point[] trace = snake.getTrace();
		Point point = position.clone();
		Random random = new Random();
		do
		{
			point.x = random.nextInt(map.width());
			point.y = random.nextInt(map.height());
		}
		while (map.getObject(point.x, point.y) != null || 
			   getDistanceToSnake(point, trace) < expectedDistanceToSnake);
		return point;
	}
	
	private double getDistanceToSnake(Point point, Point[] snakeTrace)
	{
		double distance = 0;
		for (Point part : snakeTrace)
			distance = Math.max(Point.getDistance(part, point), distance);
		return distance;
	}
}
