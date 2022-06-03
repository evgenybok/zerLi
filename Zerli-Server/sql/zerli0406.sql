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
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_details`
--

LOCK TABLES `account_details` WRITE;
/*!40000 ALTER TABLE `account_details` DISABLE KEYS */;
INSERT INTO `account_details` VALUES ('0001','4580534842970046','09/2030','259',270,'Active'),('0002','4580817918278719','07/2026','195',190,'Frozen'),('makore','ma','mam','ma',0,'Active');
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
INSERT INTO `complaint` VALUES ('5463','0001','1','LO KEF LI','Handled',20,'2000','2022-03-03 00:00:00'),('5463','0001','3','Shaked lo Gavri','Handled',190,'2000','2022-04-03 00:00:00'),('5463','0002','5','ma kore','Handled',0,'2000','2022-06-05 00:00:00'),('5463','0002','9','lala ','Handled',500,'2004','2023-07-12 00:00:00');
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ID` int DEFAULT NULL,
  `imgSrc` text,
  `Name` text,
  `Price` int DEFAULT NULL,
  `Color` text,
  `Type` text,
  `onSale` int DEFAULT NULL,
  `salePrice` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (10000,'/images/iris.png','Iris',28,'Blue','Self Assembly',1,'25'),(10003,'/images/Passion.png','Passion',35,'Yellow','Self Assembly',1,'25'),(10004,'/images/asterFlower.jpg','Aster',20,'Purple','Self Assembly',0,'0'),(10005,'/images/daisyFlower.png','Daisy',22,'White','Self Assembly',0,NULL),(10006,'/images/irisFlower.jpg','Purple Iris',25,'Purple','Self Assembly',1,'20'),(10007,'/images/lilyFlower.png','Lily',25,'White','Self Assembly',1,'18'),(10008,'/images/pinkLilyflower.jpg','Pink Lily',28,'Pink','Self Assembly',0,NULL),(10009,'/images/yellowTulipFlower.png','Yellow Tulip',16,'Yellow','Self Assembly',0,NULL),(10010,'/images/plumeriaFlower.jpg','Plumeria',22,'Yellow','Self Assembly',0,NULL),(10011,'/images/poppyFlower.png','Poppy',15,'Red','Self Assembly',0,NULL),(10012,'/images/purpleRoseFlower.png','Purple Rose',25,'Purple','Self Assembly',0,NULL),(10013,'/images/redRoseFlower.png','Red Rose',20,'Red','Self Assembly',0,NULL),(10014,'/images/tulipFlower.png','Tulip',15,'Red','Self Assembly',0,NULL),(10015,'/images/whiteRoseFlower.png','White Rose',22,'White','Self Assembly',0,NULL),(10016,'/images/whiteTulipFlower.png','White Tulip',16,'White','Self Assembly',0,NULL),(10017,'/images/zinniaFlower.jpg','Zinnia',26,'Red','Self Assembly',0,NULL),(10018,'/images/greenVase.png','Green Vase',45,'Green','Self Assembly',0,NULL),(10019,'/images/highballGlassVase.jpg','Highball Glass Vase',65,'Glass','Self Assembly',0,NULL),(10020,'/images/redVase.png','Red Vase',50,'Red','Self Assembly',0,NULL),(20001,'/images/mixedRosesBouquet.png','Mixed Roses',300,'Mixed','Premade',0,NULL),(20002,'/images/rosesBouquet.png','Premium Roses',320,'Red','Premade',1,'250'),(20003,'/images/pinkRosesBouquet.png','Pink Roses',280,'Pink','Premade',1,'150'),(20004,'/images/tulipBouquet.png','Red Tulips',150,'Red','Premade',0,NULL),(20005,'/images/whiteRosesBouquet.png','White Roses',280,'White','Premade',0,NULL),(20006,'/images/yellowTulipBouquet.png','Yellow Tulips',170,'Yellow','Premade',0,NULL),(20007,'/images/desertCactus.png','Desert Cactus',58,'Green','Premade',0,'0'),(20008,'/images/cactus.png','Cactus',45,'Green','Premade',1,'39'),(20010,'/images/pinkWeddingBouquet.jpg','Pink Wedding Bouquet',420,'Pink','Premade',0,NULL),(20011,'/images/whiteWeddingBouquet.jpg','White Wedding Bouquet',425,'White','Premade',1,'325'),(22221,'/images/1.jpg','Peach Roses',380,'Pink','Premade',1,'310'),(22222,'/images/2.jpg','Pink Rose',350,'Pink','Premade',1,'290');
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
INSERT INTO `orders` VALUES (1,20,'Happy Birthday','2000','2022-02-12 10:30:00','2022-01-09 00:00:00','Cancelled','Delivery','0001',0,'Nargilos','amit','0506576399','Express'),(2,30,'My Condolences','2001','2022-03-20 08:40:00','2022-01-01 00:00:00','Approved','PickUp','0002',0,'akko','amit','0506576399','Regular'),(3,18,NULL,'2000','2022-02-12 10:30:00','2022-02-03 00:00:00','Compensation','PickUp','0001',0,'akko','amit','0506576399','Regular'),(4,70,NULL,'2003','2022-03-20 08:40:00','2022-02-01 00:00:00','Approved','Delivery','0001',0,'akko','amit','0506576399','Regular'),(5,55,'Happy Birthday','2000','2022-03-22 14:40:00','2022-03-22 00:00:00','Cancelled','Delivery','0002',0,'akko','amit','0506576399','Regular'),(6,162,'Happy Passover','2003','2022-02-28 09:40:00','2022-02-28 00:00:00','Cancelled','PickUp','0001',0,'akko','amit','0506576399','Regular'),(7,96,'B-Day','2005','2022-03-26 15:55:00','2022-03-26 00:00:00','Approved','Delivery','0002',0,'akko','amit','0506576399','Regular'),(8,312,'B-Day','2000','2022-05-25 11:45:00','2022-05-02 10:30:00','Delivered','Delivery','0001',0,'akko','amit','0506576399','Regular'),(9,912,'B-Day','2004','2022-05-25 11:49:00','2022-05-04 10:30:00','Compensation','Delivery','0001',0,'akko','amit','0506576399','Regular'),(10,312,'B-Day','2005','2022-05-26 12:10:00','2022-05-03 10:30:00','Cancel Request','Delivery','0001',0,'kerem','elad','0506576399','Regular'),(11,312,'B-Day','2000','2022-05-26 10:49:00','2022-05-02 23:30:00','Delivered','Delivery','0001',0,'amit','amit','0506576399','Regular'),(12,52,'B-Day','2000','2022-06-02 05:45:00','2022-06-03 20:46:00','Cancelled','Delivery','0001',0,'Bait','Billy','0506576399','Regular'),(13,535,'B-Day','2004','2022-06-02 06:56:00','2022-06-03 21:56:00','Pending','PickUp','0001',0,'akko','ev','0506576399','Express'),(14,262,'B-Day','2004','2022-06-02 09:11:00','2022-06-03 01:00:00','Pending','Delivery','0001',0,'givat ram','ev','0506576399','Regular');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
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
  `IDmanager` varchar(45) NOT NULL,
  PRIMARY KEY (`IDstore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (2000,'North','zerli_Akko','1111'),(2001,'South','zerli_Beer-Sheba','4545'),(2003,'Center','zerli_Tel-Aviv','4545'),(2004,'North','zerli_Karmiel','5678'),(2005,'North','zerli_Maker','4545'),(2006,'South','zerli_Dimona','1234');
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
INSERT INTO `survey_answers` VALUES (1,1,7,8,7,7,5,10),(2,1,9,9,10,8,8,9),(3,1,10,10,10,10,10,10),(4,2,8,8,8,8,8,8),(5,2,6,6,6,6,6,6);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('cu','cu',0,'0001','Nir','Nir','customer','0252621154','nir@gmail.com'),('cu1','cu1',0,'0002','dan','dan','customer','0522151487','dan@gmail.com'),('worker1','worker1',0,'0010','amit','shitrit','worker','0522656444','amit@gmail.com'),('worker2','worker2',0,'0011','shaked','arish','worker','0554488778','shaked@gmail.com'),('mn2','mn2',0,'1111','ivgeni','vaxler','branch manager','0521514485','evgenyvex@gmail.com'),('de','de',0,'2222','itzhak','moshe','Delivery','0522221153','ronny@gmail.com'),('market','market',0,'3333','Billiy','Dilly','Marketing','0558859595','market@gmail.com'),('MN1','MN1',0,'4545','evgeny','vexler','branch manager','0454848455','evgenyBM@gmail.com'),('sp','sp',1,'4643','evgeny','boka','customer specialist','0559448488','evgeny@gmail.com'),('sv','sv',0,'5463','amr','jarrar','customer service','0559484848','amr@gmail.com'),('ceo','ceo',0,'5555','elad','raizing','ceo','0555448478','elad@gmail.com'),('ma','ma',0,'makore','makore','makore','customer','ma','ma');
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

-- Dump completed on 2022-06-03 21:29:49
