package snake;

public class Game 
{   
    private int levelNumber;
    private LevelProvider provider;
    
    public Level level;
    public GameResult result;
    
    public Game(LevelProvider provider)
    {
        level = null;
        result = GameResult.NONE;
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

        if (level.snake.getLength() == level.targetLength)
        {
            if (levelNumber < provider.getLevelsCount())
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
                level.snake.adjustLength(index - level.snake.getLength() + 1);
        }
    }
}
