package snake.tests;

import java.util.Random;
import org.junit.Test;

import snake.Direction;
import snake.Point;
import snake.Snake;

public class SnakeTests 
{
   private final int ITERATIONS_COUNT = 1000;
   private final Point[] DIRECTIONS = {
         Direction.DOWN,
         Direction.LEFT,
         Direction.UP,
         Direction.RIGHT
   };
   
   private Random random;
   
   public SnakeTests()
   {
      random = new Random();
   }
   
   @Test
   public void testInitialization() 
   {
      for (int i = 0; i < ITERATIONS_COUNT; i++)
      {
         int length = random.nextInt(99) + 1;
         Point part = new Point(random.nextInt(1000), random.nextInt(1000));
         Point direction = DIRECTIONS[random.nextInt(4)].clone();
         Snake snake = new Snake(part.clone(), direction.clone(), length);
         assert snake.getLength() == length;
         Point[] body = snake.getTrace();
         assert body.length == length;
         direction.multiply(-1);
         for (int index = 0; index < body.length; index++)
         {
            assert body[index].equals(part);
            part.add(direction);
         }
      }
   }
   
   @Test
   public void testSteps()
   {
      Point head = new Point(random.nextInt(1000), random.nextInt(1000));
      Point direction = DIRECTIONS[random.nextInt(4)];
      int length = random.nextInt(900) + 100;
      Snake snake = new Snake(head.clone(), direction.clone(), length);
      Point[] points = new Point[length];
      points[0] = head;
      direction.multiply(-1);
      for (int index = 1; index < length; index++)
         points[index] = Point.getSum(points[index - 1], direction);
      direction.multiply(-1);
      for (int i = 0; i < ITERATIONS_COUNT; i++)
      {
         snake.makeStep();
         Point[] trace = snake.getTrace();
         for (int index = 0; index < length; index++)
         {
            points[index].add(direction);
            assert points[index].equals(trace[index]);
         }
      }
   }
   
   @Test
   public void testGrowing()
   {
      Point head = new Point(random.nextInt(1000), random.nextInt(1000));
      Point direction = DIRECTIONS[random.nextInt(4)];
      int length = random.nextInt(900) + 100;
      Snake snake = new Snake(head, direction, length);
      for (int i = 0; i < ITERATIONS_COUNT; i++)
      {
         int previous = snake.getLength();
         int next = random.nextInt(999) + 1;
         int value = next - previous;
         snake.adjustLength(value);
         if (value > 0)
         {
            for (int k = 0; k < value; k++)
               snake.makeStep();
         }
         assert snake.getLength() == next;
      }
   }
}
