CREATE DATABASE  IF NOT EXISTS `privateschool` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `privateschool`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: privateschool
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `AssignmentID` int NOT NULL,
  `Title` varchar(45) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `SubmissionDateTime` datetime DEFAULT NULL,
  `Oral Mark` int DEFAULT NULL,
  `Total Mark` int DEFAULT NULL,
  PRIMARY KEY (`AssignmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1001,'BankSystem','Design a Bank System that keeps records of Customers, Accounts and Transactions','2020-12-30 23:59:59',20,80),(1002,'FunctionalOven','Create a program that is able to command the function an electronic oven','2020-02-20 23:59:59',20,80),(1003,'NetworkApp','Design a network app to work on data supplied by a network of computers.','2020-12-15 23:59:59',20,80),(1004,'SchoolStructure','Design a private School Structure and print lists of Trainers, Courses and Students','2020-11-18 23:59:59',20,80),(1005,'SupplySystem','Design a supply system for the Greek Army.','2020-03-31 23:59:59',20,80);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attends`
--

DROP TABLE IF EXISTS `attends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attends` (
  `StudentID` int NOT NULL,
  `CourseID` int NOT NULL,
  PRIMARY KEY (`StudentID`,`CourseID`),
  KEY `CourseIDFK3_idx` (`CourseID`),
  CONSTRAINT `CourseIDFK3` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  CONSTRAINT `StudentIDFK3` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attends`
--

LOCK TABLES `attends` WRITE;
/*!40000 ALTER TABLE `attends` DISABLE KEYS */;
INSERT INTO `attends` VALUES (1,101),(2,102),(4,102),(3,103),(1,104),(3,104),(4,105),(5,106);
/*!40000 ALTER TABLE `attends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `belongs`
--

DROP TABLE IF EXISTS `belongs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `belongs` (
  `AssignmentID` int NOT NULL,
  `CourseID` int NOT NULL,
  PRIMARY KEY (`AssignmentID`,`CourseID`),
  KEY `CourseID_idx` (`CourseID`),
  CONSTRAINT `AssignmentIDFK` FOREIGN KEY (`AssignmentID`) REFERENCES `assignment` (`AssignmentID`),
  CONSTRAINT `CourseID` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belongs`
--

LOCK TABLES `belongs` WRITE;
/*!40000 ALTER TABLE `belongs` DISABLE KEYS */;
INSERT INTO `belongs` VALUES (1001,101),(1003,101),(1004,101),(1002,102),(1004,102),(1005,102),(1001,103),(1003,103),(1004,103),(1002,104),(1004,104),(1005,104),(1001,105),(1003,105),(1004,105),(1002,106),(1004,106),(1005,106),(1001,107),(1003,107),(1004,107),(1004,108),(1005,108);
/*!40000 ALTER TABLE `belongs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `CourseID` int NOT NULL,
  `Title` varchar(45) DEFAULT NULL,
  `Stream` int DEFAULT NULL,
  `Type` int DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`CourseID`),
  KEY `Stream_idx` (`Stream`),
  KEY `Type_idx` (`Type`),
  CONSTRAINT `Stream` FOREIGN KEY (`Stream`) REFERENCES `coursestream` (`StreamID`),
  CONSTRAINT `Type` FOREIGN KEY (`Type`) REFERENCES `coursetype` (`TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (101,'CB12',1,10,'2020-10-01','2021-01-01'),(102,'CB12',1,11,'2020-10-01','2021-04-01'),(103,'CB12',2,10,'2020-10-01','2021-01-01'),(104,'CB12',2,11,'2020-10-01','2021-04-01'),(105,'CB12',3,10,'2020-10-01','2021-01-01'),(106,'CB12',3,11,'2020-10-01','2021-04-01'),(107,'CB12',4,10,'2020-10-01','2021-01-01'),(108,'CB12',4,11,'2020-10-01','2021-04-01');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursestream`
--

DROP TABLE IF EXISTS `coursestream`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursestream` (
  `StreamID` int NOT NULL,
  `StreamName` varchar(45) NOT NULL,
  PRIMARY KEY (`StreamID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursestream`
--

LOCK TABLES `coursestream` WRITE;
/*!40000 ALTER TABLE `coursestream` DISABLE KEYS */;
INSERT INTO `coursestream` VALUES (1,'C#'),(2,'Java'),(3,'Javascript'),(4,'Python');
/*!40000 ALTER TABLE `coursestream` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursetype`
--

DROP TABLE IF EXISTS `coursetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursetype` (
  `TypeID` int NOT NULL,
  `TypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursetype`
--

LOCK TABLES `coursetype` WRITE;
/*!40000 ALTER TABLE `coursetype` DISABLE KEYS */;
INSERT INTO `coursetype` VALUES (10,'Full time'),(11,'Part time');
/*!40000 ALTER TABLE `coursetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hastosubmit`
--

DROP TABLE IF EXISTS `hastosubmit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hastosubmit` (
  `AssignmentID` int NOT NULL,
  `CourseId` int NOT NULL,
  `StudentID` int NOT NULL,
  PRIMARY KEY (`AssignmentID`,`CourseId`,`StudentID`),
  KEY `CourseID_idx` (`CourseId`),
  KEY `StudentID_idx` (`StudentID`),
  KEY `CourseID_idx1` (`CourseId`),
  KEY `StudentID_idx1` (`StudentID`),
  CONSTRAINT `AssignmendIDFK` FOREIGN KEY (`AssignmentID`) REFERENCES `assignment` (`AssignmentID`),
  CONSTRAINT `CourseIDFK1` FOREIGN KEY (`CourseId`) REFERENCES `course` (`CourseID`),
  CONSTRAINT `StudentIDFK1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hastosubmit`
--

LOCK TABLES `hastosubmit` WRITE;
/*!40000 ALTER TABLE `hastosubmit` DISABLE KEYS */;
INSERT INTO `hastosubmit` VALUES (1001,101,1),(1003,101,1),(1004,101,1),(1002,102,2),(1004,102,2),(1001,103,4),(1003,103,3),(1004,103,3),(1004,105,4),(1004,106,5),(1005,106,5);
/*!40000 ALTER TABLE `hastosubmit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `StudentID` int NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `TuitionFees` int DEFAULT NULL,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Giorgos','Ioannou','1986-08-22',2500),(2,'Dimitris','Chatzis','1991-10-03',2100),(3,'Giannis','Voulgaris','1996-03-15',2500),(4,'Ntinos','Zoulas','1989-12-20',2500),(5,'Takis','Karamanos','1988-05-04',1800);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaches`
--

DROP TABLE IF EXISTS `teaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teaches` (
  `TrainerID` int NOT NULL,
  `CourseId` int NOT NULL,
  PRIMARY KEY (`CourseId`,`TrainerID`),
  KEY `TrainerIDFK_idx` (`TrainerID`),
  CONSTRAINT `CourseIDFK2` FOREIGN KEY (`CourseId`) REFERENCES `course` (`CourseID`),
  CONSTRAINT `TrainerIDFK` FOREIGN KEY (`TrainerID`) REFERENCES `trainer` (`TrainerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaches`
--

LOCK TABLES `teaches` WRITE;
/*!40000 ALTER TABLE `teaches` DISABLE KEYS */;
INSERT INTO `teaches` VALUES (20,101),(21,102),(22,103),(23,104),(24,105),(25,106),(26,107),(27,108);
/*!40000 ALTER TABLE `teaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer` (
  `TrainerID` int NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TrainerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` VALUES (20,'Kostas','Chatzigeorgiou'),(21,'Giannis','Papadopoulos'),(22,'Giorgos','Ioannidis'),(23,'Aliki','Alamanou'),(24,'Gianna','Frouzi'),(25,'Dimitris','Vasilakos'),(26,'Lampros','Karagiannis'),(27,'Manolis','Siopis');
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-15 12:10:47
