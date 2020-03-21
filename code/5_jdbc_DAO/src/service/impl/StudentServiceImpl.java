package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoimpl;
import entity.Student;
import service.StudentService;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/3/21 17:53
 * @desc: 业务层  避免用户直接访问DAO
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoimpl();

    @Override
    public List<Student> getAllStudent() {
        return studentDao.getAllStudent();
    }

    @Override
    public Student getStudent(int sid) {
        return studentDao.getStudent(sid);
    }

    @Override
    public int add(Student student) {
        return studentDao.add(student);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public int delete(int sid) {
        return studentDao.delete(sid);
    }
}
