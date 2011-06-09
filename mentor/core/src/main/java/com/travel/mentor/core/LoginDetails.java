package com.travel.mentor.core;

/**
 * Domain object for the username and password for any context.
 */
public final class LoginDetails extends MentorObject {

    private final String username, password;

    public LoginDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
