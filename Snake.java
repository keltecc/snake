package snake;

import java.util.LinkedList;

public class Snake 
{	
	private int lengthAdjustCounter;
	private LinkedList<Point> body;
	private Point direction;
	
	public Snake(Point location, Point direction)
	{
		lengthAdjustCounter = 0;
		body = new LinkedList<Point>();
		
		body.add(location);
		this.direction = direction;
	}
	
	public int getLength()
	{
		return body.size();
	}
	
	public void setDirection(Point direction)
	{
		this.direction = direction;
	}
	
	public void increaseLength(int length)
	{
		lengthAdjustCounter += length;
	}
	
	public void decreaseLength(int length)
	{
		while (length-- > 0)
			body.removeLast();
	}
	
	public void makeStep()
	{
		Point head = body.peekFirst().clone();
		head.add(direction);
		body.addFirst(head);
		
		if (lengthAdjustCounter > 0)
		{
			body.removeLast();
			lengthAdjustCounter--;
		}
	}
}
