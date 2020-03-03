/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.4.12-MariaDB : Database - aehs_db
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
  `disease_id` varchar(100) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(70) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `prescription` text DEFAULT NULL,
  `updated` char(10) DEFAULT 'NO',
  `deleted` char(10) DEFAULT 'NO',
  PRIMARY KEY (`disease_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `diseases` */

insert  into `diseases`(`disease_id`,`code`,`name`,`description`,`prescription`,`updated`,`deleted`) values ('1','D1','Swollen Shoot','Description of Disease 1\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','Prescription for Disease 1\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','NO','NO'),('2','D2','Black Pod','Description of Disease 2\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','Prescription for Disease 2\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','NO','NO'),('3','D3','Rhizoctonia Root Rot','Description of Disease 3\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','Prescription for Disease 3\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','NO','NO'),('4','D4','Phytophthora Rot','Description of Disease 4\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','Prescription for Disease 4\r\n\r\nFisher,D.H. and Schlimmer,J.C. (1988). Concept Simplification and Predictive Accuracy. Proceedings of the Fifth International Conference on Machine Learning (pp. 22-28). Ann Arbor, Michigan: Morgan Kaufmann.','NO','NO');

/*Table structure for table `farm_detail` */

DROP TABLE IF EXISTS `farm_detail`;

CREATE TABLE `farm_detail` (
  `_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `main_plantation` tinytext DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`),
  KEY `owner` (`owner`),
  CONSTRAINT `farm_detail_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `system_user` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `farm_detail` */

insert  into `farm_detail`(`_id`,`name`,`main_plantation`,`owner`,`location`,`updated`,`deleted`) values ('adfarm-41d72d59-2488-4231-b0b5-9ce2bf7a4467','Akropong','Soya Beans','2dab9208-6104-4519-be59-0e62e231fac4','Koforidua','NO','NO'),('ffarm-8e30ba10-4a51-4d4e-8ce1-4fdf2726567c','Farm','Dummy','2dab9208-6104-4519-be59-0e62e231fac4','Dummy','NO','NO'),('rfarm-dff5d59e-a9b5-4eef-92b2-7dfcc16c7c5d','Riddel','Cocoa','2dab9208-6104-4519-be59-0e62e231fac4','Regina','NO','NO'),('tfarm-7d771746-7c81-46a5-a428-6798c4202110','Tamale','Maize','2dab9208-6104-4519-be59-0e62e231fac4','Tamale East','NO','NO');

/*Table structure for table `ml_prediction` */

DROP TABLE IF EXISTS `ml_prediction`;

CREATE TABLE `ml_prediction` (
  `_id` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `input_param1` varchar(50) DEFAULT NULL,
  `input_param2` varchar(50) DEFAULT NULL,
  `input_param3` varchar(50) DEFAULT NULL,
  `input_param4` varchar(50) DEFAULT NULL,
  `diseases` varchar(100) DEFAULT NULL,
  `disease_name` varchar(100) DEFAULT NULL,
  `farm_detail` varchar(255) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `farm_detail` (`farm_detail`),
  KEY `prediction` (`diseases`),
  CONSTRAINT `ml_prediction_ibfk_1` FOREIGN KEY (`farm_detail`) REFERENCES `farm_detail` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ml_prediction` */

insert  into `ml_prediction`(`_id`,`date`,`input_param1`,`input_param2`,`input_param3`,`input_param4`,`diseases`,`disease_name`,`farm_detail`,`updated`,`deleted`) values ('000000001','2020-02-28','0','1','1','1','1','Swollen Shoot','adfarm-41d72d59-2488-4231-b0b5-9ce2bf7a4467','NO','NO'),('pred-14a76a5f-6d0b-41bb-b152-975b528ba366','2020-03-02','2','2','1','2','4','Phytophthora Rot','rfarm-dff5d59e-a9b5-4eef-92b2-7dfcc16c7c5d','NO','NO'),('pred-1a458544-fc00-4fc6-b7ba-e27916881829','2020-03-02','1','1','1','2','4','Phytophthora Rot','tfarm-7d771746-7c81-46a5-a428-6798c4202110','NO','NO'),('pred-6f1dc7c8-4a52-4960-9516-dc664855b0a3','2020-03-02','2','2','0','6','2','Black Pod','rfarm-dff5d59e-a9b5-4eef-92b2-7dfcc16c7c5d','NO','NO'),('pred-8ac723df-758d-4aad-9447-4abdf241ac87','2020-03-01','2','0','0','2','2','Black Pod','rfarm-dff5d59e-a9b5-4eef-92b2-7dfcc16c7c5d','NO','NO'),('pred-9470fe64-1533-42bf-a621-d7c9333fbbd8','2020-03-02','1','0','1','4','2','Black Pod','adfarm-41d72d59-2488-4231-b0b5-9ce2bf7a4467','NO','NO'),('pred-bbb653ba-e3f5-4a48-b379-7ca5073eee13','2020-02-29','2','1','0','5','2','Black Pod','adfarm-41d72d59-2488-4231-b0b5-9ce2bf7a4467','NO','NO'),('pred-ee42fefe-5d08-47f4-8543-501888d2fafc','2020-03-02','2','1','1','3','4','Phytophthora Rot','rfarm-dff5d59e-a9b5-4eef-92b2-7dfcc16c7c5d','NO','NO');

/*Table structure for table `plant_stand` */

DROP TABLE IF EXISTS `plant_stand`;

CREATE TABLE `plant_stand` (
  `_id` varchar(50) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `plant_stand` */

insert  into `plant_stand`(`_id`,`value`,`updated`,`deleted`) values ('0','Normal','NO','NO'),('1','Less-Than Normal','NO','NO');

/*Table structure for table `precip` */

DROP TABLE IF EXISTS `precip`;

CREATE TABLE `precip` (
  `_id` varchar(50) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `precip` */

insert  into `precip`(`_id`,`value`,`updated`,`deleted`) values ('0','Less-Than Normal','NO','NO'),('1','Normal','NO','NO'),('2','Greater-Than Normal','NO','NO');

/*Table structure for table `pred_date` */

DROP TABLE IF EXISTS `pred_date`;

CREATE TABLE `pred_date` (
  `_id` varchar(10) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pred_date` */

insert  into `pred_date`(`_id`,`value`,`updated`,`deleted`) values ('0','April','NO','NO'),('1','May','NO','NO'),('2','June','NO','NO'),('3','July','NO','NO'),('4','August','NO','NO'),('5','September','NO','NO'),('6','October','NO','NO');

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

/*Data for the table `system_user` */

insert  into `system_user`(`_id`,`firstname`,`lastname`,`username`,`password`,`account_status`,`gender`,`phone_number`,`user_role`,`updated`,`deleted`) values ('00001','Alberto','Gyamfi','alberto','4bdbc215d8dc3c571e802a69bced0c3071cc4a1f129ad97e15b357018aac6cd4','Active','Male','3060000000','1','NO','NO'),('00002','Siaj','Iddi','siaj','3a14dac9f87a7ffd33673cc3c4808285123d91046e93add14e9bf325506315b3','Active','Male','3069999999','2','NO','NO'),('01810bf7-019a-4067-a673-f143740460b9','Jelastic','Grid','jelastic','e6ef37cba577995394a40da1f6318eac641cd2bfbb584fb6714b8d5275e698e4','Active','Male','1000000001','1','NO','NO'),('11efa5ab-29f6-4f3f-89a8-b928354fdb4a','Riddel','UoR1','ruor','ruor','Active','Female','0245212012','3','NO','NO'),('16199a53-d463-41ca-a91a-1b1b77b5ef03','Edit','Working','dsda','dsda','Active','Male','3065000000','3','NO','NO'),('2dab9208-6104-4519-be59-0e62e231fac4','Sibdow','Iddrisu','siddrisu','siddrisu','Active','Male','0244748913','3','NO','NO'),('441e6067-4798-43ba-aa67-59a307cac9ef','Test 1','Farmer 1','tfarmer 1','tfarmer 1','Active','Male','0243761521','3','NO','NO'),('78acc4e7-f7e3-490e-9c12-e9acc1c3d5ac','Tobi','Tobi','ttobi','97705976e4340171c826ead52bb64441e368e63046d9b100afa492183d846340','Active','Female','3065000000','1','NO','NO'),('7e61215f-d573-4494-9513-cd502c2c633b','Test 2','Farmer 2','tfarmer 2','tfarmer 2','Active','Female','0244000000','3','NO','NO'),('9e00a37a-e3e1-4b64-95b0-6538954424e5','Sib','Idd','sidd','sidd','Active','Male','0212221212','3','NO','NO'),('a5e4772c-e70f-44d2-8e64-d2d5dfdcde17','Sibdow','Iddrisu','sip099@uregina.ca','beb84a455435880535522ec5f8cb5c0a0aec9d9504e4d7d52f078d322330d291','Active','Male','3065811592','2','NO','NO'),('b28a895e-8c16-44b1-a62d-cb5fb0a85dff','new','new','nnew','nnew','Active','Male','0255615151','3','NO','NO'),('c1594e9a-837b-46aa-9284-d792a7926dbb','dcc','cdsc','dcdsc','dcdsc','Active','Male','dscdcd','3','NO','NO'),('c461c9ad-9997-4da4-b311-6f435b7629e9','ddcs','cdcds','dcdcds','dcdcds','Active','Female','5454545151','3','NO','NO');

/*Table structure for table `temperature` */

DROP TABLE IF EXISTS `temperature`;

CREATE TABLE `temperature` (
  `_id` varchar(50) NOT NULL,
  `value` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `temperature` */

insert  into `temperature`(`_id`,`value`,`updated`,`deleted`) values ('0','Less-Than Normal','NO','NO'),('1','Normal','NO','NO'),('2','Greater-Than Normal','NO','NO');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `_id` varchar(50) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `updated` varchar(10) DEFAULT 'NO',
  `deleted` varchar(10) DEFAULT 'NO',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert  into `user_role`(`_id`,`role_name`,`updated`,`deleted`) values ('1','System Administrator','NO','NO'),('2','Extension Officer','NO','NO'),('3','Farmer','NO','NO');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
