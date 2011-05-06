package com.nz.travel.mentor.util.impl;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


/**
 * JNDI utility class.
 * @author Ben Warren
 */
public final class JndiUtil {

    private static final Logger LOG = Logger.getLogger(JndiUtil.class);

    private JndiUtil() {}

    /**
     * Get a JNDI referenced String object.
     * @param jndiName The name of the string.
     * @return The value of the string object.
     */
    public static String getJndiString(String jndiName) {
        return (String) getJndiObject(jndiName);
    }

    /**
     * Get a JNDI referenced object.
     * @param jndiName The name of the object.
     * @return The value of the object.
     */
    private static Object getJndiObject(String jndiName) {
        return lookupString(jndiName);
    }

    /**
     * Lookup a JNDI String resource.
     * @param key
     * @return null if not found.
     */
    private static Object lookupString(String key) {
        try {
            final InitialContextHandler initialContextHandler = InitialContextHandler.getInstance();
            if (initialContextHandler == null) {
                return null;
            }
            final Context env = InitialContextHandler.getInstance().getValue();
            return env.lookup(key);
        } catch (NamingException e) {
            LOG.warn("Name '" + key + "' not found in JNDI lookup");
            return null;
        }
    }
}


