package com.hh.www.dao;

import com.hh.www.beans.Emp;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 15:20
 * @desc:
 */
public interface EmpDao {

    List<Emp> getAllEmp();
    Emp getEmp(int empno);
    int insertEmp(Emp emp);
    int deleteEmp(int empno);
    int updateEmp(Emp emp);

    List<Emp> getEmpByPage(int pageIndex, int pageSize);
    long getEmpSize();
}
