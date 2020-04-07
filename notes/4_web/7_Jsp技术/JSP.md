###  	JSP

#### 

#### 回顾

```
一、servletContext  servlet应用上下文
	web服务器启动创建的。服务器关闭即销毁
    this.getServletContext();
    this.getServletConfig().getServletContext();
    request.getServletContext();
    request.getSession().getServletContext();
    servletContext充当容器存储
    servletContext.setAttribute(name,value);--->当前整个应用都可以共享    共享区域
    //统计当前项目下所有servlet的访问次数
二、Filter过滤器
	实现：
	implements Filter 
	init、doFilter、destroy
	配置方式：
	web.xml---->
	<filter>
		<filter-name></filter-name>
		<filter-class></filter-class>
	</filter>
	<filter-mapping>
		<filter-name></filter-name>
		//和servlet一样
		<url-pattern></url-pattern>
		//指定拦截的servlet的name
		<servlet-name></servlet-name>
	</filter-mapping>
	注解——---》
	@WebFilter()   name,value,url-patterns,servletNames,initParams
	初始化参数：
	filterConfig.getInitParameter(name);
	优先级：
	web.xml ---->配置的顺序
	注解  ---->类名的字典顺序   不同包同名
	web.xml  >  注解
	自动登录（静态、动态）、过滤脏词、编码、权限验证
```



#### 今日内容

```
1、什么是JSP
2、JSP的语法
3、JSP的指令
4、JSP的动作标签
5、JSP的内置对象
```

#### 教学目标

```
1、熟悉什么是JSP
2、掌握JSP的语法
3、掌握JSP的指令
4、掌握JSP的动作标签
5、掌握JSP的内置对象
```

## 第一节 JSP

Servlet3.1

JSP 2.3

### 1.1 JSP是什么

```
   全称： Java Server Pages,java服务器页面。和Servlet一样，是sun公司定义的一种动态网页开发技术。
    特点：基于html模版，可以在html模版嵌入java代码和jsp中的标签。

    tomcat可以包含的资源：
    静态技术：
	  html:静态页面。
      CSS:修饰美化html页面。
      JavaScript:动态修改html页面和css样式。
    动态技-术  
      Servlet：运行在服务器上的java小程序。适合编写java代码，写网页困难,适合业务处理
     	Servlet三个用途:1 接受浏览器发送的数据  2 负责调用业务层   3转发重定向
      JSP:适合编写动态内容，不适合写java代码，主要用于显示页面。
```

### 1.2 为什么要用JSP
```
    2.1 jsp性能好，可以在html页面中动态嵌入元素
    2.2 服务器调用的是已经编译好的JSP文件
    2.3 JSP基于Java Servlet Api,有很多强大企业的支持。
    2.4 JSP可以与处理业务逻辑的Servlet一起使用，该模式被Java Servlet模版引擎所支持。
```

### 1.3 JSP优势

```
    3.1 与纯 Servlet 相比：JSP可以很方便的编写或者修改HTML网页而不用去面对大量的println语句
    3.2 与JavaScript相比：虽然JavaScript可以在客户端动态生成HTML，但是很难与服务器交互，因此不能提供复杂的服务，比如访问数据库和图像处理等等。
    3.3 与静态HTML相比：静态HTML不包含动态信息
```

## 第二节 JSP构成

JSP页面中可以包含指令，Java语句、变量、方法或表达式、静态内容(html、css、javascript)

脚本元素(Scripting Elements):声明、脚本段、表达式。
注释指令(Comment Elements):HTML注释、Java注释、JSP隐式注释。
指令元素（Directive Elements):page、include、taglib 等
动作元素(Action Elements):jsp:include、jsp:forward、jsp:useBean等。

### 2.1 JSP指令

语法格式：

告诉JSP引擎如何解析jsp文件中的内容
​               语法： <%@ 指令名称 属性名称1＝“属性值1” 属性名称2＝“属性值2” 。。。。。%>
​               示例：<%@ page language=*"java"* contentType="text/html; charset=UTF-8"  pageEncoding=*"UTF-8"*%>

#### 2.1.1 page指令

作用：用于定义JSP页面的各种属性。

```
import和java代码的含义一样
         <%@ page import="java.util.Date,java.util.List"%>
             或者：
		<%@ page import="java.util.Date"%>
		<%@ page import="java.util.List"%>
session:是否会自动创建session对象，默认值为true;
buffer:JSP中有javax.servlet.jsp.JspWriter输出字符流。设置输出数据的缓存大小。默认8kb.
errorPage:如果页面中有错误，跳转到指定的资源 errorPage="/uri" 如果写“/”则代表当前应用的目录 下，绝对路径。  如果不写“/”则代表相对路径
isErrorPage:是否创建throwable对象。默认是false
contentType:等同于response.setContentType(“text/html”；charset=utf-8);服务器发送客户端的内容编码
pageEncoding: Jsp文件本身的编码
isELIgnored: 是否支持EL表达式。 默认是false,支持表达式;是true表示不支持表达式，${1+1};false输出结果2   true按照原样输出  
```

#### 2.1.2 include指令

```
include指令
  		 静态包含：把其它资源包含到当前页面中  <%@ include file="header.jsp" %>
          动态包含： <jsp:include page=“header.jsp"></jsp:include>
     两者的区别：翻译的时间段不同(可在tomcat工作目录下查看)
                静态包含：在翻译时就把两个文件进行合并,运行时效率高。
                动态包含：不会合并文件，当代码执行到include时，才包含另一个文件的内容。
```

#### 2.1.3 taglib指令

```
作用：在JSP页面中导入JSTL标签库。JSTL替换jsp中的java代码片段。
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %> 
```

###   2.2 JSP注释

​		 格式： <%-- JSP注释 --%>：安全，省流量   仅在JSP文件里可以看到
​          	 网页注释：<!-- 网页注释 --> ,特点，不安全，耗流量      

#### 1.4.3 代码演示

```jsp
<%--指令，页面的设置--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%--1、模板元素 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>第一个JSP页面</title>
</head>
<body>
<%--我是jsp特有的注释 --%>
<!--我是HTML的注释  -->
<a href="http://www.baidu.com">百度一下</a>
</body>
</html>
```

### 	2.3脚本元素

#### 	2.3.1**脚本**：

> 语法：<% java代码 %>
>
> 示例：<%  int i=10;%>					

#### **2.3.2脚本表达式：**

> System.out.println()//控制台
>
> 输出变量： <%=2+3 %>等价于输出语句  (注意：不能使用分号来结束表达式)
>

#### **2.3.3声明：**

> <%!
>
> ​	定义方法
>
> %>     

通过查看源码，发现所有的jsp页面都变成类, (文件名_jsp.java),类继承HttpJspBase, HttpJspBase又继承HttpServlet,所有的jsp页面都是Servlet，脚本里的变量是局部变量；声明里的变量是全局变量。

### 2.4 动作标签

使用标签的形式表示一段java代码，例如：

2.4.1 jsp:include 动态包含

```jsp
<jsp:include page="index.jsp"></jsp:include>
当前页面包含index.jsp
```

2.4.2	jsp:forward请求转发

> ```jsp
> <jsp:forward>动作是在服务器端完成的，在浏览器端地址栏中的内容并不会改变。
> 
> <jsp:forward>动作只包含一个page属性，可以使用<jsp:param>元素来指定参数列表。
>     
> jsp:param 请求设置参数
> 	<jsp:forward page="a.jsp">
>          <jsp:param name="username" value="aqiang"/>
>          <jsp:param name="password" value="123456"/>
>     </jsp:forward>
> ```

2.4.3 JavaBean指令	

jsp:useBean 创建一个对象  id   class
jsp:setProperty给指定的对象属性赋值  name  property value
jsp:getProperty取出指定的对象属值    name  property

```jsp
<jsp:useBean id="user" class="com.qf.www.entity.User"></jsp:useBean>
<%--为指定对象的指定属性赋值--%>
<jsp:setProperty name="user" property="username" value="aqiang"></jsp:setProperty><jsp:setProperty name="user" property="password" value="123456"></jsp:setProperty>
<%--取值--%><jsp:getProperty name="user" property="username"/>
			<jsp:getProperty name="user" property="password"/>

与表单进行精确匹配
<jsp:useBean id="user" class="com.qf.www.entity.User"></jsp:useBean>
    <jsp:setProperty property="*" name="user" />
用户名：<jsp:getProperty name="user" property="username"/>
密码：<jsp:getProperty name="user" property="password"/>
```

## 第三节 内置对象

### 3.1九大内置对象（重点）

Servlet中内置对象:request 、response、session、application(servletContext)、config、out(PrintWriter)

Jsp本质是Servlet，包含九个内置对象

| 对象名             | 类型                                     | 说明                          |
| --------------- | -------------------------------------- | --------------------------- |
| request         | javax.servlet.http.HttpServletRequest  |                             |
| response        | javax.servlet.http.HttpServletResponse |                             |
| session         | javax.servlet.http.HttpSession         | 由session=“true”开关           |
| application     | javax.servlet.ServletContext           |                             |
| config          | javax.servlet.ServletConfig            |                             |
| exception       | java.lang.Throwable                    | 由isErrorPage=“false”开关      |
| **out**         | javax.servlet.jsp.JspWriter            | javax.servlet.jsp.JspWriter |
| **pageContext** | javax.servlet.jsp.PageContext          |                             |
| page            | java.lang.Object当前对象this               | 当前servlet实例                 |

r r s a c e o p p

代码演示：

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP内置对象</title>
</head>
<body>
<%
//页面上下文对象
pageContext.setAttribute("msg", "我是PageContext对象");
System.out.println(pageContext.getAttribute("msg"));
//请求对象
System.out.println("请求对象："+request);
//响应对象
response.getWriter().print("OK");
//当前页面对象
System.out.println(page.getClass());//输出到控制台
//输出对象
out.print(48);//输出到浏览器：48
out.write(48);//输出到浏览器：0
int no=101;
//会话对象
System.out.println(session);
//全局对象
System.out.println(application.getRealPath("/"));
//配置对象--初始化参数
System.out.println(config.getServletName());
//异常对象
System.out.println(exception);
System.out.println(application);
application.setAttribute("author", "1711");
%>
<h1>jsp脚本：<%=no %></h1>
<h1>jsp脚本：<% out.print(no); %></h1>
</body>
</html>
```

jsp的out和getWriter()方法的区别

		1 out是JspWriter类型，getWriter()是PrintWriter类型
		2 out输出到缓冲区中，没有写到response中，getWriter()直接写到response中。
		3 out一般用在jsp中，getWriter()用在Servlet中


### 3.2 pageContext对象

pageContext作用：

​	1 作为容器使用

​	2  代表页面上下文,获取其他八个内置对象  

#### 3.2.1 域对象

jsp中共包含四个域对象分别是pageContext、request、session、application

request域的使用场景:本次请求之后，数据就不再使用了，根据学号查询学生，根据新闻id查询新闻。

session域的使用场景:同一个会话中共享的数据，使用session，用户登录信息

application域的使用场景:所有用户共享的数据。整个程序的配置信息

pageContext域的使用场景：当前页面。

其中pageContext可以操作其他三个域对象(request,session,application)的数据

```
    void setAttribute(String name,Object o);
    Object getAttribute(String name);
    void removeAttribute(String name);

操作其它域对象的方法
    void setAttribute(String name,Object o,int Scope);
    Object getAttribute(String name,int Scope);
    void removeAttribute(String name,int  Scope);
Scope作用域，值如下:
    PageContext.PAGE_SCOPE
    PageContext.REQUEST_SCOPE
    PageContext.SESSION_SCOPE
    PageContext.APPLICATION_SCOPE

findAttribute(Stringname)自动从pageContext,request ,session ,application依次查找，
                         找到了就取值，结束查找  （作用域的范围由小到大）
```

#### 3.2.2 它可以访问其它的8个内置对象
  在普通类中可以通过PageContext获取其它JSP隐式对象,具体如下:

```
getException方法返回exception隐式对象 
getPage方法返回page隐式对象
getRequest方法返回request隐式对象 
getResponse方法返回response隐式对象 
getServletConfig方法返回config隐式对象
getServletContext方法返回application隐式对象
getSession方法返回session隐式对象 
getOut方法返回out隐式对象
```

#### 3.2.3 提供了简易方法

```
pageContext.forward(“2.jsp”);//转发  request.getRequestDispatcher().forward();
pageContext.include(“2.jsp”);//动态包含
```

## 第四节 将之前案例整改为JSP

#### 总结

1 jsp 包含的内容 

​	 指令   脚本  脚本表达式  声明   HTML  css  javascript

​	<%@page  language="java" contentType="" pageEncoding="utf-8"%>

​	<%

​	%>

​	<%=aaa%>

​	<%!

​	%>

2 指令

​	page指令

​	include包含指令

​	taglib 指令

3 jsp动作标签

​	jsp:include

​	jsp:forward

​	jsp:parm

​	jsp:useBean

​	jsp:setPropertity

​	jsp:getPropertity

4 jsp内置对象(九大)

​	request

​	response

​	session

​	application

​	config

​	exception

​	out

​	pageContext

​	page

5 pageContext: 1获取其他八个内置对象 ，2 作为容器使用，可以获取或设置其他容器中的数据

6 4个域对象  request session application pageContext





​	

#### 作业题

```
1、使用JSP实现登录注册和图书浏览功能
```

#### 面试题

```
1、Jsp和Servlet的区别
2、Jsp的执行原理
3、说说Jsp的隐藏对象有哪些
4、说出Jsp内置对象以及方法
```