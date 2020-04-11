package com.hh.www.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/4/11 15:06
 * @desc:
 */
public class DruidUtils {

    private static DruidDataSource dataSource;

    static {

        Properties properties = new Properties();
        try {
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("database.properties"));
            dataSource= (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
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
