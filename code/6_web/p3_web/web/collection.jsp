<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/11
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>集合</title>
</head>
<body>
    <%
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        request.setAttribute("list", list);

        Map<String, String> map = new HashMap<String, String>();
        map.put("aa", "11");
        map.put("bb", "22");
        map.put("cc", "33");
        map.put("dd", "44");
        request.setAttribute("map", map);

    %>

    <%= list.get(0) %><br />

    <%--    使用EL表达式  访问集合  下标 拿一个值--%>
    ${list[0]}<br />
    ${list.get(0)}<br />

    ${map["aa"]}<br />
    ${map.aa}<br />
    ${map.get("aa")}<br />





</body>
</html>
