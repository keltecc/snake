package snake;

public class Level 
{
	public int targetLength;
    public Snake snake;
    public GameMap map;
    
    public Level(GameMap map, Snake snake, int target)
    {
        this.map = map;
        this.snake = snake;
        this.targetLength = target;
    }
}
