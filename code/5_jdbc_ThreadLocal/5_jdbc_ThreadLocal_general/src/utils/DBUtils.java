package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/3/26 16:48
 * @desc:
 */

/*
工具类不要实例化！
工具类全是公开静态方法！
工具类只负责提供服务！多余的事不属于工具类
工具类：获取连接、释放连接、增删改、查询、事务
 */
public class DBUtils {

    private static DruidDataSource dataSource;
    private static Connection connection;

    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = DBUtils.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(resourceAsStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getDataSource(){
        return dataSource;
    }

    /**
     * 获得连接
     */
    public static Connection getConnection() {
        try {
            connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增、删、改通用方法      sql语句   ===   可变长参数0~n
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object... params){

        connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i=0; i<params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            int i = preparedStatement.executeUpdate();
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询泛型方法
     * @param sql
     * @param rowMapper
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> commonsSelect(String sql, RowMapper<T> rowMapper, Object... params){

        connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //由于不知道查询到的结果是什么类型的对象，所以使用泛型规范结果
        //查询的是单个还是多个
        List<T> list = new ArrayList<T>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i =0;i<params.length;i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                list.add(rowMapper.getRow(resultSet));
            }
            return list;
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
