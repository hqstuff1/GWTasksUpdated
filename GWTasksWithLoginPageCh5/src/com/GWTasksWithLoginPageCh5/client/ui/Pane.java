package com.GWTasksWithLoginPageCh5.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.GWTasksWithLoginPageCh5.client.support.event.*;

/**
 * Serves as a base class for all panes in the application.
 *
 * @author 
 */
public abstract class Pane extends Composite implements ApplicationEventSource {

    private final ApplicationEventListenerCollection listeners;

    /**
     * Constructs a new Pane.
     */
    protected Pane() {
        listeners = new ApplicationEventListenerCollection();
    }

    /**
     * {@inheritDoc}
     */
    public void addListener(ApplicationEventListener listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeListener(ApplicationEventListener listener) {
        listeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void clearListeners() {
        listeners.clear();
    }

    /**
     * Fires the given event to all the registered listener.
     *
     * @param event The event to fire.
     */
    protected void fireEvent(ApplicationEvent event) {
        listeners.fireEvent(event);
    }

}

