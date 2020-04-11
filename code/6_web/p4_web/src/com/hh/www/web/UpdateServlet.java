package com.hh.www.web;

import com.hh.www.service.EmpService;
import com.hh.www.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: huhao
 * @time: 2020/4/11 20:15
 * @desc:
 */
@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {

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
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse = null;
//        try {
//            parse = simpleDateFormat.parse(hiredate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        String sal = request.getParameter("sal");
        String comm = request.getParameter("comm");
        String deptno = request.getParameter("deptno");

        EmpService empService = new EmpServiceImpl();
        int i = empService.updateEmp(Integer.valueOf(empno), ename, job, Integer.valueOf(mgr), hiredate, Double.valueOf
                        (sal), Double.valueOf(comm), Integer.valueOf(deptno));
        if(i>0){
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
