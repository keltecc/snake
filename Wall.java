package snake;

public class Wall extends MapObject
{
	public Wall(Point position)
	{
		super(position);
	}
	
	public Wall(int x, int y)
	{
		super (x, y);
	}

	@Override
	public void interact(Level level)
	{
		level.state = LevelState.FAILED;
	}
}
