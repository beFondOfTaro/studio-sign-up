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

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '权限名',
  `owner_available` tinyint(1) NOT NULL DEFAULT '1' COMMENT '该权限对应的资源是否是允许资源所有者访问',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `permission` */

insert  into `permission`(`id`,`name`,`owner_available`) values (1,'user:add',1),(2,'user:update',1),(3,'user:delete',2),(4,'user:find',1),(5,'teacherInfo:add',1),(6,'teacherInfo:update',1),(7,'teacherInfo:delete',2),(8,'teacherInfo:find',1),(9,'studentInfo:add',1),(10,'studentInfo:update',1),(11,'studentInfo:delete',2),(12,'role:add',2),(13,'role:update',2),(14,'role:delete',2),(15,'role:find',1),(16,'project:add',1),(17,'project:update',1),(18,'project:delete',2),(19,'project:find',1),(20,'studentInfo:find',1),(22,'userRole:find',1),(23,'userRole:add',2),(24,'userRole:update',2),(25,'userRole:delete',2),(26,'rolePermission:find',1),(27,'rolePermission:add',2),(28,'rolePermission:update',2),(29,'rolePermission:delete',2),(30,'sighUpInfo:add',1),(31,'sighUpInfo:find',1),(32,'sighUpInfo:update',1),(33,'sighUpInfo:delete',1),(34,'permission:find',1),(35,'permission:delete',2),(36,'permission:add',2),(37,'permission:update',2);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `project` */

insert  into `project`(`id`,`user_id`,`name`,`introduction`,`created_time`) values (1,1,'云平台','asdas','2017-11-23 18:31:36'),(2,1,'区块链','asdasd','2017-11-23 18:31:37'),(4,1,'wingstudio','','2017-12-01 19:04:24'),(5,1,'嘻嘻嘻','12312312asdasd','2017-12-12 23:39:36');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_2` (`name`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (2,'学生'),(4,'教师'),(3,'管理员');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa6jx8n8xkesmjmv6jqug6bg68` (`role_id`),
  KEY `FKf8yllw1ecvwqy3ehyxawqa1qp` (`permission_id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`role_id`,`permission_id`) values (1,3,32),(2,3,15),(3,3,34),(4,3,35),(5,3,36),(6,3,37),(7,3,16),(8,3,1),(9,3,2),(12,4,16),(13,3,19),(14,2,19),(15,4,19),(16,4,20),(17,4,17),(18,4,17),(19,4,31),(20,4,32),(21,4,34),(22,2,8),(23,4,31),(24,3,31);

/*Table structure for table `sigh_up_info` */

DROP TABLE IF EXISTS `sigh_up_info`;

CREATE TABLE `sigh_up_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '学生id',
  `project_id` int(11) DEFAULT NULL COMMENT '报名项目id',
  `personal_introduction` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '个人介绍',
  `check_code` int(11) DEFAULT '1' COMMENT '审核代码,1:待审核，2:未通过,3:通过',
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

insert  into `sigh_up_info`(`id`,`user_id`,`project_id`,`personal_introduction`,`check_code`,`created_time`) values (5,1,1,'asdasd',3,'2017-12-01 21:54:56');

/*Table structure for table `student_info` */

DROP TABLE IF EXISTS `student_info`;

CREATE TABLE `student_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `student_number` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '学号',
  `major` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '专业',
  `qq_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号',
  `photo` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '学生照片路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `student_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `student_info` */

insert  into `student_info`(`id`,`user_id`,`student_number`,`major`,`qq_number`,`photo`) values (6,1,'20158808','',NULL,'85f0338b-dccd-4d1e-9ff2-43a410759df6.dll');

/*Table structure for table `teacher_info` */

DROP TABLE IF EXISTS `teacher_info`;

CREATE TABLE `teacher_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `teacher_number` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '教师工号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `teacher_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `teacher_info` */

insert  into `teacher_info`(`id`,`user_id`,`teacher_number`) values (2,12,'123x'),(3,13,'123x');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `real_name` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`real_name`,`phone`,`created_time`) values (1,'hyzz','12354565','黄雅哲','1235661','2017-11-23 18:11:59'),(2,'admin','123456','asdas','1231234','2017-12-06 22:47:50'),(3,'test','123456','阿萨德','1234','2017-12-06 23:32:35'),(4,'test2','123456','阿斯达斯','78876','2017-12-09 18:20:52'),(5,'test3','123456','阿斯达斯','78876','2017-12-09 18:26:51'),(8,'test4','123456','阿斯达斯','78876','2017-12-09 18:31:25'),(9,'stu1','12354565','asd','12312','2017-12-12 16:08:51'),(12,'stu2','12354565','asd','12312','2017-12-12 17:11:05'),(13,'stu3','12354565','asd','12312','2017-12-12 17:14:04');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,1,3),(2,4,2),(3,5,2),(5,8,3),(6,9,2),(7,12,4),(8,13,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
