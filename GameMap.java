package snake;

public class GameMap 
{
    private MapObject[][] gameMap;
    
    public GameMap(int width, int height)
    {
        gameMap = new MapObject[width][];
        for (int index = 0; index < gameMap.length; index++)
        {
            gameMap[index] = new MapObject[height];
        }
    }
    
    public void set(int x, int y, MapObject object)
    {
        gameMap[x][y] = object;
    }
    
    public MapObject get(int x, int y)
    {
        return gameMap[x][y];
    }
    
    public int width()
    {
        return gameMap.length;
    }
    
    public int height()
    {
        return gameMap.length > 0 ? gameMap[0].length : 0;
    }
}
