package snake;

import java.util.HashSet;

public class Level
{
	public Snake snake;
	public GameMap map;
	public int timeout;
	public int targetLength;
	
	private int elapsed;
	private HashSet<Effect> effects = new HashSet<>();
	
	public void changeDirection(Vector direction)
	{
		if (Vector.getScalarProduct(direction, snake.getDirection()) == 0)
			snake.setDirection(direction.clone());
	}
	
	public void addEffect(Effect effect)
	{
		effects.add(effect);
	}
	
	public void tick()
	{
		if (++elapsed < timeout)
			return;
		elapsed = 0;
		Vector position = snake.getHead().getLocation()
				.add(snake.getDirection())
				.bound(map.getSize());
		MapObject object = map.get(position);
		if (object != null)
			object.interact();
		snake.makeStep();
		effectsTick();
	}
	
	private void effectsTick()
	{
		for (Effect effect : effects)
		{
			effect.tick();
			if (effect.finished)
				effects.remove(effect);
		}
	}
}
