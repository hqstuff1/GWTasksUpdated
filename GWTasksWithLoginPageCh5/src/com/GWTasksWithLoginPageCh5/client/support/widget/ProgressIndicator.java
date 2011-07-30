package com.GWTasksWithLoginPageCh5.client.support.widget;

/**
 * an indicator for ongoing progress
 * @author Normal_User
 *
 */
public interface ProgressIndicator {
	 /**
     * Sets the message shown by this indicator.
     *
     * @param message The message that should shown by this indicator.
     */
    void setMessage(String message);

    /**
     * Hides this indicator.
     */
    void hide();


}
