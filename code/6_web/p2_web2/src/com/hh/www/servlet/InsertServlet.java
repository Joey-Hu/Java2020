package com.hh.www.servlet;

import com.hh.www.entity.Dept;
import com.hh.www.service.DeptService;
import com.hh.www.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: huhao
 * @time: 2020/4/3 12:12
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
        response.setContentType("text/html; charset=utf-8");

        // 获取数据
        String deptno = request.getParameter("deptno");
        String deptname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        PrintWriter writer = response.getWriter();

        // 验证数据格式
        if(deptno.equals("") || deptno.trim().length() == 0){
            writer.println("部门编号不能为空。");
            return;
        }
        if(deptname.equals("") || deptname.trim().length() == 0){
            writer.println("部门名称不能为空。");
            return;
        }
        if(loc.equals("") || loc.trim().length() == 0){
            writer.println("部门位置不能为空。");
            return;
        }

        // 转换数据格式
        Integer deptnos = Integer.valueOf(deptno);
        Dept dept = new Dept(deptnos, deptname, loc);

        // 调用service
        DeptService deptService = new DeptServiceImpl();
        int insert = deptService.Insert(dept);
        if(insert > 0){
//            writer.println("插入成功");
            //重定向此处/ 代表的是 localhost:8080  整个web容器
            response.sendRedirect("/p2_web2/success.html");
        }else{
//            writer.println("插入失败");
            response.sendRedirect("/p2_web2/fail.html");
        }


    }
}
