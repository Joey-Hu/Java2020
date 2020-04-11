package com.hh.www.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.hh.www.beans.Dept;
import com.hh.www.dao.DeptDao;
import com.hh.www.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 18:29
 * @desc:
 */
public class DeptDaoImpl implements DeptDao {

    private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<Dept> getAllDept() {

        try {
            return queryRunner.query("SELECT * FROM dept;", new BeanListHandler<Dept>(Dept.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
