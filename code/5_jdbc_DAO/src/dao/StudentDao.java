package dao;

import entity.Student;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/3/21 17:25
 * @desc: 数据访问层接口，定义了访问数据库的方法
 */
public interface StudentDao {

    /**
     * 查询所有
     * @return 返回查询到的数据列表
     */
    List<Student> getAllStudent();

    /**
     * 查询单个
     * @param sid
     * @return 返回查询到的数据
     */
    Student getStudent(int sid);

    /**
     * 增加
     * @param student
     * @return 影响的行数
     */
    int add(Student student);

    /**
     * 修改
     * @param student
     * @return 影响的行数
     */
    int update(Student student);

    /**
     * 删除
     * @param sid
     * @return 影响的行数
     */
    int delete(int sid);



}
