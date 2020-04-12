<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/12
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/fus" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"><br>
    文件1：<input type="file" name="file1"><br>
    文件2：<input type="file" name="file2"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
