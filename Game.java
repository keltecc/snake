package snake;

public class Game 
{   
    private int levelNumber;
    private LevelProvider provider;
    
    public Level level;
    
    public Game(LevelProvider provider)
    {
        level = null;
        this.provider = provider;
    }
    
    public void loadLevel(int number) throws Exception
    {
        levelNumber = number;
        level = provider.load(levelNumber);
    }
    
    public void tick() throws Exception
    {
        if (level == null)
            throw new Exception("No level!");
        
        checkCollisions();
        if (level.state == LevelState.PLAYING)
        	level.snake.makeStep(level.map.width(), level.map.height());
        else if (level.state == LevelState.COMPLETED)
        	level = provider.load(++levelNumber);
    }
    
    public void changeDirection(Point direction)
    {
        if (Point.getScalarProduct(direction, level.snake.direction) == 0)
            level.snake.direction = direction;
    }
    
    private void checkCollisions()
    {
        Point[] trace = level.snake.getTrace();
        Point expectedHead = Point.getSumBounds(trace[0], level.snake.direction, level.map.width(), level.map.height());
        
        MapObject object = level.map.getObject(expectedHead.x, expectedHead.y);
        if (object != null)
        	object.interact(level);
        
        for (int index = 0; index < trace.length; index++)
        {
            if (trace[index].equals(expectedHead))
                level.snake.adjustLength(index - level.snake.getLength() + 1);
        }
        
        if (level.snake.getLength() == level.targetLength)
        	level.state = LevelState.COMPLETED;
    }
}
