INSERT INTO `CMS_DB`.`userroles` (`user_role_id`,`user_role_name`) VALUES (1,'Designer');
INSERT INTO `CMS_DB`.`userroles` (`user_role_id`,`user_role_name`) VALUES (2,'Content Manager');
INSERT INTO `CMS_DB`.`userroles` (`user_role_id`,`user_role_name`) VALUES (3,'Casino Manager');

INSERT INTO `CMS_DB`.`users` (`user_email_id`, `User_Name`, `User_Role_id`) VALUES ('abcd@abcd.com', 'Harsh Hajela', '3');
INSERT INTO `CMS_DB`.`users` (`user_email_id`, `User_Name`, `User_Role_id`) VALUES ('xyz@xyz.com', 'Saurabh Negi', '2');
INSERT INTO `CMS_DB`.`users` (`user_email_id`, `User_Name`, `User_Role_id`) VALUES ('blah@blah.com', 'Gaurav Kukrety', '1');

INSERT INTO `CMS_DB`.`accessoperations` (`operation_id`,`operation_name`) VALUES (1,'search');
INSERT INTO `CMS_DB`.`accessoperations` (`operation_id`,`operation_name`) VALUES (2,'add');
INSERT INTO `CMS_DB`.`accessoperations` (`operation_id`,`operation_name`) VALUES (3,'delete');
INSERT INTO `CMS_DB`.`accessoperations` (`operation_id`,`operation_name`) VALUES (4,'modify');

INSERT INTO `CMS_DB`.`assets` (`asset_id`,`asset_name`) VALUES (1,'Text');
INSERT INTO `CMS_DB`.`assets` (`asset_id`,`asset_name`) VALUES (2,'Digital');
INSERT INTO `CMS_DB`.`assets` (`asset_id`,`asset_name`) VALUES (3,'Banner');
INSERT INTO `CMS_DB`.`assets` (`asset_id`,`asset_name`) VALUES (4,'Language');

INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (1,2,1);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (1,2,2);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (1,2,3);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (1,2,4);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (2,1,1);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (2,1,2);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (2,1,3);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (2,1,4);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (2,2,1);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (2,3,1);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (3,1,1);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (3,2,1);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (3,3,1);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (3,3,2);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (3,3,3);
INSERT INTO `CMS_DB`.`user_access_asset_operations` (`user_role_id`,`asset_id`,`operation_id`) VALUES (3,3,4);


