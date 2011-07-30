package com.GWTasksWithLoginPageCh5.client.ui.event;

import com.GWTasksWithLoginPageCh5.client.model.Task;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEvent;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEventSource;

/**
 * Triggered whenever a task selection changes. When a task is selected, it can be fetched by calling the
 * {@link #getTask()} method. When a selection was cleared this method will return <code>null</code>.
 *
 * @author 
 */
public class TaskSelectionEvent  extends ApplicationEvent{

    private final Task task;

    /**
     * Constructs a new TaskSelectionEvent with the given selected task.
     *
     * @param source The source of this event.
     * @param task The selected task or <code>nul</code> if the task selection was cleared.
     */
    public TaskSelectionEvent(ApplicationEventSource source, Task task) {
        super(source);
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return (task != null) ? "Task '" + task.getTitle() + "' was selected" : "";
    }


    //============================================= Setter/Getter ======================================================

    /**
     * Returns the selected task or <code>null</code> if the selection was cleared.
     *
     * @return The selected task or <code>null</code> if the selection was cleared.
     */
    public Task getTask() {
        return task;
    }

}
