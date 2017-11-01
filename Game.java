package snake;

public class Game 
{
	private LevelLoader loader;
	
	public Level level;
	public GameState state;
	
	public Game()
	{
		state = GameState.NONE;
		loader = new LevelLoader(this);
	}
	
	public void loadLevel(int number) throws Exception
	{
		loader.load(number);
		if (level == null)
			state = GameState.FINISHED;
		state = GameState.PLAYING;
	}
	
	public void checkState()
	{
		if (level.snake.getLength() == level.targetLength)
			state = GameState.COMPLETED;
	}
}
