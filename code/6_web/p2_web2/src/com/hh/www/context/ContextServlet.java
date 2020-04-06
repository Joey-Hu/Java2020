package com.hh.www.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/6 8:33
 * @desc:
 */
@WebServlet(name = "ContextServlet", value = "/cs")
public class ContextServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.获取Context -- 通过request (常用)
        ServletContext servletContext = request.getServletContext();

        HttpSession session = request.getSession();
        session.setAttribute("username", "hh");
        servletContext.setAttribute("password", "1234");

        // 2. 获取当前项目发布路径
        System.out.println("当前项目发布路径：" + servletContext.getRealPath("/"));

        // 获取上下文路径
        System.out.println("上下文路径：" + servletContext.getContextPath());

        // 获取 servlet 信息
        System.out.println("获取Servlet信息：" + servletContext.getServerInfo());





    }
}
