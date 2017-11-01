package snake;

import java.util.HashSet;

public class Opener extends MapObject
{
	private Vector[] walls;
	private HashSet<MapObject> objects;
	
	public Opener(Game game, Vector location) 
	{
		super(game, location);
		objects = new HashSet<>();
	}
	
	@Override
	public void interact()
	{
		for (Vector wall : walls)
		{
			objects.add(game.level.map.get(wall));
			game.level.map.set(wall, null);
		}
		game.addEffect(new Effect(
				null, game -> {
					if (game.level.map.get(getLocation()) == null)
						game.level.map.set(getLocation(), this);
				}, game -> {
					for (MapObject object: objects)
						game.level.map.set(object.getLocation(), object);
					objects.clear();
				} , game.level.snake.getLength() + 3, game
		));
	}
	
	@Override
	public void parse(String text)
	{
		String[] parts = text.split(";");
		walls = new Vector[parts.length];
		for (int i = 0; i < walls.length; i++)
			walls[i] = Vector.parse(parts[i], ",");
	}
}
