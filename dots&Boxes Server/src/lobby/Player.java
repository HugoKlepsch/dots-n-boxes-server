/*
		 Title: Plaer.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package lobby;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sharedPackages.ActionRequest;
import sharedPackages.User;

/**
 * @author hugo
 *
 */
public class Player extends Thread {
	public boolean StayAlive;
	private User user;
	private ObjectInputStream csStream;
	private ObjectOutputStream scStream;
	private Socket socket;
	
	private boolean hasSetUser = false;
	private int numFailedUserChecks; //incremented when the tempplayerchecker checks the player, and hasSetUser is false;
	
	public Player(Socket socket){
		StayAlive = true;
		
	}
	
	public void run(){
		try {
			csStream = new ObjectInputStream(socket.getInputStream());
			scStream = new ObjectOutputStream(socket.getOutputStream());
			ActionRequest actionRequest;
			while(StayAlive){
				actionRequest = (ActionRequest) csStream.readObject();
				
				switch (actionRequest.getAction()){
				case ActionRequest.CS_CONNECT:
					this.setUser(actionRequest.getUser());
					break;
				case ActionRequest.CS_USERLIST:
					sendUserList();
					break;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			Lobby.logger.writeToLog("Could not find input stream of socket");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Lobby.logger.writeToLog("Sent a non action request through the stream, stupid");
		}
	}
	
	public void sendActionRequest(ActionRequest actionRequest){
		try {
			scStream.writeObject(actionRequest);
			scStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Lobby.logger.writeToLog("Couldn't send action request");
		}
		
	}
	
	public void sendUserList(){
		this.sendActionRequest(new ActionRequest(ActionRequest.SC_USERLIST, Lobby.userNames));
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
