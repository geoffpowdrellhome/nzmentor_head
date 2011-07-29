
BEGIN;

/* security tables */

/* SEC_USER table */
CREATE TABLE SECURE_USER
(
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    title VARCHAR(10) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(50) NULL,
    locale VARCHAR(40) NULL,
    enabled BOOL NOT NULL DEFAULT 'true',
    account_non_expired BOOL NOT NULL DEFAULT 'true',
    credentials_non_expired BOOL NOT NULL DEFAULT 'true',
    account_non_locked BOOL NOT NULL DEFAULT 'true',
    created TIMESTAMP NOT NULL DEFAULT 'now',
    created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
    updated TIMESTAMP NOT NULL DEFAULT 'now',
    updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE secure_user ADD CONSTRAINT pk_secure_user PRIMARY KEY (username);

INSERT INTO SECURE_USER (username, password, title, firstname, lastname, email, locale, enabled, account_non_expired, credentials_non_expired, account_non_locked) VALUES
( 'sysadm', 'sysadm', 'Mr', 'systemFirstname', 'adminlastname', 'sysadmin@travelmentor.com', NULL, true, true, true, true),
( 'guest', 'guest', 'Mr', 'guestFirstname', 'guestlastname', 'guest@travelmentor.com', NULL, true, true, true, true),
( 'admin', 'admin', 'Mr', 'Visor', 'Super', 'admin@travelmentor.com', NULL, true, true, true, true),
( 'user1', 'user1',  'Mr', 'Kingsley', 'Ben', 'B.Kingsley@travelmentor.com', NULL, true, true, true, true),
( 'headoffice', 'headoffice', 'Mr', 'Willis', 'Bruce', 'B.Willis@travelmentor.com', NULL, true, true, true, true),
( 'user2', 'user2', 'Mr', 'Kingdom', 'Marta', 'M.Kingdom@travelmentor.com', NULL, true, true, true, true);



/* SEC_ROLE table */
CREATE SEQUENCE security_role_id_seq INCREMENT 1;
CREATE TABLE SECURITY_ROLE
(
    id INTEGER NOT NULL DEFAULT nextval('security_role_id_seq'::regclass),
    rolename VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT 'now',
    created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
    updated TIMESTAMP NOT NULL DEFAULT 'now',
    updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE security_role ADD CONSTRAINT pk_secure_role PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_security_role_rolename ON security_role(rolename);
CREATE INDEX security_role_search_1 ON security_role(rolename);


/******************** Security: ROLES ********************/  
INSERT INTO SECURITY_ROLE (id, rolename, description) VALUES
( nextval('security_role_id_seq'), 'ROLE_ADMIN','Administrator Role'),
( nextval('security_role_id_seq'), 'ROLE_OFFICE_ALL_RIGHTS', 'Office User role with all rights granted.'),
( nextval('security_role_id_seq'), 'ROLE_REGIONS_ONLY_VIEW', 'Regions User role with rights that granted only view of data.'),
( nextval('security_role_id_seq'), 'ROLE_REGIONS_ALL_RIGHTS', 'Regions User role with all rights granted.'),
( nextval('security_role_id_seq'), 'ROLE_GUEST','Guest Role'),
( nextval('security_role_id_seq'), 'ROLE_OFFICE_ONLY_VIEW','Office user with rights that granted only view of data.'),
( nextval('security_role_id_seq'), 'ROLE_HEADOFFICE_USER','Headoffice User Role');




/* SEC_USERROLE table */
CREATE SEQUENCE security_userrole_id_seq INCREMENT 1;
CREATE TABLE SECURITY_USERROLE
(    
    secure_user_username VARCHAR(30) NOT NULL,
    security_role_id INTEGER NOT NULL
);

ALTER TABLE SECURITY_USERROLE ADD CONSTRAINT fk_security_userrole_username FOREIGN KEY (secure_user_username) REFERENCES secure_user(username) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE SECURITY_USERROLE ADD CONSTRAINT fk_security_userrole_rolename FOREIGN KEY (security_role_id) REFERENCES security_role(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE UNIQUE INDEX ix_security_userrole ON security_userrole(secure_user_username, security_role_id);



/******************** Security: USER-ROLES ********************/  
/* Guest account authorities */
INSERT INTO SECURITY_USERROLE (secure_user_username, security_role_id) VALUES
( 'guest', (select id from security_role where rolename='ROLE_GUEST') ),
( 'user1', (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS') ),
( 'headoffice', (select id from security_role where rolename='ROLE_HEADOFFICE_USER') );


/* Admin Usr-ID: 11 */
INSERT INTO SECURITY_USERROLE (secure_user_username, security_role_id) VALUES
( 'admin', (select id from security_role where rolename='ROLE_ADMIN') ),
( 'admin', (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS') ),
( 'admin', (select id from security_role where rolename='ROLE_GUEST') ),
( 'admin', (select id from security_role where rolename='ROLE_OFFICE_ONLY_VIEW') ),
( 'admin', (select id from security_role where rolename='ROLE_HEADOFFICE_USER') ),
( 'user2', (select id from security_role where rolename='ROLE_OFFICE_ONLY_VIEW'));




/******************** Security: SECURITY_GROUPS ********************/
CREATE SEQUENCE security_group_id_seq INCREMENT 1;
CREATE TABLE SECURITY_GROUP
(
    id INTEGER NOT NULL DEFAULT nextval('security_group_id_seq'::regclass),
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT 'now',
    created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
    updated TIMESTAMP NOT NULL DEFAULT 'now',
    updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE security_group ADD CONSTRAINT pk_security_group PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_security_group_name ON security_group(name);

 
INSERT INTO SECURITY_GROUP (id, name, description) VALUES
( nextval('security_group_id_seq'), 'Headoffice Supervisor Group', 'kjhf ff hgfd'),
( nextval('security_group_id_seq'), 'Admin Group - user accounts', 'create/modify user accounts'),
( nextval('security_group_id_seq'), 'Guest Group', 'Minimal Rights for the guests'),
( nextval('security_group_id_seq'), 'Admin Group - user rights', 'edit/modify user rights'),

/* Customers */
( nextval('security_group_id_seq'), 'Customers_View',      'Allow to  view customers data'),
( nextval('security_group_id_seq'), 'Customers_New',       'Allow create new customers'),
( nextval('security_group_id_seq'), 'Customers_Edit',      'Allow editing of customers'),
( nextval('security_group_id_seq'), 'Customers_Delete',    'Allow deleting of customers'),

/* Orders */
( nextval('security_group_id_seq'), 'Orders_View',         'Allow to view orders data'),
( nextval('security_group_id_seq'), 'Orders_New',          'Allow create new orders'),
( nextval('security_group_id_seq'), 'Orders_Edit',         'Allow editing of orders'),
( nextval('security_group_id_seq'), 'Orders_Delete',       'Allow deleting of orders'),

/* Branches */
( nextval('security_group_id_seq'), 'Branch_View',         'Allow to view branches data'),
( nextval('security_group_id_seq'), 'Branch_New',          'Allow create new branches'),
( nextval('security_group_id_seq'), 'Branch_Edit',         'Allow editing of branches'),
( nextval('security_group_id_seq'), 'Branch_Delete',       'Allow deleting of branches'),

/* Articles */
( nextval('security_group_id_seq'), 'Articles_View',       'Allow to view articles data'),
( nextval('security_group_id_seq'), 'Articles_New',        'Allow create new articles'),
( nextval('security_group_id_seq'), 'Articles_Edit',       'Allow editing of articles'),
( nextval('security_group_id_seq'), 'Articles_Delete',     'Allow deleting of articles'),

/* Offices */
( nextval('security_group_id_seq'), 'Offices_View',        'Allow to view offices data'),
( nextval('security_group_id_seq'), 'Offices_New',         'Allow create new offices'),
( nextval('security_group_id_seq'), 'Offices_Edit',        'Allow editing of offices'),
( nextval('security_group_id_seq'), 'Offices_Delete',      'Allow deleting of offices'),

/* Users */
( nextval('security_group_id_seq'), 'User_View_UsersOnly', 'Allow to view own user data.'),
( nextval('security_group_id_seq'), 'User_Edit_UsersOnly', 'Allow to edit own user data.'),
( nextval('security_group_id_seq'), 'Users_View',          'Allow to view all users data.'),
( nextval('security_group_id_seq'), 'Users_New',           'Allow create new users'),
( nextval('security_group_id_seq'), 'Users_Edit',          'Allow editing of users'),
( nextval('security_group_id_seq'), 'Users_Delete',        'Allow deleting of users'),
( nextval('security_group_id_seq'), 'Users_Search',        'Allow searching of users'),

/* secGroup */
( nextval('security_group_id_seq'), 'Security_Groups',     'Allow to view the securityGroups Dialog'),

/* secRole */
( nextval('security_group_id_seq'), 'Security_Roles',      'Allow to view the securityRoles Dialog'),

/* secRight */
( nextval('security_group_id_seq'), 'Security_Rights',     'Allow to view the securityRights Dialog');



CREATE SEQUENCE security_rolegroup_id_seq INCREMENT 1;
CREATE TABLE SECURITY_ROLEGROUP
(
    id INTEGER NOT NULL DEFAULT nextval('security_rolegroup_id_seq'::regclass),
    security_group_id INTEGER NOT NULL,
    security_role_id INTEGER NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT 'now',
    created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
    updated TIMESTAMP NOT NULL DEFAULT 'now',
    updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE security_rolegroup ADD CONSTRAINT pk_security_rolegroup PRIMARY KEY (id);
ALTER TABLE security_rolegroup ADD CONSTRAINT fk_security_rolegroup_security_group FOREIGN KEY (security_group_id) REFERENCES security_group(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE security_rolegroup ADD CONSTRAINT fk_security_rolegroup_security_role FOREIGN KEY (security_role_id) REFERENCES security_role(id) ON UPDATE NO ACTION ON DELETE NO ACTION;



/******************** Security: SEC_ROLE-GROUPS ********************/  
/* ROLE_OFFICE_ALL_RIGHTS */
/* Group: Customers_View */
INSERT INTO SECURITY_ROLEGROUP (id, security_group_id, security_role_id) values
( nextval('security_rolegroup_id_seq'), (select id from security_group where name='Headoffice Supervisor Group'), (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS')),
 /* Group: Customers_New */
( nextval('security_rolegroup_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS')),
 /* Group: Customers_Edit */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS')),
/*  Group: Customers_Delete */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Customers_Delete'), (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS')),


/*  Group: User_View_UsersOnly */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS')),
/*  Group: User_Edit_UsersOnly */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='User_Edit_UsersOnly'), (select id from security_role where rolename='ROLE_OFFICE_ALL_RIGHTS')),
/* ROLE_OFFICE_ONLY_VIEW */
/*  Group: User_View_UsersOnly */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_role where rolename='ROLE_OFFICE_ONLY_VIEW')),
/* ROLE_GUEST */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Guest Group'), (select id from security_role where rolename='ROLE_GUEST')),
/* ROLE_ADMIN */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Headoffice Supervisor Group'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Guest Group'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Admin Group - user rights'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Customers_Delete'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_New'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_Edit'), (select id from security_role where rolename='ROLE_ADMIN')),
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_Delete'), (select id from security_role where rolename='ROLE_ADMIN')),

/* Group: Users_View */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Users_View'), (select id from security_role where rolename='ROLE_ADMIN')),
/* Group: Users_New */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Users_New'), (select id from security_role where rolename='ROLE_ADMIN')),
/* Group: Users_Edit */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Users_Edit'), (select id from security_role where rolename='ROLE_ADMIN')),
/* Group: Users_Delete */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Users_Delete'), (select id from security_role where rolename='ROLE_ADMIN')),
/* Group: Users_Search */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Users_Search'), (select id from security_role where rolename='ROLE_ADMIN')),

/* ROLE_HEADOFFICE_USER */


/* Group: Articles_View */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_role where rolename='ROLE_HEADOFFICE_USER')),
/* Group: Articles_New */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_New'), (select id from security_role where rolename='ROLE_HEADOFFICE_USER')),
/* Group: Articles_Edit */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_Edit'), (select id from security_role where rolename='ROLE_HEADOFFICE_USER')),
/* Group: Articles_Delete */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Articles_Delete'), (select id from security_role where rolename='ROLE_HEADOFFICE_USER')),

/*  Group: User_View_UsersOnly */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_role where rolename='ROLE_HEADOFFICE_USER')),
/*  Group: User_Edit_UsersOnly */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='User_Edit_UsersOnly'), (select id from security_role where rolename='ROLE_HEADOFFICE_USER')),
     
/* ROLE_ADMIN */
/* Group: Security_Groups */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_role where rolename='ROLE_ADMIN')),
/* Group: Security_Roles */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_role where rolename='ROLE_ADMIN')),
/* Group: Security_Rights */
(nextval('security_rolegroup_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_role where rolename='ROLE_ADMIN'));




/* security_right_type table */
CREATE SEQUENCE security_right_type_id_seq INCREMENT 1;
CREATE TABLE security_right_type
(
	id INTEGER NOT NULL DEFAULT nextval('security_right_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE security_right_type ADD CONSTRAINT pk_security_right_type PRIMARY KEY (id);
ALTER TABLE security_right_type ADD CONSTRAINT fk_security_right_type_user_created_by FOREIGN KEY (created_by) REFERENCES secure_user(username) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE security_right_type ADD CONSTRAINT fk_security_right_type_user_updated_by FOREIGN KEY (updated_by) REFERENCES secure_user(username) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_security_right_type ON security_right_type(name);

INSERT INTO SECURITY_RIGHT_TYPE (id, name, description) values
( nextval('security_right_type_id_seq'), 'Page', 'Page'),
( nextval('security_right_type_id_seq'), 'Menu', 'Menu'),
( nextval('security_right_type_id_seq'), 'SubMenu', 'SubMenu'),
( nextval('security_right_type_id_seq'), 'DoubleClick', 'DoubleClick'),
( nextval('security_right_type_id_seq'), 'Tab', 'Tab'),
( nextval('security_right_type_id_seq'), 'Component', 'Component');


CREATE SEQUENCE security_right_id_seq INCREMENT 1;
CREATE TABLE SECURITY_RIGHT
(
    id INTEGER NOT NULL DEFAULT nextval('security_right_id_seq'::regclass),
    security_right_type_id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT 'now',
    created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
    updated TIMESTAMP NOT NULL DEFAULT 'now',
    updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE security_right ADD CONSTRAINT pk_security_right PRIMARY KEY (id);
ALTER TABLE security_right ADD CONSTRAINT fk_security_right_security_righttype FOREIGN KEY (security_right_type_id) REFERENCES security_right_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE UNIQUE INDEX ix_security_right_name ON security_right(name);




/******************** Security: SEC_RIGHTS ********************/  
INSERT INTO SECURITY_RIGHT (id, security_right_type_id, name, description) values
( nextval('security_right_id_seq'), (select id from security_right_type where name='Menu'), 'menuCat_OfficeData', 'menuCat_OfficeData'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_OfficeData_Customers', 'menuItem_OfficeData_Customers'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_OfficeData_Orders', 'menuItem_OfficeData_Orders'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Menu'), 'menuCat_MainData', 'menuCat_MainData'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_MainData_ArticleItems', 'menuItem_MainData_ArticleItems'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_MainData_Branch', 'menuItem_MainData_Branch'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_MainData_Office', 'menuItem_MainData_Office'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Menu'), 'menuCat_Administration', 'menuCat_Administration'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_Users', 'menuItem_Administration_Users'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_UserRoles', 'menuItem_Administration_UserRoles'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_Roles', 'menuItem_Administration_Roles'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_RoleGroups', 'menuItem_Administration_RoleGroups'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_Groups', 'menuItem_Administration_Groups'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_GroupRights', 'menuItem_Administration_GroupRights'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_Rights', 'menuItem_Administration_Rights'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Menu'), 'menuCat_UserRights', 'menuCat_UserRights'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_LoginsLog', 'menuItem_Administration_LoginsLog'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menuItem_Administration_HibernateStats', 'menuItem_Administration_HibernateStats'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='SubMenu'), 'menu_Item_Calendar', 'menu_Item_Calendar'),

/* Pages = Type(0) */
/* --> Page Customer */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'window_customerList', 'window_customerList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'window_customerDialog', 'window_customerDialog'),
/* --> Page Orders */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'orderListWindow', 'orderListWindow'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'orderDialogWindow', 'orderDialogWindow'),
/* --> Page Articles */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'windowArticlesList', 'windowArticlesList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'window_ArticlesDialog', 'window_ArticlesDialog'),
/* --> Page Branches */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'window_BranchesList', 'window_BranchesList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'window_BranchesDialog', 'window_BranchesDialog'),
/* --> Page Offices */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'window_OfficeList', 'window_OfficeList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'window_OfficeDialog', 'window_OfficeDialog'),
/* --> Page Admin - Users */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Admin_UserList', 'page_Admin_UserList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Admin_UserDialog', 'page_Admin_UserDialog'),
/* --> Page Admin - UserRoles */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_UserRolesList', 'page_Security_UserRolesList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_RolesList', 'page_Security_RolesList'),
/* --> Page Admin - Roles */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_RolesDialog', 'page_Security_RolesDialog'),
/* --> Page Admin - RoleGroups */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_RoleGroupsList', 'page_Security_RoleGroupsList'),
/* --> Page Admin - Groups */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_GroupsList', 'page_Security_GroupsList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_GroupsDialog', 'page_Security_GroupsDialog'),
/* --> Page Admin - GroupRights */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_GroupRightsList', 'page_Security_GroupRightsList'),
/* --> Page Admin - Rights */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_RightsList', 'page_Security_RightsList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Security_RightsDialog', 'page_Security_RightsDialog'),
/* --> Page Login Log */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'page_Admin_LoginLogList', 'page_Admin_LoginLogList'),
/* --> Nachtrag Page Orders */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'orderPositionDialogWindow', 'orderPositionDialogWindow'),

/* Tabs = Type(5) */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Tab'), 'tab_CustomerDialog_Address', 'tab_CustomerDialog_Address'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Tab'), 'tab_CustomerDialog_Chart', 'tab_CustomerDialog_Chart'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Tab'), 'tab_CustomerDialog_Orders', 'tab_CustomerDialog_Orders'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Tab'), 'tab_CustomerDialog_Memos', 'tab_CustomerDialog_Memos'),

/* Components = Type(6) */
/* --> CustomerList BUTTON */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerList_btnHelp', 'button_CustomerList_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerList_NewCustomer', 'button_CustomerList_NewCustomer'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerList_CustomerFindDialog', 'button_CustomerList_CustomerFindDialog'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerList_PrintList', 'button_CustomerList_PrintList'),
/* --> CustomerDialog BUTTON */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerDialog_btnHelp', 'button_CustomerDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerDialog_btnNew', 'button_CustomerDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerDialog_btnEdit', 'button_CustomerDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerDialog_btnDelete', 'button_CustomerDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerDialog_btnSave', 'button_CustomerDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerDialog_btnClose', 'button_CustomerDialog_btnClose'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_CustomerDialog_btnCancel', 'button_CustomerDialog_btnCancel'),
/* --> OrderList BUTTON */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderList_btnHelp', 'button_OrderList_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderList_NewOrder', 'button_OrderList_NewOrder'),
/* --> OrderDialog BUTTON */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_btnHelp', 'button_OrderDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_btnNew', 'button_OrderDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_btnEdit', 'button_OrderDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_btnDelete', 'button_OrderDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_btnSave', 'button_OrderDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_btnClose', 'button_OrderDialog_btnClose'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_PrintOrder', 'button_OrderDialog_PrintOrder'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderDialog_NewOrderPosition', 'button_OrderDialog_NewOrderPosition'),
/* --> OrderPositionDialog BUTTON */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderPositionDialog_btnHelp', 'button_OrderPositionDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderPositionDialog_PrintOrderPositions', 'button_OrderPositionDialog_PrintOrderPositions'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderPositionDialog_btnNew', 'button_OrderPositionDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderPositionDialog_btnEdit', 'button_OrderPositionDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderPositionDialog_btnDelete', 'button_OrderPositionDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderPositionDialog_btnSave', 'button_OrderPositionDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OrderPositionDialog_btnClose', 'button_OrderPositionDialog_btnClose'),
/* USERS */
/* --> userListWindow */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'userListWindow', 'userListWindow'),
/* --> userListWindow BUTTONS*/
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserList_btnHelp', 'button_UserList_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserList_NewUser', 'button_UserList_NewUser'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserList_PrintUserList', 'button_UserList_PrintUserList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserList_SearchLoginname', 'button_UserList_SearchLoginname'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserList_SearchLastname', 'button_UserList_SearchLastname'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserList_SearchEmail', 'button_UserList_SearchEmail'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'checkbox_UserList_ShowAll', 'checkbox_UserList_ShowAll'),
/* --> Mehode onDoubleClick Listbox */
( nextval('security_right_id_seq'), (select id from security_right_type where name='DoubleClick'), 'UserList_listBoxUser.onDoubleClick', 'UserList_listBoxUser.onDoubleClick'),
/* --> userDialogWindow */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'userDialogWindow', 'userDialogWindow'),
/* --> userDialogWindow BUTTONS*/
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserDialog_btnHelp', 'button_UserDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserDialog_btnNew', 'button_UserDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserDialog_btnEdit', 'button_UserDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserDialog_btnDelete', 'button_UserDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserDialog_btnSave', 'button_UserDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserDialog_btnClose', 'button_UserDialog_btnClose'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_UserDialog_btnCancel', 'button_UserDialog_btnCancel'),
/* --> userDialogWindow Special Admin Panels */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'panel_UserDialog_Status', 'panel_UserDialog_Status'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'panel_UserDialog_SecurityToken', 'panel_UserDialog_SecurityToken'),
/* --> userListWindow Search panel */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'hbox_UserList_SearchUsers', 'hbox_UserList_SearchUsers'),
/* Tab Details */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'tab_UserDialog_Details', 'tab_UserDialog_Details'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='DoubleClick'), 'data_SeeAllUserData', 'data_SeeAllUserData'),

/* BRANCHES */
/* branchListWindow Buttons*/
/* --> button_BranchList_btnHelp */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'button_BranchMain_btnPrint', 'button_BranchMain_btnPrint'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'button_BranchMain_Search_BranchName', 'button_BranchMain_Search_BranchName'),
/* branchDialogWindow BUTTONS */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnHelp', 'button_BranchMain_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnNew', 'button_BranchMain_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnEdit', 'button_BranchMain_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnDelete', 'button_BranchMain_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnSave', 'button_BranchMain_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnClose', 'button_BranchMain_btnClose'),
/* new: sge:07/18/2011  navigation buttons */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnCancel', 'button_BranchMain_btnCancel'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnFirst', 'button_BranchMain_btnFirst'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnPrevious', 'button_BranchMain_btnPrevious'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnNext', 'button_BranchMain_btnNext'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_BranchMain_btnLast', 'button_BranchMain_btnLast'),
/* ARTICLES */
/* window_ArticlesList Buttons*/
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesList_btnHelp', 'button_ArticlesList_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticleList_NewArticle', 'button_ArticleList_NewArticle'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticleList_SearchArticleID', 'button_ArticleList_SearchArticleID'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticleList_SearchName', 'button_ArticleList_SearchName'),

/* window_ArticlesDialog Buttons*/
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticleList_PrintList', 'button_ArticleList_PrintList'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnPrint', 'button_ArticlesDialog_btnPrint'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnHelp', 'button_ArticlesDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnNew', 'button_ArticlesDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnEdit', 'button_ArticlesDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnDelete', 'button_ArticlesDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnSave', 'button_ArticlesDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnClose', 'button_ArticlesDialog_btnClose'),
/* new: sge:07/18/2011  navigation buttons */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnFirst', 'button_ArticlesDialog_btnFirst'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnPrevious', 'button_ArticlesDialog_btnPrevious'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnNext', 'button_ArticlesDialog_btnNext'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnLast', 'button_ArticlesDialog_btnLast'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_ArticlesDialog_btnCancel', 'button_ArticlesDialog_btnCancel'),

/* OFFICES */
/* window_OfficeList Buttons*/
/* --> button_BranchList_btnHelp */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnPrint', 'button_OfficeMain_btnPrint'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeList_SearchNo', 'button_OfficeList_SearchNo'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeList_SearchName', 'button_OfficeList_SearchName'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeList_SearchCity', 'button_OfficeList_SearchCity'),
/* window_OfficeDialog BUTTONS */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnHelp', 'button_OfficeMain_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnNew', 'button_OfficeMain_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnEdit', 'button_OfficeMain_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnDelete', 'button_OfficeMain_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnSave', 'button_OfficeMain_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnClose', 'button_OfficeMain_btnClose'),
/* new: sge:07/18/2011  navigation buttons */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnCancel', 'button_OfficeMain_btnCancel'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnFirst', 'button_OfficeMain_btnFirst'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnPrevious', 'button_OfficeMain_btnPrevious'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnNext', 'button_OfficeMain_btnNext'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_OfficeMain_btnLast', 'button_OfficeMain_btnLast'),

/* Method/Event = Type(3) */
/* --> CustomerList BUTTON */
( nextval('security_right_id_seq'), (select id from security_right_type where name='DoubleClick'), 'CustomerList_listBoxCustomer.onDoubleClick', 'CustomerList_listBoxCustomer.onDoubleClick'),
/* --> secRoleDialogWindow */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'secRoleDialogWindow', 'secRoleDialogWindow'),
/* --> secRoleDialogWindow BUTTONS*/
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRoleDialog_btnHelp', 'button_SecRoleDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRoleDialog_btnNew', 'button_SecRoleDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRoleDialog_btnEdit', 'button_SecRoleDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRoleDialog_btnDelete', 'button_SecRoleDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRoleDialog_btnSave', 'button_SecRoleDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRoleDialog_btnClose', 'button_SecRoleDialog_btnClose'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRoleDialog_btnCancel', 'button_SecRoleDialog_btnCancel'),
/* --> secGroupDialogWindow */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'secGroupDialogWindow', 'secGroupDialogWindow'),
/* --> secGroupDialogWindow BUTTONS*/
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecGroupDialog_btnHelp', 'button_SecGroupDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecGroupDialog_btnNew', 'button_SecGroupDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecGroupDialog_btnEdit', 'button_SecGroupDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecGroupDialog_btnDelete', 'button_SecGroupDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecGroupDialog_btnSave', 'button_SecGroupDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecGroupDialog_btnClose', 'button_SecGroupDialog_btnClose'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecGroupDialog_btnCancel', 'button_SecGroupDialog_btnCancel'),
/* --> secRightDialogWindow */
( nextval('security_right_id_seq'), (select id from security_right_type where name='Page'), 'secRightDialogWindow', 'secRightDialogWindow'),
/* --> secRightDialogWindow BUTTONS*/
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRightDialog_btnHelp', 'button_SecRightDialog_btnHelp'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRightDialog_btnNew', 'button_SecRightDialog_btnNew'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRightDialog_btnEdit', 'button_SecRightDialog_btnEdit'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRightDialog_btnDelete', 'button_SecRightDialog_btnDelete'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRightDialog_btnSave', 'button_SecRightDialog_btnSave'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRightDialog_btnClose', 'button_SecRightDialog_btnClose'),
( nextval('security_right_id_seq'), (select id from security_right_type where name='Component'), 'button_SecRightDialog_btnCancel', 'button_SecRightDialog_btnCancel');



/* security_groupright table */
CREATE SEQUENCE security_group_right_id_seq INCREMENT 1;
CREATE TABLE security_group_right
(
	id INTEGER NOT NULL DEFAULT nextval('security_group_right_id_seq'::regclass),
	security_group_id INTEGER NOT NULL,
	security_right_id INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE security_group_right ADD CONSTRAINT pk_security_group_right PRIMARY KEY (id);
ALTER TABLE security_group_right ADD CONSTRAINT fk_security_group_right_security_group FOREIGN KEY (security_group_id) REFERENCES security_group(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE security_group_right ADD CONSTRAINT fk_security_group_right_security_right FOREIGN KEY (security_right_id) REFERENCES security_right(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_security_group_right ON security_group_right(security_group_id, security_right_id);


/******************** Security: SEC_GROUP-RIGHTS ********************/  
/* Headoffice Supervisor Group*/
INSERT INTO SECURITY_GROUP_RIGHT (id, security_group_id, security_right_id) VALUES
( nextval('security_group_right_id_seq'), (select id from security_group where name='Headoffice Supervisor Group'), (select id from security_right where name='menuCat_MainData')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Headoffice Supervisor Group'), (select id from security_right where name='menuItem_MainData_ArticleItems')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Headoffice Supervisor Group'), (select id from security_right where name='menuItem_MainData_Branch')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Headoffice Supervisor Group'), (select id from security_right where name='menuItem_MainData_Office')),
/* Administration Group*/
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuCat_Administration')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_Users')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_UserRoles')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_Roles')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_RoleGroups')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_Groups')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_GroupRights')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_Rights')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuCat_UserRights')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_LoginsLog')),
( nextval('security_group_right_id_seq'), (select id from security_group where name='Admin Group - user accounts'), (select id from security_right where name='menuItem_Administration_HibernateStats')),

/* New */
/* Group: Customers_View */
/* Right: menuCat_OfficeData */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='menuCat_OfficeData')),
/* Right: menuItem_OfficeData_Customers */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='menuItem_OfficeData_Customers')),
/* Right: customerListWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='window_customerList')),
/* Right: button_CustomerList_Help */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='button_CustomerDialog_btnHelp')),
/* Right: CustomerList_listBoxCustomer.onDoubleClick */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='CustomerList_listBoxCustomer.onDoubleClick')),

/* Right: customerDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='window_customerDialog')),
/* Right: button_CustomerDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='button_CustomerDialog_btnClose')),
/* Right: button_CustomerList_Help */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='button_CustomerList_btnHelp')),
/* Right: button_CustomerList_CustomerFindDialog */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='button_CustomerList_CustomerFindDialog')),
/* Right: button_CustomerList_PrintList */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='button_CustomerList_PrintList')),

/* Tab tab_CustomerDialog_Address */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='tab_CustomerDialog_Address')),
/* Tab tab_CustomerDialog_Addition */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='tab_CustomerDialog_Chart')),
/* Tab tab_CustomerDialog_Orders */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='tab_CustomerDialog_Orders')),
/* Tab tab_CustomerDialog_Memos */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_View'), (select id from security_right where name='tab_CustomerDialog_Memos')),

/* Group: Customers_New */
/* Right: customerListWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='window_customerList')),
/* Right: button_CustomerList_NewCustomer */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='button_CustomerList_NewCustomer')),
/* Right: customerDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='window_customerDialog')),
/* Right: button_CustomerDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='button_CustomerDialog_btnClose')),
/* Right: button_CustomerDialog_btnNew */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='button_CustomerDialog_btnNew')),
/* Right: button_CustomerDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='button_CustomerDialog_btnEdit')),
/* Right: button_CustomerDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='button_CustomerDialog_btnSave')),
/* Right: button_CustomerDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_New'), (select id from security_right where name='button_CustomerDialog_btnCancel')),

/* Group: Customers_Edit */
/* Right: customerListWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_right where name='window_customerList')),
/* Right: customerDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_right where name='window_customerDialog')),
/* Right: button_CustomerDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_right where name='button_CustomerDialog_btnClose')),
/* Right: button_CustomerDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_right where name='button_CustomerDialog_btnEdit')),
/* Right: button_CustomerDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_right where name='button_CustomerDialog_btnSave')),
/* Right: button_CustomerDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Edit'), (select id from security_right where name='button_CustomerDialog_btnCancel')),

/* Group: Customers_Delete */
/* Right: customerListWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Delete'), (select id from security_right where name='window_customerList')),
/* Right: customerDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Delete'), (select id from security_right where name='window_customerDialog')),
/* Right: button_CustomerDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Delete'), (select id from security_right where name='button_CustomerDialog_btnClose')),
/* Right: button_CustomerDialog_btnDelete */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Customers_Delete'), (select id from security_right where name='button_CustomerDialog_btnDelete')),

/*----------------------------------------------------------*/


/* USERS ----------------- */
/* Group: User_View_UsersOnly */
/* Right: menuCat_Administration */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='menuCat_Administration')),
/* Right: menuItem_Administration_Users */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='menuItem_Administration_Users')),
/* Right: userListWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='userListWindow')),
/* Right: button_UserList_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='button_UserList_btnHelp')),
/* Right: UserList_listBoxUser.onDoubleClick */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='UserList_listBoxUser.onDoubleClick')),
/* Right: userDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='userDialogWindow')),
/* Right: tab_UserDialog_Details */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='tab_UserDialog_Details')),
/* Right: button_UserDialog_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='button_UserDialog_btnHelp')),
/* Right: button_Dialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='button_UserDialog_btnClose')),
/* Right: button_Dialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_View_UsersOnly'), (select id from security_right where name='button_UserDialog_btnCancel')),

/* Group: User_Edit_UsersOnly */
/* Right: button_UserDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_Edit_UsersOnly'), (select id from security_right where name='button_UserDialog_btnEdit')),
/* Right: button_Dialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='User_Edit_UsersOnly'), (select id from security_right where name='button_UserDialog_btnSave')),

/* Group: Users_View */
/* Right: menuCat_Administration */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='menuCat_Administration')),
/* Right: menuItem_Administration_Users */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='menuItem_Administration_Users')),
/* Right: userListWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='userListWindow')),
/* Right: button_UserList_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='button_UserList_btnHelp')),
/* Right: button_UserList_PrintUserList */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='button_UserList_PrintUserList')),
/* Right: UserList_listBoxUser.onDoubleClick */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='UserList_listBoxUser.onDoubleClick')),
/* Right: userDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='userDialogWindow')),
/* Right: tab_UserDialog_Details */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='tab_UserDialog_Details')),
/* Right: button_UserDialog_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='button_UserDialog_btnHelp')),
/* Right: button_UserDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='button_UserDialog_btnClose')),
/* Right: panel_UserDialog_Status */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='panel_UserDialog_Status')),
/* Right: panel_UserDialog_SecurityToken */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='panel_UserDialog_SecurityToken')),
/* Right: data_SeeAllUserData */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='data_SeeAllUserData')),
/* Right: button_UserDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_View'), (select id from security_right where name='button_UserDialog_btnCancel')),

/* Group: Users_New */
/* Right: button_UserList_NewUser */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_New'), (select id from security_right where name='button_UserList_NewUser')),
/* Right: button_UserDialog_btnNew */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_New'), (select id from security_right where name='button_UserDialog_btnNew')),
/* Right: button_UserDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_New'), (select id from security_right where name='button_UserDialog_btnEdit')),
/* Right: button_UserDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_New'), (select id from security_right where name='button_UserDialog_btnSave')),

/* Group: Users_Edit */
/* Right: button_UserDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_Edit'), (select id from security_right where name='button_UserDialog_btnEdit')),
/* Right: button_UserDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_Edit'), (select id from security_right where name='button_UserDialog_btnSave')),
/* Right: button_UserDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_Edit'), (select id from security_right where name='button_UserDialog_btnCancel')),
/* Right: button_UserDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_Edit'), (select id from security_right where name='button_UserDialog_btnClose')),

/* Group: Users_Delete */
/* Right: button_UserDialog_btnDelete */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_Delete'), (select id from security_right where name='button_UserDialog_btnDelete')),

/* Group: Users_Search */
/* Right: hbox_UserList_SearchUsers */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Users_Search'), (select id from security_right where name='hbox_UserList_SearchUsers')),


/* A r t i c l e s */
/* Group: Articles_View */
/* Right: menuCat_MainData */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='menuCat_MainData')),
/* Right: menuItem_MainData_ArticleItems */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='menuItem_MainData_ArticleItems')),
/* Right: window_ArticlesList */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='windowArticlesList')),
/* Right: button_ArticlesList_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesList_btnHelp')),
/* Right: button_ArticlesDialog_btnPrint */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticleList_PrintList')),
/* Right: window_ArticlesDialog */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='window_ArticlesDialog')),
/* Right: button_ArticlesDialog_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesDialog_btnHelp')),
/* Right: button_ArticlesDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesDialog_btnClose')),
/* Right: button_ArticleList_SearchArticleID */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticleList_SearchArticleID')),
/* Right: button_ArticleList_SearchName */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticleList_SearchName')),
/* new: sge:07/18/2011  navigation buttons */
/* Right: button_ArticlesDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesDialog_btnCancel')),
/* Right: button_ArticlesDialog_btnFirst */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesDialog_btnFirst')),
/* Right: button_ArticlesDialog_btnPrevious */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesDialog_btnPrevious')),
/* Right: button_ArticlesDialog_btnNext */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesDialog_btnNext')),
/* Right: button_ArticlesDialog_btnLast */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_View'), (select id from security_right where name='button_ArticlesDialog_btnLast')),

/* Group: Articles_New */
/* Right: button_ArticleList_NewArticle */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_New'), (select id from security_right where name='button_ArticleList_NewArticle')),
/* Right: button_ArticlesDialog_btnNew */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_New'), (select id from security_right where name='button_ArticlesDialog_btnNew')),
/* Right: button_ArticlesDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_New'), (select id from security_right where name='button_ArticlesDialog_btnSave')),
/* Right: button_ArticlesDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_New'), (select id from security_right where name='button_ArticlesDialog_btnCancel')),
/* Right: button_ArticlesDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_New'), (select id from security_right where name='button_ArticlesDialog_btnClose')),

/* Group: Articles_Edit */
/* Right: button_ArticlesDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_Edit'), (select id from security_right where name='button_ArticlesDialog_btnEdit')),
/* Right: button_ArticlesDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_Edit'), (select id from security_right where name='button_ArticlesDialog_btnSave')),
/* Right: button_ArticlesDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_Edit'), (select id from security_right where name='button_ArticlesDialog_btnCancel')),
/* Right: button_ArticlesDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_Edit'), (select id from security_right where name='button_ArticlesDialog_btnClose')),

/* Group: Articles_Delete */
/* Right: button_ArticlesDialog_btnDelete */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Articles_Delete'), (select id from security_right where name='button_ArticlesDialog_btnDelete')),

/* O F F I C E S */
/* Group: Offices_View */
/* Right: menuCat_MainData */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='menuCat_MainData')),
/* Right: menuItem_MainData_Office */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='menuItem_MainData_Office')),
/* Right: window_OfficesList */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='window_OfficeList')),
/* Right: button_OfficeMain_btnPrint */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnPrint')),
/* Right: button_OfficeList_SearchNo */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeList_SearchNo')),
/* Right: button_OfficeList_SearchName */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeList_SearchName')),
/* Right: button_OfficeList_SearchCity */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeList_SearchCity')),
/* Right: window_OfficesDialog */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='window_OfficeDialog')),
/* Right: button_OfficeDialog_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnHelp')),
/* Right: button_OfficeDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnClose')),
/* Right: button_OfficeMain_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnCancel')),
/* new: sge:07/18/2011  navigation buttons */
/* Right: button_BranchMain_btnFirst */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnFirst')),
/* Right: button_BranchMain_btnPrevious */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnPrevious')),
/* Right: button_BranchMain_btnNext */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnNext')),
/* Right: button_BranchMain_btnLast */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_View'), (select id from security_right where name='button_OfficeMain_btnLast')),

/* Group: Offices_New */
/* Right: button_OfficeDialog_btnNew */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_New'), (select id from security_right where name='button_OfficeMain_btnNew')),
/* Right: button_OfficeDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_New'), (select id from security_right where name='button_OfficeMain_btnSave')),
/* Right: button_OfficeDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_New'), (select id from security_right where name='button_OfficeMain_btnClose')),
/* Right: button_OfficeMain_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_New'), (select id from security_right where name='button_OfficeMain_btnCancel')),

/* Group: Offices_Edit */
/* Right: button_OfficeDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_Edit'), (select id from security_right where name='button_OfficeMain_btnEdit')),
/* Right: button_OfficeDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_Edit'), (select id from security_right where name='button_OfficeMain_btnSave')),
/* Right: button_OfficeDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_Edit'), (select id from security_right where name='button_OfficeMain_btnClose')),
/* Right: button_OfficeMain_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_Edit'), (select id from security_right where name='button_OfficeMain_btnCancel')),

/* Group: Offices_Delete */
/* Right: button_OfficeDialog_btnDelete */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Offices_Delete'), (select id from security_right where name='button_OfficeMain_btnDelete')),

/* Group: Security_Groups */
/* Right: secGroupDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='secGroupDialogWindow')),
/* Right: button_SecGroupDialog_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='button_SecGroupDialog_btnHelp')),
/* Right: button_SecGroupDialog_btnNew */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='button_SecGroupDialog_btnNew')),
/* Right: button_SecGroupDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='button_SecGroupDialog_btnEdit')),
/* Right: button_SecGroupDialog_btnDelete */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='button_SecGroupDialog_btnDelete')),
/* Right: buton_SecGroupDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='button_SecGroupDialog_btnSave')),
/* Right: button_SecGroupDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='button_SecGroupDialog_btnClose')),
/* Right: button_SecGroupDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Groups'), (select id from security_right where name='button_SecGroupDialog_btnCancel')),

/* Group: Security_Roles */
/* Right: secRoleDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='secRoleDialogWindow')),
/* Right: button_SecRoleDialog_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='button_SecRoleDialog_btnHelp')),
/* Right: button_SecRoleDialog_btnNew */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='button_SecRoleDialog_btnNew')),
/* Right: button_SecRoleDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='button_SecRoleDialog_btnEdit')),
/* Right: button_SecRoleDialog_btnDelete */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='button_SecRoleDialog_btnDelete')),
/* Right: button_SecRoleDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='button_SecRoleDialog_btnSave')),
/* Right: button_SecRoleDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='button_SecRoleDialog_btnClose')),
/* Right: button_SecRoleDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Roles'), (select id from security_right where name='button_SecRoleDialog_btnCancel')),

/* Group: Security_Rights */
/* Right: secRightDialogWindow */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='secRightDialogWindow')),
/* Right: button_SecRightDialog_btnHelp */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='button_SecRightDialog_btnHelp')),
/* Right: button_SecRightDialog_btnNew */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='button_SecRightDialog_btnNew')),
/* Right: button_SecRightDialog_btnEdit */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='button_SecRightDialog_btnEdit')),
/* Right: button_SecRightDialog_btnDelete */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='button_SecRightDialog_btnDelete')),
/* Right: button_SecRightDialog_btnSave */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='button_SecRightDialog_btnSave')),
/* Right: button_SecRightDialog_btnClose */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='button_SecRightDialog_btnClose')),
/* Right: button_SecRightDialog_btnCancel */
( nextval('security_group_right_id_seq'), (select id from security_group where name='Security_Rights'), (select id from security_right where name='button_SecRightDialog_btnCancel'));



COMMIT;

/******************** YouTube Music Links ********************/
-- INSERT INTO youtube_link(ytb_id, ytb_interpret, ytb_title, ytb_url, version) VALUES
-- (  1, 'Loquat',                                   'Swing Set Chain',                 'http://www.youtube.com/embed/51G24IVfcaI',   0),
-- (  2, 'Empire of the Sun',                        'We Are The People',               'http://www.youtube.com/embed/1uPL5twyQOw',   0),
-- (  3, 'Loquat',                                   'Harder Hit',                      'http://www.youtube.com/watch?v=aoHUb2r8q-g', 0),
-- (  4, 'THIN LIZZY',                               'Still in Love With You',          'http://www.youtube.com/embed/oHUWXjNU0aM',   0),
-- (  5, 'THIN LIZZY',                               'Whiskey in the jar (1973)',       'http://www.youtube.com/embed/-M2jSzLBzK4',   0),
-- (  6, 'Gary Moore with Phil Lynnot',              'Parisienne Walkways (live)',      'http://www.youtube.com/embed/18FgnFVm5k0',   0),
-- (  7, 'Talking Heads',                            'This must be the place',          'http://www.youtube.com/embed/TTPqPZzH-LA',   0),
-- (  8, 'John Cale and Brian Eno',                  'Spinning away',                   'http://www.youtube.com/embed/-INeMspNSQ0',   0),
-- (  9, 'Metric',                                   'Joyride',                         'http://www.youtube.com/embed/F0ZL5YWP5I8',   0),
-- ( 10, 'Medina',                                   'Kun For Mig + Ensome',            'http://www.youtube.com/embed/5Gf004et0SI',   0),
