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
