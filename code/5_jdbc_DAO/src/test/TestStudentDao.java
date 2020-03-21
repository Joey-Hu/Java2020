package test;

import entity.Student;
import service.impl.StudentServiceImpl;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/3/21 17:35
 * @desc:
 */
public class TestStudentDao {

    public static void main(String[] args) {

        StudentServiceImpl ssi = new StudentServiceImpl();
        List<Student> allStudent = ssi.getAllStudent();

        for (Student student : allStudent) {
            System.out.println(student);
        }

        System.out.println("=======查询单个=======");
        Student student = ssi.getStudent(1);
        System.out.println(student);


    }
}
