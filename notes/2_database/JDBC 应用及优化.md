## JDBC 应用及优化

### 1. 封装工具类

DbUtils类功能：1 注册驱动  2 获取连接   3释放资源 

#### 1.1 重用性方案：

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

