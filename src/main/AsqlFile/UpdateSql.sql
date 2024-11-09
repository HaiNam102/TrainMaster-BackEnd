-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: train_master
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `train_master`
--

/*!40000 DROP DATABASE IF EXISTS `train_master`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `train_master` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `train_master`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
                           `account_id` int NOT NULL AUTO_INCREMENT,
                           `username` varchar(50) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           `enabled` tinyint(1) DEFAULT NULL,
                           `role_id` int DEFAULT NULL,
                           PRIMARY KEY (`account_id`),
                           UNIQUE KEY `username` (`username`),
                           KEY `role_id` (`role_id`),
                           CONSTRAINT `account_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'owner_johndoe','$2a$12$DYNNQ7QJQ.0/DFvPtpmA8urUi.3sXK.au3wowI7w1ozVYBxkRrdeO',1,1),(2,'fm_janesmith','$2a$12$bxmagT1EnmF0jiFemJY/5edtf2EEP28zEYz3x7GJ4.nEosTb3nApq',1,2),(3,'fm_mikejohnson','$2a$12$BbRNZCw5kfGvVavBvahnou/Z4IwzMGRL0z/SBmf4TPrZ9u6qkm2O.',1,2),(4,'pt_alexgreen','$2a$12$8gQJo91wDlQlbqXu68.yD.az4FGwu5aFgJJ.BC0GWsODQbWvNvPH2',1,3),(5,'pt_lisabrown','$2a$12$YImuPT5SfRGzZWiXHw6UCOiTReBSoUCVogRXGUH/54Hm7zjGcTr0W',1,3),(6,'pt_tomwilson','$2a$12$/YxVfgqHC4ChaJhPd6Ae0O6g9DaEBI/1gaTBMz7q2BXlWA5pV26f6',1,3),(7,'pt_sarahtaylor','$2a$12$5enBmYFv.2U5iNOzhMERQObUzrVary/8vR9t.SUAfEB3cLbCI/kR6',1,3),(8,'pt_kevinanderson','$2a$12$70mXxLospBxG33MBxEWyOOQBwOvK860AMfhkrtX3O8UJgWhJt.KE6',1,3),(9,'pt_emmalee','$2a$12$b7Vai3jTUXQYhtXclx04be/G7EGgKTDKTO.BTW3rGVCIVe2bPdKbu',1,3),(10,'pt_danielhall','$2a$12$.IPVQroplk4D7nY90QTMM.voJwCcxRw6VVyuzJWa1FOtVl2KHW6aG',1,3),(11,'pt_sophiamoore','$2a$12$kHX.UGYN9aoZX5lGlZ5F5ehJXfAFAIndWweFPqrBdJHeb57oohLfu',1,3),(12,'pt_ryanmartin','$2a$12$PzjLT1xBLMQuprDkxIEkV.bU7h.c0SEsEhNV0wfOaLRpCX/mQQ616',1,3),(13,'pt_oliviascott','$2a$12$tpLZswSNoSaMHRZjlKa8CO2A59Bk.vlMSrh/apbMQOaB9Cavs7Fla',1,3),(14,'client_client1','$2a$12$3IspGTfZK/7F4NwsJUDfO.jEp4Eozk1y3Q3VThgVWX4n0v7nDXuca',1,4),(15,'client_client2','$2a$12$b8jk945ixrvvwyvpcDDtbOM84GGOimgossmUmu4085VraFA33lRTS',1,4),(16,'client_client3','$2a$12$d70KYSJwssQIev9xJWxX/OqkDcRGuUnsS2bNVpHXHJ4Us0RuhlPIe',0,4),(17,'client_client4','$2a$12$PDBPn8N7yg31Li0Kl4Xpee7uWpdH9RB1kzfqpVyE71idEJl43afzu',1,4),(18,'client_client5','$2a$12$ixukeuYdwf/kmJmy6HyJg.yig12FWushe7QjzitbmtON0P.e2xuMK',1,4),(19,'client_client6','$2a$12$5edUQdBrVMv8m.MGYZrGKehUZRkRAP1/3GlCJWOLuI6i6DmAhS9Em',0,4),(20,'client_client7','$2a$12$gEzqN.GQLxrgeSyS5W12.el.x6bBQEr8isXB7SK6qMODQTMwp7jl6',1,4),(21,'client_client8','$2a$12$LBEhEqls07sUtETRP/xO2.vVklyNILO4QSm4FwpSZdVWaP3iaEnFG',1,4),(22,'client_client9','$2a$12$i3hFe8Kw5a1ghHGgW4vjkOlC9eoR1kkHXt9DYxBd4Ij2CNnYUdNPC',1,4),(23,'client_client10','$2a$12$uFsop/XBAU/R.cRJcUZR0eudGtdw1X1PBFuV1ER/n7C5CzLUI9pzq',1,4),(24,'client_client11','$2a$12$KGARV9Bzg1Mft0vZt4TCpOLcs8eP.orenTALiXZtJM6SuQgwcnjUS',1,4),(25,'client_client12','$2a$12$ugR4Tc84aIDbieNUxxs5OeOblLV6TzyH95QSysIdwID4sEJzVXGtK',1,4),(26,'client_client13','$2a$12$1P07fZb/Mfe6G068Jm0Y3.XEg.3gWwuWt481xYcl/Uc1hRsgcLzHu',1,4),(27,'client_client14','$2a$12$85LFaG3f/qq7W3aFKsVEUeNvk3UWREPCwqLc3sqllIGgcybCXWBUa',1,4),(28,'client_client15','$2a$12$EpwueaNjnRKv82CwH2ypQujN/ok14f.8kahROZ3rz4jr1uFcctia.',1,4),(29,'client_client16','$2a$12$YdhYwUcHQJ3xkvmrSGjBGuGh1H006udYAQ/x5XdzFg0MmLxoOUI1S',1,4),(30,'client_client17','$2a$12$hNNG9rW5U6E6OPTm4nGaG.XY0dk28ySBQo0tOlfF09SGgIDAnNPBW',1,4),(31,'client_client18','$2a$12$OXybi.Ud70ElU5gca8yxC.EuzdfXodysuCYvYomWCjtIk8ckI2RTG',1,4),(32,'client_client19','$2a$12$gB19SUw5aEVVlX/n1aPGHeAcbsrThqRBWZy0a0lrNpVTFLkuvsmvK',1,4),(33,'client_client20','$2a$12$mBpJsJrhXeOFUe8QR4NvGuRKLMsKUYIwiDjFuLMlMX34spbW45FUm',1,4);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
                            `calendar_id` int NOT NULL AUTO_INCREMENT,
                            `date` date NOT NULL,
                            `timestart` time DEFAULT NULL,
                            `timeend` time DEFAULT NULL,
                            `attendance_status` tinyint(1) DEFAULT NULL,
                            `client_id` int DEFAULT NULL,
                            PRIMARY KEY (`calendar_id`),
                            KEY `client_id` (`client_id`),
                            CONSTRAINT `calendar_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES (1,'2024-10-01','08:00:00','09:00:00',1,14),(2,'2024-10-02','09:00:00','10:00:00',1,15),(3,'2024-10-03','10:00:00','11:00:00',0,16),(4,'2024-10-04','11:00:00','12:00:00',1,17),(5,'2024-10-05','12:00:00','13:00:00',1,18),(6,'2024-10-06','13:00:00','14:00:00',0,19),(7,'2024-10-07','14:00:00','15:00:00',1,20),(8,'2024-10-08','15:00:00','16:00:00',1,1),(9,'2024-10-09','16:00:00','17:00:00',1,2),(10,'2024-10-10','17:00:00','18:00:00',0,3);
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `decrease_remainSession` AFTER INSERT ON `calendar` FOR EACH ROW BEGIN
    UPDATE Training_Package_of_Clients
    SET remainSession = remainSession - 1
    WHERE Client_ID = NEW.Client_ID;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `increase_remainSession` AFTER DELETE ON `calendar` FOR EACH ROW BEGIN
    UPDATE Training_Package_of_Clients
    SET remainSession = remainSession + 1
    WHERE Client_ID = OLD.Client_ID;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
                          `client_id` int NOT NULL AUTO_INCREMENT,
                          `pt_id` int DEFAULT NULL,
                          `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                          `last_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                          `gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `birth_date` date DEFAULT NULL,
                          `phone` varchar(15) DEFAULT NULL,
                          `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `email` varchar(100) DEFAULT NULL,
                          `years_training` int DEFAULT NULL,
                          `blood_glucose` float DEFAULT NULL,
                          `blood_pressure` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `account_id` int DEFAULT NULL,
                          `job` varchar(100) DEFAULT NULL,
                          PRIMARY KEY (`client_id`),
                          UNIQUE KEY `email` (`email`),
                          KEY `pt_id` (`pt_id`),
                          KEY `account_id` (`account_id`),
                          CONSTRAINT `client_ibfk_1` FOREIGN KEY (`pt_id`) REFERENCES `personal_trainer` (`pt_id`),
                          CONSTRAINT `client_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,1,'Client1','One','Male','2000-01-01','111222333','111 Maple St, HCMC, Vietnam','client1@example.com',1,90,'120/80',14,'MK'),(2,1,'Client2','Two','Female','1995-02-02','222333444','112 Maple St, HCMC, Vietnam','client2@example.com',2,95,'115/75',15,'IT'),(3,1,'Client3','Three','Male','1992-03-03','333444555','113 Maple St, HCMC, Vietnam','client3@example.com',3,85,'130/85',16,'PM'),(4,1,'Client4','Four','Female','1988-04-04','444555666','114 Maple St, HCMC, Vietnam','client4@example.com',1,100,'140/90',17,'PA'),(5,1,'Client5','Five','Male','1993-05-05','555666777','115 Maple St, HCMC, Vietnam','client5@example.com',4,75,'120/80',18,'Ki su'),(6,1,'Client6','Six','Female','1991-06-06','666777888','116 Maple St, HCMC, Vietnam','client6@example.com',2,92,'110/70',19,'design'),(7,1,'Client7','Seven','Male','1994-07-07','777888999','117 Maple St, HCMC, Vietnam','client7@example.com',3,80,'125/80',20,'giao vien'),(8,1,'Client8','Eight','Female','1990-08-08','888999000','118 Maple St, HCMC, Vietnam','client8@example.com',5,88,'118/76',21,'singer'),(9,1,'Client9','Nine','Male','1985-09-09','999000111','119 Maple St, HCMC, Vietnam','client9@example.com',6,91,'126/82',22,'actor'),(10,1,'Client10','Ten','Female','1992-10-10','000111222','120 Maple St, HCMC, Vietnam','client10@example.com',2,94,'128/84',23,'IT'),(11,1,'Client11','Eleven','Male','1987-11-11','111222333','121 Maple St, HCMC, Vietnam','client11@example.com',1,89,'115/75',24,'actor'),(12,1,'Client12','Twelve','Female','1995-12-12','222333444','122 Maple St, HCMC, Vietnam','client12@example.com',2,97,'122/78',25,'actor'),(13,1,'Client13','Thirteen','Male','1989-01-13','333444555','123 Maple St, HCMC, Vietnam','client13@example.com',3,86,'135/88',26,'actor'),(14,1,'Client14','Fourteen','Female','1996-02-14','444555666','124 Maple St, HCMC, Vietnam','client14@example.com',1,92,'127/81',27,'IT'),(15,1,'Client15','Fifteen','Male','1991-03-15','555666777','125 Maple St, HCMC, Vietnam','client15@example.com',4,90,'120/80',28,'IT'),(16,1,'Client16','Sixteen','Female','1988-04-16','666777888','126 Maple St, HCMC, Vietnam','client16@example.com',2,95,'115/75',29,'PM'),(17,1,'Client17','Seventeen','Male','1984-05-17','777888999','127 Maple St, HCMC, Vietnam','client17@example.com',3,88,'120/80',30,'PM'),(18,1,'Client18','Eighteen','Female','1992-06-18','888999000','128 Maple St, HCMC, Vietnam','client18@example.com',5,87,'123/79',31,'PM'),(19,1,'Client19','Nineteen','Male','1993-07-19','999000111','129 Maple St, HCMC, Vietnam','client19@example.com',1,90,'135/85',32,'PA'),(20,1,'Client20','Twenty','Female','1990-08-20','000111222','130 Maple St, HCMC, Vietnam','client20@example.com',2,93,'129/82',33,'PA');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients_tracking`
--

DROP TABLE IF EXISTS `clients_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients_tracking` (
                                    `tracking_id` int NOT NULL AUTO_INCREMENT,
                                    `client_id` int DEFAULT NULL,
                                    `date` date NOT NULL,
                                    `weight` float DEFAULT NULL,
                                    `sleep_hour` float DEFAULT NULL,
                                    `step_count` int DEFAULT NULL,
                                    `notes` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                    PRIMARY KEY (`tracking_id`),
                                    KEY `client_id` (`client_id`),
                                    CONSTRAINT `clients_tracking_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients_tracking`
--

LOCK TABLES `clients_tracking` WRITE;
/*!40000 ALTER TABLE `clients_tracking` DISABLE KEYS */;
INSERT INTO `clients_tracking` VALUES (1,14,'2024-10-01',75.5,7.5,10000,'Feeling great'),(2,15,'2024-10-02',82,6.5,8500,'Slightly tired'),(3,16,'2024-10-03',68,8,12000,'Good energy'),(4,17,'2024-10-04',90,5,6000,'Need more sleep'),(5,18,'2024-10-05',72.5,7,9500,'Balanced day'),(6,19,'2024-10-06',80,6,8000,'Feeling okay'),(7,20,'2024-10-07',77.5,7.5,11000,'Good workout'),(8,1,'2024-10-08',85,5.5,7500,'Tired but happy'),(9,2,'2024-10-09',65.5,8,10500,'Feeling strong'),(10,3,'2024-10-10',78,6.5,9000,'Normal day');
/*!40000 ALTER TABLE `clients_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
                            `exercise_id` int NOT NULL AUTO_INCREMENT,
                            `exercisename` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `muscle_group` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            PRIMARY KEY (`exercise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,'Squat','Legs'),(2,'Bench Press','Chest'),(3,'Deadlift','Back'),(4,'Pull-up','Back'),(5,'Push-up','Chest'),(6,'Lunge','Legs'),(7,'Plank','Core'),(8,'Burpee','Full Body'),(9,'Sit-up','Core'),(10,'Jump Rope','Cardio');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_of_program`
--

DROP TABLE IF EXISTS `exercise_of_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_of_program` (
                                       `exercise_of_program_id` int NOT NULL AUTO_INCREMENT,
                                       `exercise_id` int DEFAULT NULL,
                                       `program_id` int DEFAULT NULL,
                                       `sets_standard` int DEFAULT NULL,
                                       `reps_standard` int DEFAULT NULL,
                                       `set1` int DEFAULT NULL,
                                       `set2` int DEFAULT NULL,
                                       `set3` int DEFAULT NULL,
                                       `set4` int DEFAULT NULL,
                                       `set5` int DEFAULT NULL,
                                       `tempo` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                       `rir_rpe` float DEFAULT NULL,
                                       `loadofexersise` float DEFAULT NULL,
                                       PRIMARY KEY (`exercise_of_program_id`),
                                       KEY `exercise_id` (`exercise_id`),
                                       KEY `program_id` (`program_id`),
                                       CONSTRAINT `exercise_of_program_ibfk_1` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`exercise_id`),
                                       CONSTRAINT `exercise_of_program_ibfk_2` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_of_program`
--

LOCK TABLES `exercise_of_program` WRITE;
/*!40000 ALTER TABLE `exercise_of_program` DISABLE KEYS */;
INSERT INTO `exercise_of_program` VALUES (1,1,1,3,12,0,0,0,0,0,'2010',8.5,80),(2,2,2,4,10,10,10,10,NULL,NULL,'2020',9,90),(3,3,3,5,8,8,8,6,NULL,NULL,'3010',7,100),(4,4,4,3,15,15,14,12,NULL,NULL,'1020',6.5,50),(5,5,5,5,10,10,9,9,NULL,NULL,'2011',7.5,70),(6,6,6,4,12,12,12,11,NULL,NULL,'2020',8,60),(7,7,7,3,20,20,18,17,NULL,NULL,'3030',6,40),(8,8,8,5,10,10,10,8,NULL,NULL,'2020',9.5,85),(9,9,9,4,15,15,14,13,NULL,NULL,'1010',7,65),(10,10,10,3,20,20,19,18,NULL,NULL,'2010',6.5,35),(20,1,25,3,12,12,10,8,0,0,'2-1-2',7,0),(21,2,25,3,10,10,9,8,0,0,'2-1-2',7,0),(22,3,25,3,5,5,5,4,0,0,'3-1-2',6,0),(23,4,25,3,8,8,6,5,0,0,'2-1-2',7,0),(24,5,25,4,10,10,10,8,0,0,'2-1-2',8,0);
/*!40000 ALTER TABLE `exercise_of_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
                            `feedback_id` int NOT NULL AUTO_INCREMENT,
                            `feedback` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `approve` tinyint(1) DEFAULT NULL,
                            `fm_id` int DEFAULT NULL,
                            `mealplan_id` int DEFAULT NULL,
                            `program_id` int DEFAULT NULL,
                            PRIMARY KEY (`feedback_id`),
                            KEY `fm_id` (`fm_id`),
                            KEY `mealplan_id` (`mealplan_id`),
                            KEY `program_id` (`program_id`),
                            CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`fm_id`) REFERENCES `fitness_manager` (`fm_id`),
                            CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`mealplan_id`) REFERENCES `mealplan` (`mealplan_id`),
                            CONSTRAINT `feedback_ibfk_3` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'Updated feedback text.',1,1,NULL,1),(2,'I appreciate the detailed tracking of my nutritional intake. It helps me stay accountable.',0,1,NULL,2),(3,'The variety of meals keeps things interesting and enjoyable. I look forward to every meal!',1,1,NULL,3),(4,'The workout suggestions included in the program are very effective!',0,2,NULL,4),(5,'The feedback on my progress is helpful; I feel supported in my fitness journey.',1,2,NULL,5),(6,'I love how easy it is to follow the meal plan. It fits perfectly into my busy schedule.',0,2,NULL,6),(7,'The updated feedback message here.',0,1,NULL,7),(8,'I have noticed positive changes in my weight and overall health since starting this program.',0,2,NULL,8),(9,'I find the app user-friendly, and it simplifies tracking my meals and workouts.',1,1,NULL,9),(10,'The personalized approach to fitness and nutrition has been a game changer for me!',0,2,NULL,10),(11,'The nutrition advice provided is practical and easy to follow, making a difference in my eating habits.',1,1,1,NULL),(12,'I enjoy the community support; it motivates me to stay on track.',0,2,2,NULL),(13,'The progress updates help me see how far I’ve come. It is very encouraging!',1,2,3,NULL),(14,'I appreciate the regular check-ins from my fitness manager. They keep me motivated.',0,2,4,NULL),(15,'The flexibility in meal choices allows me to maintain my preferences while staying healthy.',1,1,5,NULL),(16,'I feel more confident in my fitness routine thanks to the guidance I have received.',0,1,6,NULL),(17,'The integration of fitness and nutrition advice has made a huge difference in my results.',1,2,7,NULL),(18,'I am really happy with the results I have achieved so far. Thank you for the support!',0,1,8,NULL),(19,'The meal portions are just right and help me feel satisfied without overindulging.',1,1,9,NULL),(20,'I enjoy trying new meals and discovering new favorites through the plan.',0,2,10,NULL),(21,'The regular updates and feedback from my fitness manager are invaluable in keeping me focused.',1,1,11,NULL),(22,'The positive changes in my fitness levels are undeniable. Thank you for your guidance!',0,2,12,NULL);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fitness_manager`
--

DROP TABLE IF EXISTS `fitness_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fitness_manager` (
                                   `fm_id` int NOT NULL AUTO_INCREMENT,
                                   `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                   `last_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                   `gender` varchar(255) DEFAULT NULL,
                                   `birth_date` datetime(6) DEFAULT NULL,
                                   `phone` varchar(15) DEFAULT NULL,
                                   `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                   `email` varchar(50) DEFAULT NULL,
                                   `account_id` int DEFAULT NULL,
                                   `owner_id` int DEFAULT NULL,
                                   PRIMARY KEY (`fm_id`),
                                   KEY `owner_id` (`owner_id`),
                                   KEY `account_id` (`account_id`),
                                   CONSTRAINT `fitness_manager_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`owner_id`),
                                   CONSTRAINT `fitness_manager_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fitness_manager`
--

LOCK TABLES `fitness_manager` WRITE;
/*!40000 ALTER TABLE `fitness_manager` DISABLE KEYS */;
INSERT INTO `fitness_manager` VALUES (1,'Jane','Smith','Female','1990-02-20 00:00:00.000000','987654321','456 Elm St, HCMC, Vietnam','jane.smith@example.com',2,1),(2,'Mike','Johnson','Male','1988-05-30 00:00:00.000000','192837465','789 Pine St, HCMC, Vietnam','mike.johnson@example.com',3,1);
/*!40000 ALTER TABLE `fitness_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
                        `food_id` int NOT NULL AUTO_INCREMENT,
                        `food_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `unit` varchar(10) DEFAULT NULL,
                        PRIMARY KEY (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Chicken Breast','grams'),(2,'Brown Rice','grams'),(3,'Broccoli','grams'),(4,'Almonds','grams'),(5,'Salmon','grams'),(6,'Sweet Potato','grams'),(7,'Egg','grams'),(8,'Avocado','grams'),(9,'Greek Yogurt','grams'),(10,'Oats','grams');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_of_meal`
--

DROP TABLE IF EXISTS `food_of_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_of_meal` (
                                `food_of_meal_id` int NOT NULL AUTO_INCREMENT,
                                `mealplan_id` int DEFAULT NULL,
                                `food_id` int DEFAULT NULL,
                                `protein` float DEFAULT NULL,
                                `fat` float DEFAULT NULL,
                                `carb` float DEFAULT NULL,
                                `note` varchar(255) DEFAULT NULL,
                                `amount` int DEFAULT NULL,
                                PRIMARY KEY (`food_of_meal_id`),
                                KEY `mealplan_id` (`mealplan_id`),
                                KEY `food_id` (`food_id`),
                                CONSTRAINT `food_of_meal_ibfk_1` FOREIGN KEY (`mealplan_id`) REFERENCES `mealplan` (`mealplan_id`),
                                CONSTRAINT `food_of_meal_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_of_meal`
--

LOCK TABLES `food_of_meal` WRITE;
/*!40000 ALTER TABLE `food_of_meal` DISABLE KEYS */;
INSERT INTO `food_of_meal` VALUES (1,1,1,16,19,10,'Gluten-free',348),(2,2,2,22,13,81,'Rich in fiber',431),(3,3,3,29,12,83,'Low carb',125),(4,4,4,2,19,50,'High protein',246),(5,5,5,11,20,75,'Gluten-free',413),(6,6,6,26,14,65,'Vegan-friendly',378),(7,7,7,21,12,69,'Low carb',182),(8,8,8,24,1,57,'Low carb',197),(9,9,9,23,11,29,'High protein',168),(10,10,10,22,8,54,'High protein',142),(11,1,1,30,4,83,'Low carb',510),(12,1,2,19,14,40,'Low carb',521),(13,1,3,27,14,52,'High protein',169),(14,1,1,5,6,80,'Rich in fiber',277),(15,1,2,5,13,55,'Low carb',352),(16,1,3,26,18,61,'High protein',487),(17,1,1,7,19,14,'Vegan-friendly',211),(18,1,2,3,4,62,'Rich in fiber',467),(19,1,3,13,12,44,'Rich in fiber',336),(20,11,1,14,20,40,'Low carb',221),(21,11,3,17,12,21,'Low carb',530),(22,11,2,13,11,38,'Gluten-free',149),(23,12,1,30,9,15,'Low carb',282),(24,13,1,21,16,65,'Gluten-free',227),(25,13,2,27,17,48,'Low carb',304),(26,13,3,22,1,83,'Rich in fiber',157),(31,18,1,8,12,5,'Low carb',487),(32,18,2,17,20,26,'Gluten-free',231),(33,18,3,14,14,10,'Low carb',274),(34,19,1,27,13,50,'Rich in fiber',496),(35,19,2,27,6,69,'High protein',232),(36,19,3,21,7,54,'High protein',335),(37,20,1,10,15,57,'High protein',382),(38,20,3,23,20,46,'Vegan-friendly',516),(39,21,2,3,13,77,'Vegan-friendly',380),(40,21,3,11,1,83,'Rich in fiber',284),(41,21,4,2,3,51,'Gluten-free',358),(42,22,2,16,13,43,'Gluten-free',199);
/*!40000 ALTER TABLE `food_of_meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mealplan`
--

DROP TABLE IF EXISTS `mealplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mealplan` (
                            `mealplan_id` int NOT NULL AUTO_INCREMENT,
                            `training_status` tinyint(1) DEFAULT NULL,
                            `clients_id` int DEFAULT NULL,
                            `day` date DEFAULT NULL,
                            `session` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`mealplan_id`),
                            KEY `clients_id` (`clients_id`),
                            CONSTRAINT `mealplan_ibfk_1` FOREIGN KEY (`clients_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mealplan`
--

LOCK TABLES `mealplan` WRITE;
/*!40000 ALTER TABLE `mealplan` DISABLE KEYS */;
INSERT INTO `mealplan` VALUES (1,1,1,'2024-09-23','Lunch'),(2,1,15,'2024-03-30','Lunch'),(3,0,16,'2024-01-13','Dinner'),(4,1,17,'2024-06-06','Lunch'),(5,1,18,'2024-01-23','Breakfast'),(6,0,19,'2024-01-04','Breakfast'),(7,1,20,'2024-11-09','Dinner'),(8,1,1,'2024-04-08','Dinner'),(9,1,2,'2024-10-08','Lunch'),(10,0,3,'2024-01-16','Lunch'),(11,1,14,'2024-11-25','Lunch'),(12,1,15,'2024-05-20','Dinner'),(13,1,1,'2024-03-20','Lunch'),(14,1,1,'2024-12-04','Dinner'),(15,1,5,'2024-12-28','Breakfast'),(16,1,5,'2024-03-04','Dinner'),(17,1,5,'2024-11-24','Breakfast'),(18,1,5,'2024-12-20','Lunch'),(19,1,5,'2024-02-27','Breakfast'),(20,1,5,'2024-11-13','Dinner'),(21,0,6,'2024-11-19','Lunch'),(22,1,5,'2024-10-24','Lunch');
/*!40000 ALTER TABLE `mealplan` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_AddFeedbackForMealPlan` AFTER INSERT ON `mealplan` FOR EACH ROW BEGIN
    INSERT INTO feedback (feedback, approve, fm_id, mealplan_id, program_id)
    VALUES (NULL, FALSE, NULL, NEW.mealplan_id, NULL);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner` (
                         `owner_id` int NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                         `last_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                         `gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                         `birth_date` date DEFAULT NULL,
                         `phone` varchar(15) DEFAULT NULL,
                         `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                         `email` varchar(100) DEFAULT NULL,
                         `account_id` int DEFAULT NULL,
                         PRIMARY KEY (`owner_id`),
                         KEY `account_id` (`account_id`),
                         CONSTRAINT `owner_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES (1,'John','Doe','Male','1985-01-15','123456789','123 Main St, HCMC, Vietnam','john.doe@example.com',1);
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_trainer`
--

DROP TABLE IF EXISTS `personal_trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_trainer` (
                                    `pt_id` int NOT NULL AUTO_INCREMENT,
                                    `fm_id` int DEFAULT NULL,
                                    `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                    `last_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                    `gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                    `birth_date` date DEFAULT NULL,
                                    `phone` varchar(15) DEFAULT NULL,
                                    `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                    `email` varchar(50) DEFAULT NULL,
                                    `degree` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                    `account_id` int DEFAULT NULL,
                                    PRIMARY KEY (`pt_id`),
                                    KEY `fm_id` (`fm_id`),
                                    KEY `account_id` (`account_id`),
                                    CONSTRAINT `personal_trainer_ibfk_1` FOREIGN KEY (`fm_id`) REFERENCES `fitness_manager` (`fm_id`),
                                    CONSTRAINT `personal_trainer_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_trainer`
--

LOCK TABLES `personal_trainer` WRITE;
/*!40000 ALTER TABLE `personal_trainer` DISABLE KEYS */;
INSERT INTO `personal_trainer` VALUES (1,1,'Alex','Green','Male','1992-03-10','456789123','101 Oak St, HCMC, Vietnam','alex.green@example.com','Bachelor of Sports Science',4),(2,1,'Lisa','Brown','Female','1994-06-15','321654987','102 Maple St, HCMC, Vietnam','lisa.brown@example.com','Bachelor of Fitness',5),(3,1,'Tom','Wilson','Male','1989-11-22','654123789','103 Cedar St, HCMC, Vietnam','tom.wilson@example.com','Certification in Personal Training',6),(4,1,'Sarah','Taylor','Female','1995-08-05','789321456','104 Birch St, HCMC, Vietnam','sarah.taylor@example.com','Bachelor of Health',7),(5,1,'Kevin','Anderson','Male','1991-04-14','123789456','105 Walnut St, HCMC, Vietnam','kevin.anderson@example.com','Bachelor of Physical Education',8),(6,1,'Emma','Lee','Female','1987-12-12','987321654','106 Chestnut St, HCMC, Vietnam','emma.lee@example.com','Certification in Sports Training',9),(7,1,'Daniel','Hall','Male','1993-09-30','654987321','107 Fir St, HCMC, Vietnam','daniel.hall@example.com','Bachelor of Kinesiology',10),(8,1,'Sophia','Moore','Female','1996-07-20','321789654','108 Spruce St, HCMC, Vietnam','sophia.moore@example.com','Bachelor of Exercise Science',11),(9,1,'Ryan','Martin','Male','1990-10-25','789654123','109 Hemlock St, HCMC, Vietnam','ryan.martin@example.com','Certification in Fitness Coaching',12),(10,1,'Olivia','Scott','Female','1988-02-28','123456987','110 Larch St, HCMC, Vietnam','olivia.scott@example.com','Bachelor of Health & Fitness',13);
/*!40000 ALTER TABLE `personal_trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program` (
                           `program_id` int NOT NULL AUTO_INCREMENT,
                           `day` int DEFAULT NULL,
                           `week` int DEFAULT NULL,
                           `client_id` int DEFAULT NULL,
                           PRIMARY KEY (`program_id`),
                           KEY `client_id` (`client_id`),
                           CONSTRAINT `program_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1,1,1,14),(2,2,2,15),(3,3,3,16),(4,4,4,17),(5,5,5,18),(6,6,6,19),(7,7,7,20),(8,1,1,1),(9,2,2,2),(10,3,3,3),(11,3,2,5),(12,3,2,5),(13,1,1,5),(14,1,1,5),(15,1,1,5),(16,1,1,1),(17,1,1,1),(18,1,1,1),(19,1,1,5),(20,1,1,5),(21,1,1,5),(22,1,1,5),(23,1,1,5),(24,1,1,5),(25,1,1,5);
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_AddFeedbackForProgram` AFTER INSERT ON `program` FOR EACH ROW BEGIN
    INSERT INTO feedback (feedback, approve, fm_id, mealplan_id, program_id)
    VALUES (NULL, FALSE, NULL, NULL, NEW.program_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `role_id` int NOT NULL AUTO_INCREMENT,
                        `rolename` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Owner'),(2,'Fitness Manager'),(3,'Personal Trainer'),(4,'Client');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_package`
--

DROP TABLE IF EXISTS `training_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training_package` (
                                    `package_id` int NOT NULL AUTO_INCREMENT,
                                    `package_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                    `cost` float DEFAULT NULL,
                                    `sessionnumber` int DEFAULT NULL,
                                    `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                    PRIMARY KEY (`package_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_package`
--

LOCK TABLES `training_package` WRITE;
/*!40000 ALTER TABLE `training_package` DISABLE KEYS */;
INSERT INTO `training_package` VALUES (1,'Basic Package',200,10,'10 Sessions for beginners'),(2,'Intermediate Package',350,20,'20 Sessions for intermediates'),(3,'Advanced Package',500,30,'30 Sessions for advanced clients'),(4,'Premium Package',750,40,'40 Sessions with a personal trainer'),(5,'Weight Loss Package',400,25,'25 Sessions for weight loss'),(6,'Muscle Gain Package',600,35,'35 Sessions for muscle building'),(7,'Rehabilitation Package',450,20,'Special sessions for rehabilitation'),(8,'Senior Fitness Package',300,15,'Fitness sessions for seniors'),(9,'Youth Training Package',200,10,'Youth training sessions'),(10,'Custom Package',800,50,'Custom package tailored to client goals');
/*!40000 ALTER TABLE `training_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_package_of_clients`
--

DROP TABLE IF EXISTS `training_package_of_clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training_package_of_clients` (
                                               `training_package_of_clients_id` int NOT NULL AUTO_INCREMENT,
                                               `package_id` int DEFAULT NULL,
                                               `client_id` int DEFAULT NULL,
                                               `startdate` date DEFAULT NULL,
                                               `remainsession` int DEFAULT NULL,
                                               PRIMARY KEY (`training_package_of_clients_id`),
                                               KEY `package_id` (`package_id`),
                                               KEY `client_id` (`client_id`),
                                               CONSTRAINT `training_package_of_clients_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `training_package` (`package_id`),
                                               CONSTRAINT `training_package_of_clients_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_package_of_clients`
--

LOCK TABLES `training_package_of_clients` WRITE;
/*!40000 ALTER TABLE `training_package_of_clients` DISABLE KEYS */;
INSERT INTO `training_package_of_clients` VALUES (1,1,14,'2024-10-01',8),(2,2,15,'2024-09-15',18),(3,3,16,'2024-08-20',25),(4,4,17,'2024-07-01',30),(5,5,18,'2024-09-10',12),(6,6,19,'2024-06-15',28),(7,7,20,'2024-05-25',15),(8,8,1,'2024-09-01',9),(9,9,2,'2024-04-01',5),(10,10,3,'2024-03-10',20);
/*!40000 ALTER TABLE `training_package_of_clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'train_master'
--

--
-- Dumping routines for database 'train_master'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-09 15:37:37

alter table `food_of_meal` drop column kcals;
-- Dump completed on 2024-11-07 19:46:50
ALTER TABLE mealplan
    DROP FOREIGN KEY FKgq3y6yec4i4gtcn3b7s9s34mh,
    drop column feedback_id;
ALTER TABLE program
    DROP FOREIGN KEY FKns1b098ytoqyta87ccxmggmnj,
    drop column feedback_id;