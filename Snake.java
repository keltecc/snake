package snake;

public class Snake 
{
	private int length;
	private SnakePart head;
	private SnakePart tail;
	private int lengthQueue;
	private Vector direction;
	
	public Snake(SnakePart head, Vector direction)
	{
		this.head = head;
		this.direction = direction;
		tail = head;
		length = 1;
		while (tail.next != null)
		{
			tail = tail.next;
			length++;
		}
	}
	
	public SnakePart getHead()
	{
		return head;
	}
	
	public Vector getDirection()
	{
		return direction.clone();
	}
	
	public void setDirection(Vector direction)
	{
		this.direction = direction.clone();
	}
	
	public int getLength()
	{
		return length;
	}
	
	public void setLength(int value)
	{
		lengthQueue = value - length;
		if (lengthQueue > 0)
			return;
		SnakePart part = tail;
		while (lengthQueue++ < 0)
		{
			part = part.previous;
			length--;
			part.next.delete();
		}
		part.next = null;
		tail = part;
	}
	
	public void makeStep()
	{
		Vector previous = head.getLocation().clone();
		head.changeLocation(direction);
		SnakePart part = head;
		for (int i = 1; i < length; i++)
		{
			if (part.next == null)
			{
				length = i + 1;
				tail = part;
				break;
			}
			Vector location = part.next.getLocation();
			part.next.setLocation(previous);
			previous = location;
			part = part.next;
		}
		expandTail(part);
	}
	
	private void expandTail(SnakePart tail)
	{
		Vector direction = Direction.calculate(tail.getLocation(), tail.previous.getLocation());
		if (lengthQueue > 0)
		{
			SnakePart part = new SnakePart(head.game, tail.getLocation().add(direction.multiply(-1)));
			part.previous = this.tail;
			this.tail.next = part;
			this.tail = part;
			lengthQueue--;
			length++;
		}
	}
}
