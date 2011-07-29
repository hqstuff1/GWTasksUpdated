package com.GWTasksWithLoginPageCh5.client.support.event;

/**
 * Represents a source by which application events can be fired. 
 * As such, it provides method to register application
 * event listeners that will be notified of the fired event.
 */

public interface ApplicationEventSource   //ApplicationEventSource stores and can fire events
{
	 /**
     * Adds the given application event listener to this source.
     *
     * @param listener The application event listener that will notified of the events fired by this source.
     */
	void addListener(ApplicationEventListener listener);
	
	/**
     * Removes the given application event listener from this source.
     *
     * @param listener The listener to be removed (if the given listener is not registered with this source, this method
     *        will do nothing and exit quietly).
     */
	void removeListener(ApplicationEventListener listener);
	
	 /**
     * Removes all application event listeners registered with this source.
     */
	void clearListeners();
}
