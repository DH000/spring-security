CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '权限名称',
  `type` smallint(6) NOT NULL COMMENT '权限类型',
  `url` varchar(128) DEFAULT NULL COMMENT '访问url',
  `parent_id` int(11) DEFAULT NULL COMMENT '父权限id',
  `parent_ids` varchar(128) DEFAULT NULL COMMENT '父权限列表串 如：1/2/3',
  `permission_alias` varchar(128) DEFAULT NULL COMMENT '权限别名 唯一',
  `sort` varchar(128) DEFAULT NULL COMMENT '排序字段',
  `is_delete` smallint(6) DEFAULT '0' COMMENT '是否已删除，0：未删除 1：已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `permission_index` (`type`,`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='权限表'

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(256) NOT NULL COMMENT '用户密码',
  `salt` varchar(256) NOT NULL COMMENT '密码盐',
  `sex_type` smallint(6) DEFAULT NULL COMMENT '用户性别',
  `birthdate` date DEFAULT NULL COMMENT '生日',
  `mobile` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_index` (`username`,`mobile`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表'

CREATE TABLE `user_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限关系表'