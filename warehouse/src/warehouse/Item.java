package warehouse;

public class Item {

	private String shelf;
	
	
	private String robotid;
	
	private String status;
	
	
	public String getshelf()
	{
		return shelf;
		
	}
	
	public void setshelf(String shelf)
	{
		 this.shelf=shelf;
		
	}
	
	public String getrobotid()
	{
		return robotid;
		
	}
	
	public void setrobotid(String robotid)
	{
		 this.robotid=robotid;
		
	}
	
	public String getstatus()
	{
		return status;
		
	}
	
	public void itemcollected()
	{
		
		status="collected";
	}
	
	public void itempacked()
	{
		
		status="packed";
	}
	
	
}
