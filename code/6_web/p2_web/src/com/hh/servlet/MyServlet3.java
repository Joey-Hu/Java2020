package com.hh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/2 11:08
 * @desc:
 */
@WebServlet(name = "myServlet3", value = {"/myServlet3", "/m3", "/mm3"}, loadOnStartup = -1)
public class MyServlet3 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("初始化。。。");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("注解配置。。。");
    }
}
