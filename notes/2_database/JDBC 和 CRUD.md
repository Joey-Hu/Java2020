## JDBC 和 CRUD

### 1. 关于JDBC

JDBC（Java Database Connectivity，Java 连接数据库）是一种用于执行 SQL 语句的 Java API，可以为多种关系数据库提供统一访问，它是由一组用 Java 语言编写的类和接口组成。JDBC 提供了一种基准，据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序。

JDBC 库包括通常与数据库使用相关的API：

* 连接数据库
* 创建 SQL 或 MySQL 语句
* 在数据库中执行 SQL 或 MySQL 查询
* 查看和修改生成的记录

#### 1.1 JDBC 体系结构

JDBC 体系结构由两层组成：

* JDBC：提供了应用程序到数据库的连接规范（Java 提供）。
* JDBC驱动程序：连接数据库的驱动程序的实现（数据库厂商提供）。

#### 1.2 JDBC 核心组件

**DriverManager：** 此类数据库驱动程序列表。使用通信协议将来自 Java 程序的连接请求与适当的数据库驱动程序匹配。
    
**Driver：** 此接口处理与数据库服务器的通信，很少直接与 Driver 对象进行交互，而是通过 DriverManager 对象来管理这种类型的对象。

**Connection：** 该接口具有用于连接数据库的所有方法。连接对象表示通信上下文，数据库的所有通信仅通过连接对象。

**Statement：** 使用从此接口创建的对象将 SQL 语句提交到数据库。除了执行存储过程外，一些派生接口还接受参数。

**ResultSet：** 在使用 Statement 对象执行 SQL 查询后，这些对象保存从数据库中检索的对象。它作为一个迭代器，允许我们移动其数据。

**SQLException：** 此类处理数据库应用程序中发生的任何异常。

### 2. CRUD 语法

CREATE，READ，UPDATE，and DELETE 通常被称为CRUD操作。

### 3. JDBC 初始

#### 3.1 JDBC 使用步骤

构建 JDBC 应用程序设计以下6个步骤：

* 导入 JDBC 的驱动包：下载包含数据库编程所需的JDBC的 jar 包；
	* 导入 jar 包，在项目下创建 lib 目录，把 mysql 的 jdbc 包放入此目录，右键 lib 文件夹，选择 add as library...，选择 Project Library。 
* 注册 JDBC 驱动程序：初始化驱动程序，打开与数据库的通信通道，Class.forName()；
* 创建连接：DriverManager.getConnection() 创建一个 Connection 对象；
* 执行查询：使用 StateMent 对象提交 SQL 到数据库；
* 从结果集中提取数据：ResultSet.getxxx() 方法从结果集中检索对象；
* 释放资源：明确地关闭所有数据库资源，不依赖于 JVM 的垃圾回收。

### 4. JDBC 执行 SQL 语句

一旦获得连接，我们可以与数据库进行交互。JDBC Statement 和 PreparedStatement 接口定义了能够发送 SQL 命令并从数据库接受数据的方法和属性。

* Statement：适合在运行时使用静态 SQL 语句时使用，该接口不能接受参数
* PreparedStatement：多次使用 SQL 语句时使用，可以接受输入参数

创建 statement 对象后，有以下三种执行方法：
- **boolean execute（String SQL）**：如果可以检索到ResultSet对象，则返回一个布尔值true; 否则返回false。使用此方法执行SQL DDL语句或需要使用真正的动态SQL时。
- **int executeUpdate（String SQL）**：返回受SQL语句执行影响的行数。使用此方法执行预期会影响多个行的SQL语句，例如INSERT，UPDATE或DELETE语句。
- **ResultSet executeQuery（String SQL）**：返回一个ResultSet对象。当您希望获得结果集时，请使用此方法，就像使用SELECT语句一样。

#### 4.1 ResultSet

ResultSet对象维护指向结果集中当前行的游标。术语“结果集”是指包含在ResultSet对象中的行和列数据。

如果没有指定任何ResultSet类型，您将自动获得一个TYPE_FORWARD_ONLY。

示范用例1：

```java
package jdbc1;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: huhao
 * @time: 2020/3/20 10:16
 * @desc:
 */
public class TestJdbc {

    public static void main(String[] args) {

        Connection conn = null;
        Statement statement = null;
        
        try {
            // 1. 触发注册驱动 DriverManager.registerDiver()
            /*
            DriverManager.registerDriver(new Driver());  不推荐：触发了两次注册驱动（参考源码）
            new Driver(); 不推荐：创建了两个驱动对象（参考源码）
            */
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 创建connection对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_sqltest?serverTimezone=UTC", "root", "hh123456");
            System.out.println(conn.toString());

            // 3. 创建执行SQL语句对象
            statement = conn.createStatement();

            // 4. sql语句
            // DDL 语句
            // String sql = "CREATE TABLE IF NOT EXISTS test(id int PRIMARY KEY, name VarChar(20) not null)charSet=utf8;";

            // DML 语句 Insert
            String sql2 = "INSERT INTO test VALUES(3, '王五');";

            // DML 语句 Delete
            String sql3 = "DELETE FROM test WHERE id=3;";

            // DML 语句 Update
            String sql4 = "UPDATE test set name='张强' WHERE id=2;";


            // 5. 执行DDL execute();
            // 执行DML
            // boolean result = statement.execute(sql);
            int result = statement.executeUpdate(sql4);
            System.out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 关闭释放资源
            try {
                if(statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


```

示范用例2：

```java
package jdbc1;

import java.sql.*;

/**
 * @author: huhao
 * @time: 2020/3/20 11:26
 * @desc:
 */
public class JdbcForQuery {

    public static void main(String[] args){

        Connection connection = null;
        Statement statement = null;

        try {
            // 1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 获得连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_sqltest?serverTimezone=UTC", "root", "hh123456");

            // 3. 创建Statement
            statement = connection.createStatement();

            // 4. sql语句
            String sql = "Select * from teacher;";

            // 5. 执行
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("Tno");
                String name = resultSet.getString("Tname");
                System.out.println(id + "----" + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 6. 关闭资源
            try {
                if(statement != null){ // 避免了NullPointerException
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
}
```

