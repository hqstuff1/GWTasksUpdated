
package com.GWTasksWithLoginPageCh5.client.manager.ui;

import com.GWTasksWithLoginPageCh5.client.support.widget.ProgressIndicator;
import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * Manages all common & global aspects of the UI.
 *
 * @author 
 */
public interface UIManager {

    /**
     * Shows a debug message.
     *
     * @param message The debug message to show.
     */
    void showDebugMessage(String message);

    /**
     * Shows an error message.
     *
     * @param message The error message to show.
     */
    void showErrorMessage(String message);

    /**
     * Shows an info. message.
     *
     * @param message The info. message to show.
     */
    void showInfoMessage(String message);

    /**
     * Show a confirmation dialog. The given callback is called based on the user action.
     *
     * @param message The message to show on the confirmation dialog.
     * @param callback Called with <code>true</code> if the user confirmed, <code>false</code> otherwise.
     */
    void showConfirmMessage(String message, AsyncCallback<Boolean> callback);

    /**
     * Creates and shows a progress indicator with the given message.
     *
     * @param message The message showed on the progress indicator.
     * @return The created progress indicator.
     */
    ProgressIndicator showProgressIndicator(String message);
}
