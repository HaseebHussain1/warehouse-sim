package model;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Interface PathFindingStratergy.
 */
// this interface will be used to implement a path finding stratergy that will have a class which takes in two locations and returns the next location
public interface PathFindingStratergy {
	
	/**
	 * Next loc.
	 *
	 * @param startloc the startloc
	 * @param destinationuid the destinationuid
	 * @param Actors the actors
	 * @param maxcol the maxcol
	 * @param maxrow the maxrow
	 * @return the location
	 */
	public Location nextLoc(Location startloc,String destinationuid,HashMap<String, Actor> Actors,int maxcol,int maxrow);
}
