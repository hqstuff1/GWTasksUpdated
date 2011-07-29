package com.GWTasksWithLoginPageCh5.client.support.event;

import java.util.ArrayList;


/**
 * A helper class that helps implementing {@link ApplicationEventSource application event sources}. 
 * This class serves as a collection of application event listeners. 
 * It is also possible to fire events to all the contained listeners via the {@link #fireEvent(ApplicationEvent)} method.
 */
@SuppressWarnings("serial")
public class ApplicationEventListenerCollection extends ArrayList<ApplicationEventListener>
{
    /**
     * Fires the given event for all the listeners in the  ApplicationEventListenerCollection (of type arraylist)
     *
     * @param event The event to be fired.
     */
	public void fireEvent(ApplicationEvent event)
	{
		for (ApplicationEventListener listener : this)
		{
			listener.handle(event);
		}
	}
}
