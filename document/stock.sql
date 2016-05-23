/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.1.73 : Database - stock
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`stock` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `stock`;

/*Table structure for table `datamatch` */

DROP TABLE IF EXISTS `datamatch`;

CREATE TABLE `datamatch` (
  `available` int(1) DEFAULT NULL,
  `stockkey` int(12) DEFAULT NULL,
  `type` int(12) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `datamatch` */

/*Table structure for table `exdaily` */

DROP TABLE IF EXISTS `exdaily`;

CREATE TABLE `exdaily` (
  `dorder` int(12) DEFAULT NULL,
  `num` int(12) DEFAULT NULL,
  `pid` int(12) DEFAULT NULL,
  `img` varchar(800) DEFAULT NULL,
  `available` int(1) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `exdaily` */

insert  into `exdaily`(`dorder`,`num`,`pid`,`img`,`available`,`addtime`,`id`) values (NULL,112,NULL,'',1,NULL,1),(NULL,1231231,1,'',0,NULL,2),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454215767239.png',0,NULL,3),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216255645.gif',1,NULL,4),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216293968.gif',1,NULL,5),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216304262.gif',1,NULL,6),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216343972.gif',1,NULL,7),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216454896.gif',1,NULL,8),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216522210.gif',1,NULL,9),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216531179.gif',1,NULL,10),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216538772.gif',1,NULL,11),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216546136.gif',1,NULL,12),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216553373.gif',1,NULL,13),(NULL,NULL,1,'',0,NULL,14),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216570833.gif',1,NULL,15),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216580893.gif',1,NULL,16),(NULL,NULL,1,'http://182.254.169.179:8080/uploadImages/img/2016-01/31/s_1454216591097.gif',1,NULL,17);

/*Table structure for table `exlist` */

DROP TABLE IF EXISTS `exlist`;

CREATE TABLE `exlist` (
  `available` int(1) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `exlist` */

insert  into `exlist`(`available`,`title`,`id`) values (1,'腹肌训练',1);

/*Table structure for table `manageauth` */

DROP TABLE IF EXISTS `manageauth`;

CREATE TABLE `manageauth` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `available` int(1) DEFAULT NULL,
  `menuid` int(12) DEFAULT NULL,
  `userid` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

/*Data for the table `manageauth` */

insert  into `manageauth`(`id`,`available`,`menuid`,`userid`) values (88,1,7,1),(89,1,1,1),(90,1,8,1),(91,1,2,1),(92,1,8,1),(93,1,3,1),(94,1,6,1),(95,1,3,1),(96,1,6,1),(97,1,4,1),(98,1,4,1),(99,1,7,1),(100,1,6,1),(101,1,7,1),(102,1,6,1),(103,1,8,1),(104,1,7,1),(105,1,8,1),(106,1,7,1),(107,1,8,1),(108,1,8,1),(109,1,6,1),(110,1,6,1),(111,1,7,1),(112,1,7,1),(113,1,8,1),(114,1,8,1);

/*Table structure for table `managemenus` */

DROP TABLE IF EXISTS `managemenus`;

CREATE TABLE `managemenus` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `available` int(1) DEFAULT NULL,
  `pass` int(1) DEFAULT NULL,
  `dorder` int(12) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `pid` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `managemenus` */

insert  into `managemenus`(`id`,`available`,`pass`,`dorder`,`url`,`title`,`pid`) values (1,1,1,NULL,NULL,'StockManage',0),(2,1,1,NULL,'/manage/stock/list.do','stock管理',1),(3,1,1,NULL,'/manage/stockkey/list.do','StockList',1),(4,1,1,NULL,'/manage/datamatch/list.do','数据抓取',1),(5,1,1,1,NULL,'每日生活',0),(6,1,1,NULL,'/manage/exdaily/list.do','锻炼',5),(7,1,1,NULL,'/manage/exlist/list.do','锻炼列表',5),(8,1,1,NULL,'/manage/moneytype/list.do','资金管理',5);

/*Table structure for table `manageuser` */

DROP TABLE IF EXISTS `manageuser`;

CREATE TABLE `manageuser` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `available` int(1) DEFAULT NULL,
  `pass` int(1) DEFAULT NULL,
  `img` varchar(800) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `level` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `manageuser` */

insert  into `manageuser`(`id`,`available`,`pass`,`img`,`username`,`password`,`level`) values (1,1,1,'','admin','admin',1);

/*Table structure for table `sign` */

DROP TABLE IF EXISTS `sign`;

CREATE TABLE `sign` (
  `type` int(12) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `available` int(1) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sign` */

insert  into `sign`(`type`,`addtime`,`available`,`id`) values (1,'2016-01-30 22:57:38',1,1),(2,'2016-01-30 23:20:06',1,2),(2,'2016-01-30 23:22:07',1,3),(1,'2016-01-31 14:37:49',1,4);

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `available` int(1) DEFAULT NULL,
  `day` int(12) DEFAULT NULL,
  `month` int(12) DEFAULT NULL,
  `year` int(12) DEFAULT NULL,
  `change_hand` double(9,3) DEFAULT NULL,
  `amount_trade` double(9,3) DEFAULT NULL,
  `amount_stock` double(9,3) DEFAULT NULL,
  `lowprice` double(9,3) DEFAULT NULL,
  `highprice` double(9,3) DEFAULT NULL,
  `endprice` double(9,3) DEFAULT NULL,
  `startprice` double(9,3) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `stockkey` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `stock` */

insert  into `stock`(`available`,`day`,`month`,`year`,`change_hand`,`amount_trade`,`amount_stock`,`lowprice`,`highprice`,`endprice`,`startprice`,`id`,`stockkey`) values (1,21,1,2016,6.950,5.260,53.120,9.750,10.060,9.810,9.760,1,601169),(1,21,1,2016,16.100,9.834,88.633,10.630,11.470,10.660,11.080,2,541),(1,22,1,2016,0.318,3.840,38.960,9.750,9.960,9.880,9.850,3,601169),(1,22,1,2016,3.300,9.037,83.846,10.450,11.000,10.850,10.900,4,541),(1,25,1,2016,0.290,3.666,36.809,9.840,10.010,9.950,9.940,5,601169),(1,25,1,2016,1.050,1.115,10.222,10.700,11.200,10.790,10.910,6,541),(1,26,1,2016,0.380,4.756,48.572,9.480,10.000,9.570,9.880,7,601169),(1,26,1,2016,1.610,1.591,15.735,9.710,10.720,9.710,10.600,8,541),(1,29,1,2016,37.200,8.964,10.031,8.600,9.210,9.070,8.630,9,541),(1,29,1,2016,66.660,6.244,68.304,8.780,9.490,9.320,8.900,10,601169),(1,1,2,2016,133.880,4.645,50.031,9.140,9.400,9.310,9.280,11,601169),(1,2,2,2016,112.840,3.951,41.714,9.250,9.550,9.430,9.270,12,601169),(1,3,2,2016,43.300,2.897,30.646,9.340,9.520,9.440,9.380,13,601169),(1,4,2,2016,167.920,2.983,31.187,9.440,9.620,9.590,9.440,14,601169);

/*Table structure for table `stockkey` */

DROP TABLE IF EXISTS `stockkey`;

CREATE TABLE `stockkey` (
  `name` varchar(200) DEFAULT NULL,
  `stock_key` int(12) DEFAULT NULL,
  `available` int(1) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `stockkey` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `stockkey` */

insert  into `stockkey`(`name`,`stock_key`,`available`,`id`,`stockkey`) values ('北京银行',601169,1,1,NULL),('佛山照明',541,1,2,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
