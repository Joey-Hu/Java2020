package com.hh.www.dao.impl;

import com.hh.www.dao.UserDao;
import com.hh.www.entity.User;
import com.hh.www.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/25 15:41
 * @desc:
 */
public class UserDaoImpl implements UserDao {

    private QueryRunner queryRunner =  new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<User> getAllUsers() {

        try {
            return queryRunner.query("SELECT * FROM users;", new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
