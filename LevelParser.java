package snake;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.io.File;

public class LevelParser 
{
	private static final String MAPS_PATH = "maps";
	
	private static final HashMap<Character, MapObject> mapSymbols = new HashMap<>();
	static
	{
		mapSymbols.put('X', MapObject.WALL);
		mapSymbols.put('.', MapObject.GRASS);
	}
	
    private static String[] readLines(Path path) throws Exception
    {
    	List<String> read = Files.readAllLines(path);
    	String[] lines = new String[read.size()];
    	for (int index = 0; index < lines.length; index++)
    		lines[index] = read.get(index);
    	return lines;
    }
    
    private static Path buildPath(int number)
    {
	    return Paths.get(MAPS_PATH, "1");
    }
    
    public static Level parse(int number) throws Exception
    {
        String[] lines = readLines(buildPath(number));
        Point size = Point.parse(lines[0], "x");
        GameMap map = new GameMap(size.x, size.y);
        for (int x = 0; x < map.width(); x++)
        {
            for (int y = 0; y < map.height(); y++)
            {
            	char symbol = lines[y + 1].charAt(x);
                map.set(x, y, mapSymbols.get(symbol));
            }
        }
        Point direction = Direction.parse(lines[lines.length - 3]);
        int length = Integer.parseInt(lines[lines.length - 2]);
        Point position = Point.parse(lines[lines.length - 1], ",");
        return new Level(map, new Snake(position, direction), length);
    }
}
