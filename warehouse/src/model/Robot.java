package model;
import java.util.HashMap;
import java.util.Iterator;


// TODO: Auto-generated Javadoc
/**
 * The Class Robot.
 */
//// this class accepts jobs from the pk if the cost is not to high
public class Robot extends Actor {

	/** The order is {@link Order}. */
	private Order orderr;
	
	/** is an {@link Item}. */
	private Item item;
	
	/** weather busy with order. */
	private boolean busyWithOrder;
	
	/** weather the robot is charging or not. */
	private boolean charging;
	
	/** The low battery value. */
	private boolean lowBattery;

	/** The target uid. */
	private String targetUid;
	
	/** The Cpod uid. */
	private String CpodUid;
	
	/** The pathstatergy .
	 * @see XpathFindingStratergy */
	private PathFindingStratergy pathstatergy;
	
	/** The cst statergy. */
	private CostEstimationStratergy cstStatergy;
	
	/** The battery percentage. */
	private int battery;
	
	/** The maxbattery battery value. */
	public static int maxbattery;
	
	/** The crash indicator. */
	private boolean crash;
	
	/** The maxrow. */
	public static int maxrow;
	
	/** The maxcol. */
	public static int maxcol;
	
	/** The batterydead. */
	private boolean batterydead;

	/**
	 * Instantiates a new robot.
	 *
	 * @param lUid the l uid
	 * @param row the row
	 * @param col the col
	 */
	public Robot(int lUid, int row, int col) {
		super("r", lUid, row, col);

		
		busyWithOrder = false;
		charging = false;
		pathstatergy = new simplepathFindingStratergy();
		cstStatergy = new XcostEstimationStratergy();
		battery = maxbattery;
		
		lowBattery = false;
		CpodUid = "c" + lUid;
		crash = false;
		batterydead=false;
		

	}

	/**
	 * Checks if is battery low.
	 *
	 * @return true, if is battery low
	 */
	public boolean isBatteryLow() {

		return lowBattery;
	}

	/**
	 * Checkifbatteryisdead.
	 *
	 * @return true, if successful
	 */
	public boolean checkifbatteryisdead()
	{
		return batterydead;
		
		
	}
	
	/**
	 * Increment battery percent.
	 *
	 * @param c the c
	 */
	public void incrementBatteryPercent(int c)// charging pod uses this to charge battery
	{
		if(charging==true)
		{
		if (battery<maxbattery)
		{
			
			battery += c;
		}

		if (battery >= maxbattery/2) {
			
			lowBattery = false;
			charging = false;

		}
		}
		
	}

	/**
	 * Gets the chargingpod id.
	 *
	 * @return the chargingpod id
	 */
	public String getchargingpodId() {
		return CpodUid;

	}

	/**
	 * Accept order from pk station if free and the cost is not to high.
	 *
	 * @param order the order
	 * @param actors the actors
	 * @return true, if successful
	 */
	public boolean acceptOrderFromPkStation(Order order, HashMap<String, Actor> actors)// choses to except order or not
	{
		
		int cost = cstStatergy.estimateCost(super.getLocation(), order.getPkingStationUid(),actors,order.getallshelfsnotpicked());
			cost+=cost/4;
		if (busyWithOrder == false &&( battery -cost > 0|| battery>maxbattery-15) && lowBattery==false) {
			orderr = order;
			orderr.assigneordertorb();
			busyWithOrder = true;
			item = orderr.getshelfnotcollected();

			targetUid = item.getshelfUid();
			return true;

		} else {

			return false;
		}

	}
	
	/**
	 * Gets the crashval.
	 *
	 * @return the crashval
	 */
	public boolean getcrashval()
	{
		
		return crash;
	}
	
	/**
	 * Checkifrbcrashes- checks for collisions.
	 *
	 * @param warehouseActors the warehouse actors
	 */
	private void checkifrbcrashes(HashMap<String, Actor> warehouseActors)
	{
		Iterator<HashMap.Entry<String, Actor>> actorsit = warehouseActors.entrySet().iterator();
		while (actorsit.hasNext()) {
			HashMap.Entry<String, Actor> entry = actorsit.next();
			if ( entry.getValue() instanceof Robot&& entry.getValue().getLocation().equals(getLocation())&& entry.getKey().equals(getUID())==false)
			{
				
				crash=true;
				
			}
			
		}
		
		
	}

	/**
	 * Tick. checks if the battery is dead, low and does collision detection and is incharge of charging and moving to destination
	 *
	 * @param warehouseActors the warehouse actors
	 * @param Orders the orders
	 */
	public void tick(HashMap<String, Actor> warehouseActors, HashMap<String, Order> Orders)// every tick checks if
																							// battery is low and
	// processes order
	{
		checkifrbcrashes(warehouseActors);
		if(orderr!=null&& item!=null)
		{
			if (battery<=0) {
				
				batterydead=true;
			}
			
				if(battery-cstStatergy.calculatenumbofsteps(getLocation(), warehouseActors.get(targetUid).getLocation())<1)
				{
				lowBattery = true;
				
			}
			
		}
		

		if (lowBattery == false && busyWithOrder == true&& charging==false) {
			

			if (item.hasitembeenrecivedbypk() == false && item.hasshelfbeencollected() == false) {

				if (orderr.shelfExists(targetUid) == false && orderr.checkifallitemspicked() == false) {

					item = orderr.getshelfnotcollected();
					targetUid = item.getshelfUid();

				}

				if (orderr.shelfExists(targetUid) == true) {
					// if (orderr.checkIfashelfpicked(targetUid) == false) {
					if (item.hasshelfbeencollected() == false) {

						Location tmp = new Location(super.getLocation().getRow(), super.getLocation().getCol());

						
						super.setLocation(pathstatergy.nextLoc(tmp, targetUid, warehouseActors,maxcol,maxrow));
						
						if (tmp.equals(super.getLocation()) == false) {

							battery -= 1;
						}
						if (super.getLocation().equals(warehouseActors.get(targetUid).getLocation())) {
							item.setitemcollected();
							// orderr.shelfPicked(targetUid);
							// item=orderr.getshelfnotcollected();
							// targetUid = orderr.getshelfnotcollected();//////

							targetUid = orderr.getPkingStationUid();//////

						}
					}

				}

			} else if (item.hasshelfbeencollected() && item.hasitembeenrecivedbypk() == false) {
				targetUid = orderr.getPkingStationUid();
				Location tmp = new Location(super.getLocation().getRow(), super.getLocation().getCol());
				
				super.setLocation(pathstatergy.nextLoc(tmp, targetUid, warehouseActors,maxcol,maxrow));
				if (tmp.equals(super.getLocation()) == false) {

					battery -= 2;
				}
				if (super.getLocation().equals(warehouseActors.get(targetUid).getLocation())) {
					item.setitemrecivedbypk();
					// orderr.shelfPicked(targetUid);
					// item=orderr.getshelfnotcollected();
					// targetUid = orderr.getshelfnotcollected();//////

				}

			} else if (item.hasshelfbeencollected() && item.hasitembeenrecivedbypk()
					&& orderr.checkifallitemspicked() == false) {

				item = orderr.getshelfnotcollected();
				targetUid = item.getshelfUid();

			}
			if (orderr.checkifallitemsdeliveredtopk() == true) {

				busyWithOrder = false;

			}
		}

		else {
			
		//	if battery
			if (super.getLocation().equals(warehouseActors.get(CpodUid).getLocation()) == false) {
				if(battery-cstStatergy.calculatenumbofsteps(getLocation(), warehouseActors.get(CpodUid).getLocation())<=0)
				{
					lowBattery=true;
					
					
				}
				super.setLocation(pathstatergy.nextLoc(super.getLocation(), CpodUid, warehouseActors,maxcol,maxrow));
				



			} else {
					if(battery!=maxbattery)
					{
						charging = true;
						
					}
				
			}

		
		}


	}

	/* (non-Javadoc)
	 * @see Actor#toString()
	 */
	@Override
	public String toString() {
		
		String robotproperty="["+battery+"/"+maxbattery+"]"+super.toString();
		if (busyWithOrder)
		{
			robotproperty+="busy with order"+orderr.getUid()+"  "+item.getshelfUid();
			
		}
		if (charging==true)
		{
			robotproperty+="charging";
		}

		if (crash==true)
		{
			robotproperty+="crash";
		}
		
		return robotproperty;
	}
}
