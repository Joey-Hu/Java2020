<%@ page import="com.hh.www.entinity.User" %>
<%@ page import="com.hh.www.entinity.Address" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/7
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    request.setAttribute("username", "yanguo");
    session.setAttribute("username1", "xiaolongnv");
    application.setAttribute("username2", "wangchongyang");
    request.setAttribute("user", new User("zhangsan", "123", new Address("北京", "100000")));
  %>

  <%= request.getAttribute("username")%><br/>
  <%= pageContext.findAttribute("username")%><br/>

  EL 表达式<br/>
  <%--EL表达式--%>
  ${username}<br />
  ${username1}<br />
  ${username2}<br />
  ${user.username}<br />  <%-- 相当于user.getUsername() --%>
  ${user.address.zipcode}

  <%--  如果四个作用域都重名   想拿session里的  指定作用域--%>
  ${sessionScope.username1}



  </body>
</html>
