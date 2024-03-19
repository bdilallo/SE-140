package edu.depaul;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Event_Log {
	
	ArrayList<String> eventLog = new ArrayList<String>();

	public void eventLogger(String events) throws IOException {
		eventLog = new ArrayList<String>();
		collect();

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date now = Calendar.getInstance().getTime();
		String timeLogged = dateFormat.format(now);
		events = timeLogged+" | "+events;
		
		eventLog.add(events);
		
		String storagePath = System.getProperty("user.dir")+"\\ProjectData_Dilallo";
		storagePath = storagePath+"\\Events.txt";
		FileWriter fileWrite = new FileWriter(storagePath);
		BufferedWriter buf = new BufferedWriter(fileWrite);
		
		for (int i = 0; i < eventLog.size(); i++) {
			String line = eventLog.get(i);
			buf.write(line+"\n");
		}
		buf.close();
	}
	
	private void collect() throws FileNotFoundException {
		String storagePath = System.getProperty("user.dir")+"\\ProjectData_Dilallo";
		storagePath = storagePath+"\\Events.txt";
		String eventPath = storagePath;
		File eventDoc = new File(eventPath);
		Scanner scan = new Scanner(eventDoc);
		
		while(scan.hasNextLine()) {
			String Line = scan.nextLine();
			eventLog.add(Line);
		}
		scan.close();
	}
	
	public void display() throws FileNotFoundException {
		collect();
		System.out.println("--Event Log--");
		
		for (int i = 0; i < eventLog.size(); i++) {
			System.out.println(eventLog.get(i));
		}
		System.out.println("--End of Event Log--");
	}
}
