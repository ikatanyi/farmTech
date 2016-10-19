-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: clinic
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Current Database: `clinic`
--

/*!40000 DROP DATABASE IF EXISTS `clinic`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `clinic` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `clinic`;

--
-- Table structure for table `dt_labresults`
--

DROP TABLE IF EXISTS `dt_labresults`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_labresults` (
  `PatientId` varchar(20) CHARACTER SET ascii NOT NULL,
  `TestId` varchar(50) CHARACTER SET ascii NOT NULL,
  `Results` varchar(100) CHARACTER SET ascii NOT NULL,
  `Comments` varchar(10000) CHARACTER SET ascii NOT NULL,
  `Date` varchar(20) CHARACTER SET ascii NOT NULL,
  `LabTechnicianId` varchar(20) CHARACTER SET ascii NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_labresults`
--

LOCK TABLES `dt_labresults` WRITE;
/*!40000 ALTER TABLE `dt_labresults` DISABLE KEYS */;
INSERT INTO `dt_labresults` VALUES ('4','blood tes','positive','pregnant','12/2/2012','kenty'),('241','pregnancy ','negative','3months pregnant','3/2/2012','jefrose'),('4','blood tes','positive','pregnant','12/2/2012','kenty'),('241','pregnancy ','negative','3months pregnant','3/2/2012','jefrose'),('5','blood tes','positive','pregnant','12/2/2012','kenty'),('6','pregnancy ','negative','3months pregnant','3/2/2012','jefrose');
/*!40000 ALTER TABLE `dt_labresults` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_labtests`
--

DROP TABLE IF EXISTS `dt_labtests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_labtests` (
  `PatientId` varchar(20) CHARACTER SET ascii NOT NULL,
  `tests` varchar(1000) CHARACTER SET ascii NOT NULL,
  `Prescriber` varchar(20) CHARACTER SET ascii NOT NULL,
  `Date` varchar(20) CHARACTER SET ascii NOT NULL,
  PRIMARY KEY (`PatientId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_labtests`
--

LOCK TABLES `dt_labtests` WRITE;
/*!40000 ALTER TABLE `dt_labtests` DISABLE KEYS */;
INSERT INTO `dt_labtests` VALUES ('8','[Ljava.lang.Object;@174a6e2','Osore Benedict','23-06-2012: 21:31:51');
/*!40000 ALTER TABLE `dt_labtests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_medicine`
--

DROP TABLE IF EXISTS `dt_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_medicine` (
  `ProductName` varchar(20) CHARACTER SET ascii NOT NULL,
  `ProductCode` varchar(20) CHARACTER SET ascii NOT NULL,
  `Units` varchar(20) CHARACTER SET ascii NOT NULL,
  `BP` varchar(20) CHARACTER SET ascii NOT NULL,
  `SP` varchar(20) CHARACTER SET ascii NOT NULL,
  `SupplierName` varchar(20) CHARACTER SET ascii NOT NULL,
  `SupplierConducts` varchar(20) CHARACTER SET ascii NOT NULL,
  `DocNo` varchar(20) CHARACTER SET ascii NOT NULL,
  `Date` varchar(20) CHARACTER SET ascii NOT NULL,
  PRIMARY KEY (`ProductCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_medicine`
--

LOCK TABLES `dt_medicine` WRITE;
/*!40000 ALTER TABLE `dt_medicine` DISABLE KEYS */;
INSERT INTO `dt_medicine` VALUES ('Amoxyl','001','500g','2000','3000','medRep','16457','LP578','12/30/2012'),('Panadol','002','100g','500','1000','SmithKlineBeecham','201100','LP457','2/12/12'),('Strepsilsl','003','500g','2000','3000','medRep','16457','LP578','12/30/2012'),('Piriton','004','100g','500','1000','SmithKlineBeecham','201100','LP457','2/12/12'),('Actionable','005','300g','2000','3000','medRep','16457','LP578','12/30/2012'),('Glycosis','006','200g','500','1000','SmithKlineBeecham','201100','LP457','2/12/12');
/*!40000 ALTER TABLE `dt_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_patients`
--

DROP TABLE IF EXISTS `dt_patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_patients` (
  `PatientId` varchar(10) NOT NULL,
  `Fname` varchar(20) NOT NULL,
  `Lname` varchar(20) NOT NULL,
  `Sname` varchar(20) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `IdNo` varchar(20) NOT NULL,
  `Address` varchar(20) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Date` varchar(20) NOT NULL,
  PRIMARY KEY (`PatientId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_patients`
--

LOCK TABLES `dt_patients` WRITE;
/*!40000 ALTER TABLE `dt_patients` DISABLE KEYS */;
INSERT INTO `dt_patients` VALUES ('230','willy','shinali','imbenzi','Male','56920','16332','0723456790','12/30/2012'),('241','marryann','kimani','wanjiru','female','126543','20100','072845600','2/12/12'),('3','kentra','nolan','jack','Female','12345','16332','072415544','15-28-12'),('4','Janepher','maraka','imbenzi','Female','678889','16722','07231234','15-24-12'),('5','Millicent','muse','imbenzi','Female','3456','23600','07245778','16-48-12'),('6','justus ','imbwaga','sechero','Male','2345465','21345','2345','16-31-12'),('7','miller','imbenzi','diwoma','Male','232414324','123123','02777888','17-09-12'),('8','willy','shinali','imbenzi','Female','56920','56778','0723456790','20-31-12'),('9','kobi','bryant','miller','Female','234567','9876567','75409','21-05-2012');
/*!40000 ALTER TABLE `dt_patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_prescription`
--

DROP TABLE IF EXISTS `dt_prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_prescription` (
  `PatientId` varchar(20) CHARACTER SET ascii NOT NULL,
  `Prescription` varchar(1000) CHARACTER SET ascii NOT NULL,
  `Attendant` varchar(50) CHARACTER SET ascii NOT NULL,
  `Date` varchar(20) CHARACTER SET ascii NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_prescription`
--

LOCK TABLES `dt_prescription` WRITE;
/*!40000 ALTER TABLE `dt_prescription` DISABLE KEYS */;
INSERT INTO `dt_prescription` VALUES ('8','[Ljava.lang.Object;@1b5c22f','n/a','23-06-2012: 02:20:23'),('8','[Ljava.lang.Object;@11b86c7','n/a','23-06-2012: 02:26:37'),('8','[Ljava.lang.Object;@13c550f','OsoreBenedict','23-06-2012: 02:30:53'),('4','[Ljava.lang.Object;@b2fb1e','OsoreBenedict','23-06-2012: 02:32:32'),('5','[Ljava.lang.Object;@19c5048','OsoreBenedict','23-06-2012: 02:33:32');
/*!40000 ALTER TABLE `dt_prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_psession`
--

DROP TABLE IF EXISTS `dt_psession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_psession` (
  `PatientId` varchar(20) NOT NULL,
  `Attendant` varchar(20) NOT NULL,
  `Last_Appointment` varchar(20) NOT NULL,
  `Next_Appointment` varchar(20) NOT NULL,
  `Diagnosis` varchar(1000) NOT NULL,
  `Comments` varchar(2000) CHARACTER SET ascii NOT NULL,
  `insured` varchar(20) NOT NULL,
  `insuranceId` mediumtext NOT NULL,
  `InsuranceFirm` varchar(50) NOT NULL,
  `Amount` varchar(20) NOT NULL,
  `AttendedOn` varchar(20) CHARACTER SET ascii NOT NULL,
  PRIMARY KEY (`Last_Appointment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_psession`
--

LOCK TABLES `dt_psession` WRITE;
/*!40000 ALTER TABLE `dt_psession` DISABLE KEYS */;
INSERT INTO `dt_psession` VALUES ('8','Osore Benedict','27-06-2012: 15:11:33','28-06-2012','scurvy','sickly looking with scratchy skin','No','n/a','n/a','','27-06-2012: 15:11:33');
/*!40000 ALTER TABLE `dt_psession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_schedule`
--

DROP TABLE IF EXISTS `dt_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_schedule` (
  `Id` varchar(4) NOT NULL,
  `PatientId` varchar(20) NOT NULL,
  `State` varchar(10) CHARACTER SET ascii NOT NULL,
  `Date` varchar(20) NOT NULL,
  PRIMARY KEY (`PatientId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=REDUNDANT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_schedule`
--

LOCK TABLES `dt_schedule` WRITE;
/*!40000 ALTER TABLE `dt_schedule` DISABLE KEYS */;
INSERT INTO `dt_schedule` VALUES ('2','3','Unseen','16-06-2012: 00:00:00'),('1','7','Unseen','16-06-2012: 00:00:00'),('0','8','seen','27-06-2012: 13:14:00');
/*!40000 ALTER TABLE `dt_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_tests`
--

DROP TABLE IF EXISTS `dt_tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_tests` (
  `TestName` varchar(20) CHARACTER SET ascii NOT NULL,
  `labTech` varchar(20) CHARACTER SET ascii NOT NULL,
  `Date` varchar(20) CHARACTER SET ascii NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_tests`
--

LOCK TABLES `dt_tests` WRITE;
/*!40000 ALTER TABLE `dt_tests` DISABLE KEYS */;
INSERT INTO `dt_tests` VALUES ('Blood test','okonji','12/2/03'),('Pregnancy Test','Osore','03/12/2012');
/*!40000 ALTER TABLE `dt_tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_userprivileges`
--

DROP TABLE IF EXISTS `dt_userprivileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_userprivileges` (
  `UserId` varchar(20) CHARACTER SET ascii NOT NULL,
  `UL1` varchar(20) CHARACTER SET ascii NOT NULL,
  `UL2` varchar(20) CHARACTER SET ascii NOT NULL,
  `UL3` varchar(20) CHARACTER SET ascii NOT NULL,
  `UL4` varchar(20) CHARACTER SET ascii NOT NULL,
  `UL5` varchar(20) CHARACTER SET ascii NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_userprivileges`
--

LOCK TABLES `dt_userprivileges` WRITE;
/*!40000 ALTER TABLE `dt_userprivileges` DISABLE KEYS */;
INSERT INTO `dt_userprivileges` VALUES ('2342134','GRANTED','GRANTED','GRANTED','GRANTED','GRANTED');
/*!40000 ALTER TABLE `dt_userprivileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dt_users`
--

DROP TABLE IF EXISTS `dt_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dt_users` (
  `Fname` varchar(20) NOT NULL,
  `Sname` varchar(20) NOT NULL,
  `Lname` varchar(50) CHARACTER SET ascii NOT NULL,
  `IdNo` varchar(20) NOT NULL,
  `Address` varchar(20) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Residence` varchar(50) CHARACTER SET ascii NOT NULL,
  `Age` varchar(12) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Date` varchar(20) CHARACTER SET ascii NOT NULL,
  PRIMARY KEY (`IdNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dt_users`
--

LOCK TABLES `dt_users` WRITE;
/*!40000 ALTER TABLE `dt_users` DISABLE KEYS */;
INSERT INTO `dt_users` VALUES ('willy','shinali','imbenzi','2342134','16332','0724555566','freehold','25','willy','willy','29-06-2012: 13:40:58'),('kennedy','ikatanyi','','25985679','16332','0724135544','','0','kent','kent',''),('Osore','Benedict','','534635','4674370','0723566899','','0','osore','osore','');
/*!40000 ALTER TABLE `dt_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-01 17:47:12
