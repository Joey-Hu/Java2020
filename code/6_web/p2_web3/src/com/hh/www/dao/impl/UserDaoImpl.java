package com.hh.www.dao.impl;

import com.hh.www.dao.UserDao;
import com.hh.www.entity.User;
import com.hh.www.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author: huhao
 * @time: 2020/4/6 11:19
 * @desc:
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User checkUser(User user) {

        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        try {
            return queryRunner.query("SELECT * FROM users WHERE username=? AND userpassword=?;",
                    new BeanHandler<User>(User.class), user.getUsername(), user.getUserpassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
