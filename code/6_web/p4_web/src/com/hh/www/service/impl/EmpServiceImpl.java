package com.hh.www.service.impl;

import com.hh.www.beans.Emp;
import com.hh.www.dao.EmpDao;
import com.hh.www.dao.impl.EmpDaoImpl;
import com.hh.www.service.EmpService;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 15:42
 * @desc:
 */
public class EmpServiceImpl implements EmpService {

    private EmpDao empDao = new EmpDaoImpl();


    @Override
    public List<Emp> getAllEmp() {
        return empDao.getAllEmp();
    }

    @Override
    public Emp getEmp(int empno) {
        return empDao.getEmp(empno);
    }

    @Override
    public int insertEmp(int empno, String ename, String job, int mgr, String hiredate, double sal, double comm, int deptno) {
        Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
        return empDao.insertEmp(emp);
    }

    @Override
    public int deleteEmp(int empno) {
        return empDao.deleteEmp(empno);
    }

    @Override
    public int updateEmp(int empno, String ename, String job, int mgr, String hiredate, double sal, double comm, int deptno) {
        Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
        return empDao.updateEmp(emp);
    }

    @Override
    public List<Emp> getEmpByPage(int pageIndex, int pageSize) {
        pageIndex = (pageIndex-1) * pageSize;
        List<Emp> empList = empDao.getEmpByPage(pageIndex, pageSize);
        return empList;
    }

    @Override
    public long getEmpSize() {
        return empDao.getEmpSize();
    }
}
