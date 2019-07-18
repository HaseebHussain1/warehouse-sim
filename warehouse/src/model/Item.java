package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public class Item {
	
	/** The shelf uid. */
	private String shelfUid;

	// private boolean itemassignedtopackingstation;

	/** The shelfcollected value which contains wheather the shelf has been collected. */
	private boolean shelfcollected;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		String	itemprop="[ssUid=" + shelfUid+"]";
		
		return itemprop;
	}





	/** The itemdelivered contains the true or false value of wheather item has been delivered. */
	private boolean itemdelivered;

	/**
	 * Instantiates a new item.
	 *
	 * @param shelfUid the shelf uid
	 */
	public Item(String shelfUid) {
		this.shelfUid = shelfUid;

		// itemassignedtopackingstation=false;

		shelfcollected = false;
		itemdelivered = false;

	}

	/**
	 * Setitemcollected - the item has been collected by {@link Robot}.
	 */
	public void setitemcollected() {

		shelfcollected = true;

	}

	/**
	 * Hasshelfbeencollected. returns boolean value of weather the shelf has been collected
	 *
	 * @return true, if successful
	 */
	public boolean hasshelfbeencollected() {
		return shelfcollected;
	}

	/**
	 * Hasitembeenrecivedbypk.
	 *
	 * @return true, if successful
	 */
	public boolean hasitembeenrecivedbypk() {
		return itemdelivered;
	}

	/**
	 * Gets the shelf uid.
	 *
	 * @return the shelf uid
	 */
	public String getshelfUid() {
		return shelfUid;
	}





	/**
	 * Setitemrecivedbypk. the item has been recived by pk
	 */
	public void setitemrecivedbypk() {

		itemdelivered = true;
	}
}
