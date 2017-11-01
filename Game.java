package snake;

import java.util.HashSet;

public class Game 
{
	private int elapsed;
	private LevelLoader loader;
	private HashSet<Effect> effects;
	
	public LevelInfo level;
	public GameState state;
	
	public Game()
	{
		state = GameState.NONE;
		loader = new LevelLoader(this);
		effects = new HashSet<>();
	}
	
	public void loadLevel(int number) throws Exception
	{
		loader.load(number);
		if (level == null)
			state = GameState.FINISHED;
		state = GameState.PLAYING;
	}
	
	public void changeDirection(Vector direction)
	{
		if (Vector.getScalarProduct(direction, level.snake.getDirection()) == 0)
				level.snake.setDirection(direction.clone());
	}
	
	public void addEffect(Effect effect)
	{
		effects.add(effect);
	}
	
	public void tick()
	{
		if (++elapsed < level.timeout)
			return;
		elapsed = 0;
		Vector position = level.snake.getHead().getLocation()
				.add(level.snake.getDirection())
				.bound(level.map.getSize());
		MapObject object = level.map.get(position);
		if (object != null)
			object.interact();
		level.snake.makeStep();
		effectsTick();
		if (level.snake.getLength() == level.targetLength)
			state = GameState.COMPLETED;
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
