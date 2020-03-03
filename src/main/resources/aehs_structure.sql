/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.1.26-MariaDB : Database - aehs_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`aehs_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `aehs_db`;

/*Table structure for table `diseases` */

DROP TABLE IF EXISTS `diseases`;

CREATE TABLE `diseases` (
  `_id` varchar(100) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(70) DEFAULT NULL,
  `description` text,
  `prescription` text,
  `updated` char(10) DEFAULT 'NO',
  `deleted` char(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `farm_detail` */

DROP TABLE IF EXISTS `farm_detail`;

CREATE TABLE `farm_detail` (
  `_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `main_plantation` tinytext,
  `owner` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`),
  KEY `owner` (`owner`),
  CONSTRAINT `farm_detail_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `system_user` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ml_prediction` */

DROP TABLE IF EXISTS `ml_prediction`;

CREATE TABLE `ml_prediction` (
  `_id` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `input_param1` varchar(50) DEFAULT NULL,
  `input_param2` varchar(50) DEFAULT NULL,
  `input_param3` varchar(50) DEFAULT NULL,
  `input_param4` varchar(50) DEFAULT NULL,
  `input_param5` varchar(50) DEFAULT NULL,
  `prediction` varchar(50) DEFAULT NULL,
  `farm_detail` varchar(255) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `farm_detail` (`farm_detail`),
  KEY `prediction` (`prediction`),
  CONSTRAINT `ml_prediction_ibfk_1` FOREIGN KEY (`farm_detail`) REFERENCES `farm_detail` (`_id`),
  CONSTRAINT `ml_prediction_ibfk_2` FOREIGN KEY (`prediction`) REFERENCES `diseases` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `plant_stand` */

DROP TABLE IF EXISTS `plant_stand`;

CREATE TABLE `plant_stand` (
  `_id` varchar(50) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `precip` */

DROP TABLE IF EXISTS `precip`;

CREATE TABLE `precip` (
  `_id` varchar(50) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pred_date` */

DROP TABLE IF EXISTS `pred_date`;

CREATE TABLE `pred_date` (
  `_id` varchar(10) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `system_user` */

DROP TABLE IF EXISTS `system_user`;

CREATE TABLE `system_user` (
  `_id` varchar(255) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `account_status` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `phone_number` varchar(25) DEFAULT NULL,
  `user_role` varchar(50) DEFAULT NULL,
  `updated` varchar(50) DEFAULT 'NO',
  `deleted` varchar(50) DEFAULT 'NO',
  PRIMARY KEY (`_id`),
  KEY `user_role` (`user_role`),
  CONSTRAINT `system_user_ibfk_1` FOREIGN KEY (`user_role`) REFERENCES `user_role` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `temperature` */

DROP TABLE IF EXISTS `temperature`;

CREATE TABLE `temperature` (
  `_id` varchar(50) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `_id` varchar(50) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
