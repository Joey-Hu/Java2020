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
