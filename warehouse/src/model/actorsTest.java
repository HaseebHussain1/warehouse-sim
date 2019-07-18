package model;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class actorsTest {

	Robot r;
	Robot r2;
	ChargingPod c;
	ChargingPod c2;
	packingStation pk1;
	packingStation pk2;
	storageShelf s0;
	HashMap<String, Actor> actors;
	HashMap<String, Order> orders;
	HashMap<String, Item> Items;
	HashMap<String, Item> Items1;
	Item i1;
	Item i2;
	Order o1;
	Order o2;

	@Before
	public void setUp() throws Exception {
		s0=new storageShelf(0, 1, 0);
		orders=new HashMap<String, Order>();
		Items=new HashMap<String,Item>();
		 Items1=new HashMap<String, Item>();
		i1=new Item("ss0");
		
		
		Robot.maxbattery=10;
		Robot.maxcol=10;
		Robot.maxrow=10;
		ChargingPod.chargeValue=1;
		r= new Robot(0,0,0);
		c=new ChargingPod(0, 0, 0);
		 r2= new Robot(1, 0, 0);
		 c2=new ChargingPod(1, 4, 0);
		 pk1= new packingStation(0, 2, 2);
		 pk2= new packingStation(2, 4, 4);
		 actors= new HashMap<String, Actor>();
		 actors.put(r.getUID(), r);
		 actors.put(c.getUID(), c);
		 actors.put(r2.getUID(), r2);
		 actors.put(c2.getUID(), c2);
		 actors.put(pk1.getUID(), pk1);
		 actors.put(pk2.getUID(), pk2);
		 actors.put(s0.getUID(), s0);
		 
		 
		 Items.put(i1.getshelfUid(), i1);
			o1=new Order(5, Items);
			o1.assignOrdertopkstation("ps0");
			orders.put(o1.getUid(), o1);
		
			
		 
	}

	@Test
	public void testingcollisionbetweenrobots() {// checking if the collision method works
		r.tick(actors, null);
		assertEquals(true, r.getcrashval());

	}

	@Test
	public void isorderaccpetedbyrobots() {// checking if the robotexcepts order

		r.acceptOrderFromPkStation(o1, actors);
		assertEquals(true, o1.isorderassignedtorb());

	}

	@Test
	public void isordercollectedbyrobots() {// checking if the collects order

		r.acceptOrderFromPkStation(o1, actors);
		r.tick(actors, orders);

		assertEquals(true, o1.checkifallitemspicked());

	}
	
	


	@Test
	public void isorderrecivedbypkandisrobotabletotakenextorder() {// checking the order is recived by pk and if the pk can take on order aswell as checking if robot can take on another order

		r.acceptOrderFromPkStation(o1, actors);
		r.tick(actors, orders);
		r.tick(actors, orders);
		r.tick(actors, orders);
		r.tick(actors, orders);

		assertEquals(true, o1.checkiforderrecivedbypk());
		
		i2=new Item("ss0");
		 Items1.put(i2.getshelfUid(), i2);
		o2=new Order(5, Items1);
		
		
		orders.put(o2.getUid(), o2);
		r.tick(actors, orders);
		pk2.tick(actors, orders);
		r.tick(actors, orders);
		assertEquals(true, o2.isAssignedtoPk());
		assertEquals(true, o2.isorderassignedtorb());
	}

}
