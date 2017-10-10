package snake;

import java.util.HashMap;
import java.util.HashSet;

public class GameMap 
{
    private boolean[][] walls;
    private HashMap<Point, EatingObject> food;
    private HashSet<Point> freeCells;
    
    public GameMap(int width, int height)
    {
    	freeCells = new HashSet<>();
        walls = new boolean[width][];
        food = new HashMap<Point, EatingObject>();
        
        for (int x = 0; x < walls.length; x++)
            walls[x] = new boolean[height];
    }
    
    public void setTerrain(int x, int y, boolean isWall)
    {
        walls[x][y] = isWall;
        Point point = new Point(x, y);
        if (!isWall)
        	freeCells.add(point);
        else if (freeCells.contains(point))
        	freeCells.remove(point);
    }
    
    public boolean getTerrain(int x, int y)
    {
        return walls[x][y];
    }
    
    public void addFood(EatingObject object)
    {
    	food.put(object.getLocation(), object);
    	if (freeCells.contains(object.getLocation()))
    		freeCells.remove(object.getLocation());
    }
    
    public void removeFood(EatingObject object)
    {
    	food.remove(object.getLocation());
    	freeCells.add(object.getLocation());
    }
    
    public Point getFreeCell()
    {
    	return freeCells.iterator().next();
    }
    
    public EatingObject[] getAllFood()
    {
    	EatingObject[] objects = new EatingObject[food.size()];
    	food.values().toArray(objects);
    	return objects;
    }
    
    public EatingObject getFoodAt(Point position)
    {
    	if (food.containsKey(position))
    		return food.get(position);
    	return null;
    }
    
    public int width()
    {
        return walls.length;
    }
    
    public int height()
    {
        return walls.length > 0 ? walls[0].length : 0;
    }
    
    public int foodCount()
    {
    	return food.size();
    }
}
