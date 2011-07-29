package com.GWTasksWithLoginPageCh5.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.GWTasksWithLoginPageCh5.client.events.*;

public class LoginPane extends Pane {

	private final Label messageLabel;
    private final TextBox usernameField;
    private final PasswordTextBox passwordField;

	// private final GWTasksWithLoginPageCh5 gwtasks;
	
    public LoginPane() {
        
        VerticalPanel content = new VerticalPanel();
        content.setSize("100%", "100%");

        messageLabel = new Label();
        messageLabel.setStyleName("MessageLabel");
        content.add(messageLabel);

        Grid grid = new Grid(3, 2);
        grid.setWidget(0, 0, createFieldLabel("Username:"));
        usernameField = new TextBox();
        grid.setWidget(0, 1, usernameField);
        grid.setWidget(1, 0, createFieldLabel("Password:"));
 		passwordField = new PasswordTextBox();
        grid.setWidget(1, 1, passwordField);  
        
        Button loginButton = new Button("Login");
        loginButton.addClickHandler(new ClickHandler() 
        {
        	public void onClick(ClickEvent event) {
        		 handleLogin();
        	}
        });
        grid.setWidget(2, 1, loginButton);
        grid.getCellFormatter().setStyleName(2, 1, "ButtonPanel");
        grid.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        content.add(grid);
        grid.setStyleName("Form");
        
        TitledPanel main = new TitledPanel("Login", new CenterPanel(content));
		main.setContentHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        Image registerImage = new Image("image/edit.gif");
        registerImage.setTitle("Register");
        
        main.addToolButton(registerImage, new ClickHandler()
        {	
			@Override
			public void onClick(ClickEvent event) 
			{
				handleRegister();
			}
		});
        
        main.setSize("350px", "150px");

        initWidget(new CenterPanel(main));
        setStyleName("LoginPane");
    }

    public void reset() {
        clearMessage();
        usernameField.setText("");
        passwordField.setText("");
    }

    protected Widget createFieldLabel(String text) {
        Label label = new Label(text);
        label.setStyleName("FieldLabel");
        return label;
    }

	protected void handleLogin() {
		clearMessage();
		String username = usernameField.getText().trim();
		String password = passwordField.getText().trim();
		if (authenticate(username, password)) {
            fireEvent(new LoginEvent(this, username)); //replaced: gwtasks.showMainPane();
        } else {
            showErrorMessage("Invalid username and/or password");
            passwordField.setText("");
        }
	}

    protected boolean authenticate(String username, String password) {
        clearMessage();
        // authenticate the user
        return true;
    }

    protected void handleRegister() {
    	RegistrationFormDialogBox dialog = new RegistrationFormDialogBox(this);
    	dialog.center();
    	dialog.show();
    }

    protected void clearMessage() {
        messageLabel.removeStyleDependentName("error");
        messageLabel.removeStyleDependentName("info");
        messageLabel.setText("");
        messageLabel.setVisible(false);
    }

    protected void showErrorMessage(String message) {
        showMessage(message, "error");
    }

    protected void showInfoMessage(String message) {
        showMessage(message, "info");
    }

    protected void showMessage(String message, String style) {
        clearMessage();
        messageLabel.addStyleDependentName(style);
        messageLabel.setText(message);
        messageLabel.setVisible(true);
    }


}
