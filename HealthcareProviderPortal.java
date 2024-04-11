package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HealthcareProviderPortal extends Portal {
    private Database database;
    private Stage stage;

    public HealthcareProviderPortal(Database database) {
        super(); // Calls the constructor of the parent class (Portal)
        this.database = database;
        this.stage = new Stage();
    }

    @Override
    public void displayInterface() {
        stage.setTitle("Clinic 49 Healthcare Provider Portal");

        // Top: Clinic Title
        Label titleLabel = new Label("Clinic 49");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));

        // Left: Messages Section
        ListView<String> messageList = new ListView<>();
        messageList.getItems().addAll("Person 1", "Person 2", "Person 3", "Person 4");
        VBox leftPanel = new VBox(10, new Label("Messages"), messageList);
        leftPanel.setPadding(new Insets(10));

        // Center: Patient Information, History, and Appointment Details
        Accordion centerAccordion = new Accordion();
        centerAccordion.getPanes().addAll(createPatientInfoPane(), createPatientHistoryPane(), createPatientAppointmentDetailsPane());

        // Bottom: Save button
        Button saveButton = new Button("Save Details");
        HBox bottomPanel = new HBox(saveButton);
        bottomPanel.setAlignment(Pos.CENTER_RIGHT);
        bottomPanel.setPadding(new Insets(10));

        // Assemble the main layout
        BorderPane root = new BorderPane();
        root.setTop(titleBox);
        root.setLeft(leftPanel);
        root.setCenter(centerAccordion);
        root.setBottom(bottomPanel);

        // Set the primary stage
        Scene scene = new Scene(root, 1024, 768); // Adjust the size as needed
        stage.setScene(scene);
        stage.show();
    }

    private TitledPane createPatientInfoPane() {
        GridPane patientInfoGrid = new GridPane();
        patientInfoGrid.setVgap(10);
        patientInfoGrid.setHgap(10);
        patientInfoGrid.setPadding(new Insets(10));
        // Add patient information form components here
        String[] labels = {"First Name", "Last Name", "Date of Birth", "Address", "Phone Number", "Email", "Insurance ID", "Preferred Pharmacy"};
        for (int i = 0; i < labels.length; i++) {
            patientInfoGrid.add(new Label(labels[i]), 0, i);
            patientInfoGrid.add(new TextField(), 1, i);
        }
        
        return new TitledPane("Patient Information", patientInfoGrid);
    }

    private TitledPane createPatientHistoryPane() {
        VBox patientHistoryBox = new VBox(10);
        patientHistoryBox.setPadding(new Insets(10));

        // Health History Questionnaire Section
        patientHistoryBox.getChildren().addAll(new Label("Health History Questionnaire:"), new CheckBox("Do you have any allergies?"), new CheckBox("Do you have a history of heart problems?"), new CheckBox("Have you had any surgeries?"));

        // Prescription History Section
        patientHistoryBox.getChildren().add(new Label("Prescription History:"));
        TextArea prescriptionHistoryArea = new TextArea();
        prescriptionHistoryArea.setPrefHeight(50); // Set preferred height
        patientHistoryBox.getChildren().add(prescriptionHistoryArea);

        // Immunization Records Section
        patientHistoryBox.getChildren().add(new Label("Immunization Records:"));
        TextArea immunizationRecordsArea = new TextArea();
        immunizationRecordsArea.setPrefHeight(50); // Set preferred height
        patientHistoryBox.getChildren().add(immunizationRecordsArea);

        // Past Appointment Details Section
        patientHistoryBox.getChildren().add(new Label("Past Appointment Details:"));
        ListView<String> pastAppointmentsList = new ListView<>();
        pastAppointmentsList.getItems().addAll("01/01/2020 - Checkup", "12/12/2020 - Vaccination"); // Placeholder data
        patientHistoryBox.getChildren().add(pastAppointmentsList);

        return new TitledPane("Patient History", patientHistoryBox);
    }

    private TitledPane createPatientAppointmentDetailsPane() {
        VBox appointmentDetailsBox = new VBox(10);
        appointmentDetailsBox.setPadding(new Insets(10));

        // Add components for the patient appointment details section
        appointmentDetailsBox.getChildren().addAll(new Label("Patient Appointment Details:"), createDetailItem("Today's Date:", new TextField("MM/dd/yyyy")), createDetailItem("Patient Temperature (F):", new TextField()), createDetailItem("Patient Heart Rate:", new TextField()), createDetailItem("Patient Weight:", new TextField()), createDetailItem("Patient Height:", new TextField()), createDetailItem("Patient Blood Pressure:", new TextField()), new Label("Summary of Visit:"), new TextArea());

        return new TitledPane("Appointment Details", appointmentDetailsBox);
    }

    private HBox createDetailItem(String label, TextField textField) {
        Label lbl = new Label(label);
        HBox hbox = new HBox(lbl, textField);
        hbox.setSpacing(10);
        return hbox;
    }
}