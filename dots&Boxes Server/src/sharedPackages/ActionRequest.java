/*
		 Title: ActionRequest.java
		 Programmer: hugo
		 Date of creation: Aug 6, 2015
		 Description: 
*/


package sharedPackages;

import java.io.Serializable;

/**
 * @author hugo
 *
 */
public class ActionRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int CS_CONNECT = 0;
	public static final int CS_DISCONNECT = 1;
	//ask to join game
	public static final int CS_JOIN_GAME = 2;
	//make the user join the game
	public static final int SC_JOIN_GAME = 3;
	//send the request for matchmaking
	public static final int CS_FIND_GAME = 4;
	//challenge player
	public static final int CS_CHALLENGE_PLAYER = 5;
	//receive challenge
	public static final int SC_CHALLENGE_PLAYER = 6;
	//ask for user list
	public static final int CS_USERLIST = 7;
	//send user list
	public static final int SC_USERLIST = 8;
	public static final int CS_CREATE_ACCOUNT = 9;
	
	private int action;
	private User user;
	
	public ActionRequest(int action){
		setAction(action);
	}
	
	public ActionRequest(int action, User user){
		setAction(action);
		setUser(user);
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
