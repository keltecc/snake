package snake;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CUI 
{
	public static void main(String[] args) throws Exception
	{
		int levelNumber = 1;
		Game game;
		try
		{
			game = new Game(1);
		}
		catch (Exception ex)
		{
			System.out.println("Can't load level " + levelNumber);
			ex.printStackTrace();
			return;
		}
		boolean doGame = true;
		while (doGame)
		{
			String[][] map = renderMap(game.level.map);
			addSnakeToMap(map, game.level.snake);
			printMap(map);
			String command = readLine();
			switch (command)
			{
			case "STOP":
				doGame = false;
				break;
			case "":
				break;
			default:
				game.changeDirection(Direction.parse(command));
				break;
			}
			game.tick();
		}
	}
	
	public static String readLine()
	{
		try
		{
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader buffer = new BufferedReader(reader);
			return buffer.readLine();
		}
		catch (Exception ex)
		{
			return "";
		}
	}
	
	public static String[][] renderMap(GameMap map)
	{
		String[][] characters = new String[map.width()][];
		for (int x = 0; x < characters.length; x++)
		{
			characters[x] = new String[map.height()];
			for (int y = 0; y < characters[x].length; y++)
				characters[x][y] = map.get(x, y) ? "X" : ".";
		}
		return characters;
	}
	
	public static void addSnakeToMap(String[][] map, Snake snake)
	{
		for (int index = 0; index < snake.length(); index++)
		{
			Point part = snake.getPart(index);
			map[part.y][part.x] = "S";
		}
	}
	
	public static void printMap(String[][] map)
	{
		String[] lines = new String[map.length];
		for (int index = 0; index < lines.length; index++)
			lines[index] = String.join("", map[index]);
		String text = String.join("\n", lines);
		System.out.println(text);
	}
}
