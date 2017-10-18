package snake;

public class Level 
{
	public LevelState state;
	public int targetLength;
	public GameMap map;
	public Snake snake;
    
    public Level(GameMap map, Snake snake, int target)
    {
        this.map = map;
        this.snake = snake;
        this.targetLength = target;
        
        state = LevelState.PLAYING;
    }
}
