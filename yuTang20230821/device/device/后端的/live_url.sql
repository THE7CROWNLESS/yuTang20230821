/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 09/09/2023 10:29:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for live_url
-- ----------------------------
DROP TABLE IF EXISTS `live_url`;
CREATE TABLE `live_url`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `channel_number` int(11) NULL DEFAULT NULL,
  `hls_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of live_url
-- ----------------------------
INSERT INTO `live_url` VALUES (1, 'AD2940441', 1, 'https://open.ys7.com/v3/openlive/AD2940441_1_1.m3u8?expire=1725066689&id=619459651053817856&t=40200b26b8ae2bcf21b0e60740161e528de1e693d8ac22eb5c49468ef52fecb4&ev=100');
INSERT INTO `live_url` VALUES (2, 'AF0137250', 1, 'https://open.ys7.com/v3/openlive/AF0137250_1_1.m3u8?expire=1725066463&id=619458703506022400&t=af37251fb40368ebd3eff4e5a28488cd77ae7924a234a9479b6643ad72cd7c88&ev=100');
INSERT INTO `live_url` VALUES (3, 'AF0137253', 1, 'https://open.ys7.com/v3/openlive/AF0137253_1_1.m3u8?expire=1725066487&id=619458800746758145&t=00717c08eb8b1d13cb103e4d3add84e7fd05ee50d8303e26fefd480a0eec2507&ev=100');
INSERT INTO `live_url` VALUES (4, 'AF3781307', 1, 'https://open.ys7.com/v3/openlive/AF3781307_1_1.m3u8?expire=1724910053&id=618802671305891840&t=37bec6906cd4d6c3eb3c4e72418ec87cf5db0dcdfbecb4ef15b88fae81693165&ev=100');
INSERT INTO `live_url` VALUES (5, 'AF3781311', 1, NULL);
INSERT INTO `live_url` VALUES (6, 'L50785309', 1, 'https://open.ys7.com/v3/openlive/L50785309_1_1.m3u8?expire=1725241430&id=620192566171668480&t=99ea2750b625afec38cb58e65619a65af59ce3a99ace39c6756dfaacb4ddc2ba&ev=100');

SET FOREIGN_KEY_CHECKS = 1;
