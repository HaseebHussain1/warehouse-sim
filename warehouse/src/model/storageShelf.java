package model;

import java.util.HashMap;


// TODO: Auto-generated Javadoc
/**
 * The Class storageShelf.
 */
//this is a storage shelf
public class storageShelf extends Actor {

	
	/**
	 * Instantiates a new storage shelf.
	 *
	 * @param lUid the last unique id number
	 * @param row the row
	 * @param col the col
	 */
	public storageShelf(int lUid,int row,int col) {
		super("ss",lUid, row, col);
		

	}
	
	/* (non-Javadoc)
	 * @see model.Actor#tick(java.util.HashMap, java.util.HashMap)
	 */
	public void tick(HashMap<String,Actor>warehouseActors,HashMap<String,Order>Orders)// takes the warehouse actors and orders.
	{
		
		
	}

	
}
