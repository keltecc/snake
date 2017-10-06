package snake;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LevelParser 
{
	private static String[] readLevel(int number)
	{
		return null;
	}
	
	public static Level parseLevel(int number)
	{
		String[] lines = readLevel(number);
		Field field = new Field(lines[0].length(), lines.length - 3);
		for (int x = 0; x < field.width(); x++)
		{
			for (int y = 0; y < field.height(); y++)
			{
				if (lines[y].charAt(x) == 'X')
					field.set(x, y, true);
				else if (lines[y].charAt(x) == '.')
					field.set(x, y, false);
			}
		}
		Point direction = Direction.Parse(lines[lines.length - 3]);
		int length = Integer.parseInt(lines[lines.length - 2]);
		String[] parts = lines[lines.length - 1].split(",");
		Point position = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[0]));
		return new Level(field, new Snake(position, direction), length);
	}
}
