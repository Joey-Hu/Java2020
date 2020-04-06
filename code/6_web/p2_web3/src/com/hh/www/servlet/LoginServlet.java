package com.hh.www.servlet;

import com.hh.www.entity.User;
import com.hh.www.service.UserService;
import com.hh.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/6 11:34
 * @desc: 处理登陆表单
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");

        if(username.equals("") || username.trim().length() == 0){
            response.getWriter().println("用户名不能为空。。。");
            return;
        }
        if(userpassword.equals("") || userpassword.trim().length() == 0){
            response.getWriter().println("密码不能为空。。。");
            return;
        }

        UserService userService = new UserServiceImpl();
        User user = userService.checkUser(username, userpassword);

        if(user != null){
            // 存session
            session.setAttribute("user", user);

            // 存cookie
            Cookie cookie = new Cookie("userInfo", username+"#"+userpassword);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            response.sendRedirect("/p2_web3/welcome.html");
        } else{
            response.sendRedirect("/p2_web3/fail.html");
        }

    }
}
