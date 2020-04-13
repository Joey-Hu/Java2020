<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/13
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件下载列表</title>
</head>
<body>
    <h3>下载列表</h3>
    <table>
        <tr>
            <th>文件名称</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${fileList}" var="file">
            <tr>
                <td>${file.value}</td>
                <td><a href="${pageContext.request.contextPath}/download?filename=${file.key}">下载</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
