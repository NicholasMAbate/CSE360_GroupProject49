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

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class HealthcareProviderPortal extends Portal {
	private Database database;
	
    public HealthcareProviderPortal(Database database) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
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