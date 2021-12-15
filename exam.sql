/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 08/12/2020 15:04:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `time_limit` int(11) DEFAULT NULL COMMENT '考试时长',
  `start_time` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL COMMENT '试卷状态（1、已发布，出卷中2、未开始，3、已开始，4、已结束）',
  `teacher_id` int(11) DEFAULT NULL COMMENT '出卷老师id',
  `single_points` double DEFAULT NULL,
  `multi_points` double DEFAULT NULL,
  `judge_points` double DEFAULT NULL,
  `valid` tinyint(1) DEFAULT NULL COMMENT '状态(0删除，1有效) 默认1',
  `end_time` datetime DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '1 开始时间立即考试 2 开始和结束时间范围内考完即可',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of exam
-- ----------------------------
BEGIN;
INSERT INTO `exam` VALUES (1, '项目管理', 60, '2020-11-19 08:00:00', 1, 1, 2, 4, 1, 0, '2020-11-20 08:00:00', 1);
INSERT INTO `exam` VALUES (4, 'JAVA基础', 120, '2020-11-21 08:00:00', 2, 1, 2.5, 4.5, 2, 1, '2020-11-23 08:00:00', 2);
INSERT INTO `exam` VALUES (5, '测试延迟任务', 1, '2020-11-22 08:00:00', 2, 1, 2, 4, 1, 1, '2020-11-22 08:01:00', 1);
INSERT INTO `exam` VALUES (6, '测试', 1, '2020-11-22 00:32:00', 4, 1, 2, 4, 1, 1, '2020-11-22 00:33:00', 1);
INSERT INTO `exam` VALUES (7, 'donggau', 1, '2020-11-22 08:16:00', 2, 1, 2, 4, 1, 1, '2020-11-22 08:17:02', 1);
INSERT INTO `exam` VALUES (13, '最终测试', 1, '2020-11-22 00:44:00', 4, 1, 2, 4, 1, 1, '2020-11-22 00:45:02', 1);
INSERT INTO `exam` VALUES (14, '编辑', 60, '2020-11-26 14:01:53', 1, 1, 2, 4, 1, 1, '2020-11-26 15:01:55', 1);
INSERT INTO `exam` VALUES (15, '咕咕', 60, '2020-12-03 09:24:28', 3, 1, 2, 4, 1, 0, '2020-12-03 10:24:31', 1);
INSERT INTO `exam` VALUES (16, 'asdfsda', 60, '2020-12-07 15:48:08', 1, 1, 2, 4, 1, 1, '2020-12-07 16:48:08', 1);
COMMIT;

-- ----------------------------
-- Table structure for exam_class
-- ----------------------------
DROP TABLE IF EXISTS `exam_class`;
CREATE TABLE `exam_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of exam_class
-- ----------------------------
BEGIN;
INSERT INTO `exam_class` VALUES (1, 1, 1, 0);
INSERT INTO `exam_class` VALUES (2, 1, 2, 0);
INSERT INTO `exam_class` VALUES (5, 4, 1, 0);
INSERT INTO `exam_class` VALUES (6, 4, 2, 0);
INSERT INTO `exam_class` VALUES (7, 5, 1, 1);
INSERT INTO `exam_class` VALUES (8, 6, 2, 1);
INSERT INTO `exam_class` VALUES (9, 7, 2, 0);
INSERT INTO `exam_class` VALUES (15, 13, 2, 1);
INSERT INTO `exam_class` VALUES (16, 13, 1, 0);
INSERT INTO `exam_class` VALUES (17, 13, 2, 0);
INSERT INTO `exam_class` VALUES (18, 14, 1, 0);
INSERT INTO `exam_class` VALUES (19, 14, 2, 0);
INSERT INTO `exam_class` VALUES (20, 5, 1, 0);
INSERT INTO `exam_class` VALUES (21, 15, 1, 0);
INSERT INTO `exam_class` VALUES (22, 6, 2, 1);
INSERT INTO `exam_class` VALUES (23, 6, 2, 0);
INSERT INTO `exam_class` VALUES (24, 16, 2, 0);
INSERT INTO `exam_class` VALUES (25, 16, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for exam_record
-- ----------------------------
DROP TABLE IF EXISTS `exam_record`;
CREATE TABLE `exam_record` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '做题记录id',
  `result_id` int(4) DEFAULT NULL COMMENT '考试结果id',
  `questions_number` int(4) DEFAULT NULL COMMENT '题号',
  `answer` varchar(50) DEFAULT NULL COMMENT '学生答案',
  `qtype` tinyint(1) DEFAULT NULL COMMENT '题目类型（非外键）1 单选 2 多选 3判断',
  `exam_id` int(4) DEFAULT NULL COMMENT '试卷id',
  `question_id` int(4) DEFAULT NULL COMMENT '题目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of exam_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for exam_result
-- ----------------------------
DROP TABLE IF EXISTS `exam_result`;
CREATE TABLE `exam_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `point` double DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `exam_title` varchar(50) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL COMMENT '1. 未考试 2 已完成 3 逾期未考 4 考试中 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of exam_result
-- ----------------------------
BEGIN;
INSERT INTO `exam_result` VALUES (1, 6, '2020-11-20 12:48:22', '2020-11-20 14:48:26', '项目管理', 1, 2, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for exam_result_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_result_question`;
CREATE TABLE `exam_result_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right` tinyint(1) DEFAULT NULL COMMENT '是否正确 1正确 0 错误',
  `wrong_answer` varchar(20) DEFAULT NULL COMMENT '错误答案 单选 和多选填写选项，判断填写『对』或『错』',
  `right_answer` varchar(20) DEFAULT NULL COMMENT '正确答案',
  `score` double DEFAULT NULL,
  `exam_result_id` int(11) DEFAULT NULL COMMENT '考试结果ID',
  `question_id` int(11) DEFAULT NULL COMMENT '题目号',
  `qtype` tinyint(1) DEFAULT NULL COMMENT '题目类型（非外键）1 单选 2 多选 3判断',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of exam_result_question
-- ----------------------------
BEGIN;
INSERT INTO `exam_result_question` VALUES (1, 1, NULL, 'B', 2, 1, 1, 1, 0);
INSERT INTO `exam_result_question` VALUES (2, 0, 'B', 'A', 0, 1, 2, 1, 0);
INSERT INTO `exam_result_question` VALUES (3, 1, NULL, 'A,B,C', 4, 1, 1, 2, 0);
INSERT INTO `exam_result_question` VALUES (4, 0, 'B,C,D', 'A,C,D', 0, 1, 3, 2, 0);
INSERT INTO `exam_result_question` VALUES (5, 1, NULL, '对', 1, 1, 1, 3, 0);
COMMIT;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of grade
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES (1, '2020', 0);
COMMIT;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of major
-- ----------------------------
BEGIN;
INSERT INTO `major` VALUES (1, '计算机科学与技术', 0);
COMMIT;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `modified` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of manager
-- ----------------------------
BEGIN;
INSERT INTO `manager` VALUES (1, 'admin', 'f6fdffe48c908deb0f4c3bd36c032e72', 0);
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '访问路径',
  `icon` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标',
  `pid` int(9) DEFAULT NULL COMMENT '父节点ID',
  `topid` int(9) DEFAULT NULL COMMENT '顶级节点ID',
  `menu_level` int(1) DEFAULT NULL COMMENT '菜单级别',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `hidden` int(1) DEFAULT NULL COMMENT '是否隐藏（0显示，1隐藏）',
  `type` int(4) DEFAULT NULL COMMENT '类型 0 菜单 1 方法',
  `code` varchar(50) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, '主页', 'index', '', NULL, NULL, 1, 1, 0, 0, 'home');
INSERT INTO `menu` VALUES (2, '年级管理', '', 'fa-building', 13, NULL, 1, 2, 0, 0, 'grade');
INSERT INTO `menu` VALUES (3, '专业管理', '', 'fa-graduation-cap', 13, NULL, 1, 3, 0, 0, 'major');
INSERT INTO `menu` VALUES (4, '班级管理', '', 'fa-cube', 13, NULL, 1, 4, 0, 0, 'clazz');
INSERT INTO `menu` VALUES (5, '学生管理', '', 'fa-users', 13, NULL, 1, 5, 0, 0, 'student');
INSERT INTO `menu` VALUES (6, '教师管理', 'manager/teaList', 'fa-user', 13, NULL, 1, 6, 0, 0, 'teacher');
INSERT INTO `menu` VALUES (7, '试卷管理', '/teacher/toExamTable', 'fa fa-window-maximize', 14, NULL, 1, 7, 0, 0, 'exam');
INSERT INTO `menu` VALUES (8, '考试记录', '/exam/toExamRecord', 'fa fa-gears', 14, NULL, 1, 8, 0, 0, 'exam:marked');
INSERT INTO `menu` VALUES (11, '参加考试', '/student/toStudentExam', 'fa-pencil-square-o ', 15, NULL, 1, 11, 0, 0, 'exam:in');
INSERT INTO `menu` VALUES (12, '考试记录', 'student/toStudentRecord', 'fa-file-word-o', 15, NULL, 1, 12, 0, 0, 'exam:list');
INSERT INTO `menu` VALUES (13, '管理员模块', ' ', 'fa fa-address-book', 0, NULL, 0, 1, 0, 0, 'MANAGER');
INSERT INTO `menu` VALUES (14, '教师模块', ' ', 'fa fa-address-book', 0, NULL, 0, 1, 0, 0, 'TEACHER_MODULE');
INSERT INTO `menu` VALUES (15, '学生模块', ' ', 'fa fa-address-book', 0, NULL, 0, 1, 0, 0, 'STUDENT_MODULE');
INSERT INTO `menu` VALUES (16, '主页', ' ', 'fa fa-home', 14, NULL, 1, 1, 0, 0, 'teacher:home');
COMMIT;

-- ----------------------------
-- Table structure for question_judge
-- ----------------------------
DROP TABLE IF EXISTS `question_judge`;
CREATE TABLE `question_judge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `answer` tinyint(1) DEFAULT NULL COMMENT '1 对 0错',
  `score` double(11,0) DEFAULT NULL,
  `fk_teacher` int(11) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of question_judge
-- ----------------------------
BEGIN;
INSERT INTO `question_judge` VALUES (1, '你是猪吧', 0, 1, 1, 1, 0);
INSERT INTO `question_judge` VALUES (2, 'Excel判断题', 1, 1, 1, 13, 0);
INSERT INTO `question_judge` VALUES (3, '特朗普是总统吗', 1, 1, 1, 7, 1);
INSERT INTO `question_judge` VALUES (4, 'asdas', 0, 1, 1, 15, 0);
INSERT INTO `question_judge` VALUES (5, '滴滴啦啦', 1, 1, 1, 15, 0);
INSERT INTO `question_judge` VALUES (6, 'asd', 1, 1, 1, 16, 0);
COMMIT;

-- ----------------------------
-- Table structure for question_multi
-- ----------------------------
DROP TABLE IF EXISTS `question_multi`;
CREATE TABLE `question_multi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `optionA` varchar(100) DEFAULT NULL,
  `optionB` varchar(100) DEFAULT NULL,
  `optionC` varchar(100) DEFAULT NULL,
  `optionD` varchar(100) DEFAULT NULL,
  `answer` varchar(20) DEFAULT NULL,
  `score` double(11,0) DEFAULT NULL,
  `fk_teacher` int(11) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of question_multi
-- ----------------------------
BEGIN;
INSERT INTO `question_multi` VALUES (1, '八嘎', '哟西', '花姑娘', '咪西', '哈哈哈', 'A,B,C', 4, 1, 1, 0);
INSERT INTO `question_multi` VALUES (2, '下面哪些是Redis的特性？', '单线程', '基于文件事件处理', 'IO多路复用', '贼慢', 'A,B,C', 4, 1, 4, 0);
INSERT INTO `question_multi` VALUES (3, 'Java线程池参数', '核心线程数', 'size', '最大线程数', '拒绝策略', 'A,C,D', 4, 1, 1, 0);
INSERT INTO `question_multi` VALUES (5, 'Excel多选题', '多选', '对不对', '呵呵', '滴滴', 'B,C,A', 4, 1, 13, 0);
INSERT INTO `question_multi` VALUES (6, '今天吃啥', '猪肉', '牛肉', '白菜', '粉丝', 'B,C,D', 4, 1, 7, 0);
INSERT INTO `question_multi` VALUES (7, 'sadfghgfh', 'fgh', 'gfhfg', 'hfghfg', 'hfghf', 'A,C,D', 4, 1, 15, 0);
INSERT INTO `question_multi` VALUES (8, 'aaASDAS', '阿斯顿', '按时打算d', 'a\'s\'d', 'ad', 'B,C', 4, 1, 15, 1);
INSERT INTO `question_multi` VALUES (9, 'ASD', '123', '12', '123', '12321', 'A,C', 4, 1, 16, 0);
COMMIT;

-- ----------------------------
-- Table structure for question_single
-- ----------------------------
DROP TABLE IF EXISTS `question_single`;
CREATE TABLE `question_single` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `optionA` varchar(100) DEFAULT NULL,
  `optionB` varchar(100) DEFAULT NULL,
  `optionC` varchar(100) DEFAULT NULL,
  `optionD` varchar(100) DEFAULT NULL,
  `answer` varchar(20) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `fk_teacher` int(11) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of question_single
-- ----------------------------
BEGIN;
INSERT INTO `question_single` VALUES (1, '下面哪个选项是Redis有序集合的底层实现？', '链表', '跳表', '二叉树', '哈希表', 'B', 2, 1, 1, 0);
INSERT INTO `question_single` VALUES (2, '下面哪个是Mysql的隐藏列？', 'roll_pointer', 'haha', 'shazi', 'iddd', 'A', 2, 1, 1, 0);
INSERT INTO `question_single` VALUES (3, '测试excel', '哈哈', '你说', '对不对', '巴', 'A', 2, 1, 13, 0);
INSERT INTO `question_single` VALUES (4, '测试excel', '哈哈', '你说', '对不对', '巴', 'A', 2, 1, 13, 0);
INSERT INTO `question_single` VALUES (5, '今天几号', '1', '10号', '20号', '22号', 'A', 2, 1, 7, 0);
INSERT INTO `question_single` VALUES (6, 'ASDAS', '爱仕达', 'as', '阿斯顿a', '啊ads', 'B', 2, 1, 15, 0);
INSERT INTO `question_single` VALUES (7, '单选啦啦啦', 'f\'d\'g\'d\'f', '阿萨收到', '阿斯顿', '按时打算', 'A', 2, 1, 15, 0);
INSERT INTO `question_single` VALUES (8, '21', '123', '123', '123', '213', 'A', 2, 1, 16, 0);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'student');
INSERT INTO `role` VALUES (2, 'teacher');
INSERT INTO `role` VALUES (3, 'manager');
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` VALUES (1, 1, 11);
INSERT INTO `role_menu` VALUES (2, 1, 12);
INSERT INTO `role_menu` VALUES (3, 2, 7);
INSERT INTO `role_menu` VALUES (4, 2, 8);
INSERT INTO `role_menu` VALUES (5, 2, 9);
INSERT INTO `role_menu` VALUES (6, 2, 10);
INSERT INTO `role_menu` VALUES (7, 3, 1);
INSERT INTO `role_menu` VALUES (8, 3, 2);
INSERT INTO `role_menu` VALUES (9, 3, 3);
INSERT INTO `role_menu` VALUES (10, 3, 4);
INSERT INTO `role_menu` VALUES (11, 3, 5);
INSERT INTO `role_menu` VALUES (12, 3, 6);
INSERT INTO `role_menu` VALUES (13, 1, 15);
INSERT INTO `role_menu` VALUES (14, 2, 14);
INSERT INTO `role_menu` VALUES (15, 3, 13);
INSERT INTO `role_menu` VALUES (16, 2, 16);
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `modified` tinyint(1) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (2, 'zhangsan', '82dea760d7bb362ca74883836ee4d6ba', '张三', 1, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cno` int(11) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_class
-- ----------------------------
BEGIN;
INSERT INTO `tb_class` VALUES (1, 1171, '微软1171', 1, 1, 0);
INSERT INTO `tb_class` VALUES (2, 1172, '微软1172', 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `modified` tinyint(1) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (1, 'xiaomei', 'e3b811f02146641add73dd7161ea35da', '小美', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for teacher_class
-- ----------------------------
DROP TABLE IF EXISTS `teacher_class`;
CREATE TABLE `teacher_class` (
  `teacher_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_id`,`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of teacher_class
-- ----------------------------
BEGIN;
INSERT INTO `teacher_class` VALUES (1, 1);
INSERT INTO `teacher_class` VALUES (1, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
