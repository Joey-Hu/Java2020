package com.hh.www.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/4/25 15:29
 * @desc:
 */
public class DruidUtils {

    private static DruidDataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            //获取druid.properties配置文件的路径，（DruidUtils）是"druid.properties"文件的兄弟路径，
            // getClassLoader()获取的是<他们>的运行时路径
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("database.properties"));
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getDataSource(){
        return dataSource;
    }
}
