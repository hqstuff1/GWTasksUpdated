

package com.GWTasksWithLoginPageCh5.client.ui.mainpane;

import com.google.gwt.user.client.ui.*;


/**
 * The header panel.
 *
 * @author 
 */
public class HeaderPane extends Composite {

	private final Label title;

	/**
	 * Constructs a new HeaderPane that displays the given text.
	 */
	public HeaderPane(String titleText) {
		title = new Label(titleText);
		initWidget(title);  //the label title is wrapped by the composite
	}
	
	/**
	 * Sets the text that should be displayed in this header pane.
	 */
	public void setTitleText(String titleText) {
		title.setText(titleText);
	}
}
