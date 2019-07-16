package warehouse;

public abstract class Entity {

	private Location loc;
	protected String uid;
	
	
	abstract void tick();
	
	public String getuid()
	{
		
		return uid;
	}

	
}
