package dao;

import entity.Dept;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/3/26 16:56
 * @desc:
 */
public interface DeptDao {

    List<Dept> getAllDept();

    int add(Dept dept);

    int update(Dept dept);

    int delet(int deptNo);
}
