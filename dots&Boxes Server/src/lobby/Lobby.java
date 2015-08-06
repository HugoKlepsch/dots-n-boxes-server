/*
		 Title: Lobby.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package lobby;

import java.util.Vector;

import server.ConnectionAccepter;

/**
 * @author hugo
 *
 */
public class Lobby {
	public static Vector<Player> players = new Vector<Player>(); 
	
	public static void main(String[] args) {
		new ConnectionAccepter().start();
		
	}
	
	public void addPlayer(Player player){
		players.addElement(player);
	}

}
