/*
 * 
 */
package view;
import model.*;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Simulator;



// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Application { 
	
	

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage)  {
		try {
			final Simulator simulator = new Simulator();
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("main.fxml"));
			
			loader.setController(new SimulatorController(simulator));
			final Parent root = loader.load();
		
			
			
			
			
			final Scene scene = new Scene(root, 1500, 700);
			
			primaryStage.setTitle("Simulator Warehouse");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
