package snake;

public class SnakePart extends MapObject
{
	protected SnakePart previous;
	protected SnakePart next;
	
	public SnakePart(Game game, Vector location) 
	{
		super(game, location);
		game.level.map.set(location, this);
	}

	@Override
	public void interact()
	{
		if (previous != null)
			previous.next = null;
		SnakePart part = this;
		while (part.next != null)
		{
			part.next.delete();
			part = part.next;
		}
	}

	@Override
	public void parse(String info)
	{
		SnakePart part = this;
		Vector direction = Direction.NONE;
		for (String arg : info.split("-"))
		{
			try
			{
				int length = Integer.parseInt(arg);
				for (int i = 0; i < length; i++)
				{
					part.next = new SnakePart(game, part.getLocation().add(direction));
					part.next.previous = part;
					part = part.next;
				}
			}
			catch (NumberFormatException ex)
			{
				direction = Direction.parse(arg);
			}
		}
	}
}
