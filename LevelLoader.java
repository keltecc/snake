package snake;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class LevelLoader 
{
    private static final Path LEVELS_PATH = Paths.get("bin", "snake", "levels");
    
    public static Level load(int number) throws Exception
    {
        List<String> lines = readLines(number);
        LevelBuilder builder = new LevelBuilder();
        int index = 0;
        String[] parts;
        do
        {
            parts = lines.get(index++).split("=");
            if (parts[0].equals("size"))
                builder.mapSize = Point.parse(parts[1], "x");
            else if (parts[0].equals("head"))
                builder.snakeHead = Point.parse(parts[1], ",");
            else if (parts[0].equals("direction"))
                builder.snakeDirection = Direction.parse(parts[1]);
            else if (parts[0].equals("target"))
                builder.targetLength = Integer.parseInt(parts[1]);
            else if (parts[0].equals("length"))
                builder.snakeLength = Integer.parseInt(parts[1]);
        } 
        while (parts.length > 1);
        builder.gameMap = parseMap(
                builder.mapSize, 
                lines.subList(index - 1, index + builder.mapSize.y)
        ); 
        builder.generators = parseGenerators(lines.subList(
                index + builder.mapSize.y - 1, 
                lines.size())
        );
        return builder.build();
    }
    
    private static List<String> readLines(int number) throws Exception
    {
        Path path = LEVELS_PATH.resolve(Integer.toString(number));
        if (Files.exists(path))
            return Files.readAllLines(path);
        else
            throw new Exception("Wrong path to level \"" + path.toAbsolutePath().toString() + "\"");
    }
    
    private static GameMap parseMap(Point size, List<String> lines)
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
    
    private static HashMap<Class, MealGenerator> parseGenerators(List<String> lines) 
        throws Exception
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
                if (parts[0].equals("food"))
                    type = Class.forName("snake." + parts[1]);
                else if (parts[0].equals("timeout"))
                    timeout = Integer.parseInt(parts[1]);
                else if (parts[0].equals("count"))
                    maxCount = Integer.parseInt(parts[1]);
            }
            generators.put(type, new MealGenerator(type, timeout, maxCount));
        }
        return generators;
    }
}
