package com.GWTasksWithLoginPageCh5.client.support.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.*;

/**
 * A panel that can hold only one widget in its center point.
 *
 * @author 
 */

public class CenterPanel extends Composite {

    private DockPanel container;

    private Widget content;
    
    public CenterPanel() {
        this(null);
    }

    /**
     * 
     * @wbp.parser.constructor
     */
    public CenterPanel(Widget content) {
        container = new DockPanel();
        if (content != null) {
            setContent(content);
        }
        initWidget(container);
    }

    public void setContent(Widget content) {
        if (this.content != null) {
            container.remove(this.content);
        }
        this.content = content;
        container.add(content, DockPanel.CENTER);
        container.setCellHorizontalAlignment(content, DockPanel.ALIGN_CENTER);
        container.setCellVerticalAlignment(content, DockPanel.ALIGN_MIDDLE);
    }

    public Widget getContent() {
        return content;
    }
}

//if you have more than one constructor and want to display in the design view:
/*
The parser starts parsing from known method(s) for each GUI toolkit.
For example, for Swing it starts from the constructor of a JPanel or JFrame. 
However, if you have more than one constructor, the parser can't decide which to use, 
so you should help it by adding a @wbp.parser.constructor JavaDoc tag to the constructor that should be used as the entry point. 

For example:

	public class MyPanel extends JPanel {
		/**
		 * @wbp.parser.constructor
		 */
/*		public MyPanel(String text) {
		}
		public MyPanel(Icon icon) {
		}
	}
		*/
