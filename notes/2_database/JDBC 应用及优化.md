## JDBC 应用及优化

### 1. 封装工具类

DbUtils类功能：1 注册驱动  2 获取连接   3释放资源 

#### 1.1 重用性方案

```java
package utils;

import java.sql.*;

/**
 * @author: huhao
 * @time: 2020/3/20 16:52
 * @desc: 重用性工具类
 */
public class DBUtils {

    private static String driver = "com.mysql.cj.jdbc.Driver"; // 加上static是因为静态方法不能使用非静态变量
    private static String url = "jdbc:mysql://localhost:3306/mydb_test?serverTimezone=UTC";
    private static String user = "root";
    private static String pwd =  "hh123456";



    /**
     * 注册驱动
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 释放资源
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

```

#### 1.2 跨平台方案

db.properties

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mydb_test?serverTimezone=UTC
user=root
pwd=hh123456
```

```java
package utils;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/3/21 14:49
 * @desc:
 */
public class DBUtilsPlatform {

    private static String driver;
    private static String url;
    private static String user;
    private static String pwd;
    private static Connection connection;

    /**
     * 注册驱动
     */
    static {

        // 通过流读取properties文件
        try {
//            InputStream inputStream = new FileInputStream("src\\db.properties");  不可以；应该找资源，相对路径
            InputStream resourceAsStream = DBUtilsPlatform.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            pwd = properties.getProperty("pwd");

            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接
     * @return
     */
    public static Connection getConnection(){

        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 释放资源
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection){

        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 2 ResultSet查询封装

#### 2.1 ORM（Object Relational Mapping）实体类（Entity）：零散数据的载体。

 在应用开发中，我们从数据库查询出的结果集（ResultSet）一般都需要取得（get）其中的数据然后存放到（set）实体对象（Entity）中，以便进一步的处理需要。常用也最容易理解的方式就是从ResultSet中get相应的字段值后调用实体对象的对应的属性的set方法，把值保存在实体对象中。

实体类：

```java
package com.qf.www.entity;

import java.util.Date;

public class Emp {
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private double sal;
    private double comm;
    private int deptno;

    public Emp() {
    }

    public Emp(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}

```

```java
//根据员工编号查询单个员工，查到就封装成对象，返回 
public Emp getEmpByEmpNo(int empno) { 		
		connection = DbUtils.getConnection();
        String sql = "select * from emp where empno = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");
                double sal = resultSet.getDouble("sal");
                double comm = resultSet.getDouble("comm");
                int deptno = resultSet.getInt("deptno");
                Emp emp = new Emp();
                emp.setEmpno(empno);
                emp.setEname(ename);
                emp.setJob(job);
                emp.setMgr(mgr);
                emp.setHiredate(hiredate);
                emp.setSal(sal);
                emp.setComm(comm);
                emp.setDeptno(deptno);
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return null;
 }
```

总结：

> \1.  将一行中多个零散数据进行整理。
>
> \2.  通过Entity的规则将表中的数据进行对象的封装。
>
> \3.  表名=类名；列名=属性名；提供各属性的get、set方法。
>
> \4.  提供无参构造方法（视情况添加有参构造方法）。

### 3  DAO模式

#### 3.1 工具类封装

案例实现：实现emp表的查询、添加、删除、修改

##### 3.1.1 封装DbUtils

由于多个地方都需要使用数据库连接和释放，所以把功能封装到工具类中DbUtils

四个功能：1注册驱动   2 获取连接  3 释放资源 

```java
public class DbUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	static{
	
		try {			
			driver="com.mysql.jdbc.Driver";
          	 url="jdbc:mysql://localhost:3306/mydb1";
          	 user="root";
          	 password="1234";
			//加载驱动
			Class.forName(driver);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);
	}
	
	/**
	 * 释放资源
	 * @param rs
	 * @param stat
	 * @param conn
	 */
	public static void release(ResultSet resultSet,Statement stat,Connection conn){
		try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}
}
```

**1.1.2 优化DbUtils工具类：把数据库连接信息封装到Properties文件中**

项目下创建的db.properties   error方式

src下创建db.properties      could not initialize class com.qf.www.dbutils

```java
			Properties properties=new Properties();
InputStream is=DbUtils.class.getClassLoader().getResourceAsStream("database.properties");
			properties.load(is);
			//初始化参数
			driver=properties.getProperty("driver");
			url=properties.getProperty("url");
			user=properties.getProperty("user");
			password=properties.getProperty("password");
```

#### 3.2 DAO设计模式

DAO(Database Access Object 数据库访问对象)

为了降低耦合性，提出了DAO封装数据库操作的设计模式。

它可以实现业务逻辑与数据库访问相分离。相对来说，数据库是比较稳定的，其中DAO组件依赖于数据库系统，提供数据库访问的接口，隔离了不同的数据库实现。

DAO模式的组成部分

​	1 DAO接口（主要包含 添加 修改 查询 删除方法）

​	2 DAO实现类

​	3 实体类  （domain、beans、entity、pojo、model）

​		--作用：用在数据访问代码和业务逻辑代码之间通过实体类来传输数据

​		--实体类特征：

​			◦属性一般使用private修饰

​			◦提供public修饰的getter/setter方法

​			◦实体类提供无参构造方法，根据业务提供有参构造

​			◦实现java.io.Serializable接口，支持序列化机制

​	4 数据库连接和关闭工具类

设计的包名 :  

  entity、存放实体类

   utils  存放工具类

   dao  存放接口

   dao.impl  存放实现类

使用DAO设计模式实现emp表的查询、添加、删除、修改

Employee类

```java
/**
 * 员工类
 * 实体类
 * @author wgy
 *
 */
public class Emp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 373910607014836778L;
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private double sal;
	private double comm;
	private int deptno;
	
	public Emp() {
		// TODO Auto-generated constructor stub
	}

	public Emp(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate="
				+ hiredate + ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
	}
	
}
```

接口：

```java
public interface EmpDao {
	
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

实现类

```java
public class EmpDaoImpl implements EmpDao{
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
	@Override
	public List<Emp> findAll() {
		ArrayList<Emp> emps=new ArrayList<Emp>();
		//1获取连接
		
		try {
			conn=DbUtils.getConnection();
			pstat=conn.prepareStatement("select * from emp;");
			rs=pstat.executeQuery();
			while(rs.next()){
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				int mgr=rs.getInt("mgr");
				Date date=rs.getDate("hiredate");
				double sal=rs.getDouble("sal");
				double comm=rs.getDouble("comm");
				int deptno=rs.getInt("deptno");
				
				Emp emp=new Emp(empno, ename, job, mgr, date, sal, comm, deptno);
				emps.add(emp);
			}
			return emps;
		} catch (Exception e) {
			throw new RuntimeException("查询emp失败");
		} finally {
			DbUtils.closeAll(rs, pstat, conn);
		}
		
	}

	@Override
	public void update(Emp emp) {
		conn=DbUtils.getConnection();
        String sql = "update emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? where empno = ?";
        try {
        	pstat = conn.prepareStatement(sql);
            pstat.setObject(1,emp.getEname());
            pstat.setObject(2,emp.getJob());
            pstat.setObject(3,emp.getMgr());
            pstat.setObject(4,emp.getHiredate());
            pstat.setObject(5,emp.getSal());
            pstat.setObject(6,emp.emp.getComm());
            pstat.setObject(7,emp.getDeptno());
            pstat.setObject(8,emp.getEmpno());
            pstat.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("修改emp失败");
        }finally {
			DbUtils.closeAll(rs, pstat, conn);
		}
	}

	@Override
	public void delete(int empno) {
		conn=DbUtils.getConnection();
        String sql = "delete from emp where empno = ?";
        try {
        	pstat = conn.prepareStatement(sql);
            pstat.setObject(1,empno);
            pstat.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("删除emp失败");
        }finally {
			DbUtils.closeAll(rs, pstat, conn);
		}
	}

	@Override
	public void add(Emp emp) {
		conn=DbUtils.getConnection();
        String sql = "insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(?,?,?,?,?,?,?,?)";
        try {
        	pstat = conn.prepareStatement(sql);
            pstat.setObject(1,emp.getEmpno());
            pstat.setObject(2,emp.getEname());
            pstat.setObject(3,emp.getJob());
            pstat.setObject(4,emp.getMgr());
            pstat.setObject(5,emp.getHiredate());
            pstat.setObject(6,emp.getSal());
            pstat.setObject(7,emp.emp.getComm());
            pstat.setObject(8,emp.getDeptno());
            pstat.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("新增emp失败");
        }finally {
			DbUtils.closeAll(rs, pstat, conn);
		}
	}
}
```















