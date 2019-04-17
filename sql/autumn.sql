-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: autumn
-- ------------------------------------------------------
-- Server version	5.6.31

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
-- Table structure for table `au_news`
--

DROP TABLE IF EXISTS `au_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `fileurl` varchar(255) DEFAULT NULL,
  `showImg` varchar(255) DEFAULT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='新闻';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_news`
--

LOCK TABLES `au_news` WRITE;
/*!40000 ALTER TABLE `au_news` DISABLE KEYS */;
INSERT INTO `au_news` VALUES (1,'阿斯蒂芬','1212',1,'2019-03-22 21:21:00','/news/file/20190322/201903222119288_5ba24c68d8.htm',NULL,'阿斯顿发斯蒂芬'),(3,'aaa','sfsad',1,NULL,'/news/file/20190322/201903222216400_9b7e3e8659.htm',NULL,'阿达水电费'),(4,'o\'o','解决',1,'2019-03-23 14:07:26','/news/file/20190323/201903231407256_7d77132403.htm',NULL,'测试测试');
/*!40000 ALTER TABLE `au_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_admin`
--

DROP TABLE IF EXISTS `web_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(45) DEFAULT NULL,
  `loginPass` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_admin`
--

LOCK TABLES `web_admin` WRITE;
/*!40000 ALTER TABLE `web_admin` DISABLE KEYS */;
INSERT INTO `web_admin` VALUES (2,'admin','e10adc3949ba59abbe56e057f20f883e','admin',1,'11122'),(3,'11','698d51a19d8a121ce581499d7b701668','22211',1,'11');


CREATE TABLE `autumn`.`au_photos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `desc` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `createTime` DATETIME NULL,
  PRIMARY KEY (`id`))
DEFAULT CHARACTER SET = utf8
COMMENT = '相册';

CREATE TABLE `autumn`.`au_photos_list` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `imgUrl` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `sort` INT NULL,
  `photosId` INT NULL,
  PRIMARY KEY (`id`))
DEFAULT CHARACTER SET = utf8
COMMENT = '相册明细';


ALTER TABLE `autumn`.`au_photos`
ADD COLUMN `isPublic` TINYINT(1) NULL AFTER `createTime`;

ALTER TABLE `autumn`.`au_photos`
ADD COLUMN `imgUrl` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL AFTER `isPublic`;



