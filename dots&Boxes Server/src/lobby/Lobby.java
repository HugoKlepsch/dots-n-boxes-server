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
public class Lobby {
	public static Logging logger;
	public static Vector<Player> tempPlayers = new Vector<Player>();
	public static Vector<Player> players = new Vector<Player>(); 
	public static Vector<User> userNames = new Vector<User>();
	
	public static void main(String[] args) {
		logger = new Logging("dots_n_boxes_log.txt", true);
		new ConnectionAccepter().start();
		
		
	}
	
	public static void addPlayer(Player player){
		player.getUser().wipeCreds();
		players.addElement(player);
		userNames.add(player.getUser());
		player.sendActionRequest(new ActionRequest(ActionRequest.SC_USERLIST, userNames));
		
	}
	
	public static void addTempPlayer(Player player){
		tempPlayers.addElement(player);
	}
	
	
	

	class tempPlayerManager extends Thread{
		
		
		public tempPlayerManager(){
			
		}
		
		public void run(){
			while(true){
				for (int i = 0; i < tempPlayers.size(); i++) {
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
							break;
						} else {
							logger.writeToLog("User " + tempPlayers.elementAt(i).getUser().getUsername() + " did not pass signon check");
							//TODO kill the player
						}
					} else {
						logger.writeToLog(tempPlayers.elementAt(i) + "Has failed to provide a user " + tempPlayers.elementAt(i).incrementFailedChecks() + "times");
					}
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
		public boolean checkPlayerSignOn(User user){
			return true;
		}
	}
}
