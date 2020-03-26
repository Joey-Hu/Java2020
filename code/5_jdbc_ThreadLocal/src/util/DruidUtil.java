package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/3/26 11:47
 * @desc:
 */
public class DruidUtil {

    private static DruidDataSource dataSource;
    private static Connection connection;

    /**
     * 线程---->同一个connection从头到关闭，
     * Thread --->ThreadLocal.ThreadMap--->entity---value
     * 仅仅是创建了！  底层MAP集合存的是当前线程对象和连接对象Connection
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {

        Properties properties = new Properties();
        InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(is);
            // 创建连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接池
     * @return
     */
    public static DruidDataSource getDruidDataSource(){

        return dataSource;
    }

    /**
     * 获得连接
     * @return
     */
    public static Connection getConnection(){

        try {
            //--从线程获取链接
            connection = threadLocal.get();
            if(connection == null){
                //第一次获取 创建一个连接 和当前的线程绑定
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 开启事务
     */
    public static void start(){

        connection = getConnection();
        System.out.println("开启："+connection.hashCode());
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交
     */
    public static void commit(){

        connection = getConnection();
        System.out.println("提交："+connection.hashCode());
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚
     */
    public static void rollBack(){

        connection = getConnection();
        System.out.println("回滚"+connection.hashCode());
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务关闭连接
     */
    public static void close(){
        connection = getConnection();
        System.out.println("关闭："+connection.hashCode());

        try {
            connection.close();    //只是关闭了连接！
            threadLocal.remove();    //把threadLocal里的线程对象移除掉！
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
