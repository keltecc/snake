package snake;

import java.util.HashMap;

public class LevelBuilder 
{
    public Point snakeDirection;
    public Point snakeHead;
    public Point mapSize;
    
    public int targetLength;
    public int snakeLength;
    
    public GameMap gameMap;
    public HashMap<Class, MealGenerator> generators;
    
    public LevelBuilder()
    {
        snakeDirection = null;
        snakeHead = null;
        mapSize = null;
        
        targetLength = 0;
        snakeLength = 0;
        
        gameMap = null;
        generators = null;
    }
    
    public Level build() throws Exception
    {
        if (snakeDirection == null || 
            snakeHead == null || 
            mapSize == null || 
            targetLength == 0 ||
            snakeLength == 0 ||
            gameMap == null ||
            generators == null)
        {
            throw new Exception("Level parsing failed!");
        }
        
        return new Level(
            gameMap, 
            new Snake(snakeHead, snakeDirection, snakeLength), 
            targetLength,
            generators
        );
    }
    
    public void clear()
    {
    	snakeDirection = null; 
        snakeHead = null;
        mapSize = null; 
        
        targetLength = 0;
        snakeLength = 0;
        
        gameMap = null;
        generators = null;
    }
}
