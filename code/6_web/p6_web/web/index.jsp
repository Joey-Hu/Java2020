<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/19
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>JSON使用</title>
  </head>
  <body>
  </body>

  <script type="text/javascript">
    // JSON 对象
    var stu1 = {"name":"张三", "age":23, "gender":"男"};
    console.log(stu1.name + ";" + stu1.age + ";" + stu1.gender);
    console.log(stu1);

    // JSON 字符串(定义方式符合了JSON 的定义方式,那么就可以转换为JSON对象)
    var stu2 = '{"name":"李四", "age":20, "gender":"男"}';

    // JSON 字符串转换为 JSON 对象
    var parse = JSON.parse(stu2);
    console.log(parse.name + ";" + parse.age + ";" + parse.gender)

    // JSON 对象转 JSON 字符串
    var jsonString = JSON.stringify(stu1);
    console.log(jsonString);

  </script>
</html>
