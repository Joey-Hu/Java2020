<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/23
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>
</head>
<body>
    <div id="div1">change here...</div>
    <button onclick="change()">ajax修改的内容</button>
</body>

<script type="text/javascript">
    function change(){
        // 1. 创建 XMLHttpRequest 对象
        var xhr;
        // 为了兼容市面所有浏览器
        if(window.XMLHttpRequest){
            //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xhr = new XMLHttpRequest();
        }else{
            // IE6, IE5 浏览器执行代码
            xhr = new ActiveXObject("Microsoft.XMLHTTP");

        }

        // 2. 设置回调函数
        // xhr.readstate 状态发生改变时调用该函数 初始状态是0
        // 每当xhr.readstate 发生变化时，调用下面的函数
        xhr.onreadystatechange = function(data){
            // 代表服务器状态          响应结果
            if(xhr.readyState == 4 && xhr.status == 200){
                // 动态修改页面的局部内容
                document.getElementById("div1").innerHTML = xhr.responseText;
                // document.getElementById("div1").innerText = xhr.responseText;
                document.getElementById("div1").style.color = "red";
            }
        }

        // 3.打开连接  请求方式  请求地址. 异步|同步
        xhr.open("GET", "book.xml", true);
        <%--xhr.open("GET", "${pageContext.request.contextPath}/test", true);--%>
        // 4. 发送请求
        xhr.send();
    }
</script>

</html>
