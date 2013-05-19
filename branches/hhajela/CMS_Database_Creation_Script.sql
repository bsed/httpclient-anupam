create database `CMS_DB`;
use `CMS_DB`;

CREATE TABLE `CMS_DB`.`userroles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_role_name` varchar(100) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ;

CREATE TABLE `CMS_DB`.`users` (
  `user_email_id` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_email_id`),
  KEY `role_id_idx` (`user_role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`user_role_id`) REFERENCES `userroles` (`user_role_id`) 
) ;

CREATE TABLE `CMS_DB`.`accessoperations` (
  `operation_id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(45) NOT NULL,
  PRIMARY KEY (`operation_id`)
) ;

CREATE TABLE `CMS_DB`.`assets` (
  `asset_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_name` varchar(45) NOT NULL,
  PRIMARY KEY (`asset_id`)
) ;

CREATE TABLE `CMS_DB`.`user_access_asset_operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_role_id` int(11) DEFAULT NULL,
  `asset_id` int(11) DEFAULT NULL,
  `operation_id` int(11) DEFAULT NULL,
  KEY `user_role_id_idx` (`user_role_id`),
  KEY `asset_id_idx` (`asset_id`),
  KEY `operation_id_idx` (`operation_id`),
  PRIMARY KEY (`id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `userroles` (`user_role_id`) ,
  CONSTRAINT `asset_id` FOREIGN KEY (`asset_id`) REFERENCES `assets` (`asset_id`) ,
  CONSTRAINT `operation_id` FOREIGN KEY (`operation_id`) REFERENCES `accessoperations` (`operation_id`) 
) ;

CREATE TABLE `CMS_DB`.`skin` (
  `skin_id` int(11) NOT NULL AUTO_INCREMENT,
  `skin_url` varchar(200) NOT NULL,
  PRIMARY KEY (`skin_id`)
) ;

CREATE TABLE `CMS_DB`.`supportedlanguages` (
  `lang_id` int(4) NOT NULL AUTO_INCREMENT,
  `lang_locale` varchar(20) NOT NULL,
  `lang_fullname` varchar(60) NOT NULL,
  PRIMARY KEY (`lang_id`)
) ;

CREATE TABLE `CMS_DB`.`digitalassets` (
  `digital_asset_id` int(11) NOT NULL AUTO_INCREMENT,
  `digital_asset_name` varchar(100) NOT NULL,
  `digital_asset_path` varchar(200) NOT NULL,
  `digital_asset_soft_delete` tinyint(4) DEFAULT 0,
  `digital_asset_lang_id` int(11) NOT NULL,
  `digital_asset_skin_id` int(11) NOT NULL,
  PRIMARY KEY (`digital_asset_id`),
  KEY `lang_id_idx` (`digital_asset_lang_id`),
  KEY `skin_id_idx` (`digital_asset_skin_id`),
  CONSTRAINT `digital_lang_id` FOREIGN KEY (`digital_asset_lang_id`) REFERENCES `supportedlanguages` (`lang_id`) ,
  CONSTRAINT `digital_skin_id` FOREIGN KEY (`digital_asset_skin_id`) REFERENCES `skin` (`skin_id`) 
) ;


CREATE TABLE `CMS_DB`.`textassets` (
  `text_id` int(11) NOT NULL AUTO_INCREMENT,
  `text_name` varchar(100) DEFAULT NULL,
  `text_details` varchar(200),
  `text_soft_delete` tinyint(4) DEFAULT 0,
  `text_lang_id` int(11) NOT NULL,
  `text_skin_id` int(11) NOT NULL,
  PRIMARY KEY (`text_id`),
  KEY `lang_id_idx` (`text_lang_id`),
  KEY `skin_id_idx` (`text_skin_id`),
  CONSTRAINT `lang_id` FOREIGN KEY (`text_lang_id`) REFERENCES `supportedlanguages` (`lang_id`) ,
  CONSTRAINT `skin_id` FOREIGN KEY (`text_skin_id`) REFERENCES `skin` (`skin_id`) 
) ;


CREATE TABLE `CMS_DB`.`banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(100) DEFAULT NULL,
  `banner_details` varchar(100),
  `banner_soft_delete` tinyint(4),
  PRIMARY KEY (`banner_id`)
) ;

CREATE TABLE `banner_text_digital` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_id` int(11) DEFAULT NULL,
  `text_id` int(11) DEFAULT NULL,
  `digital_id` int(11) DEFAULT NULL,
  `soft_delete` tinyint(4) DEFAULT 0,
  KEY `banner_id_idx` (`banner_id`),
  KEY `text_id_idx` (`text_id`),
  KEY `digital_id_idx` (`digital_id`),
  PRIMARY KEY (`id`),
  CONSTRAINT `banner_id` FOREIGN KEY (`banner_id`) REFERENCES `banner` (`banner_id`),
  CONSTRAINT `text_id` FOREIGN KEY (`text_id`) REFERENCES `textassets` (`text_id`) ,
  CONSTRAINT `digital_id` FOREIGN KEY (`digital_id`) REFERENCES `digitalassets` (`digital_asset_id`)
) ;


    