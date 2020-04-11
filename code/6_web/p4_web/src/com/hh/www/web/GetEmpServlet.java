package com.hh.www.web;

import com.hh.www.beans.Dept;
import com.hh.www.beans.Emp;
import com.hh.www.service.DeptService;
import com.hh.www.service.EmpService;
import com.hh.www.service.impl.DeptServiceImpl;
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
 * @time: 2020/4/11 18:06
 * @desc:
 */
@WebServlet(name = "GetEmpServlet", value = "/getEmp")
public class GetEmpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String empno = request.getParameter("empno");
        EmpService empService = new EmpServiceImpl();
        Emp emp = empService.getEmp(Integer.valueOf(empno));
        DeptService deptService = new DeptServiceImpl();
        List<Dept> allDept = deptService.getAllDept();

        if(emp != null && allDept != null){
            request.setAttribute("emp", emp);
            request.setAttribute("depts", allDept);
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        }


    }
}
