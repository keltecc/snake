package snake.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import snake.*;

public class LevelTests 
{
	@Test
	public void findingTest() 
	{
		Level level = buildLevel();
		for (Vector vector : new Vector[] {new Vector(1, 1), new Vector(2, 2), new Vector(3, 3)})
			level.map.set(vector, new Wall(level, vector));
		for (int i = 0; i < 10; i++)
			assertNull(level.map.get(level.findFreeCell()));
	}
	
	@Test
	public void effectsTest()
	{
		Level level = buildLevel();
		level.addEffect(new Effect(l -> l.state = LevelState.FAILED, 2, level));
		level.tick();
		assertEquals(level.state, LevelState.PLAYING);
		level.tick();
		assertEquals(level.state, LevelState.PLAYING);
		level.tick();
		assertEquals(level.state, LevelState.FAILED);
	}
	
	@Test
	public void timeoutTest()
	{
		Level level = buildLevel();
		level.timeout = 2;
		assertEquals(level.snake.getHeadLocation(), new Vector(0, 0));
		level.tick();
		assertEquals(level.snake.getHeadLocation(), new Vector(0, 0));
		level.tick();
		assertEquals(level.snake.getHeadLocation(), new Vector(1, 0));
		level.tick();
		assertEquals(level.snake.getHeadLocation(), new Vector(1, 0));
	}
	
	@Test
	public void completedTest()
	{
		Level level = buildLevel();
		level.targetLength = 2;
		level.snake.adjustLength(1);
		assertEquals(level.state, LevelState.PLAYING);
		level.tick();
		assertEquals(level.state, LevelState.COMPLETED);
	}
	
	private Level buildLevel()
	{
		Level level = new Level();
		level.map = new GameMap(new Vector(5, 5));
		level.snake = new Snake(new Vector(0, 0), Direction.RIGHT, level.map.getSize());
		return level;
	}
}
