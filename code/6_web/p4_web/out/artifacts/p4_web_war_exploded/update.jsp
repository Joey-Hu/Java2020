<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新员工数据</title>
</head>
<body>
    <form method="post" action="">
        <table>
            <tr>
                <td>
                    员工编号：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="empno" value="${emp.empno}" readonly >
                </td>
            </tr>
            <tr>
                <td>
                    员工姓名：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="empname" value="${emp.empname}" >
                </td>
            </tr>
            <tr>
                <td>
                    员工职位：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="job" value="${emp.job}" >
                </td>
            </tr>
            <tr>
                <td>
                    直属经理：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="mgr" value="${emp.mgr}" >
                </td>
            </tr>
            <tr>
                <td>
                    出生日期：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="date" name="hiredate" value="${emp.hiredate}" >
                </td>
            </tr>
            <tr>
                <td>
                    工资：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="sal" value="${emp.sal}" >
                </td>
            </tr>
            <tr>
                <td>
                    奖金：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="comm" value="${emp.comm}" >
                </td>
            </tr>
            <tr>
                <td>
                    部门名称：
                </td>
            </tr>
            <tr>
                <td>
                    <select name="deptno">
                        <c:forEach items="${depts}" var="dept">
                            <option value="${dept.deptno}"

                            ></option>
                        </c:forEach>



                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    员工编号：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="empno" value="${emp.empno}" >
                </td>
            </tr>
        </table>

    </form>
</body>
</html>
