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
import java.util.Arrays;
import java.util.Vector;

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
		this.socket = socket;
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
						Lobby.logger.writeToLog("Received cs_connect from " + actionRequest.getUser().getUsername());
						this.setUser(actionRequest.getUser());
						break;
					case ActionRequest.CS_USERLIST:
						Lobby.logger.writeToLog("Received cs_userlist from " + this.getUser().getUsername());
						this.sendUserList();
						break;
					case ActionRequest.CS_CHALLENGE_PLAYER:
						this.challengePlayer(actionRequest.getUser().getUsername());
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
		Lobby.logger.writeToLog("Sent user list");
	}
	
	public void challengePlayer(String username){
		Vector<String> usernames = new Vector<String>();
		for(int i = 0; i<Lobby.userNames.size();i++){
			usernames.add(Lobby.userNames.get(i).getUsername());
		}
		Object[] userArray = usernames.toArray();
		int index = Arrays.binarySearch(userArray, username);
		Lobby.players.get(index).sendActionRequest(new ActionRequest(ActionRequest.SC_CHALLENGE_PLAYER));;
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
