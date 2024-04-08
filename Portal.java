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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
	private Stage patientStage;
	
    public PatientPortal() {
        super(); // calls the constructor of the parent class (Portal)
    }

    @Override //NEEDS LOTS OF WORK, JUST INITIAL PATIENT PORTAL INTERFACE
    public void displayInterface() {
        System.out.println("Displaying Patient Portal with dimensions: " + this.xDimension + "x" + this.yDimension);

        // Create the Stage and set its title
        patientStage = new Stage();
        patientStage.setTitle("Patient Portal Interface");

        BorderPane root = new BorderPane();

        // Welcome section
        Label welcomeLabel = new Label("Welcome Back LastName, FirstName"); 

        // Health History Section
        TextArea healthHistory = new TextArea("Health History:\n*date diagnosed*         *medical condition*");
        healthHistory.setEditable(false);
        healthHistory.setPrefHeight(200); // Set preferred height

        // Prescription History Section
        TextArea prescriptionHistory = new TextArea("Prescription History:\n*date issued*           *Prescription name*");
        prescriptionHistory.setEditable(false);
        prescriptionHistory.setPrefHeight(200); // Set preferred height

        // Summary of Previous Visits Section
        ComboBox<String> previousVisitsDropdown = new ComboBox<>();
        previousVisitsDropdown.setPromptText("Date of Previous Appointment");
        previousVisitsDropdown.getItems().addAll("2024-01-01", "2024-02-15", "2024-03-20"); // Sample dates
        TextArea previousVisitsSummary = new TextArea("Summary of previous appointment:\n\nDetails...");
        previousVisitsSummary.setEditable(false);
        VBox previousVisitsSection = new VBox(10, previousVisitsDropdown, previousVisitsSummary);

        // Messages Section
        ListView<String> messagesList = new ListView<>();
        messagesList.getItems().addAll("Doctor 1", "Doctor 2", "Nurse 1", "Nurse 2", "Nurse 3");
        messagesList.setPrefWidth(300); // Set preferred width

        // Layout setup
        VBox leftSection = new VBox(10, welcomeLabel, healthHistory, prescriptionHistory, previousVisitsSection);
        VBox rightSection = new VBox(10, new Label("Messages"), messagesList);
        HBox.setHgrow(leftSection, Priority.ALWAYS); // Make left section grow
        HBox.setHgrow(rightSection, Priority.ALWAYS); // Make right section grow

        HBox mainContent = new HBox(10, leftSection, rightSection);
        mainContent.setAlignment(Pos.CENTER); // Center the main content

        root.setCenter(mainContent);

        // Buttons for edit and logout
        Button editButton = new Button("Edit Information");
        Button logoutButton = new Button("Logout?");
        logoutButton.setOnAction(event -> {
            // Assuming this switches the interface
            LoginPortal replacement = new LoginPortal(Main.getHealthcareProviderDatabase(), Main.getPatientDatabase());
            replacement.displayInterface(); // Switch interface
            patientStage.close(); // Closes the patientStage window
        });

        VBox topButtons = new VBox(5, editButton, logoutButton);
        topButtons.setAlignment(Pos.TOP_RIGHT); // Align buttons to the top-right

        root.setTop(topButtons);

        // Set the scene, using xDimension and yDimension for width and height
        patientStage.setScene(new Scene(root, this.xDimension, this.yDimension));
        patientStage.show();
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
        HealthcareProviderStage.setTitle("Healthcare Provider Portal Interface");
        HealthcareProviderStage.setScene(new Scene(new StackPane(new Text("Health Care Provider Portal Displayed")), this.xDimension, this.yDimension));
        HealthcareProviderStage.show();
    }
}

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

class SignUpPortal extends Portal {
    public SignUpPortal() {
        super(); // calls the constructor of the parent class (Portal)
    }
   
    Stage primaryStage = new Stage();
    @Override
    public void displayInterface() {
        System.out.println("Displaying Sign Up Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to SignUpPortal
        primaryStage.setTitle("Account Creation");

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
        btn.setOnAction(e -> {
            // Implement your logic to create an account
            System.out.println("Create account logic goes here.");
        });

        // Create scene and set it on the stage
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}