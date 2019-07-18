package model;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class Simulator.
 */
// this class simulate warehouse and generates orders
public class Simulator {

	/** The warehouse. */
	private Warehouse warehouse;
	
	/** The stats. */
	private WarehouseStats stats;
	
	/** The rand. */
	Random rand;
	
	/** The Orders. */
	private HashMap<String, Order> Orders;
	
	/** The actors. */
	private HashMap<String, Actor> actors;
	
	/**
	 * Instantiates a new simulator.
	 */
	public Simulator() {
		
		warehouse = new Warehouse(5, 5);
		rand = new Random();
		Orders = new HashMap<String, Order>();
		stats = new WarehouseStats();
		 actors = warehouse.getWarehouseGrid();

	}

	/**
	 * Gets the warehouse.
	 *
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;

	}

	/**
	 * Gen random orders.
	 */
	public void genRandomOrders() {

		int NumberOfStrgeShelfs = 0;
		int NumbOfOrders = rand.nextInt(100)+1;
		Iterator<HashMap.Entry<String, Actor>> entries = warehouse.getWarehouseGrid().entrySet().iterator();
		while (entries.hasNext()) {
			HashMap.Entry<String, Actor> entry = entries.next();
			if (entry.getValue() instanceof storageShelf) {
				NumberOfStrgeShelfs += 1;
			}

		}

		for (int i = 0; i < NumbOfOrders; i++) {
			int numberofShelfs = rand.nextInt(NumberOfStrgeShelfs) + 1;
			HashMap<String, Item> shelfUids = new HashMap<String, Item>();
			for (int b = 0; b < numberofShelfs; b++) {
				int rndmshelf = rand.nextInt(NumberOfStrgeShelfs);
				boolean set=false;
				while (!set) {
				if (shelfUids.containsKey("ss" + rndmshelf) == false&& actors.containsKey("ss" + rndmshelf)) {
					
					Item newitem=new Item("ss" + rndmshelf);
					shelfUids.put(newitem.getshelfUid(), newitem);
					set=true;
					

				}else {
					
					rndmshelf = rand.nextInt(NumberOfStrgeShelfs);
				}
				}
				

			}

			Order order = new Order(rand.nextInt(15)+1, shelfUids);
			Orders.put(order.getUid(), order);

		}

	}

	
	/**
	 * Simulate one tick.
	 */
	public void simulateOneTick()// simulates 1 tick for each actor 1 after the other
	{
		stats.checkifsimisover(warehouse.getWarehouseGrid(), Orders);
		if (stats.isSimulationover() == false) {
			
			Iterator<HashMap.Entry<String, Actor>> actorsit = actors.entrySet().iterator();
			while (actorsit.hasNext()) {
				HashMap.Entry<String, Actor> entry = actorsit.next();
				if ( entry.getValue() instanceof packingStation)
				{
					packingStation packingstation = (packingStation) actors.get(entry.getKey());
					packingstation.tick(actors, Orders);
					
				}
				
			}
			
			 actorsit = actors.entrySet().iterator();
			while (actorsit.hasNext()) {
				HashMap.Entry<String, Actor> entry = actorsit.next();
				if ( entry.getValue() instanceof ChargingPod)
				{
					ChargingPod cpod = (ChargingPod) actors.get(entry.getKey());
					cpod.tick(actors, Orders);
					
				}
				
			}
			
			 actorsit = actors.entrySet().iterator();
			while (actorsit.hasNext()) {
				HashMap.Entry<String, Actor> entry = actorsit.next();
				if ( entry.getValue() instanceof Robot)
				{
					Robot robot= (Robot) actors.get(entry.getKey());
					robot.tick(actors, Orders);
					
				}
				
			}
			
			

			stats.incrementticks();
		}
		
	}

	public boolean accidentoccured()
	{
		return stats.getcrash();
		
	}
	public boolean areallorderscomplete()
	{
		return stats.getordercomplete();
		
	}
	public boolean isbatterydead()
	{
		return stats.getbatterydeadvalue();
		
	}
			
			
	
	/**
	 * Simulate N ticks.
	 *
	 * @param n the n
	 */
	public void simulateNTicks(int n) {
		for (int i = 0; i < n; i++) {
			simulateOneTick();

		}
	}

	/**
	 * Simulatetillend.
	 */
	public void simulatetillend() {
		while (stats.getordercomplete() == false) {
			simulateOneTick();
		}
		
		

	}

/**
 * Resetorders.
 */
public void resetorders()
{
Orders.clear();	
}

	/**
	 * Gets the orders list.
	 *
	 * @return the orders list
	 */
	public ObservableList<String> getordersList()
	{
		
		ObservableList<String> orderslist = FXCollections.observableArrayList();
		 Iterator<Entry<String, Order>> actorsit = Orders.entrySet().iterator();
			while (actorsit.hasNext()) {
				HashMap.Entry<String, Order> entry = actorsit.next();
				orderslist.add(entry.getValue().toString());
				
			}
			return orderslist;
		
	}



/**
 * Gets the actorslist.
 *
 * @return the actorslist
 */
public ObservableList<String> getactorslist()
{
	
	ObservableList<String> actorslist = FXCollections.observableArrayList();
	 Iterator<Entry<String, Actor>> actorsit = actors.entrySet().iterator();
		while (actorsit.hasNext()) {
			HashMap.Entry<String, Actor> entry = actorsit.next();
			actorslist.add(entry.getValue().toString());
			
		}
		return actorslist;
	
}

/**
 * Gets the numberofticks.
 *
 * @return the numberofticks
 */
public int getnumberofticks()
{
return stats.getTotalnumofticks();	
}
}
