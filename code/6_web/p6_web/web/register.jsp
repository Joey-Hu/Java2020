<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/23
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <form action="register" method="post">
        用户名：<input type="text" name="username" onblur="check(this)"><span id = "sp"></span><br/>
        密码：<input type="password" name="password" ><br/>
        <input type="submit" value="注册"><br/>
    </form>

</body>
    <script type="text/javascript">
        function check(input) {
            var value = input.value;

            var xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function (ev) {
                if(xhr.readyState == 4 && xhr.status == 200){
                    var responseText = xhr.responseText;

                    // 判断servlet 返回来的值
                    if(responseText == "1"){
                        document.getElementById("sp").innerHTML = "该用户名已被注册";
                        document.getElementById("sp").style.color = "red";
                    }else{
                        document.getElementById("sp").innerHTML = "该用户名可以注册";
                        document.getElementById("sp").style.color = "green";
                    }
                }
            }

            xhr.open("GET", "${pageContext.request.contextPath}/check?username=" + value, true);
            xhr.send();
        }
    </script>
</html>
