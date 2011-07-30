
package com.GWTasksWithLoginPageCh5.client.ui.event;

import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEvent;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEventSource;


/**
 * Triggered when the user logs out of the application.
 *
 * @author 
 */
public class LogoutEvent extends ApplicationEvent {

    public LogoutEvent(ApplicationEventSource source) {
        super(source);
    }

    public String getDescription() {
        return "User logged out";
    }
}
