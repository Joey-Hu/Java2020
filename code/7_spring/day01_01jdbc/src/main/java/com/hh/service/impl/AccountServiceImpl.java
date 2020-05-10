package com.hh.service.impl;

import com.hh.dao.AccountDao;
import com.hh.dao.impl.AccountDaoImpl;
import com.hh.factory.BeanFactory;
import com.hh.service.AccountService;

/**
 * @author: huhao
 * @time: 2020/4/26 12:11
 * @desc: 账户业务层的接口实现类
 */
public class AccountServiceImpl implements AccountService {

    // private AccountDao accountDao = new AccountDaoImpl();

    /**
     * 使用工厂类的方法获取实体类
     */
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    public void saveAccount() {

        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
