package com.travel.mentor.dao.dto.base;

import com.travel.mentor.dao.dto.impl.UserDTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ReferenceTypeDTO extends AbstractAuditedNameDescDTO {

//    protected Long id;
//    protected String name;
//    protected String description;
    protected Class entityClass;
//    protected UserDTO createUserDTO=new UserDTO();
//    protected Timestamp createDate=new Timestamp(new Date().getTime());
//    protected UserDTO updateUserDTO=new UserDTO();
//    protected Timestamp updateDate=new Timestamp(new Date().getTime());

    public ReferenceTypeDTO() {}

    public ReferenceTypeDTO(String _name, String _description, Class _entityClass) {
        this.name = _name;
        this.description = _description;
        this.entityClass = _entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
