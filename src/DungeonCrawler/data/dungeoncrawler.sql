CREATE DATABASE  IF NOT EXISTS `dungeoncrawler` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dungeoncrawler`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: dungeoncrawler
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `armors`
--

DROP TABLE IF EXISTS `armors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Description` text,
  `Level` int DEFAULT NULL,
  `Armor Rating` int DEFAULT NULL,
  `Strength Modifier` int DEFAULT NULL,
  `Smarts Modifier` int DEFAULT NULL,
  `Stealth Modifier` int DEFAULT NULL,
  `Health Modifier` int DEFAULT NULL,
  `Spell Damage Modifier` int DEFAULT NULL,
  `Value` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armors`
--

LOCK TABLES `armors` WRITE;
/*!40000 ALTER TABLE `armors` DISABLE KEYS */;
INSERT INTO `armors` VALUES (1,'placeholder','placeholder',1,1,0,0,0,0,0,50),(2,'placeholder','placeholder',1,0,1,0,0,0,0,50),(3,'placeholder','placeholder',1,0,0,1,0,0,0,50),(4,'placeholder','placeholder',1,0,0,0,1,0,0,50),(5,'placeholder','placeholder',1,0,0,0,0,1,0,50),(6,'placeholder','placeholder',1,0,0,0,0,0,1,50),(7,'placeholder','placeholder',2,2,0,0,0,0,0,100),(8,'placeholder','placeholder',2,0,2,0,0,0,0,100),(9,'placeholder','placeholder',2,0,0,2,0,0,0,100),(10,'placeholder','placeholder',2,0,0,0,2,0,0,100),(11,'placeholder','placeholder',2,0,0,0,0,2,0,100),(12,'placeholder','placeholder',2,0,0,0,0,0,2,100),(13,'placeholder','placeholder',2,1,1,0,0,1,0,150),(14,'placeholder','placeholder',2,0,0,1,1,0,1,150),(15,'placeholder','placeholder',3,3,0,0,0,0,0,150),(16,'placeholder','placeholder',3,0,3,0,0,0,0,150),(17,'placeholder','placeholder',3,0,0,3,0,0,0,150),(18,'placeholder','placeholder',3,0,0,0,3,0,0,150),(19,'placeholder','placeholder',3,0,0,0,0,3,0,150),(20,'placeholder','placeholder',3,0,0,0,0,0,3,150),(21,'placeholder','placeholder',3,2,2,0,0,2,0,200),(22,'placeholder','placeholder',3,0,0,2,2,0,2,200),(23,'placeholder','placeholder',4,4,0,0,0,0,0,200),(24,'placeholder','placeholder',4,0,4,0,0,0,0,200),(25,'placeholder','placeholder',4,0,0,4,0,0,0,200),(26,'placeholder','placeholder',4,0,0,0,4,0,0,200),(27,'placeholder','placeholder',4,0,0,0,0,4,0,200),(28,'placeholder','placeholder',4,0,0,0,0,0,4,200),(29,'placeholder','placeholder',4,3,3,0,0,3,0,250),(30,'placeholder','placeholder',4,0,0,3,3,0,3,250),(31,'placeholder','placeholder',5,5,0,0,0,0,0,250),(32,'placeholder','placeholder',5,0,5,0,0,0,0,250),(33,'placeholder','placeholder',5,0,0,5,0,0,0,250),(34,'placeholder','placeholder',5,0,0,0,5,0,0,250),(35,'placeholder','placeholder',5,0,0,0,0,5,0,250),(36,'placeholder','placeholder',5,0,0,0,0,0,5,250),(37,'placeholder','placeholder',5,4,4,0,0,4,0,300),(38,'placeholder','placeholder',5,0,0,4,4,0,4,300),(39,'placeholder','placeholder',6,6,0,0,0,0,0,300),(40,'placeholder','placeholder',6,0,6,0,0,0,0,300),(41,'placeholder','placeholder',6,0,0,6,0,0,0,300),(42,'placeholder','placeholder',6,0,0,0,6,0,0,300),(43,'placeholder','placeholder',6,0,0,0,0,6,0,300),(44,'placeholder','placeholder',6,0,0,0,0,0,6,300),(45,'placeholder','placeholder',6,5,5,0,0,5,0,350),(46,'placeholder','placeholder',6,0,0,5,5,0,5,350),(47,'placeholder','placeholder',7,7,0,0,0,0,0,350),(48,'placeholder','placeholder',7,0,7,0,0,0,0,350),(49,'placeholder','placeholder',7,0,0,7,0,0,0,350),(50,'placeholder','placeholder',7,0,0,0,7,0,0,350),(52,'placeholder','placeholder',7,0,0,0,0,7,0,350),(53,'placeholder','placeholder',7,0,0,0,0,0,7,350),(54,'placeholder','placeholder',7,6,6,0,0,6,0,400),(55,'placeholder','placeholder',7,0,0,6,6,0,6,400),(56,'placeholder','placeholder',8,8,0,0,0,0,0,400),(57,'placeholder','placeholder',8,0,8,0,0,0,0,400),(58,'placeholder','placeholder',8,0,0,8,0,0,0,400),(59,'placeholder','placeholder',8,0,0,0,8,0,0,400),(60,'placeholder','placeholder',8,0,0,0,0,8,0,400),(70,'placeholder','placeholder',8,0,0,0,0,0,8,400),(71,'placeholder','placeholder',8,7,7,0,0,7,0,450),(72,'placeholder','placeholder',8,0,0,7,7,0,7,450),(73,'placeholder','placeholder',9,9,0,0,0,0,0,450),(74,'placeholder','placeholder',9,0,9,0,0,0,0,450),(75,'placeholder','placeholder',9,0,0,9,0,0,0,450),(76,'placeholder','placeholder',9,0,0,0,9,0,0,450),(77,'placeholder','placeholder',9,0,0,0,0,9,0,450),(78,'placeholder','placeholder',9,0,0,0,0,0,9,450),(79,'placeholder','placeholder',9,8,8,0,0,8,0,500),(80,'placeholder','placeholder',9,0,0,8,8,0,8,500),(81,'placeholder','placeholder',10,10,0,0,0,0,0,500),(82,'placeholder','placeholder',10,0,10,0,0,0,0,500),(83,'placeholder','placeholder',10,0,0,10,0,0,0,500),(84,'placeholder','placeholder',10,0,0,0,10,0,0,500),(85,'placeholder','placeholder',10,0,0,0,0,10,0,500),(86,'placeholder','placeholder',10,0,0,0,0,0,10,500),(87,'placeholder','placeholder',10,9,9,0,0,9,0,550),(88,'placeholder','placeholder',10,0,0,9,9,0,9,550),(89,'Armor of Gods','/toggle god mode',10,10,10,10,10,10,10,1000);
/*!40000 ALTER TABLE `armors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moves`
--

DROP TABLE IF EXISTS `moves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moves` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Description` text,
  `Theme` text,
  `Type` int DEFAULT NULL,
  `Level` int DEFAULT NULL,
  `Damage Modifier` int DEFAULT NULL,
  `Ignore Armor` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moves`
--

LOCK TABLES `moves` WRITE;
/*!40000 ALTER TABLE `moves` DISABLE KEYS */;
INSERT INTO `moves` VALUES (1,'Swipe','A simple attack; you whip your weapon through the air at your foe.','Player',1,1,0,'FALSE'),(2,'Thrash','A rake of a skeletal claw and a senseless drumbeat of bony arms upon you','Skeletons',1,1,0,'FALSE'),(3,'placeholder','placeholder','Skeletons',1,2,1,'FALSE'),(4,'placeholder','placeholder','Skeletons',1,3,2,'FALSE'),(5,'placeholder','placeholder','Skeletons',1,4,3,'FALSE'),(6,'placeholder','placeholder','Skeletons',1,5,4,'FALSE'),(7,'placeholder','placeholder','Skeletons',1,6,5,'FALSE'),(8,'placeholder','placeholder','Skeletons',1,7,6,'FALSE'),(9,'placeholder','placeholder','Skeletons',1,8,7,'FALSE'),(10,'placeholder','placeholder','Skeletons',1,9,8,'FALSE'),(11,'placeholder','placeholder','Skeletons',1,10,9,'FALSE'),(12,'placeholder','placeholder','Skeletons',2,1,0,'FALSE'),(13,'placeholder','placeholder','Skeletons',2,2,1,'FALSE'),(14,'placeholder','placeholder','Skeletons',2,3,2,'FALSE'),(15,'placeholder','placeholder','Skeletons',2,4,3,'FALSE'),(16,'placeholder','placeholder','Skeletons',2,5,4,'FALSE'),(17,'placeholder','placeholder','Skeletons',2,6,5,'FALSE'),(18,'placeholder','placeholder','Skeletons',2,7,6,'FALSE'),(19,'placeholder','placeholder','Skeletons',2,8,7,'FALSE'),(20,'placeholder','placeholder','Skeletons',2,9,8,'FALSE'),(21,'placeholder','placeholder','Skeletons',2,10,9,'FALSE'),(22,'placeholder','placeholder','Skeletons',3,1,0,'FALSE'),(23,'placeholder','placeholder','Skeletons',3,2,1,'FALSE'),(24,'placeholder','placeholder','Skeletons',3,3,2,'FALSE'),(25,'placeholder','placeholder','Skeletons',3,4,3,'FALSE'),(26,'placeholder','placeholder','Skeletons',3,5,4,'FALSE'),(27,'placeholder','placeholder','Skeletons',3,6,5,'FALSE'),(28,'placeholder','placeholder','Skeletons',3,7,6,'FALSE'),(29,'placeholder','placeholder','Skeletons',3,8,7,'FALSE'),(30,'placeholder','placeholder','Skeletons',3,9,8,'FALSE'),(31,'placeholder','placeholder','Skeletons',3,10,9,'FALSE'),(32,'placeholder','placeholder','Player',1,2,1,'FALSE');
/*!40000 ALTER TABLE `moves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Description` text,
  `Theme` text,
  `bossRoom` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'placeholder','placeholder','Automatons','FALSE'),(2,'placeholder','placeholder','Automatons','FALSE'),(3,'placeholder','placeholder','Automatons','FALSE'),(4,'placeholder','placeholder','Automatons','FALSE'),(5,'placeholder','placeholder','Automatons','FALSE'),(6,'placeholder','placeholder','Automaton','TRUE'),(7,'placeholder','placeholder','Bandit','FALSE'),(8,'placeholder','placeholder','Bandit','FALSE'),(9,'placeholder','placeholder','Bandit','FALSE'),(10,'placeholder','placeholder','Bandit','FALSE'),(11,'placeholder','placeholder','Bandit','FALSE'),(12,'placeholder','placeholder','Bandit','TRUE'),(13,'placeholder','placeholder','Beasts','FALSE'),(14,'placeholder','placeholder','Beasts','FALSE'),(15,'placeholder','placeholder','Beasts','FALSE'),(16,'placeholder','placeholder','Beasts','FALSE'),(17,'placeholder','placeholder','Beasts','FALSE'),(18,'placeholder','placeholder','Beasts','TRUE'),(19,'placeholder','placeholder','Demons','FALSE'),(20,'placeholder','placeholder','Demons','FALSE'),(21,'placeholder','placeholder','Demons','FALSE'),(22,'placeholder','placeholder','Demons','FALSE'),(23,'placeholder','placeholder','Demons','FALSE'),(24,'placeholder','placeholder','Demons','TRUE'),(25,'placeholder','placeholder','Deserters','FALSE'),(26,'placeholder','placeholder','Deserters','FALSE'),(27,'placeholder','placeholder','Deserters','FALSE'),(28,'placeholder','placeholder','Deserters','FALSE'),(29,'placeholder','placeholder','Deserters','FALSE'),(30,'placeholder','placeholder','Deserters','TRUE'),(31,'placeholder','placeholder','Fae','FALSE'),(32,'placeholder','placeholder','Fae','FALSE'),(33,'placeholder','placeholder','Fae','FALSE'),(34,'placeholder','placeholder','Fae','FALSE'),(35,'placeholder','placeholder','Fae','FALSE'),(36,'placeholder','placeholder','Fae','TRUE'),(37,'placeholder','placeholder','Flooded','FALSE'),(38,'placeholder','placeholder','Flooded','FALSE'),(39,'placeholder','placeholder','Flooded','FALSE'),(40,'placeholder','placeholder','Flooded','FALSE'),(41,'placeholder','placeholder','Flooded','FALSE'),(42,'placeholder','placeholder','Flooded','TRUE'),(43,'placeholder','placeholder','Ghosts','FALSE'),(44,'placeholder','placeholder','Ghosts','FALSE'),(45,'placeholder','placeholder','Ghosts','FALSE'),(46,'placeholder','placeholder','Ghosts','FALSE'),(47,'placeholder','placeholder','Ghosts','FALSE'),(48,'placeholder','placeholder','Ghosts','TRUE'),(49,'placeholder','placeholder','Goblins','FALSE'),(50,'placeholder','placeholder','Goblins','FALSE'),(51,'placeholder','placeholder','Goblins','FALSE'),(52,'placeholder','placeholder','Goblins','FALSE'),(53,'placeholder','placeholder','Goblins','FALSE'),(54,'placeholder','placeholder','Goblins','TRUE'),(55,'placeholder','placeholder','Golems','FALSE'),(56,'placeholder','placeholder','Golems','FALSE'),(57,'placeholder','placeholder','Golems','FALSE'),(58,'placeholder','placeholder','Golems','FALSE'),(59,'placeholder','placeholder','Golems','FALSE'),(60,'placeholder','placeholder','Golems','TRUE'),(61,'placeholder','placeholder','Mages','FALSE'),(62,'placeholder','placeholder','Mages','FALSE'),(63,'placeholder','placeholder','Mages','FALSE'),(64,'placeholder','placeholder','Mages','FALSE'),(65,'placeholder','placeholder','Mages','FALSE'),(66,'placeholder','placeholder','Mages','TRUE'),(67,'placeholder','placeholder','Oozes','FALSE'),(68,'placeholder','placeholder','Oozes','FALSE'),(69,'placeholder','placeholder','Oozes','FALSE'),(70,'placeholder','placeholder','Oozes','FALSE'),(71,'placeholder','placeholder','Oozes','FALSE'),(72,'placeholder','placeholder','Oozes','TRUE'),(73,'placeholder','placeholder','Pirates','FALSE'),(74,'placeholder','placeholder','Pirates','FALSE'),(75,'placeholder','placeholder','Pirates','FALSE'),(76,'placeholder','placeholder','Pirates','FALSE'),(77,'placeholder','placeholder','Pirates','FALSE'),(78,'placeholder','placeholder','Pirates','TRUE'),(79,'placeholder','placeholder','Plants','FALSE'),(80,'placeholder','placeholder','Plants','FALSE'),(81,'placeholder','placeholder','Plants','FALSE'),(82,'placeholder','placeholder','Plants','FALSE'),(83,'placeholder','placeholder','Plants','FALSE'),(84,'placeholder','placeholder','Plants','TRUE'),(85,'placeholder','placeholder','Skeletons','FALSE'),(86,'placeholder','placeholder','Skeletons','FALSE'),(87,'placeholder','placeholder','Skeletons','FALSE'),(88,'placeholder','placeholder','Skeletons','FALSE'),(89,'placeholder','placeholder','Skeletons','FALSE'),(90,'placeholder','placeholder','Skeletons','TRUE'),(91,'placeholder','placeholder','Spiders','FALSE'),(92,'placeholder','placeholder','Spiders','FALSE'),(93,'placeholder','placeholder','Spiders','FALSE'),(94,'placeholder','placeholder','Spiders','FALSE'),(95,'placeholder','placeholder','Spiders','FALSE'),(96,'placeholder','placeholder','Spiders','TRUE'),(97,'placeholder','placeholder','Vampires','FALSE'),(98,'placeholder','placeholder','Vampires','FALSE'),(99,'placeholder','placeholder','Vampires','FALSE'),(100,'placeholder','placeholder','Vampires','FALSE'),(101,'placeholder','placeholder','Vampires','FALSE'),(102,'placeholder','placeholder','Vampires','TRUE'),(103,'placeholder','placeholder','Void Creatures','FALSE'),(104,'placeholder','placeholder','Void Creatures','FALSE'),(105,'placeholder','placeholder','Void Creatures','FALSE'),(106,'placeholder','placeholder','Void Creatures','FALSE'),(107,'placeholder','placeholder','Void Creatures','FALSE'),(108,'placeholder','placeholder','Void Creatures','TRUE'),(109,'placeholder','placeholder','Wild Men','FALSE'),(110,'placeholder','placeholder','Wild Men','FALSE'),(111,'placeholder','placeholder','Wild Men','FALSE'),(112,'placeholder','placeholder','Wild Men','FALSE'),(113,'placeholder','placeholder','Wild Men','FALSE'),(114,'placeholder','placeholder','Wild Men','TRUE'),(115,'placeholder','placeholder','Zombies','FALSE'),(116,'placeholder','placeholder','Zombies','FALSE'),(117,'placeholder','placeholder','Zombies','FALSE'),(118,'placeholder','placeholder','Zombies','FALSE'),(119,'placeholder','placeholder','Zombies','FALSE'),(120,'placeholder','placeholder','Zombies','TRUE');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skeletons`
--

DROP TABLE IF EXISTS `skeletons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skeletons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Description` text,
  `Type` int DEFAULT NULL,
  `Tier` int DEFAULT NULL,
  `Health` int DEFAULT NULL,
  `Damage` int DEFAULT NULL,
  `Armor` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skeletons`
--

LOCK TABLES `skeletons` WRITE;
/*!40000 ALTER TABLE `skeletons` DISABLE KEYS */;
INSERT INTO `skeletons` VALUES (1,'Shambler','Little more than gangrenous bones held together by strips of rotted flesh and some infernal magic, shuffling and scraping across the floor with a mindless malice in its empty eye sockets.',1,1,10,11,0),(2,'Archer','Perhaps a hunter in a past life, shreds of moth-eaten cloth hang from the undead creature\'s bones, and it nocks and arrow in its bow.',2,2,10,13,0),(3,'Warrior','Life by the sword, death by the sword, and now unlife again by the sword: A torn tabard across its ribcage, a simple dented helm on its skull, and a rusty blade clutched in skeletal grip, it lurches forward.',1,2,11,12,1),(4,'Mage','Gnarled bones are hidden \'neath fraying fabric, whatever holy symbol or other crest once sewn into those robes now lost to time--all that remains of the man this once was is the magic he knows still even in death, broiling in hand, aimed at you.',3,3,8,17,0),(5,'Knight','A boon in life, its suit of armor, skull to toe, is now its eternal tomb. The skeleton approaches with poise and draws sword to a guard position, waiting with uncanny intelligence for you to make the first move.',1,4,13,14,3),(6,'Commander','Clanking of steel and rattling of bones, the unstable gait of this undead foe is no assurance of it being easily dispatched; it hefts a mighty greatsword over a plumed helm and calls its skeletal underlings to battle.',1,5,14,15,3),(7,'King','On its head, a crown of gold besets a crown of bone, its lustre lost to legend--perhaps a tyrant in life, cursed to rule cobwebs and carrion in death, or perhaps a benevolent ruler, bones exhumed for ill. It draws a ceremonial blade, no less sharp, and challenges you.',1,6,15,16,4),(8,'Lich','Whether the foul mind behind this unholy legion or simply a soldier in its ranks,  the terrible visage of this haunting figure, this gaunt corpse in elegant garb, twisting staff in hand and necromantic grimoire on hip, a smile cracked across its face as it lifts into the air, borne aloft by evil magics--you know you must kill it before it kills you.',3,6,10,20,0);
/*!40000 ALTER TABLE `skeletons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trinkets`
--

DROP TABLE IF EXISTS `trinkets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trinkets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Description` text,
  `Level` int DEFAULT NULL,
  `Strength Modifier` int DEFAULT NULL,
  `Smarts Modifier` int DEFAULT NULL,
  `Stealth Modifier` int DEFAULT NULL,
  `Health Modifier` int DEFAULT NULL,
  `Damage Modifier` int DEFAULT NULL,
  `Spell Damage Modifier` int DEFAULT NULL,
  `Armor Rating Modifier` int DEFAULT NULL,
  `Value` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trinkets`
--

LOCK TABLES `trinkets` WRITE;
/*!40000 ALTER TABLE `trinkets` DISABLE KEYS */;
INSERT INTO `trinkets` VALUES (1,'placeholder','placeholder',1,1,0,0,0,0,0,0,50),(2,'placeholder','placeholder',1,0,1,0,0,0,0,0,50),(3,'placeholder','placeholder',1,0,0,1,0,0,0,0,50),(4,'placeholder','placeholder',1,0,0,0,1,0,0,0,50),(5,'placeholder','placeholder',1,0,0,0,0,1,0,0,50),(6,'placeholder','placeholder',1,0,0,0,0,0,1,0,50),(7,'placeholder','placeholder',1,0,0,0,0,0,0,1,50),(8,'placeholder','placeholder',2,2,0,0,0,0,0,0,100),(9,'placeholder','placeholder',2,0,2,0,0,0,0,0,100),(10,'placeholder','placeholder',2,0,0,2,0,0,0,0,100),(11,'placeholder','placeholder',2,0,0,0,2,0,0,0,100),(12,'placeholder','placeholder',2,0,0,0,0,2,0,0,100),(13,'placeholder','placeholder',2,0,0,0,0,0,2,0,100),(14,'placeholder','placeholder',2,0,0,0,0,0,0,2,100),(15,'placeholder','placeholder',2,1,0,0,1,1,0,0,150),(16,'placeholder','placeholder',2,0,1,1,0,0,1,0,150),(17,'placeholder','placeholder',3,3,0,0,0,0,0,0,150),(18,'placeholder','placeholder',3,0,3,0,0,0,0,0,150),(19,'placeholder','placeholder',3,0,0,3,0,0,0,0,150),(20,'placeholder','placeholder',3,0,0,0,3,0,0,0,150),(21,'placeholder','placeholder',3,0,0,0,0,3,0,0,150),(22,'placeholder','placeholder',3,0,0,0,0,0,3,0,150),(23,'placeholder','placeholder',3,0,0,0,0,0,0,3,150),(24,'placeholder','placeholder',3,2,0,0,2,2,0,0,200),(25,'placeholder','placeholder',3,0,2,2,0,0,2,0,200),(26,'placeholder','placeholder',4,4,0,0,0,0,0,0,200),(27,'placeholder','placeholder',4,0,4,0,0,0,0,0,200),(28,'placeholder','placeholder',4,0,0,4,0,0,0,0,200),(29,'placeholder','placeholder',4,0,0,0,4,0,0,0,200),(30,'placeholder','placeholder',4,0,0,0,0,4,0,0,200),(31,'placeholder','placeholder',4,0,0,0,0,0,4,0,200),(32,'placeholder','placeholder',4,0,0,0,0,0,0,4,200),(33,'placeholder','placeholder',4,3,0,0,3,3,0,0,250),(34,'placeholder','placeholder',4,0,3,3,0,0,3,0,250),(35,'placeholder','placeholder',5,5,0,0,0,0,0,0,250),(36,'placeholder','placeholder',5,0,5,0,0,0,0,0,250),(37,'placeholder','placeholder',5,0,0,5,0,0,0,0,250),(38,'placeholder','placeholder',5,0,0,0,5,0,0,0,250),(39,'placeholder','placeholder',5,0,0,0,0,5,0,0,250),(40,'placeholder','placeholder',5,0,0,0,0,0,5,0,250),(41,'placeholder','placeholder',5,0,0,0,0,0,0,5,250),(42,'placeholder','placeholder',5,4,0,0,4,4,0,0,300),(43,'placeholder','placeholder',5,0,4,4,0,0,4,0,300),(44,'placeholder','placeholder',6,6,0,0,0,0,0,0,300),(45,'placeholder','placeholder',6,0,6,0,0,0,0,0,300),(46,'placeholder','placeholder',6,0,0,6,0,0,0,0,300),(47,'placeholder','placeholder',6,0,0,0,6,0,0,0,300),(48,'placeholder','placeholder',6,0,0,0,0,6,0,0,300),(49,'placeholder','placeholder',6,0,0,0,0,0,6,0,300),(50,'placeholder','placeholder',6,0,0,0,0,0,0,6,300),(52,'placeholder','placeholder',6,5,0,0,5,5,0,0,350),(53,'placeholder','placeholder',6,0,5,5,0,0,5,0,350),(54,'placeholder','placeholder',7,7,0,0,0,0,0,0,350),(55,'placeholder','placeholder',7,0,7,0,0,0,0,0,350),(56,'placeholder','placeholder',7,0,0,7,0,0,0,0,350),(57,'placeholder','placeholder',7,0,0,0,7,0,0,0,350),(58,'placeholder','placeholder',7,0,0,0,0,7,0,0,350),(59,'placeholder','placeholder',7,0,0,0,0,0,7,0,350),(60,'placeholder','placeholder',7,0,0,0,0,0,0,7,350),(70,'placeholder','placeholder',7,6,0,0,6,6,0,0,400),(71,'placeholder','placeholder',7,0,6,6,0,0,6,0,400),(72,'placeholder','placeholder',8,8,0,0,0,0,0,0,400),(73,'placeholder','placeholder',8,0,8,0,0,0,0,0,400),(74,'placeholder','placeholder',8,0,0,8,0,0,0,0,400),(75,'placeholder','placeholder',8,0,0,0,8,0,0,0,400),(76,'placeholder','placeholder',8,0,0,0,0,8,0,0,400),(77,'placeholder','placeholder',8,0,0,0,0,0,8,0,400),(78,'placeholder','placeholder',8,0,0,0,0,0,0,8,400),(79,'placeholder','placeholder',8,7,0,0,7,7,0,0,450),(80,'placeholder','placeholder',8,0,7,7,0,0,7,0,450),(81,'placeholder','placeholder',9,9,0,0,0,0,0,0,450),(82,'placeholder','placeholder',9,0,9,0,0,0,0,0,450),(83,'placeholder','placeholder',9,0,0,9,0,0,0,0,450),(84,'placeholder','placeholder',9,0,0,0,9,0,0,0,450),(85,'placeholder','placeholder',9,0,0,0,0,9,0,0,450),(86,'placeholder','placeholder',9,0,0,0,0,0,9,0,450),(87,'placeholder','placeholder',9,0,0,0,0,0,0,9,450),(88,'placeholder','placeholder',9,8,0,0,8,8,0,0,500),(89,'placeholder','placeholder',9,0,8,8,0,0,8,0,500),(90,'placeholder','placeholder',10,10,0,0,0,0,0,0,500),(91,'placeholder','placeholder',10,0,10,0,0,0,0,0,500),(92,'placeholder','placeholder',10,0,0,10,0,0,0,0,500),(93,'placeholder','placeholder',10,0,0,0,10,0,0,0,500),(94,'placeholder','placeholder',10,0,0,0,0,10,0,0,500),(95,'placeholder','placeholder',10,0,0,0,0,0,10,0,500),(96,'placeholder','placeholder',10,0,0,0,0,0,0,10,500),(97,'placeholder','placeholder',10,9,0,0,9,9,0,0,550),(98,'placeholder','placeholder',10,0,9,9,0,0,9,0,550),(99,'The God Ring','/toggle godmode',10,10,10,10,10,10,10,10,1000);
/*!40000 ALTER TABLE `trinkets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weapons`
--

DROP TABLE IF EXISTS `weapons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weapons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Description` text,
  `Level` int DEFAULT NULL,
  `Damage Rating` int DEFAULT NULL,
  `Spell Damage Modifier` int DEFAULT NULL,
  `Strength Modifier` int DEFAULT NULL,
  `Value` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weapons`
--

LOCK TABLES `weapons` WRITE;
/*!40000 ALTER TABLE `weapons` DISABLE KEYS */;
INSERT INTO `weapons` VALUES (1,'placeholder','placeholder',1,1,0,0,50),(2,'placeholder','placeholder',1,0,1,0,50),(3,'placeholder','placeholder',1,0,0,1,50),(4,'placeholder','placeholder',2,2,0,0,100),(5,'placeholder','placeholder',2,0,2,0,100),(6,'placeholder','placeholder',2,0,0,2,100),(7,'placeholder','placeholder',2,1,0,1,150),(8,'placeholder','placeholder',2,0,3,0,150),(9,'placeholder','placeholder',3,3,0,0,150),(10,'placeholder','placeholder',3,0,3,0,150),(11,'placeholder','placeholder',3,0,0,3,150),(12,'placeholder','placeholder',3,2,0,2,200),(13,'placeholder','placeholder',3,0,4,0,200),(14,'placeholder','placeholder',4,4,0,0,200),(15,'placeholder','placeholder',4,0,4,0,200),(16,'placeholder','placeholder',4,0,0,4,200),(17,'placeholder','placeholder',4,3,0,3,250),(18,'placeholder','placeholder',4,0,5,0,250),(19,'placeholder','placeholder',5,5,0,0,250),(20,'placeholder','placeholder',5,0,5,0,250),(21,'placeholder','placeholder',5,0,0,5,250),(22,'placeholder','placeholder',5,4,0,4,300),(23,'placeholder','placeholder',5,0,6,0,300),(24,'placeholder','placeholder',6,6,0,0,300),(25,'placeholder','placeholder',6,0,6,0,300),(26,'placeholder','placeholder',6,0,0,6,300),(27,'placeholder','placeholder',6,5,0,5,350),(28,'placeholder','placeholder',6,0,7,0,350),(29,'placeholder','placeholder',7,7,0,0,350),(30,'placeholder','placeholder',7,0,7,0,350),(31,'placeholder','placeholder',7,0,0,7,350),(32,'placeholder','placeholder',7,6,0,6,400),(33,'placeholder','placeholder',7,0,8,0,400),(34,'placeholder','placeholder',8,8,0,0,400),(35,'placeholder','placeholder',8,0,8,0,400),(36,'placeholder','placeholder',8,0,0,8,400),(37,'placeholder','placeholder',8,7,0,7,450),(38,'placeholder','placeholder',8,0,9,0,450),(39,'placeholder','placeholder',9,9,0,0,450),(40,'placeholder','placeholder',9,0,9,0,450),(41,'placeholder','placeholder',9,0,0,9,450),(42,'placeholder','placeholder',9,8,0,8,500),(43,'placeholder','placeholder',9,0,10,0,500),(44,'placeholder','placeholder',10,10,0,0,500),(45,'placeholder','placeholder',10,0,10,0,500),(46,'placeholder','placeholder',10,0,0,10,500),(47,'placeholder','placeholder',10,9,0,9,550),(48,'placeholder','placeholder',10,0,11,0,550),(49,'Sceptre of Smiting','/kill',10,10,10,10,1000);
/*!40000 ALTER TABLE `weapons` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-24 15:06:37
