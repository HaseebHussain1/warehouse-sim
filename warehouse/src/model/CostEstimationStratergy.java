package model;
import java.util.HashMap;
// TODO: Auto-generated Javadoc

/**
 * The Interface CostEstimationStratergy.
 */
//is the interface that will be used to create a specific const estimation stratergy
public interface CostEstimationStratergy {

	/**
	 * Estimate cost.
	 *
	 * @param sLoc the start location
	 * @param packingstationUid is the packingstation uid which is used to go between the shelfs and packing station
	 * @param Actors the actors which are used to get location by using the shelfs uid
	 * @param shelfs the shelfs
	 * @return the int which is the cost from start to {@link packingStation} and shelf repeatedly untill order is complete
	 */
	public int estimateCost(Location sLoc,String packingstationUid, HashMap<String,Actor> Actors,HashMap<String, Boolean>shelfs);	// takes in the location and the shelfUids(from an order) and Hashmap of actors and return the cost of taking order
	
	/**
	 * Calculatenumbofsteps.
	 *
	 * @param start the startlocation
	 * @param destinationLocation the destination location
	 * @return the int is the cost from start to destination
	 */
	public int calculatenumbofsteps(Location start,Location destinationLocation);
}
