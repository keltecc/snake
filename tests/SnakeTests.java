package snake.tests;

import org.junit.Test;
import org.junit.Assert;

import snake.Direction;
import snake.Point;
import snake.Snake;

public class SnakeTests 
{
   private final int HEIGHT_MAP = 4;
   private final int WIDTH_MAP = 3;

   @Test
   public void testInitialization()
   {
	   assertInitSnake(new Point(0, 1), Direction.UP, 2);
	   assertInitSnake(new Point(-2, 7), Direction.DOWN, 1000);
	   assertInitSnake(new Point(0, 0), Direction.LEFT, 100);
	   assertInitSnake(new Point(-1, -1), Direction.RIGHT, 1);
   }
   
   public void assertInitSnake(Point head, Point direction, int length)
   {
	   Snake snake = new Snake(head, direction, length);
	   Assert.assertEquals(length, snake.getLength());
	   Point[] body = snake.getTrace();
	   Assert.assertEquals(length, body.length);
	   direction.multiply(-1);
       for (int index = 0; index < body.length; index++)
       {
          Assert.assertEquals(head, body[index]);
          head.add(direction);
       }
   }
   
   @Test
   public void testStep()
   {
	   int length = 3;
	   Snake snake = new Snake(new Point(0, 1), Direction.UP, length);
	   testSnakeAfterStep(new Point[] {new Point(0, 0), new Point(0, 1), new Point(0, 2)}, snake);
	   testSnakeAfterStep(new Point[] {new Point(0, 3), new Point(0, 0), new Point(0, 1)}, snake);
	   snake.direction = Direction.LEFT;
	   testSnakeAfterStep(new Point[] {new Point(2, 3), new Point(0, 3), new Point(0, 0)}, snake);
	   snake.direction = Direction.UP;
	   testSnakeAfterStep(new Point[] {new Point(2, 2), new Point(2, 3), new Point(0, 3)}, snake);
	   snake.direction = Direction.RIGHT;
	   testSnakeAfterStep(new Point[] {new Point(0, 2), new Point(2, 2), new Point(2, 3)}, snake);
	   snake.direction = Direction.DOWN;
	   testSnakeAfterStep(new Point[] {new Point(0, 3), new Point(0, 2), new Point(2, 2)}, snake);
   }
   
   public void testSnakeAfterStep(Point[] expected, Snake snake)
   {
	   snake.makeStep(WIDTH_MAP, HEIGHT_MAP);
	   Point [] body = snake.getTrace();
	   Assert.assertEquals(expected, body);
   }
   
   @Test
   public void testGrowing()
   {
	   int length = 1;
	   int targetLength = 15;
	   Snake snake = new Snake(new Point(0, 0), Direction.DOWN, length);
	   int value = targetLength- length;
	   snake.adjustLength(value);
	   if (value > 0)
       {
          for (int k = 0; k < value; k++)
          	snake.makeStep(100, 100);
       }
	   Assert.assertEquals(targetLength, snake.getLength());
   }
}
