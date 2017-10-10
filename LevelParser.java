package snake;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelParser 
{
	private static final Path MAPS_PATH = Paths.get("bin", "snake", "maps");
	private static final Character WALL_SYMBOL = 'X';
	
    private static List<String> readMap(int number) throws Exception
    {
    	Path path = Paths.get(MAPS_PATH.toString(), Integer.toString(number));
    	if (Files.exists(path))
    		return Files.readAllLines(path);
    	else
    		throw new Exception("Wrong path to map \"" + path.toAbsolutePath().toString() + "\"");
    }
    
    public static Level parse(int number) throws Exception
    {
        List<String> lines = readMap(number);
        Point size = Point.parse(lines.get(0), "x");
        GameMap map = new GameMap(size.x, size.y);
        for (int x = 0; x < map.width(); x++)
        {
            for (int y = 0; y < map.height(); y++)
            {
            	char symbol = lines.get(y + 1).charAt(x);
                map.setTerrain(x, y, symbol == WALL_SYMBOL);
            }
        }
        Point direction = Direction.parse(lines.get(lines.size() - 3));
        int length = Integer.parseInt(lines.get(lines.size() - 2));
        Point position = Point.parse(lines.get(lines.size() - 1), ",");
        return new Level(map, new Snake(position, direction), length);
    }
}
