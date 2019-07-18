package model;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class XpathFindingStratergy.
 */
public class XpathFindingStratergy implements PathFindingStratergy {

	
	/* (non-Javadoc)
	 * @see PathFindingStratergy#nextLoc(Location, java.lang.String, java.util.HashMap, int, int)
	 */
	public Location nextLoc(Location sLoc,String targetUid,HashMap<String, Actor> Actors,int maxcol,int maxrow){
		int column = sLoc.getCol();
		int row = sLoc.getRow();
		Location tLoc = Actors.get(targetUid).getLocation();
		if (sLoc.getCol() != tLoc.getCol()) {
			if (sLoc.getCol() > tLoc.getCol()) {
				column = sLoc.getCol() - 1;

			} else if (sLoc.getCol() < tLoc.getCol()) {
				column = sLoc.getCol() + 1;

			}
		}
		else if (sLoc.getRow() != tLoc.getRow()) {
			if (sLoc.getRow() > tLoc.getRow()) {
				row = sLoc.getRow() - 1;

			} else if (sLoc.getRow() < tLoc.getRow()) {
				row = sLoc.getRow() + 1;

			}
		}
		return new Location(row, column);

	}
}
