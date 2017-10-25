package snake;

import java.nio.file.Path;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    public Level load(int levelNumber) throws Exception
    {
        builder.clear();
        List<String> lines = readLines(levelNumber);
        int mapLineIndex = parseConfiguration(lines);
        builder.gameMap = parseMap(builder.mapSize, lines.subList(
                mapLineIndex, 
                mapLineIndex + builder.mapSize.y
        ));
        return builder.build();
    }
    
    private List<String> readLines(int levelNumber) throws Exception
    {
        Path path = LEVELS_PATH.resolve(Integer.toString(levelNumber));
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
    		throws InstantiationException,
    			   IllegalAccessException,
    			   IllegalArgumentException,
    			   InvocationTargetException,
    			   SecurityException
    {
        GameMap map = new GameMap(size.x, size.y);
        for (int x = 0; x < map.width(); x++)
        {
            for (int y = 0; y < map.height(); y++)
            {
            	MapObject object;//Class object;
            	Point point = new Point(x, y);
            	switch (lines.get(y).charAt(x))
            	{
            		case 'X':
            			object = new Wall(point);//Wall.class;
            			break;
            		case 'A':
            			object = new Apple(point);//Apple.class;
            			break;
            		case 'P':
                		object = new Portal(point);//Portal.class;
                		break;
            		default:
            			object = null;
            	}
            	if (object == null)
            		continue;
            	map.setObject(x, y, object);//(MapObject)object.getConstructors()[0].newInstance(new Point(x, y)));
            }
        }
        return map;
    }
}
