-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: zerli
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
  `ExpiryDate` varchar(256) NOT NULL,
  `CVV` varchar(45) NOT NULL,
  `TotalRefund` double NOT NULL DEFAULT '0',
  `Status` varchar(45) NOT NULL,
  `OrdersAmount` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_details`
--

LOCK TABLES `account_details` WRITE;
/*!40000 ALTER TABLE `account_details` DISABLE KEYS */;
INSERT INTO `account_details` VALUES ('0001','4580534842970046','09/2030','259',0,'Active',4),('0002','4580817918278719','07/2026','195',271,'Active',0),('0003','4444123444441234','02/2023','233',0,'Active',0);
/*!40000 ALTER TABLE `account_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `HandelerUserID` varchar(45) NOT NULL,
  `complainUserID` varchar(45) NOT NULL,
  `OrderId` varchar(45) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `complainStatus` varchar(45) NOT NULL,
  `Refund` double NOT NULL,
  `sotreID` varchar(45) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES ('5000','0001','1','Bad service','Handled',19,'2000','2022-06-04 00:00:00'),('5000','0001','3','Flowers arrived messed up','WaitForHandle',0,'2000','2022-06-03 00:00:00'),('5000','0002','5','Bad service','Handled',26,'2000','2022-06-04 01:00:00'),('5000','0002','9','Bad service','WaitForHandle',0,'2004','2022-06-04 00:00:00');
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaintreport`
--

DROP TABLE IF EXISTS `complaintreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaintreport` (
  `StoreID` varchar(45) NOT NULL,
  `QuarterNumber` varchar(45) NOT NULL,
  `Year` varchar(45) NOT NULL,
  `GotRefund` varchar(45) DEFAULT NULL,
  `DidntGotRefund` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`StoreID`,`QuarterNumber`,`Year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaintreport`
--

LOCK TABLES `complaintreport` WRITE;
/*!40000 ALTER TABLE `complaintreport` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaintreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `OrderID` int NOT NULL,
  `HandelerId` int DEFAULT NULL,
  `CustomerSupplyDate` varchar(45) DEFAULT NULL,
  `DerliverySupplyDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1,2222,'2022-06-02 11:30:00','2022-06-03 06:40:33'),(4,2222,'2022-02-01 21:40:00','2022-06-03 11:05:14'),(8,2222,'2022-05-02 10:30:00','2022-06-03 10:55:24');
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incomereport`
--

DROP TABLE IF EXISTS `incomereport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incomereport` (
  `Week1` varchar(255) DEFAULT NULL,
  `Week2` varchar(45) DEFAULT NULL,
  `Week3` varchar(45) DEFAULT NULL,
  `Week4` varchar(45) DEFAULT NULL,
  `Month` varchar(45) NOT NULL,
  `Year` varchar(45) NOT NULL,
  `StoreID` varchar(45) NOT NULL,
  PRIMARY KEY (`StoreID`,`Year`,`Month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incomereport`
--

LOCK TABLES `incomereport` WRITE;
/*!40000 ALTER TABLE `incomereport` DISABLE KEYS */;
INSERT INTO `incomereport` VALUES ('430.0','1500.0','750.0','960.0','01','2022','2000'),('498.0','1500.0','500.0','960.0','02','2022','2000'),('980.0','250.0','1230.0','1610.0','03','2022','2000'),('780.0','250.0','1230.0','1310.0','04','2022','2000'),('780.0','250.0','1230.0','1922.0','05','2022','2000'),('1780.0','0','0','0','06','2022','2000');
/*!40000 ALTER TABLE `incomereport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ID` int NOT NULL,
  `imgSrc` text,
  `Name` text,
  `Price` int DEFAULT NULL,
  `Color` text,
  `Type` text,
  `onSale` int DEFAULT NULL,
  `salePrice` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (10000,'/images/iris.png','Iris',28,'Blue','Self Assembly',1,'25'),(10003,'/images/Passion.png','Passion',35,'Yellow','Self Assembly',1,'25'),(10004,'/images/asterFlower.jpg','Aster',22,'Purple','Self Assembly',0,'0.0'),(10005,'/images/daisyFlower.png','Daisy',22,'White','Self Assembly',0,NULL),(10006,'/images/irisFlower.jpg','Purple Iris',25,'Purple','Self Assembly',0,'0.0'),(10007,'/images/lilyFlower.png','Lily',25,'White','Self Assembly',1,'18'),(10008,'/images/pinkLilyflower.jpg','Pink Lily',28,'Pink','Self Assembly',0,NULL),(10009,'/images/yellowTulipFlower.png','Yellow Tulip',16,'Yellow','Self Assembly',0,NULL),(10010,'/images/plumeriaFlower.jpg','Plumeria',22,'Yellow','Self Assembly',0,NULL),(10011,'/images/poppyFlower.png','Poppy',15,'Red','Self Assembly',0,NULL),(10012,'/images/purpleRoseFlower.png','Purple Rose',25,'Purple','Self Assembly',0,NULL),(10013,'/images/redRoseFlower.png','Red Rose',20,'Red','Self Assembly',0,NULL),(10014,'/images/tulipFlower.png','Tulip',15,'Red','Self Assembly',0,NULL),(10015,'/images/whiteRoseFlower.png','White Rose',22,'White','Self Assembly',0,NULL),(10016,'/images/whiteTulipFlower.png','White Tulip',16,'White','Self Assembly',0,NULL),(10017,'/images/zinniaFlower.jpg','Zinnia',26,'Red','Self Assembly',0,NULL),(10018,'/images/greenVase.png','Green Vase',45,'Green','Self Assembly',0,NULL),(10019,'/images/highballGlassVase.jpg','Highball Glass Vase',65,'Glass','Self Assembly',1,'55.0'),(10020,'/images/redVase.png','Red Vase',50,'Red','Self Assembly',0,NULL),(20001,'/images/mixedRosesBouquet.png','Mixed Roses',300,'Mixed','Premade',0,NULL),(20002,'/images/rosesBouquet.png','Premium Roses',320,'Red','Premade',1,'250'),(20003,'/images/pinkRosesBouquet.png','Pink Roses',280,'Pink','Premade',1,'150'),(20004,'/images/tulipBouquet.png','Red Tulips',150,'Red','Premade',0,NULL),(20005,'/images/whiteRosesBouquet.png','White Roses',280,'White','Premade',0,NULL),(20006,'/images/yellowTulipBouquet.png','Yellow Tulips',170,'Yellow','Premade',0,NULL),(20007,'/images/desertCactus.png','Desert Cactus',65,'Green','Premade',0,'0.0'),(20008,'/images/cactus.png','Cactus',45,'Green','Premade',0,'0.0'),(20010,'/images/pinkWeddingBouquet.jpg','Pink Wedding Bouquet',420,'Pink','Premade',1,'400.0'),(20011,'/images/whiteWeddingBouquet.jpg','White Wedding Bouquet',425,'White','Premade',1,'325'),(22221,'/images/1.jpg','Peach Roses',380,'Pink','Premade',0,'0.0'),(22222,'/images/2.jpg','Pink Rose',350,'Pink','Premade',1,'250.0');
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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderNumber` int NOT NULL,
  `Price` double NOT NULL,
  `GreetingCard` varchar(1000) DEFAULT NULL,
  `StoreID` varchar(50) NOT NULL,
  `OrderDate` varchar(50) NOT NULL,
  `SupplyDate` varchar(50) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `SupplyType` varchar(45) NOT NULL,
  `UserID` varchar(45) NOT NULL,
  `Refund` double NOT NULL DEFAULT '0',
  `SupplyAdress` varchar(45) DEFAULT NULL,
  `RecieverName` varchar(45) DEFAULT NULL,
  `RecieverPhone` varchar(10) DEFAULT NULL,
  `Dorder` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,20,'Happy Birthday','2000','2022-02-12 10:30:00','2022-01-09 00:00:00','Cancelled','Delivery','0001',0,'Nargilos','amit','0506576399','Express'),(2,30,'My Condolences','2001','2022-03-20 08:40:00','2022-01-01 00:00:00','Approved','PickUp','0002',0,'akko','amit','0506576399','Regular'),(3,18,NULL,'2000','2022-02-12 10:30:00','2022-02-03 00:00:00','Compensation','PickUp','0001',0,'akko','amit','0506576399','Regular'),(4,70,NULL,'2003','2022-03-20 08:40:00','2022-02-01 00:00:00','Delivered','Delivery','0001',0,'akko','amit','0506576399','Regular'),(5,55,'Happy Birthday','2000','2022-03-22 14:40:00','2022-03-22 00:00:00','Delivered','Delivery','0002',0,'akko','amit','0506576399','Regular'),(6,162,'Happy Passover','2003','2022-02-28 09:40:00','2022-02-28 00:00:00','Pending','PickUp','0001',0,'akko','amit','0506576399','Regular'),(7,96,'B-Day','2005','2022-03-26 15:55:00','2022-03-26 00:00:00','Cancelled','Delivery','0002',0,'akko','amit','0506576399','Regular'),(8,312,'B-Day','2000','2022-05-25 11:45:00','2022-05-02 10:30:00','Delivered','Delivery','0001',0,'akko','amit','0506576399','Regular'),(9,912,'B-Day','2004','2022-05-25 11:49:00','2022-05-04 10:30:00','Compensation','Delivery','0002',0,'akko','amit','0506576399','Regular'),(10,312,'B-Day','2005','2022-05-26 12:10:00','2022-05-03 10:30:00','Cancel Request','Delivery','0001',0,'kerem','elad','0506576399','Regular'),(11,312,'B-Day','2000','2022-05-26 10:49:00','2022-05-02 23:30:00','Delivered','Delivery','0001',0,'amit','amit','0506576399','Regular'),(12,52,'B-Day','2000','2022-06-02 05:45:00','2022-06-03 20:46:00','Delivered','Delivery','0001',0,'Bait','Billy','0506576399','Regular'),(13,535,'B-Day','2004','2022-06-02 06:56:00','2022-06-03 21:56:00','Pending','PickUp','0001',0,'akko','ev','0506576399','Express'),(14,262,'B-Day','2004','2022-06-02 09:11:00','2022-06-03 01:00:00','Pending','Delivery','0001',0,'givat ram','ev','0506576399','Regular'),(15,412,'Happy Birthday!!','2003','2022-06-05 12:55:00','2022-06-05 15:55:00','Pending','PickUp','0001',0,'null','Evgeny Bo','0500500505','Express'),(16,262,'null','2000','2022-06-05 01:06:00','2022-06-05 16:06:00','Pending','PickUp','0001',0,'null','ev','0505050520','Express'),(17,57,'null','2000','2022-06-05 01:07:00','2022-06-05 20:07:00','Cancelled','PickUp','0001',57,'null','ev','0505050505','Express'),(18,557,'null','2003','2022-06-05 01:14:00','2022-06-05 16:14:00','Pending','PickUp','0001',0,'null','Ev','0505050505','Express'),(19,57,'null','2003','2022-06-05 01:26:00','2022-06-05 16:26:00','Pending','PickUp','0001',0,'null','ev','0505050505','Express'),(20,57,'12','2000','2022-06-05 01:33:00','2022-06-05 16:33:00','Approved','PickUp','0001',28.5,'null','ev','0505050505','Express'),(21,262,'null','2000','2022-06-05 01:57:00','2022-06-05 16:57:00','Approved','PickUp','0001',0,'null','ev','0505050505','Express'),(22,200,'null','2000','2022-06-05 04:31:00','2022-06-05 19:31:00','Pending','PickUp','0001',0,'null','Ev','0540450450','Regular'),(23,96,'null','2003','2022-06-05 04:51:00','2022-06-16 11:50:00','Pending','PickUp','0001',0,'null','evev','0505050505','Regular'),(24,116,'null','2000','2022-06-05 05:25:00','2022-06-05 20:24:00','Pending','PickUp','0001',0,'null','ev','0505050505','Express'),(25,262,'null','2000','2022-06-05 07:59:00','2022-06-05 22:58:00','Pending','PickUp','0001',0,'null','ev','0505050505','Express'),(26,512,'null','2000','2022-06-05 07:59:00','2022-06-24 10:11:00','Delivered','Delivery','0001',0,'lo','lo','2200220202','Regular'),(27,392,'null','null','2022-06-05 10:52:00','2022-06-06 01:52:00','Pending','PickUp','0001',0,'null','ev','0505050505','Express');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `IDstore` varchar(45) NOT NULL,
  `area` varchar(45) NOT NULL,
  `storeName` varchar(45) NOT NULL,
  `IDmanager` varchar(45) NOT NULL,
  PRIMARY KEY (`IDstore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES ('2000','North','zerli_Akko','1000'),('2001','South','zerli_Beer-Sheba','1001'),('2002','Center','zerli_Tel-Aviv','1002'),('2003','North','zerli_Karmiel','1003'),('2004','North','zerli_Maker','1004'),('2005','South','zerli_Dimona','1005');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `SurveyNumber` int NOT NULL,
  `SurveyCreatetorID` varchar(45) NOT NULL,
  `Q1` varchar(200) NOT NULL,
  `Q2` varchar(200) NOT NULL,
  `Q3` varchar(200) DEFAULT NULL,
  `Q4` varchar(200) NOT NULL,
  `Q5` varchar(200) NOT NULL,
  `Q6` varchar(200) NOT NULL,
  PRIMARY KEY (`SurveyNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'5463','Did you find the stores conveniently located?','Did you find the store hours appropriate for you shopping needs?','Were the stores atmosphere and decoration appealing?','Was there a good selection of products?','Was the merchendise sold of high quality?','Were you satisfied with your purchase?');
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_answers`
--

DROP TABLE IF EXISTS `survey_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_answers` (
  `ID` int NOT NULL,
  `SurveyNumber` int DEFAULT NULL,
  `Q1` int DEFAULT NULL,
  `Q2` int DEFAULT NULL,
  `Q3` int DEFAULT NULL,
  `Q4` int DEFAULT NULL,
  `Q5` int DEFAULT NULL,
  `Q6` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_answers`
--

LOCK TABLES `survey_answers` WRITE;
/*!40000 ALTER TABLE `survey_answers` DISABLE KEYS */;
INSERT INTO `survey_answers` VALUES (1,1,7,8,7,7,5,10),(2,1,9,9,10,8,8,9),(3,1,10,10,10,10,10,10),(4,2,8,8,8,8,8,8),(5,2,6,6,6,6,6,6),(6,1,9,8,7,6,5,4);
/*!40000 ALTER TABLE `survey_answers` ENABLE KEYS */;
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
  PRIMARY KEY (`id`,`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('cu1','cu1',0,'0001','Nir','Mir','customer','0252621154','nir@gmail.com'),('cu2','cu2',0,'0002','Dan','Ban','customer','0522151487','dan@gmail.com'),('yo','yo',0,'0003','yoyo','lolo','customer','0505050505','yoyololo@gmail.com'),('mn1','mn1',0,'1000','evgeny','vexler','branch manager','0454848455','mn1@gmail.com'),('mn2','mn2',0,'1001','evgeny','boka','branch manager','0521514485','mn2@gmail.com'),('mn3','mn3',0,'1002','amit','shitrit','branch manager','0507812237','mn3@gmail.com'),('mn4','mn4',0,'1003','amr','jarrar','branch manager','0523741829','mn4@gmail.com'),('mn5','mn5',0,'1004','elad','elad','branch manager','0541238967','mn5@gmail.com'),('mn6','mn6',0,'1005','shaked','arish','branch manager','0548912234','mn6@gmail.com'),('worker1','worker1',0,'2000','Tinki','Winky','worker','0522656444','worker1@gmail.com'),('worker2','worker2',0,'2001','Dipsy','Dipsy','worker','0554488778','worker1@gmail.com'),('de','de',0,'2222','Lala','Lala','Delivery','0522221153','delivery@gmail.com'),('market','market',0,'3000','Po','Po','Marketing','0558859595','market@gmail.com'),('sp','sp',0,'4000','Tor','Tor','customer specialist','0559448488','sp@gmail.com'),('sv','sv',0,'5000','Yo','Lo','customer service','0559484848','sv@gmail.com'),('ceo','ceo',0,'6000','elad','elad','ceo','0555448478','ceo@gmail.com');
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

-- Dump completed on 2022-06-06 14:35:04
