package com.hh.www.dao.impl;

import com.hh.www.dao.DeptDao;
import com.hh.www.entity.Dept;
import com.hh.www.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/3 11:08
 * @desc:
 */
public class DeptDaoImpl implements DeptDao {

    private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<Dept> getAll() {

        try {
            List<Dept> allDept = queryRunner.query("SELECT * FROM dept;", new BeanListHandler<Dept>(Dept.class));
            return allDept;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int insert(Dept dept) {

        try {
            int update = queryRunner.update("INSERT INTO dept(deptno, dname, loc) VALUES(?, ?, ?);", dept.getDeptno(), dept.getDname(), dept.getLoc());
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
