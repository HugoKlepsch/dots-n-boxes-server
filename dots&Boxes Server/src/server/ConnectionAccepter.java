/*
--		 Title: Lobby.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lobby.Lobby;
import lobby.Player;

/**
 * @author hugo
 *
 */
public class ConnectionAccepter extends Thread{
	public static final int port = 7070;
	
	public ConnectionAccepter(){
		
	}
	
	public void run(){
		try {
			System.out.println(getSelfIP());
			ServerSocket serverSocket = new ServerSocket();
			while(true){
				
				Lobby.addTempPlayer(new Player(serverSocket.accept()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static String getSelfIP() throws IOException {
		Socket ipTest = new Socket("8.8.8.8", 80); 
		String selfIP = ipTest.getLocalAddress().getHostAddress(); 
		ipTest.close(); 
		return selfIP; 
	}
	
}
