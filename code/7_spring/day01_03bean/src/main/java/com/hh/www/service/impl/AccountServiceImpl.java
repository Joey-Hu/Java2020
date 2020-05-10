package com.hh.www.service.impl;

import com.hh.www.service.IAccountService;

/**
 * @author: huhao
 * @time: 2020/5/1 10:45
 * @desc: 账户业务层实现类
 */
public class AccountServiceImpl implements IAccountService{

    // 构造方法
    public  AccountServiceImpl(){
        System.out.println("对象创建了");
    }

    public void saveAccount() {

        System.out.println("service中的saveAccount方法执行了。。。");

    }
}
