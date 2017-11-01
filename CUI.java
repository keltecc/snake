package snake;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CUI 
{
    public static void main(String[] args) throws Exception
    {
        Game game = new Game();
        game.loadLevel(1);
        while (game.state != GameState.FAILED)
        {
            printView(renderView(game.level.map, game.level.snake));
            String command = readLine();
            if (!command.equals(""))
            {
                try
                {
                    game.changeDirection(Direction.parse(command));                   
                }
                catch (Exception e)
                {
                    System.out.println("Wrong command \"" + command + "\"");
                }
            }
            game.tick();
        }
        System.out.println(game.state.toString());
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
    	Vector size = map.getSize();
        String[][] characters = new String[size.y][];
        for (int y = 0; y < size.y; y++)
        {
            characters[y] = new String[size.x];
            for (int x = 0; x < size.x; x++)
            {
                MapObject object = map.get(new Vector(x, y));
                if (object instanceof Apple)
                    characters[y][x] = "A";
                else if (object instanceof Wall)
                    characters[y][x] = "■";
                else if (object instanceof SnakePart)
                	characters[y][x] = "•";
                else if (object instanceof QuickSand)
                    characters[y][x] = "=";
                else if (object instanceof Portal)
                    characters[y][x] = "@";
                else if (object instanceof Opener)
                    characters[y][x] = "#";
                else if (object == null)
                    characters[y][x] = " ";
                else
                    characters[y][x] = "?";
            }
        }
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
