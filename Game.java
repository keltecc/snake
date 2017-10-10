package snake;

public class Game 
{    
	private final int MAX_FOOD_COUNT = 3;
	
	public Level level;
	public boolean isOver;
	
	public Game()
	{
		isOver = false;
	}
	
	public void loadLevel(int number)
	{
		try
		{
			level = LevelParser.parse(number);
		}
		catch (Exception ex)
		{
			isOver = true;
		}
	}
	
	public void changeDirection(Point direction)
	{
		if (Point.getScalarProduct(level.snake.direction, direction) == 0)
			level.snake.direction = direction;
	}
	
	public void tick()
	{
		if (checkCollisions())
		{
			isOver = true;
			return;
		}
		level.snake.makeStep();
		if (level.map.foodCount() < MAX_FOOD_COUNT)
			level.map.addFood(new Apple(level.map.getFreeCell()));
	}
	
	private boolean checkCollisions()
	{
		Point head = level.snake.getPart(0).clone();
		head.add(level.snake.direction);
		EatingObject object = level.map.getFoodAt(head);
		EatingResult result = null;
		if (object != null)
		{
			result = object.interact(level);
		}
		return (result == null || result != null && result.isGameOver)
				&& level.map.getTerrain(head.x, head.y);
	}
}
