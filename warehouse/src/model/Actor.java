package model;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Actor is an abstract class which has the abstract method tic. actor is inherited by all the main components.
 */
// actor class which is inherited by all robots, stations,charging pods and shelfs
public abstract class Actor {
// has unique ids and a location object
	
	/** The uid. */
private String UID;
	
	/** The location. */
	private Location location;
	
	
	/**
	 * Instantiates a new actor.
	 *
	 * @param Idenifier the idenifier
	 * @param LastUID the last UID
	 * @param row the row
	 * @param col the col
	 */
	public Actor(String Idenifier, int LastUID,int row,int col) {// setting the last Uid and the identifyer and location
		
		
		this.UID=Idenifier+ LastUID;
		
		location= new Location(row,col);
		
	}
	
	/**
	 * Tick.
	 * abstract method wich retrives the hashmap of actors which are stored in the {@link Warehouse} and uses the parameters to act out behaviour
	 * @param warehouseActors the warehouse actors
	 * @param Orders -is a hashmap of orders 
	 * @see Order 
	 */
	public abstract void tick(HashMap<String,Actor>warehouseActors,HashMap<String,Order>Orders);// takes the warehouse actors and orders.
	
	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public String getUID() {// return Uid
		return UID;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [" + UID + ", mylocation=" + location.getCol()+","+location.getRow() + "]";
	}
	
	/**
	 * Sets the location.
	 *
	 * @param loc the new location
	 */
	public void setLocation(Location loc)//location entered to set the actors location
	{
		location.setLocation(loc);
		
	}
	
	 /**
 	 * Gets the location.
 	 *
 	 * @return the location
 	 */
 	public Location getLocation() // actors location returned
	 {
		 return location;
		 
	 }
}
