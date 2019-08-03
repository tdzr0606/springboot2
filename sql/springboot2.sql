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
  `id` varchar(20) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `fileurl` varchar(255) DEFAULT NULL,
  `showImg` varchar(255) DEFAULT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sid`),
  KEY `id_index` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='新闻';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_news`
--

LOCK TABLES `au_news` WRITE;
/*!40000 ALTER TABLE `au_news` DISABLE KEYS */;
INSERT INTO `au_news` VALUES ('1150320447007625216','22444','22',1,'2019-07-14 16:25:41','/news/file/20190714/201907141625411_b928e7022b.htm','/files/image/20190714/201907141625385_fa883b15fd.jpg','2',1),('1150320855708995584','3336789ggg','33',1,'2019-07-14 16:27:19','/news/file/20190714/201907141627185_0ba17fbc30.htm','/files/image/20190714/201907141627160_c304d159c4.jpg','33',2);
/*!40000 ALTER TABLE `au_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_photos`
--

DROP TABLE IF EXISTS `au_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_photos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='相册';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_photos`
--

LOCK TABLES `au_photos` WRITE;
/*!40000 ALTER TABLE `au_photos` DISABLE KEYS */;
INSERT INTO `au_photos` VALUES (1,'专业','专业','2019-04-15 21:10:16',1,'/files/image/20190415/201904152236364_322feeb11e.jpg'),(3,'专业2','专业2','2019-04-16 19:42:30',1,'/files/image/20190416/201904161942244_7baa556852.jpg'),(4,'专业3','333','2019-04-16 19:47:24',1,'/files/image/20190416/201904161947195_f433f3fa92.png'),(5,'专业4','4','2019-04-16 19:52:29',1,'/files/image/20190416/201904161952248_40a91256e7.jpg');
/*!40000 ALTER TABLE `au_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_photos_list`
--

DROP TABLE IF EXISTS `au_photos_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_photos_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `photosId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='相册明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_photos_list`
--

LOCK TABLES `au_photos_list` WRITE;
/*!40000 ALTER TABLE `au_photos_list` DISABLE KEYS */;
INSERT INTO `au_photos_list` VALUES (4,'1','/files/image/20190415/201904152238558_e68ca0d177.jpg',1,1),(5,'2','/files/image/20190415/201904152239065_b886de19bc.png',2,1),(6,'1','/files/image/20190416/201904161942546_c3a1cb36f4.jpg',1,3),(7,'2','/files/image/20190416/201904161943079_d59cbb02c8.jpg',2,3),(8,'1','/files/image/20190416/201904161947435_7cd653f818.jpg',1,4),(9,'2','/files/image/20190416/201904161947571_302900220c.jpg',2,4),(10,'1','/files/image/20190416/201904161952408_5000314227.jpg',1,5);
/*!40000 ALTER TABLE `au_photos_list` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `web_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_modules`
--

DROP TABLE IF EXISTS `web_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_modules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `enTitle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `web_modules_parentId` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='模块信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_modules`
--

LOCK TABLES `web_modules` WRITE;
/*!40000 ALTER TABLE `web_modules` DISABLE KEYS */;
INSERT INTO `web_modules` VALUES (1,'系统设置','',0,1,'',NULL),(2,'角色管理','/web/roles/toPage',1,1,'','roles'),(3,'模块管理','/web/modules/toPage',1,1,'','module'),(4,'账号管理','/web/admin/toPage',1,1,'','admin'),(5,'页面管理','',0,1,'',NULL),(6,'新闻管理','/web/news/toPage',5,1,'','news'),(7,'企业风采','/web/photos/toPage',5,1,'','photos');
/*!40000 ALTER TABLE `web_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_modules_role`
--

DROP TABLE IF EXISTS `web_modules_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_modules_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `enTitle` varchar(45) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT '1',
  `note` varchar(255) DEFAULT NULL,
  `moduleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `modules_role_moduleId` (`moduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='模板权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_modules_role`
--

LOCK TABLES `web_modules_role` WRITE;
/*!40000 ALTER TABLE `web_modules_role` DISABLE KEYS */;
INSERT INTO `web_modules_role` VALUES (1,'新建','new',1,'',2),(2,'修改','mod',1,'',2),(3,'删除','del',1,'',2),(4,'新建','new',1,'',3),(5,'修改','mod',1,'',3),(6,'删除','del',1,'',3),(7,'新建','new',1,'',4),(8,'修改','mod',1,'',4),(9,'删除','del',1,'',4),(10,'新建','new',1,'',6),(11,'修改','mod',1,'',6),(12,'删除','del',1,'',6),(13,'新建','new',1,'',7),(14,'修改','mod',1,'',7),(15,'删除','del',1,'',7),(16,'启用/禁用','set',1,'',3),(17,'模板权限','role',1,'',3);
/*!40000 ALTER TABLE `web_modules_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_roles`
--

DROP TABLE IF EXISTS `web_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnName` varchar(45) DEFAULT NULL,
  `enName` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_roles`
--

LOCK TABLES `web_roles` WRITE;
/*!40000 ALTER TABLE `web_roles` DISABLE KEYS */;
INSERT INTO `web_roles` VALUES (1,'管理员','admin','管理员',1),(2,'用户','user','普通用户',1);
/*!40000 ALTER TABLE `web_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-03 16:00:46
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: autumn2
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
  `id` varchar(20) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `fileurl` varchar(255) DEFAULT NULL,
  `showImg` varchar(255) DEFAULT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sid`),
  KEY `id_index` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='新闻';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_news`
--

LOCK TABLES `au_news` WRITE;
/*!40000 ALTER TABLE `au_news` DISABLE KEYS */;
INSERT INTO `au_news` VALUES ('1150320935077810177','33231212','2323',1,'2019-07-14 16:27:38','/news/file/20190714/201907141627375_03569150c3.htm','/files/image/20190714/201907141627356_3d1466564f.jpg','23',2);
/*!40000 ALTER TABLE `au_news` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-03 16:00:46
