package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/3/26 9:07
 * @desc:
 */
public class DruidUtil {

    /**
     * 1. 创建连接池对象
     */
    private static DruidDataSource dataSource;
    private static Connection connection;

    static {

        // 创建连接池
        Properties properties = new Properties();
        InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(is);
             // 通过 Druid 连接池工厂创建一个连接池，自动解析 properties 文件中的键值对
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得一个连接池
     * @return
     */
    public static DruidDataSource getDataSource(){

        return dataSource;
    }

    /**
     * 获得连接对象
     * @return
     */
    public static Connection getConnection(){

        try {
             connection = dataSource.getConnection();
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
