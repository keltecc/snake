package snake.tests;

import org.junit.Test;
import org.junit.Assert;

import snake.GameMap;
import snake.Apple;
import snake.Wall;
import snake.Portal;

public class GameMapTests 
{
   @Test
   public void testSize()
   {
	   setSize(0,0);
	   setSize(1000, 12);
   }
   
   public void setSize(int width, int height) 
   {
      GameMap map = new GameMap(width, height);
      Assert.assertEquals(width, map.width());
      Assert.assertEquals(height, map.height());
   }

   @Test
   public void testGetSet()
   {
	   GameMap map = new GameMap(7, 7);
	   for (int x = 0; x < map.width(); x++)
       {
          for (int y = 0; y < map.height(); y++)
          {
        	 Assert.assertNull(map.getObject(x, y));
             map.setObject(x, y, new Wall(x, y));
             Assert.assertTrue(map.getObject(x, y) instanceof Wall);
             map.setObject(x, y, null);
             
             Assert.assertNull(map.getObject(x, y));
             map.setObject(x, y, new Apple(x, y));
             Assert.assertTrue(map.getObject(x, y) instanceof Apple);
             map.setObject(x, y, null);
             
             Assert.assertNull(map.getObject(x, y));
             map.setObject(x, y, new Portal(x, y));
             Assert.assertTrue(map.getObject(x, y) instanceof Portal);
             map.setObject(x, y, null);
          }
       }   
   }
}
