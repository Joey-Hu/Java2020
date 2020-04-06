package com.hh.www.service;

import com.hh.www.entity.User;

/**
 * @author: huhao
 * @time: 2020/4/6 11:24
 * @desc:
 */
public interface UserService {

    User checkUser(String username, String userpassword);
}
