
package com.GWTasksWithLoginPageCh5.client.ui.mainpane.taskpane;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.GWTasksWithLoginPageCh5.client.manager.ManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.model.*;
import com.GWTasksWithLoginPageCh5.client.support.async.Callback;
import com.GWTasksWithLoginPageCh5.client.support.widget.*;
import com.GWTasksWithLoginPageCh5.client.ui.Pane;
import com.GWTasksWithLoginPageCh5.client.ui.event.TaskSelectionEvent;

import java.util.*;

/**
 * A {@link Pane} showing a list of all the tasks for a chosen category. It allows adding/removing tasks as well
 * as un/selecting them.
 *
 * @author 
 */
public class TaskPane extends Pane {

	private final FlexTable taskTable;
	private PushButton addButton;
	private PushButton removeButton;
	private int selectedRow = -1;
	private Task selectedTask;
	private Category category;

    /**
     * Constructs a new TaskPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate with this pane.
     */	
	public TaskPane(ManagerRegistry managerRegistry) {
	    super(managerRegistry);

		taskTable = new FlexTable();
		taskTable.getColumnFormatter().setWidth(0, "20px");
		taskTable.getColumnFormatter().setWidth(1, "20px");
        taskTable.getColumnFormatter().setWidth(2, "100%");
	
		Label checkHeaderLabel = new Label();
        checkHeaderLabel.setWidth("20px");
        checkHeaderLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
        taskTable.setWidget(0, 0, checkHeaderLabel);

        Label priorityHeaderLabel = new Label("!");
        priorityHeaderLabel.setWidth("20px");
        priorityHeaderLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
        taskTable.setWidget(0, 1, priorityHeaderLabel);

        Label titleHeaderLabel = new Label("Title");
        titleHeaderLabel.setWidth("100%");
        taskTable.setWidget(0, 2, titleHeaderLabel);

        taskTable.getRowFormatter().setStyleName(0, "TableHeader");
	
		TitledPanel titledPanel = new TitledPanel("Tasks", taskTable);
		titledPanel.setContentVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		titledPanel.setSize("100%", "100%");
				
		addButton = titledPanel.addToolButton("+", "Add Task", new ClickHandler() 
		{
			@Override
			public void onClick(ClickEvent event) 
			{
				TaskFormDialogBox dialog = new TaskFormDialogBox(TaskPane.this, getManagerRegistry().getDataManager());
				dialog.center();
				dialog.show();	
			}
		});
		addButton.setEnabled(false);
		
		removeButton = titledPanel.addToolButton("-", "Remove Selected Task", new ClickHandler()
		{	
			@Override
			public void onClick(ClickEvent event) 
			{
				if (selectedRow == -1) 
				{
                    return;
                }
                getManagerRegistry().getDataManager().removeTask(selectedTask.getId(), new Callback<Void>() 
                {
                    public void onSuccess(Void result) 
                    {
                        selectedRow = -1;
                        selectedTask = null;
                        removeButton.setEnabled(false);
                        reset(category);
                    }
                });
			}
		});
		removeButton.setEnabled(false);
		
	    SimplePanel mainPanel = new SimplePanel();
        mainPanel.setWidget(titledPanel);
		initWidget(mainPanel);
        setStyleName("TaskPane");
	}

    /**
     * Reloads the the current tasks based on the currently associated category. If the associated category is
     * <code>null</code>, this call has the same effect as calling <code>reset(getCurrentCategory())</code>.
     */
    public void reloadTasks() {
        reset(category);
    }

    /**
     * Resets this pane, that is, removing its current category association and the displayed tasks.
     */
	public void reset() {
		reset(null);
	}

    /**
     * Resets this pane to be associated with the given category and displays all its tasks. If the given category
     * is <code>null</code> all currently displayed tasks will be removed.
     *
     * @param category The newly associated category.
     */
    public void reset(Category category) {
		while (taskTable.getRowCount() > 1) {
			taskTable.removeRow(taskTable.getRowCount()-1);
		}
		this.category = category;
		if (category == null) {
            return;
        }
        getManagerRegistry().getDataManager().getTasks(category.getId(), new Callback<List<Task>>() {
            public void onSuccess(List<Task> tasks) {
                for (Task task : tasks) {
                    addTask(task);
                }
                addButton.setEnabled(true);
            }
        });
	}

    /**
     * Returns the currently associated category, that is, the category whose tasks should be displayed.
     *
     * @return The currently category associated with this pane.
     */
    public Category getCurrentCategory() {
        return category;
    }
	
	//=========================================== Helper Methods =======================================================

    /**
     * Adds a task to the tasks list.
     *
     * @param task The task to be added.
     */
	protected void addTask(final Task task) {
		final int row = taskTable.getRowCount();
        final CheckBox checkBox = new CheckBox();
        checkBox.setValue(task.isClosed(), false);

        checkBox.addClickHandler(new ClickHandler()
        {
			@Override
			public void onClick(ClickEvent event) 
			{
				handleTaskClose(task, checkBox, row);
			}
		});
        
        taskTable.setWidget(row, 0, checkBox);
        String priorityName = task.getPriority().name();
		Label priorityLabel = new Label(priorityName.substring(0, 1));
		Label titleLabel = new Label(task.getTitle());
		titleLabel.setStyleName("TaskRow");
		titleLabel.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event) 
			{
				handleTaskRowClicked(row, task);
			}
		});
		
		priorityLabel.setStyleName("PriorityLabel-" + priorityName.toLowerCase());
		taskTable.setWidget(row, 1, priorityLabel);
		taskTable.setWidget(row, 2, titleLabel);
	}

    protected void handleTaskClose(final Task task, final CheckBox checkBox, final int row) {
		task.setClosed(checkBox.getValue() );//checkBox.isChecked()) - deprecated;
		getManagerRegistry().getDataManager().updateTask(task, new Callback<Void>() {
			public void onSuccess(Void result) {
                if (checkBox.getValue()) {
                    taskTable.getRowFormatter().addStyleName(row, "TaskRow-disabled");
                } else {
                    taskTable.getRowFormatter().removeStyleName(row, "TaskRow-disabled");
                }
			}
			public void onFailure(Throwable caught) 
			{
				checkBox.setValue(!checkBox.getValue());
			}
		});
	}

	protected void handleTaskRowClicked(int row, Task task) {
		HTMLTable.RowFormatter rowFormatter = taskTable.getRowFormatter();
		if (selectedRow == row) {
			selectedRow = -1;
			selectedTask = null;
			rowFormatter.removeStyleName(row, "TaskRow-selected");
            removeButton.setEnabled(false);
            fireEvent(new TaskSelectionEvent(this, null));
		} else {
			if (selectedRow != -1) {
				rowFormatter.removeStyleName(selectedRow, "TaskRow-selected");
				removeButton.setEnabled(false);
                fireEvent(new TaskSelectionEvent(this, null));
			}
			selectedRow = row;
			selectedTask = task;
			taskTable.getRowFormatter().addStyleName(row, "TaskRow-selected");
			removeButton.setEnabled(true);
            fireEvent(new TaskSelectionEvent(this, task));
		}
	}

}
