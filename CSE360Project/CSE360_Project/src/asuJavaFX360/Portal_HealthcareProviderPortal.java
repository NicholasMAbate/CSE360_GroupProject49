package asuJavaFX360;

import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class HealthcareProviderPortal extends Portal {
    private HealthcareProvider currentUser; 
    private Database database;
    private Stage stage = new Stage();
    // Define labels to be updated
    private Label nameLabel;
    private Label dobLabel;
    private Label phoneLabel;
    private Label emailLabel;
    private Label insuranceLabel;
    private Label pharmacyLabel;

    public HealthcareProviderPortal(Database database, HealthcareProvider healthcareProvider) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
        this.currentUser = healthcareProvider;
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying HealthcareProviderPortal with dimensions: " + xDimension + "x" + yDimension);
        stage.setTitle("Healthcare Provider Portal Interface");

        // Define layout sections
        BorderPane root = new BorderPane();
        
        // Top: Display current user's name and search field
        Label welcomeLabel = new Label("Welcome: " + currentUser.getLastName() + ", " + currentUser.getFirstName());
        TextField patientIDField = new TextField();
        patientIDField.setPromptText("Enter PatientID...");
        Button searchButton = new Button("Search");
        HBox topBox = new HBox(10, welcomeLabel, patientIDField, searchButton);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.CENTER_LEFT);

        // Separator Line
        Line separatorLine = new Line();
        separatorLine.setStartX(0);
        separatorLine.setEndX(Double.MAX_VALUE);

        // Patient Information Section
        VBox patientInfoBox = new VBox();
        patientInfoBox.setPadding(new Insets(10));
        patientInfoBox.setSpacing(5);

        // Initialize labels
        Label patientInfoLabel = new Label("Patient Information:");
        nameLabel = new Label("Name: ");
        dobLabel = new Label("Date of Birth: ");
        phoneLabel = new Label("Phone number: ");
        emailLabel = new Label("Email Address: ");
        insuranceLabel = new Label("InsuranceID: ");
        pharmacyLabel = new Label("Pharmacy: ");

        patientInfoBox.getChildren().addAll(patientInfoLabel, nameLabel, dobLabel, phoneLabel, emailLabel, insuranceLabel, pharmacyLabel);

        // Left: Messages Section
        ListView<String> messageList = new ListView<>();
        messageList.getItems().addAll("Person 1", "Person 2", "Person 3", "Person 4"); // Dummy data
        VBox leftPanel = new VBox(new Label("Messages"), messageList);
        leftPanel.setPadding(new Insets(10));
        leftPanel.setSpacing(5);

        // Center: Patient Lookup Form
        GridPane centerPanel = new GridPane();
        centerPanel.setPadding(new Insets(10));
        centerPanel.setVgap(10);
        centerPanel.setHgap(10);

        // Patient Information
        centerPanel.add(new Label("First and Last Name"), 0, 1);
        centerPanel.add(new TextField(), 1, 1);
        centerPanel.add(new Label("Patient Age"), 0, 2);
        centerPanel.add(new TextField(), 1, 2);
        centerPanel.add(new Label("Patient Address"), 0, 3);
        centerPanel.add(new TextField(), 1, 3);
        centerPanel.add(new Label("Patient DOB"), 0, 4);
        centerPanel.add(new TextField(), 1, 4);
        centerPanel.add(new Label("Patient Phone"), 0, 5);
        centerPanel.add(new TextField(), 1, 5);
        centerPanel.add(new Label("Patient Email"), 0, 6);
        centerPanel.add(new TextField(), 1, 6);
        centerPanel.add(new Label("Patient Insurance ID"), 0, 7);
        centerPanel.add(new TextField(), 1, 7);
        centerPanel.add(new Label("Preferred Pharmacy"), 0, 8);
        centerPanel.add(new TextField(), 1, 8);

        // Patient History
        centerPanel.add(new Label("Patient Health History Questionnaire"), 2, 1, 2, 1);
        // Add CheckBoxes for questions Q1 to Q6 here

        centerPanel.add(new Label("Prescription History"), 2, 2, 2, 1);
        // Add TextArea for prescription history here

        centerPanel.add(new Label("History of immunization"), 2, 3, 2, 1);
        // Add TextArea for immunization history here

        // Right: Patient Appointment Details
        VBox appointmentDetailsBox = new VBox(10);
        appointmentDetailsBox.setPadding(new Insets(10));
        appointmentDetailsBox.getChildren().add(new Label("Patient Appointment Details:"));
        appointmentDetailsBox.getChildren().add(new Label("Today's Date:"));
        appointmentDetailsBox.getChildren().add(new TextField());
        appointmentDetailsBox.getChildren().add(new Label("Patient Temperature (F):"));
        appointmentDetailsBox.getChildren().add(new TextField());
        appointmentDetailsBox.getChildren().add(new Label("Patient Heart Rate:"));
        appointmentDetailsBox.getChildren().add(new TextField());
        appointmentDetailsBox.getChildren().add(new Label("Patient Weight:"));
        appointmentDetailsBox.getChildren().add(new TextField());
        appointmentDetailsBox.getChildren().add(new Label("Patient Height:"));
        appointmentDetailsBox.getChildren().add(new TextField());
        appointmentDetailsBox.getChildren().add(new Label("Patient Blood Pressure:"));
        appointmentDetailsBox.getChildren().add(new TextField());
        appointmentDetailsBox.getChildren().add(new Label("Summary of Visit:"));
        appointmentDetailsBox.getChildren().add(new TextArea());

        // Bottom: Save button
        Button saveButton = new Button("Save Details");
        HBox bottomPanel = new HBox(saveButton);
        bottomPanel.setAlignment(Pos.CENTER_RIGHT);
        bottomPanel.setPadding(new Insets(10));

        // Assemble the main layout
        VBox topSection = new VBox(topBox, separatorLine);
        VBox leftSection = new VBox(patientInfoBox, leftPanel);
        VBox centerSection = new VBox(centerPanel);
        VBox rightSection = new VBox(appointmentDetailsBox);
        VBox bottomSection = new VBox(bottomPanel);

        root.setTop(topSection);
        root.setLeft(leftSection);
        root.setCenter(centerSection);
        root.setRight(rightSection);
        root.setBottom(bottomSection);
        
        // Set the primary stage
        stage.setScene(new Scene(root, xDimension, yDimension));
        stage.show();
        
        // Set action for search button
        searchButton.setOnAction(event -> search(patientIDField.getText()));
    }
   
    /* This method takes in a patientID then searches for the patient information file 
     * that is connected to this ID in order to display the correct Patient information */
    private void search(String patientID) {
        System.out.println("Searching for patient with ID: " + patientID); //test line TO BE DELETED 
        // Construct the file name based on patient ID
        String filename = System.getProperty("user.home") + "/Desktop/CSE360Project/" + patientID + "_PatientInfo.txt";
        
        // Attempt to read the file and fill out the corresponding fields
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Skip the first line (PatientID)
            reader.readLine();
            
            // Read the rest of the lines and fill out the corresponding fields
            String firstName = reader.readLine();
            String lastName = reader.readLine();
            String pNum = reader.readLine();
            String dob = reader.readLine();
            String emailAddress = reader.readLine();
            String insuranceNumber = reader.readLine();
            String pharmacy = reader.readLine();
            
            // Fill out the fields
            nameLabel.setText("Name: " + firstName + " " + lastName);
            dobLabel.setText("Date of Birth: " + dob);
            phoneLabel.setText("Phone number: " + pNum); // You can add a phone number field if needed
            emailLabel.setText("Email Address: " + emailAddress);
            insuranceLabel.setText("InsuranceID: " + insuranceNumber);
            pharmacyLabel.setText("Pharmacy: " + pharmacy);
            
            // Print success message
            System.out.println("Patient information retrieved successfully from file: " + filename);
            
        } catch (IOException e) {
            // Handle any errors that occur during file reading
            e.printStackTrace();
        }
    }
}
