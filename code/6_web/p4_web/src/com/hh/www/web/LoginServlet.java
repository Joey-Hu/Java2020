package com.hh.www.web;

import com.hh.www.beans.Emp;
import com.hh.www.service.EmpService;
import com.hh.www.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 15:56
 * @desc:
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 判断用户是否存在（后期再写）

        EmpService empService = new EmpServiceImpl();
        List<Emp> allEmp = empService.getAllEmp();

        if(allEmp != null){
            request.setAttribute("list", allEmp);
            // 重定向到getAll.jsp来显示结果
            request.getRequestDispatcher("/getAll.jsp").forward(request, response);
        }
    }
}
