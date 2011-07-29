package com.GWTasksWithLoginPageCh5.client.support.event;

/**
 * A listener that listens and is able to handle {@link ApplicationEvent application events}.
 */
public interface ApplicationEventListener
{
	/**
     * Handles the given application event.
     *
     * @param event The event to handle.
     */
	void handle(ApplicationEvent event);
}
