
package com.GWTasksWithLoginPageCh5.client.manager.ui;

import com.GWTasksWithLoginPageCh5.client.support.widget.GoogleLikeProgressIndicator;
import com.GWTasksWithLoginPageCh5.client.support.widget.ProgressIndicator;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.Window;


/**
 * A default implemenation of {@link UIManager}.
 *
 * @author 
 */
public class DefaultUIManager implements UIManager {

    private final boolean debugMode;

    /**
     * Constructs a new DefaultUIManager.
     *
     * @param debugMode Determines whether debug messages should be shown or not.
     */
    public DefaultUIManager(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * {@inheritDoc}
     */
    public void showDebugMessage(String message) {
        if (debugMode) {
            Window.alert(message);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void showErrorMessage(String message) {
        Window.alert(message);
    }

    /**
     * {@inheritDoc}
     */
    public void showInfoMessage(String message) {
        Window.alert(message);
    }

    /**
     * {@inheritDoc}
     */
    public void showConfirmMessage(String message, AsyncCallback<Boolean> callback) {
        boolean result = Window.confirm(message);
        callback.onSuccess(result);
    }

    /**
     * {@inheritDoc}
     */
    public ProgressIndicator showProgressIndicator(String message) {
        return new GoogleLikeProgressIndicator(message);
    }

}
