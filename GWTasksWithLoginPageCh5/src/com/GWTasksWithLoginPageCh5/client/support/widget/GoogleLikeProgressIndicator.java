package com.GWTasksWithLoginPageCh5.client.support.widget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * A {@link ProgressIndicator} implementation which mimic the progress indicator used by Google in their product. This
 * indicator basically shows a label on to top-center of the screen.
 *
 * @author 
 */
public class GoogleLikeProgressIndicator extends PopupPanel implements ProgressIndicator
{
	  private final Label messageLabel;

	    /**
	     * Constructs a new GoogleLikeProgressIndicator with "Loading" as a default initial message.
	     */
	    public GoogleLikeProgressIndicator() {
	        this("Loading...");
	    }

	    /**
	     * Constructs a new GoogleLikeProgressIndicator with a given initial message.
	     *
	     * @param message The message the indicator should initialy show.
	     */
	    public GoogleLikeProgressIndicator(String message) {
	        super(false, true);
			messageLabel = new Label(message);
			messageLabel.setStyleName("Message");
			setPopupPositionAndShow(new PopupPanel.PositionCallback() {
	            public void setPosition(int offsetWidth, int offsetHeight) {
	                int x = Window.getClientWidth()/2 - offsetWidth/2;
	                setPopupPosition(x, 0);
	            }
	        });
	        setWidget(messageLabel);
	        setStyleName("GoogleLikeProgressIndicator");
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public void setMessage(String message) {
	        messageLabel.setText(message);
	    }

	}

