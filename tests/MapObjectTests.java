package snake.tests;

import org.junit.Test;
import org.junit.Assert;

import snake.MapObject;
import snake.Apple;
import snake.Portal;
import snake.Wall;
import snake.Point;

public class MapObjectTests
{
	private final Class[] MAP_OBJECT_SUBCLASSES = {Apple.class, Wall.class, Portal.class};
	
	@Test
	public void testInit()
	{
		Point position = new Point(0, 1);
		Point newPosition = new Point(3, 7);
		/*
		for (Class subclass: MAP_OBJECT_SUBCLASSES)
		{
			(MapObject)subclass.getConstructors()[0].newInstance(position);
			Assert.assertEquals(position, (MapObject)subclass.getPosition());
			(MapObject)subclass.setPosition(newPosition);
			Assert.assertEquals(newPosition, (MapObject)subclass.getPosition());
		}
		*/
		Apple apple = new Apple(position);
		Assert.assertEquals(position, apple.getPosition());
		apple.setPosition(newPosition);
		Assert.assertEquals(newPosition, apple.getPosition());
		
		Wall wall = new Wall(position);
		Assert.assertEquals(position, wall.getPosition());
		wall.setPosition(newPosition);
		Assert.assertEquals(newPosition, wall.getPosition());
				
		Portal portal = new Portal(position);
		Assert.assertEquals(position, portal.getPosition());
		portal.setPosition(newPosition);
		Assert.assertEquals(newPosition, portal.getPosition());	
	}
	
	@Test
	public void testFindPosition()
	{
		
	}
}
