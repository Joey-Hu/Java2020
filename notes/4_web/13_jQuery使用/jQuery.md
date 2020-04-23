### jQuery



#### 今日内容

```
1、什么是jQuery
2、jQuery的使用
3、jQuery的语法
4、jQuery常用事件
5、jQuery的效果
6、jQuery对HTML的操作
7、jQuery的遍历操作
```

#### 第一节 jQuery概述

##### 1.1 jQuery简介

jQuery是一个快速、简洁的JavaScript框架，是继Prototype之后又一个优秀的JavaScript代码库（*或JavaScript框架*）。jQuery设计的宗旨是“Write Less，Do More”，即倡导写更少的代码，做更多的事情。它封装JavaScript常用的功能代码，提供一种简便的JavaScript设计模式，优化HTML文档操作、事件处理、动画设计和Ajax交互。

jQuery的核心特性可以总结为：具有独特的链式语法和短小清晰的多功能接口；具有高效灵活的css选择器，并且可对CSS选择器进行扩展；拥有便捷的插件扩展机制和丰富的插件。jQuery兼容各种主流浏览器，如IE 6.0+、FF 1.5+、Safari 2.0+、Opera 9.0+等。



```
目前jQuery有三个大版本：
1.x：兼容ie678,使用最为广泛的，官方只做BUG维护，功能不再新增。因此一般项目来说，使用1.x版本就可以了，最终版本：1.12.4 (2016年5月20日)
2.x：2013年2.0版本发布，不兼容ie678，很少有人使用，官方只做BUG维护，功能不再新增。如果不考虑兼容低版本的浏览器可以使用2.x，最终版本：2.2.4 (2016年5月20日)
3.x：不兼容ie678，只支持最新的浏览器。除非特殊要求，一般不会使用3.x版本的，很多老的jQuery插件不支持这个版本。目前该版本是官方主要更新维护的版本。最新版本：3.4.1（2019年5月1日）
1.X大版本下，细分版本非常多，各个版本的函数都会有一定的差异。网上看到的很多教程大多是1.x版本的。jquery官方手册：http://api.jquery.com/

参考文档:https://www.jquery123.com/ jQuery中文网、菜鸟教程
```



##### 1.2 什么是jQuery

jQuery是一个JavaScript函数库。
jQuery是一个轻量级的"写的少，做的多"的JavaScript库。
jQuery库包含以下功能：

- HTML 元素选取
- HTML 元素操作
- CSS 操作
- HTML 事件函数
- JavaScript 特效和动画
- HTML DOM 遍历和修改
- AJAX
- Utilities
##### 1.3 为什么要用jQuery
目前网络上有大量开源的 JS 框架, 但是 jQuery 是目前最流行的 JS 框架，而且提供了大量的扩展。
很多大公司都在使用 jQuery， 例如:

- Google
- Microsoft
- IBM
- Netflix
#### 第二节 jQuery安装
##### 2.1 网页中添加 jQuery

可以通过多种方法在网页中添加 jQuery。 您可以使用以下方法：

- 从 [jquery.com](http://jquery.com/download/) 下载 jQuery 库

- 从 CDN 中载入 jQuery, 如从 Google 中加载 jQuery

  

  有两个版本的 jQuery 可供下载：

- Production version - 用于实际的网站中，已被精简和压缩。

- Development version - 用于测试和开发（未压缩，是可读的代码）
  以上两个版本都可以从 [jquery.com](http://jquery.com/download/) 中下载。
  jQuery 库是一个 JavaScript 文件，您可以使用 HTML 的 <script> 标签引用它：
```html
<head>
	<script src="jquery-1.10.2.min.js"></script>
</head>
```
当然你也可以使用其它网站的CDN：

##### 2.2 百度 CDN

```html
<head>
  <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
  </script>
</head>
```
##### 2.3 新浪 CDN

```html
<head>
<script src="http://lib.sinaapp.com/js/jquery/2.0.2/jquery-2.0.2.min.js">
</script>
</head>
```
##### 2.4 Google CDN

```html
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
</head>
```
##### 2.5 Microsoft CDN

```html
<head>
<script src="http://ajax.htmlnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js">
</script>
</head>
```
#### 第三节 jQuery语法
jQuery 语法是通过$()核心函数选取 HTML 元素，并对选取的元素执行某些操作。

**使用语法1： $(匿名函数)**

表示页面dom加载完毕，就执行，**比onload事件要早，并行可以写多个**。	

   $(匿名函数)  

​	和

   $(document).ready(匿名函数) 一样

**使用语法2： $(selector).action()**

- 美元符号定义 jQuery

- 选择符（selector）"查询"和"查找" HTML 元素

- jQuery 的 action() 执行对元素的操作

  

  举例:

  $(this).hide() - 隐藏当前元素

  $("p").hide() - 隐藏所有 <p> 元素

  $("p.test").hide() - 隐藏所有 class="test" 的 <p> 元素

  $("#test").hide() - 隐藏所有 id="test" 的元素
  


##### 3.1 jQuery选择器

###### 3.1.1 元素选择器

jQuery 元素选择器基于元素名选取元素。
在页面中选取所有 <p> 元素:

```javascript
$(document).ready(function(){
  $("#btn").click(function(){
    $("p").hide();
  });
});
```
###### 3.1.2 id选择器

jQuery #id 选择器通过 HTML 元素的 id 属性选取指定的元素。
页面中元素的 id 应该是唯一的，所以您要在页面中选取唯一的元素需要通过 #id 选择器。
通过 id 选取元素语法如下：

```javascript
$(document).ready(function(){
  $("#btn").click(function(){
    $("#test").hide();
  });
});
```

###### 3.1.3 class选择器

jQuery 类选择器可以通过指定的 class 查找元素。
语法如下：

```javascript
$(document).ready(function(){
  $("#btn").click(function(){
    $(".test").hide();
  });
});
```

###### 3.1.4 层级选择器

后代选择器 

```javascript
$("#firstBox li").css("color","red");
```

父子选择器    >

```javascript
$("#firstBox>li").css("color","blue");
```

同辈选择器  +

```javascript
$(span+li).css("color","yellow");
```

后续同辈 ~

```javascript
$("#firstBox~li").css("color","green");
```

###### 3.1.5过滤选择器

```javascript
//第一个 li 标签
$("#firstBox li:first").css("color","red");
//除了第一个 li 标签以外
$("#firstBox li:not(:first)").css("color","red");
//下标大于 1 的
$("#firstBox li:gt(1)").css("color","red");
```

###### 3.1.6属性选择器

```javascript
//有 ID 属性的 li
$("li[id]").css("color","red");
//id为 xxx 的 li
$("li[id='xxx']").css("color","red");
//id 是 y 为结尾的
$("li[id$='d']").css("color","red");
//id 是 s 为开头的
$("li[id^='s']").css("color","red");

```

​	

##### 3.2 jQuery事件

###### 3.2.1 什么是事件？

页面访问者的响应叫做事件。
事件处理程序指的是当 HTML 中发生某些事件时所调用的方法。
实例：

- 在元素上移动鼠标。

- 选取单选按钮

- 点击元素

  

  常见 DOM 事件：
| 鼠标事件      | 键盘事件     | 表单事件   | 文档/窗口事件 |
| --------- | -------- | ------ | ------- |
| click     | keypress | submit | load    |
| dblclick  | keydown  | change | resize  |
| mouseover | keyup    | focus  | scroll  |
| mouseout  |          | blur   | unload  |
###### 3.2.2 jQuery 事件方法语法

在 jQuery 中，大多数 DOM 事件都有一个等效的 jQuery 方法。
页面中指定一个点击事件：

```javascript
$("p").click();
```
下一步是定义触发事件。您可以通过一个事件函数实现：
```javascript
$("p").click(function(){
    // 动作触发后执行的代码!!
});
```
也就是说，不传参数是点击，传参数是设置事件。

***click()*** 
当按钮点击事件被触发时会调用一个函数。
该函数在用户点击 HTML 元素时执行。
在下面的实例中，当点击事件在某个 <p> 元素上触发时，隐藏当前的 <p> 元素：

```javascript
$("p").click(function(){
  $(this).hide();
});
```
***dblclick()***
当双击元素时，会发生 dblclick 事件。
dblclick() 方法触发 dblclick 事件，或规定当发生 dblclick 事件时运行的函数：

```javascript
$("p").dblclick(function(){
  $(this).hide();
});
```
***mouseover()***
当鼠标指针穿过元素时，会发生 mouseover 事件。
mouseover() 方法触发 mouseover 事件，或规定当发生 mouseover 事件时运行的函数：

```javascript
$("#p1").mouseover(function(){
    alert('您的鼠标移到了 id="p1" 的元素上!');
});
```
***mouseout()***
当鼠标指针离开元素时，会发生 mouseout 事件。
mouseout() 方法触发 mouseout 事件，或规定当发生 mouseout 事件时运行的函数：

```javascript
$("#p1").mouseout(function(){
    alert("再见，您的鼠标离开了该段落。");
});
```
***hover()***
hover()方法用于模拟光标悬停事件。
当鼠标移动到元素上时，会触发指定的第一个函数(mouseover);当鼠标移出这个元素时，会触发指定的第二个函数(mouseout)。

```javascript
$("#p1").hover(
    function(){
        alert("你进入了 p1!");
    },
    function(){
        alert("拜拜! 现在你离开了 p1!");
    }
);
```

**mousedown()**
当鼠标指针移动到元素上方，并按下鼠标按键时，会发生 mousedown 事件。
mousedown() 方法触发 mousedown 事件，或规定当发生 mousedown 事件时运行的函数：

```javascript
$("#p1").mousedown(function(){
    alert("鼠标在该段落上按下！");
});
```
***mouseup()***
当在元素上松开鼠标按钮时，会发生 mouseup 事件。
mouseup() 方法触发 mouseup 事件，或规定当发生 mouseup 事件时运行的函数：

```javascript
$("#p1").mouseup(function(){
    alert("鼠标在段落上松开。");
});
```
***focus()***
当元素获得焦点时，发生 focus 事件。
当通过鼠标点击选中元素或通过 tab 键定位到元素时，该元素就会获得焦点。
focus() 方法触发 focus 事件，或规定当发生 focus 事件时运行的函数：

```javascript
$("input").focus(function(){
  $(this).css("background-color","#cccccc");
});
```
***blur()***
当元素失去焦点时，发生 blur 事件。
blur() 方法触发 blur 事件，或规定当发生 blur 事件时运行的函数：

```javascript
$("input").blur(function(){
  $(this).css("background-color","#ffffff");
});
```


#### 第四节 jQuery 效果

##### 4.1 隐藏显示

***hide()***
您可以使用 hide() 将元素隐藏

```javascript
$("#hide").click(function(){
  $("p").hide();
});
```
***show()***
您可以使用show()将元素显示

```javascript
$("#show").click(function(){
  $("p").show();
});
```
**toggle ()**
通过 jQuery，您可以使用 toggle() 方法来切换 hide() 和 show() 方法。
显示被隐藏的元素，并隐藏已显示的元素：

```javascript
$("button").click(function(){
  $("p").toggle();
});
```
事实上，这三种方法都是有两个参数的：
```javascript
$(selector).hide(speed,callback);
$(selector).show(speed,callback);
$(selector).toggle(speed,callback);
```
可选的 speed 参数规定隐藏/显示的速度，可以取以下值："slow"、"fast" 或毫秒。
可选的 callback 参数是隐藏或显示完成后所执行的函数名称。

##### 4.2 淡入淡出
通过 jQuery，您可以实现元素的淡入淡出效果。
jQuery 拥有下面四种 fade 方法：

- fadeIn()
- fadeOut()
- fadeToggle()
- fadeTo()

***jQuery fadeIn() 方法***
jQuery fadeIn() 用于淡入已隐藏的元素。

```javascript
$(selector).fadeIn(speed,callback);
```
可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。.
可选的 callback 参数是 fading 完成后所执行的函数名称。
下面的例子演示了带有不同参数的 fadeIn() 方法：

```javascript
$("button").click(function(){
  $("#div1").fadeIn();
  $("#div2").fadeIn("slow");
  $("#div3").fadeIn(3000);
});
```
***jQuery fadeOut() 方法***
jQuery fadeOut() 方法用于淡出可见元素。
```javascript
$(selector).fadeOut(speed,callback);
```
可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。
可选的 callback 参数是 fading 完成后所执行的函数名称。
下面的例子演示了带有不同参数的 fadeOut() 方法：

```javascript
$("button").click(function(){
  $("#div1").fadeOut();
  $("#div2").fadeOut("slow");
  $("#div3").fadeOut(3000);
});
```
***jQuery fadeToggle() 方法***
jQuery fadeToggle() 方法可以在 fadeIn() 与 fadeOut() 方法之间进行切换。
如果元素已淡出，则 fadeToggle() 会向元素添加淡入效果。
如果元素已淡入，则 fadeToggle() 会向元素添加淡出效果。

```javascript
$(selector).fadeToggle(speed,callback);
```
可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。
可选的 callback 参数是 fading 完成后所执行的函数名称。
下面的例子演示了带有不同参数的 fadeToggle() 方法：
```javascript
$("button").click(function(){
  $("#div1").fadeToggle();
  $("#div2").fadeToggle("slow");
  $("#div3").fadeToggle(3000);
});
```
***jQuery fadeTo() 方法***
jQuery fadeTo() 方法允许渐变为给定的透明度（值介于 0 与 1 之间）。0透明  1不透明

```javascript
$(selector).fadeTo(speed,opacity,callback);
```
必需的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。
fadeTo() 方法中必需的 opacity 参数将淡入淡出效果设置为给定的不透明度（值介于 0 与 1 之间）。
可选的 callback 参数是该函数完成后所执行的函数名称。
下面的例子演示了带有不同参数的 fadeTo() 方法：
```javascript
$("button").click(function(){
  $("#div1").fadeTo("slow",0.15);
  $("#div2").fadeTo("slow",0.4);
  $("#div3").fadeTo("slow",0.7);
});
```
##### 4.3 滑动

通过 jQuery，您可以在元素上创建滑动效果。
jQuery 拥有以下滑动方法：
- slideDown()
- slideUp()
- slideToggle()

***slideDown() 方法***

jQuery slideDown() 方法用于向下滑动元素。

```javascript
$(selector).slideDown(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是滑动完成后所执行的函数名称。

下面的例子演示了 slideDown() 方法：

```javascript
$("#flip").click(function(){
  $("#panel").slideDown();
});
```

***slideUp() 方法***

jQuery slideUp() 方法用于向上滑动元素。

```javascript
$(selector).slideUp(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是滑动完成后所执行的函数名称。

下面的例子演示了 slideUp() 方法：

```javascript
$("#flip").click(function(){
  $("#panel").slideUp();
});
```

```javascript
<script type="text/javascript">
$(document).ready(function(){
  $("#btn1").click(function(){
  $("p").slideUp(1000);
  });
  $("#btn2").click(function(){
  $("p").slideDown(1000);
  });
});
</script>
```

***slideToggle() 方法***

jQuery slideToggle() 方法可以在 slideDown() 与 slideUp() 方法之间进行切换。

如果元素向下滑动，则 slideToggle() 可向上滑动它们。

如果元素向上滑动，则 slideToggle() 可向下滑动它们。

```javascript
$(selector).slideToggle(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是滑动完成后所执行的函数名称。

下面的例子演示了 slideToggle() 方法：

```javascript
$("#flip").click(function(){
  $("#panel").slideToggle();
});
```

##### 4.4 链式编程

直到现在，我们都是一次写一条 jQuery 语句（一条接着另一条）。

不过，有一种名为链接（chaining）的技术，允许我们在相同的元素上运行多条 jQuery 命令，一条接着另一条。

**提示：** 这样的话，浏览器就不必多次查找相同的元素。

如需链接一个动作，**您只需简单地把该动作追加到之前的动作上**。

下面的例子把 css()、slideUp() 和 slideDown() 链接在一起。"p1" 元素首先会变为红色，然后向上滑动，再然后向下滑动：

```javascript
$("#p1").css("color","red").slideUp(2000).slideDown(2000);
```

如果需要，我们也可以添加多个方法调用。

**提示：**当进行链接时，代码行会变得很差。不过，jQuery 语法不是很严格；您可以按照希望的格式来写，包含换行和缩进。

如下书写也可以很好地运行：

```javascript
$("#p1").css("color","red")
  .slideUp(2000)
  .slideDown(2000);
```



#### 第五节 jQuery HTML DOM操作

#### CRUD

##### 5.1 获取

jQuery 拥有可操作 HTML 元素和属性的强大方法。

***jQuery DOM 操作***

jQuery 提供一系列与 DOM 相关的方法，这使访问和操作元素和属性变得很容易。

DOM 对象和 jQuery 对象的区别

```
var box1 = document.getElementById("box");  DOM
var box2 = $("#box");   jQuery
DOM 操作的事 JavaScript 原生对象
jQuery 操作的是自己封装的对象,当中包含了原生对象
注意:只有 jQuery 对象才可以操作 JQuery 的函数
```

***获得内容 - text()、html() 以及 val()***

三个简单实用的用于 DOM 操作的 jQuery 方法：

- text() - 设置或返回所选元素的文本内容---->innerText
- html() - 设置或返回所选元素的内容（包括 HTML 标记）---innerHTML
- val() - 设置或返回表单字段的值---value属性

下面的例子演示如何通过 jQuery text() 和 html() 方法来获得内容：

```javascript
$("#btn1").click(function(){
  alert("Text: " + $("#test").text());
});
$("#btn2").click(function(){
  alert("HTML: " + $("#test").html());
});
```

下面的例子演示如何通过 jQuery val() 方法获得输入字段的值：

```javascript
$("#btn1").click(function(){
  alert("值为: " + $("#test").val());
});
```

**获取属性-attr()**\prop()

jQuery attr() 方法用于获取属性值。

下面的例子演示如何获得链接中 href 属性的值：

```javascript
$("button").click(function(){
  alert($("#a1").attr("href"));
});
```

##### 5.2 设置

***设置内容 - text()、html() 以及 val()***

我们将使用前一章中的三个相同的方法来设置内容：

- text() - 设置或返回所选元素的文本内容
- html() - 设置或返回所选元素的内容（包括 HTML 标记）
- val() - 设置或返回表单字段的值

下面的例子演示如何通过 text()、html() 以及 val() 方法来设置内容：

```javascript
$("#btn1").click(function(){
    $("#test1").text("Hello world!");
});
$("#btn2").click(function(){
    $("#test2").html("<b>Hello world!</b>");
});
$("#btn3").click(function(){
    $("#test3").val("Hello world!");
});
```



***设置属性 attr ()***

jQuery attr() 方法也用于设置/改变属性值。

下面的例子演示如何改变（设置）文本中 color属性的值：

```javascript
$("button").click(function(){
  $("#font1").attr("color","red");
});
```



##### 5.3 添加元素

**添加新的 HTML 内容**

我们将学习用于添加新内容的四个 jQuery 方法：

- append() - 在被选元素的结尾插入内容
- prepend() - 在被选元素的开头插入内容
- after() - 在被选元素之后插入内容
- before() - 在被选元素之前插入内容

**jQuery append() 方法**

jQuery append() 方法在被选元素的结尾插入内容。

```javascript
$("p").append("追加文本");
```

**jQuery prepend() 方法**

jQuery prepend() 方法在被选元素的开头插入内容。

```javascript
$("p").prepend("在开头追加文本");
```

**通过 append() 和 prepend() 方法添加若干新元素**

在上面的例子中，我们只在被选元素的开头/结尾插入文本/HTML。

不过，append() 和 prepend() 方法能够通过参数接收无限数量的新元素。可以通过 jQuery 来生成文本/HTML（就像上面的例子那样），或者通过 JavaScript 代码和 DOM 元素。

在下面的例子中，我们创建若干个新元素。这些元素可以通过 text/HTML、jQuery 或者 JavaScript/DOM 来创建。然后我们通过 append() 方法把这些新元素追加到文本中（对 prepend() 同样有效）：

```javascript
function appendText()
{
    var txt1="<p>文本。</p>";              // 使用 HTML 标签创建文本
    var txt2=$("<p></p>").text("文本。");  // 使用 jQuery 创建文本
    var txt3=document.createElement("p");
    txt3.innerHTML="文本。";               // 使用 DOM 创建文本 text with DOM
    $("body").append(txt1,txt2,txt3);        // 追加新元素
}
```

**jQuery after() 和 before() 方法**

jQuery after() 方法在被选元素之后插入内容。

jQuery before() 方法在被选元素之前插入内容。

```javascript
$("img").after("在后面添加文本");
 
$("img").before("在前面添加文本");
```

**通过 after() 和 before() 方法添加若干新元素**

after() 和 before() 方法能够通过参数接收无限数量的新元素。可以通过 text/HTML、jQuery 或者 JavaScript/DOM 来创建新元素。

在下面的例子中，我们创建若干新元素。这些元素可以通过 text/HTML、jQuery 或者 JavaScript/DOM 来创建。然后我们通过 after() 方法把这些新元素插到文本中（对 before() 同样有效）：

```javascript
function afterText()
{
    var txt1="<b>I </b>";                    // 使用 HTML 创建元素
    var txt2=$("<i></i>").text("love ");     // 使用 jQuery 创建元素
    var txt3=document.createElement("big");  // 使用 DOM 创建元素
    txt3.innerHTML="jQuery!";
    $("img").after(txt1,txt2,txt3);          // 在图片后添加文本
}
```

##### 5.4 删除元素

**删除元素/内容**

如需删除元素和内容，一般可使用以下两个 jQuery 方法：

- remove() - 删除被选元素（及其子元素）
- empty() - 从被选元素中删除子元素

**jQuery remove() 方法**

jQuery remove() 方法删除被选元素及其子元素。

```javascript
$("#div1").remove();
```

**jQuery empty() 方法**

jQuery empty() 方法删除被选元素的子元素。

```javascript
$("#div1").empty();
```

**过滤被删除的元素**

jQuery remove() 方法也可接受一个参数，允许您对被删元素进行过滤。

该参数可以是任何 jQuery 选择器的语法。

下面的例子删除 class="italic" 的所有 <p> 元素：

```javascript
$("p").remove(".italic");
```

##### 5.5数组或集合元素遍历

```javascript
$("p").each(function(i,n){
  	alert(i+"...."+n);
});
或
$.each(arr,function(i,n){
    alert(i+"...."+n);
})

 var objs = new Array();
            objs[0]="朱";
            objs[1]="保";
            objs[2]="生";
            objs[3]="太";
            objs[4]="帅";
            objs[5]="啦";
            $(objs).each(function (i,n) {
                alert(i+"..."+n);
            })
```



##### 5.6 CSS操作

**jQuery 操作 CSS**

jQuery 拥有若干进行 CSS 操作的方法。我们将学习下面这些：

- addClass() - 向被选元素添加一个或多个类
- removeClass() - 从被选元素删除一个或多个类
- toggleClass() - 对被选元素进行添加/删除类的切换操作
- css() - 设置或返回样式属性

**实例样式表**

下面的样式表将用于本页的所有例子：

```css
.important
{
        font-weight:bold;
        font-size:xx-large;
}
 
.blue
{
        color:blue;
}
```

**jQuery addClass() 方法**

下面的例子展示如何向不同的元素添加 class 属性。当然，在添加类时，您也可以选取多个元素：

```javascript
$("button").click(function(){
  $("h1,h2,p").addClass("blue");
  $("div").addClass("important");
});
```

您也可以在 addClass() 方法中规定多个类：

```javascript
$("button").click(function(){
  $("body div:first").addClass("important blue");
});
```

**jQuery removeClass() 方法**

下面的例子演示如何在不同的元素中删除指定的 class 属性：

```javascript
$("button").click(function(){
  $("h1,h2,p").removeClass("blue");
});
```

**jQuery toggleClass() 方法**

下面的例子将展示如何使用 jQuery toggleClass() 方法。该方法对被选元素进行添加/删除类的切换操作：

```javascript
$("button").click(function(){
  $("h1,h2,p").toggleClass("blue");
});
```

##### 5.7 CSS()方法

**jQuery css() 方法**

css() 方法设置或返回被选元素的一个或多个样式属性。

**返回 CSS 属性**

如需返回指定的 CSS 属性的值，请使用如下语法：

```javascript
css("propertyname");
```

下面的例子将返回首个匹配元素的 background-color 值：

```javascript
$("p").css("background-color");
```

**设置 CSS 属性**

如需设置指定的 CSS 属性，请使用如下语法：

```javascript
css("propertyname","value");
```

下面的例子将为所有匹配元素设置 background-color 值：

```javascript
$("p").css("background-color","yellow");
```

**设置多个 CSS 属性**

如需设置多个 CSS 属性，请使用如下语法：

```javascript
css({"propertyname":"value","propertyname":"value",...});
```

下面的例子将为所有匹配元素设置 background-color 和 font-size：

```javascript
$("p").css({"background-color":"yellow","font-size":"200%"});
```



#### 第六节 jQuery节点关系



通过 jQuery 节点关系，您能够从被选（当前的）元素开始，轻松地在家族树中向上移动（祖先），向下移动（子孙），水平移动（同胞）。这种移动被称为对 DOM 进行查找。

###### 6.1 祖先

**jQuery parent() 方法**

parent() 方法返回被选元素的直接父元素。

该方法只会向上一级对 DOM 树进行遍历。

下面的例子返回每个 <span> 元素的的直接父元素：

```javascript
$(document).ready(function(){
  $("span").parent();
});
```

###### 6.2 后代

**jQuery children() 方法**

children() 方法返回被选元素的所有直接子元素。

该方法只会向下一级对 DOM 树进行遍历。

下面的例子返回每个 <div> 元素的所有直接子元素：

```javascript
$(document).ready(function(){
  $("div").children();
});
```

您也可以使用可选参数来过滤对子元素的搜索。

下面的例子返回类名为 "1" 的所有 <p> 元素，并且它们是 <div> 的直接子元素：

```javascript
$(document).ready(function(){
  $("div").children("p.1");
});
```

**jQuery find() 方法**

find() 方法返回被选元素的后代元素，一路向下直到最后一个后代。

下面的例子返回属于 <div> 后代的所有 <span> 元素：

```javascript
$(document).ready(function(){
  $("div").find("span");
});
```

下面的例子返回 <div> 的所有后代：

```javascript
$(document).ready(function(){
  $("div").find("*");
});
```

###### 6.3 同胞

**jQuery siblings() 方法**

siblings() 方法返回被选元素的所有同胞元素。

下面的例子返回 <h2> 的所有同胞元素：

```javascript
$(document).ready(function(){
  $("h2").siblings();
});
```

您也可以使用可选参数来过滤对同胞元素的搜索。

下面的例子返回属于 <h2> 的同胞元素的所有 <p> 元素：

```javascript
$(document).ready(function(){
  $("h2").siblings("p");
});
```

**jQuery next() 方法**

next() 方法返回被选元素的下一个同胞元素。

该方法只返回一个元素。

下面的例子返回 <h2> 的下一个同胞元素：

```javascript
$(document).ready(function(){
  $("h2").next();
});
```



##### 案例1 ：全选 |全不选、反向全选

```javascript
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-1.8.3.js"></script>
</head>
<body>
    <table>
        <tr>
            <th><input type="checkbox" id="checkAll" /> </th>
            <th>学号</th>
            <th>名字</th>
        </tr>
        <tr>
            <td><input type="checkbox" class="check"/> </td>
            <td>1001</td>
            <td>朱保生帅</td>
        </tr>
        <tr>
            <td><input type="checkbox" class="check" /> </td>
            <td>1002</td>
            <td>朱保生太帅</td>
        </tr>
        <tr>
            <td><input type="checkbox" class="check"/> </td>
            <td>1003</td>
            <td>朱保生没法再帅了！</td>
        </tr>
    </table>
<script type="text/javascript">
    $(function () {
        $("#checkAll").click(function () {
           var result = $("#checkAll").prop("checked");
           if(result == true){
                $(".check").prop("checked",true);
           }else{
               $(".check").prop("checked",false);
           }
        })
  			//获取多个复选框
        var cs = $(".check");

        $(".check").click(function () {
            var count = 0;//累计选中的个数
            var result = $(this).prop("checked");//获取当前点击的复选框
           for(var i = 0;i<cs.length;i++){
               //通过jquery选择数组里每一个input元素
                if($(cs[i]).prop("checked") == true){
                    count++;//累计
                    if(count==cs.length){//如果累计的个数和总数相等
                        $("#checkAll").prop("checked",true);
                    }else{
                        $("#checkAll").prop("checked",false);
                    }
                }
           }
        })
    })
//反向全选	解决方案2:用each迭代器
        //1.先拿到多个复选框
        var cs = $(".check");

        $(".check").click(function () {
            var count = 0;
            $(cs).each(function (i,v) {
                if($(cs[i]).prop("checked")==true){
                    count++;
                    
                    if(count==cs.length){
                        $("#checkAll").prop("checked",true);
                    }else{
                        $("#checkAll").prop("checked",false);
                    }
                }
            })
        })
</script>
</body>
</html>
```



##### 案例2 ：两个 select框，把一个select框中的内容移动到另一个select

```html
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>移动元素</title>
		<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	</head>

	<body>

		<select id="leftselect" size="10"  style=" width: 100px;">
			<option value="1">北京</option>
			<option value="2">上海</option>
			<option value="3">南京</option>
			<option value="4">广州</option>
			<option value="5">杭州</option>
			<option value="6">深圳</option>
		</select>
	
		<div style="display: inline-block; vertical-align: top; margin-top: 20px;">
			
			<input type="button" value="右移>>" id="toRight" /><br/>
			<input type="button" value="左移<<" id="toLeft"/><br/>
			<input type="button" value="全部右移" id="toRightAll" /><br/>
			<input type="button" value="全部左移" id="toLeftAll"/>
			
		</div>
		<select id="rightselect" size="10" multiple="multiple" style=" width: 100px;">
			
		</select>
		
		<script type="text/javascript">
			$(function () {
            var leftselect=document.getElementById("leftselect");
            var rightselect=document.getElementById("rightselect");
            $("#toRight").click(function () {
                 $("#rightselect").append(leftselect.options[leftselect.selectedIndex]);
            });
            $("#toLeft").click(function () {
                $("#leftselect").append(rightselect.options[rightselect.selectedIndex]);
            });
            $("#toRightAll").click(function () {
                $("#rightselect").append(leftselect.options);
            });
            $("#toLeftAll").click(function () {
                $("#leftselect").append(rightselect.options);

            });
        })
		</script>
	</body>

</html>
```

##### 案例演示3:省市级联

```javascript
  <select id="prov">
        <option value="0">--请选择--</option>

    </select>
    <select id="city">
        <option value="0">--请选择--</option>

    </select>
<script>
        $(function () {
            var arr=new Array();
            arr["河北省"]=["保定","石家庄","邯郸","张家口","廊坊","秦皇岛"];
            arr["山东省"]=["济南","青岛","烟台","泰安"];
            arr["山西省"]=["太原","吕梁","大同","运城"];
            arr["河南省"]=["郑州","开封","驻马店","漯河","焦作"];
            arr["四川省"]=["成都","绵阳","乐山"];

            for(var key in arr){
                $("#prov").append("<option value='"+key+"'>"+key+"</option>");
            }

            $("#prov").change(function () {

                var v= this.options[this.selectedIndex].value;
                var cityarr=arr[v];

                //清空
                $("#city").html("<option value=\"0\">--请选择--</option>");

                $(cityarr).each(function (i,n) {
                    $("#city").append("<option value='"+n+"'>"+n+"</option>");
                })
            });

        })
        
</script>
```



#### 第七节 其他

###### 7.1 jQuery noConflict方法

**jQuery 和其他 JavaScript 框架**

正如您已经了解到的，jQuery 使用 $ 符号作为 jQuery 的简写。

如果其他 JavaScript 框架也使用 $ 符号作为简写怎么办？

其他一些 JavaScript 框架包括：MooTools、Backbone、Sammy、Cappuccino、Knockout、JavaScript MVC、Google Web Toolkit、Google Closure、Ember、Batman 以及 Ext JS。

其中某些框架也使用 $ 符号作为简写（就像 jQuery），如果您在用的两种不同的框架正在使用相同的简写符号，有可能导致脚本停止运行。

jQuery 的团队考虑到了这个问题，并实现了 noConflict() 方法。

**jQuery noConflict() 方法**

noConflict() 方法会释放对 $ 标识符的控制，这样其他脚本就可以使用它了。

当然，您仍然可以通过全名替代简写的方式来使用 jQuery：

```javascript
$.noConflict();
jQuery(document).ready(function(){
  jQuery("button").click(function(){
    jQuery("p").text("jQuery 仍然在工作!");
  });
});
```

您也可以创建自己的简写。noConflict() 可返回对 jQuery 的引用，您可以把它存入变量，以供稍后使用。请看这个例子：

```javascript
var jq = $.noConflict();
jq(document).ready(function(){
  jq("button").click(function(){
    jq("p").text("jQuery 仍然在工作!");
  });
});
```

如果你的 jQuery 代码块使用 $   简写，并且您不愿意改变这个快捷方式，那么您可以把   $ 符号作为变量传递给 ready 方法。这样就可以在函数内使用 $ 符号了 - 而在函数外，依旧不得不使用 "jQuery"：

```javascript
$.noConflict();
jQuery(document).ready(function($){
  $("button").click(function(){
    $("p").text("jQuery 仍然在工作!");
  });
});
```
#### 作业题

```
1、写一个综合模块，要求能够尽可能多的使用jQuery实现
```

#### 面试题

```
1、jQuery的美元符号$有什么作用？
2、body中的onload事件和jQuery中的document.ready()有什么区别？
3、jQuery中有哪几种类型的选择器？
```