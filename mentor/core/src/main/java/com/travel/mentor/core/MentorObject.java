package com.travel.mentor.core;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;

// TODO remove Serializable and only make objects Serializable when they need it to reduce warnings
/**
 * A primordial object for Mentor.
 */
@SuppressWarnings("serial")
public class MentorObject implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * This field is transient so it is not serialized or printed in StringBuilder.reflectionToString().
     */
    protected transient Logger logger = Logger.getLogger(getClass());

    @Override
    public String toString() {
        return this.toString();
        //return ToStringUtil.getInstance().reflectionToString(this);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object.getClass() != getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}


