package com.hh.www.web;

import com.hh.www.beans.Dept;
import com.hh.www.service.DeptService;
import com.hh.www.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 20:39
 * @desc:
 */
@WebServlet(name = "GetDeptServlet", value = "/getDept")
public class GetDeptServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeptService deptService = new DeptServiceImpl();
        List<Dept> allDept = deptService.getAllDept();

        if(allDept != null){
            request.setAttribute("allDepts", allDept);
            request.getRequestDispatcher("/insert.jsp").forward(request, response);
        }

    }
}
