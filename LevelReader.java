package snake;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class LevelReader implements LevelProvider 
{
    private static final Path LEVELS_PATH = Paths.get("bin", "snake", "levels");
	
    private LevelBuilder builder;
    
    public LevelReader()
    {
    	builder = new LevelBuilder();
    }
    
    public int getLevelsCount() throws Exception
    {
    	return (int)Files.list(LEVELS_PATH).count();
    }
    
    public Level load(int number) throws Exception
    {
    	builder.clear();
    	List<String> lines = readLines(number);
    	int mapLineIndex = parseConfiguration(lines);
    	builder.gameMap = parseMap(builder.mapSize, lines.subList(
    			mapLineIndex, 
    			mapLineIndex + builder.mapSize.y
    	));
    	builder.generators = parseGenerators(lines.subList(
                mapLineIndex + builder.mapSize.y, 
                lines.size()
        ));
    	return builder.build();
    }
    
    private List<String> readLines(int number) throws Exception
    {
        Path path = LEVELS_PATH.resolve(Integer.toString(number));
        if (Files.exists(path))
            return Files.readAllLines(path);
        else
            throw new Exception("Wrong path to level \"" + path.toAbsolutePath().toString() + "\"");
    }
    
    private int parseConfiguration(List<String> lines) throws Exception
    {
    	int index = 0;
    	String[] parts;
        do
        {
            parts = lines.get(index++).split("=");
            switch (parts[0])
            {
            	case "size":
            		builder.mapSize = Point.parse(parts[1], "x");
            		break;
            	case "head":
            		builder.snakeHead = Point.parse(parts[1], ",");
            		break;
            	case "direction":
            		builder.snakeDirection = Direction.parse(parts[1]);
            		break;
            	case "target":
            		builder.targetLength = Integer.parseInt(parts[1]);
            		break;
            	case "length":
            		builder.snakeLength = Integer.parseInt(parts[1]);
            		break;
            }
        } 
        while (parts.length > 1);
        return index - 1;
    }
    
    private GameMap parseMap(Point size, List<String> lines)
    {
        final char wallSymbol = 'X';
        GameMap map = new GameMap(size.x, size.y);
        for (int x = 0; x < map.width(); x++)
        {
            for (int y = 0; y < map.height(); y++)
                map.setTerrain(x, y, lines.get(y).charAt(x) == wallSymbol);
        }
        return map;
    }
    
    private HashMap<Class, MealGenerator> parseGenerators(List<String> lines) throws Exception
    {
        HashMap<Class, MealGenerator> generators = new HashMap<>();
        for (int index = 0; index < lines.size(); index++)
        {
            String[] configs = lines.get(index).split(",");
            Class type = null;
            int timeout = 0;
            int maxCount = 0;
            for (String config : configs)
            {
                String[] parts = config.split("=");
                switch (parts[0])
                {
                	case "food":
                		type = Class.forName("snake." + parts[1]);
                		break;
                	case "timeout":
                		timeout = Integer.parseInt(parts[1]);
                		break;
                	case "count":
                		maxCount = Integer.parseInt(parts[1]);
                		break;
                }
            }
            if (timeout == 0 || maxCount == 0 || type == null)
            	throw new Exception("Wrong MealGenerator definition \"" + lines.get(index) + "\"");
            generators.put(type, new MealGenerator(type, timeout, maxCount));
        }
        return generators;
    }
}
