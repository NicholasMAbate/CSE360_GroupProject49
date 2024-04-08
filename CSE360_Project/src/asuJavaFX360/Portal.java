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