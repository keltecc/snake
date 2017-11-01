package snake;

public class Portal extends MapObject
{
	private Vector destination;
	public Portal(Game game, Vector location) 
	{
		super(game, location);
	}
	
	@Override
	public void interact()
	{
		Vector direction = game.level.snake.getDirection().multiply(-1);
		game.level.snake.getHead().setLocation(destination);
	}
	
	@Override
	public void parse(String text)
	{
		destination = Vector.parse(text, ",");
	}
}
