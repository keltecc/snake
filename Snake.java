package snake;

import java.util.LinkedList;

public class Snake 
{	
	private int lengthQueue;
	private LinkedList<Point> body;
	private Point direction;
	
	public Snake(Point location, Point direction)
	{
		lengthQueue = 0;
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
		{
			body.removeLast();
			lengthQueue--;
		}
	}
}
