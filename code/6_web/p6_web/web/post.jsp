<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/23
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax中post方法请求数据</title>
</head>
<body>
  <div id = "div1">
      看这里。。。
  </div>

  <button onclick="change()">
      点击
  </button>

  <script type="text/javascript">
      function change(){
          // 1. 创建对象
          var xhr = new XMLHttpRequest();

          // 2. 设置回调函数
          xhr.onreadystatechange = function (ev) {
              if(xhr.readyState == 4 && xhr.status == 200){
                  document.getElementById("div1").innerHTML = xhr.responseText;
              }
          }

          // 3. 建立连接
          xhr.open("POST", "${pageContext.request.contextPath}/post", true);

          // 4. 发送请求
          //post提交的数据应该以表单形式提交
          xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
          xhr.send("username=hh&password=1234");

      }

  </script>


</body>
</html>
