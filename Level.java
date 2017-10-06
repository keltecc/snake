package snake;

import java.util.HashSet;

public class Level 
{
	public int target;
    public Snake snake;
    public GameMap map;
    
    public Level(GameMap map, Snake snake, int target)
    {
        this.map = map;
        this.snake = snake;
        this.target = target;
    }
}
