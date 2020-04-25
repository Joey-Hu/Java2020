package com.hh.www.service.impl;

import com.alibaba.fastjson.JSON;
import com.hh.www.dao.UserDao;
import com.hh.www.dao.impl.UserDaoImpl;
import com.hh.www.entity.User;
import com.hh.www.service.UserService;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/25 15:45
 * @desc:
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public String getAllUsers() {

        List<User> allUsers = userDao.getAllUsers();
        String s = JSON.toJSONString(allUsers);
        return s;
    }
}
