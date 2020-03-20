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
