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

class SignUpPortal extends Portal {
	private Database database;
    private Stage signUpStage;
	
    public SignUpPortal(Database database) { //MP standing for medical professional, P standing for patient) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
    }
   
    @Override
    public void displayInterface() {
    	
    	this.signUpStage = new Stage();
        System.out.println("Displaying Sign Up Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to SignUpPortal
        signUpStage.setTitle("Account Creation");

        // Create a grid pane and set its properties
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Username label and text field
        Label userNameLabel = new Label("Username:");
        grid.add(userNameLabel, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        // Password label and password field
        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        // Confirm Password label and password field
        Label confirmPwLabel = new Label("Confirm Password:");
        grid.add(confirmPwLabel, 0, 3);
        PasswordField confirmPwBox = new PasswordField();
        grid.add(confirmPwBox, 1, 3);

        // Create Account button
        Button btn = new Button("Create Account");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        // Set action on button click
        btn.setOnAction(e -> signUp(userTextField.getText(), pwBox.getText(), confirmPwBox.getText() ) );

        // Create scene and set it on the stage
        Scene scene = new Scene(grid, this.xDimension, this.yDimension);
        signUpStage.setScene(scene);
        signUpStage.show();
    }
    
    private void signUp(String username, String password, String cPassword) {//cPassword stands for the string of the confirmed password field 
    	//If the confirmed password does not match the password then give an error to the user
    	if(!password.equals(cPassword)) {
    		showError("Passwords do not match");
    	}
    	else {
    		//If there is no error then make a new patient object and add the 
    		//inputed username and password to the new object 
    		Patient newPatient = new Patient();
    		newPatient.setUsername(username);
    		newPatient.setPassword(password);
    		database.addPatient(newPatient);
    		database.printAllUserNames();
    
    		//go back to login portal now that the account is created 
    		LoginPortal loginPortal = new LoginPortal(database);
    		loginPortal.displayInterface();
    		this.signUpStage.close();
    		System.out.println("SignUp Portal Closed");
    	}
    }
    
    //THIS SHOULD BE IN TOOLKIT.JAVA 
    private void showError(String message) { //Error system: takes in a string to display that string to user as type of error 
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}