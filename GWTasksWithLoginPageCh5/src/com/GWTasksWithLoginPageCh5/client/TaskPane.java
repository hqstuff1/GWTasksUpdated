
package com.GWTasksWithLoginPageCh5.client;

import com.GWTasksWithLoginPageCh5.client.Category;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;


import java.util.*;

/**
 * @author 
 */
public class TaskPane extends Composite {

	private final FlexTable taskTable;
	private int selectedRow = -1;
	private Task selectedTask;
	private Category category;
	private PushButton addButton;
	private PushButton removeButton;
		
	public TaskPane() {
	
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
				TaskFormDialogBox dialog = new TaskFormDialogBox(TaskPane.this);
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
				if (selectedRow != -1) {
					// removing selectedTask
					selectedRow = -1;
					selectedTask = null;
					removeButton.setEnabled(false);
					reset(category);
				}
			}
		});
		removeButton.setEnabled(false);
		
		initWidget(titledPanel);
	}
	
	/**
	 * reset the viewed task list to show only the tasks
of the given category, or no tasks at all if the category is null.
	 */
	public void reset() {
		reset(null);
	}
	
	public void reset(Category category) {
		while (taskTable.getRowCount() > 1) {
			taskTable.removeRow(taskTable.getRowCount()-1);
		}
		this.category = category;
		if (category != null) {
			List<Task> tasks = getTasksForCategory(category);
			for (Task task : tasks) {
				addTask(task);
			}
			addButton.setEnabled(true);
		}
	}

	
	
	//=========================================== Helper Methods =======================================================
	
	protected void addTask(final Task task) {
		final int row = taskTable.getRowCount();
		taskTable.setWidget(row, 0, new CheckBox());
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
	
	protected void handleTaskRowClicked(int row, Task task) {
		HTMLTable.RowFormatter rowFormatter = taskTable.getRowFormatter();
		if (selectedRow == row) {
			selectedRow = -1;
			selectedTask = null;
			rowFormatter.removeStyleName(row, "TaskRow-selected");
			removeButton.setEnabled(false);
		} else {
			if (selectedRow != -1) {
				rowFormatter.removeStyleName(selectedRow, "TaskRow-selected");
				removeButton.setEnabled(false);
			}
			selectedRow = row;
			selectedTask = task;
			taskTable.getRowFormatter().addStyleName(row, "TaskRow-selected");
			removeButton.setEnabled(true);
		}
	}
	
	protected List<Task> getTasksForCategory(Category category) {
		List<Task> tasks = new ArrayList<Task>();
		if (category == null) {
			return tasks;
		}
		if (category.getId() == 1L) {
			tasks.add(new Task(1L, "Bread", "1xwhite and 2xdark"));
			tasks.add(new Task(2L, "vegetables", "Tomatoes, Cucumbers and Lettuce"));
			tasks.add(new Task(3L, "Fruits", "Apples, Pears, Melon and Mango"));
			tasks.add(new Task(4L, "Milk", "2xnormal and 1xsoya"));
			return tasks;
		}
		if (category.getId() == 2L) {
			tasks.add(new Task(5L, "Contact project manager", "Discuss the status of the project"));
			tasks.add(new Task(6L, "Email HR manager", "Ask for vacation on December"));
			tasks.add(new Task(7L, "Setup meeting with client", "Discuss the progress of the project"));
			return tasks;
		}
		if (category.getId() == 3L) {
			tasks.add(new Task(8L, "Vacuum clean the house", Task.Priority.LOW));
			tasks.add(new Task(9L, "Pick up the kids from school", "At 15:00"));
			tasks.add(new Task(10L, "Arrange babysitter", "For the upcoming weekend"));
			return tasks;
		}
		
		return tasks;
	}

}
