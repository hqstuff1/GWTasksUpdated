
package com.GWTasksWithLoginPageCh5.client.manager.security;

/**
 * Thrown when an authentication fails in the SecurityManager.
 *
 * @author 
 */
public class AuthenticationException extends Exception {

	private static final long serialVersionUID = 7228256190491051772L;

	/**
     * Constructs a new AuthenticationException with a given message.
     *
     * @param message The given message.
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * Constructs a new AuthenticationException with a given message and the root cause.
     *
     * @param message The given message.
     * @param cause The root cause of the failure.
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
