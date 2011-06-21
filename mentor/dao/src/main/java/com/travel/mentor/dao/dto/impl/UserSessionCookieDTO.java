package com.travel.mentor.dao.dto.impl;

import java.io.Serializable;

public class UserSessionCookieDTO implements Serializable {

    protected Long userId;
    protected String sessionId;

    public UserSessionCookieDTO() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
