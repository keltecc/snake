package snake;

import java.util.Random;

public class MealGenerator<F extends Food> 
{
    private Class<F> type;
    
    private int maxCount;
    private int timeout;
    
    private int elapsed;
    private int count;
    
    public MealGenerator(Class<F> type, int timeout, int maxCount)
    {
        this.type = type;
        
        this.timeout = timeout;
        this.maxCount = maxCount;
        
        count = 0;
        elapsed = 0;
    }
    
    public void decreaseCount()
    {
        count--;
    }
    
    public void tick(GameMap map, Snake snake) throws Exception
    {
        if (count < maxCount && ++elapsed == timeout)
        {
            addFood(map, snake);
            count++;
            elapsed = 0;
        }
    }
    
    private void addFood(GameMap map, Snake snake) throws Exception
    {
        final double goodDistance = 2;
        Random random = new Random();
        Point location;
        do
        {
            location = new Point(random.nextInt(map.width()), random.nextInt(map.height()));
        }
        while (
            map.getTerrain(location.x, location.y) ||
            map.getFood(location.x, location.y) != null ||
            getDistanceToSnake(snake, location) < goodDistance
        );
        map.setFood(location.x, location.y, type.newInstance());
    }
    
    private double getDistanceToSnake(Snake snake, Point point)
    {
        double distance = Double.MAX_VALUE;
        for (Point part : snake.getTrace())
            distance = Math.min(distance, Point.getDistance(point, part));
        return distance;
    }
}
