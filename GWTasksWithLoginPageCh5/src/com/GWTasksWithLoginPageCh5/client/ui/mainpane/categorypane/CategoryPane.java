
package com.GWTasksWithLoginPageCh5.client.ui.mainpane.categorypane;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.GWTasksWithLoginPageCh5.client.manager.ManagerRegistry;
import com.GWTasksWithLoginPageCh5.client.model.*;

import java.util.*;

import com.GWTasksWithLoginPageCh5.client.support.async.Callback;
import com.GWTasksWithLoginPageCh5.client.support.widget.*;
import com.GWTasksWithLoginPageCh5.client.ui.Pane;
import com.GWTasksWithLoginPageCh5.client.ui.event.CategoryCreatedEvent;
import com.GWTasksWithLoginPageCh5.client.ui.event.CategorySelectionEvent;

/**
 * @author 
 */
public class CategoryPane extends Pane {

	private final Tree tree;
	private final PushButton addButton;
	private final PushButton removeButton;

    /**
     * Constructs a new CategoryPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate this pane with.
     */
    public CategoryPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);

		tree = new Tree();
		
		tree.addHandler(new ClickHandler() 
		{
			@Override
			public void onClick(ClickEvent event) 
			{
				TreeItem item = tree.getSelectedItem();
				Category category = ((CategoryTreeItem)item).getCategory();
			    fireEvent(new CategorySelectionEvent(CategoryPane.this, category));
                removeButton.setEnabled(true);
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
		
	/*	List<Category> categories = getAllCategories();
		for (final Category category : categories) {
			CategoryTreeItem item = createTreeItem(category);
			tree.addItem(item);
		}*/

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
				 CategoryFormDialogBox dialog =
	                        new CategoryFormDialogBox(CategoryPane.this, getManagerRegistry().getDataManager());
				dialog.center();
				dialog.show();
			}
		});

		removeButton = titledPanel.addToolButton("-", "Remove Category",new ClickHandler()
		{
			
			@Override
			public void onClick(ClickEvent event) 
			{
				final CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
                if (item == null) 
                {
                    return;
                }
                Long categoryId = item.getCategory().getId();
                getManagerRegistry().getDataManager().removeCategory(categoryId, new Callback<Void>() 
                {
                    public void onSuccess(Void result)
                    {
                        item.remove();
                        removeButton.setEnabled(false);
                        fireEvent(new CategorySelectionEvent(CategoryPane.this, null));
                    }
                });
			}
		});
		
		removeButton.setEnabled(false);
		
		initWidget(titledPanel);
	    reloadCategories();
	}	

    /**
     * Adds the given category to this pane. The category will be added as a child of the currently
     * selected category. If no category is currently selected, it will be added as a top level category.
     *
     * @param category The category to be added.
     */
    public void addCategory(Category category) {
        final CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
        CategoryTreeItem newItem = createTreeItem(category);
        if (item == null) {
            tree.addItem(newItem);
        } else {
            item.addItem(newItem);
        }
        fireEvent(new CategoryCreatedEvent(CategoryPane.this, category));
    }

    //=========================================== Helper Methods =======================================================

    protected CategoryTreeItem createTreeItem(Category category) {
        CategoryTreeItem item = new CategoryTreeItem(category);
        for (Category child : category.getChildren()) {
            item.addItem(createTreeItem(child));
        }
        return item;
    }

    protected void reloadCategories() {
        getManagerRegistry().getDataManager().getCategories(new Callback<List<Category>>() {
            public void onSuccess(List<Category> categories) {
                for (final Category category : categories) {
                    CategoryTreeItem item = createTreeItem(category);
                    tree.addItem(item);
                }
            }
        });
    }

    public Category getSelectedCategory() {
        CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
        return item != null ? item.getCategory() : null;
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
