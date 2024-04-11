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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
        TextArea healthHistory = new TextArea("Health History: " + patient.getMedicalHistory() );
        healthHistory.setEditable(true);
        healthHistory.setPrefHeight(200); // Set preferred height

        // Prescription History Section
        TextArea prescriptionHistory = new TextArea("Prescription History: ");
        prescriptionHistory.setEditable(true);
        prescriptionHistory.setPrefHeight(200); // Set preferred height
        
        
        // Summary of Previous Visits Section
        ComboBox<String> previousVisitsDropdown = new ComboBox<>();
        previousVisitsDropdown.setPromptText("Date of Previous Appointment");

        // Keep a reference to the patient's appointments
        List<Appointment> appointments = patient.getAppointments();

        for (Appointment appointment : appointments) {
            previousVisitsDropdown.getItems().add(appointment.getDate());
        }

        TextArea previousVisitsSummary = new TextArea("Summary of previous appointment:\n\nDetails...");
        previousVisitsSummary.setEditable(false);

        // Updates previous visit dropdown whenever doctor/provider enters new information from their portal.
        previousVisitsDropdown.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            // Find the appointment that matches date
            for (Appointment appointment : appointments) {
                if (appointment.getDate().equals(newValue)) {
                    // Update the TextArea with the details of the selected appointment
                    previousVisitsSummary.setText("Summary of previous appointment:\n\n" +
                        "Date: " + appointment.getDate() + "\n" +
                        "Temperature: " + appointment.getTemperature() + "\n" +
                        "Height: " + appointment.getHeight() + "\n" +
                        "Weight: " + appointment.getWeight() + "\n" +
                        "Heart Rate: " + appointment.getHeartRate() + "\n" +
                        "Blood Pressure: " + appointment.getBloodPressure() + "\n" +
                        "Summary: " + appointment.getSummaryOfVisit());
                    break; // Stop once the appointment is found
                }
            }
        });

        VBox previousVisitsSection = new VBox(10, previousVisitsDropdown, previousVisitsSummary);

        // Messages Section
        TextArea messagesTextArea = new TextArea();
        messagesTextArea.setPromptText("Enter your message here...");
        messagesTextArea.setPrefWidth(300);
        messagesTextArea.setPrefHeight(200);

        //Write messages to file for future use
        Button messageButton = new Button("Message!");
        messageButton.setOnAction(event -> {
            String message = messagesTextArea.getText();
            if (!message.isEmpty()) {
                String patientID = patient.getPatientID();
                String filename = "C:\\ASU\\CSE360\\Java\\360-workspace\\CSE360_Project\\src\\asuJavaFX360\\" + patientID + "_message.txt";
                try (FileWriter writer = new FileWriter(filename, true)) {
                    writer.write("-----------------\n");
                    writer.write("Patient: " + patient.getLastName() + ", " + patient.getFirstName() + "\n");
                    writer.write(message + "\n");
                    System.out.println("Message saved to file: " + filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                messagesTextArea.clear(); // Clear the text area after saving the message
            }
        });

        VBox messagesSection = new VBox(10, messagesTextArea, messageButton);
        messagesSection.setPrefWidth(300);

        // Display messages from file if it exists and is not empty
        String patientID = patient.getPatientID();
        String filename = "C:\\ASU\\CSE360\\Java\\360-workspace\\CSE360_Project\\src\\asuJavaFX360\\" + patientID + "_message.txt";
        File file = new File(filename);
        if (file.exists() && file.length() > 0) {
            try (Scanner scanner = new Scanner(file)) {
                StringBuilder fileContents = new StringBuilder();
                while (scanner.hasNextLine()) {
                    fileContents.append(scanner.nextLine()).append("\n");
                }
                messagesTextArea.setText(fileContents.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Layout setup
        VBox leftSection = new VBox(10, welcomeLabel, healthHistory, prescriptionHistory, previousVisitsSection);
        VBox rightSection = new VBox(10, new Label("Messages"), messagesSection);
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