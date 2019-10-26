/*
 Navicat MySQL Data Transfer

 Source Server         : 阿里
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 120.78.162.121:3306
 Source Schema         : lingfei

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 23/10/2019 13:02:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adminUser
-- ----------------------------
DROP TABLE IF EXISTS `adminUser`;
CREATE TABLE `adminUser`  (
  `userName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adminUser
-- ----------------------------
INSERT INTO `adminUser` VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for announce
-- ----------------------------
DROP TABLE IF EXISTS `announce`;
CREATE TABLE `announce`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `picture` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announce
-- ----------------------------
INSERT INTO `announce` VALUES (1, '巴拉巴拉，测试一下公告', 'Fm1NRBjDtavnaAJhIsLTcUFSMrhG', '2019-05-30 20:58:57');
INSERT INTO `announce` VALUES (2, '测试第二个公告', 'Fk0cz_EXubO1kukWfiKXx5h2COv1', '2019-05-30 22:18:25');
INSERT INTO `announce` VALUES (3, '测试第三个公告', 'FvMexLvHEcPzdmXf-1GzVVrRzJ0e', '2019-05-30 22:18:39');
INSERT INTO `announce` VALUES (4, '测试第四个公告', 'Fofy0J1o47E32UT93vqrqAEcGsTu', '2019-05-30 22:18:52');
INSERT INTO `announce` VALUES (5, '测试第五个公告', 'FqJu_-zYnH2vgOW356Efy5774ivo', '2019-05-30 22:19:02');

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `stuClass` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `item` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (1, '韩欣彤', '软工1901', '865467399@qq.com', '15170031557', '女单');
INSERT INTO `competition` VALUES (2, '周鸿乐', '软件工程1907', '3479043434@qq.com', '17779670185', '男单');

-- ----------------------------
-- Table structure for countv
-- ----------------------------
DROP TABLE IF EXISTS `countv`;
CREATE TABLE `countv`  (
  `visitor` int(6) NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of countv
-- ----------------------------
INSERT INTO `countv` VALUES (22, '2019-05-29');
INSERT INTO `countv` VALUES (76, '2019-05-30');
INSERT INTO `countv` VALUES (25, '2019-05-31');
INSERT INTO `countv` VALUES (11, '2019-06-01');
INSERT INTO `countv` VALUES (1, '2019-06-02');
INSERT INTO `countv` VALUES (7, '2019-06-14');
INSERT INTO `countv` VALUES (3, '2019-06-16');
INSERT INTO `countv` VALUES (1, '2019-06-17');
INSERT INTO `countv` VALUES (2, '2019-06-19');
INSERT INTO `countv` VALUES (2, '2019-07-05');
INSERT INTO `countv` VALUES (2, '2019-07-07');
INSERT INTO `countv` VALUES (3, '2019-07-09');
INSERT INTO `countv` VALUES (1, '2019-07-10');
INSERT INTO `countv` VALUES (1, '2019-07-16');
INSERT INTO `countv` VALUES (2, '2019-07-17');
INSERT INTO `countv` VALUES (1, '2019-07-18');
INSERT INTO `countv` VALUES (14, '2019-09-06');
INSERT INTO `countv` VALUES (48, '2019-09-07');
INSERT INTO `countv` VALUES (74, '2019-09-08');
INSERT INTO `countv` VALUES (3, '2019-09-09');
INSERT INTO `countv` VALUES (3, '2019-09-10');
INSERT INTO `countv` VALUES (8, '2019-09-13');
INSERT INTO `countv` VALUES (2, '2019-09-15');
INSERT INTO `countv` VALUES (5, '2019-09-17');
INSERT INTO `countv` VALUES (2, '2019-09-24');
INSERT INTO `countv` VALUES (63, '2019-09-25');
INSERT INTO `countv` VALUES (11, '2019-09-26');
INSERT INTO `countv` VALUES (4, '2019-09-27');
INSERT INTO `countv` VALUES (33, '2019-09-28');
INSERT INTO `countv` VALUES (3, '2019-10-11');
INSERT INTO `countv` VALUES (35, '2019-10-12');
INSERT INTO `countv` VALUES (12, '2019-10-13');
INSERT INTO `countv` VALUES (3, '2019-10-19');
INSERT INTO `countv` VALUES (1, '2019-10-21');

-- ----------------------------
-- Table structure for money_manage
-- ----------------------------
DROP TABLE IF EXISTS `money_manage`;
CREATE TABLE `money_manage`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `reimburse` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `spend` double(30, 2) NULL DEFAULT NULL,
  `balance` double(30, 2) NULL DEFAULT NULL,
  `time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of money_manage
-- ----------------------------
INSERT INTO `money_manage` VALUES (1, '严辅朝', '张左红', '赞助收入', 520.00, 520.00, '2019/06/01');
INSERT INTO `money_manage` VALUES (2, '缪可心', '张左红', '账户余额', 198.60, 718.60, '2019/06/01');
INSERT INTO `money_manage` VALUES (3, '李景涛', '张左红', '打印海报', -30.00, 688.60, '2019/06/01');
INSERT INTO `money_manage` VALUES (4, '金子恒', '张左红', '奖牌和奖品', -118.00, 570.60, '2019/06/01');
INSERT INTO `money_manage` VALUES (5, '高逸非', '张左红', '球场费用', -160.00, 410.60, '2019/06/01');
INSERT INTO `money_manage` VALUES (6, '黄俊卿', '张左红', '羽毛球费用', -55.00, 355.60, '2019/06/01');
INSERT INTO `money_manage` VALUES (7, '高逸非', '张左红', '球场费用', -80.00, 275.60, '2019/06/01');
INSERT INTO `money_manage` VALUES (8, '李景涛', '张左红', '打印海报', -44.00, 231.60, '2019/09/05');
INSERT INTO `money_manage` VALUES (9, '张左红 林育鸿', '张左红', '拉赞助', 384.00, 615.60, '2019/09/05');
INSERT INTO `money_manage` VALUES (10, '张左红', '张左红', '复印200份传单', -60.00, 555.60, '2019/09/07');
INSERT INTO `money_manage` VALUES (11, '严辅朝', '张左红', '买一箱农夫山泉矿泉水', -38.00, 517.60, '2019/09/07');
INSERT INTO `money_manage` VALUES (12, '张左红', '张左红', '9月17日新生大会买糖果', -33.86, 483.74, '2019/09/25');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(33) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `stuClass` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `depart` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `orderCount` int(10) NULL DEFAULT NULL,
  `uuid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '13330114338', 'e10adc3949ba59abbe56e057f20f883e', '熊义杰', '8002117371', '软工1709班', '2263509062', '2263509062@qq.com', '管理层', 0, '2B10B0798AFB1AE5425BF80AB0DDFF2B');
INSERT INTO `user` VALUES (2, '13870015840', 'f61ad6c74f8f10f850d23d9f6f23d38c', '李景涛', '8002118060', '软件工程1803班', '1545858682', '2130412545@qq.com', '管理层', 1, '2');
INSERT INTO `user` VALUES (3, '13707952714', 'c030b9b47198c5d43933d1d7920726b1', '张左红', '8002118190', '软件工程1807班', '1683590919', '1683590919qq.com', '管理层', 1, '3');
INSERT INTO `user` VALUES (4, '15180453063', 'e10adc3949ba59abbe56e057f20f883e', '肖阳', '8001716072', '信息安全163班', '1348611244', '1348611244@qq.com', '预备队', 0, '4');
INSERT INTO `user` VALUES (5, '15897764130', '31a28879349130a4290bba72d3f05d0e', '严辅朝', '8003117001', '信安171班', '935978993', '935978993@qq.com', '管理层', 0, '5');
INSERT INTO `user` VALUES (7, '13308603525', '9f4458e5b63c585aac3f09ad0551a962', '张学洋', '8002118361', '软工1814', '1354257152', '1354257152@qq.com', '预备队', 2, '7');
INSERT INTO `user` VALUES (8, '18376276241', 'e9a574fd737c230461bcf35db30297c4', '潘柳明', '8002118186', '软工1807班', '1024087811', '1024087811@qq.com', '预备队', 2, '8');
INSERT INTO `user` VALUES (9, '15297891215', '82e8a543508d7f3e8f19105d328158df', '邱隆', '8002118116', '软工1805班', '750412914', '750412914@qq.com', '管理层', 0, '9');
INSERT INTO `user` VALUES (10, '18744638249', '25d55ad283aa400af464c76d713c07ad', '刘泽健', '8002119073', '软工3班', '969011434', '969011434@qq.com', '预备队', 1, '10');
INSERT INTO `user` VALUES (11, '17607028314', 'c1893b0942450362b72bab58df3ed597', '曹昊', '8003119163', '信息安全-195班', '2453075015', '2463075015@qq.com', '宣传部', 0, '11');
INSERT INTO `user` VALUES (13, '13036177660', 'ce488878e8c549d14374b447cc7b1c83', '丁一凡', '8002119151', '1905', '1781358747', '1781358747@qq.com', '其他', 0, '13');
INSERT INTO `user` VALUES (14, '15170078817', '64d84e4c6fad194a8b8a2d2b60a91d17', '廖忠昊', '8002119099', '1903班', '1255717632', '1255717632@qq.com', '其他', 0, '14');
INSERT INTO `user` VALUES (15, '15180351369', '485797c3761f6594fd0efae3847fc505', '徐劲宇', '8002119155', '软工1905班', '1186891121', '1186891121@qq.com', '宣传部', 0, '15');
INSERT INTO `user` VALUES (16, '13361655155', 'd13122e7e645397a925ce8b9cdd5c7b5', '温江璐', '8003119131', '信息安全194班', '932717185', '932717185@qq.com', '预备队', 0, '16');
INSERT INTO `user` VALUES (17, '18970260028', '974031149c8cd3d8fcc99a9db1191833', '黄昊', '8003119091', '信安193', '727990982', '727990982@qq.com', '宣传部 预备队', 1, '17');
INSERT INTO `user` VALUES (18, '15579688763', '4a9659377dff50c59a2eec693fc75654', '饶瑞军', '8003119095', '信安193', '2841158267', '2841158267@qq.com', '外联部', 0, '18');
INSERT INTO `user` VALUES (20, '18770301715', 'efe9519b5bc7a3fa2d3946bf7a500a07', '丁仁华', '8003119125', '信安194班', '1941261794', '1941261794', '组织部', 0, '20');
INSERT INTO `user` VALUES (21, '13597848124', '650e53fc44a36ede17520cfcfa53b261', '晏子翔', '8002119217', '1907', '1448852399', '1448852399@qq.com', '外联部', 1, '21');
INSERT INTO `user` VALUES (22, '18322919271', 'cf7b1140171d19b2f8cda872e7bf6e6b', '江思敏', '8002119118', '1904班', '1503628750', '1503628750@qq.com', '组织部', 0, '22');
INSERT INTO `user` VALUES (23, '18579106688', '2b2311c7e8346cf2b47952a6160c877f', '魏智翔', '8003119139', '信安195', '532453171', '532453171@qq.com', '外联部', 0, '23');
INSERT INTO `user` VALUES (24, '13257078773', '1aa364140eb7455c86ef5760763e630a', '赖世豪', '8003119109', '信安194', '1508529458', '1508529458@qq.com', '组织部', 1, '702ED62059545ABB831325654AF9AF8D');
INSERT INTO `user` VALUES (25, '13576861169', 'd9205d64c6403e56361f92c8cf56e0e3', '丁舒恒', '8002119080', '软工1903班', '1445546299', '1445546299@qq.com', '预备队', 1, '25');
INSERT INTO `user` VALUES (26, '15079425970', '55555752f3d41279a7fb7f003e096d00', '罗航', '8003119110', '信安194', '871104420', 'www.871104420@qq.com', '外联部', 2, '26');
INSERT INTO `user` VALUES (28, '18312220090', '34571399544a3dc44d8a3c87e2b517af', '段宇', '8003119002', '信安191', '274822450', '274822450@qq.com', '宣传部', 0, '28');
INSERT INTO `user` VALUES (30, '13259963329', '41ebc84e4bdbf94950100487c463d534', '李俊达', '8002119047', '软工1902', '1193553378', '1193553378@qq.com', '宣传部', 0, '30');
INSERT INTO `user` VALUES (31, '18673473233', '1969c90e9a8359cfa8d4719044e68e42', '谢欣', '8002119029', '软件工程1901', '1174889502', '1174889502@qq.com', '组织部', 0, '31');
INSERT INTO `user` VALUES (32, '18379618548', 'd4be1d57c3f6c3d6685709bf35a8ca61', '曹裕洪', '8002119043', '1902', '927465013', '927465013', '组织部', 0, '32');
INSERT INTO `user` VALUES (33, '13517076587', '01d5e5486bea7b1289ad333da20e0549', '王兆敏', '8003119135', '信安195班', '1614901456', '1614901456@qq.com', '预备队', 0, '33');
INSERT INTO `user` VALUES (34, '18460091088', '670b14728ad9902aecba32e22fa4f6bd', '陶佩尧', '8002119022', '软工1901', '2689692324', '2689692324qq.com', '预备队', 1, '0402C84A9442626AAF0C452ECEFF8DAD');
INSERT INTO `user` VALUES (35, '15170031557', '513326803917762abc2a46e343c1284d', '韩欣彤', '8002119014', '软工1901', '865467399', '865467399@qq.com', '院预备队', 0, '35');
INSERT INTO `user` VALUES (36, '15593471199', '39d910b5898c57833a430699fb26d27e', '宋栎', '8002119145', '1905', '1163304077', '1163304077@qq.com', '预备队', 0, '36');
INSERT INTO `user` VALUES (37, '13036323486', '5956429ed60efb8c7899897d02db922f', '涂峻源', '8003119044', '信安192班', '3181314097', '3181314097@qq.com', '预备队', 0, '37');
INSERT INTO `user` VALUES (39, '13979962354', '9982308e688e9db1b9d468bfc8e0aa0f', '谢益', '8000116393', '卓越162', '1175200517', '1175200517@qq.com ', '管理层', 0, '39');
INSERT INTO `user` VALUES (40, '15584416653', '827ccb0eea8a706c4c34a16891f84e7b', '李俊翰', '8003119062', '1902信安', '1582324292', '1582324292@qq.com', '预备队', 0, '40');
INSERT INTO `user` VALUES (41, '13133808660', 'bd1c1b37f067e3705f5d83fe4c3feda0', '严祎迪', '8002119216', '软件工程1907班', '2682587586', '2682587506@qq.com', '预备队', 0, '41');
INSERT INTO `user` VALUES (42, '18543183768', '6dac9cfb75b1b3330a18c353597c1299', '凌霄', '8002119285', '软工1909班', '1411628487', '1411628487@qq.com', '组织部', 1, '42');
INSERT INTO `user` VALUES (43, '13079252979', 'bcc12208de940ee130b55ced6bd447a7', '崇杜飞', '8002119140', '软工1905', '2652510409', '2652510409@qq.com', '预备队', 0, '43');
INSERT INTO `user` VALUES (44, '13870176024', '11226d644cf9ed4dc7367d8be12aa014', '翁松羽', '8002119286', '1909', '2365022043', '2365022043', '预备队', 0, '44');
INSERT INTO `user` VALUES (45, '13361656180', '4a7c70b57fe183065f1a8e414808c8f3', '余陈嘉骏', '8003119073', '信息安全193班', '1146543617', '1146543617@qq.com', '组织部', 0, '45');
INSERT INTO `user` VALUES (46, '18207042723', '0da4ed65caa98c878743fe59d5658fa0', '龙文胜', '8003119177', '信安196', '1154600854', '1154600854@qq.com', '外联部', 0, '46');
INSERT INTO `user` VALUES (48, '15605057383', '57610907dfd2fdb0892bc6f7baa886da', '富奕佳', '8003119004', '信息安全191', '3306468354', '3306468354@qq.com', '组织部', 0, '48');
INSERT INTO `user` VALUES (49, '15079633879', 'ea9903e149d593f48e5924f0260c91d1', '聂洋', '8002119087', '2019软件工程03班', '2513383960', '2513383960@.qq.com', '组织部', 0, '49');
INSERT INTO `user` VALUES (50, '13979190825', '429a058045660f96688eda57004625cd', '李昊汉', '8003119127', '信安194', '353848645', 'haohan321@qq.com', '外联部', 1, '50');
INSERT INTO `user` VALUES (51, '18507920918', '5d60c4c7091397077a61f2d035650e57', '聂梦龙', '8002119184', '软件工程1906', '2191222980', '2191222980@qq.com', '组织部', 0, '51');
INSERT INTO `user` VALUES (52, '13361655528', 'f410d041e1dfd46969431bb71c9dd812', '赵艺爽', '8002119084', '1903', '1342831159', '1342831159@qq.com', '组织部', 1, '52');
INSERT INTO `user` VALUES (53, '18159555559', '1428f1d23d6bdc03a8f106c3c40d2cbf', '齐逸信', '8002119199', '软件7斑', '3175278073', '3175278073qq. com', '技术部', 0, '53');
INSERT INTO `user` VALUES (54, '13155643669', '4606b2859c732537e9a51d97970d819a', '王之笛', '8003119112', '信安194', '3511965747', '3511965747', '预备队', 0, '54');
INSERT INTO `user` VALUES (55, '18179264655', '350a39102506dd30975fd3e224dcce38', '周文', '8002119138', '1905', '2732869217', '2732869217@qq.com', '组织部', 0, '55');
INSERT INTO `user` VALUES (56, '13149563156', 'bdedc4841257731043a3f1ebb2cfd2b6', '邹世伟', '8003119080', '信安193班', '1245607680', '1245607680@qq.com', '组织部', 0, '56');
INSERT INTO `user` VALUES (57, '19955331218', '643201eb2980f8b2ccdb41611feee128', '郭秋鸣', '8003119032', '信息安全191', '1851434998', '1851434998@qq.com', '组织部', 0, '57');
INSERT INTO `user` VALUES (58, '17779670185', 'f50159600a269c5e390ca491668a8506', '周鸿乐', '8002119220', '软件工程1907', '3479043434', '无', '组织部', 0, '58');
INSERT INTO `user` VALUES (59, '13476880171', '0990b12c6862750b6045afba4956d09a', '钱何微', '8002119323', '软工1910', '1447561087', '无', '预备队', 1, '59');
INSERT INTO `user` VALUES (60, '15579897517', '4bc3d7af559450bb204896b033d6d778', '刘美林', '8002119370', '软件1912', '572394540', '572394540@qq.com', '宣传部', 0, '60');
INSERT INTO `user` VALUES (61, '13367001557', '631818e594388b15ec1063f73302d36f', '沈宇东', '8002118118', '软件工程1805班', '1923480447', '1923480447@qq.com', '宣传部', 0, '61');
INSERT INTO `user` VALUES (62, '13576518943', 'e86be34307dd4f25987b63c6647f5251', '易生辉', '8002119059', '软件工程1902班', '1440303658', '1440303658@qq.com', '宣传部', 0, '62');
INSERT INTO `user` VALUES (63, '18370066598', '23990f9cfb3e710627114f2b1551bfbb', '吴赵珑', '8003119185', '信息安全196班', '2576048562', '2576048562@qq.com', '外联部', 1, '08EE127CD5FB56745EE89B1A76875EDB');
INSERT INTO `user` VALUES (64, '13576264410', '8b321435b02d69625285beb430a17ee7', '廖培文', '8002119271', '软件工程1909', '1048450542', '1048450542', '组织部', 0, '64');
INSERT INTO `user` VALUES (65, '13879681006', 'a5e2f7388b1c8eb900b50614b018dc9f', '周承志', '8002119265', '软件学院1909班', '2849726391', '2849726391@qq.com', '组织部', 0, '65');
INSERT INTO `user` VALUES (66, '17695480988', 'b8677f6338e78061fab13c877d8180a1', '宋泽轩', '3008119007', '信息安全1901', NULL, '516451799@qq.com', '外联部', 0, '66');
INSERT INTO `user` VALUES (67, '13755932633', 'daff55cbe64a08cd58725c65417a3213', '谢斯敬', '8002119036', '软工1902', '2294248508', '2294248508@qq.com', '其他', 0, '67');
INSERT INTO `user` VALUES (68, '18070502876', '36f34f4f5e9de15359ee035b3a0203dc', '周建宇', '8002119153', '软件工程1905班', NULL, '1476869913@qq.com', '预备队', 0, '68');
INSERT INTO `user` VALUES (73, '18070502592', '4606b2859c732537e9a51d97970d819a', '王之笛', '8003119112', '信安194', '3511965747', '3511965747@qq.com', '预备队', 0, '73');
INSERT INTO `user` VALUES (74, '13367459063', 'eaca2b66ed3ebe90393f7a8fde159c1d', '向震', '8002119222', '软工1907', '2736898716', 'ndcjacmak1@outlook.com', '外联部', 0, '74');
INSERT INTO `user` VALUES (75, '13361655328', 'ec92715e306df7aa6b01091655ff00d1', '林育鸿', '8003118089', '信息安全183班', '690243954', '690243954@qq.com', '外联部', 2, '75');
INSERT INTO `user` VALUES (76, '15711137522', '48f40e569bb9094e315ac8fb2fddc7fa', '欧阳澳', '8002118360', '软工1814班', '1176314732', '1176314732@qq.com', '预备队', 1, '76');

SET FOREIGN_KEY_CHECKS = 1;
