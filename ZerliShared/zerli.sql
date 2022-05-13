-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zerli
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
-- Table structure for table `account_details`
--

DROP TABLE IF EXISTS `account_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_details` (
  `User_ID` varchar(256) NOT NULL,
  `CreditCardNumber` varchar(256) NOT NULL,
  `CreditCardDate` varchar(256) NOT NULL,
  `CreditCardCVV` varchar(45) NOT NULL,
  `Refund` varchar(256) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_details`
--

LOCK TABLES `account_details` WRITE;
/*!40000 ALTER TABLE `account_details` DISABLE KEYS */;
INSERT INTO `account_details` VALUES ('0001','4580534842970046','09/2030','259',NULL,'Active'),('0002','4580817918278719','07/2026','195',NULL,'Active');
/*!40000 ALTER TABLE `account_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ID` int NOT NULL,
  `imgSrc` varchar(255) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Price` double NOT NULL,
  `Color` varchar(30) DEFAULT NULL,
  `Type` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (10000,'/images/iris.png','Iris',28,'Red','Self Assembly'),(10001,'/images/Tulip.png','Tulip',54,'Blue','Self Assembly'),(10100,'/images/PinkLotus.png','PinkLouts',150,'Pink','Premade'),(10101,'/images/Passion.png','Passion',100,'Yellow','Premade');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iteminorder`
--

DROP TABLE IF EXISTS `iteminorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iteminorder` (
  `itemNumber` int NOT NULL,
  `orderID` int DEFAULT NULL,
  `itemID` int DEFAULT NULL,
  `amount` int DEFAULT NULL,
  PRIMARY KEY (`itemNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iteminorder`
--

LOCK TABLES `iteminorder` WRITE;
/*!40000 ALTER TABLE `iteminorder` DISABLE KEYS */;
INSERT INTO `iteminorder` VALUES (0,200,10000,1),(1,200,10001,3),(2,1,10100,1),(3,1,10101,5);
/*!40000 ALTER TABLE `iteminorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `Serial` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Price` varchar(45) NOT NULL,
  `Type` varchar(45) NOT NULL,
  `Picture` varchar(255) NOT NULL,
  PRIMARY KEY (`Serial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('1','Rakefet','White','20','Flower',''),('2','Rose','Red','15','Flower',''),('3','Kalanit','Red','13','Flower',''),('4','Israli','Blue','25','Vasa',''),('5','German','Green','25','Vasa',''),('6','Cotton','Pink','3','Tape','');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderNumber` int NOT NULL,
  `Price` double NOT NULL,
  `GreetingCard` varchar(255) DEFAULT NULL,
  `Color` varchar(20) DEFAULT NULL,
  `Dorder` varchar(255) DEFAULT NULL,
  `Shop` varchar(50) NOT NULL,
  `Date` varchar(255) NOT NULL,
  `OrderDate` varchar(255) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `SupplyType` varchar(45) NOT NULL,
  PRIMARY KEY (`OrderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,20,'Happy Birthday','Green','Express Order','North Branch','2022-02-12 10:30:00','2022-01-09 09:12:00','Approved','Delivery'),(4,30,'My Condolences','Green',NULL,'West Branch','2022-03-20 08:40:00','2022-01-01 08:26:00','Approved','PickUp'),(5,18,NULL,'Red',NULL,'North Branch','2022-02-12 10:30:00','2022-02-03 15:30:00','Approved','PickUp'),(7,70,NULL,'Blue','Express Order','West Branch','2022-03-20 08:40:00','2022-02-01 16:30:00','Approved','Delivery'),(200,55,'Happy Birthday','Blue',NULL,'East Branch','2022-03-22 14:40:00','2022-03-22 12:28:00','Approved','Delivery'),(300,162,'Happy Passover','Red','Express Order','South Branch','2022-02-28 09:40:00','2022-02-28 08:20:00','Approved','PickUp'),(600,96,NULL,'Blue',NULL,'South Branch','2022-03-26 15:55:00','2022-03-26 08:30:00','Approved','Delivery');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premade_items`
--

DROP TABLE IF EXISTS `premade_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `premade_items` (
  `Serial` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Price` varchar(45) NOT NULL,
  `Description` varchar(45) NOT NULL,
  `Components` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Picture` varchar(255) NOT NULL,
  PRIMARY KEY (`Serial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premade_items`
--

LOCK TABLES `premade_items` WRITE;
/*!40000 ALTER TABLE `premade_items` DISABLE KEYS */;
INSERT INTO `premade_items` VALUES ('500','Big Red','50','Beautiful big red git','Red roses, Kalanit','Active',''),('501','Valentine','40','Valentine gift','Red roses, White Rakefet','Active',''),('502','Birthday','60','Birthday gift','Red roses with German Vasa','Active','');
/*!40000 ALTER TABLE `premade_items` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('cu2','cu2',0,'0001','Nir','Nir','customer','0252621154','nir@gmail.com'),('cu1','cu1',1,'0002','dan','dan','customer','0522151487','dan@gmail.com'),('worker1','worker1',0,'0010','amit','shitrit','worker','0522656444','amit@gmail.com'),('worker2','worker2',0,'0011','shaked','arish','worker','0554488778','shaked@gmail.com'),('branchMN2','branchMN2',0,'1111','ivgeni','vaxler','branch manager','0521514485','evgenyvex@gmail.com'),('delivery1','delivery1',0,'2222','itzhak','moshe','Delivery','0522221153','ronny@gmail.com'),('branchMN1','branchMN1',0,'4545','evgeny','vexler','branch manager','0454848455','evgenyBM@gmail.com'),('customerSP','customerSP',0,'4643','evgeny','boka','customer specialist','0559448488','evgeny@gmail.com'),('customerSV','customerSV',0,'5463','amr','jarrar','customer service','0559484848','amr@gmail.com'),('ceo','ceo',0,'5555','elad','elgazer','ceo','0555448478','elad@gmail.com');
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

-- Dump completed on 2022-05-13 12:31:25
