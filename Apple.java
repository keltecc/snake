package snake;

public class Apple extends MapObject
{
	public Apple(Game game, Vector location)
	{
		super(game, location);
	}
	
	@Override
	public void interact()
	{
		game.level.snake.setLength(game.level.snake.getLength() + 1);
		Vector newLocation = game.level.map.getRandomFreeCell();
		game.level.map.set(newLocation, new Apple(game, newLocation));
		delete();
	}
}
