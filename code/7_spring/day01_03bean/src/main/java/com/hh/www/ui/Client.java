package com.hh.www.ui;

import com.hh.www.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: huhao
 * @time: 2020/5/1 10:48
 * @desc:
 */
public class Client {

    public static void main(String[] args) {

        // 1. 获取核心容器对象
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");

        // 2. 根据 id 获取 Bean 对象
        IAccountService accountService = classPathXmlApplicationContext.getBean("accountService", IAccountService.class);

        System.out.println(accountService);

        accountService.saveAccount();
    }
}
