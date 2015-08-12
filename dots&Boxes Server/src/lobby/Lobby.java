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
import sharedPackages.ActionRequest;
import sharedPackages.User;

/**
 * @author hugo
 *
 */
public class Lobby extends Thread{
	public static Logging logger;
	public static Vector<Player> tempPlayers = new Vector<Player>();
	public static Vector<Player> players = new Vector<Player>();
	public static Vector<User> userNames = new Vector<User>();

	public void run() {
		logger = new Logging("dots_n_boxes_log.txt", true);
		new tempPlayerManager().start();

	}
	public Lobby(){
		
	}

	public static void addPlayer(Player player) {
		logger.writeToLog("added player: " + player.getUser().getUsername());
		player.getUser().wipeCreds();
		players.addElement(player);
		userNames.add(player.getUser());
		player.sendUserList();

	}

	public static void addTempPlayer(Player player) {
		tempPlayers.addElement(player);
		player.start();
	}

	class tempPlayerManager extends Thread {
		
		private final int MAX_ATTEMPS = 5;

		public tempPlayerManager() {

		}

		public void run(){
			while(true){
				int i = 0;
				boolean keepGoing = true;
								
				while(i < tempPlayers.size() && keepGoing){
					for (int j = 0; j < tempPlayers.size(); j++) {
						logger.writeToLog((tempPlayers.elementAt(j).toString()));
					}
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if (tempPlayers.elementAt(i).hasSetUser()) {
						if (this.checkPlayerSignOn(tempPlayers.elementAt(i).getUser()) == true) {
							logger.writeToLog("User " + tempPlayers.elementAt(i).getUser().getUsername() + " passed signon check");
							Lobby.addPlayer(tempPlayers.remove(i));
							keepGoing = false;
							tempPlayers.elementAt(i).sendActionRequest(new ActionRequest(ActionRequest.SC_LOGIN_SUCCESS));
						} else {
							logger.writeToLog("User " + tempPlayers.elementAt(i).getUser().getUsername() + " did not pass signon check");
							keepGoing = false;
							tempPlayers.elementAt(i).sendActionRequest(new ActionRequest(ActionRequest.SC_LOGIN_FAILURE));
							//TODO kill the player
						}
					} else {
						logger.writeToLog(tempPlayers.elementAt(i) + "Has failed to provide a user " + tempPlayers.elementAt(i).incrementFailedChecks() + "times");
						if (tempPlayers.elementAt(i).getNumFailedUserChecks() > MAX_ATTEMPS) {
							keepGoing = false;
							tempPlayers.elementAt(i).sendActionRequest(new ActionRequest(ActionRequest.SC_LOGIN_FAILURE));
							//TODO kill the player
						}
					}
					i++;
				}
			}
		}

		/**
		 * 
			 * @author hugo
			 * Date of creation: Aug 9, 2015 
			 * @param: the user credentials
			 * @return: true if valid, false else
			 * @Description: ( ͡° ͜ʖ ͡°)
		 */
		public boolean checkPlayerSignOn(User user) {
			return true;
		}
	}
}
