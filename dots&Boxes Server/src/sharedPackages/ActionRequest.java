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
	
	public ActionRequest(int action){
		this.setAction(action);
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}
