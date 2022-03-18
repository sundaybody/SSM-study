# SSM-study
ssm学习的整合项目，适用于刚学习结束ssm框架的人练手
# 								SSM整合项目

## 一、项目简介

### 1.项目简介

使用ssm框架搭建出一套简单的CRUD的项目，主要涉及员工表和部门表，实现员工表的增删改查，其中每个员工对应这一个部门，属于多对一的关系，部门表和员工表属于多对一的关系

### 2.主要实现的功能

1.分页展示所有员工的基本信息

2.实现添加员工和删除员工

3.实现修改员工信息

4.实现批量删除员工信息

5.实现员工添加和修改时的数据校验工作

​	▪jQuery前端校验用户名和邮箱是否合法

​	▪Ajax请求校验用户名是否重复

​	▪JSR303后端检验用户名、邮箱是否合法以及用户名是否重复

### 3.涉及的技术

1.后端框架：Spring5+SpringMVC+MyBatis3

2.前端技术：zui开源HTML5框架，html5

3.视图渲染技术：thymeleaf

4.MyBatis分页插件：PageHelper

5.MyBatis逆向工程：mybatis-generator

6.RESTfui风格url

7.数据库：MySql 5.7+c3p0数据库连接池技术

### 4.最终效果图
![ssm效果图](https://user-images.githubusercontent.com/101156240/158946420-eb1d8daa-c920-4f3a-9cf1-1a936c9d52c9.JPG)
