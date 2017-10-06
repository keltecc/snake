package snake;

public class Field 
{
	private boolean[][] field;
	
	public Field(int width, int height)
	{
		field = new boolean[width][];
		for (int index = 0; index < field.length; index++)
			field[index] = new boolean[height];
	}
	
	public void set(int x, int y, boolean value)
	{
		field[x][y] = value;
	}
	
	public boolean get(int x, int y)
	{
		return field[x][y];
	}
	
	public int width()
	{
		return field.length;
	}
	
	public int height()
	{
		return field.length > 0 ? field[0].length : 0;
	}
}
