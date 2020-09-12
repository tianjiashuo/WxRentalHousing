/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : rental_housing

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-09-12 10:50:50
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
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `house_id` int NOT NULL,
  `house_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of collection
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('1', '1', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('2', '2', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('3', '3', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('4', '4', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '1');
INSERT INTO `image` VALUES ('5', '1', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '0');
INSERT INTO `image` VALUES ('6', '2', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '0');
INSERT INTO `image` VALUES ('7', '3', 'https://z1.muscache.cn/im/pictures/d9408889-273a-45fb-a6ff-7012930be9af.jpg?aki_policy=large', '0');
INSERT INTO `image` VALUES ('8', '1', 'http://127.0.0.1', '0');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `rent`
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `host_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
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

-- ----------------------------
-- Table structure for `report`
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `house_id` int NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `result` tinyint NOT NULL,
  `house_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of report
-- ----------------------------

-- ----------------------------
-- Table structure for `roommates_info`
-- ----------------------------
DROP TABLE IF EXISTS `roommates_info`;
CREATE TABLE `roommates_info` (
  `id` int NOT NULL,
  `house_id` int NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `state` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of roommates_info
-- ----------------------------

-- ----------------------------
-- Table structure for `sell`
-- ----------------------------
DROP TABLE IF EXISTS `sell`;
CREATE TABLE `sell` (
  `id` int NOT NULL,
  `host_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sell
-- ----------------------------

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
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `IDnumber` char(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------




package com.dai7.util;

 


 @ResponseBody

    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)

    public Map decodeUserInfo(String encryptedData, String iv, String code) {
        Map map = new HashMap(); 
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "";
        //授权（必填）
        String grant_type = "authorization_code";
         1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid 
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
         2、对encryptedData加密数据进行AES解密 
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }

<!-- JSONObject对象依赖的jar包 -->  
        <dependency>  
            <groupId>commons-beanutils</groupId>  
            <artifactId>commons-beanutils</artifactId>  
            <version>1.9.3</version>  
        </dependency>  
        <dependency>  
            <groupId>commons-collections</groupId>  
            <artifactId>commons-collections</artifactId>  
            <version>3.2.1</version>  
        </dependency>  
        <dependency>  
            <groupId>commons-lang</groupId>  
            <artifactId>commons-lang</artifactId>  
            <version>2.6</version>  
        </dependency>  
        <dependency>  
            <groupId>commons-logging</groupId>  
            <artifactId>commons-logging</artifactId>  
            <version>1.1.1</version> 
        </dependency>  
        <dependency>  
            <groupId>net.sf.ezmorph</groupId>  
            <artifactId>ezmorph</artifactId>  
            <version>1.0.6</version>  
        </dependency>  
        <dependency>  
            <groupId>net.sf.json-lib</groupId>  
            <artifactId>json-lib</artifactId>  
            <version>2.2.3</version>  
            <classifier>jdk15</classifier><!-- 指定jdk版本 -->  
        </dependency>  
        <!-- Json依赖架包下载 -->  
