/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.3
 * Original File Version: April 8, 2024
 * File Last Updated: April 8, 2024 
 * 
 * 1. File Description
 *  NEEDED
 */

package asuJavaFX360;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class LoginPortal extends Portal {
    private Database database;
    private Stage loginStage;
	
    public LoginPortal(Database database) { //MP standing for medical professional, P standing for patient 
       super(); // calls the constructor of the parent class (Portal)
       this.database = database;
    }

    @Override
    public void displayInterface() {
    	System.out.println("Displaying Login Portal with dimensions: " + xDimension + "x" + yDimension);
    	//first create the stage
    	this.loginStage = new Stage();
    	loginStage.setTitle("Login Portal Interface");
    	
    	//input boxes for username and password
    	Label usernameLabel = new Label("Username:");
    	TextField usernameField = new TextField();
    	Label passwordLabel = new Label("Password:");
    	TextField passwordField = new TextField();
    	
    	//Preferred width for labels and text fields 
    	usernameLabel.setPrefWidth(this.xDimension / 4);
        passwordLabel.setPrefWidth(this.xDimension / 4);
        usernameField.setPrefWidth(this.xDimension / 4);
        passwordField.setPrefWidth(this.xDimension / 4);
    	
    	//login button
    	Button loginButton = new Button("Login");
    	
    	//new account button if account doesn't exist
    	Button createButton = new Button("Create New Account");
    	
    	//logic for login button action (to be implemented) 
    	loginButton.setOnAction(event -> login(usernameField.getText(), passwordField.getText() ) );
    	
    	//logic for create account button
    	createButton.setOnAction(event -> signUp());
    	
    	// Create HBox for username input
        HBox usernameBox = new HBox(5);
        usernameBox.setAlignment(Pos.CENTER);
        usernameBox.getChildren().addAll(usernameLabel, usernameField);

        // Create HBox for password input
        HBox passwordBox = new HBox(5);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        // Create a VBox to organize components vertically
        VBox vBox = new VBox(10); // Set spacing between components
        vBox.setAlignment(Pos.CENTER); // Center-align components
        vBox.getChildren().addAll(usernameBox, passwordBox, loginButton, createButton);

    	
    	//set the Scene with the desired layout
    	Scene scene = new Scene(new StackPane(vBox), this.xDimension, this.yDimension);
    	this.loginStage.setScene(scene);
    	this.loginStage.show();
    }
    
    //Method to handle create account button MORE COMMENTS NEEDED
    private void signUp(){
    	SignUpPortal signupPortal = new SignUpPortal(database);
        signupPortal.displayInterface();
        this.loginStage.close();
        System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    }
    
    //Method to handle login button action MORE COMMENTS NEEDED 
    private void login(String username, String password) {
    	System.out.println("Login button clicked with username: " + username + " with password: " + password); //test line TO BE DELTED 
    	AccountChecker checker = new AccountChecker();
    	
    	/* First if statement checks if the given username and password link to a Health Care Provider object, the smallest 
    	 * amount of object. Then if it is not found as a Health care provider it is checked if it is a Patient object, 
    	 * the next smallest amount of an object. This is then checked if the patient, once discovered, has gone through
    	 * the account registration by checking an attribute, if it has NOT gone through registration, patient it taken to
    	 * Portal_PatientRegistrationPortal to complete. If it has already completed this then it will be logged into the 
    	 * Portal_PatientPortal. For the biggest group of object (not an object) it will make an error for not being a discoverable object */
    	if(checker.isValidUserLoginHealthcareProvider(username, password, database)) { //check if username and password is a Health care account 
    		HealthcareProviderPortal healthcareProviderPortal = new HealthcareProviderPortal(database);
        	healthcareProviderPortal.displayInterface();
        	this.loginStage.close();
        	System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    	}
    	else if(checker.isValidUserLoginPatient(username, password, database)) { //check if username and password is a Patient account 
    		if(checker.isSignedUp(username, password, database) ) {
    			PatientPortal patientPortal = new PatientPortal(database);
            	patientPortal.displayInterface();
            	this.loginStage.close();
            	System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    		}
    		else {
    			RegistrationPortal registrationPortal = new RegistrationPortal(database, username, password);
    			registrationPortal.displayInterface();
    			this.loginStage.close();
    			System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    		}
    	}	
    	else {
    		showError("Invalid Username or Password"); //display error if no account is found for given username and password
    		System.out.println("Not cool: Ur trying to log in without a valid account!");
    	}
    	
    }
    
  //method for making error object and displaying inputed string when called.
    private void showError(String message) { 
    	ErrorGenerator errorGenerator = new ErrorGenerator(message);
    	errorGenerator.showError();
    }
}