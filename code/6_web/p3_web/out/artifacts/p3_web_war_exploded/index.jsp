<%--
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
  %>

  <%= request.getAttribute("username")%><br/>
  <%= pageContext.findAttribute("username")%><br/>

  EL 表达式<br/>
  <%--EL表达式--%>
  ${username}

  </body>
</html>
