package snake;

public class Game 
{    
    private static final int LEVELS_COUNT = 2;
    
    private int levelNumber;
    
    public Level level;
    public GameResult result;
    
    public Game()
    {
        level = null;
        result = GameResult.NONE;
    }
    
    public void loadLevel(int number) throws Exception
    {
        levelNumber = number;
        level = LevelLoader.load(levelNumber);
    }
    
    public void tick() throws Exception
    {
        if (level == null)
            throw new Exception("No level!");

        if (level.snake.getLength() == level.targetLength)
        {
            if (levelNumber < LEVELS_COUNT)
                loadLevel(++levelNumber);
            else
                result = GameResult.GAME_COMPLETED;
        }
        
        checkCollisions();
        level.snake.makeStep();
        
        for (MealGenerator generator : level.generators.values())
            generator.tick(level.map, level.snake);
    }
    
    public void changeDirection(Point direction)
    {
        if (Point.getScalarProduct(direction, level.snake.direction) == 0)
            level.snake.direction = direction;
    }
    
    private void checkCollisions()
    {
        Point[] trace = level.snake.getTrace();
        Point head = Point.getSum(trace[0], level.snake.direction);
        
        if (level.map.getTerrain(head.x, head.y))
            result = GameResult.LEVEL_FAILED;
        
        Food food = level.map.getFood(head.x, head.y);
        if (food != null)
        {
            level.snake.adjustLength(food.getCost());
            level.generators.get(food.getClass()).decreaseCount();
            level.map.setFood(head.x, head.y, null);
        }
        
        for (int index = 0; index < trace.length; index++)
        {
            if (trace[index].equals(head))
                level.snake.adjustLength(-index);
        }
    }
}
