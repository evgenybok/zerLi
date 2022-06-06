-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: externaldb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `LoggedIn` tinyint NOT NULL,
  `id` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  `PhoneNumber` varchar(45) NOT NULL,
  `Email` varchar(255) NOT NULL,
  PRIMARY KEY (`Username`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('ceo','ceo',0,'6000','elad','elad','ceo','0555448478','ceo@gmail.com'),('cu1','cu1',0,'0001','Nir','Mir','customer','0252621154','nir@gmail.com'),('cu2','cu2',0,'0002','Dan','Ban','customer','0522151487','dan@gmail.com'),('de','de',0,'2222','Lala','Lala','Delivery','0522221153','delivery@gmail.com'),('market','market',0,'3000','Po','Po','Marketing','0558859595','market@gmail.com'),('mn1','mn1',0,'1000','evgeny','vexler','branch manager','0454848455','mn1@gmail.com'),('mn2','mn2',0,'1001','evgeny','boka','branch manager','0521514485','mn2@gmail.com'),('mn3','mn3',0,'1002','amit','shitrit','branch manager','0507812237','mn3@gmail.com'),('mn4','mn4',0,'1003','amr','jarrar','branch manager','0523741829','mn4@gmail.com'),('mn5','mn5',0,'1004','elad','elad','branch manager','0541238967','mn5@gmail.com'),('mn6','mn6',0,'1005','shaked','arish','branch manager','0548912234','mn6@gmail.com'),('sp','sp',0,'4000','Tor','Tor','customer specialist','0559448488','sp@gmail.com'),('sv','sv',0,'5000','Yo','Lo','customer service','0559484848','sv@gmail.com'),('worker1','worker1',0,'2000','Tinki','Winky','worker','0522656444','worker1@gmail.com'),('worker2','worker2',0,'2001','Dipsy','Dipsy','worker','0554488778','worker1@gmail.com'),('yo','yo',0,'0003','yoyo','lolo','customer','0505050505','yoyololo@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-06 14:34:24
