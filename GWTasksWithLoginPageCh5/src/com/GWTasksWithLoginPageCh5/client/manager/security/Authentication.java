

package com.GWTasksWithLoginPageCh5.client.manager.security;

/**
 * Holds user login information
 *
 * @author 
 */
public class Authentication {

    /**
     * An authentication object representing an anonymous user.
     */
    public final static Authentication ANONYMOUS = new Authentication("anonymous", "anonymous");

    private final String username;
    private final String password;

    /**
     * Constructs a new Authentication with given username and password.
     *
     * @param username The username.
     * @param password The password.
     */
    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of this authentication.
     *
     * @return The username of this authentication.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of this authentication.
     *
     * @return The password of this authentication.
     */
    public String getPassword() {
        return password;
    }

}
