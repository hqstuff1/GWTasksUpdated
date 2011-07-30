package com.GWTasksWithLoginPageCh5.client.support.async;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class Callback<T> implements AsyncCallback<T> 
{
	 /**
     * Shows the message of the given caught exception using a {@link Window#alert(String)}.
     *
     * @param caught The caught exception
     */
    public void onFailure(Throwable caught) {
        Window.alert(caught.getMessage());
    }

}
