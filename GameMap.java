package snake;

import java.util.Random;

public class GameMap 
{
	private MapObject[][] objects;
	
	public GameMap(int width, int height)
	{
		objects = new MapObject[width][];
		for (int i = 0; i < width; i++)
			objects[i] = new MapObject[height];
	}

    public Vector getSize()
    {
        return new Vector(objects.length, objects.length > 0 ? objects[0].length : 0);
    }
	
	public void set(Vector location, MapObject object)
	{
		objects[location.x][location.y] = object;
	}
	
	public MapObject get(Vector location)
	{
		return objects[location.x][location.y];
	}
	
	public Vector getRandomFreeCell()
	{
		Random random = new Random();
		Vector location;
		Vector size = getSize();
		do
			location = new Vector(random.nextInt(size.x), random.nextInt(size.y));
		while (get(location) != null);
		return location;
	}
}
