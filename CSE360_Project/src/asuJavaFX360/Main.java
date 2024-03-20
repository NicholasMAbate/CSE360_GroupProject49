/* 
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * Version: 1.0.0.1
 * Original Version: March 19, 2024
 * Last Updated: March 19, 2024
 * 
 * 1. Introduction
 * The following is the implementation of an office automation system for a pediatric doctor's office. The system is 
 * designed to be able to do the following. Provide, update, and store patient records. Establish communication 
 * between doctor and patient via a messaging terminal. And provide medication prescriptions. 
 * 	1.1 Clinic 49
 *   The following software system is designed for the fictional pediatric doctor's office of Clinic 49. This software is
 *   an academic exercise.
 *  1.2 Patient Experience
 *   TO BE ADDED 
 *  1.3 Medical Professional Experience 
 * 	 TO BE ADDED
 *  
 * 2. Detailed Design
 *  2.1 Interface Design
 *    2.1.1 GUI Design
 *     TO BE ADDED
 *    2.1.2 Web Design
 *     TO BE ADDED 
 *    2.1.3 Phone Application Design
 *     TO BE ADDED 
 *  2.2 Patient 
 *   TO BE ADDED
 *  2.3 Medical Professional
 *   TO BE ADDED
 *  2.4 Messaging Platform
 *   TO BE ADDED
 *  2.5 Patient Sign-Up
 *   TO BE ADDED 
 *  2.6 Patient Record Database
 *   TO BE ADDED 
 *   
 * 3. Further Notes
 * 	3.1  Github 
 *   Link to github repository: https://github.com/NicholasMAbate/CSE360_GroupProject49.git 
 *  
 *  
*/


package asuJavaFX360;

//import statements 
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Get screen dimensions from user computer 
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenX = screenBounds.getWidth();
        double screenY = screenBounds.getHeight();

        // Create LoginPortal instance and display
        LoginPortal loginPortal = new LoginPortal((int) screenX, (int) screenY);
        loginPortal.displayInterface();

        // Setup JavaFX stage
        primaryStage.setTitle("Portal Interface");
        primaryStage.setScene(new Scene(new StackPane(new Text("Portal Interface Displayed")), screenX, screenY));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



