package edu.depaul;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Store_Interface {
	
	ArrayList<Product> catalog = new ArrayList<Product>();
	String user = "";
	String userCart = user+" Cart";
	Cart cart = new Cart(userCart, catalog, 0);
	Checkout_interface checkout_interface = new Checkout_interface();
	Event_Log event_Log = new Event_Log();
	
	Store_Interface(Scanner scan, String user, Product_Catalog product_catalog) throws NoSuchAlgorithmException, IOException{
		System.out.println("\nWelcome to the Store!");
		catalog = product_catalog.catalog;
		this.user = user;
		productDisplay(catalog);
		UserInterface(scan);
	}
			
	public void productDisplay(ArrayList<Product> catalog) {
		System.out.print("\nProduct Catalog\n");
		System.out.print("---------------------------------------------------------\n");
		for (int i = 0; i < catalog.size(); i++) {
			
			if (catalog.get(i).getClass() == Electronics.class) {
				Electronics electronics = (Electronics) catalog.get(i);
				System.out.printf("ID: %-2d  |  Item: %-16s %-5s Price: %.2f\t|\n", i, electronics.getName(), "|",  electronics.getPrice());
			}
			if (catalog.get(i).getClass() == Food.class) {
				Food food = (Food) catalog.get(i);
				System.out.printf("ID: %-2d  |  Item: %-16s %-5s Price: %.2f\t|\n", i, food.getName(), "|", food.getPrice());
			}
			if (catalog.get(i).getClass() == Sports.class) {
				Sports sports = (Sports) catalog.get(i);
				System.out.printf("ID: %-2d  |  Item: %-16s %-5s Price: %.2f\t|\n", i, sports.getName(), "|", sports.getPrice());
				
			}
		}
		System.out.print("---------------------------------------------------------\n");
	}
	
	public void UserInterface(Scanner scan) throws NoSuchAlgorithmException, IOException {
		
		String CMDs = "   Command     || Function" 
	        	+ "\n---------------------------------------------"
	        	+ "\n -   (1)       || Add Item To Cart" 
	        	+ "\n -   (2)       || Remove Item From Cart"
	        	+ "\n -   (3)       || View Cart" 
	        	+ "\n -   (4)       || Proceed to Checkout"
	        	+ "\n -   (5)       || View Catalog" 
	        	+ "\n -   (6)       || Logout"
	        	+ "\n - (escape)    || Abort Input"
	        	+ "\n---------------------------------------------\n";

		System.out.println("\n" + CMDs);
		
		while (true) {
			String input = scan.nextLine();

			switch (input) {

			case "1":
				System.out.printf("Please input Item ID: ");
				input = scan.nextLine();
				if(!Validate_Input.val(input,0)) {break;}
				int pos = Integer.parseInt(input);
				if(!Validate_ID.val(pos,catalog.size())) {break;}
				
				if (catalog.get(pos).getClass() == Electronics.class) {
					ArrayList<Product> tempCart = new ArrayList<Product>();
					tempCart = cart.getContents();
					Electronics electronics = (Electronics) catalog.get(pos);
					tempCart.add(electronics);
					cart.setName(electronics.getName());
					cart.setContents(tempCart);
					cart.setPriceTotal(electronics.getPrice()+cart.getPriceTotal());
					System.out.println("--Item Added to Cart--");
				}
				if (catalog.get(pos).getClass() == Food.class) {
					ArrayList<Product> tempCart = new ArrayList<Product>();
					tempCart = cart.getContents();
					Food food = (Food) catalog.get(pos);
					tempCart.add(food);
					cart.setName(food.getName());
					cart.setContents(tempCart);
					cart.setPriceTotal(food.getPrice()+cart.getPriceTotal());
					System.out.println("--Item Added to Cart--");
				}
				if (catalog.get(pos).getClass() == Sports.class) {
					ArrayList<Product> tempCart = new ArrayList<Product>();
					tempCart = cart.getContents();
					Sports sports = (Sports) catalog.get(pos);
					tempCart.add(sports);
					cart.setName(sports.getName());
					cart.setContents(tempCart);
					cart.setPriceTotal(sports.getPrice()+cart.getPriceTotal());
					System.out.println("--Item Added to Cart--");
				}
				break;

			case "2":
				if (cart.getContents().size() == 0){System.out.println("Cart is Empty"); break;}
				System.out.printf("Item ID of Product to Remove: ");
				input = scan.nextLine();
				if(!Validate_Input.val(input,0)) {break;}
				pos = Integer.parseInt(input);
				if(!Validate_ID.val(pos,cart.getContents().size())) {break;}
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
				System.out.printf("--Product Removed--");
				break;
				
			case "3":
				if (cart.getContents().size() == 0) {System.out.println("--Cart is Empty--"); break;}
				System.out.printf("--Contents of Cart--\n");
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
				break;
				
			case "4":
				if (cart.getContents().size() == 0) {System.out.println("--Cart is Empty--"); break;}
				checkout_interface.UserInterface(user, cart, scan);
				System.out.println("\nWelcome to the Store");
				System.out.println("\n" + CMDs);
				break;
				
			case "5":
				productDisplay(catalog);
				break;
			
			case "6":
				String event = user+" "+"Logged Out";
				event_Log.eventLogger(event);
				return;
			}
		}
	}
}
