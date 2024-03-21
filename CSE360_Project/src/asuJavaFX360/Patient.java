/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.2
 * Original File Version: March 20, 2024
 * File Last Updated: March 20, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Patient class. The Patient class stores vital 
 *  information about the patient (TO BE ADDED)  
 */

package asuJavaFX360;

class Patient {
	//Initializing all attributes to null 
	private String firstName = null;
	private String lastName = null;
	private String username = null;
	private String password = null;
	private String email = null;
	private String medicalHistory = null;
	private int phoneNumber = Integer.MIN_VALUE;
	private boolean isSetup = false; //only turned true when all other attributes have been correctly initialized 
	
	
	
	//helper methods to setup all attributes with correct information 
	public void setFirstName(String name) { //takes in a String: name, then assigns it to the firstName 
		this.firstName = name;
	}
	
	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	
	
	//further implementation required! 
	
	
	//THE FOLLOWING IS A TEST METHOD TO BE DELETED BEFORE FINAL PRODUCT 
	public void printAll() {
		System.out.println(this.firstName);
		System.out.println(this.lastName);
		System.out.println(this.username);
		System.out.println(this.password);
		
	}
	
}