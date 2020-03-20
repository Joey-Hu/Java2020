package jdbc1;

import java.sql.*;
import java.util.Scanner;

/**
 * @author: huhao
 * @time: 2020/3/20 12:31
 * @desc:
 */
public class TestJdbcForLogIn {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名：");
        String username = sc.nextLine();

        System.out.println("请输入密码：");
        String password = sc.nextLine();

        // 连接数据库

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_test?serverTimezone=UTC", "root", "hh123456");
            statement = connection.createStatement();
            String sql = "select * from users where username='"+username+"' and userpassword='"+password+"'";
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
}
