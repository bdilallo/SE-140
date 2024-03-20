package edu.depaul;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Initialize_Storage {
	
	String dir;
	
	Initialize_Storage() throws IOException{
		if (!createDir()) {return;}
		createEventLog();
		createCredentialLog();
		createProductLog();
	}

	public boolean createDir()
	{
		String storagePath = System.getProperty("user.dir")+"\\ProjectData_Dilallo";
		Path path = Paths.get(storagePath);
		if (Files.exists(path)) {
			System.out.println("Storage Folder Exists: "+ storagePath);
			return false;
		}
		dir = storagePath;
		new File(storagePath).mkdirs();
		System.out.println("A folder has been created here: "+dir);
		return true;
	}
	
	public void createCredentialLog() throws IOException
	{
		File Credentials =  new File(dir+"\\Credentials.txt");
		Credentials.createNewFile();
	}
	
	public void createProductLog() throws IOException
	{
		File Products = new File(dir+"\\Products.txt");
		Products.createNewFile();
	}
	
	public void createEventLog() throws IOException
	{
		File Events =  new File(dir+"\\Events.txt");
		Events.createNewFile();
	}
	
}
