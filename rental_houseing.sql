/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : rental_houseing

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-09-06 21:41:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'maowenrui', '123456');
INSERT INTO `admin` VALUES ('2', 'tianjiashuo', '123456');
INSERT INTO `admin` VALUES ('3', 'liqinying', '123456');
INSERT INTO `admin` VALUES ('4', 'baijiaxin', '123456');

-- ----------------------------
-- Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `house_id` int NOT NULL,
  `house_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('1', '1', '1', '1');
INSERT INTO `collection` VALUES ('2', '1', '2', '1');
INSERT INTO `collection` VALUES ('3', '3', '2', '2');

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `house_id` int NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `house_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('1', '1', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('2', '2', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('3', '3', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('4', '4', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('5', '1', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '2');
INSERT INTO `image` VALUES ('6', '2', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '2');
INSERT INTO `image` VALUES ('7', '3', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '2');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '1', '您好，请您关注发布的房产信息  ——管理员');
INSERT INTO `news` VALUES ('2', '2', '您好，请您关注收藏的的房产信息  ——管理员');
INSERT INTO `news` VALUES ('3', '3', '您好，您收藏的房产信息有变更 ——管理员');

-- ----------------------------
-- Table structure for `rent`
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `host_id` int NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `type` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `orientation` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `floor` int NOT NULL,
  `is_elevator` tinyint NOT NULL,
  `is_pet` tinyint NOT NULL,
  `shortest_lease` int NOT NULL,
  `area` int NOT NULL,
  `furniture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `price` int NOT NULL,
  `state` tinyint NOT NULL,
  `form` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of rent
-- ----------------------------
INSERT INTO `rent` VALUES ('1', '3', '津南新城合雅苑', '津南新城合雅园  离海教园图书馆近 交通便捷', '2室两厅', '南北朝向', '5', '1', '0', '6', '100', '家具齐全，拎包入住', '1700', '0', '0');
INSERT INTO `rent` VALUES ('2', '4', '河西 柳林', '精装修隔音好，房租押一付一，无中介', '4室1厅1卫', '东西朝向', '6', '1', '1', '12', '14', '家具齐全，拎包入住', '960', '0', '1');
INSERT INTO `rent` VALUES ('3', '4', '和平区 南营门', '天南地北海角天涯 不管在哪里 温馨的小窝都是你避风的港湾', '2室1厅1卫', '南北', '2', '0', '0', '1', '15', '家具齐全，拎包入住', '830', '0', '1');

-- ----------------------------
-- Table structure for `report`
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `house_id` int NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `result` tinyint NOT NULL,
  `house_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('1', '1', '1', '涉黄', '0', '1');
INSERT INTO `report` VALUES ('2', '2', '2', '涉爆', '1', '2');

-- ----------------------------
-- Table structure for `roommates_info`
-- ----------------------------
DROP TABLE IF EXISTS `roommates_info`;
CREATE TABLE `roommates_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `house_id` int NOT NULL,
  `user_id` int NOT NULL,
  `state` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of roommates_info
-- ----------------------------
INSERT INTO `roommates_info` VALUES ('1', '1', '1', '1');
INSERT INTO `roommates_info` VALUES ('2', '2', '2', '0');
INSERT INTO `roommates_info` VALUES ('3', '3', '3', '1');
INSERT INTO `roommates_info` VALUES ('4', '4', '4', '0');

-- ----------------------------
-- Table structure for `sell`
-- ----------------------------
DROP TABLE IF EXISTS `sell`;
CREATE TABLE `sell` (
  `id` int NOT NULL AUTO_INCREMENT,
  `host_id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `property` tinyint(1) NOT NULL,
  `type` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `orientation` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `floor` int NOT NULL,
  `is_renovation` tinyint NOT NULL,
  `is_elevator` tinyint NOT NULL,
  `area` int NOT NULL,
  `price` int NOT NULL,
  `state` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sell
-- ----------------------------
INSERT INTO `sell` VALUES ('1', '1', '海河边江景房/全落地窗俯瞰海河风光', ' 天津和平区', '1', '2室一厅', '东西朝向', '12', '1', '1', '120', '1300000', '0');
INSERT INTO `sell` VALUES ('2', '1', '铭宿-长城脚下的小院', '北京·平房里的独立房间', '1', '1室一厅', '南北朝向', '1', '0', '0', '80', '1000000', '1');
INSERT INTO `sell` VALUES ('3', '2', '中国铁建国际城诗景颂苑', '天津市河北区', '1', '2室两厅', '南北朝向', '5', '1', '1', '90', '130000', '0');
INSERT INTO `sell` VALUES ('4', '3', '板楼南北通透， 户型方正，商品房精装修拎包住', '天津市河北区', '1', '3室两厅', '南北朝向', '7', '1', '1', '160', '1500000', '0');

-- ----------------------------
-- Table structure for `shareflat_info`
-- ----------------------------
DROP TABLE IF EXISTS `shareflat_info`;
CREATE TABLE `shareflat_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `house_id` int NOT NULL,
  `allowed_number` int NOT NULL,
  `current_number` int NOT NULL,
  `requirement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of shareflat_info
-- ----------------------------
INSERT INTO `shareflat_info` VALUES ('1', '1', '2', '1', '限男性');
INSERT INTO `shareflat_info` VALUES ('2', '2', '5', '3', '不可以养宠物');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `IDnumber` char(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'c:/pic/mao', 'maowenrui', '我是毛文瑞', '123456', '15378965067', '0', '111111');
INSERT INTO `user` VALUES ('2', 'c:/pic/tian', 'tianjiashuo', '我是田家硕', '123456', '15378965068', '1', '222222');
INSERT INTO `user` VALUES ('3', 'c:/pic/li', 'liqinying', '我是李青颖', '123456', '15378965069', '1', '333333');
INSERT INTO `user` VALUES ('4', 'c:/pic/bai', 'baijiaxin', '我是白嘉欣', '123456', '15378965070', '1', '333333');