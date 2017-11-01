package snake;

import java.util.function.Consumer;

public class Effect 
{
	private int duration;
	private int passed;
	private Game game;
	
	private Consumer<Game> tickAction;
	private Consumer<Game> endAction;
	
	public boolean finished;
	
	public Effect(
			Consumer<Game> startAction, 
			Consumer<Game> tickAction, 
			Consumer<Game> endAction, 
			int duration,
			Game game)
	{
		this.tickAction = tickAction != null ? tickAction : g -> {};
		this.endAction = endAction != null ? endAction : g -> {};
		
		this.duration = duration;
		this.game = game;
		passed = 0;
		finished = false;
		
		if (startAction != null)
			startAction.accept(game);
	}
	
	public void tick()
	{
		if (passed++ >= duration)
		{
			finished = true;
			endAction.accept(game);
			return;
		}
		tickAction.accept(game);
	}
}
