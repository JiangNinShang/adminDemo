

CREATE TABLE Departmen (
  Did int(11) PRIMARY KEY COMMENT '部门编号',
  Dname varchar(100) NOT NULL COMMENT '部门名称',
  principal int(11) NOT NULL COMMENT '部门负责人',
  sum int(11) NOT NULL COMMENT '总人数'
) ;


CREATE TABLE Jurisdiction (
  jid int(11) PRIMARY KEY COMMENT '权限编号',
  jname varchar(100) UNIQUE  NOT NULL COMMENT '权限名称',
  jpath varchar(128) UNIQUE  NOT NULL COMMENT '权限路径',
  state int(11) NOT NULL DEFAULT '0' COMMENT '状态 0：存在 1：以删除'
) ;


CREATE TABLE Picture (
  pid int(11) PRIMARY KEY COMMENT '图片编号',
  pPath varchar(100) UNIQUE  NOT NULL COMMENT '路径'
) ;


CREATE TABLE Position (
  pid int(11) PRIMARY KEY COMMENT '职务编号',
  pname int(11) NOT NULL COMMENT '职务',
  salary int(11) NOT NULL COMMENT '薪水',
  state int(11) DEFAULT '0' COMMENT '状态 0：存在 1：以删除'
);


CREATE TABLE User (
  uid int(11) PRIMARY KEY DEFAULT '1' COMMENT '用户id',
  uname varchar(16) UNIQUE NOT NULL COMMENT '用户名称',
  upwd varchar(64) NOT NULL COMMENT '密码',
  headImg int(11) DEFAULT '1' COMMENT '头像',
  phone int(11) NOT NULL COMMENT '联系电话',
  sex int(1) NOT NULL DEFAULT '0' COMMENT '性别  0：男  1：女',
  age int(11) NOT NULL DEFAULT '18' COMMENT '年龄',
  addressId int(11) DEFAULT NULL COMMENT '家庭住址编号',
  mail varchar(100) NOT NULL COMMENT '邮箱',
  deleted int(1) NOT NULL DEFAULT '0' COMMENT '是否注销 0：否 1：是',
  state int(1) NOT NULL DEFAULT '0' COMMENT '状态 0：离线 1：在线 3：忙'
);


CREATE TABLE UserPosition (
  udid int(11) PRIMARY KEY COMMENT '编号',
  uid int(11) NOT NULL COMMENT '用户编号',
  pid int(11) NOT NULL COMMENT '职位编号'
) ;

