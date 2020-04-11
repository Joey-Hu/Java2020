<%@ page import="com.hh.www.entinity.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>分支结构</title>
</head>
<body>

    <%
        User user = new User("zhangsan", "1234");
        request.setAttribute("user", user);
    %>

    <c:if test="${user.username == 'zhangsan'}">
        <h1>欢迎您:${user.username}</h1>
    </c:if>

    <c:set var="score" value="30"/>

    <c:choose>
        <c:when test="${score>=90}"><font color="#ffc0cb"> 优秀</font></c:when>
        <c:when test="${score>=80}"><font color="blue"> 良好</font></c:when>
        <c:when test="${score>=60}"><font color="yellow"> 及格</font></c:when>
        <c:when test="${score<60}"><font color="red"> 不及格</font></c:when>
    </c:choose>

</body>
</html>
