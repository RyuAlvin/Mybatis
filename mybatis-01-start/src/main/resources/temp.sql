CREATE DATABASE `mybatis`;
USE `mybatis`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
 `id` int(20)user NOT NULL,
 `name` varchar(30) DEFAULT NULL,
 `pwd` varchar(30) DEFAULT NULL,
 PRIMARY KEY (`id`) )
 ENGINE=InnoDB DEFAULT CHARSET=utf8;
 insert into `user`(`id`,`name`,`pwd`) values
 (1,'张三','123'),
 (2,'李四','456'),
 (3,'王五','789');

CREATE TABLE `teacher` (
 `id` INT(10) NOT NULL,
 `name` VARCHAR(30) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO teacher(`id`, `name`) VALUES (1, '刘老师');

CREATE TABLE `student` (
 `id` INT(10) NOT NULL,
 `name` VARCHAR(30) DEFAULT NULL,
 `tid` INT(10) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `fktid` (`tid`),
 CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小刘', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小陈', '1');

CREATE TABLE `blog` (
 `id` varchar(50) NOT NULL COMMENT '博客id',
 `title` varchar(100) NOT NULL COMMENT '博客标题',
 `author` varchar(30) NOT NULL COMMENT '博客作者',
 `create_time` datetime NOT NULL COMMENT '创建时间',
 `views` int(30) NOT NULL COMMENT '浏览量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8