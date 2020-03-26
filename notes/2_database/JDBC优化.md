

### 三层架构、JDBC事务、DBUtils

#### 今日内容

```
1、三层架构
2、三层架构结合事务
3、ThreadLocal解决事务问题
4、DAO通用封装方法
5、DbUtils的使用
```



## 第一节  三层架构

### 1.1 引言

>  三层架构(3-tier architecture) 通常意义上的三层架构就是将整个业务应用划分为：界面层（User Interface layer）、业务逻辑层（Business Logic Layer）、数据访问层（Data access layer）。区分层次的目的即为了“[高内聚低耦合](https://baike.baidu.com/item/高内聚低耦合/5227009)”的思想。 

### 1.2各层作用

> 数据访问层（DAL）的作用：
>         从数据源加载数据（select）
>         向数据源写入数据（Insert/Update）
>         从数据源删除数据（Delete）
>   业务逻辑层（BLL）的作用：
>        从DAL中获取数据，以供UI显示用
>        从UI中获取用户指令和数据，执行业务逻辑
>        从UI获取用户指令和数据，通过DAL写入数据源
>
>   显示层（UI）的作用：
>        向用户展现特定业务数据
>        采集用户的输入信息和操作

### 1.3引用关系

> UI----->BLL------>DAL

### 1.4 分层优缺点

>  优点
>
> 1、开发人员可以只关注整个结构中的其中某一层；
> 2、可以很容易的用新的实现来替换原有层次的实现；
> 3、可以降低层与层之间的依赖；
> 4、有利于标准化；
> 5、利于各层逻辑的复用。
> 6、结构更加的明确
> 7、在后期维护的时候，极大地降低了维护成本和维护时间 
>
> 
>
> 缺点
> 1、降低了系统的性能。这是不言而喻的。如果不采用分层式结构，很多业务可以直接造访数据库，以此获取相应的数据，如今却必须通过中间层来完成。
> 2、有时会导致级联的修改。这种修改尤其体现在自上而下的方向。如果在表示层中需要增加一个功能，为保证其设计符合分层式结构，可能需要在相应的业务逻辑层和数据访问层中都增加相应的代码。
> 3、增加了开发成本。

### 1.5 业务层(service)

> 什么是业务？
>
> ​     代表用户完成的一个业务功能，可以由一个或多个DAO的调用组成。（软件所提供的一个功能都叫业务）  
>
> 转账：一次转账。两个DAO操作：扣钱，加钱

#### 1.5.1定义业务层

> 包名：service
>
> 实现类包名：impl
>
> 接口名：xxxService
>
> 实现类：xxxServiceImpl

业务层Service接口代码：

```java
package com.qf.www.service;
/*
	service层是业务层，是个接口
*/
public interface EmpService {
    //1查询
    List<Emp> findAll();
    //2更新
    void update(Emp e);
    //3删除
    void delete(int empno);
    //4添加
    void add(Emp e);
}

```

业务层service实现类代码

```java
package com.qf.www.service.impl;

import com.qf.www.service.EmpService;
/*
	业务层实现类主要是创建DAO层的数据访问对象，然后调用方法完成业务功能
*/
public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();
    @Override
    public List<Emp> findAll() {
        List<Emp> list = empDao.findAll();
        return list;
    }

    @Override
    public void update(Emp e) {
		empDao.update(e);
    }

    @Override
    public void delete(int empno) {
			empDao.delete(empno);
    }

    @Override
    public void add(Emp e) {
		empDao.add(e);
    }
}

```



## 第二节 三层结合事务

> 通过三层结合事务，完成转账练习

### 2.1项目准备

​	创建Java项目

​	导入需要的jar包  mysql驱动、 druid.jar

​	添加数据库配置文件

* db.properties

```properties
#连接设置
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/account
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

### 2.2编写Java代码

> 应用三层架构，其中dao层，我们已经搭建过，只需要添加service业务层和view视图层

#### 2.2.1分包

   	com.qf.dao

​       com.qf.dao.impl

​       com.qf.service

​       com.qf.service.impl

​       com.qf.view

​       com.qf.utils

​       com.qf.domain

#### 	2.2.2编写DataSourceUtils工具类

> DatasourceUtils工具类,优化获取连接,优化事务操作

```java
package com.itqf.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.qf.utils.DruidUtils;

public class DataSourceUtils {
	private static DruidDataSource ds=null;
   //静态代码块
    static {
        InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties=new Properties();
        try {
            properties.load(is);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	/**
	 * 获取数据源
	 * @return 连接池
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 从当前线程上获取连接
	 * @return 连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		//--从
		try {
            Connection connection = ds.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
 //关闭
    public static void close(){
        Connection connection = getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	/**
	 *---- 开启事务
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException{
		//获取连接//开启事务
		getConnection().setAutoCommit(false);
	}
	
	/**
	 *--- 事务提交
	 */
	public static void commitAndClose(){
		try {
			//获取连接
			Connection conn = getConnection();
			//提交事务
			conn.commit();
			//释放资源
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ----事务回滚
	 */
	public static void rollbackAndClose(){
		try {
			//获取连接
			Connection conn = getConnection();
			//事务回滚
			conn.rollback();
			//释放资源
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
```

#### 2.2.3编写DAO层代码

> dao层进行具体数据库操作

实体类：

```java
package com.qf.www.entity;

public class Account {
    private int id;
    private String name;
    private double money;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Account() {
    }

    public Account(int id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }
}
```



```java
package com.qf.www.dao.impl;

import com.qf.www.dao.AccountDao;
import com.qf.www.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    @Override
    public void updateSave(int cardNo, double money) {
        connection = DBUtils.getConnection();
        String sql = "update account set money = money+? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, money);
            preparedStatement.setInt(2, cardNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close();
        }
    }

    @Override
    public void updateTake(int cardNo, double money) {
        connection = DBUtils.getConnection();
        String sql = "update account set money = money-? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, money);
            preparedStatement.setInt(2, cardNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close();
        }
    }
}

```

#### 2.2.4编写业务层代码

```java
package com.itqf.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.itqf.dao.AccountDao;
import com.itqf.dao.AccountDaoDButis;
import com.itqf.dao.AccountDaoLocal;
import com.itqf.utils.DataSourceUtils;
import com.itqf.utils.JdbcUtils;
/**
 * jdbc+threadlocal
 * 
 * @author Administrator
 *
 */
public class AccountServiceImpl {
    
	/**
	 * 转账业务逻辑
	 * @param from
	 * @param to
	 * @param money
	 * @throws Exception 
	 */
	public  void transfer(int from, int to,double money) throws Exception {
		AccountDaoDButis accountDao = new AccountDaoDButis();
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			//1.转出
			accountDao.out(from,money);
			//2.转入
			accountDao.in(to,money);
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace
			DataSourceUtils.rollbackAndClose();
			throw e; //接着向外抛
		}
	}

}
```

#### 2.2.4编写视图层代码

> 视图层主要用于用户输入转账内容，调用业务层

```java
package com.qf.www.view;

import com.qf.www.service.AccountService;
import com.qf.www.service.impl.AccountServiceImpl;

import java.util.Scanner;

public class StartView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎登陆转账系统：");
        System.out.println("请输入卡号：");
        int from  = scanner.nextInt();
        System.out.println("请输入对方卡号");
        int to = scanner.nextInt();
        System.out.println("请输入转账金额");
        double money = scanner.nextDouble();
        //调用业务层
        AccountService accountService = new AccountServiceImpl();
        //调用转账方法，传入参数
        accountService.transfer(from, to, money);
    }
}

```







## 第三节 ThreadLocal

> 三层结合事务现存在的问题：
>
> 当前事务出现异常，一个DAO操作执行成功，一个DAO失败，则需要进行回滚，但是却发现回滚失败了，原因是在整个事务操作过程中，每一个环节应用的数据库连接对象都不是同一个，所以事务的控制从头到尾不是一个连接。
>
> 需要使用ThreadLocal， 可以在整个线程（单条执行路径）所持有的Map中，存储一个键（threadlocal）值（conn）。  

### 3.1优化DataSourceUtils

```java
package com.itqf.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.qf.utils.DruidUtils;

public class DataSourceUtils {
	private static DruidDataSource ds=null;
    //创建
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
   //静态代码块
    static {
        InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties=new Properties();
        try {
            properties.load(is);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	/**
	 * 获取数据源
	 * @return 连接池
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 从当前线程上获取连接
	 * @return 连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		try {
           //--从线程获取链接
            Connection connection = threadLocal.get();
            if(connection==null){
                //第一次获取 创建一个连接 和当前的线程绑定
                connection = ds.getConnection();
                	 //----绑定
                threadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
 //关闭
    public static void close(){
        Connection connection = getConnection();
        try {
            //和当前线程解绑
            threadLocal.remove();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	/**
	 *---- 开启事务
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException{
		//获取连接//开启事务
		getConnection().setAutoCommit(false);
	}
	
	/**
	 *--- 事务提交
	 */
	public static void commitAndClose(){
		try {
			//获取连接
			Connection conn = getConnection();
			//提交事务
			conn.commit();
			//释放资源
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ----事务回滚
	 */
	public static void rollbackAndClose(){
		try {
			//获取连接
			Connection conn = getConnection();
			//事务回滚
			conn.rollback();
			//释放资源
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
```

### 3.2优化DAO层代码

```java
package com.qf.www.dao.impl;

import com.qf.www.dao.AccountDao;
import com.qf.www.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    @Override
    public void updateSave(int cardNo, double money) {
        connection = DBUtils.getConnection();
        String sql = "update account set money = money+? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, money);
            preparedStatement.setInt(2, cardNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            DBUtils.close();不在dao调用关闭连接的方法！
        }
    }

    @Override
    public void updateTake(int cardNo, double money) {
        connection = DBUtils.getConnection();
        String sql = "update account set money = money-? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, money);
            preparedStatement.setInt(2, cardNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            DBUtils.close();不在dao调用关闭连接的方法！
        }
    }
}

```

## 第四节 DAO通用方法

### 4.1增删改通用方法：

```java
//封装 增、删、改的通用方法
    public static int executeUpdate(String sql, Object... params) { // Object...    可变长参数
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, preparedStatement, connection);
        }
        return 0;
    }

```

### 4.2查询通用方法：

定义封装规则接口：

```java
package com.qf.www.utils;

import java.sql.ResultSet;
//封装规则。封装对象为泛型，通用。参数需要的是ResultSet对象
public interface RowMapper<T> {
    T getRow(ResultSet rs);
}

```

定义封装方式：

```java
package com.qf.www.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
//定义具体的封装方式，决定通用查询封装的是具体某个对象
//遍历ResultSet，取值做对象属性的赋值
public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account getRow(ResultSet rs) {
        Account account = new Account();
        try {
            account.setId(rs.getInt(1));
            account.setMoney(rs.getDouble(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}

```



查询通用方法：

```java
//查询通用方法 泛型方法。返回值为泛型集合，因为查询可能是单个或多个
//参数:sql语句、封装方式、参数列表
    public static <T> List<T> commonsSelect(String sql , RowMapper<T> mapper,Object... params){
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet rs = null;
        //定义泛型集合，类型由封装方式的具体对象来决定
        List<T> list=new ArrayList<T>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            //循环为占位符赋值
            for(int i= 0;i<params.length;i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            rs = preparedStatement.executeQuery();
            //迭代
            while(rs.next()){
                //获得一行数据，调用封装方式，得到对象，添加到集合中
                list.add(mapper.getRow(rs))  ;
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(rs, preparedStatement, connection);
        }	

        return null;
    }
```



## 第五节  DBUtils使用

> Commons DbUtils是[Apache](https://baike.baidu.com/item/Apache/8512995)组织提供的一个对[JDBC](https://baike.baidu.com/item/JDBC)进行简单封装的开源工具类库，使用它能够简化[JDBC](https://baike.baidu.com/item/JDBC)应用程序的开发，同时也不会影响程序的性能。

### 5.1 DBUtils简介

DBUtils是java编程中的数据库操作实用工具，小巧简单实用，

1.对于数据表的读操作，可以把结果转换成List，Array，Set等java集合，便于程序员操作。

2.对于数据表的写操作，也变得很简单（只需写sql语句）。

**DBUtils包括主要类**

DbUtils类：启动类

**ResultSetHandler**接口：转换类型接口

​	--ArrayHandler类：实现类，把记录转化成数组

​	--ArrayListHandler类：把记录转化成数组，并放入集合中

​	--ColumnListHandler类：取某一列的数据。封装到List中。

​	--**ScalarHandle**r类：适合获取一行一列数据。

​	--**BeanHandler**类：实现类,把记录转成对象。

​	--**BeanListHandler**类：实现类，把记录转化成List，使记录为JavaBean类型的对象

**QueryRunner**类：执行SQL语句的类



### 5.2 DBUtils工具类封装

#### 5.2.1 项目准备

* 创建项目

* 导入jar包 工具类 配置文件
  commons-dbutils-1.6.jar
  druid-1.1.5.jar
  
  数据库驱动包
  
  DruidUtils.java工具类
  database.properties配置文件

#### 5.2.2 实现代码

```java
public class ResultHanlder {

	@Test
	public void testArrayHander() throws SQLException {

		// ArrayHandler:适合取1条记录。把该条记录的每列值封装到一个数组中Object[]
		QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

		Object[] query = runner.query("select * from school where empno = ?", new ArrayHandler(), 1234);

		for (Object object : query) {

			System.out.println(object);
		}

	}

	@Test
	public void testArrayListHander() throws SQLException {

		// ArrayHandler:适合取1条记录。把该条记录的每列值封装到一个数组中Object[]
		QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

		List<Object[]> query = runner.query("select * from emp ", new ArrayListHandler());

		for (Object[] objects : query) {
			for (Object object : objects) {

				System.out.println(object);
			}
		}

	}

	@Test
	public void testColumnListHander() throws SQLException {

		// ColumnListHandler:取某一列的数据。封装到List中。
		QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

		List<Object> query = runner.query("select * from emp ", new ColumnListHandler<Object>(2));

		for (Object objects : query) {

			System.out.println(objects);
		}

	}


	@Test
	public void testScalarHandler() throws SQLException {

		// ScalarHandler:适合取单行单列数据
		QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

		Object query = runner.query("select count(*) from emp ", new ScalarHandler());
		System.out.println(query);
	}

	@Test
	public void testBeanHandler() throws SQLException {
		// BeanHandler:适合取单行单列数据
		QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
		Employee query = runner.query("select * from emp where empno=1234 ", new BeanHandler<Employee>(Employee.class));
		System.out.println(query.toString());
	}
  
  @Test
	public void testBeanListHandler() throws SQLException {
		// BeanHandler:适合取多行多列数据
		QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
		List<Employee> query2 =  runner.query("select * from emp", new BeanListHandler<Employee>(Employee.class));
		for (Employee employee : query2) {
			System.out.println(employee);
		}
	}
}
```

## 总结

1、三层架构        DAO、Service、View        Utils

2、结合事务的三层架构，在DBUtils工具类里，封装对事务的一系列操作

3、ThreadLocal解决多个DAO操作，connection对象不同步的问题。

4、DAO通用方法：executeUpdate增删改、commonsSelect查询方法（重点）。

5、DBUtils的使用，QueryRunner