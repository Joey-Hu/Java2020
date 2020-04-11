<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<%--
    ${pageContext.request.contextPath}是JSP取得绝对路径的方法，等价于<%=request.getContextPath()%>
    也就是取出部署的应用程序名或者是当前的项目名称
    比如我的项目名称是demo1在浏览器中输入为http://localhost:8080/demo1/a.jsp ${pageContext.request.contextPath}或
    <%=request.getContextPath()%>取出来的就是/demo1,而"/"代表的含义就是http://localhost:8080

    故有时候项目中这样写${pageContext.request.contextPath}/a.jsp

    source: https://www.cnblogs.com/zshibo/p/8011514.html
--%>
    <form method="post" action="${pageContext.request.contextPath}/login">  <%--提交到loginServlet--%>
        用户名：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        <input type="submit" value="提交"><br>
    </form>

</body>
</html>
