package tests;

import java.util.Random;
import org.junit.Test;

import snake.Apple;
import snake.GameMap;

public class GameMapTests 
{
   private final int ITERATION_COUNT = 1000;
   
   private Random random;
   
   public GameMapTests()
   {
      random = new Random();
   }
   
   @Test
   public void testSize() 
   {
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         int width = random.nextInt(1000);
         int height = random.nextInt(1000);
         GameMap map = new GameMap(width, height);
         assert width == map.width();
         assert height == map.height();
      }
   }

   @Test
   public void testGetSet() 
   {
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         int width = random.nextInt(100);
         int height = random.nextInt(100);
         GameMap map = new GameMap(width, height);
         for (int x = 0; x < width; x++)
         {
            for (int y = 0; y < height; y++)
            {
               assert map.getTerrain(x, y) == false;
               assert map.getFood(x, y) == null;
               map.setTerrain(x, y, true);
               assert map.getTerrain(x, y) == true;
               map.setTerrain(x, y, false);
               assert map.getTerrain(x, y) == false;
               map.setFood(x, y, new Apple());
               assert map.getFood(x, y) != null;
               assert map.getFood(x, y) instanceof Apple;
               map.setFood(x, y, null);
               assert map.getFood(x, y) == null;
            }
         }
      }
   }
}
