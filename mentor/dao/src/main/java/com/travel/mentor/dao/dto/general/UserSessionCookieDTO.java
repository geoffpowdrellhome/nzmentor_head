package com.travel.mentor.dao.dto.general;

import java.io.Serializable;

public class UserSessionCookieDTO implements Serializable {

    protected UserDTO userDTO;
    protected String sessionId;

    public UserSessionCookieDTO() {}

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
