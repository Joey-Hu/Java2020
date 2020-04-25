package com.hh.www.web;

import com.hh.www.service.UserService;
import com.hh.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @author: huhao
 * @time: 2020/4/25 15:48
 * @desc:
 */
@WebServlet(name = "GetAllServlet", value = "/getAllUsers")
public class GetAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");
//        response.setContentType("ap");
        UserService userService = new UserServiceImpl();
        String allUsers = userService.getAllUsers();
        PrintWriter writer = response.getWriter();

        if(allUsers != null){
            writer.print(allUsers);
        }else {
            writer.print("error");
        }


    }
}
