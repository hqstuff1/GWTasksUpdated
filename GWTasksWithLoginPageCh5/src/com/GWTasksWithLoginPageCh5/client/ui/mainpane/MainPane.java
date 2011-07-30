package com.GWTasksWithLoginPageCh5.client.ui.mainpane;

import com.GWTasksWithLoginPageCh5.client.manager.ManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.model.Category;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEvent;
import com.GWTasksWithLoginPageCh5.client.support.event.ApplicationEventListener;
import com.GWTasksWithLoginPageCh5.client.ui.Pane;
import com.GWTasksWithLoginPageCh5.client.ui.event.CategorySelectionEvent;
import com.GWTasksWithLoginPageCh5.client.ui.mainpane.categorypane.CategoryPane;
import com.GWTasksWithLoginPageCh5.client.ui.mainpane.taskpane.TaskPane;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;

/**
 * A {@link Pane} that shows the main page of the application. This page consists of four parts:
 * <ul>
 *  <li>{@link CategoryPane} - shows the category tree.</li>
 *  <li>{@link TaskPane} - shows the tasks for the selected category</li>
 *  <li>{@link HeaderPane} - The header of the page</li>
 *  <li>{@link StatusBarPane} - Serves as the status bar of the page.</li>
 * </ul>
 *
 * @author 
 */
public class MainPane extends Pane {

    private HeaderPane headerPane;
    private StatusBarPane statusBarPane;
    private TaskPane taskPane;
    private CategoryPane categoryPane;

	/**
	 * Constructs a new MainPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate with this pane.
	 */
	public MainPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);

		DockPanel mainPanel = new DockPanel();
		mainPanel.setBorderWidth(5);    	
		mainPanel.setSize("100%", "100%");
		
    	headerPane = new HeaderPane("GWTasks", managerRegistry);
    	headerPane.addListener(new HeaderPaneListener());
        mainPanel.add(headerPane, DockPanel.NORTH);
    	mainPanel.setCellHeight(headerPane, "30px");
		mainPanel.setCellHorizontalAlignment(headerPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(headerPane, DockPanel.ALIGN_MIDDLE);
    	
    	statusBarPane = new StatusBarPane(managerRegistry);
		mainPanel.add(statusBarPane, DockPanel.SOUTH); 
		mainPanel.setCellHeight(statusBarPane, "25px");
		mainPanel.setCellHorizontalAlignment(statusBarPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(statusBarPane, DockPanel.ALIGN_MIDDLE);
    	//
		taskPane = new TaskPane(managerRegistry);
        taskPane.addListener(new TaskPaneListener());
		mainPanel.add(taskPane, DockPanel.EAST);
		mainPanel.setCellHeight(taskPane, "500px");
		mainPanel.setCellVerticalAlignment(taskPane, DockPanel.ALIGN_TOP);
		mainPanel.setCellHorizontalAlignment(taskPane, DockPanel.ALIGN_LEFT);
		
		categoryPane = new CategoryPane(managerRegistry);
        categoryPane.addListener(new CategoryPaneListener());
		mainPanel.add(categoryPane, DockPanel.WEST);
		mainPanel.setCellWidth(categoryPane, "220px");
		mainPanel.setCellVerticalAlignment(categoryPane, DockPanel.ALIGN_TOP);
	
		initWidget(mainPanel);
	}

    //============================================= Inner Classes ======================================================

    protected class CategoryPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            if (event instanceof CategorySelectionEvent) {
                Category category = ((CategorySelectionEvent)event).getCategory();
                taskPane.reset(category);
            }
            statusBarPane.setMessage(event.getDescription());
        }
    }

    protected class TaskPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            statusBarPane.setMessage(event.getDescription());
        }
    }

    protected class HeaderPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            fireEvent(event); // bubble it up
        }
    }

}