

package com.GWTasksWithLoginPageCh5.client.manager;

import com.GWTasksWithLoginPageCh5.client.manager.data.DataManager;
import com.GWTasksWithLoginPageCh5.client.manager.security.MySecurityManager;
import com.GWTasksWithLoginPageCh5.client.manager.ui.UIManager;



/**
 * A registry of all available managers.
 *
 * @author 
 */
public interface ManagerRegistry {

    /**
     * Returns the security manager of this application.
     *
     * @return The security manager of this application.
     */
	MySecurityManager getSecurityManager();

    /**
     * Returns the data manager of this application.
     *
     * @return The data manager of this application.
     */
    DataManager getDataManager();

    /**
     * Returns the UI manager of this application.
     *
     * @return The UI manager of this application.
     */
    UIManager getUIManager();

}
