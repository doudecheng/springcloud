/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rest_demo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-29 18:03:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for information_demo
-- ----------------------------
DROP TABLE IF EXISTS `information_demo`;
CREATE TABLE `information_demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information_demo
-- ----------------------------
INSERT INTO `information_demo` VALUES ('1', '主力已抢疯！错过了白马 别再错过超额收益的它！', '高增长的公司相对于行业便有较明显的超额收益。其认为，当前时点投资者已可以开始布局中报高增长预期的公司。\n</p>\n<p>\n	　　长江证券观点指出，在过去5年中，整体上高增长的股票在财报披露前20天(T-20)即开始有小幅的超额收益，在财报披露前一周开始，超额收益开始迅速上升，累计相对于行业大概有1.5%左右的超额收益，在财报披露之后，超额收益反而会短时间内下降。\n</p>\n<img title=\"image006.jpg \" src=\"http://z1.dfcfw.com/2017/7/7/201707070803311507423290.jpg\" width=\"549\" /> \n<p>\n	　　长江证券表示，在当前时间点，投资者应该着手开始对中报有高增长预期公司的布局，预计在7、8月份，中报高增长行业与公司均将有稳定的超额收益。\n</p>\n<img title=\"image008.jpg \" src=\"http://z1.dfcfw.com/2017/7/7/201707070803402058699269.jpg\" width=\"550\" /> \n<p>\n	　　有券商观点表示，从本轮行情由“漂亮50”向众多细分行业龙头个股的扩散情况来看，机构资金正按照业绩与估值相匹配的逻辑，挖掘更多性价比更高的二线价值成长股。在此背景下，估值合理、行业盈利大幅改善、上市公司业绩普遍增长的周期性板块也因此受其青睐。\n</p>');
INSERT INTO `information_demo` VALUES ('2', '近三年仅5家公司被A股“辞退” 退市制度需要“铁腕', 'om/news/1354,20170707753784288.html\">23年A股路 新都酒店留下“铁公鸡”、“不死鸟”、“小滑头”三大标签</a> \n</p>\n<p>\n	<span>　　</span><a target=\"_blank\" href=\"http://finance.eastmoney.com/news/1355,20170707753784191.html\">“000033”新都退：留下三大标签 “退”到三板</a> \n</p>\n<span>　　</span><a target=\"_blank\" href=\"http://finance.eastmoney.com/news/1354,20170707753782368.html\">山东信托陷多事之秋：前脚“踩雷”新都退 后脚副总落马</a>');
INSERT INTO `information_demo` VALUES ('3', '近三年仅5家公司被A股“辞退” 退市制度需要“铁腕', 'om/news/1354,20170707753784288.html\">23年A股路 新都酒店留下“铁公鸡”、“不死鸟”、“小滑头”三大标签</a> \n</p>\n<p>\n	<span>　　</span><a target=\"_blank\" href=\"http://finance.eastmoney.com/news/1355,20170707753784191.html\">“000033”新都退：留下三大标签 “退”到三板</a> \n</p>\n<span>　　</span><a target=\"_blank\" href=\"http://finance.eastmoney.com/news/1354,20170707753782368.html\">山东信托陷多事之秋：前脚“踩雷”新都退 后脚副总落马</a>');
INSERT INTO `information_demo` VALUES ('4', '1212', '2112131');
INSERT INTO `information_demo` VALUES ('5', 'dfdsafasdfasfasdfas', 'dfsadfdsafasfsaf');
INSERT INTO `information_demo` VALUES ('6', 'fffffffffffffffhgggggggggggggggg', 'dfjuewrfhfdgdsfgds');
INSERT INTO `information_demo` VALUES ('7', 'sdfsadfasfsafdsafsafsa', 'safsafsafsafsafsafas');
INSERT INTO `information_demo` VALUES ('8', 'safsafdsafsafsafas', 'hfhfdgdfsgsdgdsgfds');
INSERT INTO `information_demo` VALUES ('9', 'sdfsafsafsafa', 'fgsfsafsafdsadfsafasf');
INSERT INTO `information_demo` VALUES ('10', 'sfsafsafsafddsaf', 'sfsafdasfsafsafsaf');
INSERT INTO `information_demo` VALUES ('11', 'sfsafdsafsafddsafdsa', 'asfsafsafsafsafsafdasfasfasfasf');

-- ----------------------------
-- Table structure for member_demo
-- ----------------------------
DROP TABLE IF EXISTS `member_demo`;
CREATE TABLE `member_demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_demo
-- ----------------------------
INSERT INTO `member_demo` VALUES ('1', 'test', '123456', '11', '1');
INSERT INTO `member_demo` VALUES ('2', 'andy', '111111', '12', '2');
INSERT INTO `member_demo` VALUES ('3', 'caolisong', '111111', null, null);
INSERT INTO `member_demo` VALUES ('6', '649251829', '123456', null, null);
INSERT INTO `member_demo` VALUES ('10', '111', '111', null, null);
INSERT INTO `member_demo` VALUES ('11', 'dff', 'ffff', null, null);
INSERT INTO `member_demo` VALUES ('12', 'fdd', 'fff', null, null);
INSERT INTO `member_demo` VALUES ('13', 'd', 'ff', null, null);
INSERT INTO `member_demo` VALUES ('15', 'f', 'f', null, null);
INSERT INTO `member_demo` VALUES ('17', null, null, null, null);
INSERT INTO `member_demo` VALUES ('18', null, null, null, null);
INSERT INTO `member_demo` VALUES ('19', null, null, null, null);
INSERT INTO `member_demo` VALUES ('20', null, null, null, null);
INSERT INTO `member_demo` VALUES ('21', null, null, null, null);
INSERT INTO `member_demo` VALUES ('22', null, null, null, null);
INSERT INTO `member_demo` VALUES ('23', null, null, null, null);
INSERT INTO `member_demo` VALUES ('24', null, null, null, null);
INSERT INTO `member_demo` VALUES ('25', null, null, null, null);
INSERT INTO `member_demo` VALUES ('26', null, null, null, null);
INSERT INTO `member_demo` VALUES ('27', null, null, null, null);
INSERT INTO `member_demo` VALUES ('28', null, null, null, null);
INSERT INTO `member_demo` VALUES ('29', null, null, null, null);
INSERT INTO `member_demo` VALUES ('30', null, null, null, null);
INSERT INTO `member_demo` VALUES ('31', null, null, null, null);
INSERT INTO `member_demo` VALUES ('32', null, null, null, null);
INSERT INTO `member_demo` VALUES ('33', null, null, null, null);
INSERT INTO `member_demo` VALUES ('34', null, null, null, null);
INSERT INTO `member_demo` VALUES ('35', null, null, null, null);
INSERT INTO `member_demo` VALUES ('36', null, null, null, null);
INSERT INTO `member_demo` VALUES ('37', null, null, null, null);
INSERT INTO `member_demo` VALUES ('38', 'ff', 'ff', null, null);
INSERT INTO `member_demo` VALUES ('39', 'fff', 'fff', null, null);
INSERT INTO `member_demo` VALUES ('40', 'ffff', 'ffff', null, null);

-- ----------------------------
-- Table structure for tb_sys_member
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_member`;
CREATE TABLE `tb_sys_member` (
  `id` varchar(64) NOT NULL,
  `mem_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `mem_pwd` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `mem_qq` varchar(20) DEFAULT NULL COMMENT 'QQ号',
  `mem_mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `mem_headimg_url` varchar(500) DEFAULT NULL COMMENT '用户头像URL',
  `mem_email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mem_trueName` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `mem_type` varchar(3) DEFAULT NULL COMMENT '用户类型（01：平台用户、02：业务人员）',
  `mem_status` tinyint(2) DEFAULT NULL COMMENT '审核状态（1：启用、2：停用）',
  `mem_main_style` varchar(100) DEFAULT NULL COMMENT '用户首页风格',
  `mem_parent_id` varchar(64) DEFAULT NULL COMMENT '上级用户',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `remarks` varchar(500) DEFAULT NULL COMMENT '注释',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户主表，相关资料存储';

-- ----------------------------
-- Records of tb_sys_member
-- ----------------------------
INSERT INTO `tb_sys_member` VALUES ('2454ebface9011e7b2db1c1b0d2e9fd7', 'admin', null, '11111111', null, null, '6492851829@qq.com', null, null, null, null, null, '2017-11-21 16:18:28', null, null, null, null, null);
INSERT INTO `tb_sys_member` VALUES ('2795452ece9411e7b2db1c1b0d2e9fd7', 'xiaolang', null, '234214324', null, null, 'ewrfewfef', null, null, null, null, null, '2017-11-21 16:17:33', null, null, null, null, null);
INSERT INTO `tb_sys_member` VALUES ('449555f1cf2c11e7875c1c1b0d2e9fd7', 'marie', null, '841358521', null, null, '99999999999', null, null, null, null, null, '2017-11-22 10:24:30', null, null, null, null, null);
INSERT INTO `tb_sys_member` VALUES ('4e14cc40ce8a11e7b2db1c1b0d2e9fd7', 'refreg', null, '4545455', null, null, '6492851829@qq.com', null, null, null, null, null, '2017-11-22 10:32:46', null, null, null, null, null);
INSERT INTO `tb_sys_member` VALUES ('c50ed8a7cf5111e7875c1c1b0d2e9fd7', 'superadmin', null, '649251829', null, null, '666', null, null, null, null, null, '2017-11-22 14:52:57', null, null, null, null, null);
