package snake;

import java.io.FileNotFoundException;

public class Game 
{    
	private Level level;
	
	public boolean isOver;
	
	public Game(int levelNumber) throws Exception
	{
		isOver = false;
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
		Point snakeHead = level.snake.getHead();
		if (level.map.get(snakeHead.x, snakeHead.y) == MapObject.WALL)
			isOver = true;
	}
}
