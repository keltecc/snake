package snake;

public class Direction 
{
    public static final Point LEFT = new Point(-1, 0);
    public static final Point RIGHT = new Point(1, 0);
    public static final Point UP = new Point(0, 1);
    public static final Point DOWN = new Point(0, -1);
    
    public static Point Parse(String name)
    {
        try 
        {
            return (Point)Direction.class.getField(name).get(null);
        }
        finally
        {
            return new Point(0, 0);
        }
    }
}
