package snake;

import java.util.HashMap;

public class Level 
{
	public int targetLength;
    public Snake snake;
    public GameMap map;
    
    public HashMap<Class, MealGenerator> generators;
    
    public Level(GameMap map, Snake snake, int target, HashMap<Class, MealGenerator> generators)
    {
        this.map = map;
        this.snake = snake;
        this.targetLength = target;
        
        this.generators = generators;
    }
}
