package snake;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelLoader 
{
	private static final Path LEVELS_PATH = Paths.get("bin", "snake", "levels");
	private Game game;
	
	public LevelLoader(Game game)
	{
		this.game = game;
	}
	
    public void load(int number)
	{
    	try
    	{
    		List<String> lines = readLines(number);
    		game.level = buildConfiguration(lines.get(0));
    		for (int i = 1; i < lines.size() - 1; i++)
    		{
    			MapObject[] objects = parseObject(lines.get(i));
    			for (MapObject object : objects)
    				game.level.map.set(object.getLocation(), object);
    		}
    		String[] snakeInfo = lines.get(lines.size() - 1).split(" ");
    		game.level.snake = new Snake(
    				(SnakePart)game.level.map.get(Vector.parse(snakeInfo[0], ",")),
    				Direction.parse(snakeInfo[1])
    		);
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    		game.level = null;
    	}
	}
    
    private List<String> readLines(int levelNumber) throws IOException
    {
        Path path = LEVELS_PATH.resolve(Integer.toString(levelNumber) + ".lvl");
        return Files.readAllLines(path);
    }
    
    private Level buildConfiguration(String line) 
    {
    	Level level = new Level();
    	String[] parts = line.split(" ");
    	Vector size = Vector.parse(parts[0], "x");
    	level.map = new GameMap(size.x, size.y);
    	level.targetLength = Integer.parseInt(parts[1]);
    	level.timeout = Integer.parseInt(parts[2]);
    	return level;
    }
    
    private MapObject[] parseObject(String info) throws Exception
    {
    	String[] parts = info.split(" ");
		String[] vectors = parts[1].split(";");
		MapObject[] objects = new MapObject[vectors.length];
		for (int i = 0; i < objects.length; i++)
		{
			objects[i] = (MapObject)Class
					.forName("snake." + parts[0])
					.getConstructor(Game.class, Vector.class)
					.newInstance(game, Vector.parse(vectors[i], ","));			
		}
		if (objects.length == 1 && parts.length == 3)
			objects[0].parse(parts[2]);
		return objects;
    }
}
