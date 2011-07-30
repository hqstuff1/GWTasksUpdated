
package com.GWTasksWithLoginPageCh5.client.ui.event;

import com.GWTasksWithLoginPageCh5.client.model.Task;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEvent;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEventSource;


/**
 * Triggered whenever a new task is created.
 *
 * @author 
 */
public class TaskCreatedEvent extends ApplicationEvent {

    private final Task task;

    /**
     * Constructs a new TaskCreatedEvent with the given created task.
     *
     * @param source The source of this event.
     * @param task The newly created task.
     */
    public TaskCreatedEvent(ApplicationEventSource source, Task task) {
        super(source);
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return "Created task '" + task.getTitle() + "'";
    }


    //============================================= Setter/Getter ======================================================

    /**
     * Returns the newly created task.
     *
     * @return The newly created task.
     */
    public Task getTask() {
        return task;
    }
}
