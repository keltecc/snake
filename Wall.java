package snake;

public class Wall extends MapObject
{
	public Wall(Game game, Vector location) 
	{
		super(game, location);
	}

	@Override	
	public void interact()
	{
		game.state = GameState.FAILED;
	}
}
