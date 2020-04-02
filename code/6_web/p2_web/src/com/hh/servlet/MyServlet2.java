package com.hh.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/2 10:27
 * @desc:
 */
public class MyServlet2 extends GenericServlet {
    /*
    可以直接继承父类方法，省略了4个方法 (init, getServletConfig, getServletInfo, Destroy)
    GenericServlet 抽象类与 Servlet 接口都是与协议无关的  --> HttpServlet
     */

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("提供服务。");
    }
}
