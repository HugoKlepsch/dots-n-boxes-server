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
public class ConnectionAccepter{
	public static final int port = 7075;
	
	
	
	public static void main(String[] args){
		try {
			new Lobby().start();
			System.out.println(getSelfIP());
			ServerSocket serverSocket = new ServerSocket(port);
			while(null == null){
				Socket tempSock = serverSocket.accept();
				Lobby.addTempPlayer(new Player(tempSock));
			}
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static String getSelfIP() throws IOException {
		Socket ipTest = new Socket("www.google.ca", 80); 
		String selfIP = ipTest.getLocalAddress().getHostAddress(); 
		ipTest.close(); 
		return selfIP; 
	}
	
}
