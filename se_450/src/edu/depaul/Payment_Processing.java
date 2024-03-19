package edu.depaul;

import java.io.IOException;
import java.util.Scanner;

public class Payment_Processing {
	
	Event_Log event_Log = new Event_Log();
	
	private void display(String name, int cardNumber, int cvv, String expiration, String address, String phone) {
		System.out.printf("\nDoes Everything Look Correct?\n"
				+ "----------------------------------------------------\n"
				+ "Name: %s\n"
				+ "Card Number: %d\n"
				+ "CVV Number: %d\n"
				+ "Expiration Date: %s\n"
				+ "Address: %s\n"
				+ "Phone Number: %s\n",
				name, cardNumber, cvv, expiration, address, phone);
	}

	public boolean UserInterface(String user, Scanner scan) throws IOException {
		
		String name = "";
		int cardNumber = -1;
		int cvv = -1;
		String expiration = "";
		String address = "";
		String phone = "";

		System.out.println("Thank you for Shopping");
		System.out.println("Type (escape) to abort Payment");

		while (true) {

			System.out.printf("Please input First and Last Name: ");
			String input = scan.nextLine();
			if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--"); return false;}
			name = input;
			
			System.out.printf("Please input card number: ");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
			if(!Validate_Input.val(input,0)) {
				while (true) {
					System.out.printf("Please input card number: ");
					input = scan.nextLine();
					if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
					if (Validate_Input.val(input,0)) {break;}
				}
			}
			cardNumber = Integer.parseInt(input);
			
			System.out.printf("Please input CVV: ");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
			if(!Validate_Input.val(input,0) || input.length() != 3) {
				while (true) {
					System.out.printf("--Must be Proper CVV (000)--\n");
					System.out.printf("Please input CVV: ");
					input = scan.nextLine();
					if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
					if (Validate_Input.val(input,0) && input.length() == 3) {break;}
				}
			}
			cvv = Integer.parseInt(input);
			
			String tempExp = "";
			System.out.printf("Please input Expiration date MONTH (mm): ");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
			if(!Validate_Input.val(input,0) || Integer.parseInt(input) > 12 || Integer.parseInt(input) < 1 || input.length() != 2) {
				while (true) {
					System.out.printf("--Must be Proper Month--\n");
					System.out.printf("Please input Expiration date MONTH (mm): ");
					input = scan.nextLine();
					if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
					if (Validate_Input.val(input,0) && Integer.parseInt(input) < 13 && Integer.parseInt(input) > 0 && input.length() == 2) {break;}
				}
			}
			tempExp = input;
			System.out.printf("Please input Expiration date DAY (dd): ");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
			if(!Validate_Input.val(input,0) || Integer.parseInt(input) > 31 || Integer.parseInt(input) < 1 || input.length() != 2) {
				while (true) {
					System.out.printf("--Must be Proper Day--\n");
					System.out.printf("Please input Expiration date Day (dd): ");
					input = scan.nextLine();
					if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
					if (Validate_Input.val(input,0) && Integer.parseInt(input) < 31 && Integer.parseInt(input) > 1 && input.length() == 2) {break;}
				}
			}
			
			expiration = tempExp+"/"+input;
			
			System.out.printf("Please input Address: ");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
			address = input;
			
			System.out.printf("Please input Phone Number (0000000000): ");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
			if(!Validate_Input.val(input,1) || input.length() != 10) {
				while (true) {
					System.out.printf("--Must be Correct Length--\n");
					System.out.printf("Please input Phone number: ");
					input = scan.nextLine();
					if (input.equalsIgnoreCase("escape")) {System.out.println("--Input Aborted--");return false;}
					if (Validate_Input.val(input,1) && input.length() == 10) {break;}
				}
			}
			
			phone = input.substring(0, 3)+"-"+input.substring(3, 6)+"-"+input.substring(6, 10);
			display(name, cardNumber, cvv, expiration, address, phone);
			System.out.printf("----------------------------------------------------\nProceed with Payment (Y/N): ");
			input = scan.nextLine();
			
			if (input.equalsIgnoreCase("Y")) {
				System.out.printf("Thank You for Shopping With Us, Your order has been placed.\n");
				String event = user+" "+"Placed Order";
				event_Log.eventLogger(event);
				return true;
			}
			else {
				System.out.printf("Returning to Checkout");
				return false;
			}
		}
	}
}
