package com.travel.mentor.util;

public class MessageUtil {

    protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MessageUtil.class);

    public MessageUtil() {
        if (logger.isDebugEnabled()) {
            logger.debug("MessageUtil class instantiatied ");
        }
    }

    public static Throwable getRootCause(Throwable e) {
        Throwable cause = e.getCause();
        return cause == null ? e : getRootCause(cause);
    }

}

