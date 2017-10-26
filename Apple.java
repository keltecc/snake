package snake;

public class Apple extends MapObject
{
    private static final int COST = 1;

    public Apple(Point position) 
    {
        super(position);
    }
    
    public Apple(int x, int y)
    {
        super(x, y);
    }

    public void interact(Level level)
    {
        level.snake.adjustLength(COST);
        Point position = findEmptyPosition(level.snake, level.map);
        try
        {                    
            level.map.setObject(position.x, position.y, new Apple(position));
        }
        finally
        {
            level.map.setObject(this.getPosition().x, this.getPosition().y, null);
        }
    }
}
