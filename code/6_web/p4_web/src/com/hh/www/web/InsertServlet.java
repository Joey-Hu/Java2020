package com.hh.www.web;

import com.hh.www.service.EmpService;
import com.hh.www.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/11 20:45
 * @desc:
 */
@WebServlet(name = "InsertServlet", value = "/insert")
public class InsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String empno= request.getParameter("empno");
        String ename = request.getParameter("ename");
        String job = request.getParameter("job");
        String mgr = request.getParameter("mgr");
        String hiredate = request.getParameter("hiredate");
        String sal = request.getParameter("sal");
        String comm = request.getParameter("comm");
        String deptno = request.getParameter("deptno");

        EmpService empService = new EmpServiceImpl();
        int i = empService.insertEmp(Integer.valueOf(empno), ename, job, Integer.valueOf(mgr), hiredate, Double.valueOf
                (sal), Double.valueOf(comm), Integer.valueOf(deptno));
        if(i > 0){
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
