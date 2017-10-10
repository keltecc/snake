package snake;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CUI 
{
	public static void main(String[] args) throws Exception
	{
		Game game = new Game();
		game.loadLevel(1);
		while (!game.isOver)
		{
			String[][] map = renderMap(game.level.map);
			addSnakeToMap(map, game.level.snake);
			addFoodToMap(map, game.level.map.getAllFood());
			printMap(map);
			String command = readLine();
			switch (command)
			{
				case "":
					break;
				default:
					game.changeDirection(Direction.parse(command));
					break;
			}
			game.tick();
		}
		System.out.println("GAME OVER!");
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
				characters[x][y] = map.getTerrain(x, y) ? "X" : ".";
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
	
	public static void addFoodToMap(String[][] map, EatingObject[] objects)
	{
		for (EatingObject object : objects)
			map[object.getLocation().x][object.getLocation().y] = "A";
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
