/*
SQLyog Enterprise - MySQL GUI v7.0 Beta 1
MySQL - 5.6.12 : Database - student
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`student` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `student`;

/*Table structure for table `c` */

DROP TABLE IF EXISTS `c`;

CREATE TABLE `c` (
  `Cno` varchar(50) NOT NULL,
  `Cname` varchar(50) DEFAULT NULL,
  `Teacher` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c` */

insert  into `c`(`Cno`,`Cname`,`Teacher`) values ('101','Java语言','马俊'),('102','C++','程建军'),('103','电路分析','刘同山');

/*Table structure for table `s` */

DROP TABLE IF EXISTS `s`;

CREATE TABLE `s` (
  `Sid` varchar(50) NOT NULL,
  `Sname` varchar(50) DEFAULT NULL,
  `Sex` varchar(2) DEFAULT NULL,
  `Sage` int(11) DEFAULT NULL,
  `Sdep` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s` */

insert  into `s`(`Sid`,`Sname`,`Sex`,`Sage`,`Sdep`) values ('10001','张三','男',18,'信息学院'),('10002','马晓红','女',20,'土木工程学院');

/*Table structure for table `sc` */

DROP TABLE IF EXISTS `sc`;

CREATE TABLE `sc` (
  `Sid` varchar(50) NOT NULL,
  `Cno` varchar(50) NOT NULL,
  `Grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`Sid`,`Cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc` */

insert  into `sc`(`Sid`,`Cno`,`Grade`) values ('10001','101',98),('10001','102',90),('10001','103',90),('10002','101',89),('10002','102',92),('10002','103',94);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
