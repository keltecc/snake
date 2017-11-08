package snake;

public class Oracle extends MapObject
{
	private Vector[] area;
	
	public Oracle(Level level, Vector location)
	{
		super(level, location);
	}
	
	@Override
	public void interact()
	{
		int delay = level.snake.getLength() + 1;
		for (Vector wall : area)
		{
			level.map.set(wall, null);
			(new Wall(level, wall)).setRevealDelay(delay);
		}
	}
	
	@Override
	public void configure(String info)
	{
		String[] parts = info.split("-");
		area = new Vector[parts.length];
		for (int i = 0; i < area.length; i++)
			area[i] = Vector.parse(parts[i], ",");
	}
}
