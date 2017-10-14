package snake;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CUI 
{
    public static void main(String[] args) throws Exception
    {
        Game game = new Game(new LevelReader());
        game.loadLevel(1);
        while (game.result == GameResult.NONE)
        {
            printView(renderView(game.level.map, game.level.snake));
            String command = readLine();
            if (!command.equals(""))
            {
                try
                {
                    game.changeDirection(Direction.parse(command.toUpperCase()));                   
                }
                catch (Exception e)
                {
                    System.out.println("Wrong command \"" + command + "\"");
                }
            }
            game.tick();
        }
        System.out.println(game.result.toString());
    }
    
    private static String readLine()
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
    
    private static String[][] renderView(GameMap map, Snake snake)
    {
        String[][] characters = new String[map.height()][];
        for (int y = 0; y < map.height(); y++)
        {
            characters[y] = new String[map.width()];
            for (int x = 0; x < map.width(); x++)
            {
                Food food = map.getFood(x, y);
                if (food instanceof Apple)
                    characters[y][x] = "A";
                else if (food instanceof Cherry)
                    characters[y][x] = "C";
                else if (food == null)
                    characters[y][x] = map.isTerrain(x, y) ? "X" : ".";
                else
                    characters[y][x] = "?";
            }
        }
        for (Point part : snake.getTrace())
            characters[part.y][part.x] = "S";
        return characters;
    }
    
    private static void printView(String[][] view)
    {
        String[] lines = new String[view.length];
        for (int index = 0; index < lines.length; index++)
            lines[index] = String.join("", view[index]);
        String text = String.join("\n", lines);
        System.out.println(text);
    }
}
