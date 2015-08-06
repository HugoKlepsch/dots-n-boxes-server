/*
		 Title: Lobby.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package lobby;

import java.util.Vector;

import server.ConnectionAccepter;
import server.Logging;

/**
 * @author hugo
 *
 */
public class Lobby {
	public static Logging logger;
	public static Vector<Player> players = new Vector<Player>(); 
	
	public static void main(String[] args) {
		new ConnectionAccepter().start();
		logger = new Logging("dots_n_boxes_log.txt", true);
		
	}
	
	public static void addPlayer(Player player){
		players.addElement(player);
	}

}
