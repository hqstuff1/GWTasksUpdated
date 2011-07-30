
package com.GWTasksWithLoginPageCh5.client.manager;

import com.GWTasksWithLoginPageCh5.client.manager.data.DataManager;
import com.GWTasksWithLoginPageCh5.client.manager.security.MySecurityManager;
import com.GWTasksWithLoginPageCh5.client.manager.ui.UIManager;



/**
 * A simple implementation of the {@link ManagerRegistry}.
 *
 * @author 
 */
public class SimpleManagerRegistry implements ManagerRegistry {

    private final MySecurityManager securityManager;
    private final DataManager dataManager;
    private final UIManager uiManager;

    /**
     * Creates a SimpleManagerRegistry with all required managers.
     *
     * @param securityManager The security manager.
     * @param dataManager The data manager.
     * @param uiManager The UI manager.
     */
    public SimpleManagerRegistry(MySecurityManager securityManager, DataManager dataManager, UIManager uiManager) {
        this.securityManager = securityManager;
        this.dataManager = dataManager;
        this.uiManager = uiManager;
    }

    /**
     * {@inheritDoc}
     */
    public MySecurityManager getSecurityManager() {
        return securityManager;
    }

    /**
     * {@inheritDoc}
     */
    public DataManager getDataManager() {
        return dataManager;
    }

    /**
     * {@inheritDoc}
     */
    public UIManager getUIManager() {
        return uiManager;
    }

}
