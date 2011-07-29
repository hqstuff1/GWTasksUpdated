

package com.GWTasksWithLoginPageCh5.client;


import com.google.gwt.user.client.ui.*;


/**
 * The status bar of the GWTasks application.
 *
 * @author 
 */
public class StatusBarPane extends Composite {

	private final Label messageLabel;
	
	public StatusBarPane() {
		messageLabel = new Label();
		initWidget(messageLabel);  //the label  is wrapped by the composite
	}
	
	/**
	 * Sets the message to be displayed in this status bar.
	 */
	public void setMessage(String message) {
		messageLabel.setText(message);
	}

}
