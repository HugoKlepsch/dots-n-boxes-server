/*
		 Title: LobbyOutComm.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package lobby;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.Logging;
import sharedPackages.ActionRequest;

/**
 * @author hugo
 *
 */
public class LobbyOutComm extends Thread{
	private Socket scSock;
	private ObjectOutputStream scStream;
	private Player player;
	
	public LobbyOutComm(Socket socket, Player player){
		scSock = socket;
		this.player = player;
	}
	
	public void run(){
		try {
			scStream = new ObjectOutputStream(scSock.getOutputStream());
			while(player.StayAlive){
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Lobby.logger.writeToLog("Could not find output stream of socket");
		}
	}

}
