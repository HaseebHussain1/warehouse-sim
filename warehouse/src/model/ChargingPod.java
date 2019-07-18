package model;
import java.util.HashMap;
// TODO: Auto-generated Javadoc

/**
 * The Class ChargingPod.
 */
// charging pod inherits the Actor and it hold a static uid to allow unique id per robot
public class ChargingPod extends Actor  {
	

	/** The Robot uid. */
	private String RbUid;
	 
	/** The charge value. */
	public static int chargeValue;
	
	/**
	 * Instantiates a new charging pod.
	 *
	 * @param lUid is the last unique id 
	 * @param row the row
	 * @param col the col
	 */
	public ChargingPod(int lUid,int row,int col)// geting row and coll to set location in actor class and cval is the amount that the charging pod charges the rb
	{
		
		super("c",lUid, row, col);
		

		RbUid="r"+lUid;
		
		
	}
	
	/**
	 * Gets the rb id.
	 *
	 * @return the rb id
	 */
	public String getRbId()
	{
		return RbUid;
		
	}
	
	/**
	 * Tick.
	 * this method checks if corosonding {@link Robot} is at the chargingpods location
	 *
	 * @param warehouseActors the warehouse actors
	 * @param Orders the orders
	 */
	public void tick(HashMap<String,Actor>warehouseActors,HashMap<String,Order>Orders)// takes the warehouse actors and orders.
	{// corosponding robot is stored in robot and the location is compared and if the battery is low the robots battery value will incrmente by charge value.
		Robot robot=(Robot)warehouseActors.get(RbUid);
		if (super.getLocation().equals(robot.getLocation()))
		{
			robot.incrementBatteryPercent(chargeValue);
			
			
		}
		
		
	}

}
