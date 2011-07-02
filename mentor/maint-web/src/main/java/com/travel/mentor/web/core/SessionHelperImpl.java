package com.travel.mentor.web.core;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionHelperImpl implements SessionHelper, Serializable {

    private Map<SessionKey, Object> attributes = new HashMap<SessionKey, Object>();

    public void remove(SessionKey key) {
        attributes.remove(key);
    }

    public <T> T get(SessionKey key) {
        return (T) attributes.get(key);
    }

    public <T> T get(SessionKey key, HttpServletRequest request) {
        String param = key.getString(request);
        if (param != null) {
            attributes.put(key, param);
        }
        return (T) attributes.get(key);
    }

    public Long getLong(SessionKey key, HttpServletRequest request) {
        setLong(key, request);
        return (Long) attributes.get(key);
    }

    public Long getLong(SessionKey key) {
        return (Long) attributes.get(key);
    }

    public void set(Sessionable value) {
        attributes.put(value.getSessionKey(), value);
    }

    public void set(SessionKey key, Object value) {
        if (!(value instanceof ServletRequest)) {
            attributes.put(key, value);
        }
    }

    public void setLong(SessionKey key, HttpServletRequest request) {
        String param = key.getString(request);
        if (param != null) {
            attributes.put(key, new Long(param));
        }
    }

     public void setString(SessionKey key, HttpServletRequest request) {
        String param = key.getString(request);
        if (param != null) {
            attributes.put(key, param);
        }
    }

}

