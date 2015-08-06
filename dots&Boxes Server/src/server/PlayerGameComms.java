/*
		 Title: GameComms.java
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
public class PlayerGameComms extends Thread {
	Player player;

	public PlayerGameComms(Player player) {
		this.player = player;
	}
	
	

}
