### JSON和AJAX

#### 

#### 今日内容

```
1、JSON解析
2、什么是Ajax
3、Ajax工作原理
4、Ajax的使用
5、Ajax的综合练习
```

#### 教学目标

```
1、掌握JSON解析
2、熟悉什么是Ajax
3、掌握Ajax工作原理
4、掌握Ajax的使用
5、熟练Ajax的综合练习
```

#### 第一节 JSON概述

##### 1.1 什么是json

JSON(JavaScript Object Notation, JS 对象表示) 是一种轻量级的数据交换格式。它基于 ECMAScript (w3c制定的js规范)的一个子集，采用完全独立于编程语言的文本格式来存储和表示数据。简洁和清晰的层次结构使得 JSON 成为理想的数据交换语言。易于机器解析和生成，并有效地提升网络传输效率。

##### 1.2 json语法

[]  表示数组

{}  表示对象

""  表示是属性名或字符串类型的值

:   表示属性和值之间的分隔符

,   表示多个属性的间隔符或者是多个元素的间隔符



json的值：

- 数字（整数或浮点数）
- 字符串（在双引号或单引号中）
- 逻辑值（true 或 false）
- 数组（在中括号中）
- 对象（在大括号中）
- null

实例演示：

```java
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>json语法</title>
</head>
<body>
	<script type="text/javascript">
		//表示对象
		var  p=new Object();
		p.name="haha";
		p.age=20;
		p.address="beijing";
		console.log(p.name+"..."+p.age+"...."+p.address);
		
		var p1={"name":"李四","age":30,"address":"上海"};
		console.log(p1.name+"..."+p1.age+"...."+p1.address);
		
		var json='{"name":"李四","age":30,"address":"杭州"}';//json字符串
		
		var p2=JSON.parse(json);
		console.log(p2.name+"..."+p2.age+"...."+p2.address);
		
		//要实现从对象转换为 JSON 字符串，使用 JSON.stringify() 方法：
		//var json = JSON.stringify({a: 'Hello', b: 'World'}); //结果是 '{"a": "Hello", "b": "World"}'
		//要实现从 JSON 转换为对象，使用 JSON.parse() 方法：
		//var obj = JSON.parse('{"a": "Hello", "b": "World"}'); //结果是 {a: 'Hello', b: 'World'}

		//表示数组
		
		var arr=["北京","上海","南京"];
		var arr2=[{name:"xxx",age:20},{name:"yyy",age:22},{name:"zzz",age:30}];
			
	</script>
</body>
</html>
```





#### 第二节 JSON解析

要解析的字符串：

```java
//对象嵌套数组嵌套对象
String json1="{'id':1,'name':'JAVAEE-1910','stus':[{'id':101,'name':'刘一','age':16}]}";
//数组
String json2="['北京','天津','杭州']";
```

初始的类：

Student.java

```java
public class Student {

    private int id;
    private String name;
    private int age;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Student(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Student() {
        super();
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
```

Grade.java

```java
public class Grade {

    private int id;
    private String name;
    private ArrayList<Student> stus;
    public Grade(int id, String name, ArrayList<Student> stus) {
        super();
        this.id = id;
        this.name = name;
        this.stus = stus;
    }
    public Grade() {
        super();
    }
    @Override
    public String toString() {
        return "Grade [id=" + id + ", name=" + name + ", stus=" + stus + "]";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Student> getStus() {
        return stus;
    }
    public void setStus(ArrayList<Student> stus) {
        this.stus = stus;
    }
}
```



##### 2.1 FastJson解析

```java
public class FASTJson {

    //解析
    @Test
    public void test1() {
        // 对象嵌套数组嵌套对象
        String json1 = "{'id':1,'name':'JAVAEE-1703','stus':[{'id':101,'name':'刘铭','age':16}]}";
        // 数组
        String json2 = "['北京','天津','杭州']";
        //1、
        //静态方法转成对象
        Grade grade=JSON.parseObject(json1, Grade.class);
        System.out.println(grade);
        //转成数组
        List<String> list=JSON.parseArray(json2, String.class);
        System.out.println(list);
    }
    //生成
    @Test
    public void test2(){
        ArrayList<Student> list=new ArrayList<>();
        for(int i=1;i<3;i++){
            list.add(new Student(101+i, "码子", 20+i));
        }
        Grade grade=new Grade(100001,"张三", list);
        String json=JSON.toJSONString(grade);
        System.out.println(json);
    }
}

//注意事项
	//1.1控制不序列化某些属性  @JSONField(serialize=false)  默认是true
	//1.2控制输出  
		// SerializerFeature.PrettyFormat
		// SerializerFeature.WriteMapNullValue  null也输出
		// SerializerFeature.WriteNullStringAsEmpty   null输出""
		// 循环引用检测 SerializerFeature.DisableCircularReferenceDetect
```

##### 2.2 Jackson解析

```java

import com.fasterxml.jackson.core.type.TypeReference;
public class JackSonTest {

    //解析
    @Test
    public void test1() throws Exception{
        // 对象嵌套数组嵌套对象
        String json1 = "{\"id\":1,\"name\":\"JAVAEE-1703\",\"stus\":[{\"id\":101,\"name\":\"刘一\",\"age\":16}]}";
        // 数组
        String json2 = "[\"北京\",\"天津\",\"杭州\"]";
        //1、创建对象映射
        ObjectMapper mapper=new ObjectMapper();
        Grade grade=mapper.readValue(json1, Grade.class);
        System.out.println(grade);
        //2、
        ArrayList<String> list=mapper.readValue(json2, 
                new TypeReference<ArrayList<String>>() {
        });
        System.out.println(list);

    }
    //生成
    @Test
    public void test2() throws JsonProcessingException{
        ArrayList<Student> list=new ArrayList<>();
        for(int i=1;i<3;i++){
            list.add(new Student(101+i, "码子", 20+i));
        }
        Grade grade=new Grade(100001,"张三", list);
        ObjectMapper mapper=new ObjectMapper();
        //将对象转换为JSON格式字符串
        String json=mapper.writeValueAsString(grade);
        System.out.	println(json);
    }
}
//Jackson注解
//1 @JsonProperty注解指定一个属性用于JSON映射，默认情况下映射的JSON属性与注解的属性名称相同，不过可以使用该注解的value值修改JSON属性名
//2 @JsonIgnore注解用于排除某个属性，这样该属性就不会被Jackson序列化和反序列化。
//3 格式化输出
// 美化输出
//mapper.enable(SerializationFeature.INDENT_OUTPUT);
// 允许序列化空的POJO类（否则会抛出异常）
//mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

```







#### 第三节 Ajax概述

AJAX 是一种在**无需重新加载整个网页**的情况下，能够实现**局部更新**的技术。

##### 3.1 什么是AJAX   

AJAX = 异步 JavaScript 和 XML。  (Asynchronized JavaScript And  XML)

AJAX 是一种用于创建快速动态网页的技术。

通过在后台与服务器进行少量数据交换，AJAX 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。

传统的网页（不使用 AJAX）如果需要更新内容，必需重载整个页面。

有很多使用 AJAX 的应用程序案例：新浪微博、Google 地图、开心网等等。

##### 3.2 AJAX工作原理

![img1](img\img1.png)

AJAX是基于现有的Internet标准，并且联合使用它们：

- XMLHttpRequest 对象 (异步的与服务器交换数据)
- JavaScript/DOM (信息显示/交互)
- CSS (给数据定义样式)
- XML (作为转换数据的格式)、JSON

##### 3.3 AJAX实例

使用Ajax发送请求四个步骤:

**1 创建XMLHTTPRequest对象**

**2 设置onreadystatechange回调函数**

**3 open() 打开连接**

**4 send() 发送请求**

div 部分用于显示来自服务器的信息。当按钮被点击时，它负责调用名为 loadXMLDoc() 的函数：

```html
<!DOCTYPE html>
<html>
<head>
  <title>ajax实例</title>
  <meta charset="utf-8"/>
  <script type="text/javascript">
	function loadXMLDoc()
	{
   //1 创建XMLHTTPRequest对象
			var xhr;
			if(window.XMLHttpRequest){
				xhr=new XMLHttpRequest();
			}else{
				//ie
				xhr=new ActiveXObject("Microsoft.XMLHTTP");
			}

			//2 设置onreadystatechange回调函数(异步，如果同步不需要)
			xhr.onreadystatechange=function(){
				//处理数据  readyState==4表示服务器响应完毕    状态码 200  404  500  302
				if(xhr.readyState==4&&xhr.status==200){
					document.getElementById("mydiv").innerHTML=xhr.responseText;
				}
			}

			//3 open() 打开连接  method(GET POST)  url(表示请求的地址)  async（同步或异步  true异步，false同步）
			xhr.open("GET","book.xml",true);

			//4 send() 发送请求 
			xhr.send();

	}
</script>
</head>
<body>
  	<div id="myDiv"><h2>使用 AJAX 修改该文本内容</h2></div>
    <button type="button" onclick="loadXMLDoc()">修改内容</button>
</body>
</html> 
```

book.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<books>
    <book>
        <id>1101</id>
        <bookname>java开发</bookname>
        <author>徐廉政</author>
        <price>99</price>
    </book>
    <book>
        <id>1102</id>
        <bookname>HTML开发</bookname>
        <author>张健</author>
        <price>100</price>
    </book>
</books>
```



##### 3.4 创建XMLHttpRequest对象

XMLHttpRequest对象是AJAX的基础。
所有现代浏览器均支持 XMLHttpRequest 对象（IE5 和 IE6 使用 ActiveXObject）。
XMLHttpRequest 用于在后台与服务器交互数据。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。

所有现代浏览器（IE7+、Firefox、Chrome、Safari 以及 Opera）均内建 XMLHttpRequest 对象。创建 XMLHttpRequest 对象的语法：

```javascript
var xmlhttp=new XMLHttpRequest();
```

老版本的 Internet Explorer （IE5 和 IE6）使用 ActiveX 对象：

```javascript
var xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
```

为了应对所有的现代浏览器，包括 IE5 和 IE6，请检查浏览器是否支持 XMLHttpRequest 对象。如果支持，则创建 XMLHttpRequest 对象。如果不支持，则创建 ActiveXObject ：

```javascript
var xmlhttp;
if (window.XMLHttpRequest)
{
    //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
}
else
{
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
```



##### 3.5 onreadystatechange回调函数

当请求被发送到服务器时，我们需要执行一些基于响应的任务。每当 readyState 改变时，就会触发 onreadystatechange 事件。readyState 属性存有 XMLHttpRequest 的状态信息。
下面是 XMLHttpRequest 对象的三个重要的属性：

| 属性                 | 描述                                       |
| ------------------ | ---------------------------------------- |
| onreadystatechange | 存储函数（或函数名），每当 readyState 属性改变时，就会调用该函数。  |
| readyState         | 存有 XMLHttpRequest 的状态。从 0 到 4 发生变化。0: 请求未初始化1: 服务器连接已建立2: 请求已接收3: 请求处理中4: 请求已完成，且响应已就绪 |
| status             | 200: "OK"404: 未找到页面                      |

在 onreadystatechange 事件中，我们规定当服务器响应已做好被处理的准备时所执行的任务。

当 readyState 等于 4 且状态为 200 时，表示响应已就绪：

```javascript
xmlhttp.onreadystatechange=function()
{
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
        document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
}
```

**注意：** onreadystatechange 事件被触发 4 次， 五个状态（0 - 4），对应着 readyState 的每个变化。







##### 3.5 XMLHttpRequest请求

如需将请求发送到服务器，我们使用 XMLHttpRequest 对象的 open() 和 send() 方法：

```javascript
xmlhttp.open("GET","servlet1",true);
xmlhttp.send();
```

| 方法                           | 描述                                       |
| ---------------------------- | ---------------------------------------- |
| open(*method*,*url*,*async*) | 规定请求的类型、URL 以及是否异步处理请求。*method*：请求的类型；GET 或 POST*url*：文件在服务器上的位置*async*：true（异步）或 false（同步） |
| send(*string*)               | 将请求发送到服务器。*string*：仅用于 POST 请求           |

**method-请求方式**

***GET 还是 POST？***

与 POST 相比，GET 更简单也更快，并且在大部分情况下都能用。

然而，在以下情况中，请使用 POST 请求：

- 不使用缓存文件（更新服务器上的文件或数据库）
- 向服务器发送大量数据（POST 没有数据量限制）
- 发送包含未知字符的用户输入时，POST 比 GET 更稳定也更可靠

***GET 请求***

一个简单的 GET 请求：

```javascript
xmlhttp.open("GET","/try/ajax/servlet3",true);
xmlhttp.send();
```

在上面的例子中，您可能得到的是缓存的结果。

为了避免这种情况，请向 URL 添加一个唯一的 ID：

```javascript
xmlhttp.open("GET","/try/ajax/servlet3?number=" + Math.random(),true);
xmlhttp.send();
```

如果您希望通过 GET 方法发送信息，请向 URL 添加信息：

```javascript
xmlhttp.open("GET","/try/ajax/servlet3?username=leilei&password=123",true);
xmlhttp.send();
```

***POST 请求***

一个简单 POST 请求：

```javascript
xmlhttp.open("POST","/try/ajax/servlet4",true);
xmlhttp.send();
```

如果需要像 HTML 表单那样 POST 数据，请使用 setRequestHeader() 来添加 HTTP 头。然后在 send() 方法中规定您希望发送的数据：

```javascript
xmlhttp.open("POST","/try/ajax/servlet4",true);
xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xmlhttp.send("username=leilei&password=123");
```

| 方法                               | 描述                                       |
| -------------------------------- | ---------------------------------------- |
| setRequestHeader(*header,value*) | 向请求添加 HTTP 头。*header*: 规定头的名称    *value*: 规定头的值 |

**url - 服务器地址**

open() 方法的 *url* 参数是服务器上资源的地址：

```javascript
xmlhttp.open("GET","servlet5",true);
```

**异步 - True 或 False**

AJAX 指的是异步 JavaScript 和 XML（Asynchronous JavaScript and XML）。
XMLHttpRequest 对象如果要用于 AJAX 的话，其 open() 方法的 async 参数必须设置为 true：

```javascript
xmlhttp.open("GET","ajax_test.html",true);
```

对于 web 开发人员来说，发送异步请求是一个巨大的进步。很多在服务器执行的任务都相当费时。AJAX 出现之前，这可能会引起应用程序挂起或停止。

通过 AJAX，JavaScript 无需等待服务器的响应，而是：

- 在等待服务器响应时执行其他脚本
- 当响应就绪后对响应进行处理

**Async=true**

当使用 async=true 时，请规定在响应处于 onreadystatechange 事件中的就绪状态时执行的函数：

```javascript
xmlhttp.onreadystatechange=function()
{
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
        document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
}
xmlhttp.open("GET","/try/ajax/ajax_info.txt",true);
xmlhttp.send();
```



**Async=false**

如需使用 async=false，请将 open() 方法中的第三个参数改为 false：

我们不推荐使用 async=false，但是对于一些小型的请求，也是可以的。

请记住，JavaScript 会等到服务器响应就绪才继续执行。如果服务器繁忙或缓慢，应用程序会挂起或停止。

**注意：**当您使用 async=false 时，请不要编写 onreadystatechange 函数 - 把代码放到 send() 语句后面即可：

```javascript
xmlhttp.open("GET","/try/ajax/ajax_info.txt",false);
xmlhttp.send();
document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
```



##### 3.6 XMLHttpRequest响应

***服务器响应***

如需获得来自服务器的响应，请使用 XMLHttpRequest 对象的 responseText 或 responseXML 属性。

| 属性           | 描述              |
| ------------ | --------------- |
| responseText | 获得字符串形式的响应数据。   |
| responseXML  | 获得 XML 形式的响应数据。 |

***responseText 属性***

如果来自服务器的响应并非 XML，请使用 responseText 属性。

responseText 属性返回字符串形式的响应，因此您可以这样使用：

```javascript
document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
```

***responseXML 属性***

如果来自服务器的响应是 XML，而且需要作为 XML 对象进行解析，请使用 responseXML 属性：

```javascript
xmlDoc=xmlhttp.responseXML;
txt="";
x=xmlDoc.getElementsByTagName("ARTIST");
for (i=0;i<x.length;i++)
{
    txt=txt + x[i].childNodes[0].nodeValue + "<br>";
}
document.getElementById("myDiv").innerHTML=txt;
```





#### 第四节 Ajax的使用

##### 4.1 Ajax与服务器交互

* 模拟登陆验证

  > 验证用户是否可以注册!利用ajax技术进行动态验证

  1.编写注册页面

```html
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="./ajax.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   		<center>
   			 <font color="red" size="7">qq注册页面</font>
   			 <input type="text" name="username" onkeyup="kp(this)" /> <span id="sp"></span> <br/>	 
   			 <input type="password" name="password"/><br/>
   			 <input type="submit" value="注册"/>
   		</center>
   		<script type="text/javascript">
   			
   			//当用户名输入框输入内容就调用此方法
   			function kp(ipt){
   			 
   			 //1.获取input输入框的value
   			   var value = ipt.value;
   			 //2.进行验证
   			   if(value != null && value !=""){
   			       	//1-5
   			       	//1.创建Ajax
   			       	var xmlhttp = getAjax();
   			       	
   			       	//2.设置状态改变监听
   			       	xmlhttp.onreadystatechange = function(){
   			       		
   			       		//5获取响应数据
   			       		if(xmlhttp.readyState == 4 && xmlhttp.status ==200)
   			       		{
   			       			
   			       			var result = xmlhttp.responseText; //获取结果
   			       			
   			       			//  1 行    2 不行
   			       			
   			       			//1.找到span标签
   			       			
   			       			var sp = document.getElementById("sp");
   			       			
   			       			if(result == "1"){
   			       				//成功的 span 提示一句绿色的话		
   			       				sp.innerHTML=""; //清空
   			       			 	var ft = document.createElement("font");
   			       				var fttext = document.createTextNode("恭喜您!可以注册!!"); //文本标签
   			       				ft.setAttribute("color", "green");
   			       				ft.appendChild(fttext);
   			       				sp.appendChild(ft);  
   			       			}else{
   			       				//失败的 span 提示一句红色的话
   			       				sp.innerText="用户已经被注册!";
   			       				sp.style.color = "red";
   			       			}
   			       		}
   			       	
   			       	}
   			       	
   			       	//3.设置ajax method  url
   			       	xmlhttp.open("POST",
   			       	 "${pageContext.request.contextPath}/servlet/DealServlet");
   			         
   			        //4.发送请求
   			        //设置一个请求头
   			        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
   			        xmlhttp.send("value="+value);
   			   
   			   }	
   			}
   		</script>
  </body>
</html>
```

##### 4.2 编写Ajax处理servlet

```java
public class DealServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.设置编码格式
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
		//1.
	    String value = request.getParameter("value");
		//2.
	    String  result = null;
	    if (value.equals("root") | value.equals("admin")) {
		   result = "2";
		   //代表已经存在
		}else{
			//可以注册
			result = "1";
		}
		//3.
		response.getWriter().write(result);   
    }	
}
```

#### 作业题

```
1、使用Ajax实现进行数据的异步提交和结果回显
2、请完成下列数据的解析(利用IDEA 插件直接从json 数据生成Java 实体类)
{
    "state": "success",
    "code": "0",
    "data": {
        "acc": 100,
        "city": "北京市",
        "dist": "通州区",
        "addr": "北京市通州区永乐店镇北京金篮子生态种植有限公司",
        "prov": "北京市",
        "lon": 116.82106018,
        "number": "13",
        "town": "永乐店镇",
        "street": "永乐大街",
        "lat": 39.69581985
    }
}
3、自己创建一张表   2个字段  username   password
	编写MVC+ajax。实现用户名框失去焦点，数据库验证用户名是否存在。给出相应的提示！

```

#### 面试题

```
1、为什么要用ajax
2、请介绍一下XMLhttprequest对象
3、Ajax和javascript的区别
4、请描述json的作用
5、请写出常用的json解析技术
```