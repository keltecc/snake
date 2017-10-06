package snake;

public class Level 
{
	private Snake snake;
	private int finalLength;
	private Field field;
	
	public Level(Field field, Snake snake, int finalLength)
	{
		this.field = field;
		this.snake = snake;
		this.finalLength = finalLength;
	}
}
