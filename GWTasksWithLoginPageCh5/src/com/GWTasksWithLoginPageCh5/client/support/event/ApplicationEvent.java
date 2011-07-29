package com.GWTasksWithLoginPageCh5.client.support.event;


/**
 * This class serves as a parent for all application level events - this class stores events (in source).
 * It holds the {@link ApplicationEventSource source} that triggered the event.
 * 
 * This class also enforces each event implementation to provide a description for the event.
 */
public abstract class ApplicationEvent 
{
	private final ApplicationEventSource source;
	
    /**
     * Constructs a new AppliationEvent which is triggered by the given {@link ApplicationEventSource source}.
     *
     * @param source The {@link ApplicationEventSource source} that triggers this event.
     */
	public ApplicationEvent(ApplicationEventSource source)
	{
		this.source = source;
	}
	
    /**
     * Returns the {@link ApplicationEventSource source} that triggered this event.
     *
     * @return The {@link ApplicationEventSource source} that triggered this event.
     */
	public ApplicationEventSource getSource()
	{
		return source;
	}
	
    /**
     * Returns the description of this event. 
     * This can be a dynamic description that changes between event instances of the same type.
     *
     * @return The description of this event.
     */
	public abstract String getDescription();
}
