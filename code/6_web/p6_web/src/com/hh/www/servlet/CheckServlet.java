package com.hh.www.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: huhao
 * @time: 2020/4/23 10:05
 * @desc:
 */
@WebServlet(name = "CheckServlet", value = "/check")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        PrintWriter writer = response.getWriter();

        // 连接数据库验证   参照前面代码

        if(username.equals("hh")){
            writer.print("1");
        }else{
            writer.print("0");
        }

    }
}
