-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: zerli
-- ------------------------------------------------------
-- Server version	8.0.29

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
  `TotalRefund` varchar(256) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_details`
--

LOCK TABLES `account_details` WRITE;
/*!40000 ALTER TABLE `account_details` DISABLE KEYS */;
INSERT INTO `account_details` VALUES ('0001','4580534842970046','09/2030','259','480.0','Active'),('0002','4580817918278719','07/2026','195','190.0','Active'),('makore','ma','mam','ma','0','Active');
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
  PRIMARY KEY (`HandelerUserID`,`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES ('5463','0001','10','LO KEF LI','Handled',20),('5463','0002','11','Shaked lo Gavri','Handled',190),('5463','0001','15','ma kore','WaitForHandle',200),('5463','0001','6','ma kore','WaitForHandle',100),('5463','0002','8','ma kore','Handled',0),('5463','0002','9','lala ','WaitForHandle',0);
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
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
INSERT INTO `item` VALUES (10000,'/images/iris.png','Iris',28,'Blue','Self Assembly'),(10003,'/images/Passion.png','Passion',35,'Yellow','Self Assembly'),(10004,'/images/asterFlower.jpg','Aster',20,'Purple','Self Assembly'),(10005,'/images/daisyFlower.png','Daisy',22,'White','Self Assembly'),(10006,'/images/irisFlower.jpg','Purple Iris',25,'Purple','Self Assembly'),(10007,'/images/lilyFlower.png','Lily',25,'White','Self Assembly'),(10008,'/images/pinkLilyflower.jpg','Pink Lily',28,'Pink','Self Assembly'),(10009,'/images/yellowTulipFlower.png','Yellow Tulip',16,'Yellow','Self Assembly'),(10010,'/images/plumeriaFlower.jpg','Plumeria',22,'Yellow','Self Assembly'),(10011,'/images/poppyFlower.png','Poppy',15,'Red','Self Assembly'),(10012,'/images/purpleRoseFlower.png','Purple Rose',25,'Purple','Self Assembly'),(10013,'/images/redRoseFlower.png','Red Rose',20,'Red','Self Assembly'),(10014,'/images/tulipFlower.png','Tulip',15,'Red','Self Assembly'),(10015,'/images/whiteRoseFlower.png','White Rose',22,'White','Self Assembly'),(10016,'/images/whiteTulipFlower.png','White Tulip',16,'White','Self Assembly'),(10017,'/images/zinniaFlower.jpg','Zinnia',26,'Red','Self Assembly'),(10018,'/images/greenVase.png','Green Vase',45,'Green','Self Assembly'),(10019,'/images/highballGlassVase.jpg','Highball Glass Vase',65,'Glass','Self Assembly'),(10020,'/images/redVase.png','Red Vase',50,'Red','Self Assembly'),(20001,'/images/mixedRosesBouquet.png','Mixed Roses',300,'Mixed','Premade'),(20002,'/images/rosesBouquet.png','Premium Roses',320,'Red','Premade'),(20003,'/images/pinkRosesBouquet.png','Pink Roses',280,'Pink','Premade'),(20004,'/images/tulipBouquet.png','Red Tulips',150,'Red','Premade'),(20005,'/images/whiteRosesBouquet.png','White Roses',280,'White','Premade'),(20006,'/images/yellowTulipBouquet.png','Yellow Tulips',170,'Yellow','Premade'),(20007,'/images/desertCactus.png','Desert Cactus',58,'Green','Premade'),(20008,'/images/cactus.png','Cactus',45,'Green','Premade'),(20010,'/images/pinkWeddingBouquet.jpg','Pink Wedding Bouquet',420,'Pink','Premade'),(20011,'/images/whiteWeddingBouquet.jpg','White Wedding Bouquet',425,'White','Premade');
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
  `GreetingCard` varchar(1000) DEFAULT NULL,
  `StoreID` varchar(50) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `SupplyDate` datetime NOT NULL,
  `Status` varchar(45) NOT NULL,
  `SupplyType` varchar(45) NOT NULL,
  `UserID` varchar(45) NOT NULL,
  `Refund` varchar(45) DEFAULT NULL,
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
INSERT INTO `orders` VALUES (1,20,'Happy Birthday','2000','2022-02-12 10:30:00','2022-01-09 00:00:00','Approved','Delivery','0001',NULL,'Nargilos','amit','0506576399',NULL),(2,30,'My Condolences','2001','2022-03-20 08:40:00','2022-01-01 00:00:00','Approved','PickUp','0002',NULL,NULL,NULL,NULL,NULL),(3,18,NULL,'2000','2022-02-12 10:30:00','2022-02-03 00:00:00','Approved','PickUp','0001',NULL,NULL,NULL,NULL,NULL),(4,70,NULL,'2007','2022-03-20 08:40:00','2022-02-01 00:00:00','Approved','Delivery','0001',NULL,NULL,NULL,NULL,NULL),(5,55,'Happy Birthday','2000','2022-03-22 14:40:00','2022-03-22 00:00:00','Approved','Delivery','0002',NULL,NULL,NULL,NULL,NULL),(6,162,'Happy Passover','2003','2022-02-28 09:40:00','2022-02-28 00:00:00','Approved','PickUp','0001',NULL,NULL,NULL,NULL,NULL),(7,96,NULL,'2016','2022-03-26 15:55:00','2022-03-26 00:00:00','Approved','Delivery','0002',NULL,NULL,NULL,NULL,NULL),(8,312.9,'ssss','2000','2022-05-25 11:45:00','2022-05-02 10:30:00','WaitForApprove','Delivery','0001','null','akko','amit','005056045','Regular'),(9,912.9,'ssss','2004','2022-05-25 11:49:00','2022-05-04 10:30:00','WaitForApprove','Delivery','0001','null','akko','amit','0343423','Regular'),(10,312.9,'ma kore','2005','2022-05-26 12:10:00','2022-05-03 10:30:00','WaitForApprove','Delivery','0001','null','kerem','elad','06056050','Regular'),(11,312.9,'null','2000','2022-05-26 10:49:00','2022-05-03 12:30:00','WaitForApprove','Delivery','0001','null','amit','amit','amit','Regular');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premade`
--

DROP TABLE IF EXISTS `premade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `premade` (
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
-- Dumping data for table `premade`
--

LOCK TABLES `premade` WRITE;
/*!40000 ALTER TABLE `premade` DISABLE KEYS */;
/*!40000 ALTER TABLE `premade` ENABLE KEYS */;
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
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `IDstore` int NOT NULL,
  `area` varchar(45) NOT NULL,
  `storeName` varchar(45) NOT NULL,
  `IDmanger` varchar(45) NOT NULL,
  PRIMARY KEY (`IDstore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (2000,'North','zerli_Akko','1111'),(2001,'South','zerli_Beer-Sheba','4545'),(2003,'Center','zerli_Tel-Aviv','4545'),(2004,'North','zerli_Karmiel','1111'),(2005,'North','zerli_Maker','4545'),(2006,'South','zerli_Dimona','1111');
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
INSERT INTO `survey` VALUES (1,'5463','MA kore','ma luz','ma','ma','ma','ma'),(2,'5463','dddd','dddd','aaaa','aaaa','aaaaa','aaaa'),(3,'5463','amit ','shitrit','yagever','ahi ','bolam','toda'),(4,'5463','Elad','Elad','Elad','Elad','Elad','Elad'),(5,'5463','k','k','k','k','k','shakwed');
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
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
INSERT INTO `users` VALUES ('cu2','cu2',0,'0001','Nir','Nir','customer','0252621154','nir@gmail.com'),('cu1','cu1',0,'0002','dan','dan','customer','0522151487','dan@gmail.com'),('worker1','worker1',0,'0010','amit','shitrit','worker','0522656444','amit@gmail.com'),('worker2','worker2',0,'0011','shaked','arish','worker','0554488778','shaked@gmail.com'),('branchMN2','branchMN2',0,'1111','ivgeni','vaxler','branch manager','0521514485','evgenyvex@gmail.com'),('de','de',0,'2222','itzhak','moshe','Delivery','0522221153','ronny@gmail.com'),('MN1','MN1',0,'4545','evgeny','vexler','branch manager','0454848455','evgenyBM@gmail.com'),('customerSP','customerSP',0,'4643','evgeny','boka','customer specialist','0559448488','evgeny@gmail.com'),('sv','sv',1,'5463','amr','jarrar','customer service','0559484848','amr@gmail.com'),('ceo','ceo',0,'5555','elad','raizing','ceo','0555448478','elad@gmail.com'),('ma','ma',0,'makore','makore','makore','customer','ma','ma');
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

-- Dump completed on 2022-05-31 13:00:00
