package com.GWTasksWithLoginPageCh5.client;

//start on page  134 -135, Beginning Google Web Toolkit from Novice to Professional


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.GWTasksWithLoginPageCh5.client.events.*;

/**
 * The GWTasks application.
 *
 * @author
 */

public class GWTasksWithLoginPageCh5 implements EntryPoint {

	private SimplePanel main;
	private LoginPane loginPane;
	private MainPane mainPane;

	public void onModuleLoad() 
	{
		mainPane = new MainPane();
		mainPane.setSize("100%", "100%");
		loginPane = new LoginPane();
		
		loginPane.addListener(new LoginPaneListener());
		loginPane.setSize("100%", "100%");
		
		main = new SimplePanel();
		main.setSize("100%", "100%");
		main.setWidget(loginPane);
		RootPanel.get().add(main);
	}
	
	public void showLoginPane() {
		main.setWidget(loginPane);
	}
	
	public void showMainPane() {
		main.setWidget(mainPane);
	}
	
	protected class LoginPaneListener implements ApplicationEventListener
	{
		public void handle(ApplicationEvent event)
		{
			if(event instanceof LoginEvent)
			{
				showMainPane();
			}
		}
	}
}
