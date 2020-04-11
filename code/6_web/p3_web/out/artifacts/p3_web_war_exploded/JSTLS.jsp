<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>JSTL应用</title>
</head>
<body>
    <c:set var="a" value="5"/>
    <c:out value="${a}"/><br />
    <%--声明变量并赋值 单个值  如果是运算的值  加EL表达式 默认存在pageContext作用域--%>
    <c:set var="b" value="${1+1}" scope="request"/>
    <c:out value="${b}"/><br />
    <c:remove var="a"/>

    <hr/>
    <%-- 给指定变量赋默认值（如果没有该变量的话），不会创建变量 --%>
    <c:out value="${a}" default="没有值"/>
    <c:out value="${a}"/>



</body>
</html>
