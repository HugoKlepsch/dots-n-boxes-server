/*
		 Title: LobbyInComm.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package lobby;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import sharedPackages.ActionRequest;

/**
 * @author hugo
 *
 */
public class LobbyInComm extends Thread {
	private Socket csSock;
	private ObjectInputStream csStream;
	private Player player;
	
	public LobbyInComm(Socket socket, Player player){
		csSock = socket;
		this.player = player;
	}
	
	public void run(){
		try {
			csStream = new ObjectInputStream(csSock.getInputStream());
			ActionRequest actionRequest;
			while(player.StayAlive){
				actionRequest = (ActionRequest) csStream.readObject();
				if(actionRequest.getAction() == ActionRequest.CS_CONNECT){
					player.setUser(actionRequest.getUser());
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
	

}
