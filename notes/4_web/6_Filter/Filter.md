### Web之过滤器



#### 今日内容

```
1、什么是过滤器
2、过滤器链
3、过滤器的优先级和参数
4、过滤器的典型应用
```

#### 教学目标

```
1、熟悉什么是过滤器
2、掌握过滤器链
3、掌握过滤器的优先级和参数
4、掌握过滤器的典型应用
```

#### 第一节 过滤器

##### 1.1 什么是过滤器

~~~
	Filter也称之为过滤器，它是Servlet技术中最激动人心的技术，WEB开发人员通过Filter技术，对web服务器管理的所有web资源：例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，从而实现一些特殊的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。
　　Servlet API中提供了一个Filter接口，开发web应用时，如果编写的Java类实现了这个接口，则把这个java类称之为过滤器Filter。通过Filter技术，开发人员可以实现用户在访问某个目标资源之前，对访问的请求和响应进行拦截。
~~~

##### 1.2 如何编写过滤器

~~~
1、编写java类实现Filter接口
2、重写doFilter方法
3、设置拦截的url
~~~

入门案例:

```java
package com.qf.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author wgy 2018/11/28 9:23
 * @version 1.0
 */
@WebFilter("/myservlet1")//过滤路径
public class MyFilter1 implements Filter {

    //初始化过滤器
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化了........init...  "+filterConfig);
    }

    //执行过滤
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤前........doFilter ");
        //放行
        chain.doFilter(request, response);

        System.out.println("过滤后.......doFilter");

    }

    //销毁
    @Override
    public void destroy() {
        System.out.println("销毁了.....destroy");
    }
}
```



##### 1.3 过滤器的配置

###### 1.3.1 注解式配置

在自定义的Filter类上使用注解@WebFilter(“/*”)

###### 1.3.2 xml配置

在web.xml中进行过滤器的配置：

~~~Xml
<!--过滤器的xml配置  -->
  <filter>
  <!--名称-->
    <filter-name>sf</filter-name>
    <!--过滤器类全称-->
    <filter-class>com.qf.web.filter.SecondFilter</filter-class>
  </filter>
 <!--映射路径配置-->
  <filter-mapping>
     <!--名称-->
    <filter-name>sf</filter-name>
     <!--过滤的url匹配规则和Servlet的一模一样-->
    <url-pattern>/*</url-pattern>
  </filter-mapping>
~~~

##### 1.4 过滤器链

~~~
通常客户端对服务器请求之后，服务器调用Servlet之前会执行一组过滤器（多个过滤器）,那么这组过滤器就称为一条过滤器链。
每个过滤器实现某个特定的功能，一个过滤器检测多个Servlet。（匹配几个，检测几个）。
一组过滤器中的执行顺序与<filter-mapping>的配置顺序有关。
当第一个Filter的doFilter方法被调用时，web服务器会创建一个代表Filter链的FilterChain对象传递给该方法。在doFilter方法中，开发人员如果调用了FilterChain对象的doFilter方法，则web服务器会检查FilterChain对象中是否还有filter，如果有，则调用第2个filter，如果没有，则调用目标资源
~~~

##### 1.5 过滤器的优先级

~~~
在一个web应用中，可以开发编写多个Filter，这些Filter组合起来称之为一个Filter链。web服务器根据Filter在web.xml文件中的注册顺序，决定先调用哪个Filter。当第一个Filter的doFilter方法被调用时，web服务器会创建一个代表Filter链的FilterChain对象传递给该方法。在doFilter方法中，开发人员如果调用了FilterChain对象的doFilter方法，则web服务器会检查FilterChain对象中是否还有filter，如果有，则调用第2个filter，如果没有，则调用目标资源

如果为注解的话，是按照类名的字符串顺序进行起作用的
如果web.xml，按照 filter-mapping注册顺序，从上往下
web.xml配置高于注解方式
如果不同包、同名的filter，按照包名再排序

~~~

##### 1.6 过滤器的初始化参数

在过滤器的创建的时候，可以传递初始化参数

第一种：基于注解的

~~~Java
/**
 * Servlet Filter implementation class FirstFilter 创建过滤器
 */
@WebFilter(value="/*",initParams= {@WebInitParam(name = "version", value = "1.0")})
public class FirstFilter implements Filter {

	public FirstFilter() {
	}

	/**
	 * @see Filter#destroy() 销毁
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy销毁……");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) 过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("doFilter……过滤");
		// 是否继续---访问下一个
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * 初始化
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init……初始化");
		System.out.println("初始化参数：版本号："+fConfig.getInitParameter("version"));
	}
}
~~~

第二种：基于xml配置

~~~java

/**
 *  创建过滤器
 */
public class SecondFilter implements Filter {

	public SecondFilter() {
		// TODO Auto-generated constructor stub
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 是否继续---访问下一个
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * 初始化
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("初始化参数：版本号："+fConfig.getInitParameter("version"));
	}

}

~~~

Web.xml实现配置：

~~~Xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">
  <display-name>Web_Day</display-name>
  
  <!--过滤器的xml配置  -->
  <filter>
    <filter-name>myfilter</filter-name>
    <filter-class>com.qf.web.filter.SecondFilter</filter-class>
     <!--过滤器的初始化参数  -->
    <init-param>
      <param-name>version</param-name>
      <param-value>1.0</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>myfilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
  </welcome-file-list>
</web-app>
~~~

##### 1.7 过滤器的优点

可以实现 Web 应用程序中的预处理和后期处理逻辑

##### 1.8 过滤器的典型应用

###### 案例1 自动登录

创建数据库和用户表

DbHelper类：

~~~java
package com.qf.utils;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//数据库工具类
public class DbHelper {

	private static DataSource ds;
	private static QueryRunner qr;
	static{
		ds=new ComboPooledDataSource();
		qr=new QueryRunner(ds);
	}
	//执行非查询语句，返回值受影响的行数
	public static int execute(String sql,Object... vs){
		try {
			return qr.execute(sql, vs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//执行查询语句
	public static <T> T querySingle(String sql,Class<T> clz,Object... vs){
		try {
			
			return qr.query(sql, new BeanHandler<>(clz),vs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
~~~

User类：

~~~Java
public class User {
	private int id;
	private String username;
	private String pass;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
~~~

过滤器代码：

~~~Java
/**
 * Servlet Filter implementation class AutoLoginFilter
 * 实现自动登录，只是拦截登录页面
 */
@WebFilter(value="/login.html")
public class AutoLoginFilter implements Filter {
    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//强制转换为Http的请求和响应
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse rep=(HttpServletResponse) response;
		
		//验证是否登录
		if(req.getSession().getAttribute("user")==null){
			//从Cookie获取上次保存的账号和密码
			Cookie[] cks=req.getCookies();
			User user=null;
			for(Cookie c:cks){
				if(c.getName().equals("user")){
					String[] us=c.getValue().split("@");
					user=new User();
					user.setUsername(us[0]);
					user.setPass(us[1]);
					break;
				}
			}
			//如果存储Cookie，那么就实现自动登录
			if(user!=null){//需要自动登录
				// 登录校验
				User user1 = DbHelper.querySingle("select * from tb_user where username=?", User.class, user.getUsername());
				boolean res=true;
				if (user1 != null) {
					if (user.getPass().equals(user1.getPass())) {
						req.getSession().setAttribute("user", user1);
						res=false;
						rep.sendRedirect(req.getServletContext().getContextPath()+"/success.jsp");
					}
				}
				if(res){//登录失败，之前的记录账号和密码错误
					Cookie ck=new Cookie("user","");
					ck.setPath("/");
					ck.setMaxAge(0);
					rep.addCookie(ck);
					rep.sendRedirect(req.getServletContext().getContextPath()+"/login.jsp");
				}
			}
			else{//直接登录页面
				chain.doFilter(request, response);
			}	
		}
		else{//如果已经登录，那么就直接放行
			rep.sendRedirect("success.jsp");
		}	
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
~~~

###### 案例2 过滤脏词

```java
public class DirtyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		chain.doFilter(new DirtyHttpServletRequest((HttpServletRequest)request), response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	static class DirtyHttpServletRequest extends HttpServletRequestWrapper{

		private List<String> dirtywords=new ArrayList<String>();
		public DirtyHttpServletRequest(HttpServletRequest request) {
			super(request);
			dirtywords.add("sb");
			dirtywords.add("狗蛋");
			dirtywords.add("扯淡");
		}
		@Override
		public String getParameter(String name) {
		
			String v=super.getParameter(name);
			for (String s : dirtywords) {
				v=v.replaceAll(s, "***");
			}
			return v;
		}
	}
}
```

###### 案例3 过滤器解决编码

```java
public class CharacterEncodingFilter implements Filter {

	//filter配置
	private FilterConfig config;
	//默认编码
	private String defaultcharset="utf-8";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config=filterConfig;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String charset=config.getInitParameter("charset");
		if(charset==null){
			charset=defaultcharset;
		}
		//1设置请求和响应的编码
		request.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		//2放行
		chain.doFilter(request, response);
		System.out.println("xxxxxxxxxxxxxxxx");
	
	}

	@Override
	public void destroy() {
		
	}

}
```

案例4

登录、自动登录、查询所有、权限验证（普通-查看、删除）

#### 总结

过滤器 

​	Filter

​	

#### 作业题

```
1、使用过滤器实现未登录拦截
```

#### 面试题

```
1、过滤器有哪些作用和用法？ 
```

