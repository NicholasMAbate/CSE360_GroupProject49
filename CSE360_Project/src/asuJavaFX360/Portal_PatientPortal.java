/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.0
 * Original File Version: April 8, 2024
 * File Last Updated: April 8, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Patient Portal class. This is an extension of the Portal class
 *  which is the abstract class that allows for the creation of different Portals so a display can be displayed to a user.
 *  This is the portal for the patients (also known as a Screen). 
 */

package asuJavaFX360;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


class PatientPortal extends Portal {
	private Stage patientStage;
	private Database database;
	private Patient patient;
	private String username;
	
    public PatientPortal(Database database, String username) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
        this.username = username;
    }

    @Override //NEEDS LOTS OF WORK, JUST INITIAL PATIENT PORTAL INTERFACE
    public void displayInterface() {
        System.out.println("Displaying Patient Portal with dimensions: " + this.xDimension + "x" + this.yDimension);

        // Create the Stage and set its title
        patientStage = new Stage();
        patientStage.setTitle("Patient Portal Interface");

        BorderPane root = new BorderPane();
        
        patient = database.getPatientByUsername(username);
        
        String firstName = patient.getFirstName();
        String lastName = patient.getLastName();

        // Welcome section
        Label welcomeLabel = new Label("Welcome Back " + lastName + ", " + firstName); 

        // Health History Section
        TextArea healthHistory = new TextArea("Health History:\n*date diagnosed*         *medical condition*\n" +
        		                              "                                        " + patient.getMedicalHistory() );
        healthHistory.setEditable(false);
        healthHistory.setPrefHeight(200); // Set preferred height

        // Prescription History Section
        TextArea prescriptionHistory = new TextArea("Prescription History:\n*date issued*          *Prescription name*");
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
            LoginPortal replacement = new LoginPortal(database);
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