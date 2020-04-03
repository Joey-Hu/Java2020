package com.hh.www.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/4/3 10:38
 * @desc:
 */
public class DruidUtils {

    // 创建连接池对象
    private static DruidDataSource dataSource;

    static {

        // 创建连接池
        Properties properties = new Properties();
        InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(is);
            // 通过 Druid 连接池工厂创建一个连接池，自动解析 properties 文件中的键值对
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接池对象
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
            DruidPooledConnection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}


