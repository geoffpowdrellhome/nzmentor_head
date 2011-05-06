package com.nz.travel.mentor.util.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * Holds a single instance of the Initial Context. This is required for applications that spawn their own threads (which
 * are unmanaged and therefore do not have access to the container and container resources). A copy of the initial
 * context is stored by the initial worker thread before any child threads are created. The child threads can then use
 * this class to access the initial context.
 * @author mckeanl
 */
public class InitialContextHandler {

    private static Logger log = Logger.getLogger(InitialContextHandler.class);
    private static InitialContextHandler singletonInstance;
    private Context initialContext;

    /**
     * Default constructor.
     * @param context the InitialContext from the container
     */
    private InitialContextHandler(String contextName) throws NamingException {
        log.debug("Initialising InitialContextHandler with name: " + contextName);
        initialContext = new InitialContext();
        if (contextName != null && contextName.length() > 0) {
            initialContext = (Context) initialContext.lookup(contextName);
            // initialContext.listBindings(contextName);
        }
    }

    /**
     * Returns an instance of the singleton. May be null if not initialised.
     */
    public static InitialContextHandler getInstance() {
        return singletonInstance;
    }

    /**
     * Creates an instance of the singleton with the specified InitialContext.
     * @param context the InitialContext from the container
     */
    public static synchronized void initialise(String contextName) throws NamingException {
        if (singletonInstance == null) {
            singletonInstance = new InitialContextHandler(contextName);
        }
    }

    /**
     * Returns the Context from the container
     */
    public Context getValue() {
        return initialContext;
    }
}


