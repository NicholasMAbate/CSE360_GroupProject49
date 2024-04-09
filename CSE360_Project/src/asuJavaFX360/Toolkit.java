/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.1
 * Original File Version: March 20, 2024
 * File Last Updated: March 20, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the extra class that do not fit in any other file. 
 *  Most of these classes will perform assistance with functionality of other classes. 
 *  
 *  more information TO BE ADDED
 */

package asuJavaFX360;

class accountChecker {
	public accountChecker() {}
	
	/*This method checks if the login is a valid Health care provider within the system
	 * Returns a boolean that only returns true if and only if the inputed username AND password 
	 * match a valid username and password for a Health care provider account */
	public boolean isValidUserLoginHealthcareProvider(String username, String password, Database database) { 
		 return(database.authenticateHealthcareProvider(username, password));

	}
	
	public boolean isValidUserLoginPatient(String username, String password, Database database) { 
		 return(database.authenticatePatient(username, password));

	}
	
	/*
     * This method takes in a database, a username, and a password to determine 
     * if the inputed account has be correctly setup. If it has then it will return true 
     * if it has not then it will return false */
    public boolean isSignedUp(String username, String password, Database database) {
    	System.out.println(database.isSignedUp(username, password));//test line TO BE DELETED 
        return(database.isSignedUp(username, password));
    }
}