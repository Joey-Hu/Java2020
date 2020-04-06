package com.hh.www.service.impl;

import com.hh.www.dao.UserDao;
import com.hh.www.dao.impl.UserDaoImpl;
import com.hh.www.entity.User;
import com.hh.www.service.UserService;

/**
 * @author: huhao
 * @time: 2020/4/6 11:26
 * @desc:
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User checkUser(String username, String userpassword) {


        User user = new User();

        user.setUsername(username);
        user.setUserpassword(userpassword);

        return userDao.checkUser(user);
    }
}
