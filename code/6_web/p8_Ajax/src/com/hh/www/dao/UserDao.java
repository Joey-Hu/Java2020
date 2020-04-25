package com.hh.www.dao;

import com.hh.www.entity.User;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/25 15:40
 * @desc:
 */
public interface UserDao {

    List<User> getAllUsers();
}
