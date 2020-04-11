<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hh.www.entinity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>循环结构</title>
</head>
<body>

    <%
        List<User> list = new ArrayList<User>();
        list.add(new User("zhangsanfeng", "1234"));
        list.add(new User("zhangcuishan", "12345"));
        list.add(new User("zhangwuji", "123456"));
        pageContext.setAttribute("list", list);
    %>
    <%--基础遍历--%>
    <c:forEach var="i" begin="1" end="100" step="10">
         ${i}
    </c:forEach>

    <%--对象遍历--%>
    <h3>测试list集合遍历获取用户列表</h3>
    <table border="1" width="80%" bordercolor="red" cellpadding="0" align="center">
        <tr>
            <th>用户名</th>
            <th>密码</th>
            <th>是否是第一个</th>
            <th>是否是最后一个</th>
            <th>计数count</th>
            <th>索引index</th>
        </tr>
        <c:forEach var="user" items="${list}" varStatus="vs">
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${vs.first}</td>
                <td>${vs.last}</td>
                <td>${vs.count}</td>
                <td>${vs.index}</td>
            </tr>

        </c:forEach>
    </table>


<br />

</body>
</html>
