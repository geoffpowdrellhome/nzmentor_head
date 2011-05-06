package com.nz.travel.mentor.util;

import net.flitech.faregate.air.StatusMessage;

public class MessageUtil {

    protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MessageUtil.class);

    public MessageUtil() {
        if (logger.isDebugEnabled()) {
            logger.debug("MessageUtil class instantiatied ");
        }
    }

    public static StatusMessage createStatusMessage(String errorCode, String errorMsg) {
        StatusMessage message = new StatusMessage();
        message.setType(net.flitech.faregate.air.StatusType.ERROR);
        message.setCode(errorCode);
        message.setText(errorMsg);
        return message;
    }

    public static Throwable getRootCause(Throwable e) {
        Throwable cause = e.getCause();
        return cause == null ? e : getRootCause(cause);
    }

}

