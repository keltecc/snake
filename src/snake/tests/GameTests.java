package snake.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import snake.*;

public class GameTests 
{
	@Test
	public void ouroborosTest() 
	{
		Level level = buildLevel(5);
		level.snake.setDirection(Direction.DOWN);
		level.tick();
		level.snake.setDirection(Direction.LEFT);
		level.tick();
		level.snake.setDirection(Direction.UP);
		assertEquals(level.state, LevelState.PLAYING);
		level.tick();
		assertEquals(level.state, LevelState.FAILED);
	}
	
	@Test
	public void appleTest()
	{
		Level level = buildLevel(1);
		Vector appleLocation = new Vector(1, 0);
		level.map.set(appleLocation, new Apple(level, appleLocation));
		assertEquals(level.snake.getLength(), 1);
		level.tick();
		assertNull(level.map.get(appleLocation));
		level.tick();
		assertEquals(level.snake.getLength(), 2);
		level.tick();
		boolean found = false;
		for (int x = 0; x < level.map.getSize().x; x++)
			for (int y = 0; y < level.map.getSize().y; y++)
				if (level.map.get(new Vector(x, y)) instanceof Apple)
					found = true;
		assertTrue(found);
	}
	
	@Test
	public void wallTest()
	{
		Level level = buildLevel(1);
		level.map.set(new Vector(1, 0), new Wall(level, new Vector(1, 0)));
		level.tick();
		assertEquals(level.state, LevelState.FAILED);
	}

	private Level buildLevel(int snakeLength)
	{
		Level level = new Level();
		level.map = new GameMap(new Vector(5, 5));
		level.snake = new Snake(new Vector(0, 0), Direction.RIGHT, level.map.getSize());
		level.snake.adjustLength(snakeLength - 1);
		while (--snakeLength > 0)
			level.tick();
		return level;
	}
}
