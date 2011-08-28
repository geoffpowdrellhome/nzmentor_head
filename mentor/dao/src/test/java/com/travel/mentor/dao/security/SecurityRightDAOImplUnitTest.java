package com.travel.mentor.dao.security;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class SecurityRightDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "secureUserDAO")
    private SecureUserDAO secureUserDAO;

    @Resource(name = "securityRoleDAO")
    private SecurityRoleDAO securityRoleDAO;

    // @@TODO - Not coded yet!!!
    protected static final String EXISTING_GUEST_USERNAME_VALUE="guest";
    protected static final String SECURITY_RIGHTS_LIKE_RIGHT_NAME = "Administration";
    protected static final String SECURITY_GROUPS_LIKE_NAME = "Customers";
    protected static final String SECURITY_ROLES_LIKE_NAME = "REGIONS";

    protected static final Long EXISTING_SECURITY_RIGHTS_TYPE_ID = 3L;

    @Test
    public void testFindAll() {
        List<SecureUserDTO> itemDTOList = secureUserDAO.findAllSecureUsers();
        assertRecordsReturned(itemDTOList);
    }
}
