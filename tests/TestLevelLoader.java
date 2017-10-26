package snake.tests;

import snake.*;

public class TestLevelLoader implements LevelProvider 
{
	@Override
	public int getLevelsCount() throws Exception 
	{
		return 1;
	}

	@Override
	public Level load(int number) throws Exception 
	{
		return new Level(generateMap(), generateSnake(), 3);
	}
	
	private GameMap generateMap()
	{
		GameMap map = new GameMap(5, 5);
		map.setObject(1, 0, new Apple(1, 0));
		map.setObject(2, 0, new Wall(2, 0));
		return map;
	}
	
	private Snake generateSnake()
	{
		return new Snake(new Point(0, 0), Direction.RIGHT, 1);
	}
}
