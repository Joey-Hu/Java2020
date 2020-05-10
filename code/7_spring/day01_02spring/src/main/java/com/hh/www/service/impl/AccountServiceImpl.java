package com.hh.www.service.impl;

import com.hh.www.dao.IAccountDao;
import com.hh.www.dao.impl.AccountDaoImpl;
import com.hh.www.service.IAccountService;

/**
 * @author: huhao
 * @time: 2020/5/1 10:45
 * @desc: 账户业务层实现类
 */
public class AccountServiceImpl implements IAccountService{

    private IAccountDao accountDao = new AccountDaoImpl();

    public void saveAccount() {

        accountDao.saveAccount();

    }
}
