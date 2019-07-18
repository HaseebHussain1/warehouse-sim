package model;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class simplepathFindingStratergy.
 */
public class simplepathFindingStratergy implements PathFindingStratergy {
	
	/** The map. */
	HashMap<Location, Integer>map=new HashMap<Location, Integer>();



	/* (non-Javadoc)
	 * @see PathFindingStratergy#nextLoc(Location, java.lang.String, java.util.HashMap, int, int)
	 */
	public Location nextLoc(Location startloc,String destinationuid,HashMap<String, Actor> Actors,int maxcol,int maxrow)
	{
		Location destinationl=Actors.get(destinationuid).getLocation();
		
		LinkedList<Location>listlocs= new LinkedList<Location>();
		map=new HashMap<Location, Integer>();
		listlocs.add(destinationl);
		map.put(destinationl, 0);
		
		while (listlocs.isEmpty()==false)
		{
			Location loc=listlocs.pop();
			int col=loc.getCol();
			int row=loc.getRow();
			Integer currentcellvalue=map.get(loc);
			Integer tmplocationvalue;
			
			Location tmpl=new Location(row, col-1);
		if (tmpl.getCol()>=0&& isrbatlocation(tmpl, Actors)==false)
			{
			tmplocationvalue=map.get(tmpl);
			if(map.get(tmpl)==null) {
				map.put(tmpl, Integer.MAX_VALUE);
				tmplocationvalue=map.get(tmpl);
			
			} if (currentcellvalue+1<tmplocationvalue) {
				
				map.put(tmpl,currentcellvalue+1 );
				listlocs.add(tmpl);
				
			}
			
			}
		 tmpl=new Location(row, col+1);
		if (tmpl.getCol()<=maxcol&&isrbatlocation(tmpl, Actors)==false)
		{
			tmplocationvalue=map.get(tmpl);
			if(map.get(tmpl)==null) {
				map.put(tmpl, Integer.MAX_VALUE);
				tmplocationvalue=map.get(tmpl);
			
			} if (currentcellvalue+1<tmplocationvalue) {
				
				map.put(tmpl,currentcellvalue+1 );
				listlocs.add(tmpl);
				
			}
		}
		 tmpl=new Location(row-1, col);
		if (tmpl.getRow()>=0&& isrbatlocation(tmpl, Actors)==false)
			{
			tmplocationvalue=map.get(tmpl);
			if(map.get(tmpl)==null) {
				map.put(tmpl, Integer.MAX_VALUE);
				tmplocationvalue=map.get(tmpl);
			
			} if (currentcellvalue+1<tmplocationvalue) {
				
				map.put(tmpl,currentcellvalue+1 );
				listlocs.add(tmpl);
				
			}
			
			}
		 tmpl=new Location(row+1, col);
		if (tmpl.getRow()<=maxrow &&isrbatlocation(tmpl, Actors)==false)
		{
			tmplocationvalue=map.get(tmpl);
			if(map.get(tmpl)==null) {
				map.put(tmpl, Integer.MAX_VALUE);
				tmplocationvalue=map.get(tmpl);
			
			} if (currentcellvalue+1<tmplocationvalue) {
				
				map.put(tmpl,currentcellvalue+1 );
				listlocs.add(tmpl);
				
			}
		
		}
			
			
			
}
		
		
		if(isrbatlocation(destinationl, Actors))
		{
			map.put(destinationl, Integer.MAX_VALUE);
			
		}
		
		Location movedown=new Location(startloc.getRow()+1, startloc.getCol());
		Location moveup=new Location(startloc.getRow()-1, startloc.getCol());
		Location moveleft=new Location(startloc.getRow(), startloc.getCol()-1);
		Location moveright=new Location(startloc.getRow(), startloc.getCol()+1);
		
		
		if (map.get(moveup)==null)
		{
			map.put(moveup, Integer.MAX_VALUE);
			
		}
		if (map.get(movedown)==null)
		{
			map.put(movedown, Integer.MAX_VALUE);
			
		}
		if (map.get(moveleft)==null)
		{
			map.put(moveleft, Integer.MAX_VALUE);
			
		}
		if (map.get(moveright)==null)
		{
			map.put(moveright, Integer.MAX_VALUE);
			
		}
		
		
		if (map.get(moveright)==Integer.MAX_VALUE&&map.get(moveleft)==Integer.MAX_VALUE&&map.get(moveup)==Integer.MAX_VALUE&&map.get(movedown)==Integer.MAX_VALUE)
		{
		
		return startloc;
		
		
		
		
		}else {
			
			if(map.get(movedown)<=map.get(moveup)&&map.get(movedown)<=map.get(moveleft)&&map.get(movedown)<=map.get(moveright)){
				
				return movedown;
			}
		
			
			if(map.get(moveup)<=map.get(movedown)&&map.get(moveup)<=map.get(moveleft)&&map.get(moveup)<=map.get(moveright)){
				return moveup;
				
			}
			
			if(map.get(moveleft)<=map.get(moveup)&&map.get(moveleft)<=map.get(movedown)&&map.get(moveleft)<=map.get(moveright)){
				
				return moveleft;
			}
			
			if(map.get(moveright)<=map.get(moveup)&&map.get(moveright)<=map.get(moveleft)&&map.get(moveright)<=map.get(movedown)){
				return moveright;
				
			}
			
		}

	
		return startloc;
	}

	/**
	 * Checks if is rbatlocation.
	 *
	 * @param loc the loc
	 * @param Actors the actors
	 * @return true, if is rbatlocation
	 */
	private boolean isrbatlocation(Location loc, HashMap<String, Actor> Actors) {

		Iterator<HashMap.Entry<String, Actor>> entries = Actors.entrySet().iterator();
		while (entries.hasNext()) {
			HashMap.Entry<String, Actor> entry = entries.next();
			if (entry.getValue() instanceof Robot&& entry.getValue().getLocation().equals(loc)) {
				return true;
			}

		}
		
		return false;
		
		
	}

}
