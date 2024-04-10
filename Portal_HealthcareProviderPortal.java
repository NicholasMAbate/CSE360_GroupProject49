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
import javafx.scene.layout.*;
import javafx.stage.Stage;

class HealthcareProviderPortal extends Portal {
    private Database database;
    private Stage stage = new Stage();

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
        root.setTop(searchBox);
        root.setLeft(leftPanel);
        root.setCenter(centerPanel);
        root.setRight(appointmentDetailsBox);
        root.setBottom(bottomPanel);

        // Set the primary stage
        stage.setScene(new Scene(root, xDimension, yDimension));
        stage.show();
    }
}
