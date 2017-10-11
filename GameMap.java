package snake;

public class GameMap 
{
    private Food[][] meal;
    private boolean[][] walls;
    
    public GameMap(int width, int height)
    {
        meal = new Food[width][];
        walls = new boolean[width][];
        
        for (int x = 0; x < width; x++)
        {
            meal[x] = new Food[height];
            walls[x] = new boolean[height];
        }
    }
       
    public int width()
    {
        return walls.length;
    }
    
    public int height()
    {
        return walls.length > 0 ? walls[0].length : 0;
    }
    
    public void setTerrain(int x, int y, boolean isWall)
    {
        walls[x][y] = isWall;
    }
    
    public boolean getTerrain(int x, int y) 
    {
        return walls[x][y];
    }
    
    public void setFood(int x, int y, Food food)
    {
        meal[x][y] = food;
    }
    
    public Food getFood(int x, int y)
    {
        return meal[x][y];
    }
}
