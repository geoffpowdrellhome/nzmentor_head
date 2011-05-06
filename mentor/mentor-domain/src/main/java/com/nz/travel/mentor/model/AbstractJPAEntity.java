package com.nz.travel.mentor.model;

import com.nz.travel.mentor.model.impl.MentorObject;
import com.nz.travel.mentor.type.BooleanJPAType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Transient;
import java.util.UUID;

/**
 * Layer Super-Type for JPA Entities in FareGate.
 */
@TypeDef(name = "booleanType", typeClass = BooleanJPAType.class)
public abstract class AbstractJPAEntity extends MentorObject {

    /**
     * This UUID is for .equals() and hashCode() in memory.
     * <ul>
     * <li>Can't use ID because non-persisted objects have no ID.</li>
     * </ul>
     */
    @Transient
    private UUID uuid;

    public AbstractJPAEntity() {
        uuid = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
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
        return uuid.equals(((AbstractJPAEntity) object).uuid);
    }
}


