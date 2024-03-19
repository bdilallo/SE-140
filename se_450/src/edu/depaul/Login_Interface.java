package edu.depaul;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login_Interface {
	
	Event_Log event_Log = new Event_Log();
	
	Login_Interface() throws NoSuchAlgorithmException, IOException {
		User_Auth user_auth = new User_Auth();
		UserInterface(user_auth);
	}
	
	public void UserInterface(User_Auth user_auth) throws NoSuchAlgorithmException, IOException {

		String CMDs = "   Command     || Function" 
		        	+ "\n---------------------------------------------"
		        	+ "\n -   (1)       || User Login" 
		        	+ "\n -   (2)       || Create Account"
		        	+ "\n -   (3)       || View Existing Accounts" 
		        	+ "\n -   (4)       || Event Log" 
		        	+ "\n -   (5)       || Exit Program" 
		        	+ "\n - (escape)    || Abort Input"
		        	+ "\n---------------------------------------------\n";
		
		System.out.println("\n" + CMDs);

		while (true) {

			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();

			switch (input) {

			case "1":
				if(user_auth.existingUser(scan)) {
					user_auth.access(scan);
					System.out.printf("--Logged Out--:\n");
					System.out.println("\n" + CMDs);
				}
				break;

			case "2":
				
				if(user_auth.createUser(scan)) {
					user_auth.access(scan);
					System.out.printf("--Logged Out--:\n");
					System.out.println("\n" + CMDs);
				}
				break;
				
			case "3":
				user_auth.displayUsers();
				break;
				
			case "4":
				event_Log.display();
				break;
			
			case "5":
				scan.close();
				return;
			}
		}
	}
}
