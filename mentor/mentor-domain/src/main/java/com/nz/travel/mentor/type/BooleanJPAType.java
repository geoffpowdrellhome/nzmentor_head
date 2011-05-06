package com.nz.travel.mentor.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.EnhancedUserType;

/**
 * A Hibernate/JPA type for @TypeDef for reading a Y/N database field into a Boolean datatype.
 * @see http://www.codeweblog.com/int-field-with-hibernate-mapping-to-the-enumerated-type/
 */
public class BooleanJPAType implements EnhancedUserType, Serializable {

    private static final String YES = "Y";
    private static final String NO = "N";
    private static final int[] SQL_TYPES = new int[] {Types.VARCHAR};

    @Override
    public Object fromXMLString(String string) {
        return YES.equals(string) ? Boolean.TRUE : Boolean.FALSE;
    }

    // TODO implement
    @Override
    public String objectToSQLString(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toXMLString(Object object) {
        return toString(object);
    }

    @Override
    public Object assemble(Serializable cached, Object value) throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) return true;
        if (x == null || y == null) return false;
        Boolean bx = (Boolean) x;
        Boolean by = (Boolean) y;
        return bx.equals(by);
    }

    @Override
    public int hashCode(Object object) throws HibernateException {
        if (object == null) return 0;
        return object.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] strings, Object object) throws HibernateException, SQLException {
        return nullSafeGet(rs, strings[0]);
    }

    private Object nullSafeGet(ResultSet rs, String string) throws HibernateException, SQLException {
        if (string == null) return Boolean.FALSE;
        String value = (String) Hibernate.STRING.nullSafeGet(rs, string);
        return YES.equals(value) ? Boolean.TRUE : Boolean.FALSE;
    }

    // TODO implement
    @Override
    public void nullSafeSet(PreparedStatement ps, Object object, int column) throws HibernateException, SQLException {
        ps.setString(column, toString(object));
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Class<?> returnedClass() {
        return Boolean.class;
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    private String toString(Object object) {
        if (object == null) return NO;
        Boolean bool = (Boolean) object;
        return bool == true ? YES : NO;
    }
}

