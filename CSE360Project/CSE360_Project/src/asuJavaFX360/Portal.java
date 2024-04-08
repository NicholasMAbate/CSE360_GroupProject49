/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.2.2
 * Original File Version: March 19, 2024
 * File Last Updated: March 20, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Portal classes. The Portal classes display the different portals
 *  (also known as Screens) of the coding system 
 */


package asuJavaFX360;

//import the javaFX that is used 
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

abstract class Portal {
    protected int xDimension;
    protected int yDimension;

    public Portal() {
    	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        this.xDimension = (int) screenBounds.getWidth();
        this.yDimension = (int) screenBounds.getHeight();
    }

    public abstract void displayInterface();
    
}

class PatientPortal extends Portal {
    public PatientPortal() {
        super(); // calls the constructor of the parent class (Portal)
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying Patient Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to PatientPortal
    }
}

class HealthcareProviderPortal extends Portal {
    public HealthcareProviderPortal() {
        super(); // calls the constructor of the parent class (Portal)
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying Healthcare Provider Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to HealthcareProviderPortal
        
        Stage HealthcareProviderStage = new Stage();
        HealthcareProviderStage.setTitle("Health care Provider Portal Interface");
        HealthcareProviderStage.setScene(new Scene(new StackPane(new Text("Health Care Provider Portal Displayed")), this.xDimension, this.yDimension));
        HealthcareProviderStage.show();
    }
}

class LoginPortal extends Portal {
    private Database database;
    private Stage loginStage;
	
    public LoginPortal(Database MP_database) { //MP standing for medical professional 
       super(); // calls the constructor of the parent class (Portal)
       this.database = MP_database;
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
    	
    	//logic for login button action (to be implemented) 
    	loginButton.setOnAction(event -> login(usernameField.getText(), passwordField.getText(), database));
    	
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
        vBox.getChildren().addAll(usernameBox, passwordBox, loginButton);

    	
    	//set the Scene with the desired layout
    	Scene scene = new Scene(new StackPane(vBox), this.xDimension, this.yDimension);
    	this.loginStage.setScene(scene);
    	this.loginStage.show();
    }
    
    //Method to handle login button action MORE COMMENTS NEEDED 
    private void login(String username, String password, Database database) {
    	System.out.println("Login button clicked with username: " + username + " with password: " + password);
    	accountChecker checker = new accountChecker();
    	System.out.println(checker.isValidUserLoginHealthcareProvider(username, password, database));
    	if(checker.isValidUserLoginHealthcareProvider(username, password, database)) {
    		HealthcareProviderPortal healthcareProviderPortal = new HealthcareProviderPortal();
        	healthcareProviderPortal.displayInterface();
        	this.loginStage.close();
        	System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    	}
    	
    }
}

class SignUpPortal extends Portal {
    public SignUpPortal() {
        super(); // calls the constructor of the parent class (Portal)
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying Sign Up Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to SignUpPortal
    }
}
