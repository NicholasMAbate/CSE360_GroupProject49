package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.DatePicker;



public class HealthcareProviderPortal extends Portal {
    private Database database;

    public HealthcareProviderPortal(Database database) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
    }

    @Override
    public void displayInterface(Stage stage) {
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
        TitledPane patientInfoPane = createPatientInfoPane();
        TitledPane patientHistoryPane = createPatientHistoryPane();
        TitledPane patientAppointmentDetailsPane = createPatientAppointmentDetailsPane();
        centerAccordion.getPanes().addAll(patientInfoPane, patientHistoryPane, patientAppointmentDetailsPane);

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
        //primaryStage.setScene(new Scene(root, 1024, 768)); // Adjust the size as needed
        //primaryStage.show();
    }

    private TitledPane createPatientInfoPane() {
        GridPane patientInfoGrid = new GridPane();
        patientInfoGrid.setVgap(10);
        patientInfoGrid.setHgap(10);
        patientInfoGrid.setPadding(new Insets(10));
        // Add patient information form components here
        // ...
        patientInfoGrid.add(new Label("First Name"), 0, 0);
        patientInfoGrid.add(new TextField(), 1, 0);

        patientInfoGrid.add(new Label("Last Name"), 0, 1);
        patientInfoGrid.add(new TextField(), 1, 1);

        patientInfoGrid.add(new Label("Date of Birth"), 0, 2);
        patientInfoGrid.add(new TextField(), 1, 2);

        patientInfoGrid.add(new Label("Address"), 0, 3);
        patientInfoGrid.add(new TextField(), 1, 3);

        patientInfoGrid.add(new Label("Phone Number"), 0, 4);
        patientInfoGrid.add(new TextField(), 1, 4);

        patientInfoGrid.add(new Label("Email"), 0, 5);
        patientInfoGrid.add(new TextField(), 1, 5);

        patientInfoGrid.add(new Label("Insurance ID"), 0, 6);
        patientInfoGrid.add(new TextField(), 1, 6);

        patientInfoGrid.add(new Label("Preferred Pharmacy"), 0, 7);
        patientInfoGrid.add(new TextField(), 1, 7);
        
        
        TitledPane patientInfoPane = new TitledPane("Patient Information", patientInfoGrid);
        return patientInfoPane;
    }

    private TitledPane createPatientHistoryPane() {
        VBox patientHistoryBox = new VBox(10);
        patientHistoryBox.setPadding(new Insets(10));
        // Add patient history components here
        // ...
        
     // Health History Questionnaire Section
        VBox healthHistoryBox = new VBox(5);
        healthHistoryBox.getChildren().add(new Label("Health History Questionnaire:"));
        // Example questions - these can be checkboxes or yes/no options
        healthHistoryBox.getChildren().add(new CheckBox("Do you have any allergies?"));
        healthHistoryBox.getChildren().add(new CheckBox("Do you have a history of heart problems?"));
        healthHistoryBox.getChildren().add(new CheckBox("Have you had any surgeries?"));

        // Prescription History Section
        VBox prescriptionHistoryBox = new VBox(5);
        prescriptionHistoryBox.getChildren().add(new Label("Prescription History:"));
        TextArea prescriptionHistoryArea = new TextArea();
        prescriptionHistoryArea.setPrefHeight(50); // Set preferred height
        prescriptionHistoryBox.getChildren().add(prescriptionHistoryArea);

        // Immunization Records Section
        VBox immunizationRecordsBox = new VBox(5);
        immunizationRecordsBox.getChildren().add(new Label("Immunization Records:"));
        TextArea immunizationRecordsArea = new TextArea();
        immunizationRecordsArea.setPrefHeight(50); // Set preferred height
        immunizationRecordsBox.getChildren().add(immunizationRecordsArea);

        // Past Appointment Details Section
        VBox pastAppointmentsBox = new VBox(5);
        pastAppointmentsBox.getChildren().add(new Label("Past Appointment Details:"));
        ListView<String> pastAppointmentsList = new ListView<>();
        pastAppointmentsList.getItems().addAll("01/01/2020 - Checkup", "12/12/2020 - Vaccination"); // Placeholder data
        pastAppointmentsBox.getChildren().add(pastAppointmentsList);

        // Add all sections to the main patient history box
        patientHistoryBox.getChildren().addAll(
                healthHistoryBox,
                prescriptionHistoryBox,
                immunizationRecordsBox,
                pastAppointmentsBox
        );
        
        
        TitledPane patientHistoryPane = new TitledPane("Patient History", patientHistoryBox);
        return patientHistoryPane;
    }
    
    private TitledPane createPatientAppointmentDetailsPane() {
        VBox appointmentDetailsBox = new VBox(10);
        appointmentDetailsBox.setPadding(new Insets(10));

        // Create components for the patient appointment details section
        Label appointmentDetailsLabel = new Label("Patient Appointment Details:");
        appointmentDetailsLabel.setStyle("-fx-font-weight: bold;");

        Label dateLabel = new Label("Today's Date:");
        TextField dateField = new TextField();
        dateField.setPromptText("MM/dd/yyyy");

        Label temperatureLabel = new Label("Patient Temperature (F):");
        TextField temperatureField = new TextField();

        Label heartRateLabel = new Label("Patient Heart Rate:");
        TextField heartRateField = new TextField();

        Label weightLabel = new Label("Patient Weight:");
        TextField weightField = new TextField();

        Label heightLabel = new Label("Patient Height:");
        TextField heightField = new TextField();

        Label bloodPressureLabel = new Label("Patient Blood Pressure:");
        TextField bloodPressureField = new TextField();

        Label summaryOfVisitLabel = new Label("Summary of Visit:");
        TextArea summaryOfVisitArea = new TextArea();
        summaryOfVisitArea.setPrefHeight(100); // Set preferred height for the TextArea

        // Add all components to the box
        appointmentDetailsBox.getChildren().addAll(
            appointmentDetailsLabel,
            dateLabel, dateField,
            temperatureLabel, temperatureField,
            heartRateLabel, heartRateField,
            weightLabel, weightField,
            heightLabel, heightField,
            bloodPressureLabel, bloodPressureField,
            summaryOfVisitLabel, summaryOfVisitArea
        );

        // Create TitledPane for Appointment Details
        TitledPane appointmentDetailsPane = new TitledPane("Appointment Details", appointmentDetailsBox);
        return appointmentDetailsPane;
    }

}
