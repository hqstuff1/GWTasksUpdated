
package com.GWTasksWithLoginPageCh5.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import java.util.*;

/**
 * @author 
 */
public class CategoryPane extends Composite {

	private final Tree tree;
	private final TaskPane taskPane;
	private final PushButton addButton;
	private final PushButton removeButton;

	public CategoryPane(TaskPane taskPane) {
		this.taskPane = taskPane;
		tree = new Tree();
		
		tree.addHandler(new ClickHandler() 
		{
			@Override
			public void onClick(ClickEvent event) 
			{
				TreeItem item = tree.getSelectedItem();
				Category category = ((CategoryTreeItem)item).getCategory();
			    CategoryPane.this.taskPane.reset(category);
			}
		}, ClickEvent.getType()  );
		
/*	OLD CODE - deprecated: 
 * 			tree.addTreeListener(new TreeListener() {
			public void onTreeItemSelected(TreeItem item) {
		        Category category = ((CategoryTreeItem)item).getCategory();
		        CategoryPane.this.taskPane.reset(category);
		    }

		    public void onTreeItemStateChanged(TreeItem item) {
		    }
		});*/
		
		List<Category> categories = getAllCategories();
		for (final Category category : categories) {
			CategoryTreeItem item = createTreeItem(category);
			tree.addItem(item);
		}

		TitledPanel titledPanel = new TitledPanel("Categories", tree);
		titledPanel.setContentVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		titledPanel.setSize("100%", "100%");
		
		/*
		  The SimplePanel
is another GWT panel that just serves as a wrapper around a single widget. This wrapping
enables you to set different style names for the different panes. If we hadn’t done so (and passed
the titled panel as the argument to the initWidget method), each titled panel in the different
pane would have a different style name, and we would have lost the default styles we’ve just
configured. Now, not only do we still gain from the default styles, but we can still customize
each titled pane using nested styles (as shown in Listing 5-32).
Example:
Listing 5-32. Customizing the TitledPanel of the CategoryPane
.CategoryPane .TitledPanel {

}        */
		
/*		SimplePanel main = new SimplePanel();
		main.setWidget(titledPanel);
		initWidget(main);
		setStyleName("CategoryPane");
*/
		addButton = titledPanel.addToolButton("+", "Add Category", new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event) 
			{
				CategoryFormDialogBox dialog = new CategoryFormDialogBox(CategoryPane.this);
				dialog.center();
				dialog.show();
			}
		});

		removeButton = titledPanel.addToolButton("-", "Remove Category",new ClickHandler()
		{
			
			@Override
			public void onClick(ClickEvent event) 
			{
				CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
				if (item != null) 
				{
					Category category = item.getCategory();
					item.remove();
					removeButton.setEnabled(false);
					CategoryPane.this.taskPane.reset();
				}
			}
		});
		
		removeButton.setEnabled(false);
		
		initWidget(titledPanel);
	}	
	
	/**
	 * Adds the given category to this pane. The category will be added as a child of the currently
	 * selected category. If no category is currently selected, it will be added as a top level category.
	 *
	 * @param category The category to be added.
	 */
	public void addCategory(Category category)
	{
		CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
		if (item == null) 
		{
			tree.addItem(createTreeItem(category));
		} 
		else 
		{
			item.addItem(createTreeItem(category));
		}
	}
	//=========================================== Helper Methods =======================================================
	
		protected CategoryTreeItem createTreeItem(Category category) {
			CategoryTreeItem item = new CategoryTreeItem(category);
			for (Category child : category.getChildren()) {
				item.addItem(createTreeItem(child));
			}
			return item;
		}
		
		protected List<Category> getAllCategories() {
			List<Category> categories = new ArrayList<Category>();
			
			Category work = new Category(1L, "Work", "Things at work");
			work.addChildCategory(new Category(2L, "Calls", "Make phone calls"));
			work.addChildCategory(new Category(3L, "Meetings", "People I need to meet with"));
			categories.add(work);
			Category home = new Category(4L, "Home", "Things at home");
			home.addChildCategory(new Category(5L, "Shoppings", "Things I need to buy"));
			home.addChildCategory(new Category(6L, "Bills", "Bills I need to sort"));
			categories.add(home);
			categories.add(new Category(3L, "Others", "Other things I need to do"));
			return categories;
		}
		
		
		//============================================ Inner Classes =======================================================
		
		protected class CategoryTreeItem extends TreeItem {
			
			public CategoryTreeItem(Category category) {
				super(category.getName());
				setTitle(category.getDescription());
				setUserObject(category);
			}
			
			public Category getCategory() {
				return (Category) getUserObject();
			}
		}

	}

