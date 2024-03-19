package edu.depaul;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

class userData {
	String id;
	String pass;
	
	public userData(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}
}

public class Credential_Storage {
	
	Credential_Storage() throws FileNotFoundException{
		collect();
	}
	ArrayList<userData> login_database = new ArrayList<userData>();
	
	private void collect() throws FileNotFoundException {
		String storagePath = System.getProperty("user.dir")+"\\ProjectData_Dilallo";
		String credPath = storagePath+"\\Credentials.txt";
		File credDoc = new File(credPath);
		Scanner scan = new Scanner(credDoc);
		
		while(scan.hasNextLine()) {
			String Line = scan.nextLine();
			String[] cred = Line.split("-");
			login_database.add(new userData (cred[0],cred[1]));
		}
		scan.close();
	}
	
	public boolean validate(String ID, String Password) throws NoSuchAlgorithmException {
		
		for (int i = 0; i < login_database.size(); i++) {
			if(login_database.get(i).id.contentEquals(ID)) {
				String val = Encrypter(Password);
				if (login_database.get(i).pass.contentEquals(val)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public void addUser(String ID, String Password) throws NoSuchAlgorithmException, IOException {
		
		for (int i = 0; i < login_database.size(); i++) {
			if(login_database.get(i).id.contentEquals(ID)) {
				System.out.println("UserName Taken");
				return;
			}
		}
		
		Password = Encrypter(Password);
		login_database.add(new userData (ID, Password));
		docUpdate();
		System.out.println("Account Created");
	}
	
	private void docUpdate() throws IOException {
		String storagePath = System.getProperty("user.dir")+"\\ProjectData_Dilallo";
		storagePath = storagePath+"\\Credentials.txt";
		FileWriter fileWrite = new FileWriter(storagePath);
		BufferedWriter buf = new BufferedWriter(fileWrite);
		
		for (int i = 0; i < login_database.size(); i++) {
			String line = login_database.get(i).id+"-"+login_database.get(i).pass+"\n";
			buf.write(line);
		}
		buf.close();
	}
	
	
	private String Encrypter(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] mdInput = md.digest(password.getBytes());
		BigInteger converted = new BigInteger(1, mdInput);
		return converted.toString();
	}
	
	public void displayAccounts() {
		if (login_database.size() == 0) {System.out.println("--No Accounts--");}
		for (int i = 0; i<login_database.size(); i++) {
			System.out.println(login_database.get(i).id+" "+login_database.get(i).pass);
		}
	}
}
