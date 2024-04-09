/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.0
 * Original File Version: April 8, 2024
 * File Last Updated: April 8, 2024 
 * 
 * 1. File Description
 *  NEEDED
 */

package asuJavaFX360;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

class RegistrationPortal extends Portal {
	private Database database;
    private Stage registrationStage;
	
    public RegistrationPortal(Database database) { //MP standing for medical professional, P standing for patient) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
    }
   
    @Override
    public void displayInterface() {
    	
    	this.registrationStage = new Stage();
        System.out.println("Displaying Registration Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to SignUpPortal
        registrationStage.setTitle("Registration Page");

        // Create a grid pane and set its properties
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        // Create scene and set it on the stage
        Scene scene = new Scene(grid, this.xDimension, this.yDimension);
        registrationStage.setScene(scene);
        registrationStage.show();
    }
    
    
}