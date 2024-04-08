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
import java.io.*;

public class Database {
	private List<HealthcareProvider> Clinic49_HealthcareProviders; //creating an array List of type Health care providers
	private List<Patient> Clinic49_Patients; //creating an array list of type Patient
	
	public Database() { //constructor 
		Clinic49_HealthcareProviders = new ArrayList<>();
		Clinic49_Patients = new ArrayList<>();
	}
	
	//main functionality methods 
	
	//Takes in a HealthcareProvider and adds it to the array list of HealthCareProviders, returns nothing
	public void addHealthcareProvider(HealthcareProvider healthcareProvider) { 
		Clinic49_HealthcareProviders.add(healthcareProvider);
	}
	
	//Takes in a Patient and adds it to the array list of patients, returns nothing 
	public void addPatient(Patient patient) {
		Clinic49_Patients.add(patient);
	}
	
	/* The following method will take in a patient account and check if it has been fully setup
     * It will return a boolean after searching for the account and if it has been fully signed up. 
     * This will allow for a new account after logging in to be correctly showed the sign-up screen */
    
    public boolean isSignedUp(String username, String password) {
        for(Patient patient : Clinic49_Patients) {
            if(patient.getUsername().equals(username) && patient.getPassword().equals(password) && (patient.getIsSetup() == true ) ) {
                return true; //account has been set up 
            }
        }
        return false; //account is not set up 
    }
	
	//Method to save the database as a text file 
	public void saveToFiles() { //When called it saves the database to the desktop of the computer system.
		try { //prevents IO error by using try catch block
			
			//write HealthcareProviders to HealthcareProviders.txt
			PrintWriter healthcareProviderWriter = new PrintWriter(new FileWriter(System.getProperty("user.home") +"/Desktop/CSE360Project/HealthCareProviders.txt"));
	        for (HealthcareProvider provider : Clinic49_HealthcareProviders) {
	            healthcareProviderWriter.println(provider.getUsername() + "," +
	                provider.getPassword() + "," +
	                provider.getFirstName() + "," +
	                provider.getLastName());
	        }
	        healthcareProviderWriter.close();

			//write Patients to Patients.txt 
	        PrintWriter patientWriter = new PrintWriter(new FileWriter(System.getProperty("user.home") + "/Desktop/CSE360Project/Patients.txt"));
	        for (Patient patient : Clinic49_Patients) {
	            patientWriter.println(patient.getUsername() + "," +
	                patient.getPassword() + "," +
	                patient.getFirstName() + "," +
	                patient.getLastName());
	        }
	        patientWriter.close();
	        
		} catch (IOException e) {//catches an IO exception :D 
			e.printStackTrace();
		}
	}
	
    // Public Method to load data from text files
    public void loadFromFiles() {
        loadHealthcareProvidersFromFile(System.getProperty("user.home") + "/Desktop/CSE360Project/HealthCareProviders.txt");
        loadPatientsFromFile(System.getProperty("user.home") + "/Desktop/CSE360Project/Patients.txt");
    }
    
    //The following two methods are private methods called by the public method in order to load
    //the database stored within the different .txt files 
    
    // Load healthcare providers from HealthCareProviders.txt
    private void loadHealthcareProvidersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                HealthcareProvider provider = new HealthcareProvider();
                provider.setUsername(parts[0]);
                provider.setPassword(parts[1]);
                provider.setFirstName(parts[2]);
                provider.setLastName(parts[3]);
                Clinic49_HealthcareProviders.add(provider);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Load patients from Patients.txt
    private void loadPatientsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Patient patient = new Patient();
                patient.setUsername(parts[0]);
                patient.setPassword(parts[1]);
                patient.setFirstName(parts[2]);
                patient.setLastName(parts[3]);
                Clinic49_Patients.add(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	/*
	 *The following methods verify if the inputed Username and Password from the login screen
	 *return to a matching Healthcare Provider account or a Patient account. The both take in Strings 
	 *of username and password then return a boolean determining if the account exists within the list.  
	 */
	public boolean authenticateHealthcareProvider(String username, String password) {
		for (HealthcareProvider provider : Clinic49_HealthcareProviders) {
			if(provider.getUsername().equals(username) && provider.getPassword().equals(password) ) {
				return true; //match found!
			}
		}
		
		return false; //no match
	}
		
	public boolean authenticatePatient(String username, String password) {
		for (Patient patient : Clinic49_Patients) {
			if(patient.getUsername().equals(username) && patient.getPassword().equals(password) && patient.getIsSetup()) {
					return true; //match found!
			}
		}
		
		return false; //no match
	}
}