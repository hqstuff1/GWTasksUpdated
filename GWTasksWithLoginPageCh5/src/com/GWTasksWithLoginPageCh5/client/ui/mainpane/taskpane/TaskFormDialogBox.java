
package com.GWTasksWithLoginPageCh5.client.ui.mainpane.taskpane;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.GWTasksWithLoginPageCh5.client.manager.data.DataManager;
import com.GWTasksWithLoginPageCh5.client.model.*;
import com.GWTasksWithLoginPageCh5.client.support.async.Callback;

public class TaskFormDialogBox extends DialogBox {

	private final static String ERROR_IMAGE_URL = "image/field-error.gif";

	private final TextBox titleField;
    private final Image titleErrorImage;
	private final ListBox priorityField;
    private final TextArea descriptionField;
    private final Button submitButton;
    private final Button cancelButton;

    private final Task task;
    private final TaskPane taskPane;
    private final DataManager dataManager;
    private final boolean editMode;

    public TaskFormDialogBox(TaskPane taskPane, DataManager dataManager) {
        this(taskPane, new Task(), dataManager, false);
    }

    public TaskFormDialogBox(TaskPane taskPane, Task task, DataManager dataManager) {
        this(taskPane, task, dataManager, true);
    }

    private TaskFormDialogBox(TaskPane taskPane, Task task, DataManager dataManager, boolean editMode) {
        super(false, true);
        this.task = task;
		this.taskPane = taskPane;
		this.dataManager = dataManager;
        this.editMode = editMode;

        setText("Task Form");

        VerticalPanel main = new VerticalPanel();
        main.add(new Label("Title"));
        addGap(main, "3px");

        titleField = new TextBox();
        titleErrorImage = new Image(ERROR_IMAGE_URL);
        titleErrorImage.setVisible(false);
        HorizontalPanel titleFieldRow = new HorizontalPanel();
        titleFieldRow.add(titleField);
        titleFieldRow.setCellWidth(titleField, "60%");
        titleFieldRow.add(titleErrorImage);
        main.add(titleFieldRow);
        main.setCellWidth(titleFieldRow, "100%");
        addGap(main, "10px");

		main.add(new Label("Priority"));
		addGap(main, "3px");
		
		priorityField = new ListBox(false);
        priorityField.setVisibleItemCount(1);
        priorityField.addItem("LOW");
        priorityField.setItemText(0, "Low");
        priorityField.addItem("NORMAL");
        priorityField.setItemText(1, "Normal");
        priorityField.addItem("HIGH");
        priorityField.setItemText(2, "High");
        priorityField.setItemSelected(1, true);
		main.add(priorityField);
		addGap(main, "10px");
		
        main.add(new Label("Description"));
        addGap(main, "3px");

        descriptionField = new TextArea();
        descriptionField.setSize("300px", "250px");
        main.add(descriptionField);
        addGap(main, "10px");

        HorizontalPanel buttons = new HorizontalPanel();
        submitButton = new Button(editMode ? "Edit" : "Add");
        submitButton.addClickHandler(new ClickHandler() 
        {	
			@Override
			public void onClick(ClickEvent event)
			{
				handleSubmit();
			}
		});
        
        cancelButton = new Button("Cancel");
        cancelButton.addClickHandler(new ClickHandler()
        {
			@Override
			public void onClick(ClickEvent event) 
			{
				handleCancel();
			}
		});
  
        buttons.add(submitButton);
        addGap(buttons, "5px");
        buttons.add(cancelButton);
        main.add(buttons);
        addGap(main, "10px");
        main.setCellHorizontalAlignment(buttons, VerticalPanel.ALIGN_CENTER);
        main.setCellVerticalAlignment(buttons, VerticalPanel.ALIGN_MIDDLE);

        SimplePanel content = new SimplePanel();
        content.setWidget(main);
        content.setStyleName("DialogContent");
        setWidget(content);
    }
    

    //============================================== Helper Methods ====================================================

    protected void handleSubmit() {
        if (validate()) {
            task.setTitle(titleField.getText().trim());
            task.setPriority(resolveSelectedPriority());
            task.setDescription(descriptionField.getText().trim());
            if (editMode) {
                handleUpdate(task);
            } else {
                handleCreate(task);
            }
        }
    }

    protected void handleCreate(Task task) {
        dataManager.createTask(task, taskPane.getCurrentCategory().getId(), new Callback<Task>() {
            public void onSuccess(Task task) {
                taskPane.addTask(task);
                hide();
            }
        });
    }

    protected void handleUpdate(Task task) {
        dataManager.updateTask(task, new Callback<Void>() {
                public void onSuccess(Void result) {
                    taskPane.reloadTasks();
                    hide();
                }
            });
    }

    protected void handleCancel() {
        hide();
    }
    
    protected Task.Priority resolveSelectedPriority() {
    	int index = priorityField.getSelectedIndex();
        String value = priorityField.getValue(index);
        return Task.Priority.valueOf(value);
    }

    protected boolean validate() {
        String title = titleField.getText().trim();
        if (title.length() == 0) {
            titleErrorImage.setTitle("Required");
            titleErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected void addGap(HorizontalPanel panel, String width) {
        Label label = new Label();
        panel.add(label);
        panel.setCellWidth(label, width);
    }

    protected void addGap(VerticalPanel panel, String height) {
        Label label = new Label();
        panel.add(label);
        panel.setCellHeight(label, height);
    }

}
