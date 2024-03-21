/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.1
 * Original File Version: March 20, 2024
 * File Last Updated: March 20, 2024 
 * 
 * 1. File Description
 *  This is a helper file that allows for account storage and data storage.
 *  more information TO BE ADDED
 */

package asuJavaFX360;

//import statements 
import java.util.ArrayList;
import java.util.List;

public class Database {
	private List<HealthcareProvider> Clinic49_HealthcareProviders; //creating a List of all Clinic 49's Health care workers 
	
	public Database() { //constructor 
		Clinic49_HealthcareProviders = new ArrayList<>();
	}
	
	//main functionality methods 
	public void addHealthcareProvider(HealthcareProvider healthcareProvider) {
		Clinic49_HealthcareProviders.add(healthcareProvider);
	}
	
	public boolean authenticateHealthcareProvider(String username, String password) {
		for (HealthcareProvider provider : Clinic49_HealthcareProviders) {
			if(provider.getUsername().equals(username) && provider.getPassword().equals(password) ) {
				return true; //match found!
			}
		}
		
		return false; //no match
	}
}