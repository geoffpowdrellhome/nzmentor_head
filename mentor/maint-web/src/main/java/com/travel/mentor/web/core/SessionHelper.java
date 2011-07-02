package com.travel.mentor.web.core;

public interface SessionHelper {

    void remove(SessionKey key);

    <T> T get(SessionKey key);

    Long getLong(SessionKey key);

    void set(Sessionable value);

    void set(SessionKey key, Object value);
}

