/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.0
 * Original File Version: April 8, 2024
 * File Last Updated: April 8, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Health care Provider Portal class. This is an extension of the Portal class
 *  which is the abstract class that allows for the creation of different Portals so a display can be displayed to a user.
 *  This is the portal (also known as a Screen) for the Health care Providers (aka medical professionals). 
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
    private Database MPdatabase;
    private Database Pdatabase;
    private Stage loginStage;
	
    public LoginPortal(Database MP_database, Database P_database) { //MP standing for medical professional, P standing for patient 
       super(); // calls the constructor of the parent class (Portal)
       this.MPdatabase = MP_database;
       this.Pdatabase = P_database;
    }

    @Override
    public void displayInterface() {
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
    	loginButton.setOnAction(event -> login(usernameField.getText(), passwordField.getText(), MPdatabase, Pdatabase));
    	
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
    	SignUpPortal signupPortal = new SignUpPortal();
        signupPortal.displayInterface();
        this.loginStage.close();
        System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    }
    
    //Method to handle login button action MORE COMMENTS NEEDED 
    private void login(String username, String password, Database MPdatabase, Database Pdatabase) {
    	System.out.println("Login button clicked with username: " + username + " with password: " + password);
    	accountChecker checker = new accountChecker();
    	
    	if(checker.isValidUserLoginHealthcareProvider(username, password, MPdatabase)) {
    		HealthcareProviderPortal healthcareProviderPortal = new HealthcareProviderPortal();
        	healthcareProviderPortal.displayInterface();
        	this.loginStage.close();
        	System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    	}
    	else if(checker.isValidUserLoginPatient(username, password, Pdatabase)  && checker.isSignedUp(username, password, Pdatabase)) {
    		PatientPortal patientPortal = new PatientPortal();
        	patientPortal.displayInterface();
        	System.out.println("Patient Portal Displayed");
        	this.loginStage.close();
        	System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    	}
    	else {
            //Implement error system
            System.out.println("Not cool: Ur not logging in with a valid account!");
        }
    	
    }
}