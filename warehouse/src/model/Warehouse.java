package model;
import java.util.HashMap;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class Warehouse.
 */
// this warehouse adds actor to the hashmap
public class Warehouse {
	
	/** The warehouse actors. */
	private HashMap<String, Actor> warehouseActors;
	
	/** The row. */
	private int row;
	
	/** The columns. */
	private int columns;
	
	/** The numbofrbandchargingstations. */
	private int numbofrbandchargingstations;
	
	/** The numbofpackingstations. */
	private int numbofpackingstations;
	
	/** The numbofshelfs. */
	private int numbofshelfs;

	/**
	 * Instantiates a new warehouse.
	 *
	 * @param row the row
	 * @param column the column
	 */
	public Warehouse(int row, int column) {
		this.row = row;
		this.columns = column;
		Robot.maxrow=getrows()-1;
		Robot.maxcol=getcolumns()-1;
		warehouseActors = new HashMap<String, Actor>();
		numbofrbandchargingstations = 0;
		numbofpackingstations = 0;
		numbofshelfs = 0;

	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public int getrows() {
		return row;

	}
	
	/**
	 * Minrequirmentsofgrid.
	 *
	 * @return true, if successful
	 */
	public boolean minrequirmentsofgrid()
	{
		if (numbofrbandchargingstations>0&&numbofpackingstations>0&&numbofshelfs>0)
		{
			return true;
			
		}
		return false;
		
	}
	/**
	 * Resetwarehouse.
	 */
	public void resetwarehouse()
	{
		warehouseActors.clear();
		numbofrbandchargingstations = 0;
		numbofpackingstations = 0;
		numbofshelfs = 0;
		
	}

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public int getcolumns() {
		return columns;

	}

	 /**
 	 * Setcolumnandrows.
 	 *
 	 * @param row the row
 	 * @param column the column
 	 */
 	public void setcolumnandrows(int row,int column)
	 {
	 this.row=row;
	 this.columns=column;
	 Robot.maxrow=getrows()-1;
		Robot.maxcol=getcolumns()-1;
	
	
	
	 }
	
	/**
	 * Adds the actor.
	 *
	 * @param actor the actor
	 */
	private void addActor(Actor actor) {
		warehouseActors.put(actor.getUID(), actor);

	}

	/**
	 * Adds the rb and chrging pad.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void addRbAndChrgingPad(int row, int col) {

		Robot robot = new Robot(numbofrbandchargingstations, row, col);
		addActor(robot);
		ChargingPod cpod = new ChargingPod(numbofrbandchargingstations, row, col);
		addActor(cpod);
		numbofrbandchargingstations = numbofrbandchargingstations + 1;

	}

	/**
	 * Addpacking station.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void addpackingStation(int row, int col) {

		packingStation packstation = new packingStation(numbofpackingstations, row, col);
		addActor(packstation);

		numbofpackingstations = numbofpackingstations + 1;

	}

	/**
	 * Adds the storageshelf.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void addStorageshelf(int row, int col) {

		storageShelf sshelf = new storageShelf(numbofshelfs, row, col);
		addActor(sshelf);

		numbofshelfs = numbofshelfs + 1;

	}

	/**
	 * Gets the warehouse grid.
	 *
	 * @return the warehouse grid
	 */
	public HashMap<String, Actor> getWarehouseGrid()// returns warehouse actors
	{
		return warehouseActors;

	}

	/**
	 * Does entity exist.
	 *
	 * @param colindex the colindex
	 * @param rowIndex the row index
	 * @return true, if successful
	 */
	public boolean doesEntityExist(int colindex, int rowIndex) {

		boolean entityAlreadyExists = false;
		Location l = new Location(rowIndex, colindex);
		Iterator<HashMap.Entry<String, Actor>> actors1 = warehouseActors.entrySet().iterator();
		while (actors1.hasNext()) {
			HashMap.Entry<String, Actor> entry = actors1.next();

			
			if (entry.getValue().getLocation().equals(l)) {
				entityAlreadyExists = true;

			}

		}
		return entityAlreadyExists;

	}

	/**
	 * Removes the entity.
	 *
	 * @param colindex the colindex
	 * @param rowIndex the row index
	 */
	public void removeEntity(int colindex, int rowIndex) {
		Location l = new Location(rowIndex, colindex);
		
		boolean removerobot=false;
		String rbUid="",chargUid="";
		Iterator<HashMap.Entry<String, Actor>> actors = warehouseActors.entrySet().iterator();
		while (actors.hasNext()) {
			HashMap.Entry<String, Actor> entry = actors.next();

			
			if (entry.getValue().getLocation().equals(l)) {
				
				if (entry.getValue() instanceof Robot)
				{
					removerobot=true;
					rbUid=entry.getKey();
					
					
				}else if (entry.getValue() instanceof ChargingPod)
				{
					removerobot=true;
					chargUid=entry.getKey();
					
				}else if (entry.getValue() instanceof packingStation)
				{
					numbofpackingstations-=1;
					actors.remove();
					
				}else if (entry.getValue() instanceof storageShelf)
				{
					numbofshelfs-=1;
					actors.remove();
					
				}
			
			

					

		}
			
			
			

		}
		if (removerobot)
		{
			warehouseActors.remove(rbUid);
			warehouseActors.remove(chargUid);
			numbofrbandchargingstations-=1;
			
		}

	}

}
