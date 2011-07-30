package com.GWTasksWithLoginPageCh5.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.GWTasksWithLoginPageCh5.client.manager.ManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.support.event.*;

/**
 * Serves as a base class for all panes in the application.
 *
 * @author 
 */
public abstract class Pane extends Composite implements ApplicationEventSource {

    private final ApplicationEventListenerCollection listeners;
    private final ManagerRegistry managerRegistry;
    
    /**
     * Constructs a new Pane.
     */
    protected Pane(ManagerRegistry managerRegistry) {
        listeners = new ApplicationEventListenerCollection();
        this.managerRegistry = managerRegistry;
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

    /**
     * Returns the manager registry associated with this pane.
     *
     * @return The manager registry associated with this pane.
     */
    protected ManagerRegistry getManagerRegistry() {
        return managerRegistry;
    }
}

