package edu.depaul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Checkout_interface {
	
	Payment_Processing payment_processing = new Payment_Processing();
	
	public void cartDisplay(Cart cart) {
		System.out.printf("--Contents of Your Cart--\n");
		for (int i = 0; i < cart.getContents().size(); i++) {
			
			if (cart.getContents().get(i).getClass() == Electronics.class) {
				Electronics electronics = (Electronics) cart.getContents().get(i);
				System.out.printf("ID: %-2d  |  Item: %-16s %-5s Price: %.2f\t|\n", i, electronics.getName(), "|",  electronics.getPrice());
			}
			if (cart.getContents().get(i).getClass() == Food.class) {
				Food food = (Food) cart.getContents().get(i);
				System.out.printf("ID: %-2d  |  Item: %-16s %-5s Price: %.2f\t|\n", i, food.getName(), "|", food.getPrice());
			}
			if (cart.getContents().get(i).getClass() == Sports.class) {
				Sports sports = (Sports) cart.getContents().get(i);
				System.out.printf("ID: %-2d  |  Item: %-16s %-5s Price: %.2f\t|\n", i, sports.getName(), "|", sports.getPrice());
				
			}
		}
		System.out.printf("Subtotal: %.2f\n", cart.getPriceTotal());
		System.out.printf("--End Cart--:\n");
	}
	
	public void UserInterface(String user, Cart cart, Scanner scan) throws IOException {
		System.out.println("\nWelcome to Checkout");
		String CMDs = "   Command     || Function" 
	        	+ "\n---------------------------------------------"
	        	+ "\n -   (1)       || Proceed with Payment" 
	        	+ "\n -   (2)       || Remove Item From Cart"
	        	+ "\n -   (3)       || View Cart" 
	        	+ "\n -   (4)       || Go back"
	        	+ "\n - (escape)    || Abort Input"
	        	+ "\n---------------------------------------------\n";

		System.out.println("\n" + CMDs);

		while (true) {

			String input = scan.nextLine();

			switch (input) {

			case "1":
				if (payment_processing.UserInterface(user, scan)) {
					ArrayList<Product> clear = new ArrayList<Product>();
					cart.setContents(clear);
					cart.setPriceTotal(0);
				}
				System.out.println("\nWelcome to Checkout");
				System.out.println("\n" + CMDs);
				break;

			case "2":
				if (cart.getContents().size() == 0){System.out.println("Your Cart is Empty"); break;}
				removeItem(scan, cart);
				break;
				
			case "3":
				if (cart.getContents().size() == 0){System.out.println("Your Cart is Empty"); break;}
				cartDisplay(cart);
				break;
			
			case "4":
				return;
			}
		}
	}
	
	public void removeItem(Scanner scan, Cart cart) {
		String input = "";
		int pos = -1;
		System.out.printf("Item ID of Product to Remove: ");
		input = scan.nextLine();
		if(!Validate_Input.val(input,0)) {return;}
		pos = Integer.parseInt(input);
		if(!Validate_ID.val(pos,cart.getContents().size())) {return;}
		double priceDown = 0;
		
		if (cart.getContents().get(pos).getClass() == Electronics.class) {
			Electronics electronics = (Electronics) cart.getContents().get(pos);
			priceDown = electronics.getPrice();
		}
		if (cart.getContents().get(pos).getClass() == Food.class) {
			Food food = (Food) cart.getContents().get(pos);
			priceDown = food.getPrice();
		}
		if (cart.getContents().get(pos).getClass() == Sports.class) {
			Sports sports = (Sports) cart.getContents().get(pos);
			priceDown = sports.getPrice();
		}
		cart.getContents().remove(pos);
		double temp = cart.getPriceTotal();
		temp = temp - priceDown;
		cart.setPriceTotal(temp);
		System.out.printf("--Product Removed--\n");
		cartDisplay(cart);
	}
}
