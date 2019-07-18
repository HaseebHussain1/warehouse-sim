package model;
import java.util.HashMap;
// This class accepts orders and call the rb and assignes orders and waites to reicve them and dispatches the orders
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class packingStation.
 */
public class packingStation extends Actor {
	
	/** The occupied. */
	private Boolean occupied;
	
	/** The order. */
	private Order order;

	/**
	 * Instantiates a new packing station.
	 *
	 * @param lUid the last uid number
	 * @param row the row
	 * @param col the col
	 */
	public packingStation(int lUid, int row, int col) {
		super("ps", lUid, row, col);

		occupied = false;
	}

	/**
	 * Checks if packingStation is occupied.
	 *
	 * @return the boolean
	 */
	public Boolean isOccupied() {// returns if the pk is busy

		return occupied;
	}
	

	/* (non-Javadoc)
	 * @see Actor#toString()
	 */
	@Override
	public String toString() {
	String	packingstationproperty=super.toString();
	if(occupied)
	{
		packingstationproperty+=" [busy=" + occupied + ", order=" + order.getUid()+" packingticks"+order.getPackingTicks()+ "total ticks"+ order.GetTotalTicks() + "]";
	}else {
		packingstationproperty+=" ["+"idle" +"]";
			
		
	}
		return packingstationproperty;
	}

	/**
	 * Tick method which takes up orders if it is free and assignes orders to robots and does packing here
	 *
	 * @param warehouseActors the warehouse actors
	 * @param Orders the orders
	 */
	public void tick(HashMap<String, Actor> warehouseActors, HashMap<String, Order> Orders)// all the main behaviour
																							// happens here
	{

		if (occupied == false) {

			Iterator<HashMap.Entry<String, Order>> entries = Orders.entrySet().iterator();
			while (entries.hasNext() && occupied != true) {
				HashMap.Entry<String, Order> entry = entries.next();
				if (entry.getValue().isAssignedtoPk() == false) {
					entry.getValue().assignOrdertopkstation(getUID());
					order = entry.getValue();
					occupied = true;

				}

			}

		}
		if (occupied == true && order.isorderDispatched() == false) {

			if (order.isorderassignedtorb() == false) {
				int i = 0;
				while (warehouseActors.containsKey("r" + i) && order.isorderassignedtorb() == false) {
					Robot r = (Robot) warehouseActors.get("r" + i);

					if (r.acceptOrderFromPkStation(order, warehouseActors)) {
						order.assigneordertorb();
						

					}
					i++;

				}

			}

			if (order.checkiforderrecivedbypk()) {
				if (order.getPackingTicks() == 0) {
					order.Dispatched();
					occupied = false;
					

				} else {
					order.decreasePKticks();

				}

			}
			order.incrementTotalTicks();
		}

	}

}
