package model;
import java.util.HashMap;
import java.util.Iterator;
// TODO: Auto-generated Javadoc

/**
 * The Class WarehouseStats.
 */
public class WarehouseStats {
	
	/** The totalnumofticks. */
	private int totalnumofticks;
	
	/** The order complete. */
	private boolean orderComplete;
	
	/** The crash. */
	private boolean crash;
	
	/** The simulationover. */
	private boolean simulationover;
	
	/** The deadbattery. */
	private boolean deadbattery;
	
/**
 * Instantiates a new warehouse stats.
 */
public WarehouseStats()
{
	totalnumofticks=0;
	orderComplete=false;
	crash=false;
	simulationover=false;
	deadbattery=false;
}

/**
 * Incrementticks.
 */
public void incrementticks()
{
	totalnumofticks+=1;

}

/**
 * Gets the ordercomplete.
 *
 * @return the ordercomplete
 */
public boolean getordercomplete()
{
	return orderComplete;
}

/**
 * Check ordercomplete.
 *
 * @param Orders the orders
 * @return true, if successful
 */
private boolean checkOrdercomplete(HashMap<String,Order>Orders)
{
	
	int numberoforderscomplete=0;
	Iterator<HashMap.Entry<String, Order>> entries = Orders.entrySet().iterator();
	while (entries.hasNext()) {
	    HashMap.Entry<String, Order> entry = entries.next();
	    
	    
	  if (entry.getValue().isorderDispatched())
	  {
		 
		  numberoforderscomplete+=1;
	  }
	  
	   
	}
	
	if (numberoforderscomplete== Orders.size())
	{
		
		orderComplete=true;
		simulationover=true;
	}else {
	
	orderComplete=false;}
	return orderComplete;
	
}



/**
 * Checkifrbcrash.
 *
 * @param warehouseActors the warehouse actors
 */
private void checkifrbcrash(HashMap<String, Actor> warehouseActors)
{
	
	int i = 0;
	
	while (warehouseActors.containsKey("r" + i)) {
	Robot r=(Robot)warehouseActors.get("r"+i);
		if(r.getcrashval())
		{
			crash=true;
			simulationover=true;
			
		}
		i += 1;
		
	}
	

	
	
}

/**
 * Checkifbatteryisdead.
 *
 * @param warehouseActors the warehouse actors
 */
public void checkifbatteryisdead(HashMap<String, Actor> warehouseActors)
{
	Iterator<HashMap.Entry<String, Actor>> actors = warehouseActors.entrySet().iterator();
	while (actors.hasNext()) {
		HashMap.Entry<String, Actor> entry = actors.next();
		if (entry.getValue()instanceof Robot)
		{
		Robot r=(Robot)entry.getValue();
	deadbattery=r.checkifbatteryisdead();
		if(deadbattery==true)
		{
			simulationover=true;
		
		}
			
		}
		
	
	
	}	

}

/**
 * Gets the batterydeadvalue.
 *
 * @return the batterydeadvalue
 */
public boolean getbatterydeadvalue()
{
return deadbattery;	
}

/**
 * Checkifsimisover.
 *
 * @param warehouseActors the warehouse actors
 * @param orders the orders
 */
public void checkifsimisover(HashMap<String, Actor> warehouseActors,HashMap<String, Order>orders)
{
	checkOrdercomplete(orders);
	checkifrbcrash(warehouseActors);
	
	checkifbatteryisdead(warehouseActors);
	
	
}

/**
 * Gets the crash.
 *
 * @return the crash
 */
public boolean getcrash()
{
	return crash;
	
}
//// need to use theese methods


/**
 * Gets the totalnumofticks.
 *
 * @return the totalnumofticks
 */
public int getTotalnumofticks() {
	return totalnumofticks;
}

/**
 * Checks if is simulationover.
 *
 * @return true, if is simulationover
 */
public boolean isSimulationover() {
	return simulationover;
}





}
