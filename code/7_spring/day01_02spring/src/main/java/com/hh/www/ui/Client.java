package com.hh.www.ui;

import com.hh.www.service.IAccountService;
import com.hh.www.service.impl.AccountServiceImpl;

/**
 * @author: huhao
 * @time: 2020/5/1 10:48
 * @desc:
 *
 * 建立 spring 项目步骤：
 * 1 新建 maven 工程
 * 2 编写持久层和业务层逻辑代码
 * 3 pom 文件导入 spring 依赖    <dependencies>... spring-context ...</dependencies>
 * 4 xml 文件 添加约束xmlns
 * 4.1 把对象的创建交给 spring 管理 id class
 * 5 获取 spring 的 Ioc 核心容器，并根据 id 获取对象
 * 5.1 获取核心容器对象
 * 5.2 根据 id 获取 Bean 对象
 */
public class Client {

    public static void main(String[] args) {

        IAccountService accountService = new AccountServiceImpl();
        accountService.saveAccount();
    }
}
