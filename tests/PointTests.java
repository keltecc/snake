package tests;

import java.util.Random;
import org.junit.Test;

import snake.Point;

public class PointTests 
{
   private final int ITERATION_COUNT = 1000;
   
   private Random random;
   
   public PointTests()
   {
      random = new Random();
   }
   @Test
   public void testEquals() 
   {
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         int x = random.nextInt(1000);
         int y = random.nextInt(1000);
         assert new Point(x, y).equals(new Point(x, y));
         assert !new Point(x, y).equals(new Point(x + 1, y - 1));
      }
   }

   @Test
   public void testClone()
   {
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         Point p1 = new Point(random.nextInt(1000), random.nextInt(1000));
         Point p2 = p1.clone();
         assert p1.equals(p2);
      }
   }
   
   @Test
   public void testScalarProduction()
   {
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         Point p1 = new Point(random.nextInt(1000), random.nextInt(1000));
         Point p2 = new Point(-p1.y, p1.x);
         assert Point.getScalarProduct(p1, p2) == 0;
      }
   }
   
   @Test
   public void testMultiplication()
   {
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         int x = random.nextInt(1000);
         int y = random.nextInt(1000);
         int value = random.nextInt(1000);
         Point point = new Point(x, y);
         Point expected = new Point(x * value, y * value);
         assert Point.getMultiplication(point, value).equals(expected);
         point.multiply(value);
         assert point.equals(expected);
      }
   }
   
   @Test
   public void testSum()
   {
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         int x1 = random.nextInt(1000);
         int y1 = random.nextInt(1000);
         int x2 = random.nextInt(1000);
         int y2 = random.nextInt(1000);
         Point p1 = new Point(x1, y1);
         Point p2 = new Point(x2, y2);
         Point expected = new Point(x1 + x2, y1 + y2);
         assert Point.getSum(p1, p2).equals(expected);
         p1.add(p2);
         assert p1.equals(expected);
      }
   }
   
   @Test
   public void testParsing()
   {
      String symbols = "qwertyuiopasdfghjklzxcvbnm";
      for (int i = 0; i < ITERATION_COUNT; i++)
      {
         int x = random.nextInt(1000);
         int y = random.nextInt(1000);
         int index = random.nextInt(symbols.length());
         String symbol = Character.toString(symbols.charAt(index));
         String text = x + symbol + y;
         assert Point.parse(text, symbol).equals(new Point(x, y));
      }
   }
}
