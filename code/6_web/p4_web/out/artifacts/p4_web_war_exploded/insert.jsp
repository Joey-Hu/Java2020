<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>增加用户信息</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/insert">
    <table>
        <tr>
            <td>
                员工编号：
            </td>
            <td>
                <input type="text" name="empno" >
            </td>
        </tr>
        <tr>
            <td>
                员工姓名：
            </td>
            <td>
                <input type="text" name="ename" >
            </td>
        </tr>
        <tr>
            <td>
                员工职位：
            </td>
            <td>
                <input type="text" name="job" >
            </td>
        </tr>
        <tr>
            <td>
                直属经理：
            </td>
            <td>
                <input type="text" name="mgr" >
            </td>
        </tr>
        <tr>
            <td>
                出生日期：
            </td>
            <td>
                <input type="date" name="hiredate" >
            </td>
        </tr>
        <tr>
            <td>
                工资：
            </td>
            <td>
                <input type="text" name="sal" >
            </td>
        </tr>
        <tr>
            <td>
                奖金：
            </td>
            <td>
                <input type="text" name="comm" >
            </td>
        </tr>
        <tr>
            <td>
                部门名称：
            </td>
            <td>
                <select name="deptno">
                    <c:forEach items="${allDepts}" var="dept">
                        <option value="${dept.deptno}">${dept.dname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交">
            </td>
            <td>
                <input type="reset" value="恢复">
            </td>
        </tr>
    </table>

</form>
</body>
</html>
