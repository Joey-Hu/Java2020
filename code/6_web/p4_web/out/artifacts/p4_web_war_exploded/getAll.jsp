<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>获取所有</title>
</head>
<body>
    <table border="1" bordercolor="red">
        <tr>
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>员工职位</th>
            <th>直属经理</th>
            <th>入职日期</th>
            <th>工资</th>
            <th>奖金</th>
            <th>部门编号</th>
            <th>操作</th>
        </tr>

        <c:forEach var="emp" items="${list}">
            <tr>
                <td>${emp.empno}</td>
                <td>${emp.ename}</td>
                <td>${emp.job}</td>
                <td>${emp.mgr}</td>
                <td>${emp.hiredate}</td>
                <td>${emp.sal}</td>
                <td>${emp.comm}</td>
                <td>${emp.deptno}</td>
                <td><a href="${pageContext.request.contextPath}/getEmp?empno=${emp.empno}">修改</a> &ensp;
                    &ensp;<a href="${pageContext.request.contextPath}/delete?empno=${emp.empno}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/getDept">新增员工</a><br />
    <a href="${pageContext.request.contextPath}/login?pageIndex=1&pageSize=3">首页</a>
    <c:if test="${pageIndex>1}">
        <a href="${pageContext.request.contextPath}/login?pageIndex=${pageIndex-1}&pageSize=3">上一页</a>
    </c:if>
    <c:forEach var="i" begin="1" end="${total}" step="1">
        <a href="${pageContext.request.contextPath}/login?pageIndex=${i}&pageSize=3">${i}</a>
    </c:forEach>
    <c:if test="${pageIndex<total}">
        <a href="${pageContext.request.contextPath}/login?pageIndex=${pageIndex+1}&pageSize=3">下一页</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/login?pageIndex=${total}&pageSize=3">尾页</a>
</body>
</html>
