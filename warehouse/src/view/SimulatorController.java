/*
 * 
 */
package view;

import model.*;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class SimulatorController.
 */
public class SimulatorController {

	/** The model. */
	private Simulator model;

	/**
	 * Instantiates a new simulator controller.
	 *
	 * @param simulator
	 *            the simulator
	 */
	public SimulatorController(Simulator simulator) {
		this.model = simulator;

	}

	/** The i. */
	ImageView i;

	/** The group. */
	private ToggleGroup group;

	/** The batterycapacitycombobox. */
	@FXML
	private ChoiceBox<Integer> batterycapacitycombobox;

	/** The chargespeedcombobox. */
	@FXML
	private ChoiceBox<Integer> chargespeedcombobox;

	/** The robot rd. */
	@FXML
	private RadioButton robotRd;

	/** The row index list. */
	ObservableList<Integer> rowIndexList = FXCollections.observableArrayList();

	/** The col index list. */
	ObservableList<Integer> colIndexList = FXCollections.observableArrayList();

	/** The batterypercentage list. */
	ObservableList<Integer> batterypercentageList = FXCollections.observableArrayList();

	/** The chargingval list. */
	ObservableList<Integer> chargingvalList = FXCollections.observableArrayList();

	/** The maxrow index list. */
	ObservableList<Integer> maxrowIndexList = FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8, 9, 10);

	/** The maxcolrowindex list. */
	ObservableList<Integer> maxcolrowindexList = FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8, 9, 10);

	/** The gen btn. */
	@FXML
	private Button genBtn;

	/** The maxrowschoicebx. */
	@FXML
	private ChoiceBox<Integer> maxrowschoicebx;

	/** The maxcolschoicebx. */
	@FXML
	private ChoiceBox<Integer> maxcolschoicebx;

	/**
	 * Generate btn clicked.
	 *
	 * @param event
	 *            the event
	 */
	@FXML
	void generateBtnClicked(ActionEvent event) {
		model.resetorders();
		orderslisst = model.getordersList();
		ordersListview.setItems(orderslisst);
		model.getWarehouse().resetwarehouse();
		model.getWarehouse().setcolumnandrows((int) maxrowschoicebx.getValue(), (int) maxcolschoicebx.getValue());
		rowIndexList.clear();
		for (int i = 0; i < model.getWarehouse().getrows(); i++) {
			rowIndexList.add(i);

		}

		colIndexList.clear();
		for (int i = 0; i < model.getWarehouse().getcolumns(); i++) {
			colIndexList.add(i);

		}

		resetgrid();

		Robot.maxbattery = batterycapacitycombobox.getValue();

		ChargingPod.chargeValue = chargespeedcombobox.getValue();

	}

	/** The grid 1. */
	private GridPane grid1;

	/** The gridcontainer. */
	@FXML
	private Pane gridcontainer;

	/** The removeradiobtn. */
	@FXML
	private RadioButton removeradiobtn;

	/** The row index choicebox. */
	@FXML
	private ChoiceBox<Integer> rowIndexChoicebox;

	/** The packing station. */
	@FXML
	private RadioButton packingStation;

	/** The shelf rd. */
	@FXML
	private RadioButton shelfRd;

	/** The column index choicebox. */
	@FXML
	private ChoiceBox<Integer> columnIndexChoicebox;

	/** The txtbx. */
	@FXML
	private TextField txtbx;

	/** The Add entity button. */
	@FXML
	private Button AddEntityButton;

	/** The orders listview. */
	@FXML
	private ListView<String> ordersListview;

	/** The orderslisst. */
	ObservableList<String> orderslisst = FXCollections.observableArrayList();

	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {

		group = new ToggleGroup();
		robotRd.setToggleGroup(group);
		shelfRd.setToggleGroup(group);
		removeradiobtn.setToggleGroup(group);
		packingStation.setToggleGroup(group);
		rowIndexChoicebox.setItems(rowIndexList);
		columnIndexChoicebox.setItems(colIndexList);
		ordersListview.setItems(orderslisst);

		rowIndexList.clear();
		for (int i = 0; i < model.getWarehouse().getrows(); i++) {
			rowIndexList.add(i);

		}

		colIndexList.clear();
		for (int i = 0; i < model.getWarehouse().getcolumns(); i++) {
			colIndexList.add(i);

		}
		batterypercentageList.clear();
		for (int i = 50; i <= 100; i++) {

			batterypercentageList.add(i);

		}
		chargingvalList.clear();
		for (int i = 1; i <= 5; i++) {
			chargingvalList.add(i);

		}
		batterycapacitycombobox.setItems(batterypercentageList);
		chargespeedcombobox.setItems(chargingvalList);
		// i = new ImageView("emptycell.png");

		maxcolschoicebx.setItems(maxcolrowindexList);
		maxrowschoicebx.setItems(maxrowIndexList);
		maxrowschoicebx.setValue(new Integer(model.getWarehouse().getrows()));
		maxcolschoicebx.setValue(new Integer(model.getWarehouse().getcolumns()));

		grid1 = new GridPane();

		gridcontainer.getChildren().add(grid1);
		resetgrid();

		batterycapacitycombobox.setValue(new Integer(85));
		Robot.maxbattery = batterycapacitycombobox.getValue();
		chargespeedcombobox.setValue(new Integer(1));
		ChargingPod.chargeValue = chargespeedcombobox.getValue();
	}

	/**
	 * Resetgrid.
	 */
	private void resetgrid() {

		gridcontainer.getChildren().remove(0);
		grid1 = new GridPane();

		for (int row = 0; row < model.getWarehouse().getrows(); row++) {
			for (int col = 0; col < model.getWarehouse().getcolumns(); col++) {

				grid1.add(new ImageView("emptycell.png"), col, row);

			}

		}
		gridcontainer.getChildren().add(grid1);

	}

	/**
	 * Clickedbtn.
	 *
	 * @param event
	 *            the event
	 */
	@FXML
	void clickedbtn(ActionEvent event) {
		model.resetorders();
		orderslisst = model.getordersList();
		ordersListview.setItems(orderslisst);
		if (rowIndexChoicebox.getValue() != null && columnIndexChoicebox.getValue() != null) {
			int colindex = (int) columnIndexChoicebox.getValue();
			int rowIndex = (int) rowIndexChoicebox.getValue();
			boolean entityAlreadyExists = model.getWarehouse().doesEntityExist(colindex, rowIndex);

			if (entityAlreadyExists == false) {

				if (robotRd.isSelected()) {

					model.getWarehouse().addRbAndChrgingPad(rowIndex, colindex);
					grid1.add(new ImageView("chargingpd.png"), colindex, rowIndex);
					grid1.add(new ImageView("rbot.png"), colindex, rowIndex);

				} else if (shelfRd.isSelected()) {

					model.getWarehouse().addStorageshelf(rowIndex, colindex);
					grid1.add(new ImageView("storageshelf1.png"), colindex, rowIndex);
				} else if (packingStation.isSelected()) {

					model.getWarehouse().addpackingStation(rowIndex, colindex);
					grid1.add(new ImageView("pkstation.png"), colindex, rowIndex);

				}

			} else if (removeradiobtn.isSelected()) {

				model.getWarehouse().removeEntity(colindex, rowIndex);

				grid1.add(new ImageView("emptycell.png"), colindex, rowIndex);

			}

		}

	}

	/**
	 * Generateordersbtnpressed.
	 */
	public void generateordersbtnpressed() {
		if (!model.getWarehouse().minrequirmentsofgrid()) {

			JOptionPane.showMessageDialog(null, "please add 1 or more of each actor", "error",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			model.resetorders();
			model.genRandomOrders();
			orderslisst = model.getordersList();
			ordersListview.setItems(orderslisst);
		}

	}

	/**
	 * Startbtn pressed.
	 */
	@FXML
	public void startbtnPressed() {
		if (model.getordersList().isEmpty() == false) {

			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("simpage2.fxml"));

				fxmlLoader.setController(new Simulationpagecontroller(model));

				Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
				Stage stage = new Stage();
				stage.setTitle("New Window");
				stage.setScene(scene);
				stage.show();
				Stage removestage = (Stage) removeradiobtn.getScene().getWindow();
				// do what you have to do
				removestage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			JOptionPane.showMessageDialog(null, "please click generate orders", "error",
					JOptionPane.INFORMATION_MESSAGE);

		}
	}

}
