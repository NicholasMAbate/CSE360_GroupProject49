/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.5
 * Original File Version: March 20, 2024
 * File Last Updated: April 10, 2024 
 * 
 * 1. File Description
 *  This is a helper file that allows for account storage and data storage. This is done by 
 *  storing the Patient objects and Health care Provider objects in their own respective array list then saving those 
 *  array lists onto individual .txt files within the root directory: CSE360Project.
 */

package asuJavaFX360;

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
     * This will allow for a new account after logging in to be correctly showed to the sign-up screen */
    public boolean isSignedUp(String username, String password) {
        for(Patient patient : Clinic49_Patients) {
            if(patient.getUsername().equals(username) && patient.getPassword().equals(password) && (patient.getFirstName() != null)) {
                return true; //account has been set up 
            }
        }
        return false; //account is not set up 
    }
	
	//Method to save the database as a text file 
    public void saveToFiles() { //When called it saves the database to the defined path.
        try { //prevents IO error by using try catch block
            
            // Defines primary path for file directory
            String basePath = System.getProperty("user.home") + "/Desktop/CSE360Project/";

            //writes HealthcareProviders to a text file: HealthcareProviders.txt
            PrintWriter healthcareProviderWriter = new PrintWriter(new FileWriter(basePath + "HealthCareProviders.txt"));
            for (HealthcareProvider provider : Clinic49_HealthcareProviders) {
                healthcareProviderWriter.println(provider.getUsername() + "," +
                    provider.getPassword() + "," +
                    provider.getFirstName() + "," +
                    provider.getLastName());
            }
            healthcareProviderWriter.close();

            //write Patients to .txt file: Patients.txt 
            PrintWriter patientWriter = new PrintWriter(new FileWriter(basePath + "Patients.txt"));
            for (Patient patient : Clinic49_Patients) {
                patientWriter.println(
                    patient.getPatientID() + "," +
                    patient.getUsername() + "," +
                    patient.getPassword() + "," +
                    patient.getFirstName() + "," +
                    patient.getLastName() + "," +
                    patient.getPhoneNumber() + "," +
                    patient.getAddress()  + "," +
                    patient.getAge()      + "," +
                    patient.getDOB()    + "," +
                    patient.getEmail() + "," +
                    patient.getInsuranceID() + "," +
                    patient.getMedicalHistory() + "," +
                    patient.getPharmacy());
            }
            patientWriter.close();
            
        } catch (IOException e) {//catches an IO exception :D 
            e.printStackTrace();
        }
    }

	//This method is the backworks for searching from a doctor's POV. It returns the Patient object if there is a match.
    public Patient getPatientByUsername(String username) {
        for (Patient patient : Clinic49_Patients) {
            if (patient.getUsername().equals(username)) {
                return patient;
            }
        }
        return null; // or throw an exception if preferred :)
    }
    
    public Patient getPatientByFirstAndLast(String fullname){
        for (Patient patient : Clinic49_Patients) {
            if (patient.getFullName().equals(fullname)) {
                return patient;
            }
        }
        return null; // or throw an exception if preferred :)
    }
    // load data from the text files
    public void loadFromFiles() {
        
        String basePath = System.getProperty("user.home") + "/Desktop/CSE360Project/";

        loadHealthcareProvidersFromFile(basePath + "HealthCareProviders.txt");
        loadPatientsFromFile(basePath + "Patients.txt");
    }

    
    //The following two methods are private methods called by the public method in order to load
    //the database stored within the different .txt files 
    
    // Load health care providers from HealthCareProviders.txt
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
    
    // Load patients from the Patients.txt file
    private void loadPatientsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Patient patient = new Patient();
                patient.setPatientID(parts[0]);
                patient.setUsername(parts[1]);
                patient.setPassword(parts[2]);
                patient.setFirstName(parts[3]);
                patient.setLastName(parts[4]);
                patient.setFullName(patient.getFirstName(), patient.getLastName());
                patient.setPhoneNumber(parts[5]);
                patient.setAddress(parts[6]);
                patient.setAge(Integer.parseInt(parts[7]));
                patient.setDOB(parts[8]);
                patient.setEmail(parts[9]);
                patient.setInsuranceID(parts[10]);
                patient.setMedicalHistory(parts[11]);
                patient.setPharmacy(parts[12]);
                Clinic49_Patients.add(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	 /*The following methods verify if the inputed Username and Password from the login screen
	 *return to a matching Health care Provider account or a Patient account. The both take in Strings 
	 *of username and password then return a boolean determining if the account exists within the list. */
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
			if(patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
					return true; //match found!
			}
		}
		
		return false; //no match
	}
	
	public boolean authenticateUniqueUsername(String username) {
		for(Patient patient : Clinic49_Patients) {
			if(patient.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/* This method returns a Patient object to a called username and password. This is called by the 
	 * Patient Registration and the Patient Portal to edit Patient information.  */
	public Patient patientToUpdate(String username, String password) {
		for (Patient patient : Clinic49_Patients) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                return patient; //did find given user
            }
        }
		return null; //did not find given user
	}
	
	/* This method returns a Health care Provider object to an inputed username and password. This is called 
	 * in the login portal in order to pass the correct health care provider to the Portal_HealthcareProviderPortal */
	public HealthcareProvider healthcareProviderToLogin(String username, String password) {
		for(HealthcareProvider provider : Clinic49_HealthcareProviders) {
			if(provider.getUsername().equals(username) && provider.getPassword().equals(password)) {
				return provider; //found provider with same username and password 
			}
		}
		
		return null; //did not find matching Health care provider 
	}
	
	
	
	//TEST METHOD TO BE DELTED 
	public void printAllUserNames() {
		for(Patient patient : Clinic49_Patients) {
			System.out.println(patient.getUsername());
        }
	}
}
