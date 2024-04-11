/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.3
 * Original File Version: March 20, 2024
 * File Last Updated: April 9, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Patient class. The Patient class stores vital 
 *  information about the patient for the clinic to know. The patient is the general account 
 *  for those who are looking to use the clinic's system. The patient is only able to edit information about itself
 *  and only able to look at information about itself.   
 */

package asuJavaFX360;

class Patient {
	//Initializing all attributes to null 
	private String firstName = null;
	private String lastName = null;
	private String fullName = null;
	private String username = null;
	private String password = null;
	private String patientID = null;
	private String email = null;
	private String medicalHistory = null;
	private String phoneNumber = null;
	private int age;
	private String address = null;
	private String DOB = null;
	private String InsuranceID = null;
	private String Pharmacy = null;
	private boolean isSetup = false; //only turned true when all other attributes have been correctly initialized 
	
	public void setIsSetup() {
		if (this.firstName != null) {
			isSetup = true; //if the first name is has been made then it is determined that RegistrationPortal has completed for this patient
		}
	}
	//helper methods to setup all attributes with correct information 
	public void setFirstName(String name) { 
		this.firstName = name;
	}
	
	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public void setFullName(String first, String last) {
		this.fullName = first + " " + last;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setEmail(String mail) {
		this.email = mail;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setMedicalHistory(String history) {
		this.medicalHistory = history;
	}
	
	public void setPhoneNumber(String pNum) {
		this.phoneNumber = pNum;
	}
	
	public void setDOB(String dob) {
		this.DOB = dob;
	}
	
	public void setInsuranceID(String ID) {
		this.InsuranceID = ID;
	}
	
	public void setPharmacy(String pharmacy) {
		this.Pharmacy = pharmacy;
	}
	
	public void setPatientID(String id) {
		this.patientID = id;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	//helper methods to get attributes 
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	//MIGHT NEED TO BE DELETED 
	public boolean checkCredentials(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }
	
	public boolean getIsSetup() {
		return this.isSetup;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getDOB() {
		return this.DOB;
	}
	
	public String getMedicalHistory() {
		return this.medicalHistory;
	}
	
	public String getPharmacy() {
		return this.Pharmacy;
	}
	
	public String getInsuranceID() {
		return this.InsuranceID;
	}
	
	public String getPatientID() {
		return this.patientID;
	}
	
	
	//THE FOLLOWING IS A TEST METHOD TO BE DELETED BEFORE FINAL PRODUCT 
	public void printAll() {
		System.out.println(this.firstName);
		System.out.println(this.lastName);
		System.out.println(this.username);
		System.out.println(this.password);
	}
	
}