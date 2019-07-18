/*
 * 
 */
package view;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;
import view.SimulatorController;



// TODO: Auto-generated Javadoc
/**
 * The Class simpage2controller.
 *
 * @author Haseeb
 */

public class Simulationpagecontroller {
	
	/** The model. */
	private Simulator model;
	
	/** The actors. */
	private HashMap<String, Actor> actors;

	/**
	 * Instantiates a new simpage 2 controller.
	 *
	 * @param simulator the simulator
	 */
	public Simulationpagecontroller(Simulator simulator) {
		this.model = simulator;
		
		 actors = model.getWarehouse().getWarehouseGrid();


	}

	 /** The orderslistbox. */
 	@FXML
	    private ListView<String> orderslistbox;
	    
    	/** The actorslist. */
    	@FXML
	    private ListView<String> actorslist;

	/** The grid 1. */
	@FXML
	private GridPane grid1;
	
	/** The tentickbtn. */
	@FXML
	private Button tentickbtn;

	/** The return btn. */
	@FXML
	private Button returnBtn;
	
	/** The onetickbtn. */
	@FXML
	private Button onetickbtn;
	
    /** The tickslbl. */
    @FXML
    private Label tickslbl;

	/** The repeat till endbtn. */
	@FXML
	private Button repeatTillEndbtn;

	/**
	 * Tenticksbtnpressed.
	 */
	@FXML
	public void tenticksbtnpressed() {

		model.simulateNTicks(10);
		outputsforfaliurorsucsess();
		redrawgrid();
		orderslistbox.setItems(model.getordersList());
		actorslist.setItems(model.getactorslist());
		tickslbl.setText("ticks: "+model.getnumberofticks());

	}

	/**
	 * Oneticksbtnpressed.
	 */
	@FXML
	public void oneticksbtnpressed() {

		model.simulateOneTick();
		outputsforfaliurorsucsess();
		redrawgrid();
		orderslistbox.setItems(model.getordersList());
		actorslist.setItems(model.getactorslist());
		tickslbl.setText("ticks: "+model.getnumberofticks());

	}

/**
 * Returnbtnpressed.
 */
@FXML
public void returnbtnpressed()
{
	try {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("main.fxml"));
		model=new Simulator();
		fxmlLoader.setController(new SimulatorController(model));
		
		Scene scene = new Scene(fxmlLoader.load(), 1500, 700);
		Stage stage = new Stage();
		stage.setTitle("New Window");
		stage.setScene(scene);
		stage.show();
		Stage removestage = (Stage) onetickbtn.getScene().getWindow();
	    // do what you have to do
		removestage.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}	

}
	
	/**
	 * Repeat till endbtnpressed.
	 */

private void outputsforfaliurorsucsess()
{
	
	
	if (model.accidentoccured())
	{
		JOptionPane.showMessageDialog(null, 
                "robotcrashed", 
                "error", 
                JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	else if (model.areallorderscomplete())
	{
		JOptionPane.showMessageDialog(null, 
                "ordercomplete", 
                "succsess", 
                JOptionPane.INFORMATION_MESSAGE);
		
		
	}else if (model.isbatterydead())
	{
		JOptionPane.showMessageDialog(null, 
                "baterrydead", 
                "error", 
                JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
}
	@FXML
	public void repeatTillEndbtnpressed() {

		model.simulatetillend();
		outputsforfaliurorsucsess();
		redrawgrid();
		orderslistbox.setItems(model.getordersList());
		actorslist.setItems(model.getactorslist());
		tickslbl.setText("ticks: "+model.getnumberofticks());

	}

	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {

		
		resetgrid(2, 3);
		redrawgrid();
		orderslistbox.setItems(model.getordersList());
		actorslist.setItems(model.getactorslist());
		tickslbl.setText("ticks: "+model.getnumberofticks());
		

	}

	/**
	 * Resetgrid.
	 *
	 * @param oldcolums the oldcolums
	 * @param oldrows the oldrows
	 */
	private void resetgrid(int oldcolums, int oldrows) {

		for (int b = oldrows - 1; b >= 0; b--) {
			grid1.getRowConstraints().remove(b);

		}
		for (int i = oldcolums - 1; i >= 0; i--) {
			grid1.getColumnConstraints().remove(i);

		}

	}

	// print items to screen

	/**
	 * Redrawgrid.
	 */
	private void redrawgrid() {
		int newrows = model.getWarehouse().getrows();
		int newcolums = model.getWarehouse().getcolumns();
		for (int row = 0; row < newrows; row++) {
			for (int col = 0; col < newcolums; col++) {

				grid1.add(new ImageView("emptycell.png"), col, row);

			}

		}

	
		Iterator<HashMap.Entry<String, Actor>> actorsit = actors.entrySet().iterator();
		while (actorsit.hasNext()) {
			HashMap.Entry<String, Actor> entry = actorsit.next();
			if ( entry.getValue() instanceof packingStation)
			{
				packingStation packingStation = (packingStation) actors.get(entry.getKey());
				Location pklocation = packingStation.getLocation();
				grid1.add(new ImageView("pkstation.png"), pklocation.getCol(), pklocation.getRow());
				
			}
			
		}
		 actorsit = actors.entrySet().iterator();
			while (actorsit.hasNext()) {
				HashMap.Entry<String, Actor> entry = actorsit.next();
				if ( entry.getValue() instanceof ChargingPod)
				{
					ChargingPod chargingpod = (ChargingPod) actors.get(entry.getKey());
					Location cplocation = chargingpod.getLocation();
					grid1.add(new ImageView("chargingpd.png"), cplocation.getCol(), cplocation.getRow());
					
				}
				
			}
			 actorsit = actors.entrySet().iterator();
				while (actorsit.hasNext()) {
					HashMap.Entry<String, Actor> entry = actorsit.next();
					if ( entry.getValue() instanceof storageShelf)
					{
						storageShelf storageShelf = (storageShelf) actors.get(entry.getKey());
						Location shelflocation = storageShelf.getLocation();
						grid1.add(new ImageView("storageshelf1.png"), shelflocation.getCol(), shelflocation.getRow());
						
					}
					
				}
				 actorsit = actors.entrySet().iterator();
					while (actorsit.hasNext()) {
						HashMap.Entry<String, Actor> entry = actorsit.next();
						if ( entry.getValue() instanceof Robot)
						{
							Robot robot = (Robot) actors.get(entry.getKey());
							Location robotlocation = robot.getLocation();
							grid1.add(new ImageView("rbot.png"), robotlocation.getCol(), robotlocation.getRow());
							
						}
						
					}
		

	}

}
