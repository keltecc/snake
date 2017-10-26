package snake.tests;

import snake.*;
import org.junit.Test;

public class GameTests {

	@Test
	public void testLevelStateChanging() throws Exception 
	{
		Game game = new Game(new TestLevelLoader());
		game.loadLevel(1);
		assert game.level.snake.getLength() == 1;
		game.tick();
		assert game.level.state == LevelState.PLAYING;
		assert game.level.snake.getLength() == 2;
		game.tick();
		assert game.level.state == LevelState.FAILED;
	}
	
	@Test
	public void testChangeDirection() throws Exception
	{
		Game game = new Game(new TestLevelLoader());
		game.loadLevel(1);
		assert game.level.snake.direction.equals(Direction.RIGHT);
		game.changeDirection(Direction.LEFT);
		assert game.level.snake.direction.equals(Direction.RIGHT);
		game.changeDirection(Direction.DOWN);
		assert game.level.snake.direction.equals(Direction.DOWN);
	}
}
