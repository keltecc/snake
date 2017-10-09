package snake;

public class GameMap 
{
    private boolean[][] gameMap;
    
    public GameMap(int width, int height)
    {
        gameMap = new boolean[width][];
        for (int x = 0; x < gameMap.length; x++)
        {
            gameMap[x] = new boolean[height];
        }
    }
    
    public void set(int x, int y, boolean isWall)
    {
        gameMap[x][y] = isWall;
    }
    
    public boolean get(int x, int y)
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
