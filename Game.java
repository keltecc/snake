package snake;

public class Game 
{    
	public Level level;
	
	public Game(int levelNumber) throws Exception
	{
		level = LevelParser.parse(levelNumber);
	}
	
	public void changeDirection(Point direction)
	{
		if (Point.getScalarProduct(level.snake.direction, direction) == 0)
			level.snake.direction = direction;
	}
	
	public void tick()
	{
		level.snake.makeStep();
	}
}
