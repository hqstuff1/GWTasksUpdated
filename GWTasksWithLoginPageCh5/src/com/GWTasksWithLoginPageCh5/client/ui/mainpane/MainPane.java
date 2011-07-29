package com.GWTasksWithLoginPageCh5.client.ui.mainpane;

import com.GWTasksWithLoginPageCh5.client.ui.mainpane.categorypane.CategoryPane;
import com.GWTasksWithLoginPageCh5.client.ui.mainpane.taskpane.TaskPane;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;

public class MainPane extends Composite {

	public MainPane() {

		DockPanel mainPanel = new DockPanel();
		mainPanel.setBorderWidth(5);    	
		mainPanel.setSize("100%", "100%");
		
    	HeaderPane headerPane = new HeaderPane("GWTasks- headerPane");
    	mainPanel.add(headerPane, DockPanel.NORTH);
    	mainPanel.setCellHeight(headerPane, "30px");
		mainPanel.setCellHorizontalAlignment(headerPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(headerPane, DockPanel.ALIGN_MIDDLE);
    	
    	StatusBarPane statusBarPane = new StatusBarPane();
    	statusBarPane.setMessage("statusBarPane");
		mainPanel.add(statusBarPane, DockPanel.SOUTH); 
		mainPanel.setCellHeight(statusBarPane, "25px");
		mainPanel.setCellHorizontalAlignment(statusBarPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(statusBarPane, DockPanel.ALIGN_MIDDLE);
    		
		TaskPane taskPane = new TaskPane();
		mainPanel.add(taskPane, DockPanel.EAST);
		mainPanel.setCellHeight(taskPane, "500px");
		mainPanel.setCellVerticalAlignment(taskPane, DockPanel.ALIGN_TOP);
		mainPanel.setCellHorizontalAlignment(taskPane, DockPanel.ALIGN_LEFT);
		
		CategoryPane categoryPane = new CategoryPane(taskPane);
		mainPanel.add(categoryPane, DockPanel.WEST);
		mainPanel.setCellWidth(categoryPane, "220px");
		mainPanel.setCellVerticalAlignment(categoryPane, DockPanel.ALIGN_TOP);
	
		initWidget(mainPanel);
	}

}
