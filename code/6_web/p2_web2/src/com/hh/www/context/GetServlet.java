package com.hh.www.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

/**
 * @author: huhao
 * @time: 2020/4/6 8:47
 * @desc:
 */
@WebServlet(name = "GetServlet", value = "/gs")
public class GetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();

        Object username = session.getAttribute("username");
        Object password = servletContext.getAttribute("password");

        servletContext.removeAttribute("password");
        //把servletContext存储的数据移除了。当前上下文仍旧存在
        System.out.println(username + ": " + password);

        String username1 = servletContext.getInitParameter("username");
        String password1 = servletContext.getInitParameter("password");

        System.out.println(username1 + ": " + password1);


    }
}
