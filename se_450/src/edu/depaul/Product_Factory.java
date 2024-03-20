package edu.depaul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Product_Factory {
	
	ArrayList<Product> catalog = new ArrayList<Product>();
	ArrayList<String> ElectronicsNames = new ArrayList<String>();
	ArrayList<String> FoodNames = new ArrayList<String>();
	ArrayList<String> SportsNames = new ArrayList<String>();
	int ElectronicsID = 99;
	int FoodID = 199;
	int SportsID = 299;
	
	Product_Factory(Product_Catalog product_catalog) throws IOException{
		if (product_catalog.catalog.size() == 0) {
			populateNames();
			generateProducts();
			passCatalog(product_catalog);
		}
	}
	
	public void generateProducts() {
		
		for (int i = 0; i < 20; i++) {
			int randomType = ThreadLocalRandom.current().nextInt(1, 4);
			double randomPrice = 1;
			int ranNum = -1;
			String randomName = "";
			int id = -1;
			
			
			switch(randomType){
				case 1:
					randomPrice = ThreadLocalRandom.current().nextDouble(50, 300);
					ranNum = ThreadLocalRandom.current().nextInt(0, ElectronicsNames.size());
					randomName = ElectronicsNames.get(ranNum);
					ElectronicsNames.remove(ranNum);
					ElectronicsID++;
					id = ElectronicsID;
					break;
					
				case 2:
					randomPrice = ThreadLocalRandom.current().nextDouble(1, 10);
					ranNum = ThreadLocalRandom.current().nextInt(0, FoodNames.size());
					randomName = FoodNames.get(ranNum);
					FoodNames.remove(ranNum);
					FoodID++;
					id = FoodID;
					break;
					
				case 3:
					randomPrice = ThreadLocalRandom.current().nextDouble(20, 100);
					ranNum = ThreadLocalRandom.current().nextInt(0, SportsNames.size());
					randomName = SportsNames.get(ranNum);
					SportsNames.remove(ranNum);
					SportsID++;
					id = SportsID;
					break;
				
			}
			
			productType(randomType, randomName, id, randomPrice);
		}
	}
	
	public void productType(int type, String name, int productID, double price) {
		
		//Electronics
		if (type == 1) {
			catalog.add(new Electronics(name, productID, price));
		}
		
		//Food
		if (type == 2) {
			catalog.add(new Food(name, productID, price));
		}
		
		//Sports
		if (type == 3) {
			catalog.add(new Sports(name, productID, price));
		}
	}
	
	public void passCatalog(Product_Catalog product_catalog) throws IOException {
		product_catalog.uploadCat(catalog);
	}
	
	public void populateNames() {
		ElectronicsNames.add("Toaster"); 
		ElectronicsNames.add("Headphones"); 
		ElectronicsNames.add("Camera"); 
		ElectronicsNames.add("Microwave"); 
		ElectronicsNames.add("Cell Phone"); 
		ElectronicsNames.add("Remote Control"); 
		ElectronicsNames.add("Printer"); 
		ElectronicsNames.add("Razor"); 
		ElectronicsNames.add("Television"); 
		ElectronicsNames.add("Laptop"); 
		ElectronicsNames.add("Speakers"); 
		ElectronicsNames.add("Projector"); 
		ElectronicsNames.add("Game Console"); 
		ElectronicsNames.add("Computer Mouse"); 
		ElectronicsNames.add("SSD"); 
		ElectronicsNames.add("Space Heater"); 
		ElectronicsNames.add("Desk Lamp");
		ElectronicsNames.add("Computer Monitor");
		ElectronicsNames.add("Wifi Adapater");
		ElectronicsNames.add("Electric Kettle");
		
		FoodNames.add("Apples");
		FoodNames.add("Bananas");
		FoodNames.add("Potatoes");
		FoodNames.add("Oranges");
		FoodNames.add("Chips");
		FoodNames.add("Brownies");
		FoodNames.add("Cookies");
		FoodNames.add("Cake");
		FoodNames.add("Onion Rings");
		FoodNames.add("Pudding");
		FoodNames.add("Coffee");
		FoodNames.add("Donuts");
		FoodNames.add("Pie");
		FoodNames.add("Pizza");
		FoodNames.add("Sushi");
		FoodNames.add("Ice Cream");
		FoodNames.add("Bread");
		FoodNames.add("Pasta");
		FoodNames.add("Milk");
		FoodNames.add("Biscuits");
		
		SportsNames.add("Baseball Ball");
		SportsNames.add("Soccer Ball");
		SportsNames.add("Baseball Bat");
		SportsNames.add("Shin Guards");
		SportsNames.add("Helmet");
		SportsNames.add("Hockey Puck");
		SportsNames.add("Hockey Stick");
		SportsNames.add("Ice Skates");
		SportsNames.add("Kite");
		SportsNames.add("Skateboard");
		SportsNames.add("Knee Pads");
		SportsNames.add("Tennis Ball");
		SportsNames.add("Tennis Racket");
		SportsNames.add("Gloves");
		SportsNames.add("Bicycle");
		SportsNames.add("Skis");
		SportsNames.add("Snowboard");
		SportsNames.add("Gym Shoes");
		SportsNames.add("Backpack");
		SportsNames.add("Sled");
			
	}
}
