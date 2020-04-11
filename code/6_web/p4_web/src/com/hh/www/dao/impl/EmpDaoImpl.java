package com.hh.www.dao.impl;

import com.hh.www.beans.Emp;
import com.hh.www.dao.EmpDao;
import com.hh.www.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 15:23
 * @desc:
 */
public class EmpDaoImpl implements EmpDao {

    private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<Emp> getAllEmp() {

        try {
            List<Emp> list = queryRunner.query("SELECT * FROM emp;", new BeanListHandler<Emp>(Emp.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Emp getEmp(int empno) {

        try {
            return queryRunner.query("SELECT * FROM emp WHERE empno=?;", new BeanHandler<Emp>(Emp.class), empno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertEmp(Emp emp) {
        Object[] params = {emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(),
                emp.getComm(), emp.getDeptno()};

        try {
            int update = queryRunner.update("INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?);", params);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteEmp(int empno) {

        try {
            int update = queryRunner.update("DELETE FROM emp WHERE empno=?", empno);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateEmp(Emp emp) {

        Object[] params = {emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(), emp.getComm(),
                emp.getDeptno(), emp.getEmpno()};
        try {
            int update = queryRunner.update("UPDATE emp SET ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?, " +
                    "deptno=? WHERE empno=?", params);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
