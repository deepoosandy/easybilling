-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.21 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for myshop
CREATE DATABASE IF NOT EXISTS `myshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `myshop`;

-- Dumping structure for table myshop.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_date` datetime NOT NULL,
  `bill_to` varchar(2000) DEFAULT NULL,
  `billing_phone_number` varchar(12) DEFAULT NULL,
  `billing_address` varchar(4000) DEFAULT NULL,
  `bill_total_amount` varchar(4000) NOT NULL,
  `totol_invoice_generated` int NOT NULL DEFAULT '0',
  `billed_item_count` int NOT NULL,
  `invoice_number` varchar(200) NOT NULL,
  PRIMARY KEY (`invoice_number`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.item_details_master
CREATE TABLE IF NOT EXISTS `item_details_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_code` varchar(200) NOT NULL,
  `item_name` varchar(2000) NOT NULL,
  `item_description` varchar(2000) DEFAULT NULL,
  `up_loaded_date` datetime NOT NULL,
  PRIMARY KEY (`item_code`),
  UNIQUE KEY `item_code` (`item_code`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.item_of_invoice
CREATE TABLE IF NOT EXISTS `item_of_invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_measurement_code` varchar(10) NOT NULL,
  `item_code` varchar(200) NOT NULL,
  `invocie_number` varchar(400) NOT NULL,
  `selling_date` datetime NOT NULL,
  `item_unit_price` double NOT NULL,
  `item_quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__invoice` (`invocie_number`),
  CONSTRAINT `FK__invoice` FOREIGN KEY (`invocie_number`) REFERENCES `invoice` (`invoice_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.measurement_master
CREATE TABLE IF NOT EXISTS `measurement_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `measurement_desc` varchar(2000) DEFAULT NULL,
  `measurement_code` varchar(50) NOT NULL,
  `measurement_name` varchar(50) NOT NULL,
  `measuement_unit_consist_of` bigint NOT NULL,
  `calculation_formula` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `measurement_code` (`measurement_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.shop_details
CREATE TABLE IF NOT EXISTS `shop_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(200) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `shop_number` varchar(50) DEFAULT NULL,
  `shop_address` varchar(1000) NOT NULL,
  `shop_type` varchar(200) DEFAULT NULL,
  `shop_code` varchar(200) DEFAULT NULL,
  `isactive` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_shop_number` (`user_id`,`shop_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.shop_details_master
CREATE TABLE IF NOT EXISTS `shop_details_master` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_gst_number` varchar(100) NOT NULL,
  `shop_address_1` varchar(1000) NOT NULL,
  `state` varchar(100) NOT NULL,
  `District` varchar(100) NOT NULL,
  `phone_number_1` varchar(20) NOT NULL,
  `phone_number_2` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `shop_name` varchar(2000) NOT NULL,
  `shop_description` varchar(2000) NOT NULL,
  `pincode` varchar(10) NOT NULL,
  `bank_account_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bank_account_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bank_ifsc_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `invoice_prefix` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.shop_user
CREATE TABLE IF NOT EXISTS `shop_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `first_name` varchar(500) NOT NULL,
  `last_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table myshop.unit_price_t
CREATE TABLE IF NOT EXISTS `unit_price_t` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `unit_price` double NOT NULL,
  `eff_dt` datetime NOT NULL,
  `thru_dt` datetime NOT NULL,
  `measurement_unit` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__item_details_master` (`item_code`) USING BTREE,
  CONSTRAINT `FK__item_details_master` FOREIGN KEY (`item_code`) REFERENCES `item_details_master` (`item_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
