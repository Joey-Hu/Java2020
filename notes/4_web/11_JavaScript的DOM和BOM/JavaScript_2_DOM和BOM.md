### JavaScript的BOM和DOM

#### 今日内容

```
1、Js的BOM
2、Js的DOM
```

#### 第一节 JavaScript的BOM

浏览器对象模型 (BOM) 使 JavaScript 有能力与浏览器"对话"。

浏览器对象模型 (BOM):(**B**rowser **O**bject **M**odel)尚无正式标准。

由于现代浏览器已经（几乎）实现了 JavaScript 交互性方面的相同方法和属性，因此常被认为是 BOM 的方法和属性。

##### 1.1 window

所有浏览器都支持 window 对象。它表示浏览器窗口。

所有 JavaScript 全局对象、函数以及变量均自动成为 window 对象的成员。

全局变量是 window 对象的属性。

全局函数是 window 对象的方法。

甚至 HTML DOM 的 document 也是 window 对象的属性之一：

```html
window.document.getElementById("header");
```

与此相同：

```html
document.getElementById("header");
```

###### 1.1.1 window尺寸

有三种方法能够确定浏览器窗口的尺寸。

对于Internet Explorer、Chrome、Firefox、Opera 以及 Safari：

- window.innerHeight - 浏览器窗口的内部高度(不包括滚动条、菜单栏、工具栏)
- window.innerWidth - 浏览器窗口的内部宽度(不包括滚动条、菜单栏、工具栏)

对于 Internet Explorer 8、7、6、5：

- document.documentElement.clientHeight
- document.documentElement.clientWidth

或者

- document.body.clientHeight
- document.body.clientWidth

实用的 JavaScript 方案（涵盖所有浏览器）：

```javascript
var w=window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth;
var h=window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight;
```

###### 1.1.2 window方法

- window.open() - 打开新窗口
- window.close() - 关闭当前窗口

###### 1.1.3 Screen

- 可用宽度：screen.availWidth 属性返回访问者屏幕的宽度，以像素计，减去界面特性，比如窗口任务栏。

返回您的屏幕的可用宽度：

```javascript
document.write("可用宽度: " + screen.availWidth);
```

以上代码输出为：

可用宽度: 1920

- 可用高度：screen.availHeight 属性返回访问者屏幕的高度，以像素计，减去界面特性，比如窗口任务栏。

返回您的屏幕的可用高度：

```javascript
document.write("可用高度: " + screen.availHeight);
```

以上代码将输出：

可用高度: 1040

###### 1.1.4 Location

window.location 对象用于获得当前页面的地址 (URL)，并把浏览器重定向到新的页面。

**window.location** 对象在编写时可不使用 window 这个前缀。 一些例子：

一些实例:

- location.href 属性返回当前页面的 URL。
- location.hostname 返回 web 主机的域名
- location.pathname 返回当前页面的路径和文件名
- location.port 返回 web 主机的端口 （80 或 443）
- location.protocol 返回所使用的 web 协议（http:// 或 https://）



location.href 属性返回当前页面的 URL。

代码：

```html
document.write(location.href);
```

以上代码输出为：

http://www.xxx.com/js/my.html

location.pathname 属性返回 URL 的路径名。

代码：

```html
document.write(location.pathname);
```

以上代码输出为：

/js/my.html

location.assign() 方法加载新的文档。

加载一个新的文档：

```html
<html>
<head>
<script>
function newDoc()
  {
  window.location.assign("http://www.baidu.com/")
  }
</script>
</head>
<body>

<input type="button" value="Load new document" onclick="newDoc()">

</body>
</html>
```

###### 1.1.5 History

window.history 对象包含浏览器的历史。

window.history 对象在编写时可不使用 window 这个前缀。

为了保护用户隐私，对 JavaScript 访问该对象的方法做出了限制。

一些方法：

- history.back() - 与在浏览器点击后退按钮相同

```html
<html>
<head>
<script>
function goBack()
  {
  window.history.back()
  }
</script>
</head>
<body>

<input type="button" value="Back" onclick="goBack()">

</body>
</html>
```

- history.forward() - 与在浏览器中点击按钮向前相同

```html
<html>
<head>
<script>
function goForward()
  {
  window.history.forward()
  }
</script>
</head>
<body>

<input type="button" value="Forward" onclick="goForward()">

</body>
</html>
```

#### 第二节 JavaScript计时

##### **2.1 Date对象**

```javascript
var d=new Date();
document.write(d);
document.write("<br/>")
document.write("年份："+(d.getYear()+1900));
document.write("<br/>")
document.write("年份："+d.getFullYear());
document.write("<br/>");
document.write("月份:"+(d.getMonth()+1))
document.write("<br/>");
document.write("日期:"+d.getDate());
document.write("<br/>");
document.write("小时:"+d.getHours());
document.write("<br/>");
document.write("分钟:"+d.getMinutes());
document.write("<br/>");
document.write("秒:"+d.getSeconds());
```



##### 2.2 JavaScript计时函数

**setInterval()** 周期执行函数

间隔指定的毫秒数不停地执行指定的代码：

每三秒弹出 "hello" ：

```javascript
setInterval(function(){alert("Hello")},3000);
```

实例展示了如何使用 setInterval() 方法，但是每三秒弹出一次对用户体验并不好。

以下实例将显示当前时间。 setInterval() 方法设置每秒钟执行一次代码，就是手表一样。

```javascript
var myVar=setInterval(function(){myTimer()},1000);

function myTimer()
{
var d=new Date();
var t=d.toLocaleTimeString();
document.getElementById("demo").innerHTML=t;
}
```

clearInterval() 方法用于停止 setInterval() 方法执行的函数代码。

代码：

```html
<p id="demo"></p>
<button onclick="myStopFunction()">Stop time</button>

<script>
var myVar=setInterval(function(){myTimer()},1000);
function myTimer()
{
var d=new Date();
var t=d.toLocaleTimeString();
document.getElementById("demo").innerHTML=t;
}
function myStopFunction()
{
clearInterval(myVar);
}
</script>
```

**setTimeout()**  延迟执行函数

延迟执行指定的函数，只能执行一次。

window.setTimeout("*javascript 函数*",*毫秒数*);

第一个参数是含有 JavaScript 语句的字符串。这个语句可能诸如 "alert('5 seconds!')"，或者对函数的调用，诸如 alertMsg()"。

第二个参数指示从当前起多少毫秒后执行第一个参数。

提示：1000 毫秒等于一秒。

等待3秒，然后弹出 "Hello":

```javascript
setTimeout(function(){alert("Hello")},3000);
```

clearTimeout() 方法用于停止执行setTimeout()方法的函数代码。

```javascript
var myVar;

function myFunction()
{
myVar=setTimeout(function(){alert("Hello")},3000);
}

function myStopFunction()
{
clearTimeout(myVar);
}
```



使用setTimeout实现周期执行

```javascript
<script type="text/javascript">
			
			var num=0;
			var id=0;
			function show(){
				document.write(id+"好好学习<br/>");
				num++;
				if(num!=10){
					id=setTimeout(show,1000);
				}
				
				
			}
			
			id=setTimeout(show,1000);
			
		</script>
```



#### 第三节 JavaScript的DOM

JavaScript主要包括三部分内容:ECMAScript、DOM、BOM

##### 3.1 DOM概述

通过 HTML DOM,使用 JavaScript访问 HTML 文档的所有元素。

当网页被加载时，浏览器会创建页面的文档对象模型（Document Object Model）。

**HTML DOM** 模型被构造为**对象**的树：

![img9](img\img10.png)

浏览器加载 HTML 时,会将所有的 HTMl 标签封装成对象(标签对象),称为节点(Node),并悬挂在树状结构中.

上图可知，**在HTML当中，一切都是节点**：（非常重要）

- **元素节点**：HMTL标签。
- **文本节点**：标签中的文字（比如标签之间的空格、换行）
- **属性节点**：标签的属性。

各节点关系体现为:父子关系\兄弟关系

通过可编程的对象模型，JavaScript 获得了足够的能力来创建动态的 HTML。

- JavaScript 能够改变页面中的所有 HTML 元素。
- JavaScript 能够改变页面中的所有 HTML 属性。
- JavaScript 能够改变页面中的所有 CSS 样式。
- JavaScript 能够对页面中的所有事件做出反应。



3.2DOM 的节点属性

​	在文档对象模型 (DOM) 中，每个节点都是一个对象。DOM 节点有三个重要的属性 ：

> 1. nodeName : 节点的名称
>
>    ```javascript
>    nodeName 属性: 节点的名称，是只读的。
>    
>    1. 元素节点的 nodeName 与标签名相同
>    	document.write(document.body.nodeName)
>    2. 属性节点的 nodeName 是属性的名称
>    	document.write(document.body.attributes[0].nodeName)
>    3. 文本节点的 nodeName 永远是 #text
>    	document.write(document.getElementById("demo").childNodes[0].nodeName)	
>    ```
>
> 2. nodeValue ：节点的值
>
>    ```javascript
>    nodeValue 属性：节点的值
>    
>    1. 元素节点的 nodeValue 是 undefined 或 null
>    		document.write(document.head.nodeValue)
>    2. 文本节点的 nodeValue 是文本自身
>    		document.write(document.getElementById("demo").childNodes[0].nodeValue)
>    3. 属性节点的 nodeValue 是属性的值
>    		document.write(document.getElementById("demo").attributes[0].nodeValue)
>    ```
>
>    
>
> 3. nodeType ：节点的类型
>
>    ```
>    nodeType 属性: 节点的类型，是只读的。以下常用的几种结点类型:
>    
>    元素类型    节点类型
>      元素          1
>      属性          2
>      文本          3
>      注释          8
>      文档          9
>    ```



##### 3.2常用节点属性和方法

###### 获取父节点

调用者就是节点。一个节点只有一个父节点，调用方式就是

```JavaScript
    节点.parentNode
var parentNode = document.body.parentNode;
获得 body 的父元素 
```

###### 获取所有的子节点

（1）**childNodes**：标准属性。返回的是指定元素的**子节点**的集合（包括元素节点、所有属性、文本节点）。是W3C的亲儿子。

- 火狐谷歌等高版本会把换行也看做是子节点。（了解）

用法：

```JavaScript
子节点数组 = 父节点.childNodes;   //获取所有节点。
var c=document.body.childNodes;
此处 c 代表的就是 body 所有的子节点的集合
```

###### 获得元素属性的集合

```javascript
节点.attributes
var i = document.body.attributes.length;
```

###### 应用案例:

遍历 body 下所有的节点

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>循环遍历body下节点</title>
</head>
<body>
<p id="demo">单击按钮获取body元素子节点的节点名称。</p>
<button onclick="myFunction()">点我</button>
<script type="text/javascript">
function myFunction(){
	var txt="";
	var c=document.body.childNodes;
	for (i=0; i<c.length; i++){
		txt=txt + c[i].nodeName + "<br>";
	};
	document.write(txt)
}
</script>
<p><strong>注意:</strong> 空格元素是文本，文本是节点</p>

</body>
</html>
```



#### 第四节 DOM 元素的操作

##### 4.1 获取HTML元素

通常，通过 JavaScript，您需要操作 HTML 元素。

为了做到这件事情，您必须首先找到该元素。有三种方法来做这件事：

- 通过 id 找到 HTML 元素

- 通过标签名找到 HTML 元素

- 通过类名找到HTML 元素


###### 4.1.1 id找到HTML元素

在 DOM 中查找 HTML 元素的最简单的方法，是通过使用元素的 id。

本例查找 id="intro" 元素：

```javascript
var x=document.getElementById("intro");
```

如果找到该元素，则该方法将以对象（在 x 中）的形式返回该元素。

如果未找到该元素，则 x 将包含 null。

###### 4.1.2 标签名找到 HTML 元素

本例查找 id="main" 的元素，然后查找 id="main" 元素中的所有 <p> 元素：

```javascript
var x=document.getElementById("main");
var y=x.getElementsByTagName("p");
```

###### 4.1.3 类名找到HTML 元素

本例通过 getElementsByClassName 函数来查找 class="intro" 的元素：

```javascript
var x=document.getElementsByClassName("intro");
```

##### 4.2 修改HTML

HTML DOM 允许 JavaScript 改变 HTML 元素的内容。

###### 4.2.1 改变HTML文本

修改 HTML 文本的最简单的方法时使用 **innerHTML **属性。

如需改变 HTML 元素的内容，请使用这个语法：

document.getElementById(*id*).innerHTML="*abcd*";

或

document.getElementById(*id*).innerText="xxxx";

> innerHTML 和 innerText 不同的是: innerText 无论写什么都会被当成文本进行打印，innerHTML 会解析内容，如果有页面标签，就会进行编译。

本例改变了 <p>元素的文本：

```html
<html>
<body>
<p id="p1">Hello World!</p>
<script>
document.getElementById("p1").innerHTML="abcd";
</script>
</body>
</html>
```

运行结果：

![img9](img\img12.png)

###### 4.2.2 改变HTML属性

如需改变 HTML 元素的属性，请使用这个语法：

document.getElementById(*id*).*attribute=新属性值*

或者

document.getElementById("image").setAttribute("属性","值");

本例改变了 <img> 元素的 src 属性：

```html
<!DOCTYPE html>
<html>
<body>
<img id="image" src="1.gif">
<script>
document.getElementById("image").src="2.jpg";
</script>
</body>
</html>
```



##### 4.3 修改CSS样式

改变HTML的样式：

如需改变 HTML 元素的样式，请使用这个语法：

document.getElementById(*id*).style.*property*=*新样式*

下面的例子会改变 <p> 元素的样式：

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<p id="p1">Hello World!</p>
<p id="p2">Hello World!</p>
<script>
document.getElementById("p2").style.color="blue";
document.getElementById("p2").style.fontFamily="Arial";
document.getElementById("p2").style.fontSize="larger";
</script>
<p>以上段落通过脚本修改。</p>
</body>
</html>
```

运行结果为：

![img9](img\img13.png)



##### 4.4 元素标签的 CRUD

###### 4.4.1 创建新元素

如需向 HTML DOM 添加新元素，您必须首先创建该元素（元素节点），然后向一个已存在的元素追加该元素。

```html
<div id="div1">
<p id="p1">这是一个段落。</p>
<p id="p2">这是另一个段落。</p>
</div>

<script>
var para=document.createElement("p");
var node=document.createTextNode("这是一个新段落。");
para.appendChild(node);

var element=document.getElementById("div1");
element.appendChild(para);
</script>
```

例子说明：

这段代码创建新的<p> 元素：

var para=document.createElement("p");

如需向 <p> 元素添加文本，您必须首先创建文本节点。这段代码创建了一个文本节点：

var node=document.createTextNode("这是一个新段落。");

然后必须向 <p> 元素追加这个文本节点：

**para.appendChild(node);**

最后必须向一个已有的元素追加这个新元素。

这段代码找到一个已有的元素：

var element=document.getElementById("div1");

以下代码在已存在的元素后添加新元素：

element.appendChild(para);



**insertBefore(元素 1,元素 2)**

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body onload="" id="h">
	<div id="div1">
		<p id="p1">这是一个段落。</p>
		<p id="p2">这是另一个段落。</p>
	</div>
	
	<script>
      //在 div1 里面,插入 para 到p1 的前面
	var para=document.createElement("p");
	var node=document.createTextNode("这是一个新段落。");
	para.appendChild(node);
	var p1 = document.getElementById("p1");
	var element=document.getElementById("div1");
	element.insertBefore(para,p1);
	</script>
	</body>
</html>

```



###### 4.4.2 删除已有的 HTML 元素

**remove()**

以下代码演示了如何删除元素：

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body onload="" id="h">
	<div id="div1">
		<p id="p1">这是一个段落。</p>
		<p id="p2">这是另一个段落。</p>
	</div>
	<button type="button" id="btn" onclick="deletes()"> 删除 p1</button>
	<script>
	function deletes(){
		document.getElementById("p1").remove();
	}
	</script>
	</body>
</html>
```

**removeChild()**

以下代码演示了如何删除子元素：

```html
<div id="div1">
<p id="p1">这是一个段落。</p>
<p id="p2">这是另一个段落。</p>
</div>
<script>
var parent=document.getElementById("div1");
var child=document.getElementById("p1");
parent.removeChild(child);
</script>
```

例子说明：

这个 HTML 文档含有拥有两个子节点（两个 <p> 元素）的 <div> 元素：

```html
<div id="div1">

<p id="p1">这是一个段落。</p>

<p id="p2">这是另一个段落。</p>

</div>
```

找到 id="div1" 的元素：

var parent=document.getElementById("div1");

找到 id="p1" 的 <p> 元素：

var child=document.getElementById("p1");

从父元素中删除子元素：

parent.removeChild(child);

如果能够在不引用父元素的情况下删除某个元素，就太好了。不过很遗憾。DOM 需要清楚您需要删除的元素，以及它的父元素。

这是常用的解决方案：找到您希望删除的子元素，然后使用其 parentNode 属性来找到父元素：

var child=document.getElementById("p1");
child.parentNode.removeChild(child);



###### 4.4.3 替换元素

**replaceChild(元素 1,元素 2);**

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body onload="" id="h">
	<div id="div1">
		<p id="p1">这是一个段落。</p>
		<p id="p2">这是另一个段落。</p>
	</div>
	<button type="button" id="btn" onclick="deletes()"> 删除 p1</button>
	<script>
	
	function deletes(){
		var para=document.createElement("p");
		var node=document.createTextNode("这是一个新段落。");
		para.appendChild(node);
		var p1 = document.getElementById("p1");
		var element=document.getElementById("div1");
		element.replaceChild(para,p1);
	}
	</script>
	</body>
</html>

```

###### 购物车案例:

shopping.html 页面

```javascript
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>当当购物车页面</title>
    <link type="text/css" rel="stylesheet" href="css/cartStyle.css" />
</head>
<body>

<div class="content">
    <div class="logo">
        <img src="images/dd_logo.jpg"><span onclick="close_plan();">关闭</span>
    </div>
    <div class="cartList">
        <ul>
            <li>¥<input type="text" name="price" value="21.90"></li>
            <li><input type="button" name="minus" value="-" onclick="minus(0);"><input type="text" name="amount" value="1"><input type="button" name="plus" value="+" onclick="plus(0);"></li>
            <li id="price0">¥21.90</li>
            <li><p  onclick="collection();">移入收藏</p><p onclick="del();">删除</p></li>
        </ul>
        <ul>
            <li>¥<input type="text" name="price" value="24.00"></li>
            <li><input type="button" name="minus" value="-" onclick="minus(1);"><input type="text" name="amount" value="1"><input type="button" name="plus" value="+" onclick="plus(1);"></li>
            <li id="price1">¥24.00</li>
            <li><p  onclick="collection();">移入收藏</p><p onclick="del();">删除</p></li>
        </ul>
        <ol>
            <li id="totalPrice">&nbsp;</li>
            <li><span">结 算</span></li>
        </ol>
    </div>
</div>
<script type="text/javascript" src="js/shopping.js"></script>
</body>
</html>
```

shopping.css 样式

```css
body,ul,li,div,p,h1,h2,ol{margin: 0;padding: 0;}
ul,li,ol{list-style: none;}
.content{width: 810px; margin: 0 auto;  font-family: "微软雅黑";}
.logo{margin: 10px 0;}
.logo span{
    display: inline-block;
    float: right;
    width: 60px;
    height: 30px;
    line-height: 30px;
    font-size: 14px;
    background: #ff0000;
    color: #ffffff;
    text-align: center;
    border-radius: 10px;
    margin-top: 5px;
    margin-right: 10px;
    cursor: pointer;
    font-weight: bold;
}
.cartList{
    background: url("../images/shoppingBg.jpg") no-repeat;
    height: 414px;
    overflow: hidden;
}
.cartList ul{
    float: right;
    width: 450px;
}
.cartList ul:nth-of-type(1){
    margin-top: 125px;
}
.cartList ul:nth-of-type(2){
    margin-top:70px;
}
.cartList ul li{
    font-family: "微软雅黑";
    font-size: 12px;
    color: #666666;
    text-align: center;
    line-height: 25px;
    float: left;
}
.cartList ul li input[name="price"]{
    border: none;
    background: transparent;
    width: 45px;
    text-align: center;
}
.cartList ul li input[name="amount"]{
    width: 45px;
    text-align: center;
    border: 1px solid #999999;
    border-left: none;
    border-right: none;
    height: 21px;
}
.cartList ul li input[name="minus"],.cartList ul li input[name="plus"]{
    height: 25px;
    border: 1px #999999 solid;
    width: 25px;
    text-align: center;
}
.cartList ul li:nth-of-type(1){width: 130px;}
.cartList ul li:nth-of-type(2){width: 100px;}
.cartList ul li:nth-of-type(3){width: 130px;}
.cartList ul li p{cursor: pointer;}
.cartList ol{
    float: right;
    clear: both;
    margin-top: 60px;
}
.cartList ol li{
    float: left;
}
.cartList ol li:nth-of-type(1){
    color: #ff0000;
    width: 120px;
}
.cartList ol li span{display: inline-block;
    float: right;
    width: 80px;
    height: 35px;
    line-height: 35px;
    font-size: 14px;
    font-family: "微软雅黑";
    background: #ff0000;
    color: #ffffff;
    text-align: center;
    margin-top: 5px;
    margin-right: 15px;
    cursor: pointer;
    font-weight: bold;}
```

shopping.js

```javascript

/*关闭窗口*/
function close_plan(){
    window.close();
}
function collection(){
    var flag=confirm("移入收藏后，将不再购物车显示，是否继续操作?");
    if(flag==true){
        alert("移入收藏成功!");
    }
}
function del(){
    var flag=confirm("您确定要删除商品吗?");
    if(flag==true){
        alert("删除成功!");
    }
}
function accounts(){
    var flag=confirm("您本次购买的商品信息如下：\n\n商品名称：白岩松：白说、岛上书店；\n商品数量：2件；\n商品总计：46.00；\n运费：0元；\n\n请确认以上信息是否有误！！！");
    if(flag){
        alert("您的订单已提交");
    }
}

function minus(num){
    var prices=document.getElementsByName("price")[num].value;
    var count=parseInt(document.getElementsByName("amount")[num].value)-1;
    if(count<1){
        alert("不能再减了，再减就没有啦！");
    }
    else{
        document.getElementsByName("amount")[num].value=count;
        var totals=parseFloat(prices*count);
        document.getElementById("price"+num).innerHTML="¥" +totals;
        total();
    }
}
function plus(num){
    var prices=document.getElementsByName("price")[num].value;
    var count=parseInt(document.getElementsByName("amount")[num].value)+1;
    document.getElementsByName("amount")[num].value=count;
    var totals=parseFloat(prices*count);
    document.getElementById("price"+num).innerHTML="¥" +totals;
    total();
}
function total(){
    var prices=document.getElementsByName("price");
    var count=document.getElementsByName("amount");
    var sum=0;
    for(var i=0; i<prices.length;i++){
       sum+=prices[i].value*count[i].value;
    }
    document.getElementById("totalPrice").innerHTML="¥" +sum;
}
total();
```



##### 4.5 DOM事件

HTML DOM 允许我们通过触发事件来执行代码。

比如以下事件：

- 元素被点击。

- 页面加载完成。

- 输入框被修改。

在接下来的章节，你会学到更多关于事件的知识。

本例改变了 id="id1" 的 HTML 元素的样式，当用户点击按钮时：

```html
<!DOCTYPE html>
<html>
<body>

<h1 id="id1">myH1</h1>
<button type="button" 
onclick="document.getElementById('id1').style.color='red'">
button</button>

</body>
</html>
```

点击前：

![img9](img\img14.png)

点击后：

![img9](img\img15.png)



在本例中，当用户在 <h1> 元素上点击时，会改变其内容：

```html
<!DOCTYPE html>
<html>
<body>
<h1 onclick="this.innerHTML='改变内容!'">点击文本!</h1>
</body>
</html>
```

本例从事件处理器调用一个函数：

```html
<!DOCTYPE html>
<html>
<head>
<script>
function changetext(id)
{
    id.innerHTML="改变内容!";
}
</script>
</head>
<body>
<h1 onclick="changetext(this)">点击文本!</h1>
</body>
</html>
```



HTML DOM 允许您使用 JavaScript 来向 HTML 元素分配事件：

```html
<script>
document.getElementById("myBtn").onclick=function(){
    document.getElementById("myhead2").style.color='blue';
};
</script>
```

onchange 事件常结合对输入字段的验证来使用。

下面是一个如何使用 onchange 的例子。当用户改变输入字段的内容时，会调用 upperCase() 函数。

```html
<input type="text" id="fname" onchange="upperCase()">
```

onmouseover 和 onmouseout 事件可用于在用户的鼠标移至 HTML 元素上方或移出元素时触发函数。

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<div onmouseover="mOver(this)" onmouseout="mOut(this)" style="background-color:#D94A38;width:120px;height:20px;padding:40px;">Mouse Over Me</div>
<script>
function mOver(obj){
	obj.innerHTML="Thank You"
}
function mOut(obj){
	obj.innerHTML="Mouse Over Me"
}
</script>

</body>
</html>
```



#####  4.6EventListener

addEventListener() 方法

在用户点击按钮时触发监听事件：

```html
document.getElementById("myBtn").addEventListener("click", displayDate);
```

addEventListener() 方法用于向指定元素添加事件句柄。

addEventListener() 方法添加的事件句柄不会覆盖已存在的事件句柄。

你可以向一个元素添加多个事件句柄。

你可以向同个元素添加多个同类型的事件句柄，如：两个 "click" 事件。

你可以向任何 DOM 对象添加事件监听，不仅仅是 HTML 元素。如： window 对象。

addEventListener() 方法可以更简单的控制事件（冒泡与捕获）。

当你使用 addEventListener() 方法时, JavaScript 从 HTML 标记中分离开来，可读性更强， 在没有控制HTML标记时也可以添加事件监听。

你可以使用 removeEventListener() 方法来移除事件的监听。

语法：

```java
element.addEventListener(event, function, useCapture);
```

第一个参数是事件的类型 (如 "click" 或 "mousedown").  把on去掉

第二个参数是事件触发后调用的函数。

第三个参数是个布尔值用于描述事件是冒泡还是捕获。该参数是可选的。

*注意:不要使用 "on" 前缀。 例如，使用 "click" ,而不是使用 "onclick"。*

当用户点击元素时弹出 "Hello World!" :

```html
element.addEventListener("click", myFunction);

function myFunction() {
    alert ("Hello World!");
}
```

addEventListener() 方法允许向同个元素添加多个事件，且不会覆盖已存在的事件：

```html
element.addEventListener("click", myFunction);
element.addEventListener("click", mySecondFunction);
```

事件冒泡或事件捕获？

事件传递有两种方式：冒泡与捕获。

事件传递定义了元素事件触发的顺序。 如果你将 <p> 元素插入到 <div> 元素中，用户点击 <p> 元素, 哪个元素的 "click" 事件先被触发呢？

在 *冒泡 *中，内部元素的事件会先被触发，然后再触发外部元素，即： <p> 元素的点击事件先触发，然后会触发 <div> 元素的点击事件。

在 *捕获 *中，外部元素的事件会先被触发，然后才会触发内部元素的事件，即： <div> 元素的点击事件先触发 ，然后再触发 <p> 元素的点击事件。

addEventListener() 方法可以指定 "useCapture" 参数来设置传递类型：

```html
addEventListener(event, function, useCapture);
```

默认值为 false, 即冒泡传递，当值为 true 时, 事件使用捕获传递。

```html
document.getElementById("myDiv").addEventListener("click", myFunction, true);
```

removeEventListener() 方法移除由 addEventListener() 方法添加的事件句柄:

```html
element.removeEventListener("mousemove", myFunction);
```



#### 总结



#### 作业题

```
1.写一个点击“大”、“中”、“小”三个超链，实现页面部分文字随之分别为：18，14，12号字体的切换效果。

2.加入适当的css美化页面 如效果图所示
	使用JavaScript为网页添加动态效果并实现与用户的交互功能
<div id="txt">
    <h5>JavaScript为网页添加动态效果并实现与用户交互的功能。</h5>
    <p>111111111111111111111111111111111</p>
    <p>222222222222222222222222222222222</p>
    <p>333333333333333333333333333333333</p>
</div>
<form>
    <!--当点击相应按钮，执行相应操作，为按钮添加相应事件-->
    <input type="button" value="改变颜色" onClick="changecolor()" >
    <input type="button" value="改变宽高" onClick="changewidth()">
    <input type="button" value="隐藏内容" onClick="hidetext()">
    <input type="button" value="显示内容" onClick="showtext()">
    <input type="button" value="取消设置" onClick="queXiao()">
</form>

```

#### 面试题

```
1、实现函数 isInteger(x) 来判断 x 是否是整数
2、写一个少于 80 字符的函数，判断一个字符串是不是回文字符串
3、for (var i = 0; i < 5; i++) {
 setTimeout(function() {
  console.log(new Date, i);
 }, 1000);
}
 
console.log(new Date, i);
输出的结果是什么？
```