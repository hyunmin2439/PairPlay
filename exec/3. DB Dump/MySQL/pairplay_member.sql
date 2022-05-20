CREATE DATABASE  IF NOT EXISTS `pairplay` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pairplay`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: k6e205.p.ssafy.io    Database: pairplay
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.20.04.3

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `sido` varchar(255) DEFAULT NULL,
  `gugun` varchar(255) DEFAULT NULL,
  `detail_address` varchar(255) DEFAULT NULL,
  `birth_dt` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `gender` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `social_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (9,'2022-04-28 15:33:21.900000',NULL,'부산','강서구','가달1로 7','1995-08-29','ckdgus777@naver.com',_binary '',0,'임창현','lch','$2a$10$q9oEZJ38oC1auwHF/RbMjuwuKTgkYxxI.0cvT7BHriRSHEbIeVSpi','010-9501-4090','9bf2f27fc0a841b9b300356f272c99d0.png','제 이름은 임창현 입니다!!','2247920545',NULL),(28,'2022-05-02 13:59:43.745000',NULL,'부산','강서구','신호산단1로 76','2022-05-20','asd@asd',_binary '',0,'정재호','asd','$2a$10$zX1oeTMCM6hSkmKmqI1N/uhqmLuZHjBghsizdBj4adf22Ol3DmKdW','010-1234-1234','default_profile.jpeg','Welcome To My Profile Page!!!',NULL,NULL),(38,'2022-05-02 15:47:22.593000',NULL,'부산','강서구','신호산단1로 54','1995-09-01','jaho901@naver.com',_binary '',0,'정재호','정재호','$2a$10$ycJkYt8HHbvVIr4BWOs6i.TcYkjc2tqQVfYw/8V4S/stsUSAdU0nW','010-1234-1234','aca0449b9aae4d4c85fd77fa469440d0.jpg','운동 좋아합니다! ','2225680860',NULL),(45,'2022-05-03 15:35:46.924000',NULL,'서울','종로구','청와대로 1','2022-05-03','sasuke@yahoo.co.jp',_binary '',0,'나루토','나루토','$2a$10$MAHgwN0dmhAaXL5uy9Hn/evWww7we4eu0pbqHeTX/RE7wKQ9OBtpe','777-7777','default_profile.jpeg',NULL,NULL,NULL),(46,'2022-05-04 15:07:14.386000',NULL,'부산','강서구','가달2로 49','1995-01-01','asdf@ssafy.com',_binary '',1,'김싸피','ssafy1234','$2a$10$lOACsXdywM06wiZKjVCMmuX0dO5jVB2QN2ldhc7kIb535R8IO.YTi','010-1234-5678','default_profile.jpeg','안녕하세요. 저는 김싸피 입니다.',NULL,NULL),(49,'2022-05-05 23:25:17.015000',NULL,'부산','강서구','분당구 경부고속도로 409','2022-05-12','aaaaa@aaaaa',_binary '',0,'123','aaaaa','$2a$10$u3I3U4TgOgUV/1rd/7i7eOcA2ZMaXP7VdlF4IDqHD9.Q0jKjFTwy6','010-1234-1234','default_profile.jpeg',NULL,NULL,NULL),(53,'2022-05-06 13:50:01.810000',NULL,'경기','성남시','분당구 판교역로 235','2022-05-05','pairplay.teams@gmail.com',_binary '',0,'페어플레이','Pairplay Teams','$2a$10$2HEg5AUBn406/g6U4V7l6.iR0cfC7NvVA0SpYCUIpO5EUqx.yGggG','010-1223-1124','https://lh3.googleusercontent.com/a/AATXAJwq7oX0_n7_NTK89Zgi1_sCNqoQX9YeYLbeezsY=s96-c',NULL,'110562460256749853180',NULL),(61,'2022-05-09 18:13:30.646000',NULL,'부산','강서구','가달1로 7','2022-05-14','tg123',_binary '',1,'tg123','tg123','$2a$10$snKrk9omD8ANgwGqYrfEXOSeeuZEFXE2V3ZrNsDmkN4P/j7RONHVm','010-4444-4444','default_profile.jpeg',NULL,NULL,NULL),(62,'2022-05-09 18:15:38.382000',NULL,'부산','연제구','해맞이로 23','1991-09-01','tg8685@gmail.com',_binary '',0,'조해성','대댐','$2a$10$DzN1gt7nBcMBKiu5UXfWFOLVtFBdsH/2RYPSG4CeR7QloGSJgms6O','010-4449-8685','1b9d23a6d33e4c3495b4c365252736e4.jpg','안녕하세요, 저는 부산 연제구에 사는 30대 남자입니다. 운동매니아라서 함께 운동하면 좋을 것 같습니다.','102299188960093296737',NULL),(63,'2022-05-11 13:56:06.389000',NULL,'서울','강남구','테헤란로 212','2022-05-05','gsmj1712@gmail.com',_binary '',0,'김민정','ssafycon','$2a$10$8Wvd6STJZk.BAt/ZdajwmeYzZ5Rzz/jf.xgO1CcWfmA5ASPljOMKa','010-1111-1111','default_profile.jpeg',NULL,NULL,NULL),(64,'2022-05-12 13:22:07.931000',NULL,'부산','강서구','가달1로 7','2022-05-04','loveyh95@gmail.com',_binary '',0,'123','조영현','$2a$10$qIGsl4LVU6YkZ2KETPIe4edTMWqXm7rR/Rw/Cmpzzty.vJHw1jsuK','010-1234-1234','https://lh3.googleusercontent.com/a/AATXAJw6Vz3CisNDDW62ZvQbB9ef6y83sJWSw57VXl_7bw=s96-c',NULL,'100367075570867187736',NULL),(65,'2022-05-15 23:53:46.350000',NULL,'부산','강서구','가달1로 7','2022-05-03','ssafy1@naver.com',_binary '',0,'김싸피','김싸피','$2a$10$a5PJyllWKx2Zv.7.DolxIO7dgL.hUrJn6/kpk7PiYKQn8L/jJe4Cq','010-1234-5678','default_profile.jpeg',NULL,NULL,NULL),(68,'2022-05-18 15:14:38.637000',NULL,'부산','부산진구','백양대로208번길 133','1995-08-29','ckdgus777@ssafy.com',_binary '',0,'임창현','짱구리','$2a$10$P0vehbBUHtCHuMplL7Cs2O.1vfiNd.Czcc3MRePlQWEMveGAjI2qW','010-9501-4090','default_profile.jpeg',NULL,NULL,NULL),(69,'2022-05-18 17:04:11.912000',NULL,'서울','동대문구','약령동길 6','2022-05-12','fdfsdfsd',_binary '',0,'dfsafad','fdsfdsf','$2a$10$54I6t.lv7U4wrCbrHEBUAOnlmE/AN/j3kno.Pt7HvZFj72wFedeRe','012-1612-621','default_profile.jpeg',NULL,NULL,NULL),(70,'2022-05-18 22:12:52.111000',NULL,'부산','강서구','녹산산업북로 433','1995-08-01','qwe@qwe.com',_binary '',1,'정재호호','qweqwe','$2a$10$IfTNJyO9GRwYugbYWjCGKuoe4jPMh9/l0VEmzr3A8gGWt0xfMnALq','010-1234-5678','1e50530482bc4de08a756ebd8c9e9118.jpg','나는 누구인가? 바로 운동을 하기 위해 태어난 사람이다!!',NULL,NULL),(75,'2022-05-19 16:02:30.394000',NULL,'부산','강서구','녹산산업중로 333','2022-05-22','ansgml6491@naver.com',_binary '',0,'asas','asas','$2a$10$zWLhfUQOvCyqMqnvhhEEKenLoljohlg8TwMjeBNaxI8983EY9aKC.','010-1111-2222','18ffbd55d5dd48939186e3cedeac67f6.jpg','안녕!',NULL,NULL),(77,'2022-05-19 18:59:29.775000',NULL,'부산','해운대구','APEC로 17','1995-01-12','ssafy@edu.com',_binary '',1,'배소원','붕어뺭','$2a$10$rGiFtrtKkBRUeNXlgm9lkeaGWYGStCsRBdeSCClWThN8aKop6KiU2','010-3535-1230','855da0f7e0fa4516ba7b703d0105a459.jpg',NULL,NULL,NULL),(78,'2022-05-19 20:33:10.360000',NULL,'부산','강서구','가달1로 7','1993-05-16','muscles@naver.com',_binary '',0,'김근육','그뉵그뉵','$2a$10$s5L8TgpiFJErWnOxwZGpHOL.6Z2eD4dxozHAm8etr9n/CFmy5v8hC','010-2222-3333','4a4837cc72434039ab147409df545ea6.webp',NULL,NULL,NULL),(79,'2022-05-19 21:05:23.188000',NULL,'부산','해운대구','구남로 8-2','1995-05-20','son@naver.com',_binary '',0,'손길동','흥민손','$2a$10$.CwLwlLMiUKgftmpROSr1eUaJs5r0wvgepIitvVL5U.RBznLwYVk6','010-2424-3535','443c1a06345e4e0184ba3122390eaf66.jpg',NULL,NULL,NULL),(80,'2022-05-19 21:49:50.173000',NULL,'부산','강서구','가달1로 7','2022-05-04','abc@abc.abc',_binary '',0,'홍길동','페이커','$2a$10$GisEmbjVk2gGXLBko/qOXuFF7nkTDGttPXaoo1IhYqKa6qFo6iU9u','010-1234-5678','default_profile.jpeg',NULL,NULL,NULL),(81,'2022-05-19 21:52:29.753000',NULL,'부산','강서구','가달1로 7','2022-05-12','yh@naver.com',_binary '',0,'조영현','오팀장','$2a$10$xW3Kzhkh1Kt2CYE01GPrsOl.9hgSWa9AD1KTes9b4BP52Lj6to7U6','010-1234-5678','a2706596614e483cada528c76f572453.png',NULL,NULL,NULL),(82,'2022-05-19 22:07:46.390000',NULL,'부산','강서구','가달1로 7','1992-05-28','ml2439@naver.com',_binary '',0,'김현민','티벳여우','$2a$10$J1I/N2vFv84JX9YZCjWkSuKuvsW2grRySqoquQatVrVPhLWqmomHa','010-5025-2439','aed64f6520c442e0b0e64dfd4a70cd05.jpg',NULL,NULL,NULL),(83,'2022-05-19 22:20:45.575000',NULL,'부산','강서구','가달1로 7','2022-05-04','ssafy1234@naver.com',_binary '',0,'조해성','자왜칭','$2a$10$kIX.UoVNn9GaLyRG3/wkxOSvjx8Is1PfPRezODCPgKL0iLad5fOai','010-1234-5667','9369739aa9aa4e4da1af9e90efa4f374.png',NULL,NULL,NULL),(84,'2022-05-20 03:11:42.079000',NULL,NULL,NULL,NULL,NULL,'201826888@snu.ac.kr',_binary '',0,NULL,'­조해성','$2a$10$TayEofvIpu1CRVm3jxBUPu34wlh.bnXYKuSPeZ3f9i..oKDgHDWCK',NULL,'https://lh3.googleusercontent.com/a/AATXAJyNW7oSeMEjEnVNqnQRuXzdB5Z-b3zYp4T6kaAd=s96-c',NULL,'103233091658515207705',NULL),(85,'2022-05-20 03:12:31.681000',NULL,NULL,NULL,NULL,NULL,'d8958685@naver.com',_binary '',0,NULL,'대대미','$2a$10$/r6wp/aCwGKoqlIRV./JQOBE0nNh/GHzPFbkVrXbbDOr3GsFTNzHa',NULL,'default_profile.jpeg',NULL,NULL,NULL),(86,'2022-05-20 10:40:05.707000',NULL,'경기','성남시','분당구 대왕판교로606번길 45','2022-05-21','dudrl0944@gmail.com',_binary '',0,'1123','김영기','$2a$10$G0gcMn8gOD6ZKkM8yI7TO.aEQz.3qgVL.Ae/XKsDzIl8RZmYCQR/W','010-1233-1333','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',NULL,'2249679832',NULL),(87,'2022-05-20 10:44:39.464000',NULL,'경기','성남시','분당구 대왕판교로 477','2022-05-05','dncjf1969@naver.com',_binary '',0,'김철수','haha','$2a$10$NLdI0n28JseaO37KW37Yluy3jKGstKjbJg2bJ5xHONf110y2ZVwm6','010-5833-1111','default_profile.jpeg',NULL,NULL,NULL),(88,'2022-05-20 10:53:48.443000',NULL,'부산','강서구','녹산산업중로 333','2022-05-20','qsd1@naver.com',_binary '',0,'qsd1','qsd1','$2a$10$t5QS71MTpA0OkcAzsjnCnu7DO.02Yn9PMvy3cP.UQ.eAE8jz5uHDG','010-0000-0000','beaf849bcf62483db19dbfdc9970e9a5.png',NULL,NULL,NULL),(89,'2022-05-20 11:08:19.345000',NULL,'부산','수영구','감포로 2','2010-08-11','wdf1@google.com',_binary '',0,'해달','수영하는해달','$2a$10$C62W23GFjVv8n27mg3ZoHuIuwTJ.JoroUqTKsicO8/pLXKDevGD5S','010-2452-2424','58e934cecc334f6a88da283645c226ef.jpg','수영하는 해달입니다!',NULL,NULL),(90,'2022-05-20 11:21:59.157000',NULL,'부산','강서구','녹산산업중로 333','2022-05-20','sksek2@naver.com',_binary '',0,'sksek2','sksek2','$2a$10$a4kTt8MUFACWKaCTg51./uaQ65JOE5GISS2IoeGYjnr/0LLu.hjpq','010-0000-0000','70777a74e862429aacac128db970fd74.png',NULL,NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 11:41:23
