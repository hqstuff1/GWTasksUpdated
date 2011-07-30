
package com.GWTasksWithLoginPageCh5.client.ui.mainpane.categorypane;

import com.GWTasksWithLoginPageCh5.client.manager.data.DataManager;
import com.GWTasksWithLoginPageCh5.client.model.Category;
import com.GWTasksWithLoginPageCh5.client.support.async.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

/**
 * @author
 */
public class CategoryFormDialogBox extends DialogBox {

	private final static String ERROR_IMAGE_URL = "image/field-error.gif";

    private final TextBox nameField;
    private final Image nameErrorImage;
    private final TextArea descriptionField;
    private final Button submitButton;
    private final Button cancelButton;

    private final boolean editMode;
    private final Category category;
    private final CategoryPane categoryPane;
    private final DataManager dataManager;

    public CategoryFormDialogBox(CategoryPane categoryPane, DataManager dataManager) {
        this(categoryPane, dataManager, new Category(), false);
    }

    public CategoryFormDialogBox(CategoryPane categoryPane, DataManager dataManager, Category category) {
        this(categoryPane, dataManager, category, true);
    }

    private CategoryFormDialogBox(CategoryPane categoryPane, DataManager dataManager, Category category, boolean editMode) {
        super(false, true);
        setText("Category Form");
        this.category = category;
		this.categoryPane = categoryPane;
        this.dataManager = dataManager;
		this.editMode = editMode;

        VerticalPanel main = new VerticalPanel();
        main.add(new Label("Name"));
        addGap(main, "3px");

        nameField = new TextBox();
        nameErrorImage = new Image(ERROR_IMAGE_URL);
        nameErrorImage.setVisible(false);
        HorizontalPanel nameFieldRow = new HorizontalPanel();
        nameFieldRow.add(nameField);
        nameFieldRow.setCellWidth(nameField, "60%");
        nameFieldRow.add(nameErrorImage);
        main.add(nameFieldRow);
        main.setCellWidth(nameFieldRow, "100%");
        addGap(main, "10px");

        main.add(new Label("Description"));
        addGap(main, "3px");

        descriptionField = new TextArea();
        descriptionField.setSize("300px", "250px");
        main.add(descriptionField);
        addGap(main, "10px");

//----
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
 
        cancelButton = new Button("Cancel", new ClickHandler()
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
            category.setName(nameField.getText().trim());
            category.setDescription(descriptionField.getText().trim());
            if (editMode) {
                handleUpdate(category);
            } else {
                handleCreate(category);
            }
        }
    }

    protected void handleCreate(Category category) {
        Category selectedCategory = categoryPane.getSelectedCategory();
        Long parentCategoryId = selectedCategory == null ? null : selectedCategory.getId();
        dataManager.createCategory(category, parentCategoryId, new Callback<Category>() {
            public void onSuccess(Category category) {
                categoryPane.addCategory(category);
                hide();
            }
        });
    }

    protected void handleUpdate(Category category) {
        dataManager.updateCategory(category, new Callback<Void>() {
            public void onSuccess(Void result) {
                categoryPane.reloadCategories();
            }
        });
    }

    protected void handleCancel() {
        hide();
    }

    protected boolean validate() {
        String name = nameField.getText().trim();
        if (name.length() == 0) {
            nameErrorImage.setTitle("Required");
            nameErrorImage.setVisible(true);
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
   

 