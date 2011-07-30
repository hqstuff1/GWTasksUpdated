
package com.GWTasksWithLoginPageCh5.client.manager.security;

import com.GWTasksWithLoginPageCh5.client.model.Account;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Map;
import java.util.HashMap;

/**
 * Default in-memory implementation of the {@link MySecurityManager}.
 *
 * @author 
 */
public class InMemorySecurityManager implements MySecurityManager 
{

    private final static Map<String, Account> accountByUsername = new HashMap<String, Account>();
    private static Authentication authentication = Authentication.ANONYMOUS;
    private static long accountIdCounter = 0;

    public InMemorySecurityManager(){}
    
    /**
     * {@inheritDoc}
     */
    public void createAccount(Account account, AsyncCallback<Account> callback) 
    {
        Account newAccount = new Account(++accountIdCounter, account);
        accountByUsername.put(newAccount.getUsername(), newAccount);
        callback.onSuccess(newAccount);
    }

    /**
     * {@inheritDoc}
     */
    public void login(Authentication auth, AsyncCallback<Account> callback) 
    {
        if (auth == Authentication.ANONYMOUS) {
            throw new IllegalArgumentException("Cannot login anonymous authentication");
        }
        Account account = accountByUsername.get(auth.getUsername());
        if (account == null || !auth.getPassword().equals(account.getPassword())) {
            callback.onFailure(new AuthenticationException("Could not authenticate '" + auth.getUsername() + "'"));
        } else {
             authentication = new Authentication(account.getUsername(), account.getPassword());
            callback.onSuccess(account);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logout() 
    {
        authentication = Authentication.ANONYMOUS;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLoggedIn() 
    {
        return authentication != Authentication.ANONYMOUS;
    }

    /**
     * {@inheritDoc}
     */
    public Authentication getCurrentAuthentication() {
        return authentication;
    }

}
