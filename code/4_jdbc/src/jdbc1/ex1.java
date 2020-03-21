package jdbc1;

import utils.DBUtils;
import utils.DBUtilsPlatform;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author: huhao
 * @time: 2020/3/20 15:39
 * @desc: 输入学生ID号或出生日期查询该学生的全部信息
 */
public class ex1 {

    public static void main(String[] args) throws Exception {

        // 1. 输入学生ID号查询
        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入学生的id: ");
//        int id = sc.nextInt();

        // 2. 输入出生日期查询
        System.out.println("请输入学生的出生日期：");
        String birthday = sc.nextLine();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date birthday = simpleDateFormat.parse(rowBirthday);

        // 数据库操作
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBUtilsPlatform.getConnection();
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_test?serverTimezone=UTC", "root", "hh123456");
//            String sql = "SELECT * FROM student WHERE sid=?;";
            String sql = "SELECT * FROM student WHERE birthday=?;";
            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setObject(1, id);
            preparedStatement.setObject(1, birthday);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int sid = resultSet.getInt("sid");
                String sname = resultSet.getString("sname");
                int sage = resultSet.getInt("sage");
                String ssex = resultSet.getString("ssex");
                Date sbirthday = resultSet.getDate("birthday");
                double sscore = resultSet.getDouble("sscore");

                System.out.println(sid + "----" + sname + "----" + sage + "----" + ssex + "----" + sbirthday + "----" + sscore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtilsPlatform.closeAll(resultSet, preparedStatement, connection);
        }

    }
}
