package snake;

public abstract class MapObject 
{
	private Vector location;
	protected Game game;
	
	public MapObject(Game game, Vector location) 
	{
		this.game = game;
		this.location = location.clone();
	}
	
	public void interact() {}
	public void parse(String info) {};
	
	public void delete()
	{
		game.level.map.set(location, null);
	}
	
	public Vector getLocation()
	{
		return location.clone();
	}
	
	public void setLocation(Vector newLocation)
	{
		newLocation = newLocation.bound(game.level.map.getSize());
		delete();
		game.level.map.set(newLocation, this);
		this.location = newLocation.clone();
	}
	
	public void changeLocation(Vector delta)
	{
		setLocation(getLocation().add(delta));
	}
}
