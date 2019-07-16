package warehouse;

public class ChargingPod extends Entity {

	public static int chargingPodnumb=0;

	public ChargingPod()
	{
		super.uid="cb"+ChargingPod.chargingPodnumb;
		ChargingPod.chargingPodnumb+=1;
		
		
	}
	public void tick()
	{
		// search hash map for corosponding robot
		// if it exist and it its location is the same as urs then charge
		
	}
}
