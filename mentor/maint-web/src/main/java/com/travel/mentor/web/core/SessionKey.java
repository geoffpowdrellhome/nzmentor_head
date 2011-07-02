package com.travel.mentor.web.core;

import javax.servlet.http.HttpServletRequest;

public enum SessionKey {
    USER_SESSION_COOKIE;

    private String param;

    SessionKey() {
    }

    SessionKey(String param) {
        this.param = param;
    }

    String getString(HttpServletRequest request) {
        if (param != null) {
            return request.getParameter(param);
        }
        return null;
    }

}
