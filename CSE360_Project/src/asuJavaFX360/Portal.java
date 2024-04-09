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
import javafx.stage.Screen;

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
