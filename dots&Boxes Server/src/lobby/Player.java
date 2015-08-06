/*
		 Title: Plaer.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package lobby;

import java.net.Socket;

/**
 * @author hugo
 *
 */
public class Player {
	private Socket socket;
	
	public Player(Socket socket){
		this.socket = socket;
	}

}
