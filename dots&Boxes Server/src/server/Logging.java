/*
		 Title: Logging.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package server;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hugo
 *
 */
public class Logging {
	private static PrintWriter fileOut;
	private static boolean printToStdOutAswell;
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
	
	public static void writeToLog(String message){
		fileOut.println(message);
		if (printToStdOutAswell) {
			System.out.println(message);
		}
	}
	
}
