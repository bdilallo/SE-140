package edu.depaul;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class User_Auth {
	
	Credential_Storage credential_data;
	Event_Log event_Log = new Event_Log();
	
	User_Auth() throws FileNotFoundException{
		Credential_Storage credential_data = new Credential_Storage();
		this.credential_data = credential_data;
	}
	
	String user = "";
	
	public void access(Scanner scan) throws IOException, NoSuchAlgorithmException{
		Product_Catalog product_catalog = new Product_Catalog();
		Product_Factory product_factory = new Product_Factory(product_catalog);
		Store_Interface store_interface = new Store_Interface(scan, user, product_catalog);
	}
	
	public boolean existingUser(Scanner scan) throws NoSuchAlgorithmException, IOException {
		String ID;
		String Password;
			
		System.out.printf("User ID: ");
		ID = scan.nextLine();
		if (ID.equalsIgnoreCase("escape")){
			System.out.println("Input Aborted");
			return false;
		}
		
		System.out.printf("User Password: ");
		Password = scan.nextLine();
		if (Password.equalsIgnoreCase("escape")){
			System.out.println("Input Aborted");
			return false;
		}
		
		if (credential_data.validate(ID, Password)) {
			System.out.println("Logging in...");
			String event = ID+" "+"Logged in";
			event_Log.eventLogger(event);
			user = ID;
			return true;
		}
		
		else {
			System.out.println("Invalid Username or Password");
			event_Log.eventLogger("Failed Login");
			return false;
		}
	}
	
	public boolean createUser(Scanner scan) throws NoSuchAlgorithmException, IOException {
		String ID;
		String Password;
		
		System.out.printf("User ID: ");
		ID = scan.nextLine();
		if (ID.equalsIgnoreCase("escape")){
			System.out.println("Input Aborted");
			return false;
		}
		
		System.out.printf("User Password: ");
		Password = scan.nextLine();
		if (Password.equalsIgnoreCase("escape")){
			System.out.println("Input Aborted");
			return false;
		}
		
		System.out.println("Processing...");
		
		if (credential_data.validate(ID, Password)) {
			System.out.println("User Exists, Logging in...");
			return true;
		}
		
		credential_data.addUser(ID, Password);
		String event = ID+" "+"Account Created";
		event_Log.eventLogger(event);
		return false;
	}
	
	public void displayUsers() {
		credential_data.displayAccounts();
	}
}
