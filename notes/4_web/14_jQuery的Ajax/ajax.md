### 第一节 jQuery AJAX  

今日内容

```
1.jQuery的Ajax
2.综合案例
```



##### 1.1 jQuery AJAX简介

**什么是 AJAX？**

AJAX = 异步 JavaScript 和 XML（Asynchronous JavaScript and XML）。

简短地说，在不重载整个网页的情况下，AJAX 通过后台加载数据，并在网页上进行显示。

使用 AJAX 的应用程序案例：谷歌地图、腾讯微博、优酷视频、人人网等等。

##### 1.2 ajax()方法

​	jQuery 底层 AJAX 实现。简单易用的高层实现get和post方法 等。$.ajax() 返回其创建的 XMLHttpRequest 
对象。大多数情况下你无需直接操作该函数，除非你需要操作不常用的选项，以获得更多的灵活性。 

​	常用参数

​	contentType:(默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数情况。

​	data:发送到服务器的数据

​	dataType:预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断。

​		"xml": 返回 XML 文档，可用 jQuery 处理。

​		"html": 返回纯文本 HTML 信息；包含的script标签会在插入dom时执行。

​		"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了"cache"参数。'''注意：'''在远程请求时(不在同一个域下)，所有POST请求都将转为GET请求。(因为将使用DOM的script标签来加载)

​		"json": 返回 JSON 数据 。

​		"jsonp": [JSONP](http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/) 格式。使用 [JSONP](http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/) 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。

​		"text": 返回纯文本字符串

​	success：请求成功后的回调函数。参数：由服务器返回，并根据dataType参数进行处理后的数据；描述状态的字符串。

```
function (data, textStatus) {
    // data 可能是 xmlDoc, jsonObj, html, text, 等等...
    this; // 调用本次AJAX请求时传递的options参数
}
```

​	type：(默认: "GET") 请求方式 ("POST" 或 "GET")， 默认为 "GET"。注意：其它 HTTP 请求方法，如 PUT 和 DELETE 也可以使用，但仅部分浏览器支持。

​	url：(默认: 当前页地址) 发送请求的地址。

````html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>使用jQuery实现ajax请求</title>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="mydiv">
	
	</div>

	<input id="btn1" type="button" value="发送ajax请求get方式" >
	<input id="btn2" type="button" value="发送ajax请求post方式" >
	<script type="text/javascript">
		$(function(){
			$("#btn1").click(function(){
				
				//ajax方法
				$.ajax({
					url:"servlet1",
					type:"GET",
					data:"username=张三&age=20",
					dataType:"html",
					contentType:"text/plain",
					success:function(msg,status){
						$("#mydiv").append(msg);
						alert(status);
					}
				});
				
			});
			
		$("#btn2").click(function(){
				
				//ajax方法
				$.ajax({
					url:"servlet1",
					type:"POST",
					data:"username=张三&age=20",
					dataType:"html",
					contentType:"application/x-www-form-urlencoded",
					success:function(msg,status){
						$("#mydiv").append(msg);
						alert(status);
					}
				});
				
			});
			
		})
	
	</script>
</body>
</html>
````





##### 1.3 get和post方法

**$.get() 方法**

$.get() 方法通过 HTTP GET 请求从服务器上请求数据。

语法：

```javascript
$.get(URL,callback);
```

必需的 *URL* 参数规定您希望请求的 URL。

可选的 *callback* 参数是请求成功后所执行的函数名。

下面的例子使用 $.get() 方法从服务器上的一个文件中取回数据：

```javascript
$("button").click(function(){
  $.get("demo_test.php",function(data,status){
    alert("数据: " + data + "\n状态: " + status);
  });
});
```

**$.post() 方法**

$.post() 方法通过 HTTP POST 请求从服务器上请求数据。

**语法:**

$.post(*URL,data,callback*);

必需的 *URL* 参数规定您希望请求的 URL。

可选的 *data* 参数规定连同请求发送的数据。

可选的 *callback* 参数是请求成功后所执行的函数名。

下面的例子使用 $.post() 连同请求一起发送数据：

```javascript
$("button").click(function(){
    $.post("/try/ajax/demo_test_post.jsp",
    {
        name:"百度",
        url:"http://www.baidu.com"
    },
        function(data,status){
        alert("数据: \n" + data + "\n状态: " + status);
    });
});
```

### 第二节 综合案例(ajax实现增删改查)

#### 2.1建库建表

数据库shop数据库

product表语句:

```mysql

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `pid` varchar(96) DEFAULT NULL,
  `pname` varchar(150) DEFAULT NULL,
  `market_price` varchar(10) DEFAULT NULL,
  `shop_price` varchar(10) DEFAULT NULL,
  `pimage` varchar(600) DEFAULT NULL,
  `pdate` varchar(30) DEFAULT NULL,
  `pdesc` varchar(765) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 插入数据的sql语句
insert  into `product`(`pid`,`pname`,`market_price`,`shop_price`,`pdate`,`pdesc`) values 
('1','小米 4c 标准版','1399','1299','2015-11-02','小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待'),
('10','华为 Ascend Mate7','2699','2599','2015-11-02','华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！'),
('11','vivo X5Pro','2399','2298','2015-11-02','移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术'),
('12','努比亚（nubia）My 布拉格','1899','1799','2015-11-02','努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！'),
('13','华为 麦芒4','2599','2499','2015-11-02','华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖'),
('14','vivo X5M','1899','1799','2015-11-02','vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV'),
('15','Apple iPhone 6 (A1586)','4399','4288','2015-11-02','Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！'),
('1','小米 4c 标准版','1399','1299','2015-11-02','小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待'),
('10','华为 Ascend Mate7','2699','2599','2015-11-02','华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！'),
('11','vivo X5Pro','2399','2298','2015-11-02','移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术'),
('12','努比亚（nubia）My 布拉格','1899','1799','2015-11-02','努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！'),
('13','华为 麦芒4','2599','2499','2015-11-02','华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖'),
('14','vivo X5M','1899','1799','2015-11-02','vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV'),
('15','Apple iPhone 6 (A1586)','4399','4288','2015-11-02','Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！'),
('1','小米 4c 标准版','1399','1299','2015-11-02','小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待'),
('10','华为 Ascend Mate7','2699','2599','2015-11-02','华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！'),
('11','vivo X5Pro','2399','2298','2015-11-02','移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术'),
('12','努比亚（nubia）My 布拉格','1899','1799','2015-11-02','努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！'),
('13','华为 麦芒4','2599','2499','2015-11-02','华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖'),
('14','vivo X5M','1899','1799','2015-11-02','vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV'),
('15','Apple iPhone 6 (A1586)','4399','4288','2015-11-02','Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！'),
('1','小米 4c 标准版','1399','1299','2015-11-02','小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待'),
('10','华为 Ascend Mate7','2699','2599','2015-11-02','华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！'),
('11','vivo X5Pro','2399','2298','2015-11-02','移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术'),
('12','努比亚（nubia）My 布拉格','1899','1799','2015-11-02','努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！'),
('13','华为 麦芒4','2599','2499','2015-11-02','华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖'),
('14','vivo X5M','1899','1799','2015-11-02','vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV'),
('15','Apple iPhone 6 (A1586)','4399','4288','2015-11-02','Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！');

```

#### 2.2 MVC三层搭建

utlis工具包

```java
package com.qf.www.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.util.Properties;

public class DruidUtils {
    private static DruidDataSource dataSource;
    static {
        Properties properties = new Properties();
        try {
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("database.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DruidDataSource getDataSource(){
        return dataSource;
    }
}

```

连接池配置文件:

```properties
#连接设置
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/product
username=root
password=1234
#<!-- 初始化连接 -->
initialSize=10
#最大连接数量
maxActive=50
#<!-- 最小空闲连接 -->
minIdle=5
#<!-- 超时等待时间以毫秒为单位 6000毫秒/1000等于60秒 -->
maxWait=5000
```

根据表结构创建实体类

```java
package com.qf.www.entity;

public class Product {
    private String pid;
    private String pname;
    private String market_price;
    private String shop_price;
    private String pdate;
    private String pdesc;

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", market_price='" + market_price + '\'' +
                ", shop_price='" + shop_price + '\'' +
                ", pdate='" + pdate + '\'' +
                ", pdesc='" + pdesc + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public Product() {
    }

    public Product(String pid, String pname, String market_price, String shop_price, String pdate, String pdesc) {
        this.pid = pid;
        this.pname = pname;
        this.market_price = market_price;
        this.shop_price = shop_price;
        this.pdate = pdate;
        this.pdesc = pdesc;
    }
}

```

DAO层:

```java
package com.qf.www.dao;

import com.qf.www.entity.Product;

import java.util.List;

public interface ProductDao {
    //查询所有
    List<Product> getAllProduct();
    //删除
    int delete(String pid);
    //修改
    int update(Product product);
}

```

DAO实现类:

```java
package com.qf.www.dao.impl;

import com.qf.www.dao.ProductDao;
import com.qf.www.entity.Product;
import com.qf.www.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public List<Product> getAllProduct() {
        try {
            return queryRunner.query("select * from product;",new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(String pid) {
        try {
            return queryRunner.update("delete from product where pid = ?",pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Product product) {
        Object[] params={product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPdesc(),product.getPid()};
        try {
            return queryRunner.update("update product set pname = ? , market_price = ? , shop_price = ? ,pdesc = ? where  pid = ? ;",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

```

Service层:

```java
package com.qf.www.service;

import com.qf.www.entity.Product;

import java.util.List;

public interface ProductService {
    String getAllProduct();
    int delete(String pid);
    int update(Product product);
}

```

Service层实现类:

```java
package com.qf.www.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qf.www.dao.ProductDao;
import com.qf.www.dao.impl.ProductDaoImpl;
import com.qf.www.entity.Product;
import com.qf.www.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    @Override
    public String getAllProduct() {
        List<Product> allProduct = productDao.getAllProduct();
        if(allProduct!=null){
            return JSON.toJSONString(allProduct, SerializerFeature.PrettyFormat);
        }
        return null;
    }

    @Override
    public int delete(String pid) {
        return productDao.delete(pid);
    }

    @Override
    public int update(Product product) {
        return productDao.update(product);
    }
}

```

web层servlet编写:

1.查询所有servlet

```java
package com.qf.www.web;

import com.qf.www.entity.Product;
import com.qf.www.service.ProductService;
import com.qf.www.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetAllServlet",value = "/getall")
public class GetAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ProductService productService = new ProductServiceImpl();
        //得到业务层转换对象的JSON字符串
        String allProduct = productService.getAllProduct();
        System.out.println(allProduct);
        //响应给客户端
        response.getWriter().print(allProduct);
    }
}

```

2.删除servlet

```java
package com.qf.www.web;

import com.qf.www.service.ProductService;
import com.qf.www.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DelServlet",value = "/del")
public class DelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        ProductService productService = new ProductServiceImpl();
        int delete = productService.delete(pid);
        PrintWriter writer = response.getWriter();
        //如果service返回的大于0,删除成功,响应给页面对应的结果
        if(delete>0){
            writer.print("1");
        }else{
            writer.print("0");
        }
    }
}

```

修改servlet

```java
package com.qf.www.web;

import com.alibaba.fastjson.JSON;
import com.qf.www.entity.Product;
import com.qf.www.service.ProductService;
import com.qf.www.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateServlet",value = "/update")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String pinfo = request.getParameter("pinfo");

        Product product = JSON.parseObject(pinfo, Product.class);
		
        ProductService productService = new ProductServiceImpl();
        int update = productService.update(product);
        PrintWriter writer = response.getWriter();
        //如果修改成功,返回给页面对应的结果
        if(update >0){
            System.out.println("sss");
            writer.print("1");
        }else{
            writer.print("0");
        }
    }
}

```

#### 2.3 页面搭建

show.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table width="70%" align="center" border="1px" bgcolor="green" cellspacing="0px"
       cellpadding="0px" id="content">
    <tr>
        <th width="4%"><input id="checkall" type="checkbox" >全选</th>
        <th width="8%">编号</th>
        <th width="10%">名称</th>
        <th width="8%">市场价格</th>
        <th width="8%">商场价格</th>
        <th width="12%">上架日期</th>
        <th width="20%">商品描述</th>
        <th width="10%">商品操作</th>
    </tr>

    <tbody >

    </tbody>


<script src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(function () {
            $.get("/product/getall",function (data) {
                var obj  = JSON.parse(data);
                loadAll(obj);
            })
        })
        function loadAll(obj) {
            $.each(obj,function (i,n) {
                var content =  $("#content");
                var o = obj[i];
                var tr  = document.createElement("tr");

                var checktd = document.createElement("td");
                var checkinput = document.createElement("input");
                checkinput.setAttribute("type","checkbox");
                checkinput.setAttribute("name","idcheck");
                checkinput.setAttribute("value",o.pid);
                checkinput.setAttribute("class","son");
                checktd.appendChild(checkinput);

                tr.appendChild(checktd);
                var tdpid = document.createElement("td");
                tdpid.innerHTML = o.pid;
                tr.appendChild(tdpid);

                //  <td>充气泵</td>
                var nametd = document.createElement("td");
                nametd.innerText = o.pname;
                tr.appendChild(nametd);

                // <td>998</td>
                var markettd = document.createElement("td");
                markettd.innerText = o.market_price;
                tr.appendChild(markettd);

                // <td>250</td>
                var shoptd = document.createElement("td");
                shoptd.innerText = o.shop_price;
                tr.appendChild(shoptd);

                var shoptd = document.createElement("td");
                shoptd.innerText = o.pdate;
                tr.appendChild(shoptd);

                var shoptd = document.createElement("td");
                shoptd.innerText = o.pdesc;
                tr.appendChild(shoptd);


                var dealtd = document.createElement("td");

                dealtd.innerHTML="<a href=\"javascript:void(0)\" onclick=\"deleteProduct("+o.pid+")\">删除</a>  \n" +
                    " <a href=\"javascript:void(0)\" onclick=\"updateProduct('"+o.pid+"','"+o.pname+"','"+o.market_price+"','"+o.shop_price+"','"+o.pdesc+"','"+o.pdate+"')\">修改</a>"

                tr.appendChild(dealtd);

                content.append(tr);
            })
        }
        function deleteProduct(pid) {
            var result = confirm("确定删除吗?");
            if(result ==true){
                $.post("/product/del","pid="+pid,function (data) {
                    if(data=="1"){
                        alert("删除成功!");
                        location.reload();
                    }else{
                        alert("删除失败!");
                    }
                })
            }
        }
        //修改  拼接参数连接
        function updateProduct(pid,pname,market_price,shop_price,pdesc,pdate) {
            location.href="edit.html"+"?pid="+pid+"&pname="+pname+"&marketPrice="+market_price+"&shopPrice="+shop_price+"&pdesc="+pdesc+"&pdate="+pdate;

        }
    </script>
</table>
</body>
</html>
```

修改页面:

edit.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<script src="js/jquery-1.8.3.js"></script>

<body>
<form method="post" id="f1">
    <input  id="pid" type="hidden" name="pid">
    商品名称：<input id="pname" type="text" name="pname" value=""> <br>
    市场价格：<input id="marketPrice" type="text" name="market_price" value=""> <br>
    上架日期:<input id="pdate" type="date" name="pdate" value="">
    商城价格：<input id="shopPrice" type="text" name="shop_price" value=""> <br>
    商品描述：<input id="pdesc" type="text" name="pdesc" value=""> <br>
</form>
<button onclick="update()">修改</button>
<script src="https://cdn.bootcss.com/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>

<script type="text/javascript">
    $(function () {
        //获得url地址之后的参数: ?xxx=xxx&yyy=xxx...
        var search = location.search;
        //从字符串下标1开始截取参数部分.?舍掉
        var s = search.substring(1);
        //根据&进行分割成 名称和值为一对的的数组
        var strings = s.split("&");
        //jQuery的遍历方法
        $.each(strings,function (i) {
            //对数组中每一个元素用=分割,成为  名字=值 的格式数组
            var param = strings[i].split("=");
            //下标0是要获得的name属性
            var name = param[0];
            //下标1获得的是值
            var value=param[1];
            //如果参数字符串中有中文,需要进行解码.
            if(name=="pname" || name=="pdesc"){
                value = decodeURI(value);
            }
            //选择对应的标签进行填充value值
            $("#"+name).val(value);

        })
    })
    function update() {
        //将FORM表单进行序列化转化为JSON对象
        var serialize = $("#f1").serializeJSON();
		//将JSON对象转换为JSON字符串
        var s= JSON.stringify(serialize)
        $.post("/product/update","pinfo="+s,function (data) {
            if(data=="1"){
                alert("修改成功!进入首页");
                location.href="show.html";
            }else{
                alert("修改失败")
            }
        })
    }
</script>
</body>
</html>
```