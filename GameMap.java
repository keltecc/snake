package snake;

public class GameMap 
{
    private MapObject[][] objects;
    
    public GameMap(int width, int height)
    {
        objects = new MapObject[width][];
        
        for (int x = 0; x < width; x++)
        {
            objects[x] = new MapObject[height];
        }
    }
       
    public int width()
    {
        return objects.length;
    }
    
    public int height()
    {
        return objects.length > 0 ? objects[0].length : 0;
    }
    
    public void setObject(int x, int y, MapObject object)
    {
        objects[x][y] = object;
    }
    
    public MapObject getObject(int x, int y) 
    {
        return objects[x][y];
    }
}
