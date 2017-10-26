package snake;

public class Level 
{
    public LevelState state;
    public int targetLength;
    public GameMap map;
    public Snake snake;
    
    public Level(GameMap map, Snake snake, int targetLength)
    {
        this.map = map;
        this.snake = snake;
        this.targetLength = targetLength;
        
        state = LevelState.PLAYING;
    }
}
