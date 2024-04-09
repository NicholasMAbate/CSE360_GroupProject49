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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;

class RegistrationPortal extends Portal {
	private Database database;
	private String username;
	private String password;
    private Stage registrationStage;
	
    public RegistrationPortal(Database database, String uName, String pword) { //uName: username; pword: Password this is so we know what account we are updating
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
        this.username = uName;
        this.password = pword;
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

        // Labels for textboxes
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label dobLabel = new Label("Date Of Birth (mm/dd/yyyy):");
        Label phoneNumberLabel = new Label("Phone Number (XXX-XXX-XXXX):");
        Label emailLabel = new Label("Email Address:");
        Label insuranceIdLabel = new Label("Insurance ID#:");
        Label pharmacyLabel = new Label("Preferred Pharmacy:");

        // Textfields for user input
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField dobField = new TextField();
        TextField phoneNumberField = new TextField();
        TextField emailField = new TextField();
        TextField insuranceIdField = new TextField();
        TextField pharmacyField = new TextField();

        // Add labels and text fields to the grid
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(dobLabel, 0, 2);
        grid.add(dobField, 1, 2);
        grid.add(phoneNumberLabel, 0, 3);
        grid.add(phoneNumberField, 1, 3);
        grid.add(emailLabel, 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(insuranceIdLabel, 0, 5);
        grid.add(insuranceIdField, 1, 5);
        grid.add(pharmacyLabel, 0, 6);
        grid.add(pharmacyField, 1, 6);
        
     // Button for confirming details
        Button confirmButton = new Button("Confirm Details");
        HBox confirmButtonBox = new HBox(10);
        confirmButtonBox.setAlignment(Pos.CENTER);
        confirmButtonBox.getChildren().add(confirmButton);
        grid.add(confirmButtonBox, 0, 7, 2, 1);
        confirmButton.setOnAction(event -> confirmed(firstNameField.getText(), lastNameField.getText(), 
        		dobField.getText(), phoneNumberField.getText(), emailField.getText(), 
        		insuranceIdField.getText(), pharmacyField.getText() ));

        // Create scene and set it on the stage
        Scene scene = new Scene(grid, this.xDimension, this.yDimension);
        registrationStage.setScene(scene);
        registrationStage.show();
    }
    
    private void confirmed(String firstName, String lastName, String DOB, String pNum, String emailAddress, String Insurance, String Pharmacy) {
    	// Find the patient in the database based on the provided username and password
        Patient patientToUpdate = database.patientToUpdate(username, password);
        
        
     // Update patient's details if found
        if (patientToUpdate != null) {
        	//Create a unique patient ID 
        	Random rand = new Random();
        	int patientID = rand.nextInt(99999) + 1;
        	String formattedPatientID = String.format("%05d", patientID);
        	
            // Update patient's details
            patientToUpdate.setFirstName(firstName);
            patientToUpdate.setLastName(lastName);
            patientToUpdate.setDOB(DOB);
            patientToUpdate.setPhoneNumber(pNum);
            patientToUpdate.setEmail(emailAddress);
            patientToUpdate.setInsuranceID(Insurance);
            patientToUpdate.setPharmacy(Pharmacy);
            patientToUpdate.setPatientID(formattedPatientID);
            
            
            // Set the account as fully setup
            patientToUpdate.setIsSetup();
            
            
            
            LoginPortal loginPortal = new LoginPortal(database);
            loginPortal.displayInterface();
            registrationStage.close();
            
        } else {
            // Inform the user that patient details could not be updated
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Unable to find patient account. Please try again.");
            alert.showAndWait();
        }
    }
    
}