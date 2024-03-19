package edu.depaul;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Product_Catalog {
	
	Event_Log event_Log = new Event_Log();
	Product_Catalog () throws IOException{
			collect();
			}

	public ArrayList<Product> catalog = new ArrayList<Product>();

	public void collect() throws IOException {
			String storagePath = System.getProperty("user.dir")+"\\ProjectData_Dilallo";
			String credPath = storagePath+"\\Products.txt";
			File credDoc = new File(credPath);
			Scanner scan = new Scanner(credDoc);
			
			while(scan.hasNextLine()) {
				String Line = scan.nextLine();
				String[] cred = Line.split("-");
				int id = Integer.parseInt(cred[1]);
				double price = Double.parseDouble(cred[2]);
				
				if (id >= 100 && id < 200) {catalog.add(new Electronics (cred[0], id, price));}
				if (id >= 200 && id < 300) {catalog.add(new Food (cred[0], id, price));}
				if (id >= 300) {catalog.add(new Sports (cred[0], id, price));}
			}
			scan.close();
			if (catalog.size() != 0 ) {event_Log.eventLogger("Products Loaded From Storage");}
	}
	
	public void uploadCat(ArrayList<Product> catalog) throws IOException {
		String storagePath = System.getProperty("user.dir")+"\\ProjectData_Dilallo";
		storagePath = storagePath+"\\Products.txt";
		FileWriter fileWrite = new FileWriter(storagePath);
		BufferedWriter buf = new BufferedWriter(fileWrite);
		
		for (int i = 0; i < catalog.size(); i++) {
			
			if (catalog.get(i).getClass() == Electronics.class) {
				Electronics electronics = (Electronics) catalog.get(i);
				String line = electronics.getName()+"-"+String.valueOf(electronics.getProductID())+"-"+String.valueOf(electronics.getPrice())+"\n";
				buf.write(line);
			}
			if (catalog.get(i).getClass() == Food.class) {
				Food food = (Food) catalog.get(i);
				String line = food.getName()+"-"+String.valueOf(food.getProductID())+"-"+String.valueOf(food.getPrice())+"\n";
				buf.write(line);
			}
			if (catalog.get(i).getClass() == Sports.class) {
				Sports sports = (Sports) catalog.get(i);
				String line = sports.getName()+"-"+String.valueOf(sports.getProductID())+"-"+String.valueOf(sports.getPrice()+"\n");
				buf.write(line);
				
			}
		}
		buf.close();
		event_Log.eventLogger("Products Written to Storage");
		collect();
	}
}
