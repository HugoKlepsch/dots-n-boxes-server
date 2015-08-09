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
	Player player1;
	Player player2;
	
	public GameRoom(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
	}

}
