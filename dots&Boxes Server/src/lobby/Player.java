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
	
	private boolean hasSetUser = false;
	private int numFailedUserChecks; //incremented when the tempplayerchecker checks the player, and hasSetUser is false;
	
	public Player(Socket socket){
		StayAlive = true;
		inComm = new LobbyInComm(socket, this);
		outComm = new LobbyOutComm(socket, this);
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		hasSetUser = true;
	}
	
	public boolean hasSetUser(){
		return hasSetUser;
	}
	
	public int getNumFailedUserChecks(){
		return numFailedUserChecks;
	}
	
	public int incrementFailedChecks(){
		return ++ numFailedUserChecks;
	}
	

}
