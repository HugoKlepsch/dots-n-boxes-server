/*
		 Title: Logging.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: handles program logging in a single object. 
*/


package server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hugo
 *
 */
public class Logging {
	private PrintWriter fileOut;
	private boolean printToStdOutAswell; //if true, will print to stdout as well when logging. 
	
	/**
	 * 
	 * @param filename - the filename to log to 
	 * @param printToStdOutAswell - boolean, if true will print to stdout as well as the file
	 */
	public Logging(String filename, boolean printToStdOutAswell){
		this.printToStdOutAswell = printToStdOutAswell;
		try {
			fileOut = new PrintWriter(new FileWriter(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error making file log file writer. ");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
		 * @author hugo
		 * Date of creation: Aug 6, 2015 
		 * @param: The message to log
		 * @return: None
		 * @Description: Adds the given message to the server log, and alternatively also to the stdout
	 */
	public void writeToLog(String message){
		fileOut.println(message);
		if (printToStdOutAswell) {
			System.out.println(message);
		}
	}
	
}
