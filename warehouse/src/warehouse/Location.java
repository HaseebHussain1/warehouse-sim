package warehouse;

public class Location {

	private int col;
	private int row;
	public Location(int col, int row)
	{
		this.col=col;
		this.row=row;
		
	}
	
	public void set(int row,int col)
	{
		this.col=col;
		this.row=row;
		
	}
	
	public int getcol()
	{
		return this.col;
	}
	
	public int getrow()
	{
		return this.row;
	}
	
	public boolean equals(Location loc)
	{
		if (loc.getcol()== this.col && loc.getrow()== this.row){
			
			return true;
		}
		
		return false;
	}
}
