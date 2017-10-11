package snake;

public class Point 
{    
    public int x;
    public int y;
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object point)
    {
        return point instanceof Point 
                && x == ((Point)point).x 
                && y == ((Point)point).y;
    }
    
    @Override
    public int hashCode()
    {
        final int half = Integer.MAX_VALUE / 2;
        return x % half + y % half;
    }
    
    public Point clone()
    {
        return new Point(this.x, this.y);
    }
    
    public void add(Point point)
    {
        this.x += point.x;
        this.y += point.y;
    }
    
    public void multiply(int value)
    {
        this.x *= value;
        this.y *= value;
    }
    
    public static Point parse(String text, String delimeter)
    {
        String[] parts = text.split(delimeter);
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new Point(x, y);
    }
    
    public static int getScalarProduct(Point p1, Point p2)
    {
        return p1.x * p2.x + p1.y * p2.y;
    }
    
    public static double getDistance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
    
    public static Point getSum(Point p1, Point p2)
    {
        return new Point(p1.x + p2.x,p1.y + p2.y);
    }
    
    public static Point getMultiplication(Point point, int value)
    {
        return new Point(point.x * value, point.y * value);
    }
}
