/*
		 Title: Plaer.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package lobby;

import java.net.Socket;

import sharedPackages.User;

/**
 * @author hugo
 *
 */
public class Player {
	public boolean StayAlive;
	private LobbyInComm inComm;
	private LobbyOutComm outComm;
	private User user;
	
	public Player(Socket socket){
		StayAlive = true;
		inComm = new LobbyInComm(socket, this);
		outComm = new LobbyOutComm(socket, this);
		
	}

}
