

package com.GWTasksWithLoginPageCh5.client.ui.mainpane;

import com.GWTasksWithLoginPageCh5.client.manager.ManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.ui.Pane;
import com.GWTasksWithLoginPageCh5.client.ui.event.LogoutEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.GWTasksWithLoginPageCh5.client.manager.security.MySecurityManager;

/**
 * A {@link Pane} that shows the header of the main page. This header consists of a title and a logout button/link.
 *
 * @author 
 */
public class HeaderPane extends Pane {

	private final Label title;

	/**
	 * Constructs a new HeaderPane that displays the given text.
     *
     * @param titleText The text this header should show as a title.
     * @param managerRegistry The manager registry to associate with this pane.
	 */
	public HeaderPane(String titleText, ManagerRegistry managerRegistry) {
        super(managerRegistry);

        DockPanel main = new DockPanel();

        title = new Label(titleText);
        title.setStyleName("Title");
        main.add(title, DockPanel.CENTER);
        main.setCellWidth(title, "100%");

        PushButton logoutButton = new PushButton("Logout");
        logoutButton.setStyleName("LogoutButton");
        logoutButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) 
			{
				((MySecurityManager)getManagerRegistry().getSecurityManager()).logout();
                fireEvent(new LogoutEvent(HeaderPane.this));
			}
		});
        

        main.add(logoutButton, DockPanel.EAST);

		initWidget(main);
        setStyleName("HeaderPane");
	}
	
	/**
	 * Sets the text that should be displayed in this header pane.
     *
     * @param titleText The text of the title.
	 */
	public void setTitleText(String titleText) {
		title.setText(titleText);
	}
}
