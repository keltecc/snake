package snake.tests;

import org.junit.Test;
import org.junit.Assert;

import snake.Point;

public class PointTests 
{
   @Test
   public void testEquals()
   {
       assertEquals(true, new Point(8, 1), new Point(8, 1));
       assertEquals(true, new Point(-7, 2), new Point(-7, 2));
       assertEquals(true, new Point(-4, -12), new Point(-4, -12));
       assertEquals(true, new Point(0, 0), new Point(0, 0));
       assertEquals(false, new Point(-1, 1), new Point(1, -1));
   }

   public void assertEquals(boolean expected, Point point1, Point point2) 
   {
      Assert.assertEquals(expected, point1.equals(point2));
      Assert.assertEquals(expected, point2.equals(point1));
   }
   
   @Test
   public void testClone()
   {
      Point [] points = new Point []
              {new Point(0,0), new Point(-7, 9), new Point(-12, -34), new Point(1, 2)};
      for (Point point: points)
          Assert.assertEquals(point, point.clone());
   }
   
   @Test
   public void testParsing()
   {
      Assert.assertTrue(Point.parse("1-1", "-").equals(new Point(1, 1)));
      Assert.assertTrue(Point.parse("12,7", ",").equals(new Point(12, 7)));
      Assert.assertTrue(Point.parse("1;4", ";").equals(new Point(1, 4)));
      Assert.assertTrue(Point.parse("3#-5", "#").equals(new Point(3, -5)));
      Assert.assertTrue(Point.parse("-2i-2", "i").equals(new Point(-2, -2)));
    }
   
   @Test
   public void testScalarProduction()
   {
      Assert.assertEquals(0, Point.getScalarProduct(new Point(0, 0), new Point(0, 0)));
      Assert.assertEquals(0, Point.getScalarProduct(new Point(0, 0), new Point(15, 2)));
      Assert.assertEquals(0, Point.getScalarProduct(new Point(0, 0), new Point(-7, 4)));
      Assert.assertEquals(12, Point.getScalarProduct(new Point(2, 3), new Point(3, 2)));
      Assert.assertEquals(0, Point.getScalarProduct(new Point(-10, -5), new Point(1, -2)));
   }
   
   @Test
   public void testDistance()
   {
      Assert.assertEquals(8.48, Point.getDistance(new Point(1, 1), new Point(7, 7)), 1e10);
      Assert.assertEquals(1, Point.getDistance(new Point(2, 1), new Point(1, 1)), 1e10);
      Assert.assertEquals(1, Point.getDistance(new Point(1, 1), new Point(2, 1)), 1e10);
      Assert.assertEquals(2, Point.getDistance(new Point(3, 4), new Point(3, 6)), 1e10);
      Assert.assertEquals(0, Point.getDistance(new Point(7, 3), new Point(7, 3)), 1e10);
      Assert.assertEquals(1, Point.getDistance(new Point(0, 0), new Point(-1, 0)), 1e10);
      Assert.assertEquals(5.83, Point.getDistance(new Point(-2, -4), new Point(1, 1)), 1e10);
   }
   
   @Test
   public void testSummation()
   {
      assertEqualsSumAndAdd(new Point(0, 0), new Point(-1, -1), new Point(1, 1));
      assertEqualsSumAndAdd(new Point(6, 6), new Point(4, 2), new Point(2, 4));
   }
   
   public void assertEqualsSumAndAdd(Point expected, Point p1, Point p2)
   {
       Assert.assertEquals(expected, Point.getSum(p1, p2));
       p1.add(p2);
       Assert.assertEquals(expected, p1);
   }
   
   @Test
   public void testSummationBounds()
   {
       Assert.assertEquals(new Point(0, 0),
               Point.getSumBounds(new Point(12, 13), new Point(-2, -3), 5, 5));
       Assert.assertEquals(new Point(7, 2),
               Point.getSumBounds(new Point(4, 11), new Point(3, -1), 8, 4));
   }

   @Test
   public void testMultiplication()
   {
      assertEqualsMultiply(new Point(0, 0), new Point(4, 12), 0);
      assertEqualsMultiply(new Point(3, 3), new Point(1, 1), 3);
      assertEqualsMultiply(new Point(3, 4), new Point(-3, -4), -1);
   }
   
   public void assertEqualsMultiply(Point expected, Point point, int value)
   {
       Assert.assertEquals(expected, Point.getMultiplication(point, value));
       point.multiply(value);
       Assert.assertEquals(expected, point);
   }
}
