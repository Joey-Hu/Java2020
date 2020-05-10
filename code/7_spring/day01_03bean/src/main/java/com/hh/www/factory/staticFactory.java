package com.hh.www.factory;

import com.hh.www.service.IAccountService;
import com.hh.www.service.impl.AccountServiceImpl;

/**
 * @author: huhao
 * @time: 2020/5/10 21:03
 * @desc:
 */
public  class staticFactory {

    public static IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
