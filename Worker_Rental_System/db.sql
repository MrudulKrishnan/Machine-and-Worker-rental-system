/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 8.0.22 : Database - m_w_rental
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`m_w_rental` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `m_w_rental`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add login',7,'add_login'),
(26,'Can change login',7,'change_login'),
(27,'Can delete login',7,'delete_login'),
(28,'Can view login',7,'view_login'),
(29,'Can add product',8,'add_product'),
(30,'Can change product',8,'change_product'),
(31,'Can delete product',8,'delete_product'),
(32,'Can view product',8,'view_product'),
(33,'Can add user',9,'add_user'),
(34,'Can change user',9,'change_user'),
(35,'Can delete user',9,'delete_user'),
(36,'Can view user',9,'view_user'),
(37,'Can add workers',10,'add_workers'),
(38,'Can change workers',10,'change_workers'),
(39,'Can delete workers',10,'delete_workers'),
(40,'Can view workers',10,'view_workers'),
(41,'Can add workers request',11,'add_workersrequest'),
(42,'Can change workers request',11,'change_workersrequest'),
(43,'Can delete workers request',11,'delete_workersrequest'),
(44,'Can view workers request',11,'view_workersrequest'),
(45,'Can add skills',12,'add_skills'),
(46,'Can change skills',12,'change_skills'),
(47,'Can delete skills',12,'delete_skills'),
(48,'Can view skills',12,'view_skills'),
(49,'Can add shop',13,'add_shop'),
(50,'Can change shop',13,'change_shop'),
(51,'Can delete shop',13,'delete_shop'),
(52,'Can view shop',13,'view_shop'),
(53,'Can add ratings',14,'add_ratings'),
(54,'Can change ratings',14,'change_ratings'),
(55,'Can delete ratings',14,'delete_ratings'),
(56,'Can view ratings',14,'view_ratings'),
(57,'Can add product request',15,'add_productrequest'),
(58,'Can change product request',15,'change_productrequest'),
(59,'Can delete product request',15,'delete_productrequest'),
(60,'Can view product request',15,'view_productrequest'),
(61,'Can add delivery boy',16,'add_deliveryboy'),
(62,'Can change delivery boy',16,'change_deliveryboy'),
(63,'Can delete delivery boy',16,'delete_deliveryboy'),
(64,'Can view delivery boy',16,'view_deliveryboy'),
(65,'Can add delivery',17,'add_delivery'),
(66,'Can change delivery',17,'change_delivery'),
(67,'Can delete delivery',17,'delete_delivery'),
(68,'Can view delivery',17,'view_delivery'),
(69,'Can add complaint',18,'add_complaint'),
(70,'Can change complaint',18,'change_complaint'),
(71,'Can delete complaint',18,'delete_complaint'),
(72,'Can view complaint',18,'view_complaint'),
(73,'Can add chat',19,'add_chat'),
(74,'Can change chat',19,'change_chat'),
(75,'Can delete chat',19,'delete_chat'),
(76,'Can view chat',19,'view_chat'),
(77,'Can add assign',20,'add_assign'),
(78,'Can change assign',20,'change_assign'),
(79,'Can delete assign',20,'delete_assign'),
(80,'Can view assign',20,'view_assign');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user` */

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(3,'auth','group'),
(2,'auth','permission'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(6,'sessions','session'),
(20,'Worker_Rental_SystemApp','assign'),
(19,'Worker_Rental_SystemApp','chat'),
(18,'Worker_Rental_SystemApp','complaint'),
(17,'Worker_Rental_SystemApp','delivery'),
(16,'Worker_Rental_SystemApp','deliveryboy'),
(7,'Worker_Rental_SystemApp','login'),
(8,'Worker_Rental_SystemApp','product'),
(15,'Worker_Rental_SystemApp','productrequest'),
(14,'Worker_Rental_SystemApp','ratings'),
(13,'Worker_Rental_SystemApp','shop'),
(12,'Worker_Rental_SystemApp','skills'),
(9,'Worker_Rental_SystemApp','user'),
(10,'Worker_Rental_SystemApp','workers'),
(11,'Worker_Rental_SystemApp','workersrequest');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'Worker_Rental_SystemApp','0001_initial','2023-09-23 06:38:38.170414'),
(2,'Worker_Rental_SystemApp','0002_workers_gender','2023-09-23 06:38:42.755438'),
(3,'Worker_Rental_SystemApp','0003_assign_product_request_id','2023-09-23 06:38:54.175280'),
(4,'Worker_Rental_SystemApp','0004_deliveryboy_shop_id','2023-09-23 06:38:58.597901'),
(5,'Worker_Rental_SystemApp','0005_auto_20230923_1152','2023-09-23 06:39:10.297249'),
(6,'Worker_Rental_SystemApp','0006_remove_productrequest_login_id','2023-09-23 06:39:21.621045'),
(7,'Worker_Rental_SystemApp','0007_productrequest_login_id','2023-09-23 06:39:27.507928'),
(8,'contenttypes','0001_initial','2023-09-23 06:39:29.971522'),
(9,'auth','0001_initial','2023-09-23 06:40:26.532002'),
(10,'admin','0001_initial','2023-09-23 06:40:44.563669'),
(11,'admin','0002_logentry_remove_auto_add','2023-09-23 06:40:44.737873'),
(12,'admin','0003_logentry_add_action_flag_choices','2023-09-23 06:40:44.915452'),
(13,'contenttypes','0002_remove_content_type_name','2023-09-23 06:40:50.862138'),
(14,'auth','0002_alter_permission_name_max_length','2023-09-23 06:40:56.157358'),
(15,'auth','0003_alter_user_email_max_length','2023-09-23 06:40:58.290497'),
(16,'auth','0004_alter_user_username_opts','2023-09-23 06:40:58.416448'),
(17,'auth','0005_alter_user_last_login_null','2023-09-23 06:41:06.893539'),
(18,'auth','0006_require_contenttypes_0002','2023-09-23 06:41:07.209573'),
(19,'auth','0007_alter_validators_add_error_messages','2023-09-23 06:41:07.559437'),
(20,'auth','0008_alter_user_username_max_length','2023-09-23 06:41:14.679756'),
(21,'auth','0009_alter_user_last_name_max_length','2023-09-23 06:41:19.895726'),
(22,'auth','0010_alter_group_name_max_length','2023-09-23 06:41:21.713684'),
(23,'auth','0011_update_proxy_permissions','2023-09-23 06:41:21.968808'),
(24,'auth','0012_alter_user_first_name_max_length','2023-09-23 06:41:27.996625'),
(25,'sessions','0001_initial','2023-09-23 06:41:33.294393'),
(26,'Worker_Rental_SystemApp','0008_ratings_product_id','2023-09-23 09:39:54.314245'),
(27,'Worker_Rental_SystemApp','0009_auto_20230925_1253','2023-09-25 07:25:00.564475'),
(28,'Worker_Rental_SystemApp','0010_remove_assign_product_id','2023-10-03 05:50:47.814935'),
(29,'Worker_Rental_SystemApp','0011_auto_20231003_1334','2023-10-03 08:04:25.857930');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('013rw3vh22x7v3iunfyla7vjeix53rta','eyJ1c2VyX2lkIjo1fQ:1qkiWy:TNMYvFihqZj1lrxgx2kDirvlW1NSv0shYpAj4gY46yE','2023-10-09 10:09:32.821644'),
('0hs2jy93xk5k8my2mf6x7u0n29xo7k5u','eyJ3b3JrZXJfaWQiOjN9:1qmAEV:qDHD5oR9sWEorobDf4cI-VIGFAD6-ivc2GuRh9ZWaUY','2023-10-13 09:56:27.302537'),
('0j1rbvk4t0m6xahymrskboazlg8vjxhd','eyJ1c2VyX2lkIjo1fQ:1qliME:z5ELVMszEhltcnPi-4xUQgXtC-PVgXknPrNmOFhaG_I','2023-10-12 04:10:34.730968'),
('1eoqu1teok5044awb068cpjxrnowvfni','eyJ3b3JrZXJfaWQiOjN9:1qm4Md:CxE9B1qUyd2m_vbYm3Ohhb_tjDDKYs1Bjw6WaIS-dMI','2023-10-13 03:40:27.949947'),
('1gwwh4inmn7y10rzp06zgx9okewlctkd','eyJ3b3JrZXJfaWQiOjN9:1qkjgF:-04-MnOdrLn8ES7a8tEAXr96FK_0wNT6-Bkkt9DoT-s','2023-10-09 11:23:11.518540'),
('1i5kytedoydopd4pk82gvuag31s3ou9e','eyJ3b3JrZXJfaWQiOjN9:1qnaHX:WX4rXWfrqJn-SPh-VDNHbdNVF9z8XXniIq4fj2TXWSs','2023-10-17 07:57:27.772550'),
('1mu7ufojyxdhqevtmdht9d6mfng8tffp','eyJ3b3JrZXJfaWQiOjN9:1qmYBw:pOSmO7fXBI9phBLh09IYq1jhPF-v38aHoTi8XsmlgQU','2023-10-14 11:31:24.503856'),
('1vs51j2ic1zkp02mxzzppooykph9e06w','eyJ3b3JrZXJfaWQiOjN9:1qke2V:7fya3NVfcSrHbdj41rp0NH7Rrwbjh1Dl21yGuJhupT0','2023-10-09 05:21:47.652004'),
('20wem131mp5sd3un8klair2ksdyhq254','eyJ1c2VyX2lkIjo1fQ:1qkeRy:HbkRMgAVRdYGlAkqHxW4aQ4hPSY8eMTHJcTq_HN6y88','2023-10-09 05:48:06.632818'),
('21urvrqcbar7rtdmz8qmch64o5ussp2b','eyJ3b3JrZXJfaWQiOjN9:1qnaJR:_TZX_fTREnHNObw-0ve8ObYxADDcBXBpxIntF9gqihU','2023-10-17 07:59:25.773634'),
('2e15y9hunqg5lgq018n59k01hz88320p','eyJ3b3JrZXJfaWQiOjN9:1qnyHX:tMSyPTr2Y0bVjpAj7_k-uPC3vJEY6TuI-vj1iIEIyoY','2023-10-18 09:35:03.817083'),
('2gfsvbrhll8gmju3mzl310dvz7qojjjn','eyJ1c2VyX2lkIjo1fQ:1qntSo:Fnoppc8xwS_NS2t5dQhLwiE-vtLoL7IRLI_UaPPZ7sA','2023-10-18 04:26:22.899960'),
('2hk3fvoq9tyrki965k30539of02ixjiu','eyJ3b3JrZXJfaWQiOjN9:1qjzPY:8f8MGLVxviBra75NNhtAIMJ58Tv4TBcoVTqgTpDQixo','2023-10-07 09:58:52.867273'),
('2pq26o2ohz9sibqcrfzww30xsksdbkm9','eyJ3b3JrZXJfaWQiOjN9:1qmAcj:bvQg3H_BSWkmrTkyb7tdfUoGiG9s52POhj6elnYcquA','2023-10-13 10:21:29.953774'),
('2qjiztokd62xjnssdmzgdvg8ilg9bg0h','eyJ1c2VyX2lkIjo1fQ:1qnd3t:6tNLu_PdurnrKlORrk3FWrzKs2J4dFxt4bTQbio-Onk','2023-10-17 10:55:33.478078'),
('2scp4jzljyyekxhswnz7y6gjuguqf7cv','eyJ1c2VyX2lkIjo1fQ:1qkjIQ:9iEfBjXYNgigRiQMx-HKlJifM_0Vkv0sgcCfsKDeB6w','2023-10-09 10:58:34.254458'),
('398a2fi8vsoq3yeywlmjg0x0gjbyej2z','eyJ1c2VyX2lkIjo1fQ:1ql6jz:MBa55B-6YWzpKnXNAtlXl2wbT5hVCoTyLJeGzUmFEy8','2023-10-10 12:00:35.576365'),
('3oy4mq0zgk1kqmpa600wyc31tyujo5yi','eyJ3b3JrZXJfaWQiOjN9:1qnubx:qpoOJOID2hihDl9p7yw1QRGwIPtv_2BFw8VusrG3uxg','2023-10-18 05:39:53.913957'),
('3t39gz1jp5z1vtluwox1tc57ec7io1mk','eyJ3b3JrZXJfaWQiOjN9:1qliND:jMFgd-Z31no639GvFfWOGUc_XQVnJqhfcvMbxgE9YmU','2023-10-12 04:11:35.310827'),
('4hbiac2hwhmj4me67ax7hl5b6ymm6beh','eyJ1c2VyX2lkIjo1fQ:1qkese:MD0uC0uIjvK25P596Bg2mLxi2iE-59hyTPGYaflPRu4','2023-10-09 06:15:40.718559'),
('4is42zypd6yj10my655w9g286bmoilfe','eyJ1c2VyX2lkIjo1fQ:1qnwAm:JUwTGI5AfUS8qMIPSMbZ_pcg0Q2h8Wk39gYw2vH7O60','2023-10-18 07:19:56.932385'),
('4mz9bkvqcdgxoq5ttp0wqtlqiqewx8b7','eyJ3b3JrZXJfaWQiOjN9:1qnyGP:r2OKQjz8HH2YrnFLhNji5lT0LmS3IeMTorPJ-IDS4WY','2023-10-18 09:33:53.006629'),
('4tf06mlvjj5u2njv6fiw1w47c2xqkwru','eyJ1c2VyX2lkIjo1fQ:1qndkD:Pu_HXcI_BVyKvoOVWIYIfG1D9mOrnoy0CbECRgLE_fE','2023-10-17 11:39:17.856571'),
('4xbyc08yn652mevtxlqiuh39px2ik9qn','eyJ3b3JrZXJfaWQiOjN9:1qkf6u:3K7PNmo4WF_pD4tOCJ9bihkOdkh5OEs3fUlfKJW2sy8','2023-10-09 06:30:24.394011'),
('571binqokjrfhb7qbpmilwr0m29gw9dc','eyJ1c2VyX2lkIjo1fQ:1qnbqM:l91NhBxXTObRhELtfoGyvy0AvMBJCJ2f7w4c6cJ2jFY','2023-10-17 09:37:30.470735'),
('57chfce6g3h6razjmx6yjklwk39vji1l','eyJ1c2VyX2lkIjo1fQ:1qlLew:bmE1ks5A1_IzvZ7ftqKdhVhJjvutFrL68MKwynRzGeA','2023-10-11 03:56:22.195743'),
('5h6xwayjv2dobd01tjdil5ya1jp4yt0m','eyJ1c2VyX2lkIjo1fQ:1qmA2S:YM0-8LyHqpRItEGuvmgJsm1j2GMTezw6LgFIYuh1-dw','2023-10-13 09:44:00.676229'),
('5miw7sjwowdjvbyb87471behlzdbrwfo','eyJ1c2VyX2lkIjo1fQ:1qkiYw:WLoOFWYRkvPmSBHSyVQmZqqeNwav0q3I2DtqbI40AzI','2023-10-09 10:11:34.801497'),
('5tg7hjfr8feboz3hq5demju92m14evh2','eyJ3b3JrZXJfaWQiOjN9:1qnvZL:2iw9RiTraltMrQTmuHMTSH_kTX2x3TzPL27_nrclRf8','2023-10-18 06:41:15.091884'),
('5zfflftlz5u7lqmag826lew6zf5cu4ga','eyJ1c2VyX2lkIjo1fQ:1qlOdS:w7ynfAg8omEIyLESimVhanRRFmYh-8UztdY1liddKA8','2023-10-11 07:07:02.296037'),
('5zjtsq9hhdi8r3i4yub1ml6nii562scb','eyJ1c2VyX2lkIjo1fQ:1qntSn:Vn-oXXlvF1nsrrAmhBLUQKSrTXetFQXZDnpWB0NeAEk','2023-10-18 04:26:21.718341'),
('6aoq1468wkk99vmf1bc713o1utna28ql','eyJ1c2VyX2lkIjo1fQ:1qkeSu:uN2yKRjb5AilH_EDVYujE5qgv8HS6Ip_1LNQsB-FiYU','2023-10-09 05:49:04.827732'),
('6eftbeh99tps535w0teybevnlvut3tzd','eyJ1c2VyX2lkIjo1fQ:1qnyG0:S-gG8tgb8fb7n_cJhcXBOpoZo2K6_Kf3utBcXIQnJ1s','2023-10-18 09:33:28.300813'),
('6g0cwcj1eo9sd0smd00ln30hwzka1vx5','eyJ3b3JrZXJfaWQiOjN9:1qjziZ:QCR-wQaGLEGeRQzdkY4oZNTtnPb3785iUE20ZLoQsi8','2023-10-07 10:18:31.623781'),
('6jlo4gvlkxhnozjok8rm0lylcmxcd1gi','eyJ1c2VyX2lkIjo1fQ:1qndao:zp88CR5O53Eyps7vVO5bprfuhOkdRbvYLNSkG5ZBoQ4','2023-10-17 11:29:34.377982'),
('6sqzngmb3imjbouyq91toaurteuuep8e','eyJ1c2VyX2lkIjo1fQ:1qmWUg:ANk6qCKS45f58IHl4N35eeVW_HwsRG6GEmiL0Su0W_I','2023-10-14 09:42:38.479469'),
('6vmo8125a8i4ms5yx7uehexea5uktw2y','eyJ1c2VyX2lkIjo1fQ:1qm672:7EMGEQb6aDP8vFYfB2d7sJIoR5iLzCIlM1VHM0pELtg','2023-10-13 05:32:28.808734'),
('6yy5ka83cd5uxs6tvhci78kwwwigv5ou','eyJ1c2VyX2lkIjo1fQ:1qnuMm:iOzf_31PeIGdA2Jh0rNce3_jm-0cp8-eLuk_BMYB4EA','2023-10-18 05:24:12.851709'),
('7x00l6f3l4o796nuv214s9gkid32aqu3','eyJ1c2VyX2lkIjo1fQ:1qntjK:zm75nuuuzWd1Xw-Di0ttsZqv-aCqzHUaN98ux1RbITU','2023-10-18 04:43:26.702419'),
('8f8nu6rdj46crcjkmu0gewt80d2wuolg','eyJ1c2VyX2lkIjo1fQ:1ql4g3:iz4JK3Y3n5iIFLW9upyDAugkpxUlrgaadz-m1O3vfLU','2023-10-10 09:48:23.693765'),
('8hceyfgj9esa1fxd2xxzquoqg4siykhs','eyJ3b3JrZXJfaWQiOjN9:1qmA6Q:pkT46i2JOF04GkMO44UjZUDy3e9-3Fg-XouZeijzao0','2023-10-13 09:48:06.661302'),
('8nztg1hzso4vkioytfjmzib79p3aj0k4','eyJ1c2VyX2lkIjo1fQ:1qlSU3:arLRnn0Si2DwJcWKJGWMu-kslNhyviJEZGpHleovfag','2023-10-11 11:13:35.003450'),
('9l30c8ql0serc3pqnferm9m08rfv0mjq','eyJ1c2VyX2lkIjo1fQ:1qnwkD:hVOFgMRStKbGaT_DsdyuFarUTk8OOcEkvS25211Sd7s','2023-10-18 07:56:33.343187'),
('9rpix1ozimgura111blnf61f11t7nxlq','eyJ1c2VyX2lkIjo1fQ:1qkjv0:4cWArrLcQT6A9MpN1asufs-EbHbIwvpacfDEoCxlEaw','2023-10-09 11:38:26.895308'),
('9zid0ns1ivxfip7huji3r9mkwasg20gh','eyJzaG9wX2lkIjoyfQ:1qjwNT:SsHCIXS6UsSb-rsTjKMEDiv5osYDQUsrWXKuAJe1BUk','2023-10-07 06:44:31.774800'),
('a8prw9zhhjv0ro0q1nxti3qj8u0k8565','eyJ1c2VyX2lkIjo1fQ:1qmXb1:WjMLqNm-wgN1THSMtX_B1quc7WrVPSeSFtkRZPUTL7M','2023-10-14 10:53:15.204578'),
('ajvezhgt05vzf7kyohk41if1iswqsmsx','eyJ1c2VyX2lkIjo1fQ:1qliQJ:gzIgB-rDNud-PHD1hox1UakQJFbOgPYv-hhNBoLqmYk','2023-10-12 04:14:47.285831'),
('alqgs699nb8yppj0if9pm6sp762jykjb','eyJ3b3JrZXJfaWQiOjN9:1qmBcJ:NFykKWc5Igudr6TFWyzrvaRo3zBCFJRgaSlLYb-qqyM','2023-10-13 11:25:07.619886'),
('apescne6vokamop2t0h9w07vh52tzj1y','eyJ1c2VyX2lkIjo1fQ:1qlRn4:m95RQBHHdPO_L6qWZGd4ZQWXb2xkDTfaUNPBuo5OYl4','2023-10-11 10:29:10.939653'),
('arwwn7ie13awbkp5gd7bywnzo0ln48bj','eyJ1c2VyX2lkIjo1fQ:1qkdxy:1Ahx0DPRF-cyKSjfMIo1DKDaELNPpAP3IPu5HvuBwOA','2023-10-09 05:17:06.168152'),
('azwmchuzlak2x2m3tqhzsahwwyymx0s4','eyJ3b3JrZXJfaWQiOjN9:1qnaJQ:Kb3HVhUZScck1AaxW9uIML2Je6geR7KRqd32F0sEKZs','2023-10-17 07:59:24.212259'),
('b2b63f1k36jjtvzw9003fi8wfmp6l9v2','eyJ3b3JrZXJfaWQiOjN9:1qk0KH:Kj2EJR3HnExTIoYwZCd7E_qSDh4qxh5HvDpZSoNHOgY','2023-10-07 10:57:29.844599'),
('bdqnsgrvx3drerktajulx82y1xaxoj03','eyJ3b3JrZXJfaWQiOjN9:1qkd9M:rc_qrxh6O0OdxybO7GRXdrcbreGgFunU5oD-W8udrfs','2023-10-09 04:24:48.226068'),
('bgk0liwlaq0rz3us2fccm1knibpqq1ef','eyJ1c2VyX2lkIjo1fQ:1qkitR:Ey3EtyoBxF78REArnAg7tdgyuIrmbClBIhhhoV9eyHY','2023-10-09 10:32:45.413985'),
('bok660gt46fx2cngsc2r55dyf92ne3so','eyJ1c2VyX2lkIjo1fQ:1qmYDO:zW_6G_WrIv4z8kvikoJJaD-GzlPoRZ-_zYWNj986ciU','2023-10-14 11:32:54.109525'),
('bp63fe3og8pq6g2ybrs43pb6edotkp56','eyJ1c2VyX2lkIjo1fQ:1qlOFI:VI3fiy1rQV1Oxc9o9ifyuBb3OQLmtMlwxXnhVwkAjmU','2023-10-11 06:42:04.785917'),
('brivwlkqjofb9jab5tmnd5xho2jno8f7','eyJ1c2VyX2lkIjo1fQ:1qlRpr:8hc0PSCn3t_3YwdlqHV7xcGoetq0_E5fDiFkqXnGclA','2023-10-11 10:32:03.032533'),
('bw80znawbsrsps3etxu1tr2vfow9eer3','eyJ1c2VyX2lkIjo1fQ:1qntSt:u79s0cp3MOFx_OAPzt_DdoAIRN8ZbNF03aEvaeWlSrM','2023-10-18 04:26:27.968097'),
('c6xpo1y4untk6cw7g3rtsofxcs3iyqrg','eyJ1c2VyX2lkIjo1fQ:1qm9y4:IZj2FPVktS6qrgPpNHFseIPMc2jvvNMI7gam9QrPZY8','2023-10-13 09:39:28.267140'),
('c80lvwnaarcytxfhnd1yxfqqvwnur7l4','eyJ1c2VyX2lkIjo1fQ:1qkdy3:ab03ruvyzKzTGUPL_med1QJH_PN2W-UdnSmRLhCwrhs','2023-10-09 05:17:11.351653'),
('c9mwb9jcq5987hp0jfrz6meu7sbvn91y','eyJ1c2VyX2lkIjo1fQ:1qndGn:JNekTtr3EtlsccZjn1t9-FVnIklwovrjgHfyqagxKFU','2023-10-17 11:08:53.296601'),
('cf3tc18s1sgme56ojqhmh9pq07okamv6','eyJ3b3JrZXJfaWQiOjN9:1qm4Ma:qoXJEe5s0zmUsLA5Jn8-zoCxf0gSlQfGwvlUcAoCvEs','2023-10-13 03:40:24.560399'),
('cipfccryqh0zun2vpkqamtavgbmlxd3k','eyJ1c2VyX2lkIjo1fQ:1qmWZC:HfSUx8dycvOviaZBwBDz5p0qYvM56t-CaGTwatPo7vw','2023-10-14 09:47:18.218772'),
('crqemw1m7tm2ak1gpokmu7wmz83lo1bg','eyJ1c2VyX2lkIjo1fQ:1qkfCY:2vo3vJhpvOcBNA8fgS4egm2x-MkCAkIcWlCurg6qLAc','2023-10-09 06:36:14.018559'),
('ctbj8braj3db10xsqdzz993nwwajmzen','eyJ1c2VyX2lkIjo1fQ:1qnwSp:AsnowxR__gIs3ToV11aXht7jGKT1fbC0mm_D4aCX3X4','2023-10-18 07:38:35.755138'),
('ctg5nshfov2oosna0c7klmwctoayhxjs','eyJ1c2VyX2lkIjo1fQ:1qkjvm:m543hQmXqj4h0SJTVrxHbrvwdUKNvwsvW1yH_4Qr1AM','2023-10-09 11:39:14.298078'),
('cz000umzx54gvi51dnl5r6blb2p0nrld','eyJ1c2VyX2lkIjo1fQ:1qncbE:yFyo84tuSGa2Q9aPi7Z0VJPASK6KVz0RWHHvn47pLUw','2023-10-17 10:25:56.067737'),
('dnqkfiflflwcg97j52qvdsket34crc1t','eyJ3b3JrZXJfaWQiOjN9:1qlmJH:9aar1v4-Hhxc7adfPXQWmk1kDrJEakKpAFptG6f_jtw','2023-10-12 08:23:47.438539'),
('dpuzntafuxqcch01napeuu8x6rwbdfz5','eyJ3b3JrZXJfaWQiOjN9:1qmA6T:trL4SXESGfvsNJOwWNMGROuJX2ZJkghr7zeNRnc-JLA','2023-10-13 09:48:09.355973'),
('ep1c9rn7s3xq6lio4xklatxvgjnmqckw','eyJ3b3JrZXJfaWQiOjN9:1qlRNZ:WuepG088NCBg2AADGgOr8PNp0dtEZSmiCjnRFNIJR_0','2023-10-11 10:02:49.376409'),
('euzej3c72l9e2fbus8b2xfgrcp99rmni','eyJ3b3JrZXJfaWQiOjZ9:1qm5vg:4Lu9WV7-jx7IZXfYvjX2Sy3Ep7HkoinGW9wgWUbxMaM','2023-10-13 05:20:44.700226'),
('evwzg12id68iiz5nosy7uobfqyx9r5ji','eyJ1c2VyX2lkIjo1fQ:1qkzIR:XkR4wFLldcoC3aSjV_NpEkvojCGhKHry_whqyP5q6ZA','2023-10-10 04:03:39.050043'),
('ey85mmfhbcuosc9vt4kuvvb7rx6ht0nk','eyJ3b3JrZXJfaWQiOjN9:1qmWJ7:Yh3WMoSiWnniZJfmLMjCj9b4-qZ9Q695JP-2mai5X8U','2023-10-14 09:30:41.776900'),
('f87dxsyh7sb1r57w3s4pqsrbjzz2c625','eyJ1c2VyX2lkIjo1fQ:1qm5cX:xq6k7wAnUrBR5pXuhs8L4MOpl_8dvurSXQPudWvE74w','2023-10-13 05:00:57.935749'),
('fa3qtqsi94tle966yescoed4gze7nx8r','eyJ1c2VyX2lkIjo1fQ:1qkg7E:nNpamhUjFWT8pXcGuvWGZjRT_BVV4qOBZaimbxMyxgc','2023-10-09 07:34:48.271394'),
('ggb6dxe0xk96tc8ku9xqthokjjfhw9nw','eyJ3b3JrZXJfaWQiOjN9:1qlSVC:lvImyDuyAQzXoePqaXPGWPlqf8JaC9pAFvG7ByYAgBk','2023-10-11 11:14:46.885423'),
('ghz29wbt9bcghl7dg3pf0jp7c82egw7z','eyJ1c2VyX2lkIjo1fQ:1ql0uS:9uyP1_qAZPPjKf4Jw4ox-WoQ7y1tp2cK7MMXdcjNQaI','2023-10-10 05:47:00.025444'),
('gupxhqr9yt0ma2193kvmuxipx558nq4d','eyJ3b3JrZXJfaWQiOjN9:1qjwoi:LAEJyGedt_XGWEH8O6WzV_RDLTpZciZjsNV0pV8Z9ME','2023-10-07 07:12:40.634048'),
('hvocx6b28tcpfxq63zsyyftsk2pf8lxa','eyJ3b3JrZXJfaWQiOjN9:1qloD7:d8tGsOeZx1RnJW2fWMcaQMwb1M5cV0HAzinrJOKzbt8','2023-10-12 10:25:33.008368'),
('hxptjew7wjw152ttx58pwoeczzvnw0q5','eyJ1c2VyX2lkIjo1fQ:1qkieg:PGMX8mwGf3E6jg8gIo7mv_WoJhemzKfLiyJTtK5ri_c','2023-10-09 10:17:30.220138'),
('i76nynqc30o9g79145q7676u4bke1q7d','eyJ1c2VyX2lkIjo1fQ:1qkeZe:oj4QA1Y7hFLtNT7BpHdCqTcoJwoplYzFmFmANX4tNo0','2023-10-09 05:56:02.979432'),
('idb0kgsobky0g6osyiuf38xqrwuewoba','eyJ3b3JrZXJfaWQiOjN9:1qjwfT:X2zMCUNJ5ClzSzaXIk9X_6lJPUVkjCOLA4AOC1XkDII','2023-10-07 07:03:07.384161'),
('ifv9cd7xnpi1cho6z0r8cw3bn3fayiq5','eyJ1c2VyX2lkIjo1fQ:1qkjfy:qYGHHRxF9jzeGS34U28rnf9qtTK2AGnzn_BqhiN61M8','2023-10-09 11:22:54.113801'),
('iqie5fbtqf18uh6j5yz9v4rgc88tpo1e','eyJ3b3JrZXJfaWQiOjN9:1qk04I:MHwVM8hzkYZZ_9-ZcFH_qcbYCLTo-o6r2CZ0e_Ba-RY','2023-10-07 10:40:58.877948'),
('iuh12gdt2ijyz1geaci5xo4dh7svsc9m','eyJ3b3JrZXJfaWQiOjN9:1qmA6T:trL4SXESGfvsNJOwWNMGROuJX2ZJkghr7zeNRnc-JLA','2023-10-13 09:48:09.197862'),
('ivyabhuf35itilssvknrgtkwro67c953','eyJ1c2VyX2lkIjo1fQ:1qmXa3:xAj80KPpLyUmAbdFOCzkF-nNDQe0-1Chvy0m7vIDINc','2023-10-14 10:52:15.089721'),
('j6usfvz6vfm1tjduhwa0z41ys42r13ql','eyJ3b3JrZXJfaWQiOjN9:1qmVji:wQ5UnhtKPJNIf4uw-1ZVew0_JtwfXJEiJZRNmLK7Ygc','2023-10-14 08:54:06.715499'),
('jcok3rkm70jpn5hz2w6eg11fnzyb3tzo','eyJ1c2VyX2lkIjo1fQ:1qnd0a:HithsUbAAXHnNaDqHrDLbLEIGcVQiXfxkzlvXKfx4m0','2023-10-17 10:52:08.553347'),
('jfz1o2nxihbnga61lecmhhii4cw1b4i2','eyJ1c2VyX2lkIjo1fQ:1qmYCC:s8bKs37TgQdOZIyYXZC0k-ddbWCW7p-aYwx6dNd2dhM','2023-10-14 11:31:40.519413'),
('jlv5d72h2aotnzjp9qz3jnxzwsxk3giw','eyJ3b3JrZXJfaWQiOjN9:1qliT7:Vl8k5e1SiUdusaYaNP-d6peAHo6hkoMyEedetqAZ85g','2023-10-12 04:17:41.119520'),
('jspwhcvrimvipaa6qeln1w6rh176npdw','eyJ3b3JrZXJfaWQiOjN9:1qnvf3:rwx4aOkEOQiKUlldvhJOkAyY3OpnZ_wHOSpBv9ogn90','2023-10-18 06:47:09.207704'),
('k1kvvdlt8yowyy3pemmlq7lodmync84r','eyJ1c2VyX2lkIjo1fQ:1qnXQr:jbtCamEY-7_xKBenw7iKvKG2jBl2-W2H9SknT2u0CW8','2023-10-17 04:54:53.316772'),
('k2jzmr0exa3v6v72t2w3aqry1pz1ejvz','eyJ3b3JrZXJfaWQiOjN9:1qjzZF:nhYmN8-oORflxXjEGBP4Pk8NPuntLtZ156qpjFVilvs','2023-10-07 10:08:53.961379'),
('kcqa1otkd1c1r3sagsyz2yaexdyczv00','eyJ1c2VyX2lkIjo1fQ:1qkhFb:J1Cz8j5qddZz26DN34JVPXjU5q1UooeEE5i05d_fDtc','2023-10-09 08:47:31.539008'),
('kf9xbwgtz80dybfuwxfgf81sq8jd0lgl','eyJ1c2VyX2lkIjo1fQ:1qlLP2:aabb7pWRzXXrnNrZxG6wLgjP412hW9lV1njLi26YSio','2023-10-11 03:39:56.919720'),
('kg8bss7kzgqm6suvh635zpytxm7n1el0','eyJ1c2VyX2lkIjo1fQ:1qlPKI:i-nT-HTRwwvnWpHGRlmUSHF1kW4xiyR3ZQb1_L5I9IM','2023-10-11 07:51:18.472561'),
('kmydirevgxbka9csbrjhktuerusbf97t','eyJ1c2VyX2lkIjo1fQ:1qmYPI:sQyLtu0JCz2eHnbFbBeOl_K2tOTeB8kmVBBHLJ_Qywo','2023-10-14 11:45:12.448468'),
('l40wwrnogroe7g77s28dtvpm2tegp7hh','eyJ1c2VyX2lkIjo1fQ:1qkiid:iCgZ4UpE3IU5SZLu8m7JTFiYz8rWKbBwlBIG76l1oYI','2023-10-09 10:21:35.437728'),
('l4p3ehaa3scrq47n1m985p5mcey99qxz','eyJ1c2VyX2lkIjo1fQ:1qlODQ:WAxEfreWwIGrZrAPTKFjmmRdvT3ePyH_yVspchaIvh0','2023-10-11 06:40:08.935681'),
('l4vep32l4060vkfgtlaprzy7lmiukrk7','eyJ3b3JrZXJfaWQiOjN9:1qjzUs:f21B7SpKUu2LuzMg1WsAFezGY_0oTgZej9W2wdFP9Xk','2023-10-07 10:04:22.144647'),
('l83wbucjgrts7phodh5t3fp708l3l8l4','eyJ1c2VyX2lkIjo1fQ:1qkf1a:fB0ODdC5HBOEENXUtFACQUVYeWTXhR15oGnZZNptsdg','2023-10-09 06:24:54.949157'),
('leddwxp0hanxpl0jocyraiqb95hguhyf','eyJ1c2VyX2lkIjo1fQ:1qm5zC:7VXAqHQ2rnyZPIoJC_bdGB3i_ID7NPYMhHBeYAA5Z3o','2023-10-13 05:24:22.212928'),
('m2p00h3uoja9ph6kzmjvhjccwazpmggz','eyJ1c2VyX2lkIjo1fQ:1qlRLJ:re19A1zqUqTtOQB_NLo-bBP011yVpjxEUbeDGzzsidg','2023-10-11 10:00:29.283485'),
('m36bmhbf3xvv4r03rxglqmos5m9q22o8','eyJ3b3JrZXJfaWQiOjN9:1qnv3f:y1bvkerrVsIkU3ykV622zfwXUmDP79q0ubCdKl9apiE','2023-10-18 06:08:31.748816'),
('mgq5z3dinzmmr5exk6fiea09oilacmpu','eyJ3b3JrZXJfaWQiOjN9:1ql5db:yDmNuo7v1NkFCIMeWDVtNwi-d3WQOO9_plNqi11q3hE','2023-10-10 10:49:55.901834'),
('mmign1skge1qbaxxmj9szuuelt4ks8qy','eyJ3b3JrZXJfaWQiOjN9:1ql5c2:8AdDmp2gdbmYZJ_7oVdD-BOUb-LFH5_jdAM1XfAS-vI','2023-10-10 10:48:18.042619'),
('n252l6eh6xuyep8w8zsj08rhfa7dcdgi','eyJ1c2VyX2lkIjo1fQ:1qkieg:PGMX8mwGf3E6jg8gIo7mv_WoJhemzKfLiyJTtK5ri_c','2023-10-09 10:17:30.697786'),
('nd84pi0jm8yzkjzs8sne2ypfebi6vqep','eyJ3b3JrZXJfaWQiOjN9:1qnYy0:f7DlGi_Qjv4uDrbIzZUDXDzFsnweoXD5RohrnyZH_58','2023-10-17 06:33:12.708892'),
('ntgvzd8yf1z7ylxnrod47e7zrzvsnjs9','eyJ1c2VyX2lkIjo1fQ:1ql5s7:gADIfbGHehrvNe3RBqfhoMc6HwkGwSQ2Q6G0bvjD5Hk','2023-10-10 11:04:55.419146'),
('oan98rukylt1d8vne2d5wvm5efceipm6','eyJ1c2VyX2lkIjo1fQ:1qkeTk:uBUjrQBXwPoYwR8ciNnYFX_tODqDz2nHWPe_Ej1sc04','2023-10-09 05:49:56.782819'),
('oidcgww2lf2h3z2nenlgb9cu20xflc97','eyJ3b3JrZXJfaWQiOjN9:1qjzGD:lT3C91UppB0kqoNVqeRqBhHoG3pvHqShQJG7dLzPhwg','2023-10-07 09:49:13.293365'),
('oitnpqukew2xbnvhuqdrcjvfpb5vt1h5','eyJ3b3JrZXJfaWQiOjN9:1qmYT1:0QD_emyl_CKajI9x2FDnbV2F7b_2veplqbjxMHA8OmY','2023-10-14 11:49:03.661946'),
('p89vxyayic0etjxn0zl069pippyg157t','eyJ1c2VyX2lkIjo1fQ:1qmWiS:0oGVSJDlKDxGgRM_DgPFOma7003uTPRWFvcwZkRyO94','2023-10-14 09:56:52.425694'),
('p8nv75brzf045b8gfbkhupgl1o6sv5jb','eyJ1c2VyX2lkIjo1fQ:1qmYKC:Jq3s_z6H8eWlI-QOIueQj-axMHxUgZ1wY8U-IOalzXY','2023-10-14 11:39:56.730076'),
('q180793pmvax6www446hkec8szltjjs8','eyJ3b3JrZXJfaWQiOjN9:1qkj17:Kk59qqxvOKrkk7xJT_x0gc5YLUzxWYthNVnsDK1DoqM','2023-10-09 10:40:41.847450'),
('q1zzjigse3npwe1dbqe8678ktbkrw9p0','eyJ3b3JrZXJfaWQiOjN9:1qnuN0:HyU7xVTSGp95Wor2G8p6R8eKUZWN41mI_lQiAkwr_E4','2023-10-18 05:24:26.255045'),
('r94nt1e4qkux79vk53afbolqdf03kuak','eyJ1c2VyX2lkIjo1fQ:1qkeV6:tWxLSfxloE4znrFtd7U-f1Z2WDphs49-eGmeB-xveiY','2023-10-09 05:51:20.446072'),
('ras59kwp9efzuy5kp7eh94ok4uxd7ikb','eyJ1c2VyX2lkIjo1fQ:1qndsC:8GEH49nG2Yho7Y6X4wD3C7wm8Fjhee_D-ZAzI0enNnQ','2023-10-17 11:47:32.561713'),
('rub3zhzoq80o69umsd4lmv0gudl6flw2','eyJ3b3JrZXJfaWQiOjN9:1qnvLz:4iekwVx5zpHFTtBpzb6nzWQXxlGfKcXeYwzCz3O7G-8','2023-10-18 06:27:27.297736'),
('rzkdtu6ifibv55w4olt0qwgug7qbi3mu','eyJ3b3JrZXJfaWQiOjN9:1qnd5b:bF1N2stYeoLZlcPNu6HpqMgkjvhs8y2jiFgxjAURqY8','2023-10-17 10:57:19.246004'),
('s5fcgc8yolvi1cf2fewqr5ys5hc9wuox','eyJ1c2VyX2lkIjo1fQ:1qkib4:HTGtqkaoe4upEY-1Djq2o9EKMDZrotyUFi32V3YVUHs','2023-10-09 10:13:46.787347'),
('s8fi3o3fuuhl8i2lqycts40mxbue9efj','eyJ1c2VyX2lkIjo1fQ:1qkiSH:aGxbTzhoc3IUn8QpOQvMOzeUJ0eZufkkXIO2LCaB5_E','2023-10-09 10:04:41.699440'),
('s9cgcfyqta11qcx2xzi7n3hvqx70mweq','eyJ1c2VyX2lkIjo1fQ:1qlRfG:-8BdvnTUdawHUIUB5ZFQCH5iYkfIeo3s3efZesjQE9o','2023-10-11 10:21:06.435155'),
('sc2ahant04sywf11ic1qumw3y0fmv84y','eyJ3b3JrZXJfaWQiOjN9:1qnyBe:BrcIXL1JOyjVYvglkBdrhD4FG7HuWPGdQCyTDnN0YQM','2023-10-18 09:28:58.952292'),
('sdjqqefcdldydxoun36ap29revilkw7n','eyJ3b3JrZXJfaWQiOjN9:1qnvYH:uX9lvA1WJnznsXWOh-EZppmyNvdG3XT8Ptf-mrefk2Y','2023-10-18 06:40:09.030744'),
('soqr3m1bwqyijcpno191k1n5n63zphpv','eyJ1c2VyX2lkIjo1fQ:1qmYJ7:7xSUte-mSoyV40WXpIfYv0aVOOiIOeVxuXeVu0jT-jU','2023-10-14 11:38:49.559937'),
('ssifa6axkcbe57hjklb49qda2y1uol5o','eyJ3b3JrZXJfaWQiOjN9:1qkeYj:L_PNoMNx4YI7PgRtQ59-pa3HCsIm2XsRkmfIRik9RDs','2023-10-09 05:55:05.334537'),
('syw41v8ecwg4sxejzjdjg2w0tl6hcih4','eyJ1c2VyX2lkIjo1fQ:1qkjeF:azOQzyE9Djh3b7es20suyem0QuQZXLNo7idMrGC8CAM','2023-10-09 11:21:07.396536'),
('t63cyvtriibbc3xkftkyqdhyaxfqa5wy','eyJ3b3JrZXJfaWQiOjN9:1qke5r:UlOgPLroMfQz0vahhgzQWHOHHemYY9wJHOKjAxT9RIU','2023-10-09 05:25:15.155741'),
('tjvtkhyep8i6ilma4xwpgqnjcxzqs2af','eyJ3b3JrZXJfaWQiOjN9:1qnwPo:jvgYTw-BnlzMZzMr5jiXxjdxDun5PqblswvWf-86pnA','2023-10-18 07:35:28.092865'),
('tp6q6vmlwjqvbkevxs2kyawe1fmo5xgq','eyJ1c2VyX2lkIjo1fQ:1qmYBN:eRPd3Xxchxk7dqdkMKrYpYgJ3-kj_uqMWolYcbUJvNk','2023-10-14 11:30:49.063359'),
('twe1jj03ks85f81ns8c0a5xgj89w1npv','eyJ3b3JrZXJfaWQiOjZ9:1qm681:LinnPcCqt91hn0r8iPSFW9rSxMXKD46TBdgOsd4CCJU','2023-10-13 05:33:29.627522'),
('uil624jtkcyn7eo6t2xhzgntsnfb3m4y','eyJ3b3JrZXJfaWQiOjN9:1qnbq9:LYgEP6w1k244czLsSVIwc_S18cD6LLwxjMiRoO-TkQc','2023-10-17 09:37:17.071696'),
('ukzqky5gmys6q0w2gm77cic09g5lxads','eyJ3b3JrZXJfaWQiOjN9:1qlhfG:USxd8fdQ9UIHpUEmTVFAIwuvMgH4YomQNYVJPJH5KXc','2023-10-12 03:26:10.539333'),
('uluvcpp17xinwp0hw2gckw7h73c7donl','eyJ1c2VyX2lkIjo1fQ:1qlOxi:4pWrr4MEdWMubE6uVRynWC7u1-SNxSZvU7cEtuk0nmI','2023-10-11 07:27:58.791132'),
('uml3f16pm9w0nb6579w8t48tgix2j9nc','eyJ1c2VyX2lkIjo1fQ:1qkzqt:pV9i06-Md7lUt9P1iS2P0QKI0oN2oE712i5R642PUdo','2023-10-10 04:39:15.491517'),
('unbovvcwoucmx552t7ajzhq9y4l324za','eyJ1c2VyX2lkIjo1fQ:1qlQnv:Gq8jhd7YkGzcDekcpjlvDxdYn9Yvn4ciwh3GuBlhz1s','2023-10-11 09:25:59.334340'),
('unp6qaur0jcxezau6ydz01jm19hpyksq','eyJ1c2VyX2lkIjo1fQ:1qmABW:qsiAFyz55WAeheKuGhGBESw3jgbefa5KcKrH_ERhkkE','2023-10-13 09:53:22.109774'),
('uvtlc6zej2hm52xqw7whj3ich9j6if7q','eyJ1c2VyX2lkIjo1fQ:1qntSn:Vn-oXXlvF1nsrrAmhBLUQKSrTXetFQXZDnpWB0NeAEk','2023-10-18 04:26:21.548192'),
('v09lhx8e2k2eqwy6vtm1my7wi3nj00ec','eyJ3b3JrZXJfaWQiOjN9:1qliPC:VyvoH9-z9egqVRjQZDleU13xbSelAtZSC9OIKiAAnMI','2023-10-12 04:13:38.410492'),
('v30k3o626fltc46fxxuswq07vfrr0ie3','eyJ3b3JrZXJfaWQiOjN9:1qnwAy:900qtt3bBCeH8j7JnjgjGc6qLlymk8sUmrl8FCi0akA','2023-10-18 07:20:08.213025'),
('vdfk7hth2ah76b1iw10oc2h35b37ynxp','eyJ1c2VyX2lkIjo1fQ:1qnWDl:3-NB8pEJnxCApQ5JKJpEsdkKvOBxlv3oZ9rnn6efqik','2023-10-17 03:37:17.154555'),
('vg0yvowe66xzm5pror2rfj9mrdftwrrb','eyJ1c2VyX2lkIjo1fQ:1qmY60:u0O_yhbM5X34JgBVwmrdRUhKMafzsudnOTo5wvfyZtk','2023-10-14 11:25:16.138163'),
('vgyt5l3l5qdpg4z7iuyn2wlbny2g0d42','eyJ3b3JrZXJfaWQiOjN9:1qncb2:3Jq2aOmBP8DDnyN_SNSW1TbKuGdTrqMJrgKNpNm7b-g','2023-10-17 10:25:44.708571'),
('vnnuz2qbhone8daesk4g4rc0r5zlj1w8','eyJ1c2VyX2lkIjo1fQ:1qlOes:QWnLy7ShUkmXuSsi6jZzz0j6z8Way0tB-l0RVBDaDPM','2023-10-11 07:08:30.567776'),
('wa7il3nsmpg067giu49w4izczb2sud21','eyJ1c2VyX2lkIjo1fQ:1qm5sV:VSseZGEBly1e1x43d6oYGTdTCH32miaRbB3j7FuTaFY','2023-10-13 05:17:27.333066'),
('wtd76ghah677f95cmskxjd07ovmz9j3b','eyJ1c2VyX2lkIjo1fQ:1qkiXA:pkjwBg2ttbdWbK1Hn8b3MlB2Xy9y1GMA5rfonJCiOng','2023-10-09 10:09:44.868541'),
('wy3cew2nk8njfvic7brq3k9ovntakdf2','eyJ1c2VyX2lkIjo1fQ:1qkijk:RofgQO50WRZjbayTXYe3AV-imD8snfgHhV_51Bq1QKU','2023-10-09 10:22:44.074900'),
('x84o9wnqncakc738v9kprwfyxuc3fynh','eyJ3b3JrZXJfaWQiOjN9:1qloMn:zLI5Lrd2L8XbL9bAg8TWaySV4Z0S-R-2QB3oPP-lzAQ','2023-10-12 10:35:33.569431'),
('x8jwixqjm7xqp589vastsoy65rkkc8do','eyJ3b3JrZXJfaWQiOjN9:1qjzvP:SMDb-CdA1p5YY5sX3rVpXPuAJxLXeSzAGrqd9x9-6dc','2023-10-07 10:31:47.366684'),
('xd1osyrv8w5uuwhyp4wul4xdo2w8wcuj','eyJ1c2VyX2lkIjo1fQ:1qnthq:qUxw2DsIfpSa49hVlST-ofgF1ZI5HEXzWOsg_GGPS_U','2023-10-18 04:41:54.962302'),
('xefs96mwu687kubqltt5cf3gjkfbjhzv','eyJ1c2VyX2lkIjo1fQ:1qmYI9:gC5-V_xB1O_7fbDrcz_o3flm9LkF7RiMCOPbYArHFTY','2023-10-14 11:37:49.846961'),
('xrd9g9p0kcb0chnupw5ec6jjnd3i7ifr','eyJ3b3JrZXJfaWQiOjN9:1qmCB8:Aq7PKb_Jtw9N4a5H2LOO3EazhGJyamLvJDcVbdWOKUU','2023-10-13 12:01:06.070879'),
('xs265ihnohv9x64c03l5jw594wsjir2o','eyJ3b3JrZXJfaWQiOjN9:1qmWZq:siI8JaivccSnJmC2FiOos963uS6EvMNboLNxFrFk3AI','2023-10-14 09:47:58.292608'),
('xwa9l9qg163k6xdwl57mjn1nepxtdxr4','eyJ1c2VyX2lkIjo1fQ:1qm9xY:gqeiQWsmtQFPxqct1dBerTaZVuNfx2j7D69yXLQRaFk','2023-10-13 09:38:56.906576'),
('xycwba7k5m6cey8xo4y290kwfhyn5mbq','eyJ3b3JrZXJfaWQiOjN9:1qm5JQ:vaWKBDF4Zeq-Lap7Fj5uEpbIVSMcFlo-MoyIAvohU8M','2023-10-13 04:41:12.964881'),
('y1xvq3w3n8xierurkg2o7u1ki995t0ki','eyJ1c2VyX2lkIjo1fQ:1qlRT4:SUo5e7IgtQuNANp0v7K5d96KxyBEdwnVRFdFIQ50sFM','2023-10-11 10:08:30.838692'),
('y39jayl1goihngx51pyruju650dcc1ni','eyJ3b3JrZXJfaWQiOjN9:1qmXaH:HhAi2s_FUXbooj13ksOzOI_EwaRrqJS6FJJbbgLS7dk','2023-10-14 10:52:29.817890'),
('yg18se770bdbrjmst2r6o8zbnu2unh9g','eyJ1c2VyX2lkIjo1fQ:1qmYIM:tSpypQ-DWcR03BjLgPFN1NpoAcL6kOHTkIl-i9j-Dcw','2023-10-14 11:38:02.095401'),
('yw4jako5zc272v3ligo7q8gthnrl7fg6','eyJ1c2VyX2lkIjo1fQ:1qmYW0:rA-TRtVfKnjG8GyD7tNw6HtvmLXhnNM5dO-hFcuv5P4','2023-10-14 11:52:08.793384'),
('zdfs120gin30ic8vv9p1dr85egehr9li','eyJ1c2VyX2lkIjo1fQ:1qkjgT:C1z0EBxdWRQy2LW42hfADKbA5CWwV0wmCxuI041nqiY','2023-10-09 11:23:25.913310'),
('zixvistyj7nz3jpg7i1un61931t6ynyl','eyJ1c2VyX2lkIjo1fQ:1qkiS3:4Qs93WPisxENffbXGhMcQebwbCJI6n_oDXtBL3DI40g','2023-10-09 10:04:27.618369'),
('zqpeqntzqkv9p8jpw1hxhqx72ihlnyra','eyJ3b3JrZXJfaWQiOjN9:1qnvHX:huPifXyv1qqsXCmKusUmCmwrTwJyfnRSXUSYkrSa3zg','2023-10-18 06:22:51.024209');

/*Table structure for table `worker_rental_systemapp_assign` */

DROP TABLE IF EXISTS `worker_rental_systemapp_assign`;

CREATE TABLE `worker_rental_systemapp_assign` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Date` varchar(20) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `DELIVERY_BOY_ID_id` bigint NOT NULL,
  `PRODUCT_REQUEST_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_DELIVERY_BOY_ID_id_cc28ef6e_fk_Worker_Re` (`DELIVERY_BOY_ID_id`),
  KEY `Worker_Rental_System_PRODUCT_REQUEST_ID_i_1ec94a64_fk_Worker_Re` (`PRODUCT_REQUEST_ID_id`),
  CONSTRAINT `Worker_Rental_System_DELIVERY_BOY_ID_id_cc28ef6e_fk_Worker_Re` FOREIGN KEY (`DELIVERY_BOY_ID_id`) REFERENCES `worker_rental_systemapp_deliveryboy` (`id`),
  CONSTRAINT `Worker_Rental_System_PRODUCT_REQUEST_ID_i_1ec94a64_fk_Worker_Re` FOREIGN KEY (`PRODUCT_REQUEST_ID_id`) REFERENCES `worker_rental_systemapp_productrequest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_assign` */

insert  into `worker_rental_systemapp_assign`(`id`,`Date`,`Status`,`DELIVERY_BOY_ID_id`,`PRODUCT_REQUEST_ID_id`) values 
(1,'5-10-2023','pending',2,28);

/*Table structure for table `worker_rental_systemapp_chat` */

DROP TABLE IF EXISTS `worker_rental_systemapp_chat`;

CREATE TABLE `worker_rental_systemapp_chat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Message` varchar(100) NOT NULL,
  `Date` varchar(100) NOT NULL,
  `Time` varchar(100) NOT NULL,
  `FROM_ID_id` bigint NOT NULL,
  `TO_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_FROM_ID_id_74ae565c_fk_Worker_Re` (`FROM_ID_id`),
  KEY `Worker_Rental_System_TO_ID_id_532b0787_fk_Worker_Re` (`TO_ID_id`),
  CONSTRAINT `Worker_Rental_System_FROM_ID_id_74ae565c_fk_Worker_Re` FOREIGN KEY (`FROM_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`),
  CONSTRAINT `Worker_Rental_System_TO_ID_id_532b0787_fk_Worker_Re` FOREIGN KEY (`TO_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_chat` */

insert  into `worker_rental_systemapp_chat`(`id`,`Message`,`Date`,`Time`,`FROM_ID_id`,`TO_ID_id`) values 
(1,'hi','25/09/2023','9:00',5,3),
(2,'hi','27-09-23','14:38:15',3,3),
(3,'hello','27-09-23','14:38:41',3,3),
(4,'hai','27-09-23','14:52:46',3,6),
(5,'hi','27-09-23','14:58:17',3,6),
(6,'y','27-09-23','15:43:46',3,3),
(7,'hello','27-09-23','16:24:33',3,2),
(8,'r','27-09-23','16:35:59',3,2),
(9,'t','27-09-23','16:37:50',3,2),
(10,'t','27-09-23','16:38:14',3,2),
(11,'hello','27-09-23','16:40:16',3,2),
(12,'hi','27-09-23','16:40:23',3,2),
(13,'how are you ','27-09-23','16:41:03',3,2),
(14,'hi','28-09-23','09:39:21',3,1),
(15,'hello mru','28-09-23','09:40:56',3,1),
(16,'','28-09-23','09:40:57',3,1);

/*Table structure for table `worker_rental_systemapp_complaint` */

DROP TABLE IF EXISTS `worker_rental_systemapp_complaint`;

CREATE TABLE `worker_rental_systemapp_complaint` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Complaints` varchar(100) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `Reply` varchar(100) NOT NULL,
  `LOGIN_ID_id` bigint NOT NULL,
  `SHOP_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_LOGIN_ID_id_7420fe94_fk_Worker_Re` (`LOGIN_ID_id`),
  KEY `Worker_Rental_System_SHOP_ID_id_e8a307f9_fk_Worker_Re` (`SHOP_ID_id`),
  CONSTRAINT `Worker_Rental_System_LOGIN_ID_id_7420fe94_fk_Worker_Re` FOREIGN KEY (`LOGIN_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`),
  CONSTRAINT `Worker_Rental_System_SHOP_ID_id_e8a307f9_fk_Worker_Re` FOREIGN KEY (`SHOP_ID_id`) REFERENCES `worker_rental_systemapp_shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_complaint` */

insert  into `worker_rental_systemapp_complaint`(`id`,`Complaints`,`Date`,`Reply`,`LOGIN_ID_id`,`SHOP_ID_id`) values 
(1,'comp1','23/09/23','pending',3,2),
(2,'worker srav compl','25/09.23','pending',5,2),
(3,'comp1','29-09-23','pending',3,1),
(5,'new comp','29-09-23','pending',3,2),
(6,'user1 comp','29-09-23','pending',5,2),
(7,'srav2','29-09-23','pending',5,1),
(9,'worker3comp','29-09-23','pending',3,2),
(11,'user4 compsrav','29-09-23','pending',5,2);

/*Table structure for table `worker_rental_systemapp_delivery` */

DROP TABLE IF EXISTS `worker_rental_systemapp_delivery`;

CREATE TABLE `worker_rental_systemapp_delivery` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Location` varchar(30) NOT NULL,
  `USER_ID_id` bigint NOT NULL,
  `WORKER_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_USER_ID_id_7999fee5_fk_Worker_Re` (`USER_ID_id`),
  KEY `Worker_Rental_System_WORKER_ID_id_9d4d10fa_fk_Worker_Re` (`WORKER_ID_id`),
  CONSTRAINT `Worker_Rental_System_USER_ID_id_7999fee5_fk_Worker_Re` FOREIGN KEY (`USER_ID_id`) REFERENCES `worker_rental_systemapp_user` (`id`),
  CONSTRAINT `Worker_Rental_System_WORKER_ID_id_9d4d10fa_fk_Worker_Re` FOREIGN KEY (`WORKER_ID_id`) REFERENCES `worker_rental_systemapp_workers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_delivery` */

/*Table structure for table `worker_rental_systemapp_deliveryboy` */

DROP TABLE IF EXISTS `worker_rental_systemapp_deliveryboy`;

CREATE TABLE `worker_rental_systemapp_deliveryboy` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(100) NOT NULL,
  `LastName` varchar(100) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(100) NOT NULL,
  `Place` varchar(100) NOT NULL,
  `Post` varchar(100) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `Email` varchar(100) NOT NULL,
  `LOGIN_ID_id` bigint NOT NULL,
  `SHOP_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_LOGIN_ID_id_9410f460_fk_Worker_Re` (`LOGIN_ID_id`),
  KEY `Worker_Rental_System_SHOP_ID_id_d7ad9f39_fk_Worker_Re` (`SHOP_ID_id`),
  CONSTRAINT `Worker_Rental_System_LOGIN_ID_id_9410f460_fk_Worker_Re` FOREIGN KEY (`LOGIN_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`),
  CONSTRAINT `Worker_Rental_System_SHOP_ID_id_d7ad9f39_fk_Worker_Re` FOREIGN KEY (`SHOP_ID_id`) REFERENCES `worker_rental_systemapp_shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_deliveryboy` */

insert  into `worker_rental_systemapp_deliveryboy`(`id`,`FirstName`,`LastName`,`Age`,`Gender`,`Place`,`Post`,`Pin`,`Phone`,`Email`,`LOGIN_ID_id`,`SHOP_ID_id`) values 
(2,'renjith','n',33,'Male','kozhikoe','kuruvattor',673611,98765432,'r@gmail.com',7,1);

/*Table structure for table `worker_rental_systemapp_login` */

DROP TABLE IF EXISTS `worker_rental_systemapp_login`;

CREATE TABLE `worker_rental_systemapp_login` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_login` */

insert  into `worker_rental_systemapp_login`(`id`,`Username`,`Password`,`Type`) values 
(1,'admin','admin','admin'),
(2,'sh','sh','shop'),
(3,'mru','mru','worker'),
(5,'srav','srav','user'),
(6,'dilu','dilu','worker'),
(7,'ren','ren','boy'),
(8,'NW','NW','shop');

/*Table structure for table `worker_rental_systemapp_product` */

DROP TABLE IF EXISTS `worker_rental_systemapp_product`;

CREATE TABLE `worker_rental_systemapp_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Product_Type` varchar(30) NOT NULL,
  `Product_Name` varchar(30) NOT NULL,
  `Product_Details` varchar(30) NOT NULL,
  `Price_per_day` varchar(30) NOT NULL,
  `Image` varchar(100) NOT NULL,
  `Stock` int NOT NULL,
  `SHOP_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_SHOP_ID_id_b46ad04c_fk_Worker_Re` (`SHOP_ID_id`),
  CONSTRAINT `Worker_Rental_System_SHOP_ID_id_b46ad04c_fk_Worker_Re` FOREIGN KEY (`SHOP_ID_id`) REFERENCES `worker_rental_systemapp_shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_product` */

insert  into `worker_rental_systemapp_product`(`id`,`Product_Type`,`Product_Name`,`Product_Details`,`Price_per_day`,`Image`,`Stock`,`SHOP_ID_id`) values 
(1,'Electical','welding Machine','230v50Hz','150','ktProof_2VWg2Oo.jpg',4,1),
(2,'Electrical','Cutting Machine','230v50Hz','80','ktProof_2VWg2Oo.jpg',2,1),
(3,'hand','driller','wodden','50','ktProof_2VWg2Oo.jpg',3,1);

/*Table structure for table `worker_rental_systemapp_productrequest` */

DROP TABLE IF EXISTS `worker_rental_systemapp_productrequest`;

CREATE TABLE `worker_rental_systemapp_productrequest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Date` varchar(20) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `ReturnDate` varchar(20) NOT NULL,
  `PRODUCT_ID_id` bigint NOT NULL,
  `LOGIN_ID_id` bigint NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL,
  `address` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_PRODUCT_ID_id_1a14d79a_fk_Worker_Re` (`PRODUCT_ID_id`),
  KEY `Worker_Rental_System_LOGIN_ID_id_0c41440a_fk_Worker_Re` (`LOGIN_ID_id`),
  CONSTRAINT `Worker_Rental_System_LOGIN_ID_id_0c41440a_fk_Worker_Re` FOREIGN KEY (`LOGIN_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`),
  CONSTRAINT `Worker_Rental_System_PRODUCT_ID_id_1a14d79a_fk_Worker_Re` FOREIGN KEY (`PRODUCT_ID_id`) REFERENCES `worker_rental_systemapp_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_productrequest` */

insert  into `worker_rental_systemapp_productrequest`(`id`,`Date`,`Status`,`ReturnDate`,`PRODUCT_ID_id`,`LOGIN_ID_id`,`Latitude`,`Longitude`,`address`) values 
(28,'30/09/23','accept','07-10-2023',1,5,11.4567,76.34567,'234567'),
(29,'30/09/23','accept','06-10-2023',2,3,11.4567,76.34567,'234567');

/*Table structure for table `worker_rental_systemapp_ratings` */

DROP TABLE IF EXISTS `worker_rental_systemapp_ratings`;

CREATE TABLE `worker_rental_systemapp_ratings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Rating` varchar(20) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `Review` varchar(100) NOT NULL,
  `LOGIN_ID_id` bigint NOT NULL,
  `PRODUCT_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_LOGIN_ID_id_6be8193c_fk_Worker_Re` (`LOGIN_ID_id`),
  KEY `Worker_Rental_System_PRODUCT_ID_id_6cb75577_fk_Worker_Re` (`PRODUCT_ID_id`),
  CONSTRAINT `Worker_Rental_System_LOGIN_ID_id_6be8193c_fk_Worker_Re` FOREIGN KEY (`LOGIN_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`),
  CONSTRAINT `Worker_Rental_System_PRODUCT_ID_id_6cb75577_fk_Worker_Re` FOREIGN KEY (`PRODUCT_ID_id`) REFERENCES `worker_rental_systemapp_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_ratings` */

insert  into `worker_rental_systemapp_ratings`(`id`,`Rating`,`Date`,`Review`,`LOGIN_ID_id`,`PRODUCT_ID_id`) values 
(1,'3.5','29-09-23','good ',3,2),
(2,'5.0','29-09-23','excellent ',3,1),
(3,'2.0','29-09-23','good',5,2),
(4,'3.5','29-09-23','good',6,2);

/*Table structure for table `worker_rental_systemapp_shop` */

DROP TABLE IF EXISTS `worker_rental_systemapp_shop`;

CREATE TABLE `worker_rental_systemapp_shop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Place` varchar(20) NOT NULL,
  `Post` varchar(20) NOT NULL,
  `Pin` varchar(20) NOT NULL,
  `Phone` bigint NOT NULL,
  `Email` varchar(20) NOT NULL,
  `LOGIN_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_LOGIN_ID_id_613d2052_fk_Worker_Re` (`LOGIN_ID_id`),
  CONSTRAINT `Worker_Rental_System_LOGIN_ID_id_613d2052_fk_Worker_Re` FOREIGN KEY (`LOGIN_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_shop` */

insert  into `worker_rental_systemapp_shop`(`id`,`Name`,`Place`,`Post`,`Pin`,`Phone`,`Email`,`LOGIN_ID_id`) values 
(1,'Hardware','kozhikode','kozhikode','673612',987654321,'new@gmail.com',2),
(2,'New World','kannur','puthiyatheru','672623',987654332,'world@gmail.com',8);

/*Table structure for table `worker_rental_systemapp_skills` */

DROP TABLE IF EXISTS `worker_rental_systemapp_skills`;

CREATE TABLE `worker_rental_systemapp_skills` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Skill` varchar(50) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `WorkType` varchar(50) NOT NULL,
  `Exp` int NOT NULL,
  `WORKER_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_WORKER_ID_id_f3b97665_fk_Worker_Re` (`WORKER_ID_id`),
  CONSTRAINT `Worker_Rental_System_WORKER_ID_id_f3b97665_fk_Worker_Re` FOREIGN KEY (`WORKER_ID_id`) REFERENCES `worker_rental_systemapp_workers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_skills` */

insert  into `worker_rental_systemapp_skills`(`id`,`Skill`,`Date`,`WorkType`,`Exp`,`WORKER_ID_id`) values 
(1,'house wiring ','25/09/23','electrical ',5,1),
(2,'cutting','27/09.2023','construction',10,2),
(3,'cleaner','29/09/23','house clean',10,1);

/*Table structure for table `worker_rental_systemapp_user` */

DROP TABLE IF EXISTS `worker_rental_systemapp_user`;

CREATE TABLE `worker_rental_systemapp_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `Place` varchar(20) NOT NULL,
  `Post` varchar(20) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `Email` varchar(20) NOT NULL,
  `LOGIN_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_LOGIN_ID_id_8105f1c1_fk_Worker_Re` (`LOGIN_ID_id`),
  CONSTRAINT `Worker_Rental_System_LOGIN_ID_id_8105f1c1_fk_Worker_Re` FOREIGN KEY (`LOGIN_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_user` */

insert  into `worker_rental_systemapp_user`(`id`,`FirstName`,`LastName`,`Age`,`Gender`,`Place`,`Post`,`Pin`,`Phone`,`Email`,`LOGIN_ID_id`) values 
(1,'srav','n',29,'Male','waynad ','kalppatta',673612,8129068160,'srav@gmail.com',5);

/*Table structure for table `worker_rental_systemapp_workers` */

DROP TABLE IF EXISTS `worker_rental_systemapp_workers`;

CREATE TABLE `worker_rental_systemapp_workers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Age` int NOT NULL,
  `Place` varchar(20) NOT NULL,
  `Post` varchar(20) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `Field` varchar(20) NOT NULL,
  `LOGIN_ID_id` bigint NOT NULL,
  `Gender` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_LOGIN_ID_id_7bb52eb1_fk_Worker_Re` (`LOGIN_ID_id`),
  CONSTRAINT `Worker_Rental_System_LOGIN_ID_id_7bb52eb1_fk_Worker_Re` FOREIGN KEY (`LOGIN_ID_id`) REFERENCES `worker_rental_systemapp_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_workers` */

insert  into `worker_rental_systemapp_workers`(`id`,`FirstName`,`LastName`,`Age`,`Place`,`Post`,`Pin`,`Phone`,`Field`,`LOGIN_ID_id`,`Gender`) values 
(1,'mrudul','krishnan',30,'kakkodi','kizhakkummury',673611,9876543210,'electrician',3,'Male'),
(2,'dillu','dil',32,'kuruvattoor','kuruvattoor',673611,98765432,'constructor',6,'Male');

/*Table structure for table `worker_rental_systemapp_workersrequest` */

DROP TABLE IF EXISTS `worker_rental_systemapp_workersrequest`;

CREATE TABLE `worker_rental_systemapp_workersrequest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Date` varchar(20) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `Work_Details` varchar(20) NOT NULL,
  `USER_ID_id` bigint NOT NULL,
  `WORKER_ID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Worker_Rental_System_USER_ID_id_a8f018c2_fk_Worker_Re` (`USER_ID_id`),
  KEY `Worker_Rental_System_WORKER_ID_id_8dc39b22_fk_Worker_Re` (`WORKER_ID_id`),
  CONSTRAINT `Worker_Rental_System_USER_ID_id_a8f018c2_fk_Worker_Re` FOREIGN KEY (`USER_ID_id`) REFERENCES `worker_rental_systemapp_user` (`id`),
  CONSTRAINT `Worker_Rental_System_WORKER_ID_id_8dc39b22_fk_Worker_Re` FOREIGN KEY (`WORKER_ID_id`) REFERENCES `worker_rental_systemapp_workers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `worker_rental_systemapp_workersrequest` */

insert  into `worker_rental_systemapp_workersrequest`(`id`,`Date`,`Status`,`Work_Details`,`USER_ID_id`,`WORKER_ID_id`) values 
(1,'27-09-2023','Accepted','wiring',1,1),
(2,'28/09/2023','Rejected','house ',1,1),
(3,'30/09/2023','pending','concrete cutting ',1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
