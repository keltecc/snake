package snake;

public interface EatingObject 
{
	public EatingResult interact(Level level);
	public Point getLocation();
}
