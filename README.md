# 学生信息管理系统

## 功能说明

学生信息管理，包括学生、班级、院系、课程、成绩等的管理。

本程序仅供学习食用。（¯﹃¯）

## 工程环境

* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [MySQL](https://www.mysql.com/downloads/)

## 运行说明

1. 本程序是Runable JAR文件，需安装JRE才可运行。  
[Java SE Runtime Environment 8 Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
2. 本程序使用MySQL数据库，使用前请导入[DumpStructureOnly.sql](database/DumpStructureOnly.sql)（数据库结构文件）或者[DumpStructure_and_Data.sql](database/DumpStructure_and_Data.sql)（带有测试数据的数据库文件）进MySQL，并设置如下（可在`dbConn.java`修改）：
    * 数据库端口：3306
    * 数据库名：stuManagerDB
    * 数据库用户名：root
    * 数据库密码：123
3. 满足以上条件下运行`stuManager.jar`则可以运行系统。

## 登录说明

1. 打开本程序首先进入登录界面，有账号可直接登录，无账号点击注册进行注册登陆。  
**注意**：注册，默认注册普通用户（`userType = 2`），普通用户无添加用户、删除用户功能；要添加管理员账号（`userType = 1`）必须在数据库添加。
2. `DumpStructure_and_Data.sql`数据库的`tb_user`表中有学生系统管理员账号：`admin`，密码为空，可以用其登录测试。

## 运行截图

![stuManagerScreenshot](https://huihut-img.oss-cn-shenzhen.aliyuncs.com/stuManageScreenshot.jpg)

## 系统功能结构

![SystemFunctionStructure](https://huihut-img.oss-cn-shenzhen.aliyuncs.com/SystemFunctionStructure.jpg)

## 数据库结构（E-R图）

![DatabaseStructure](https://huihut-img.oss-cn-shenzhen.aliyuncs.com/DatabaseERDiagram.png)
