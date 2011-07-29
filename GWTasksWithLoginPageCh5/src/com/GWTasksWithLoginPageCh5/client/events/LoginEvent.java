package com.GWTasksWithLoginPageCh5.client.events;


/**
 * Triggered when a user successfully logged in the application.
 */
public class LoginEvent extends ApplicationEvent 
{
   private final String username;
   
   /**
    * Constructs a new LoginEvent.
    *
    * @param source The source of this event.
    * @param username The username of the logged in user.
    */
   public LoginEvent(ApplicationEventSource source, String username)
   {
	   super(source);  //store the source in the ApplicationEvent (base class)
	   this.username = username;
   }
   
   /**
    * {@inheritDoc}
    */
   public String getDescription()
   {
	   return "'" + username + "' logged in";
   }
   
   /**
    * Returns the  username of the logged in user.
    * @return The username of the logged in user.
    */
   public String getUsername()
   {
	   return username;
   }
}
