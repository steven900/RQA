CREATE DATABASE  IF NOT EXISTS `stock` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `stock`;
-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: stock
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `managemenus`
--

DROP TABLE IF EXISTS `managemenus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `managemenus` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `available` int(1) DEFAULT NULL,
  `pass` int(1) DEFAULT NULL,
  `dorder` int(12) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `pid` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managemenus`
--

LOCK TABLES `managemenus` WRITE;
/*!40000 ALTER TABLE `managemenus` DISABLE KEYS */;
INSERT INTO `managemenus` VALUES (1,1,1,NULL,NULL,'StockManage',0),(2,1,1,NULL,'/manage/stock/list.do','stock管理',1),(3,1,1,NULL,'/manage/stockkey/list.do','StockList',1),(4,1,1,NULL,'/manage/datamatch/list.do','数据抓取',1),(5,1,1,1,NULL,'每日生活',0),(6,1,1,NULL,'/manage/exdaily/list.do','锻炼',5),(7,1,1,NULL,'/manage/exlist/list.do','锻炼列表',5),(8,0,1,NULL,'/manage/moneytype/list.do','资金管理',5),(9,1,1,NULL,'/manage/moneymanagement/list.do','moneymanage',5),(10,1,1,NULL,'/manage/typemanage/list.do','资金管理',5),(11,1,1,NULL,'/manage/exmanage/list.do','运动管理',5),(12,1,1,NULL,'/manage/consumetype/list.do','消费类型',5),(13,1,1,NULL,'/manage/weightmange/list.do','体重管理',5),(14,1,1,NULL,'/manage/lifelogs/list.do','日记',5);
/*!40000 ALTER TABLE `managemenus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-17 10:28:18
