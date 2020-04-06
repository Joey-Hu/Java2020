package com.hh.www.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/6 16:30
 * @desc:
 */
@WebServlet(name = "DirtyWordServlet", value = "/dirty")
public class DirtyWordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        // 此时的 request 是过滤器封装的request
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");

        response.getWriter().println(username+": "+ nickname);

    }
}
