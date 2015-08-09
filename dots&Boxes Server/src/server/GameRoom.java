/*
		 Title: GameRoom.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package server;

import lobby.Player;

/**
 * @author hugo
 *
 */
public class GameRoom extends Thread {
	PlayerGameComms player1Comms;
	PlayerGameComms player2Comms;
	
	public GameRoom(Player player1, Player player2){
		player1Comms = new PlayerGameComms(player1);
		player2Comms = new PlayerGameComms(player2);
	}

}
