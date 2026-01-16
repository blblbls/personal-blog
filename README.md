 Personal Blog System（个人博客系统）

> 基于SSM 架构（Spring + SpringMVC + MyBatis）的 Web 博客管理系统  

---

 1. 项目概述

本项目是一个基于经典 SSM 三层架构的个人博客系统，支持用户登录、文章管理与评论管理等核心功能。  
项目遵循 MVC 分层设计思想，结构清晰、逻辑规范，适合用于 Java 后端学习与实习面试展示。

 项目目标
- 理解 Web 开发的分层架构
- 掌握 SSM 框架整合开发
- 熟悉 MyBatis + MySQL 数据持久化
- 掌握 Git/GitHub 版本管理

---

2. 技术架构

浏览器
↓
Controller（控制层）
↓
Service（业务层）
↓
Mapper（持久层）
↓
MySQL 数据库


 技术栈
| 层级 | 技术 |
|------|------|
| 前端 | JSP + HTML + CSS + JavaScript |
| 控制层 | SpringMVC |
| 业务层 | Spring |
| 持久层 | MyBatis |
| 数据库 | MySQL |
| 构建工具 | Maven |
| 服务器 | Tomcat |
| 版本管理 | Git + GitHub |
| IDE | IntelliJ IDEA |

---

 3. 核心功能模块

 用户模块
- 用户登录  
- 登录拦截校验（未登录无法访问后台）  

 文章模块
- 发布文章  
- 编辑文章  
- 删除文章  
- 文章列表分页展示  
- 文章详情展示  

 评论模块
- 对文章发表评论  
- 评论展示  

 系统功能
- 统一异常处理  
- 基于拦截器的权限校验  
- MVC 分层架构设计  

---

 4. 项目目录结构

src/
├── controller # 控制层（处理请求）
├── service # 业务层（核心逻辑）
├── mapper # MyBatis 持久层
├── pojo # 实体类（Article/User/Comment）
└── resources # 配置文件
├── applicationContext.xml
├── spring-mvc.xml
├── mybatis-config.xml
└── mapper/*.xml

---

 5. 关键技术实现

 登录拦截器
使用 SpringMVC 拦截器，防止未登录用户访问后台页面：

```java
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}

MyBatis 数据查询流程

Controller 调用 Service

Service 调用 Mapper 接口

MyBatis 根据 Mapper.xml 执行 SQL

查询结果映射为 Java 对象返回

<select id="selectAll" resultType="com.example.pojo.Article">
    select * from article;
</select>

6. 运行说明

导入项目到 IntelliJ IDEA

创建 MySQL 数据库 blog

修改 jdbc.properties：
jdbc.url=jdbc:mysql://localhost:3306/blog
jdbc.username=root
jdbc.password=your_password
部署到 Tomcat

访问：http://localhost:8080/

作者

GitHub: @blblbls
