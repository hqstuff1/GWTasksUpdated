

package com.GWTasksWithLoginPageCh5.client.ui.mainpane;


import com.GWTasksWithLoginPageCh5.client.manager.ManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.ui.Pane;
import com.google.gwt.user.client.ui.*;


/**
 * The status bar of the GWTasks application.
 *
 * @author
 */
public class StatusBarPane extends Pane {

	private final Label messageLabel;

    /**
     * Constructs a new StatusBarPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate with this pane.
     */
	public StatusBarPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);
		messageLabel = new Label();
        messageLabel.setStyleName("MessageLabel");
		initWidget(messageLabel);
        setStyleName("StatusBarPane");
	}
	
	/**
	 * Sets the message to be displayed in this status bar.
     *
     * @param message The message to be displayed in this status bar.
	 */
	public void setMessage(String message) {
		messageLabel.setText(message);
	}

}