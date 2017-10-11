package snake;

import java.util.LinkedList;

public class Snake 
{    
    private int lengthQueue;
    private LinkedList<Point> body;
    
    public Point direction;
    
    public Snake(Point location, Point direction, int length)
    {
        lengthQueue = 0;
        body = new LinkedList<Point>();
        
        body.add(location);
        this.direction = direction;
        
        while (--length > 0)
        {
            body.addLast(Point.getSum(
                body.peekLast().clone(), 
                Point.getMultiplication(direction, -1)
            ));
        }
    }
    
    public int getLength()
    {
        return body.size();
    }
    
    public Point[] getTrace()
    {
        Point[] trace = new Point[getLength()];
        body.toArray(trace);
        return trace;
    }
    
    public void adjustLength(int length)
    {
        while (length < 0)
        {
            body.removeLast();
            length++;
        }
        
        lengthQueue += length;
    }
    
    public void makeStep()
    {
        Point head = body.peekFirst().clone();
        head.add(direction);
        body.addFirst(head);
        
        if (lengthQueue > 0)
            lengthQueue--;
        else
            body.removeLast();
    }
}
