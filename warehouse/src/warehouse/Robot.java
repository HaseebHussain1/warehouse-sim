package warehouse;

public class Robot extends Entity {
	public static int robotnum=0;

	public Robot()
	{
		super.uid="rb"+Robot.robotnum;
		Robot.robotnum+=1;
		
		
	}
	public void tick()
	{
		
		
	}
}
