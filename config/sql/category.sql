/*
-- Query: SELECT * FROM ibook.category
LIMIT 0, 1000

-- Date: 2015-05-22 15:59
*/
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6201,'中国',0,0,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6202,'天津',2,0,6201);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6203,'南开',3,0,6202);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6204,'销售|客服|市场',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6205,'财务|人力资源|行政',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6206,'项目|质量|高级管理',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6207,'IT|互联网|通信',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6208,'房产|建筑|物业管理',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6209,'金融',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6210,'采购|贸易|交通|物流',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6211,'生产|制造',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6212,'传媒|印刷|艺术|设计',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6213,'咨询|法律|教育|翻译',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6214,'服务业',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6215,'能源|环保|农业|科研',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6216,'兼职|实习|社工|其他',NULL,1,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6217,'销售业务',NULL,1,6204);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6218,'销售管理',NULL,1,6204);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6219,'销售行政/商务',NULL,1,6204);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6220,'客服/售前/售后技术支持',NULL,1,6204);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6221,'市场',NULL,1,6204);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6222,'公关/媒介',NULL,1,6204);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6223,'广告/会展',NULL,1,6204);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6224,'上市公司',NULL,2,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6225,'公司旅游',NULL,2,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6226,'Java',NULL,3,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6227,'C/C++',NULL,3,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6228,'全职',NULL,4,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6229,'兼职',NULL,4,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6230,'实习',NULL,4,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6231,'高中',NULL,5,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6232,'大专',NULL,5,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6233,'本科',NULL,5,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6234,'硕士',NULL,5,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6235,'博士',NULL,5,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6236,'三险一金',NULL,6,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6237,'五险一金',NULL,6,NULL);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6238,'财务/审计/税务',NULL,1,6205);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6239,'人力资源',NULL,1,6205);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6240,'行政/后勤/文秘',NULL,1,6205);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6241,'软件/互联网开发/系统集成',NULL,1,6207);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6242,'硬件开发',NULL,1,6207);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6243,'互联网产品/运营管理',NULL,1,6207);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6244,'IT质量管理/测试/配置管理',NULL,1,6207);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6245,'IT运维/技术支持',NULL,1,6207);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6246,'IT管理/项目协调',NULL,1,6207);
INSERT INTO `category` (`id`,`name`,`subType`,`type`,`PARENT_ID`) VALUES (6247,'电信/通信技术开发及应用',NULL,1,6207);
