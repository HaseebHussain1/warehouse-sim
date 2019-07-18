package model;
import java.util.HashMap;
import java.util.Iterator;

import com.sun.javafx.css.CalculatedValue;

// TODO: Auto-generated Javadoc
/**
 * The Class XcostEstimationStratergy.
 */
public class XcostEstimationStratergy implements CostEstimationStratergy {
	
	/* (non-Javadoc)
	 * @see CostEstimationStratergy#estimateCost(Location, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	
	/**
	 * estimate cost
	 * this method estimates cost from current location to all the shelfs and packing station
	  * @param sLoc is the starting location
	  * @param packingstationUid of the packsation so the location can be retrived
	 * @param Actors of the warehouse 
	 * @param shelfs
	 */
	public int estimateCost(Location sLoc,String packingstationUid, HashMap<String,Actor> Actors,HashMap<String, Boolean>shelfs) {
		Location newstartloc=new Location(sLoc.getRow(), sLoc.getCol());
		int cost=0;
		
		//if at start loc there is a shelf u should set that shelf to true;
		Iterator<HashMap.Entry<String, Boolean>> entriestmp = shelfs.entrySet().iterator();
		
		while (entriestmp.hasNext()) {
			HashMap.Entry<String, Boolean> newentry = entriestmp.next();
			if(Actors.get( newentry.getKey()).getLocation().equals(sLoc)) {
				shelfs.put(newentry.getKey(),true);
				
			}
			
		}
		
		
		Iterator<HashMap.Entry<String, Boolean>> entries = shelfs.entrySet().iterator();
		Location lastloc=null;
		while (entries.hasNext()) {
			HashMap.Entry<String, Boolean> entry = entries.next();
		//	shelfsonly.put(entry.getKey(), false);
			if(!entry.getValue()) {
			if (newstartloc!=null) {
			cost+=	calculatenumbofsteps(sLoc, Actors.get(entry.getKey()).getLocation());
			lastloc=Actors.get(entry.getKey()).getLocation();
			
			cost+=	calculatenumbofsteps(lastloc, Actors.get(packingstationUid).getLocation())*2;
			lastloc=Actors.get(packingstationUid).getLocation();
			shelfs.put(entry.getKey(), true);
			newstartloc=null;
				
			}else {
				
				
				
				cost+=	calculatenumbofsteps(lastloc, Actors.get(entry.getKey()).getLocation());
				lastloc=Actors.get(entry.getKey()).getLocation();
				cost+=	calculatenumbofsteps(lastloc, Actors.get(packingstationUid).getLocation())*2;
				lastloc=Actors.get(entry.getKey()).getLocation();
				shelfs.put(entry.getKey(), true);
				
			}
			}
		}
		
		return cost;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see CostEstimationStratergy#calculatenumbofsteps(Location, Location)
	 */
	/**
	 * calculate number of steps
	 * this method estimates cost from current location to target location
	  * @param start is the starting location
	  * @param destinationLocation is the target location
	 */
	public int calculatenumbofsteps(Location start,Location destinationLocation)
	{
		int cost=0;
		int startcol=start.getCol();
		int startrow=start.getRow();
		
		int descol=destinationLocation.getCol();
		int desrow=destinationLocation.getRow();
		
		if (startcol>descol)
		{
			cost +=startcol-descol;
			
			
		}else
		{
			
			cost +=descol-startcol;
		}
		
		if (startrow>desrow)
		{
			cost +=startrow-desrow;
			
			
		}else
		{
			
			cost +=desrow-startrow;
		}
		
		
		
		return cost+=cost;
	}
}
