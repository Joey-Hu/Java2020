package com.hh.www.service;

import com.hh.www.beans.Emp;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 15:38
 * @desc:
 */
public interface EmpService {

    List<Emp> getAllEmp();
    Emp getEmp(int empno);
    int insertEmp(int empno, String ename, String job, int mgr,String hiredate, double sal, double comm, int deptno);
    int deleteEmp(int empno);
    int updateEmp(int empno, String ename, String job, int mgr,String hiredate, double sal, double comm, int deptno);
}
