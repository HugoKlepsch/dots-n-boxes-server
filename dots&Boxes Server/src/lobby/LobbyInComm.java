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
			while(player.StayAlive){
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Lobby.logger.writeToLog("Could not find input stream of socket");
		}
	}
	

}
