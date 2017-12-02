/*
SQLyog v10.2 
MySQL - 5.7.19-log : Database - studio_sigh_up_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studio_sigh_up_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `studio_sigh_up_db`;

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '发布者id',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '项目类型名',
  `introduction` varchar(600) COLLATE utf8_bin DEFAULT NULL COMMENT '项目简介',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`),
  CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `project` */

insert  into `project`(`id`,`user_id`,`name`,`introduction`,`created_time`) values (1,1,'云平台','asdas','2017-11-23 18:31:36'),(2,1,'区块链','asdasd','2017-11-23 18:31:37'),(4,1,'wingstudio','','2017-12-01 19:04:24');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (2,'学生'),(4,'教师'),(3,'管理员');

/*Table structure for table `sigh_up_info` */

DROP TABLE IF EXISTS `sigh_up_info`;

CREATE TABLE `sigh_up_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '学生id',
  `project_id` int(11) DEFAULT NULL COMMENT '报名项目id',
  `personal_introduction` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '个人介绍',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FKlqt3kvcxenwb5gktm2vtjjfs3` (`project_id`),
  KEY `FK78pjncb5n9rpws5o3vtimi2ts` (`user_id`),
  CONSTRAINT `FK78pjncb5n9rpws5o3vtimi2ts` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKhw9od2oyvahdqmmf1yndr3wh1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlqt3kvcxenwb5gktm2vtjjfs3` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `sigh_up_info_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `sigh_up_info_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sigh_up_info` */

insert  into `sigh_up_info`(`id`,`user_id`,`project_id`,`personal_introduction`,`created_time`) values (5,1,1,'asdasd','2017-12-01 21:54:56');

/*Table structure for table `student_info` */

DROP TABLE IF EXISTS `student_info`;

CREATE TABLE `student_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '所属用户id',
  `student_number` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '学号',
  `major` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '专业',
  `qq_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号',
  `photo` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '学生照片路径',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `student_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `student_info` */

/*Table structure for table `teacher_info` */

DROP TABLE IF EXISTS `teacher_info`;

CREATE TABLE `teacher_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '所属user的id',
  `teacher_number` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '教师工号',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `teacher_info_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `teacher_info` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `username` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `real_name` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`role_id`,`username`,`password`,`real_name`,`phone`,`created_time`) values (1,2,'hyzz','12354565','黄雅哲','1235661','2017-11-23 18:11:59');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
