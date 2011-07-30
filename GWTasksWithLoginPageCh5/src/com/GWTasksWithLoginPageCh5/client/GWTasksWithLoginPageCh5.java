package com.GWTasksWithLoginPageCh5.client;

import com.GWTasksWithLoginPageCh5.client.manager.ManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.manager.SimpleManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.manager.data.DataManager;
import com.GWTasksWithLoginPageCh5.client.manager.data.InMemoryDataManager;
import com.GWTasksWithLoginPageCh5.client.manager.security.InMemorySecurityManager;
import com.GWTasksWithLoginPageCh5.client.manager.security.MySecurityManager;
import com.GWTasksWithLoginPageCh5.client.manager.ui.DefaultUIManager;
import com.GWTasksWithLoginPageCh5.client.manager.ui.UIManager;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEvent;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEventListener;
import com.GWTasksWithLoginPageCh5.client.ui.event.LoginEvent;
import com.GWTasksWithLoginPageCh5.client.ui.event.LogoutEvent;
import com.GWTasksWithLoginPageCh5.client.ui.loginpane.LoginPane;
import com.GWTasksWithLoginPageCh5.client.ui.mainpane.MainPane;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

//start on page  134 -135, Beginning Google Web Toolkit from Novice to Professional




/**
 * The GWTasks application.
 *
 * @author
 */

public class GWTasksWithLoginPageCh5 implements EntryPoint 
{
	private SimplePanel main;
	private LoginPane loginPane;
	private MainPane mainPane;

    private ManagerRegistry managerRegistry;

	public void onModuleLoad() {

        // initializing the managers.
        MySecurityManager securityManager = new InMemorySecurityManager();
        DataManager dataManager = new InMemoryDataManager();
        UIManager uiManager = new DefaultUIManager(false);
        managerRegistry = new SimpleManagerRegistry(securityManager, dataManager, uiManager);

		mainPane = new MainPane(managerRegistry);
        mainPane.addListener(new MainPaneListener());
		mainPane.setSize("100%", "100%");

		loginPane = new LoginPane(managerRegistry);
        loginPane.addListener(new LoginPaneListener());
		loginPane.setSize("100%", "100%");

        main = new SimplePanel();
		main.setSize("100%", "100%");
		main.setWidget(loginPane);

        RootPanel.get().add(main);
	}

    /**
     * Shows the login pane.
     */
	protected void showLoginPane() {
		main.setWidget(loginPane);
	}

    /**
     * Shows the main pane.
     */
	protected void showMainPane() {
		main.setWidget(mainPane);
	}


    //============================================= Inner Classes ======================================================

    protected class LoginPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            if (event instanceof LoginEvent) {
                showMainPane();
            }
        }
    }

    protected class MainPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            if (event instanceof LogoutEvent) {
                showLoginPane();
            }
        }
    }
}
