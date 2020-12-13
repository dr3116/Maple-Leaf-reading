/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50701
Source Host           : localhost:3306
Source Database       : maple-leaf-reading

Target Server Type    : MYSQL
Target Server Version : 50701
File Encoding         : 65001

Date: 2020-12-13 16:38:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_name` varchar(20) NOT NULL,
  `classification` varchar(20) NOT NULL,
  `reading_volume` int(20) unsigned NOT NULL,
  `number_of_chapters` int(20) unsigned NOT NULL,
  `release_time` datetime NOT NULL,
  `book_photo` varchar(255) NOT NULL,
  `book_rating` double(20,2) unsigned NOT NULL,
  `author` varchar(255) NOT NULL,
  `number_of_collections` int(20) unsigned NOT NULL,
  `brief_introduction` varchar(255) NOT NULL,
  PRIMARY KEY (`book_name`),
  KEY `sad` (`classification`),
  CONSTRAINT `sad` FOREIGN KEY (`classification`) REFERENCES `classifications` (`classification`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('AI算法工程师手册', 'Python', '88010', '504', '2020-06-17 08:10:04', '1.jpg', '5.00', '进击的皇虫', '282', '作者华晓专，曾任阿里巴巴资深算法工程师、智易科技首席算法研究院，现任腾讯高级研究院，《Python大战机器学习的作者。这是作者多年以来总结的笔记，经整理后开源于世。');
INSERT INTO `book` VALUES ('Android Studio 用户指南', 'Android', '53004', '130', '2019-08-29 09:25:54', '2.jpg', '4.00', '进击的皇虫', '122', 'Android Studio 是基于IntelliJ IDEA的官方Android应用开发集成开发环境（IDE)。除了InterlliJ强大的代码编译器和开发者工具，Android Studio提供了更多可提高Android应用的构建效率的功能。');
INSERT INTO `book` VALUES ('Android官方培训课程中文版', 'Android', '104101', '303', '2018-05-17 15:08:50', '3.jpg', '5.00', '进击的皇虫', '91', 'Google Android团队在2012的时候开设了Android Training板块 - http://developer.android.com/trainning/index.html,这些课程是学习Android应用开发的绝佳资料。我们通过Github发起开源协作翻译的项目，完成中文版的输出，欢迎大家传阅学习！文章中难免会有很多写的不对不好的地方，欢迎读者加入此协作项目，进行纠错，为完善这份教程贡献一份力量。');
INSERT INTO `book` VALUES ('Android面试宝典', 'Android', '22071', '98', '2020-05-13 08:31:29', '4.jpg', '4.00', '进击的皇虫', '87', 'Android面试宝典，内容包括：JavaSE基础，JavaSE高级，Android基础，Android高级，Android项目，项目面试常见问题，面试实战记录，BAT面试题，Android最新技术。');
INSERT INTO `book` VALUES ('C/C++面试基础知识总结', 'C/C++', '62765', '69', '2019-08-31 09:17:02', '5.jpg', '4.00', '进击的皇虫', '324', 'C/C++面试基础知识总结，只为复习、分享。');
INSERT INTO `book` VALUES ('C语言进阶', 'C/C++', '31483', '31', '2020-05-14 09:10:53', '6.jpg', '4.00', '进击的皇虫', '389', '对于已经掌握C语言基础的人，本书是非常值得学习参考的，作者打破了传统C语言学习的繁琐过程，个性展示不一样的C');
INSERT INTO `book` VALUES ('H5游戏贪吃鱼', 'H5小游戏', '8147', '20', '2020-01-02 08:53:05', '7.jpg', '4.50', '进击的皇虫', '66', 'H5游戏贪吃鱼，H5 TypeScript爱心鱼小游戏');
INSERT INTO `book` VALUES ('Java/Android笔试、面试 知识', 'Android', '60145', '178', '2019-09-30 15:06:27', '8.jpg', '4.00', '进击的皇虫', '102', 'Java/Android笔试、面试 知识整理');
INSERT INTO `book` VALUES ('Java基础入门笔记', 'Java', '79928', '126', '2018-11-09 14:49:59', '9.jpg', '3.00', '进击的皇虫', '126', '对于一个开发者而言，能够胜任系统中任意一个模块的开发是其核心价值的体现。对于一个架构师而言，掌握各种语言的优势并可以运用到系统中，由此简化系统的开发，是其架构生涯的第一步。对于一个开发团队而言，能在短期内开发出用户满意的软件系统是起核心竞争力的体现。');
INSERT INTO `book` VALUES ('Java笔记（Java Note）', 'Java', '34312', '91', '2020-03-11 19:45:02', 'a', '5.00', '进击的皇虫', '402', 'Java是一门面向对象的编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用俩个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程。');
INSERT INTO `book` VALUES ('Java编程思想', 'Java', '39843', '46', '2020-02-05 19:36:48', '11.jpg', '5.00', '进击的皇虫', '710', '本书原作者为【美】Bruce Eckel，即《Java编程思想》的作者。本书是事实上的《Java编程思想》第五版。《Java编程思想》第四版基于JAVA5版本；《On Java8》基于JAVA8版本');
INSERT INTO `book` VALUES ('JSTree中文文档', 'JQuery', '22607', '13', '2018-08-21 15:01:04', '12.jpg', '4.00', '进击的皇虫', '6', 'jsTree是基于JavaScript的一个跨浏览器树控件，功能强大，而且是免费的。');
INSERT INTO `book` VALUES ('MySQL基础笔记', 'MySQL', '15975', '20', '2018-11-08 15:30:33', '13.jpg', '4.00', '进击的皇虫', '216', '记录学习mysql的笔记，涉及安装，配置，基础SQL语句，与Python链接开发等等。');
INSERT INTO `book` VALUES ('pandyle使用手册', 'JQuery', '6114', '18', '2019-10-17 15:05:02', '14.jpg', '4.00', '进击的皇虫', '4', 'pandyle是一个基于jQuery的MVVM库。它为jQuery提供了基本的模板和组件功能。pandyle秉承jQuery--write less,do more的设计理念，主要关注点即在于简单，它的大小只有7kb（压缩后），易学易用，努力减少你书写的代码量，并且更贴合传统的jQuery的书写方式。');
INSERT INTO `book` VALUES ('Python - 100天从新手到大师', 'Python', '118744', '193', '2020-09-24 20:09:08', '15.jpg', '4.00', '进击的皇虫', '1000', 'Python是一个“优雅”、“明确”、“简单”的编程语言。');
INSERT INTO `book` VALUES ('Python 网络爬虫教程', 'Python', '44739', '122', '2020-03-10 08:07:09', '16.jpg', '4.00', '进击的皇虫', '372', '使用Python实现网络爬虫教程，教你如何抓取内容。');
INSERT INTO `book` VALUES ('Robot Framwork用户手册', '测试', '23641', '98', '2020-03-10 14:27:09', '17.jpg', '4.00', '进击的皇虫', '38', 'Root Framework 是一个基于Python的、可扩展的、关键字驱动的测试自动化框架，用于端到端的验收测试或者验收驱动测试开发（ATDD)中。');
INSERT INTO `book` VALUES ('Selenium教程', '测试', '10656', '68', '2020-07-13 14:22:30', '18.jpg', '4.00', '进击的皇虫', '65', 'Selenium是一个基于Web应用程序测试的工具。Selenium测试直接运行在浏览器中，就像真正的用户在操作一样。支持的浏览器包括IE(7,8,9,10,11),Mozilla Firefox,Safari,Google Chrome,Opera等。');
INSERT INTO `book` VALUES ('Spring、Spring Boot和T', '测试', '12531', '22', '2018-06-07 14:16:39', '19.jpg', '4.00', '进击的皇虫', '79', 'Spring、Spring Boot都提供了非常便利的测试工具，但遗憾的是官方文档的大多数例子都是基于JUnits的。本人比较喜欢用TestNG做单元、集成测试，所以开启本项目收集了在Spring、Spring Boot 项目中利用TestNG测试的例子。');
INSERT INTO `book` VALUES ('前端工程师手册', 'JQuery', '88969', '193', '2018-04-26 14:57:05', '20.jpg', '4.00', '进击的皇虫', '198', '前端工程师手册，囊括前端各种知识点，从JavaScript到js框架，从HTML5到UI，以及CSS3动画，还有一些浏览器前端访问跨领域等的方案。');
INSERT INTO `book` VALUES ('动手学深度学习', '深度学习', '60860', '106', '2019-07-20 08:40:07', '21.jpg', '5.00', '进击的皇虫', '183', '《动手学深度学习》，英文版即伯克利深度学习（STA 157,2019春)教材。面向中文读者、能运行、可讨论。');
INSERT INTO `book` VALUES ('周志华《机器学习》学习笔记', '机器学习', '38526', '95', '2019-11-13 14:43:58', '22.jpg', '4.00', '进击的皇虫', '205', '周志华《机器学习》又称西瓜书是一本较为全面的书籍，书中详细介绍了机器学习领域不同类型的算法（例如：监督学习、无监督学习、半监督学习、强化监督学习、集成降维、特征选择等），记录了本人学习过程中的理解思路和扩展知识点，希望对新人阅读西瓜书有所帮助！');
INSERT INTO `book` VALUES ('安卓逆向系列教程', 'Android', '10469', '19', '2018-03-23 15:16:35', '23.jpg', '4.00', '进击的皇虫', '66', 'Android是一种基于Linux的自由及开放源代码的操作系统，主要用于移动设备，如智能手机和平板电脑，有Google公司和开放手机联盟领导及开发。');
INSERT INTO `book` VALUES ('廖雪峰 JavaScript教程', 'JavaScript', '12821', '99', '2020-07-17 09:46:31', '24.jpg', '4.00', '进击的皇虫', '182', '这是小白零基础的JavaScript全栈教程。JavaScript是世界上最流行的脚本语言，因为你在电脑、手机、平板上浏览的所有的网页，以及无数基于HTML5的手机App,交互逻辑都是由JavaScript驱动的。简单地说，JavaScript是一种运行在浏览器中的解释型的编程语言');
INSERT INTO `book` VALUES ('廖雪峰 Java教程', 'Java', '173428', '292', '2020-07-24 19:56:31', '25.jpg', '5.00', '进击的皇虫', '1185', '这是专门针对小白的零基础Java教程。为什么要学Java？因为java是全球排名第一的编程语言，Java工程师也是市场需求最大的软件工程师，选择Java，就选择了高薪。');
INSERT INTO `book` VALUES ('廖雪峰 Python 3教程', 'Python', '312331', '123', '2018-06-01 19:58:25', '26.jpg', '4.60', '进击的皇虫', '731', 'Python的3.0版本，常被称为Python 3000，或简称Py3k。相对于Python的早期版本，这是一个较大的升级。为了不带入过多的累赘，Python3.0在设计的时候没有考虑向下兼容。');
INSERT INTO `book` VALUES ('廖雪峰 SQL教程（MySQL)', 'MySQL', '25088', '31', '2020-01-15 15:18:20', '27.jpg', '4.00', '进击的皇虫', '512', '什么是SQL?简单来说，SQL就是访问和处理关系数据库的计算机标准语言。也就是说，无论什么编程语言（Java、Python、C++......）编写程序，只要涉及到操作关系数据库，比如，一个电商网站需要把用户和商品信息存入数据库，或者一个手机游戏需要把用户的道具、通关信息存入数据库，都必须通过SQL来完成。所以，现代程序离不开关系数据库，要使用关系数据库就必须掌握SQL。在本教程中，你将学到关系数据库的基本概念，如何使用SQL操作数据库，以及一种最流行的开源数据库MySQL的基本安装和使用方法。');
INSERT INTO `book` VALUES ('微信小游戏API文档（201912）', 'H5小游戏', '75823', '561', '2019-12-26 09:02:15', '28.jpg', '4.00', '进击的皇虫', '25', '微信小游戏是基于微信客户端的游戏，它即点即玩，无需下载安装，体验轻便，可以和微信内的好友一起玩，比如PK、围观等，享受小游戏带来的乐趣。');
INSERT INTO `book` VALUES ('微信小游戏云开发文档（201912）', 'H5小游戏', '59171', '331', '2019-12-18 08:57:03', '29.jpg', '5.00', '进击的皇虫', '49', '云开发为开发者提供完整的原生云端支持和微信服务支持，弱化后端和运维概念，无需搭建服务器，使用平台提供的API进行和核心业务开发，即可实现快速上线和迭代，同时这一能力，同开发者已经使用的云服务器相兼容，并不互斥。');
INSERT INTO `book` VALUES ('微信小程序入门教程', '小程序', '58000', '12', '2019-10-02 19:44:16', '30.jpg', '4.70', '进击的皇虫', '141', '微信小程序是腾讯在2017年1月9日推出的一种不需要下载安装就可以在微信平台上使用的应用，主要提供给企业、政府、媒体、其他组织或个人的开发者在微信平台上提供服务。');
INSERT INTO `book` VALUES ('微信小程序大全', '小程序', '36212', '29', '2018-02-08 19:51:19', '31.jpg', '4.72', '进击的皇虫', '93', '【微信小程序】优秀教程、轮子、开源项目 资源汇总');
INSERT INTO `book` VALUES ('技术面试需要掌握的基础知识整理', 'MySQL', '69940', '29', '2018-11-25 15:14:45', '32.jpg', '4.70', '进击的皇虫', '772', '本仓库是笔者在准备2018春招实习过程中的学习总结，内容以计算机书籍的学习笔记为主，在整理重点知识的同时会尽量保证知识的系统性。');
INSERT INTO `book` VALUES ('李炎恢HTML/CSS教程', 'HTML5', '55274', '38', '2020-03-28 15:23:50', '33.jpg', '4.00', '进击的皇虫', '362', '李炎恢HTML/CSS，HTML5,CSS3教程');
INSERT INTO `book` VALUES ('现代C++教程：高速上手C++11/14', 'C/C++', '17801', '18', '2020-02-07 09:28:14', '34.jpg', '4.00', '进击的皇虫', '356', '本书号称【高速上手】，对现代C语言做了全面的介绍，你一定会喜欢的。');
INSERT INTO `book` VALUES ('现代JavaScript教程中文版', 'JavaScript', '30363', '199', '2020-06-01 09:43:10', '35.jpg', '5.00', '进击的皇虫', '246', '现代JavaScript教程，最新的JavaScript标准为基准。通过简单但足够详细的内容，为你讲解从基础到高阶JavaScript相关知识。');
INSERT INTO `book` VALUES ('神经网络与深度学习', '深度学习', '5322', '7', '2018-06-22 08:44:19', '36.jpg', '4.00', '进击的皇虫', '16', '神经网络和深度学习是一本免费的在线书。本书会教会你：神经网络，一种美妙的受生物学启发的编程范式，可以让计算机从观测数据中进行学习；深度学习，一个强有力的用于神经网络学习的众多技术的集合');
INSERT INTO `book` VALUES ('笔试面试知识整理', 'Android', '39572', '68', '2018-06-14 08:21:42', '37.jpg', '5.00', '进击的皇虫', '271', '这份笔试面试题比较全面，囊括了以下内容：计算机网络、数据结构与算法、体系结构与操作系统、数据库系统、编译原理、设计模型、版本控制、IOS开发、Android开发等。');
INSERT INTO `book` VALUES ('简单粗暴TensorFlow2.0', '机器学习', '78843', '85', '2019-10-25 14:49:25', '38.jpg', '5.00', '进击的皇虫', '135', 'TensorFlow是一个开源软件库，用于各种感知语言和语言理解任务的机器学习，被广泛应用于各类机器学习（machine）算法的编程实现。');
INSERT INTO `book` VALUES ('算法珠玑（Java版）', 'Java', '68478', '250', '2018-10-18 14:58:51', '39.jpg', '4.00', '进击的皇虫', '205', '算法珠玑（Java版）——一个最精简的题库，本书的目标读者是准备去硅谷找工作的码农，也适合于国内找工作的码农，以及刚接触ACM算法竞赛的新手。');
INSERT INTO `book` VALUES ('编程之法：面试和算法心得', '机器学习', '62148', '88', '2018-07-20 14:34:00', '40.jpg', '4.50', '进击的皇虫', '501', '《编程之法：面试和算法心得》涉及面试、算法、机器学习三个主题。书中的每个编程题目都给出了多种思路、多种解法，不断优化、逐层递进。本书第一章至第六章分别阐述字符串、数组、树、查找、动态规划、海量数据处理等相关的编程面试题和算法，第七章介绍机器学习的俩个算法-K近邻和SVM。');
INSERT INTO `book` VALUES ('阮一峰 ECMAScript 6入门教程', 'HTML5', '30310', '32', '2018-01-25 15:26:59', '41.jpg', '4.70', '进击的皇虫', '270', '《ECMAScript 6入门》是一本开源的JavaScript语言教程，全面介绍ECMAScript 6新增的语法特性。');
INSERT INTO `book` VALUES ('阮一峰 HTML语言教程', 'HTML5', '17040', '105', '2020-06-03 15:32:02', '42.jpg', '5.00', '进击的皇虫', '108', 'HTML预言师互联网开发的基础。本教程完整介绍HTML语言的所有内容，既可以当做初学者的入门教程，也可做参考手册查阅语法。');
INSERT INTO `book` VALUES ('阮一峰 JavaScript教程', 'JavaScript', '53574', '116', '2020-07-24 09:35:53', '43.jpg', '4.50', '进击的皇虫', '373', '本教程全面介绍JavaScript核心用法，从简单的开始讲起，循序渐进、由浅入深，力求清晰易懂。所有章节都带有大量的代码实例，便于理解和模仿，可以用到实际项目中，即学即用。');
INSERT INTO `book` VALUES ('阿里巴巴 Java开发手册', 'Java', '28104', '27', '2018-07-13 14:42:41', '44.jpg', '4.00', '进击的皇虫', '260', '《阿里巴巴Java开发手册》是阿里集团技术团队和集体智慧结晶和经验总结，经历了多次大规模一线实战的检验及不断地完善，系统化地整理成册，回馈给广大开发者。现代软件行业的高速发展对开发者的综合素质要求越来越高，因为不仅是编程知识点，其它维度的知识点也会影响到软件的最终交付质量。');

-- ----------------------------
-- Table structure for `bookshelf`
-- ----------------------------
DROP TABLE IF EXISTS `bookshelf`;
CREATE TABLE `bookshelf` (
  `user_id` int(20) unsigned NOT NULL,
  `book_name` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`book_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of bookshelf
-- ----------------------------
INSERT INTO `bookshelf` VALUES ('1', 'Android面试宝典');
INSERT INTO `bookshelf` VALUES ('1', 'Java笔记（Java Note）');
INSERT INTO `bookshelf` VALUES ('2', 'Java编程思想');
INSERT INTO `bookshelf` VALUES ('2', '廖雪峰 Java教程');
INSERT INTO `bookshelf` VALUES ('3', 'Python - 100天从新手到大师');
INSERT INTO `bookshelf` VALUES ('3', 'Python 网络爬虫教程');
INSERT INTO `bookshelf` VALUES ('3', '廖雪峰 JavaScript教程');
INSERT INTO `bookshelf` VALUES ('4', 'C/C++面试基础知识总结');
INSERT INTO `bookshelf` VALUES ('4', 'C语言进阶');
INSERT INTO `bookshelf` VALUES ('4', '前端工程师手册');
INSERT INTO `bookshelf` VALUES ('4', '现代C++教程：高速上手C++11/14');
INSERT INTO `bookshelf` VALUES ('5', '微信小游戏API文档（201912）');
INSERT INTO `bookshelf` VALUES ('5', '微信小游戏云开发文档（201912）');
INSERT INTO `bookshelf` VALUES ('6', '微信小程序大全');
INSERT INTO `bookshelf` VALUES ('6', '现代JavaScript教程中文版');

-- ----------------------------
-- Table structure for `book_review`
-- ----------------------------
DROP TABLE IF EXISTS `book_review`;
CREATE TABLE `book_review` (
  `book_review_id` int(20) unsigned NOT NULL,
  `book_name` varchar(20) NOT NULL,
  `user_id` int(20) unsigned NOT NULL,
  `content` varchar(255) NOT NULL,
  `score` double(20,2) unsigned NOT NULL,
  PRIMARY KEY (`book_review_id`),
  KEY `book_name` (`book_name`) USING BTREE,
  KEY `us_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of book_review
-- ----------------------------
INSERT INTO `book_review` VALUES ('3', 'Java编程思想', '2', '就很棒！！', '5.00');
INSERT INTO `book_review` VALUES ('4', 'Java笔记（Java Note）', '2', '讲解的很详细，面面俱到', '5.00');
INSERT INTO `book_review` VALUES ('5', '廖雪峰 Java教程', '1', 'nb', '5.00');
INSERT INTO `book_review` VALUES ('7', 'Python - 100天从新手到大师', '3', '100天教你学习Python，nice', '5.00');
INSERT INTO `book_review` VALUES ('8', 'Python 网络爬虫教程', '4', '教你学会爬虫，但请慎重使用', '5.00');
INSERT INTO `book_review` VALUES ('9', '廖雪峰 Python 3教程', '4', '个人认为写的不错', '5.00');
INSERT INTO `book_review` VALUES ('10', '微信小程序入门教程', '6', '就很棒啊', '5.00');
INSERT INTO `book_review` VALUES ('11', '廖雪峰 JavaScript教程', '5', '爱死这本书了', '5.00');
INSERT INTO `book_review` VALUES ('12', 'C/C++面试基础知识总结', '3', '虽然已经很熟练了，但在这本书中还能学到新的知识', '5.00');
INSERT INTO `book_review` VALUES ('13', 'C语言进阶', '6', '对小白来说很友好', '4.70');
INSERT INTO `book_review` VALUES ('14', '笔试面试知识整理', '2', '很详细，刷面试题的一本好书', '4.90');
INSERT INTO `book_review` VALUES ('15', '廖雪峰 SQL教程（MySQL)', '5', '有了这本书，再也不用愁不用用MySQL了', '5.00');
INSERT INTO `book_review` VALUES ('16', 'Spring、Spring Boot和T', '3', '测试困难？这本书了解一些。', '4.80');
INSERT INTO `book_review` VALUES ('17', '前端工程师手册', '4', '想学好前端吗，这本书值得一试', '4.90');
INSERT INTO `book_review` VALUES ('18', '微信小程序大全', '3', '这本书中有很多的小程序用例', '5.00');
INSERT INTO `book_review` VALUES ('19', '动手学深度学习', '5', '不了解深度学习，只停留在知识上，快来拿这本书实践吧', '4.90');
INSERT INTO `book_review` VALUES ('20', '周志华《机器学习》学习笔记', '4', '这本书对学习机器学习来说很棒', '5.00');
INSERT INTO `book_review` VALUES ('21', '技术面试需要掌握的基础知识整理', '5', '技术方面面试题大全，应对企业面试的不二良选', '5.00');
INSERT INTO `book_review` VALUES ('22', '前端工程师手册', '3', '前端开发必备', '5.00');

-- ----------------------------
-- Table structure for `classifications`
-- ----------------------------
DROP TABLE IF EXISTS `classifications`;
CREATE TABLE `classifications` (
  `classification` varchar(20) NOT NULL,
  `book_name` varchar(20) NOT NULL,
  `category` varchar(20) NOT NULL,
  PRIMARY KEY (`classification`,`book_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of classifications
-- ----------------------------
INSERT INTO `classifications` VALUES ('Android', 'Android面试宝典', '移动端');
INSERT INTO `classifications` VALUES ('Android', 'Java/Android笔试、面试 知识', '移动端');
INSERT INTO `classifications` VALUES ('Android', '安卓逆向系列教程', '移动端');
INSERT INTO `classifications` VALUES ('Android', '笔试面试知识整理', '移动端');
INSERT INTO `classifications` VALUES ('C/C++', 'C/C++面试基础知识总结', '后端');
INSERT INTO `classifications` VALUES ('C/C++', 'C语言进阶', '后端');
INSERT INTO `classifications` VALUES ('C/C++', '现代C++教程：高速上手C++11/14', '后端');
INSERT INTO `classifications` VALUES ('H5小游戏', '微信小游戏API文档（201912）', '游戏开发');
INSERT INTO `classifications` VALUES ('H5小游戏', '微信小游戏云开发文档（201912）', '游戏开发');
INSERT INTO `classifications` VALUES ('HTML5', '李炎恢HTML/CSS教程', '前端');
INSERT INTO `classifications` VALUES ('HTML5', '阮一峰 ECMAScript 6入门教程', '前端');
INSERT INTO `classifications` VALUES ('Java', 'Java基础入门笔记', '后端');
INSERT INTO `classifications` VALUES ('Java', 'Java笔记（Java Note）', '后端');
INSERT INTO `classifications` VALUES ('Java', 'Java编程思想', '后端');
INSERT INTO `classifications` VALUES ('Java', '廖雪峰 Java教程', '后端');
INSERT INTO `classifications` VALUES ('Java', '算法珠玑（Java版）', '后端');
INSERT INTO `classifications` VALUES ('JavaScript', '廖雪峰 JavaScript教程', '前端');
INSERT INTO `classifications` VALUES ('JavaScript', '现代JavaScript教程中文版', '前端');
INSERT INTO `classifications` VALUES ('JQuery', 'JSTree中文文档', '前端');
INSERT INTO `classifications` VALUES ('JQuery', 'pandyle使用手册', '前端');
INSERT INTO `classifications` VALUES ('JQuery', '前端工程师手册', '前端');
INSERT INTO `classifications` VALUES ('MySQL', 'MySQL基础笔记', '数据库');
INSERT INTO `classifications` VALUES ('MySQL', '廖雪峰 SQL教程（MySQL)', '数据库');
INSERT INTO `classifications` VALUES ('MySQL', '技术面试需要掌握的基础知识整理', '数据库');
INSERT INTO `classifications` VALUES ('Python', 'Python - 100天从新手到大师', '后端');
INSERT INTO `classifications` VALUES ('Python', 'Python 网络爬虫教程', '后端');
INSERT INTO `classifications` VALUES ('Python', '廖雪峰 Python 3教程', '后端');
INSERT INTO `classifications` VALUES ('小程序', '微信小程序入门教程', '前端');
INSERT INTO `classifications` VALUES ('小程序', '微信小程序大全', '前端');
INSERT INTO `classifications` VALUES ('机器学习', '周志华《机器学习》学习笔记', '云计算&大数据');
INSERT INTO `classifications` VALUES ('机器学习', '简单粗暴TensorFlow2.0', '云计算&大数据');
INSERT INTO `classifications` VALUES ('机器学习', '编程之法：面试和算法心得', '云计算&大数据');
INSERT INTO `classifications` VALUES ('测试', 'Robot Framwork用户手册', '产品&运营&IDE');
INSERT INTO `classifications` VALUES ('测试', 'Selenium教程', '产品&运营&IDE');
INSERT INTO `classifications` VALUES ('测试', 'Spring、Spring Boot和T', '产品&运营&IDE');
INSERT INTO `classifications` VALUES ('深度学习', '动手学深度学习', '云计算&大数据');
INSERT INTO `classifications` VALUES ('深度学习', '神经网络与深度学习', '云计算&大数据');

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `comments_id` int(20) unsigned NOT NULL,
  `post_id` int(20) unsigned NOT NULL,
  `content` varchar(255) NOT NULL,
  `commenter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`comments_id`),
  KEY `post_id` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '1', '+1+1', '1');
INSERT INTO `comments` VALUES ('2', '1', '同感', '2');
INSERT INTO `comments` VALUES ('3', '4', '+1', '3');
INSERT INTO `comments` VALUES ('4', '2', 'wow!', '4');
INSERT INTO `comments` VALUES ('5', '5', '这本书确实不错', '5');
INSERT INTO `comments` VALUES ('6', '3', '赞', '6');
INSERT INTO `comments` VALUES ('7', '3', '+1', '7');

-- ----------------------------
-- Table structure for `fans`
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `user_id` int(20) unsigned NOT NULL,
  `people_concerned_id` int(20) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`people_concerned_id`),
  KEY `pc_id` (`people_concerned_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of fans
-- ----------------------------
INSERT INTO `fans` VALUES ('3', '1');
INSERT INTO `fans` VALUES ('10', '1');
INSERT INTO `fans` VALUES ('1', '2');
INSERT INTO `fans` VALUES ('3', '2');
INSERT INTO `fans` VALUES ('4', '2');
INSERT INTO `fans` VALUES ('5', '2');
INSERT INTO `fans` VALUES ('10', '2');
INSERT INTO `fans` VALUES ('1', '4');
INSERT INTO `fans` VALUES ('2', '5');
INSERT INTO `fans` VALUES ('4', '5');
INSERT INTO `fans` VALUES ('6', '5');
INSERT INTO `fans` VALUES ('10', '5');
INSERT INTO `fans` VALUES ('1', '6');

-- ----------------------------
-- Table structure for `master`
-- ----------------------------
DROP TABLE IF EXISTS `master`;
CREATE TABLE `master` (
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `state` char(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of master
-- ----------------------------
INSERT INTO `master` VALUES ('苗雨', '123456', '0');
INSERT INTO `master` VALUES ('杜然', '123456', '0');
INSERT INTO `master` VALUES ('acs', 'acs', '0');
INSERT INTO `master` VALUES ('mnb', 'mnb', '0');
INSERT INTO `master` VALUES ('vcx', 'vcx', '0');
INSERT INTO `master` VALUES ('dsa', 'dsa', '0');
INSERT INTO `master` VALUES ('sa', '123456', '0');
INSERT INTO `master` VALUES ('name1', 'password1', '1');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(20) unsigned NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `book_name` varchar(20) DEFAULT NULL,
  `number_of_likes` int(20) unsigned DEFAULT NULL,
  `comments` int(20) unsigned DEFAULT NULL,
  `user_id` int(20) unsigned DEFAULT NULL,
  `post_time` datetime DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `use_id` (`user_id`),
  KEY `boo_name` (`book_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '推荐Android书籍', '我推荐Android面试题宝典这本书，讲的很多面试题，企业面试的很多题目都有收录，真的很棒啊。', '1.jpg', 'Android面试宝典', '500', '5', '1', '2020-11-28 09:46:56');
INSERT INTO `post` VALUES ('2', '推荐JavaNote', '个人认为写的不错，也得到了业界很多大佬的肯定，我推荐给学习Java的人读一读！！', '2.jpg', 'Java笔记（Java Note）', '652', '15', '2', '2020-11-12 09:46:59');
INSERT INTO `post` VALUES ('4', '推荐Python学习书籍', '这本书系统的讲解了Python相关的知识，对于Python的初学者来说值得推荐', '3.jpg', '廖雪峰 Python 3教程', '425', '17', '3', '2020-11-08 09:47:06');
INSERT INTO `post` VALUES ('5', '推荐C语言学习书籍', '强烈推荐这本书，对于从未学习过编程的人来说真的是太友好了，very Good', '4.jpg', '现代C++教程：高速上手C++11/14', '1206', '30', '4', '2020-11-07 09:47:09');
INSERT INTO `post` VALUES ('6', '推荐机器学习这本书', '真的很棒', '5.jpg', '周志华《机器学习》学习笔记', '651', '32', '4', '2020-11-18 09:47:11');
INSERT INTO `post` VALUES ('7', '就很棒', '值得一看', '6.jpg', '笔试面试知识整理', '1655', '50', '6', '2020-11-23 09:47:15');

-- ----------------------------
-- Table structure for `recent_reading`
-- ----------------------------
DROP TABLE IF EXISTS `recent_reading`;
CREATE TABLE `recent_reading` (
  `recent_reading_id` int(20) unsigned NOT NULL,
  `user_id` int(20) unsigned NOT NULL,
  `book_name` varchar(20) NOT NULL,
  `last_reading_time` datetime NOT NULL,
  PRIMARY KEY (`recent_reading_id`),
  KEY `userid` (`user_id`),
  KEY `bookname` (`book_name`),
  KEY `lastreadingtime` (`last_reading_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of recent_reading
-- ----------------------------
INSERT INTO `recent_reading` VALUES ('2', '2', 'Android面试宝典', '2020-11-17 15:02:32');
INSERT INTO `recent_reading` VALUES ('3', '3', 'Python - 100天从新手到大师', '2020-11-17 19:02:35');
INSERT INTO `recent_reading` VALUES ('4', '4', 'C/C++面试基础知识总结', '2020-11-19 15:03:05');
INSERT INTO `recent_reading` VALUES ('5', '10', 'Android面试宝典', '2020-12-07 13:38:33');
INSERT INTO `recent_reading` VALUES ('6', '10', 'Java基础入门笔记', '2020-12-07 13:38:30');
INSERT INTO `recent_reading` VALUES ('7', '10', 'Java/Android笔试、面试 知识', '2020-12-09 13:39:12');

-- ----------------------------
-- Table structure for `searchrecords`
-- ----------------------------
DROP TABLE IF EXISTS `searchrecords`;
CREATE TABLE `searchrecords` (
  `search_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `search_history` varchar(20) NOT NULL,
  `user_id` int(20) unsigned NOT NULL,
  PRIMARY KEY (`search_id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of searchrecords
-- ----------------------------
INSERT INTO `searchrecords` VALUES ('1', 'java', '1');
INSERT INTO `searchrecords` VALUES ('2', 'c++', '7');
INSERT INTO `searchrecords` VALUES ('3', 'python', '2');
INSERT INTO `searchrecords` VALUES ('4', 'PHP', '3');
INSERT INTO `searchrecords` VALUES ('5', 'JS', '4');
INSERT INTO `searchrecords` VALUES ('6', '机器学习', '6');
INSERT INTO `searchrecords` VALUES ('7', '深度学习', '5');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_photo` varchar(255) NOT NULL,
  `sign_num` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`user_id`,`user_name`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '苗雨', '123456', '1.jpg', '121');
INSERT INTO `user` VALUES ('2', '杜然', '123456', '2.jpg', '100');
INSERT INTO `user` VALUES ('3', 'acs', 'acs', '3.jpg', '50');
INSERT INTO `user` VALUES ('4', 'mnb', 'mnb', '4.jpg', '25');
INSERT INTO `user` VALUES ('5', 'vcx', 'vcx', '5.jpg', '12');
INSERT INTO `user` VALUES ('6', 'dsa', 'dsa', '6.jpg', '7');
INSERT INTO `user` VALUES ('7', 'sa', '123456', '7.jpg', '3');
INSERT INTO `user` VALUES ('9', '2018011710', '123456', '8.jpg', '152');
INSERT INTO `user` VALUES ('10', 'name1', 'password1', '9.jpg', '163');
