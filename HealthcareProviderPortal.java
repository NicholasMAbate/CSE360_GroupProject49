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


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class HealthcareProviderPortal extends Portal {
    
    private Database database;
    private Stage stage = new Stage();
    private Patient patient;
    private Appointment appointment;

    public HealthcareProviderPortal(Database database) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
    }

    @Override
    public void displayInterface() {
        stage.setTitle("Healthcare Provider Portal Interface");

        // Define layout sections
        BorderPane root = new BorderPane();

        // Top: Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("First and Last Name...");
        Button searchButton = new Button("Search");
        HBox searchBox = new HBox(100, searchField, searchButton);
        searchBox.setPadding(new Insets(10));
        searchBox.setAlignment(Pos.CENTER);

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
        // First and Last Name
        TextField nameField = new TextField();
        nameField.setEditable(false);
        centerPanel.add(new Label("First and Last Name"), 0, 1);
        centerPanel.add(nameField, 1, 1);

        // Patient Age
        TextField ageField = new TextField();
        ageField.setEditable(false);
        centerPanel.add(new Label("Patient Age"), 0, 2);
        centerPanel.add(ageField, 1, 2);

        // Patient Address
        TextField addressField = new TextField();
        addressField.setEditable(false);
        centerPanel.add(new Label("Patient Address"), 0, 3);
        centerPanel.add(addressField, 1, 3);

        // Patient DOB
        TextField dobField = new TextField();
        dobField.setEditable(false);
        centerPanel.add(new Label("Patient DOB"), 0, 4);
        centerPanel.add(dobField, 1, 4);

        // Patient Phone
        TextField phoneField = new TextField();
        phoneField.setEditable(false);
        centerPanel.add(new Label("Patient Phone"), 0, 5);
        centerPanel.add(phoneField, 1, 5);

        // Patient Email
        TextField emailField = new TextField();
        emailField.setEditable(false);
        centerPanel.add(new Label("Patient Email"), 0, 6);
        centerPanel.add(emailField, 1, 6);

        // Patient Insurance ID
        TextField insuranceIDField = new TextField();
        insuranceIDField.setEditable(false);
        centerPanel.add(new Label("Patient Insurance ID"), 0, 7);
        centerPanel.add(insuranceIDField, 1, 7);

        // Preferred Pharmacy
        TextField pharmacyField = new TextField();
        pharmacyField.setEditable(false);
        centerPanel.add(new Label("Preferred Pharmacy"), 0, 8);
        centerPanel.add(pharmacyField, 1, 8);

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

        // Adding labels
        appointmentDetailsBox.getChildren().add(new Label("Patient Appointment Details:"));
        appointmentDetailsBox.getChildren().add(new Label("Today's Date:"));

        // Declare and add TextFields and TextArea
        TextField dateField = new TextField();
        TextField temperatureField = new TextField();
        TextField heartRateField = new TextField();
        TextField weightField = new TextField();
        TextField heightField = new TextField();
        TextField bloodPressureField = new TextField();
        TextArea summaryArea = new TextArea();

        // Adding fields to VBox
        appointmentDetailsBox.getChildren().addAll(dateField, new Label("Patient Temperature (F):"), temperatureField,
                new Label("Patient Heart Rate:"), heartRateField, new Label("Patient Weight:"), weightField,
                new Label("Patient Height:"), heightField, new Label("Patient Blood Pressure:"), bloodPressureField,
                new Label("Summary of Visit:"), summaryArea);

        // Bottom: Save Appointment Details button
        Button submitButton = new Button("Submit");
        appointmentDetailsBox.getChildren().add(submitButton);

        // Left: Messages Section
        TextArea messagesTextArea = new TextArea();
        messagesTextArea.setPromptText("Enter your message here...");
        messagesTextArea.setPrefWidth(300);
        messagesTextArea.setPrefHeight(200);

        Button messageButton = new Button("Message!");
        messageButton.setOnAction(event -> {
            String message = messagesTextArea.getText();
            if (!message.isEmpty()) {
                String patientID = patient.getPatientID();
                String filename = System.getProperty("user.home") + "/Desktop/CSE360Project/" + patientID + "_message.txt";
                try (FileWriter writer = new FileWriter(filename, true)) {
                    writer.write("\n-----------------\n");
                    writer.write("Doctor: " + message + "\n");
                    System.out.println("Message saved to file: " + filename);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                messagesTextArea.clear(); // Clear the text area after saving the message
            }
        });

        VBox messagesSection = new VBox(10, new Label("Messages"), messagesTextArea, messageButton);
        messagesSection.setPrefWidth(300);

        // Layout setup
        HBox mainContent = new HBox(10, leftPanel, centerPanel, appointmentDetailsBox);
        mainContent.setAlignment(Pos.CENTER); // Center the main content

        root.setTop(searchBox);
        root.setLeft(messagesSection);
        root.setCenter(mainContent);

        //THIS WILL UPDATE ALL ABOVE FIELDS AFTER SEARCHING
        searchButton.setOnAction(event -> {
            patient = database.getPatientByFirstAndLast(searchField.getText());

            if (patient != null) {
                nameField.setText(patient.getFullName());
                ageField.setText(Integer.toString(patient.getAge()));
                addressField.setText(patient.getAddress());
                emailField.setText(patient.getEmail());
                dobField.setText(patient.getDOB());
                phoneField.setText(patient.getPhoneNumber());
                insuranceIDField.setText(patient.getInsuranceID());
                pharmacyField.setText(patient.getPharmacy());

                // Read and display contents of the message file
                String patientID = patient.getPatientID();
                String filename = System.getProperty("user.home") + "/Desktop/CSE360Project/" + patientID + "_message.txt";
                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    StringBuilder messageContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        messageContent.append(line).append("\n");
                    }
                    messagesTextArea.setText(messageContent.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Search Error");
                alert.setHeaderText(null); // No header text
                alert.setContentText("No patient found with the provided name. Please try again.");
                alert.showAndWait();
            }
        });


        //THIS WILL PUSH APPOINTMENT OBJECTS TO PATIENT OF INTEREST POST SEARCH!
        submitButton.setOnAction(e -> {
            patient = database.getPatientByFirstAndLast(searchField.getText());

            appointment = new Appointment(dateField.getText(), temperatureField.getText(), heartRateField.getText(), weightField.getText(),
                    heightField.getText(), bloodPressureField.getText(), summaryArea.getText());

            patient.addAppointment(appointment);

            dateField.setText("");
            temperatureField.setText("");
            heartRateField.setText("");
            weightField.setText("");
            heightField.setText("");
            bloodPressureField.setText("");
            summaryArea.setText("");
        });

        // Layout setup
        mainContent.setAlignment(Pos.CENTER); // Center the main content

        root.setTop(searchBox);
        root.setLeft(messagesSection);
        root.setCenter(mainContent);

        // Set the primary stage
        stage.setScene(new Scene(root, xDimension, yDimension));
        stage.show();
    }
}