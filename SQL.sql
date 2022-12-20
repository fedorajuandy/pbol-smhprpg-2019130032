-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.7.33 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_smhprpg
DROP DATABASE IF EXISTS `db_smhprpg`;
CREATE DATABASE IF NOT EXISTS `db_smhprpg` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `db_smhprpg`;

-- Dumping structure for table db_smhprpg.base_stats
DROP TABLE IF EXISTS `base_stats`;
CREATE TABLE IF NOT EXISTS `base_stats` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `abbrev` varchar(5) NOT NULL,
  `name` varchar(25) NOT NULL,
  `des` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.base_stats: ~8 rows (approximately)
/*!40000 ALTER TABLE `base_stats` DISABLE KEYS */;
INSERT INTO `base_stats` (`id`, `abbrev`, `name`, `des`) VALUES
	(1, 'STR', 'strength', 'Measures physical power and carrying capacity.'),
	(2, 'CON', 'constitution', 'Measures endurance, stamina, and good health.'),
	(3, 'DEX', 'dexterity', 'Measures agility, balance, coordination, and reflexes.'),
	(4, 'INT', 'intelligence', 'Measures deductive reasoning, cognition, knowledge, memory, logic, and rationality.'),
	(5, 'WIS', 'wisdom', 'Measures self-awareness, common sense, restraint, perception, and insight'),
	(6, 'CHA', 'charisma', 'Measures force of personality, persuasiveness, leadership, and successful planning.'),
	(7, 'LUC', 'luck', 'Measures the chances of happentances, usually the matter of successes and failures.');
/*!40000 ALTER TABLE `base_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.base_to_battle_stats
DROP TABLE IF EXISTS `base_to_battle_stats`;
CREATE TABLE IF NOT EXISTS `base_to_battle_stats` (
  `base_stat_id` int(10) NOT NULL,
  `battle_stat_id` int(10) NOT NULL,
  `scale` double NOT NULL DEFAULT '1',
  KEY `FK_basetobattlestats_basestatid` (`base_stat_id`),
  KEY `FK_basetobattlestats_battlestatid` (`battle_stat_id`),
  CONSTRAINT `FK_basetobattlestats_basestatid` FOREIGN KEY (`base_stat_id`) REFERENCES `base_stats` (`id`),
  CONSTRAINT `FK_basetobattlestats_battlestatid` FOREIGN KEY (`battle_stat_id`) REFERENCES `battle_stats` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.base_to_battle_stats: ~10 rows (approximately)
/*!40000 ALTER TABLE `base_to_battle_stats` DISABLE KEYS */;
INSERT INTO `base_to_battle_stats` (`base_stat_id`, `battle_stat_id`, `scale`) VALUES
	(1, 1, 1),
	(1, 2, 1),
	(2, 5, 1),
	(2, 2, 1),
	(3, 1, 0.5),
	(3, 7, 1),
	(4, 3, 1),
	(4, 6, 0.5),
	(5, 6, 1),
	(5, 3, 0.5);
/*!40000 ALTER TABLE `base_to_battle_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.battle_stats
DROP TABLE IF EXISTS `battle_stats`;
CREATE TABLE IF NOT EXISTS `battle_stats` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `abbrev` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `des` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.battle_stats: ~6 rows (approximately)
/*!40000 ALTER TABLE `battle_stats` DISABLE KEYS */;
INSERT INTO `battle_stats` (`id`, `abbrev`, `name`, `des`) VALUES
	(1, 'PATK', 'physical attack', 'Adds physical damage using muscle and weapons.'),
	(2, 'PDEF', 'physical defence', 'Reduces physical damage taken, like blocking incoming attacks.'),
	(3, 'MATK', 'magic attack', 'Adds magic damage using magic type attacks.'),
	(4, 'MDEF', 'magic defence', 'Reduces taken magic damages.'),
	(5, 'HP', 'health point', 'Health point of a character; or a life point that once it hits zero means the character dies.'),
	(6, 'MP', 'magic point', 'Magic point for using magic type skills such as spells.'),
	(7, 'SPD', 'speed', 'The speed of taking actions, evasions, and running aways.');
/*!40000 ALTER TABLE `battle_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.classes
DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parentclass_id` int(10) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_classes_subclassid` (`parentclass_id`) USING BTREE,
  CONSTRAINT `FK_classes_parentclassid` FOREIGN KEY (`parentclass_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.classes: ~11 rows (approximately)
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` (`id`, `parentclass_id`, `name`, `des`) VALUES
	(1, NULL, 'swordsman', 'A person who uses swords or other basic weapons to fight. Quite balanced at physical attributes.'),
	(2, 1, 'knight', 'A person who uses a sword and a shield to protect allies. Has high physical defence and blocking rate.'),
	(3, 1, 'fighter', 'A person who deals damage with bare hands or knuckles. Quite fast and has low defence.'),
	(4, NULL, 'thief', 'A person who can steals others\' belongings. Fast.'),
	(5, 4, 'ninja', 'A person who excels in subterfudge. Has high evasion rate and speed.'),
	(6, NULL, 'mage', 'A person who uses basic magic spells with balanced magical attributes.'),
	(7, 6, 'elementalist', 'A mage who uses elemental skills. Sometimes can be found using weapons as mediums.'),
	(8, 6, 'summoner', 'A mage who summons other creatures to assist them in battles and other stuff.'),
	(9, NULL, 'archer', 'A person who uses ranged attacks such as bow or bowgun. Has high dexterity but low defences.'),
	(10, 9, 'gunner', 'A person who uses various guns from afar.'),
	(11, 4, 'assassin', 'A person who sneaks around the enemies and oftenly deals critical damages. Has high speed and dodging rate.');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.class_base_stats
DROP TABLE IF EXISTS `class_base_stats`;
CREATE TABLE IF NOT EXISTS `class_base_stats` (
  `class_id` int(10) NOT NULL,
  `base_stat_id` int(10) NOT NULL,
  `levelup_val` int(2) NOT NULL DEFAULT '0',
  KEY `FK_classbasestats_classid` (`class_id`),
  KEY `FK_classbasestats_basestatid` (`base_stat_id`),
  CONSTRAINT `FK_classbasestats_basestatid` FOREIGN KEY (`base_stat_id`) REFERENCES `base_stats` (`id`),
  CONSTRAINT `FK_classbasestats_classid` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.class_base_stats: ~77 rows (approximately)
/*!40000 ALTER TABLE `class_base_stats` DISABLE KEYS */;
INSERT INTO `class_base_stats` (`class_id`, `base_stat_id`, `levelup_val`) VALUES
	(1, 1, 3),
	(1, 2, 3),
	(1, 3, 2),
	(1, 4, 1),
	(1, 5, 1),
	(1, 6, 2),
	(1, 7, 0),
	(2, 1, 3),
	(2, 2, 4),
	(2, 3, 2),
	(2, 4, 1),
	(2, 5, 1),
	(2, 6, 2),
	(2, 7, 0),
	(3, 1, 4),
	(3, 2, 3),
	(3, 3, 2),
	(3, 4, 1),
	(3, 5, 1),
	(3, 6, 2),
	(3, 7, 0),
	(4, 1, 2),
	(4, 2, 2),
	(4, 3, 4),
	(4, 4, 1),
	(4, 5, 1),
	(4, 6, 2),
	(4, 7, 0),
	(5, 1, 2),
	(5, 2, 2),
	(5, 3, 5),
	(5, 4, 1),
	(5, 2, 1),
	(5, 5, 2),
	(5, 7, 0),
	(6, 1, 1),
	(6, 2, 1),
	(6, 3, 2),
	(6, 4, 3),
	(6, 5, 3),
	(6, 6, 2),
	(6, 7, 0),
	(7, 1, 1),
	(7, 2, 1),
	(7, 3, 2),
	(7, 4, 4),
	(7, 5, 3),
	(7, 6, 2),
	(7, 7, 0),
	(8, 1, 1),
	(8, 2, 1),
	(8, 3, 2),
	(8, 4, 3),
	(8, 2, 4),
	(8, 6, 2),
	(8, 7, 0),
	(9, 1, 3),
	(9, 2, 2),
	(9, 3, 3),
	(9, 4, 1),
	(9, 5, 1),
	(9, 6, 2),
	(9, 7, 0),
	(10, 1, 3),
	(10, 2, 2),
	(10, 3, 4),
	(10, 4, 1),
	(10, 5, 1),
	(10, 6, 2),
	(10, 7, 0),
	(11, 1, 3),
	(11, 2, 2),
	(11, 3, 4),
	(11, 4, 1),
	(11, 5, 1),
	(11, 6, 2),
	(11, 7, 0);
/*!40000 ALTER TABLE `class_base_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.class_battle_stats
DROP TABLE IF EXISTS `class_battle_stats`;
CREATE TABLE IF NOT EXISTS `class_battle_stats` (
  `class_id` int(10) NOT NULL,
  `battle_stat_id` int(10) NOT NULL,
  `scale` double NOT NULL DEFAULT '1',
  KEY `FK_classbattlestats_classid` (`class_id`),
  KEY `FK_classbattlestats_battleid` (`battle_stat_id`),
  CONSTRAINT `FK_classbattlestats_battleid` FOREIGN KEY (`battle_stat_id`) REFERENCES `battle_stats` (`id`),
  CONSTRAINT `FK_classbattlestats_classid` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.class_battle_stats: ~73 rows (approximately)
/*!40000 ALTER TABLE `class_battle_stats` DISABLE KEYS */;
INSERT INTO `class_battle_stats` (`class_id`, `battle_stat_id`, `scale`) VALUES
	(1, 2, 1),
	(1, 3, 0.5),
	(1, 4, 0.5),
	(1, 5, 1),
	(1, 6, 1),
	(1, 7, 0.75),
	(2, 1, 1),
	(2, 2, 2),
	(2, 4, 0.5),
	(2, 5, 2),
	(2, 6, 1),
	(2, 7, 0.75),
	(3, 1, 1),
	(3, 2, 0.75),
	(3, 3, 0.5),
	(3, 4, 0.5),
	(3, 5, 1.5),
	(3, 6, 1),
	(3, 7, 1.25),
	(2, 3, 0.5),
	(4, 1, 1),
	(4, 2, 1),
	(4, 3, 0.5),
	(4, 4, 0.5),
	(4, 5, 1),
	(4, 6, 1),
	(4, 7, 1.5),
	(5, 1, 1),
	(5, 2, 1),
	(5, 3, 1),
	(5, 4, 1),
	(5, 5, 1),
	(5, 6, 1),
	(5, 7, 2),
	(6, 1, 0.5),
	(6, 2, 0.5),
	(6, 3, 1),
	(6, 4, 1),
	(6, 5, 1),
	(6, 6, 1),
	(6, 7, 0.75),
	(7, 1, 0.5),
	(7, 2, 0.5),
	(7, 3, 2),
	(7, 4, 2),
	(7, 5, 1),
	(7, 6, 1),
	(7, 7, 0.75),
	(8, 1, 0.5),
	(8, 2, 0.5),
	(8, 3, 2),
	(8, 4, 2),
	(8, 5, 1),
	(8, 6, 1),
	(8, 7, 0.75),
	(9, 1, 1),
	(9, 2, 0.75),
	(9, 3, 0.75),
	(9, 4, 0.75),
	(9, 5, 1),
	(9, 6, 1),
	(9, 7, 1.5),
	(10, 1, 1.5),
	(10, 2, 0.75),
	(10, 3, 1),
	(10, 4, 0.75),
	(10, 5, 1),
	(10, 6, 1),
	(10, 7, 1.5),
	(11, 1, 1.75),
	(11, 2, 1.25),
	(11, 3, 1),
	(11, 4, 1),
	(11, 5, 1),
	(11, 6, 1),
	(11, 7, 2.5);
/*!40000 ALTER TABLE `class_battle_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.class_skills
DROP TABLE IF EXISTS `class_skills`;
CREATE TABLE IF NOT EXISTS `class_skills` (
  `class_id` int(10) NOT NULL,
  `skill_id` int(10) NOT NULL,
  KEY `FK_classskills_classid` (`class_id`),
  KEY `FK_classskills_skillid` (`skill_id`),
  CONSTRAINT `FK_classskills_classid` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FK_classskills_skillid` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.class_skills: ~13 rows (approximately)
/*!40000 ALTER TABLE `class_skills` DISABLE KEYS */;
INSERT INTO `class_skills` (`class_id`, `skill_id`) VALUES
	(4, 1),
	(7, 7),
	(1, 1),
	(3, 1),
	(9, 1),
	(6, 8),
	(6, 2),
	(2, 5),
	(10, 6),
	(4, 9),
	(8, 10),
	(11, 12),
	(5, 12);
/*!40000 ALTER TABLE `class_skills` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.class_traits
DROP TABLE IF EXISTS `class_traits`;
CREATE TABLE IF NOT EXISTS `class_traits` (
  `class_id` int(10) NOT NULL,
  `trait_id` int(10) NOT NULL,
  KEY `FK_classtraits_classid` (`class_id`),
  KEY `FK_classtraits_traitid` (`trait_id`),
  CONSTRAINT `FK_classtraits_classid` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FK_classtraits_traitid` FOREIGN KEY (`trait_id`) REFERENCES `traits` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.class_traits: ~7 rows (approximately)
/*!40000 ALTER TABLE `class_traits` DISABLE KEYS */;
INSERT INTO `class_traits` (`class_id`, `trait_id`) VALUES
	(3, 5),
	(4, 7),
	(7, 10),
	(8, 10),
	(10, 2),
	(1, 6);
/*!40000 ALTER TABLE `class_traits` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.effects
DROP TABLE IF EXISTS `effects`;
CREATE TABLE IF NOT EXISTS `effects` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `des` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.effects: ~6 rows (approximately)
/*!40000 ALTER TABLE `effects` DISABLE KEYS */;
INSERT INTO `effects` (`id`, `name`, `des`) VALUES
	(1, 'normal', 'Default effect on a character.'),
	(2, 'poisoned', 'Deals constant damage until treated with magic or antidote.'),
	(3, 'sleep', 'Puts a character to sleep, preventing them from acting. Taking damage will sometimes wake up the sleeping character.'),
	(4, 'burnt', 'Lits character on fire; taking constant damage until flames are extinguished.'),
	(5, 'frozen', 'Encases character in ice, stopping them from acting.'),
	(6, 'paralised', 'Makes character unable to act.');
/*!40000 ALTER TABLE `effects` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.heroes
DROP TABLE IF EXISTS `heroes`;
CREATE TABLE IF NOT EXISTS `heroes` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `race_id` int(10) NOT NULL,
  `curr_class_id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL DEFAULT '0',
  `des` varchar(255) DEFAULT '',
  `exp` int(15) NOT NULL DEFAULT '0',
  `stat_points` int(2) NOT NULL DEFAULT '0',
  `skill_points` int(1) NOT NULL DEFAULT '0',
  `image` varchar(255) NOT NULL DEFAULT '',
  `user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_heroes_raceid` (`race_id`),
  KEY `FK_heroes_currclassid` (`curr_class_id`),
  KEY `FK_heroes_userid` (`user_id`),
  CONSTRAINT `FK_heroes_currclassid` FOREIGN KEY (`curr_class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FK_heroes_raceid` FOREIGN KEY (`race_id`) REFERENCES `races` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.heroes: ~11 rows (approximately)
/*!40000 ALTER TABLE `heroes` DISABLE KEYS */;
INSERT INTO `heroes` (`id`, `race_id`, `curr_class_id`, `name`, `gender`, `des`, `exp`, `stat_points`, `skill_points`, `image`, `user_id`) VALUES
	(1, 9, 3, 'Badortiz', 'female', 'A kind orc who despites her limitation in intelligence, uses her strenght to help people.', 755, 0, 0, '', 2),
	(2, 6, 4, 'Crookedke', 'male', 'A petty thief who seeks revenge for some time.', 2449, 0, 0, '', 1),
	(3, 2, 11, 'Helffin Shson', 'male', 'A man who has lost everything and entered the dark side of Y Town since then.', 10938, 0, 0, 'src/pbol/smhprpg/pkg2019130032/imgs/HumanM.png', 2),
	(4, 4, 9, 'Smtran', 'male', 'Some says there would be some arrows aimed at visitors in a dwarf town from unknown directions...', 57, 0, 0, 'src/pbol/smhprpg/pkg2019130032/imgs/Gnome.png', 1),
	(5, 3, 1, 'Azjohnson', 'male', 'A common elf who loves to fight.', 22, 0, 0, '', 1),
	(6, 1, 2, 'Macat Thomamar', 'male', 'A dwarf who guards the entrance to his home.', 87777, 0, 0, 'src/pbol/smhprpg/pkg2019130032/imgs/Dwarf.png', 2),
	(7, 7, 8, 'Kelleyolly Benorty Magicturner', 'female', 'A mage who has just started her journey as a summoner. Hint: she is bad at it.', 1588, 0, 0, 'src/pbol/smhprpg/pkg2019130032/imgs/Elf.png', 2),
	(8, 5, 5, 'Broommacdonald Harkhoughton', 'female', 'A weird, flashy colorful ninja with equally weird name.', 5249, 0, 0, 'src/pbol/smhprpg/pkg2019130032/imgs/Human.png', 2),
	(9, 8, 7, 'Morganizzt Do\'bryant', 'female', 'Some rumors in Z City claims there is a skilled elementalist somewhere hidden near there.', 255567, 0, 0, '', 1),
	(10, 10, 10, 'Hayesette', 'female', 'A professional hitman from unknown origin.', 904838, 0, 0, '', 2);
/*!40000 ALTER TABLE `heroes` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.hero_base_stats
DROP TABLE IF EXISTS `hero_base_stats`;
CREATE TABLE IF NOT EXISTS `hero_base_stats` (
  `hero_id` int(10) NOT NULL,
  `base_stat_id` int(10) NOT NULL,
  `val` int(10) NOT NULL,
  KEY `FK_herostats_heroid` (`hero_id`),
  KEY `FK_herostats_statid` (`base_stat_id`) USING BTREE,
  CONSTRAINT `FK_herobasestats_basestatid` FOREIGN KEY (`base_stat_id`) REFERENCES `base_stats` (`id`),
  CONSTRAINT `FK_herostats_heroid` FOREIGN KEY (`hero_id`) REFERENCES `heroes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.hero_base_stats: ~70 rows (approximately)
/*!40000 ALTER TABLE `hero_base_stats` DISABLE KEYS */;
INSERT INTO `hero_base_stats` (`hero_id`, `base_stat_id`, `val`) VALUES
	(1, 1, 11),
	(1, 2, 10),
	(1, 3, 7),
	(1, 4, 5),
	(1, 5, 5),
	(1, 6, 10),
	(1, 7, 5),
	(2, 1, 7),
	(2, 2, 6),
	(2, 3, 12),
	(2, 4, 6),
	(2, 5, 5),
	(2, 6, 7),
	(2, 7, 5),
	(3, 1, 7),
	(3, 2, 7),
	(3, 3, 11),
	(3, 4, 8),
	(3, 5, 7),
	(3, 6, 8),
	(3, 7, 5),
	(4, 1, 9),
	(4, 2, 9),
	(4, 3, 9),
	(4, 4, 6),
	(4, 5, 8),
	(4, 6, 7),
	(4, 7, 5),
	(5, 1, 12),
	(5, 2, 10),
	(5, 3, 5),
	(5, 4, 5),
	(5, 2, 5),
	(5, 5, 9),
	(5, 7, 6),
	(6, 1, 12),
	(6, 2, 12),
	(6, 3, 5),
	(6, 4, 5),
	(6, 5, 8),
	(6, 6, 6),
	(6, 7, 5),
	(7, 1, 5),
	(7, 2, 7),
	(7, 3, 7),
	(7, 4, 10),
	(7, 5, 10),
	(7, 6, 9),
	(7, 7, 5),
	(8, 1, 8),
	(8, 2, 8),
	(8, 3, 10),
	(8, 4, 7),
	(8, 2, 7),
	(8, 6, 8),
	(8, 7, 5),
	(9, 1, 5),
	(9, 2, 5),
	(9, 3, 5),
	(9, 4, 12),
	(9, 5, 12),
	(9, 6, 9),
	(9, 7, 5),
	(10, 1, 8),
	(10, 2, 8),
	(10, 3, 12),
	(10, 4, 5),
	(10, 5, 5),
	(10, 6, 9),
	(10, 7, 5);
/*!40000 ALTER TABLE `hero_base_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.hero_classes
DROP TABLE IF EXISTS `hero_classes`;
CREATE TABLE IF NOT EXISTS `hero_classes` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `hero_id` int(10) NOT NULL,
  `class_id` int(10) NOT NULL,
  `mastery_lv` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_heroclasses_heroid` (`hero_id`),
  KEY `FK_heroclasses_classid` (`class_id`),
  CONSTRAINT `FK_heroclasses_classid` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FK_heroclasses_heroid` FOREIGN KEY (`hero_id`) REFERENCES `heroes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.hero_classes: ~19 rows (approximately)
/*!40000 ALTER TABLE `hero_classes` DISABLE KEYS */;
INSERT INTO `hero_classes` (`id`, `hero_id`, `class_id`, `mastery_lv`) VALUES
	(1, 1, 1, 20),
	(2, 2, 4, 34),
	(3, 3, 4, 20),
	(4, 4, 9, 0),
	(5, 5, 1, 0),
	(6, 6, 1, 25),
	(7, 7, 6, 30),
	(8, 8, 4, 27),
	(9, 9, 6, 25),
	(10, 10, 4, 25),
	(11, 1, 3, 2),
	(12, 3, 11, 30),
	(13, 6, 3, 15),
	(14, 6, 2, 30),
	(15, 7, 8, 0),
	(16, 8, 5, 15),
	(17, 9, 7, 58),
	(18, 10, 9, 20),
	(19, 10, 10, 41);
/*!40000 ALTER TABLE `hero_classes` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.hero_effects
DROP TABLE IF EXISTS `hero_effects`;
CREATE TABLE IF NOT EXISTS `hero_effects` (
  `hero_id` int(10) NOT NULL,
  `effect_id` int(10) NOT NULL DEFAULT '1',
  `duration_left` int(10) NOT NULL DEFAULT '0',
  KEY `FK_heroeffects_heroid` (`hero_id`),
  KEY `FK_heroeffects_effectid` (`effect_id`),
  CONSTRAINT `FK_heroeffects_effectid` FOREIGN KEY (`effect_id`) REFERENCES `effects` (`id`),
  CONSTRAINT `FK_heroeffects_heroid` FOREIGN KEY (`hero_id`) REFERENCES `heroes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.hero_effects: ~11 rows (approximately)
/*!40000 ALTER TABLE `hero_effects` DISABLE KEYS */;
INSERT INTO `hero_effects` (`hero_id`, `effect_id`, `duration_left`) VALUES
	(1, 1, 0),
	(2, 1, 0),
	(3, 4, 2),
	(4, 2, 3),
	(5, 1, 0),
	(6, 1, 0),
	(7, 5, 5),
	(8, 1, 0),
	(9, 3, 10),
	(10, 1, 0),
	(4, 6, 1),
	(7, 2, 2);
/*!40000 ALTER TABLE `hero_effects` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.hero_skills
DROP TABLE IF EXISTS `hero_skills`;
CREATE TABLE IF NOT EXISTS `hero_skills` (
  `hero_class_id` int(10) NOT NULL,
  `skill_id` int(10) NOT NULL,
  `lv` int(2) NOT NULL DEFAULT '1',
  KEY `FK_heroskills_heroclassid` (`hero_class_id`),
  KEY `FK_heroskills_skillid` (`skill_id`),
  CONSTRAINT `FK_heroskills_heroclassid` FOREIGN KEY (`hero_class_id`) REFERENCES `hero_classes` (`id`),
  CONSTRAINT `FK_heroskills_skillid` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.hero_skills: ~24 rows (approximately)
/*!40000 ALTER TABLE `hero_skills` DISABLE KEYS */;
INSERT INTO `hero_skills` (`hero_class_id`, `skill_id`, `lv`) VALUES
	(1, 1, 1),
	(1, 1, 10),
	(2, 1, 7),
	(2, 9, 6),
	(3, 1, 7),
	(3, 9, 7),
	(4, 1, 2),
	(5, 1, 1),
	(6, 1, 3),
	(7, 2, 5),
	(7, 8, 5),
	(8, 1, 8),
	(8, 9, 5),
	(9, 2, 5),
	(9, 8, 2),
	(10, 1, 8),
	(10, 9, 3),
	(11, 11, 6),
	(12, 12, 7),
	(13, 11, 9),
	(14, 4, 5),
	(15, 10, 7),
	(16, 12, 8),
	(17, 7, 3),
	(18, 1, 1),
	(19, 12, 4);
/*!40000 ALTER TABLE `hero_skills` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.hero_traits
DROP TABLE IF EXISTS `hero_traits`;
CREATE TABLE IF NOT EXISTS `hero_traits` (
  `hero_id` int(10) NOT NULL,
  `trait_id` int(10) NOT NULL,
  KEY `FK_herotraits_heroid` (`hero_id`),
  KEY `FK_herotraits_traitid` (`trait_id`),
  CONSTRAINT `FK_herotraits_heroid` FOREIGN KEY (`hero_id`) REFERENCES `heroes` (`id`),
  CONSTRAINT `FK_herotraits_traitid` FOREIGN KEY (`trait_id`) REFERENCES `traits` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.hero_traits: ~10 rows (approximately)
/*!40000 ALTER TABLE `hero_traits` DISABLE KEYS */;
INSERT INTO `hero_traits` (`hero_id`, `trait_id`) VALUES
	(3, 2),
	(3, 7),
	(2, 8),
	(1, 3),
	(1, 6),
	(4, 8),
	(6, 2),
	(9, 10),
	(9, 1),
	(10, 2);
/*!40000 ALTER TABLE `hero_traits` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.level
DROP TABLE IF EXISTS `level`;
CREATE TABLE IF NOT EXISTS `level` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `max_lv` int(5) DEFAULT '100',
  `base_exp` int(10) NOT NULL DEFAULT '1000',
  `scale` double NOT NULL DEFAULT '1.25',
  `stat_points` int(2) NOT NULL DEFAULT '0',
  `skill_points` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.level: ~0 rows (approximately)
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` (`id`, `max_lv`, `base_exp`, `scale`, `stat_points`, `skill_points`) VALUES
	(1, 100, 1000, 1.25, 5, 1);
/*!40000 ALTER TABLE `level` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.races
DROP TABLE IF EXISTS `races`;
CREATE TABLE IF NOT EXISTS `races` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parentrace_id` int(10) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `des` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_races_parentraceid` (`parentrace_id`),
  CONSTRAINT `FK_races_parentraceid` FOREIGN KEY (`parentrace_id`) REFERENCES `races` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.races: ~11 rows (approximately)
/*!40000 ALTER TABLE `races` DISABLE KEYS */;
INSERT INTO `races` (`id`, `parentrace_id`, `name`, `des`) VALUES
	(1, NULL, 'dwarf', 'Dwarves favor earth tones in their clothing and prefer simple and functional garb. The skin can be very dark, but it is always some shade of tan or brown. Hair color can be black, gray, or brown.'),
	(2, NULL, 'human', 'Humans are the most common race with no special bonuses or penalties.'),
	(3, NULL, 'elf', 'Elves live on fruits and grains, though they occasionally hunt for fresh meat. Elves prefer colorful clothes, usually with a green-and-gray cloak that blends well with the colors of the forest.'),
	(4, NULL, 'gnome', 'Gnomes\' skin color ranges from dark tan to woody brown, their hair is fair, and their eyes can be any shade of blue. Gnomes generally wear leather or earth tones, though they decorate their clothes with intricate stitching or fine jewelry.'),
	(5, NULL, 'halfling', 'Halflings have brown or black eyes. Unlike members of most races, they prefer actual comfort to shows of wealth.'),
	(6, 9, 'half-orc', 'Orc-human crossbreeds can be found in either orc or human society (where their status varies according to local sentiments), or in communities of their own. Half-orcs usually inherit a good blend of the physical characteristics of their parents.'),
	(7, 3, 'aquatic elf', 'Also called sea elves, these creatures are waterbreathing cousins to land-dwelling elves.'),
	(8, 3, 'drow', 'Also known as dark elves, drow are a depraved and evil subterranean offshoot.'),
	(9, NULL, 'orc', 'An orc’s hair usually is black. It has lupine ears and reddish eyes. Orcs prefer wearing vivid colors that many humans would consider unpleasant, such as blood red, mustard yellow, yellow-green, and deep purple.'),
	(10, 4, 'svirfneblin', 'Also called deep gnomes, svirfneblin are said to dwell in great cities deep underground.'),
	(11, 11, 'the cursed', 'The ancient ones reside in Unknown whose domain are destiny. They have silver hair and eyes. The main characteristic of them is the emotionless nature and duty bound actions.');
/*!40000 ALTER TABLE `races` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.race_base_stats
DROP TABLE IF EXISTS `race_base_stats`;
CREATE TABLE IF NOT EXISTS `race_base_stats` (
  `race_id` int(10) NOT NULL,
  `base_stat_id` int(10) NOT NULL,
  `val` int(10) NOT NULL DEFAULT '0',
  KEY `FK_racebasestats_raceid` (`race_id`),
  KEY `FK_racebasestats_basestatid` (`base_stat_id`),
  CONSTRAINT `FK_racebasestats_basestatid` FOREIGN KEY (`base_stat_id`) REFERENCES `base_stats` (`id`),
  CONSTRAINT `FK_racebasestats_raceid` FOREIGN KEY (`race_id`) REFERENCES `races` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.race_base_stats: ~70 rows (approximately)
/*!40000 ALTER TABLE `race_base_stats` DISABLE KEYS */;
INSERT INTO `race_base_stats` (`race_id`, `base_stat_id`, `val`) VALUES
	(1, 1, 0),
	(1, 2, 2),
	(1, 3, 0),
	(1, 4, 0),
	(1, 5, 0),
	(1, 7, 0),
	(2, 1, 0),
	(2, 2, 0),
	(2, 3, 0),
	(2, 4, 1),
	(2, 5, 0),
	(1, 6, -2),
	(2, 6, 0),
	(2, 7, 0),
	(3, 1, 0),
	(3, 2, -2),
	(3, 3, 2),
	(3, 4, 0),
	(3, 5, 0),
	(3, 6, 0),
	(3, 7, 0),
	(4, 1, -2),
	(4, 2, 2),
	(4, 3, 0),
	(4, 4, 0),
	(4, 5, 0),
	(4, 6, 0),
	(4, 7, 0),
	(5, 1, -2),
	(5, 2, 0),
	(5, 3, 2),
	(5, 4, 0),
	(5, 5, 0),
	(5, 6, 0),
	(5, 7, 0),
	(6, 1, 2),
	(6, 2, 0),
	(6, 3, 0),
	(6, 4, -2),
	(6, 5, 0),
	(6, 6, -2),
	(6, 7, 0),
	(7, 1, 0),
	(7, 2, 2),
	(7, 3, 2),
	(7, 4, -2),
	(7, 5, 0),
	(7, 6, -2),
	(7, 7, 0),
	(8, 1, 0),
	(8, 2, 0),
	(8, 3, 0),
	(8, 4, 2),
	(8, 5, 0),
	(8, 6, 2),
	(8, 7, 0),
	(9, 1, 4),
	(9, 2, 2),
	(9, 3, 0),
	(9, 4, -2),
	(9, 5, -2),
	(9, 6, -2),
	(9, 7, 0),
	(10, 1, 0),
	(10, 2, 2),
	(10, 3, 0),
	(10, 4, 0),
	(10, 5, 0),
	(10, 6, -2),
	(10, 7, 0);
/*!40000 ALTER TABLE `race_base_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.race_traits
DROP TABLE IF EXISTS `race_traits`;
CREATE TABLE IF NOT EXISTS `race_traits` (
  `race_id` int(10) NOT NULL,
  `trait_id` int(10) NOT NULL,
  KEY `FK_racetraits_raceid` (`race_id`),
  KEY `FK_racetraits_traitid` (`trait_id`),
  CONSTRAINT `FK_racetraits_raceid` FOREIGN KEY (`race_id`) REFERENCES `races` (`id`),
  CONSTRAINT `FK_racetraits_traitid` FOREIGN KEY (`trait_id`) REFERENCES `traits` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.race_traits: ~9 rows (approximately)
/*!40000 ALTER TABLE `race_traits` DISABLE KEYS */;
INSERT INTO `race_traits` (`race_id`, `trait_id`) VALUES
	(3, 1),
	(3, 2),
	(7, 6),
	(1, 2),
	(4, 3),
	(8, 10),
	(9, 8),
	(6, 6),
	(5, 7);
/*!40000 ALTER TABLE `race_traits` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.skills
DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `des` varchar(255) DEFAULT '',
  `mp_cost` int(5) DEFAULT '0',
  `dmg` double DEFAULT NULL,
  `success_rate` double NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.skills: ~11 rows (approximately)
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` (`id`, `name`, `des`, `mp_cost`, `dmg`, `success_rate`) VALUES
	(1, 'attack', 'Attacks an enemy using equiped weapon(s) or bare hands.', 0, 0.2, 100),
	(2, 'magic attack', 'Attacks an anemy using basic spell, only possible if the character has at least 1 proficiency in mage class.', 0, 0.2, 100),
	(4, 'horisontal slash', 'Attacks several enemies in line.', 0, 0.4, 100),
	(5, 'guard', 'Raise PDEF temporarily.', 0, 0, 100),
	(6, 'snipe', 'Hits one enemy from afar. Has low accuracy but the enemy instantly lost if succeeds.', 0, 0, 15),
	(7, 'fire ball', 'Hits enemy(s) using fire ball(s). Might give enemy(s) burnt effect. Level up skill makes burnt damage higher and more fireballs.', 20, 0.3, 90),
	(8, 'heal all', 'Heals all alies.', 50, 0.5, 100),
	(9, 'steal', 'Tries to steal something from the enemy.', 0, 0, 50),
	(10, 'summon familiar', 'Summons chosen base familiar.', 0, 0, 100),
	(11, 'kick', 'Kicks an enemy.', 0, 0.3, 95),
	(12, 'hide', 'Being invisible for some time.', 0, 0, 100);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.traits
DROP TABLE IF EXISTS `traits`;
CREATE TABLE IF NOT EXISTS `traits` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `des` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.traits: ~11 rows (approximately)
/*!40000 ALTER TABLE `traits` DISABLE KEYS */;
INSERT INTO `traits` (`id`, `name`, `des`) VALUES
	(1, 'absent minded', 'You are fascinated by knowledge and learning and are capable of pursuing complex trains of thought quite quickly. However, your preoccupation with such thoughts makes you a little less aware of your surroundings.'),
	(2, 'focused', 'You can keep your attention on a task despite many distractions; however, events in the background pass you by.'),
	(3, 'honest', 'You are naturally straightforward and sincere. This quality helps you persuade people to your viewpoint, but you have difficulty telling lies and seeing deception in others.'),
	(4, 'illiterate', 'You cannot read, but you have devoted yourself to learning other skills.'),
	(5, 'musclebound', 'You are good at almost everything that requires strength, but less adept than most at tasks that require coordination.'),
	(6, 'polite', 'You are courteous and well spoken.'),
	(7, 'quick', 'You are fast, but less sturdy than average members of your race.'),
	(8, 'reckless', 'You naturally sacrifice accuracy to put more power behind your blows.'),
	(9, 'slow', 'You are slow, but sturdier than average members of your race.'),
	(10, 'spellgifted', 'You have a gift for casting spells from a certain school. Although your spells from this school are more potent than those of other casters, you are not as effective at casting spells from other schools.');
/*!40000 ALTER TABLE `traits` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.trait_base_stats
DROP TABLE IF EXISTS `trait_base_stats`;
CREATE TABLE IF NOT EXISTS `trait_base_stats` (
  `trait_id` int(10) NOT NULL,
  `base_stat_id` int(10) NOT NULL,
  `val` int(10) NOT NULL,
  KEY `FK_traitstats_traitid` (`trait_id`),
  KEY `FK_traitstats_statid` (`base_stat_id`) USING BTREE,
  CONSTRAINT `FK_traitstats_basestatid` FOREIGN KEY (`base_stat_id`) REFERENCES `base_stats` (`id`),
  CONSTRAINT `FK_traitstats_traitid` FOREIGN KEY (`trait_id`) REFERENCES `traits` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.trait_base_stats: ~10 rows (approximately)
/*!40000 ALTER TABLE `trait_base_stats` DISABLE KEYS */;
INSERT INTO `trait_base_stats` (`trait_id`, `base_stat_id`, `val`) VALUES
	(1, 3, -1),
	(1, 4, 3),
	(2, 3, 2),
	(3, 6, 1),
	(4, 4, -2),
	(5, 2, 2),
	(5, 1, 3),
	(6, 6, 3),
	(7, 3, 3),
	(8, 3, -2),
	(9, 3, -2),
	(10, 4, 3);
/*!40000 ALTER TABLE `trait_base_stats` ENABLE KEYS */;

-- Dumping structure for table db_smhprpg.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(128) NOT NULL,
  `role` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table db_smhprpg.users: ~4 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
	(1, 'admin', 'admin', 0),
	(2, 'user0', '12345', 1),
	(3, 'user1', '23456', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
