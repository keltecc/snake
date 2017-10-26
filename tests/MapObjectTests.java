package snake.tests;

import org.junit.Test;
import java.lang.reflect.Method;
import org.junit.Assert;
import snake.*;

public class MapObjectTests
{
    private final Class[] MAP_OBJECT_SUBCLASSES = {Apple.class, Wall.class, Portal.class};
    
    @Test
    public void testInit() throws Exception
    {
        Point position = new Point(0, 1);
        Point newPosition = new Point(3, 7);
        for (Class subclass: MAP_OBJECT_SUBCLASSES)
        {
            MapObject object = (MapObject)subclass.getConstructors()[0].newInstance(position);
            Assert.assertEquals(position, object.getPosition());
            object.setPosition(newPosition);
            Assert.assertEquals(newPosition, object.getPosition());
        }
    }
    
    @Test
    public void testFindEmptyPosition() throws Exception
    {
    	Point position = new Point(5, 5);
    	Level level = (new TestLevelLoader()).load(1);
    	for (Class subclass: MAP_OBJECT_SUBCLASSES)
        {
            MapObject object = (MapObject)subclass.getConstructors()[0].newInstance(position);
            Assert.assertEquals(position, object.getPosition());
            //Method method = MapObject.class.getMethod("findEmptyPosition", Snake.class, GameMap.class);
            for (Method m : MapObject.class.getMethods())
            	System.out.println(m.getName());
            //Point found = (Point)method.invoke(object, level.snake, level.map);
            //assert found != null;
            //assert level.map.getObject(found.x, found.y) == null;
        }
    }
}
