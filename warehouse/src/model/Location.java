package model;
// TODO: Auto-generated Javadoc
/**
 * The Class Location.
 */
// used to represent locsation of entities
public class Location {

	/** The row. */
	private int row;// row and col of entity
	
	/** The col. */
	private int col;

	/**
	 * Instantiates a new location.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return row + "," + col;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	/**
	 * Sets the location.
	 *
	 * @param loc the new location
	 */
	public void setLocation(Location loc)// loc paramter row and col is stored as the objects row and col
	{
		row = loc.getRow();
		col = loc.getCol();

	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow()// returns row
	{
		return row;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol()// return col
	{
		return col;
	}
}
