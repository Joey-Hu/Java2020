### 1. Maven 介绍

#### 1.1 Maven 简介

Maven是一个**项目管理工具**，它包含了一个项目对象模型 (**POM**：Project Object Model)，一组**标准集合**，一个**项目生命周期**(Project Lifecycle)，一个**依赖管理系统**(Dependency Management System)，和用来运行定义在生命周期阶段(phase)中插件(plugin)目标(goal)的逻辑。

对项目进行构建、依赖管理。

#### 1.2 Maven 优势

1. Maven 的依赖管理

传统的 Web 项目中，必须将工程依赖的 jar 包复制到工程中，会导致工程变得很大。而 Maven 工程中不直接将 jar 包导入工程中，，而是通过在pom.xml文件中添加所需jar包的坐标，这样就很好的避免了jar直接引入进来，在需要用到jar包的时候，只要查找pom.xml文件，再通过pom.xml文件中的坐标，到一个专门用于”存放jar包的仓库”(maven仓库)中根据坐标从而找到这些jar包，再把这些jar包拿去运行。

```
1. 存放 jar 包的仓库长什么样？

2. 通过读取pom.xml 文件中的坐标，再到仓库中找到jar包，会不会很慢？从而导致这种方式不可行！

为了解决这个过程中速度慢的问题，maven中也有索引的概念，通过建立索引，可以大大提高加载jar包的速度，使得我们认为jar包基本跟放在本地的工程文件中再读取出来的速度是一样的。

```

2. 项目的一键构建

构建：指项目从编译、测试、运行、打包、安装、部署的整个过程。

![Maven规范化构建流程](/img/maven1.png)

### 2. Maven 的使用

#### 2.1 下载及安装

下载地址：http://archive.apache.org/dist/maven/maven-3/

安装：解压即可，解压到一个没有中文没有空格的路径下

环境配置及 JDK 配置

#### 2.2 Maven 仓库

#####  Maven 仓库的分类

1. 本地仓库：即本地文件夹，用来存储从远程仓库或中央仓库下载的插件和 jar 包，项目使用一些插件或jar包，优先从本地仓库查找，默认本地仓库位置在 ${user.dir}/.m2/repository，${user.dir}表示windows用户目录。

2. 远程仓库：如果本地需要插件或者jar包，本地仓库没有，默认去远程仓库下载。远程仓库可以在互联网内也可以在局域网内。

3. 中央仓库 ：在maven软件中内置一个远程仓库地址 http://repo1.maven.org/maven2 ，它是中央仓库，服务于整个互联网，它是由Maven团队自己维护，里面存储了非常全的jar包，它包含了世界上大部分流行的开源项目构件。

##### Maven 本地仓库配置

选择一个目录作为本地仓库，在MAVE_HOME/conf/settings.xml文件中配置本地仓库位置（maven的安装目录下），打开 setting.xml ，配置如下：

![localRepository.png](/img/localRepository.png)

#### 2.3 Maven 工程的目录结构

![tree1.png](/img/tree1.png)

src 目录：

![srcTree.png](/img/srcTree.png)

src/main/java —— 存放项目的.java文件

src/main/resources —— 存放项目资源文件，如spring, hibernate配置文件

src/test/java —— 存放所有单元测试.java文件，如JUnit测试类

src/test/resources —— 测试资源文件

target —— 项目输出位置，编译后的class文件会输出到此目录

pom.xml——maven项目核心配置文件

注意：如果是普通的java项目，那么就没有webapp目录。

#### 2.4 Maven 工程运行

进入maven工程目录（当前目录有pom.xml文件），运行tomcat:run命令。

根据上边的提示信息，通过浏览器访问：http://localhost:8080/${项目名称}/

```
问题处理:

1. maven项目中jsp不能编译情况(Unable to compile class for JSP)

报错信息：

HTTP Status 500
type Exception report
exception
org.apache.jasper.JasperException: Unable to compile class for JSP:

解决:
https://blog.csdn.net/qq1300375795/article/details/73850682
https://yanzhuit.github.io/2017/07/12/Maven-web-project-run-error-Unable-to-compile-class-for-JSP/

首先确定导入了以下两个依赖：
<dependency>
<groupId>javax.servlet</groupId>
<artifactId>servlet-api</artifactId>
<scope>provided</scope>
</dependency>
<dependency>
<groupId>javax.servlet</groupId>
<artifactId>jsp-api</artifactId>
<scope>provided</scope>
</dependency>

其次配置了 tomcat 插件：
<build>
<plugins>
<!-- 配置Tomcat插件 -->
<plugin>
<groupId>org.apache.tomcat.maven</groupId>
<artifactId>tomcat7-maven-plugin</artifactId>
<configuration>
<path>/</path>
<port>8082</port>
</configuration>
</plugin>
</plugins>
</build>

如果还存在问题，可能是Apache -maven-3.5.0:默认运行的tomcat是Apache tomcat /6.0.29，但是Apache tomcat /6.0.29不能用于jdk1.8.0。将命令改成tomcat7:run。
```

### 3. Maven 常用命令

compile: 编译命令 mvn compile ，作用是将 src/main/java 下的文件便以为 class 文件输出到 target 目录下。

test: 测试命令 mvn test, 执行 src/test/java 下的单元测试类。

clean: 清理命令 mvn clean  删除 target 目录及内容。

pacakage: 打包命令 mvn package 对于 java 工程打包成 jar 包，对于 web 工程打包成 war 包。

install: 安装命令 mvn install 执行install将maven打成jar包或war包发布到本地仓库。

#### 3.1 Maven 指令的生命周期

maven对项目构建过程分为三套相互独立的生命周期，请注意这里说的是“三套”，而且“相互独立”，这三套生命周期分别是：

Clean Lifecycle 在进行真正的构建之前进行一些清理工作。

Default Lifecycle 构建的核心部分，编译，测试，打包，部署等等。

Site Lifecycle 生成项目报告，站点，发布站点。

#### 3.2 Maven 概念模型

项目对象模型（Project Object Model）

依赖管理系统（Dependency Management System）

一个项目生命周期（Project Lifecycle）

### 4 idea开发maven项目

#### 4.1 不使用骨架创建普通 Java 项目

![maven_java.png](/img/maven_java.png)

#### 4.2 使用骨架创建 web 项目

参考《Maven 基础讲义》

#### 4.3 Servlet 跳转 -- maven web 工程

具体步骤见《Maven 基础讲义》

porm.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.hh</groupId>
  <artifactId>test_maven_web</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>test_maven_web Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <!--本项目需要导入的依赖-->
  <dependencies>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>2.5</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.0</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <!--插件-->
  <build>
    <finalName>test_maven_web</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        <!--添加tomcat7插件 解决jar包冲突-->
        <plugin>
          <groupId>org.apache.tomcat.maven</groupId>
          <artifactId>tomcat7-maven-plugin</artifactId>
          <version>2.2</version>
          <configuration>
            <port>8888</port>
          </configuration>
        </plugin>
        <!--设置jdk编译版本-->
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <configuration>
            <target>1.8</target>
            <source>1.8</source>
            <encoding>UTF-8</encoding>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```

web.xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.hh.servlet.MyServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/myServlet</url-pattern>
    </servlet-mapping>
</web-app>
```

servlet

```java
package com.hh.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/5/10 9:36
 * @desc: 完成JSP跳转
 */
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("/helloworld.jsp").forward(request, response);

    }
}
```

如何运行 maven 项目：见《Maven 基础讲义》

#### 4.4 获取 mysql 数据库 -- maven java 工程

项目结构组织

```
D:.
├─.idea
│  ├─dataSources
│  │  └─a58fc34a-9a18-4bf6-ac4c-e618179cbbc7
│  │      └─storage_v2
│  │          └─_src_
│  │              └─schema
│  ├─inspectionProfiles
│  └─libraries
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─hh
│  │  │          ├─dao
│  │  │          │  └─impl
│  │  │          └─domaim
│  │  └─resources
│  └─test
│      └─java
└─target
    ├─classes
    │  └─com
    │      └─hh
    │          ├─dao
    │          │  └─impl
    │          └─domaim
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    └─test-classes
```

porm.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.hh</groupId>
    <artifactId>test_maven_mysql</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

实体类 Emp.java

```java
package com.hh.domaim;

import java.util.Date;

/**
 * @author: huhao
 * @time: 2020/5/10 10:34
 * @desc:
 */
public class Emp {

    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private double sal;
    private double comm;
    private int deptno;

    public Emp(int empno, String ename, String job,
               int mgr, Date hiredate, double sal, double comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Emp() {
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }
}
```

EmpDaoImpl.java

```java
package com.hh.dao.impl;

import com.hh.dao.IEmpDao;
import com.hh.domaim.Emp;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author: huhao
 * @time: 2020/5/10 10:39
 * @desc:
 */
public class EmpDaoImpl implements IEmpDao {
    public ArrayList<Emp> getAll() {

        ArrayList<Emp> ret = new ArrayList<Emp>();

        Connection connection = null;
        Statement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_test?serverTimezone=UTC",
                    "root", "hh123456");
            String sql = "SELECT * FROM emp;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                Emp emp = new Emp();

                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                String hiredate1 = resultSet.getString("hiredate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date hiredate = dateFormat.parse(hiredate1);
                double sal = resultSet.getDouble("sal");
                double comm = resultSet.getDouble("comm");
                int deptno = resultSet.getInt("deptno");

                emp.setEmpno(empno);
                emp.setEname(ename);
                emp.setJob(job);
                emp.setMgr(mgr);
                emp.setHiredate(hiredate);
                emp.setSal(sal);
                emp.setComm(comm);
                emp.setDeptno(deptno);

                ret.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}

```

测试类TestEmp.java

```java
import com.hh.dao.IEmpDao;
import com.hh.dao.impl.EmpDaoImpl;
import com.hh.domaim.Emp;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: huhao
 * @time: 2020/5/10 11:05
 * @desc:
 */
public class TestEmp {

    @Test
    public void getAll(){

        IEmpDao empDao = new EmpDaoImpl();
        ArrayList<Emp> ret = empDao.getAll();
        for (Emp emp : ret) {
            emp.toString();
        }
    }
}
```




###  参考：
黑马程序员 Maven 基础课程 ——《Maven 基础讲义》

maven中使用tomcat7插件servlet-api的jar包冲突 https://blog.csdn.net/luojishan1/article/details/81159975 

idea 导出项目结构树 https://blog.csdn.net/chouzhuanhe1683/article/details/101009729
