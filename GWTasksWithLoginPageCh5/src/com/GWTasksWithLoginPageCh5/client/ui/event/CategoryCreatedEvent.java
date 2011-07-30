package com.GWTasksWithLoginPageCh5.client.ui.event;


import com.GWTasksWithLoginPageCh5.client.model.Category;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEvent;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEventSource;


/**
 * Triggered whenever a new category is created.
 *
 * @author 
 */
public class CategoryCreatedEvent extends ApplicationEvent {

    private final Category category;

    /**
     * Constructs a new CategoryCreatedEvent with the given new category.
     *
     * @param source The source of this event.
     * @param category The newly created category.
     */
    public CategoryCreatedEvent(ApplicationEventSource source, Category category) {
        super(source);
        this.category = category;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return "Created category '" + category.getName() + "'";
    }

    //============================================= Setter/Getter ======================================================

    /**
     * Returns the newly created category.
     *
     * @return The newly created category.
     */
    public Category getCategory() {
        return category;
    }
}
