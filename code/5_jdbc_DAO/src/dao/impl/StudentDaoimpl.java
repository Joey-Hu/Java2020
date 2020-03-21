package dao.impl;

import dao.StudentDao;
import entity.Student;
import utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/3/21 17:34
 * @desc:
 */
public class StudentDaoimpl implements StudentDao{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Student> getAllStudent() {

        Student student = null;
        List<Student> studentList = new ArrayList<Student>();

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM student;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int sid = resultSet.getInt("sid");
                String sname = resultSet.getString("sname");
                int sage = resultSet.getInt("sage");
                String ssex = resultSet.getString("ssex");
                Date birthday = resultSet.getDate("birthday");
                double sscore = resultSet.getDouble("sscore");

                student = new Student(sid, sname, sage, ssex, birthday, sscore);
                studentList.add(student);
            }
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }

        return null;
    }

    @Override
    public Student getStudent(int sid) {

        Student student = null;
        connection = DBUtil.getConnection();
        String sql = "SELECT * FROM student WHERE sid=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, sid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int sid1 = resultSet.getInt("sid");
                String sname = resultSet.getString("sname");
                int sage = resultSet.getInt("sage");
                String ssex = resultSet.getString("ssex");
                Date birthday = resultSet.getDate("birthday");
                double sscore = resultSet.getDouble("sscore");

                student = new Student(sid1, sname, sage, ssex, birthday, sscore);
            }
            return student;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public int add(Student student) {

        connection = DBUtil.getConnection();
        String sql = "INSERT INTO student(sid, sname, sage, ssex, birthday, sscore) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, student.getSid());
            preparedStatement.setObject(2, student.getSage());
            preparedStatement.setObject(3, student.getSid());
            preparedStatement.setObject(4, student.getSid());
            preparedStatement.setObject(5, student.getSid());
            preparedStatement.setObject(6, student.getSid());


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public int delete(int sid) {
        return 0;
    }
}
