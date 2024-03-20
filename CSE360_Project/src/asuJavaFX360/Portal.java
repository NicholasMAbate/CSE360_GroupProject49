/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0
 * Original File Version: March 19, 2024
 * File Last Updated: March 19, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Portal classes. The Portal classes display the different portals
 *  (also known as Screens) of the coding system 
 */







package asuJavaFX360;
abstract class Portal {
    protected int xDimension;
    protected int yDimension;

    public Portal(int xDimension, int yDimension) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    public abstract void displayInterface();
}

class PatientPortal extends Portal {
    public PatientPortal(int xDimension, int yDimension) {
        super(xDimension, yDimension);
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying Patient Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to PatientPortal
    }
}

class HealthcareProviderPortal extends Portal {
    public HealthcareProviderPortal(int xDimension, int yDimension) {
        super(xDimension, yDimension);
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying Healthcare Provider Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to HealthcareProviderPortal
    }
}

class LoginPortal extends Portal {
    public LoginPortal(int xDimension, int yDimension) {
        super(xDimension, yDimension);
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying Login Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to LoginPortal
    }
}

class SignUpPortal extends Portal {
    public SignUpPortal(int xDimension, int yDimension) {
        super(xDimension, yDimension);
    }

    @Override
    public void displayInterface() {
        System.out.println("Displaying Sign Up Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to SignUpPortal
    }
}
