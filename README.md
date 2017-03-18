# 学生信息管理系统

## 功能说明

学生信息管理，包括学生、班级、院系、课程、成绩等的管理。

本程序仅供学习使用

## 运行截图

![stuManagerScreenshot](http://ojlsgreog.bkt.clouddn.com/stuManageScreenshot.jpg)

## 运行环境
* [JRE](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

## 工程环境

* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

* [Eclipse](https://www.eclipse.org/downloads/)

* [MySQL](https://www.mysql.com/downloads/)

## 数据库说明

本程序使用 Mysql 数据库，数据库文件在项目根目录，包括：
* `DumpStructureOnly.sql`：只包括数据库结构
* `DumpStructure_and_Data.sql`：数据库结构和测试数据

`dbConn` 类中默认数据库管理员账号：`root`，密码 `stu123`

## 登录说明

打开本程序首先进入登录界面，有账号可直接登录，无账号点击注册进行注册登陆。

**注意**：注册，默认注册普通用户（`userType = 2`），普通用户无添加用户、删除用户功能；要添加管理员账号（`userType = 1`）必须在后台添加。

## 系统功能结构

![SystemFunctionStructure](http://ojlsgreog.bkt.clouddn.com/SystemFunctionStructure.jpg)

## 数据库结构

![DatabaseStructure](http://ojlsgreog.bkt.clouddn.com/DatabaseStructure.jpg)
