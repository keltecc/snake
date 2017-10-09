package snake;

import java.util.LinkedList;

public class Snake 
{    
    private int lengthQueue;
    private LinkedList<Point> body;
    
    public Point direction;
    
    public Snake(Point location, Point direction)
    {
        lengthQueue = 0;
        body = new LinkedList<Point>();
        
        body.add(location);
        this.direction = direction;
    }
    
    public int length()
    {
        return body.size();
    }
    
    public Point getPart(int number)
    {
    	if (number >= 0 && number < length())
    		return body.get(number);
    	return null;
    }
    
    public void adjustLength(int length)
    {
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
