package com.hh.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/1 17:22
 * @desc:
 */
public class MyServlet implements Servlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        // 响应给浏览器的是 html 页面，所以要指定响应给浏览器页面的编码
        servletResponse.setContentType("text/html;charset=utf-8");
        servletResponse.getWriter().println("前十大My first Servlet.");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
