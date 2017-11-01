package snake;

public class QuickSand extends MapObject
{
	public QuickSand(Game game, Vector location) 
	{
		super(game, location);
	}
	
	@Override
	public void interact()
	{
		game.addEffect(new Effect(
				game -> {game.level.timeout *= 2; delete();},
				null,
				game -> {
					game.level.timeout /= 2;
					Vector location = game.level.map.getRandomFreeCell();
					game.level.map.set(location, new QuickSand(game, location));
				},
				5,
				game
		));
	}
}
