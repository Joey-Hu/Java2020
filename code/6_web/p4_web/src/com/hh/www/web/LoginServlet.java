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
        String pi = request.getParameter("pageIndex");
        String ps = request.getParameter("pageSize");

        // 判断用户是否存在（后期再写）

        EmpService empService = new EmpServiceImpl();
        // 获取全部用户
//        List<Emp> allEmp = empService.getAllEmp();

        int pageIndex = 0;
        int pageSize = 0;

        if(pi == null){
            pageIndex = 1;
        }else{
            pageIndex = Integer.valueOf(pi);
        }

        if(ps == null){
            pageSize = 3;
        } else {
            pageSize = Integer.valueOf(ps);
        }

        if(pageSize!=3){
            pageSize=3;
        }

        List<Emp> allEmp = empService.getEmpByPage(pageIndex, pageSize);
        int empSize = (int)empService.getEmpSize();
        int total = empSize % pageSize == 0 ? empSize / pageSize : (empSize / pageSize) + 1;

        if(allEmp != null){
            request.setAttribute("list", allEmp);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("total", total);

            // 重定向到getAll.jsp来显示结果
            request.getRequestDispatcher("/getAll.jsp").forward(request, response);
        }
    }
}
