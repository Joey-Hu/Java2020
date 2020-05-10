package com.hh.web;

import com.hh.dao.AccountDao;
import com.hh.factory.BeanFactory;
import com.hh.service.AccountService;
import com.hh.service.impl.AccountServiceImpl;

/**
 * @author: huhao
 * @time: 2020/4/26 12:16
 * @desc:
 */
public class Client {

    public static void main(String[] args) {

        // AccountService accountService = new AccountServiceImpl();

        for (int i = 0; i < 5; i++) {
            // 多例模式
            AccountService accountService = (AccountService) BeanFactory.getBean("accountService");

            System.out.println(accountService);
            accountService.saveAccount();
        }

        // 单例模式实现


    }
}
