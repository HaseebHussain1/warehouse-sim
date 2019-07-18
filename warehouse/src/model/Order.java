package model;

import java.util.HashMap;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class Order.
 */
// this class creates an order object which is used by many other classes
public class Order {

	/** The last UId number. */
	private static int lastUIDnumber = 0;
	
	/** The uid string. */
	private String UID;// has uid to idnetify each object
	
	/**  isassignedtopk- contains the boolean value of wheather the order has been assigned to pk. 
	 * @see packingStation which uses the boolean value to take up orders
	 * */
	private boolean isAssignedtoPk;
	
	/** The isassignedtorb - holds boolean of wheather the order has been assigned to robot. */
	private boolean isAssignedtoRb;
	
	/** The packing ticks- the number ofticks requried to complete order {@link packingStation#tick(HashMap, HashMap)} */
	private int packingTicks;// order is complete in numb of ticks
	
	/** collection of items . */
	private HashMap<String, Item> shelfs;// has shelfs to collect from
	
	/** The order picked. */
	private boolean orderPicked;
	
	/** The recivedby pk station = knows if the order has been recived by packing station. */
	private boolean recivedbyPkStation;
	
	/** The Dispatched boolean controls when the {@link packingStation} can pick next order. */
	private boolean Dispatched;
	
	/** The totalticks. */
	private int totalticks;// total numb of ticks to collect to dispatch order
	
	/** The Packing station UID. */
	private String PackingStationUID;// uid of target aking station

	/**
	 * Instantiates a new order.
	 *
	 * @param pkingticks the pkingticks
	 * @param shelfsvalue the shelfsvalue
	 */
	public Order(int pkingticks, HashMap<String, Item> shelfsvalue)// Still have to set shelfs hashmap
	{
		lastUIDnumber += 1;
		this.UID = "o" + lastUIDnumber;
		packingTicks = pkingticks;
		totalticks = 0;
		isAssignedtoPk = false;
		isAssignedtoRb = false;
		orderPicked = false;
		recivedbyPkStation = false;
		Dispatched = false;
		this.shelfs = shelfsvalue;

	}
	
	/**
	 * Checks if is orderassignedtorb.
	 *
	 * @return true, if is orderassignedtorb
	 */
	public boolean isorderassignedtorb()
	{
		return isAssignedtoRb;
		
	}
	
	/**
	 * Assigneordertorb.
	 */
	public void assigneordertorb()
	{
		isAssignedtoRb=true;
		
	}
	
	/**
	 * Checks if is assignedto pk.
	 *
	 * @return true, if is assignedto pk
	 */
	public boolean isAssignedtoPk()
	{
		return isAssignedtoPk;
		
	}

	
	/**
	 * Checks if is order dispatched.
	 *
	 * @return true, if is order dispatched
	 */
	public boolean isorderDispatched()
	{
		return Dispatched;
		
	}
	
	/**
	 * Dispatched.
	 */
	public void Dispatched()
	{
		Dispatched=true;
		
	}
	
/**
 * Checkiforderrecivedbypk.
 *
 * @return true, if successful
 */
public boolean checkiforderrecivedbypk()
{
return recivedbyPkStation;	
}
	
	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public String getUid()// returns order uid
	{
		return UID;
	}

	/**
	 * Gets the packing ticks.
	 *
	 * @return the packing ticks
	 */
	public int getPackingTicks()// returns the number of pk ticks
	{
		return packingTicks;
	}

	/**
	 * Decrease P kticks.
	 */
	public void decreasePKticks()// decreases the packing ticks
	{
		if (packingTicks > 0) {
			packingTicks -= 1;
		}
	}

	/**
	 * Increment total ticks.
	 */
	public void incrementTotalTicks()// increments total ticks required for order
	{

		if (Dispatched != true && isAssignedtoPk)

		{
			totalticks++;

		}

	}

	/**
	 * Gets the total ticks.
	 *
	 * @return the int
	 */
	public int GetTotalTicks() {

		return totalticks;
	}

	/**
	 * Gets a shelf that has not been collected.
	 *
	 * @return the shelfnotcollected
	 */
	public Item getshelfnotcollected()// returns the shelfs that have not been picked
	{

		Iterator<HashMap.Entry<String, Item>> entries = shelfs.entrySet().iterator();
		while (entries.hasNext()) {
			HashMap.Entry<String, Item> entry = entries.next();
			if (entry.getValue().hasshelfbeencollected() == false) {
				return entry.getValue();

			}

		}
		orderPicked = true;
		return null;
	}
	
	/**
	 * Checkifallitemsdeliveredtopk.
	 *
	 * @return true, if successful
	 */
	public boolean checkifallitemsdeliveredtopk()// returns the shelfs that have not been picked
	{

		Iterator<HashMap.Entry<String, Item>> entries = shelfs.entrySet().iterator();
		while (entries.hasNext()) {
			HashMap.Entry<String, Item> entry = entries.next();
			if (entry.getValue().hasitembeenrecivedbypk() == false) {
				return false;

			}

		}
		recivedbyPkStation=true;
		return true;
	}
	
	/**
	 * Checkifallitemspicked.
	 *
	 * @return true, if successful
	 */
	public boolean checkifallitemspicked()// returns the shelfs that have not been picked
	{

		Iterator<HashMap.Entry<String, Item>> entries = shelfs.entrySet().iterator();
		while (entries.hasNext()) {
			HashMap.Entry<String, Item> entry = entries.next();
			if (entry.getValue().hasshelfbeencollected() == false) {
				return false;

			}

		}
		
		return true;
	}



	/**
 * Check ifashelfpicked.
 *
 * @param ssuid the ssuid
 * @return true, if successful
 */
public boolean checkIfashelfpicked(String ssuid)// returns if the specific shelf has been picked
	{

		return shelfs.get(ssuid).hasshelfbeencollected();

	}

	/**
	 * Shelf exists.
	 *
	 * @param uid the uid
	 * @return true, if successful
	 */
	public boolean shelfExists(String uid)// check if shelfExists
	{

		if (!shelfs.containsKey(uid)) {

			return false;
		} else {
			return true;
		}

	}



	/**
	 * Gets the order picked.
	 *
	 * @return the order picked
	 */
	public boolean getOrderPicked() {
		return orderPicked;
	}



	/**
 * Assign ordertopkstation.
 *
 * @param UID the uid
 */
public void assignOrdertopkstation(String UID)// assignes order to pk by storing Uid
	{
		PackingStationUID = UID;
		isAssignedtoPk = true;
	}

	/**
	 * Gets the pking station uid.
	 *
	 * @return the pking station uid
	 */
	public String getPkingStationUid() {

		return PackingStationUID;
	}
	
	

	
	
	/**
	 * Gets the allshelfsnotpicked.
	 *
	 * @return the allshelfsnotpicked
	 */
	public HashMap<String, Boolean> getallshelfsnotpicked()
	{
		HashMap<String, Boolean> shelfsonly= new HashMap<String, Boolean>();
		Iterator<HashMap.Entry<String, Item>> entries = shelfs.entrySet().iterator();
		while (entries.hasNext()) {
			
			HashMap.Entry<String, Item> entry = entries.next();
			if(entry.getValue().hasshelfbeencollected()==false)
			{
				shelfsonly.put(entry.getKey(), false);
				
			}
			
		}
		
		return shelfsonly;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String orderproperty="["+ UID+"   "+packingTicks;
		
		Iterator<HashMap.Entry<String, Item>> entries = shelfs.entrySet().iterator();
		while (entries.hasNext()) {
			
			HashMap.Entry<String, Item> entry = entries.next();
			orderproperty+="  "+entry.getKey();
			
		}
		
		if(Dispatched)
		{
			
			orderproperty+="  dispatched";
			
		}else if (recivedbyPkStation) {
			
			orderproperty+="  recived by pks";
		}else if (isAssignedtoRb) {
			
			orderproperty+="  isAssignedtoRb=" + isAssignedtoRb;
		}
		else if (isAssignedtoPk) {
			
			orderproperty+=" assigned to pk";
		}
		
		
		
		return orderproperty;
	}
}
