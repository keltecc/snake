package snake;

public class Wall extends MapObject
{
	public Wall(Point position)
	{
		super(position);
	}

	@Override
	public void interact(Level level)
	{
		level.state = LevelState.FAILED;
	}
}
