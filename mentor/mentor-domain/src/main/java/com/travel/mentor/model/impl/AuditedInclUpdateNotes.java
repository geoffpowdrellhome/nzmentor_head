package com.travel.mentor.model.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditedInclUpdateNotes extends Audited {

    @Column(name = "update_notes")
    protected String updateNotes = "SysNotes";

    public String getUpdateNotes() {
        return updateNotes;
    }

    public void setUpdateNotes(String updateNotes) {
        this.updateNotes = updateNotes;
    }
}
