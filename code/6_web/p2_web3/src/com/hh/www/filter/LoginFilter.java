package com.hh.www.filter;

import com.hh.www.entity.User;
import com.hh.www.service.UserService;
import com.hh.www.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/6 11:52
 * @desc:
 */
@WebFilter(filterName = "LoginFilter", value = "/login.html")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");
        if(user != null){
            response.sendRedirect("/p2_web3/welcome.html");
        }else {
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if(name.equals("userInfo")){
                        String value = cookie.getValue();
                        String[] split = value.split("#");
                        UserService userService = new UserServiceImpl();
                        User user1 = userService.checkUser(split[0], split[1]);

                        if(user1 != null){
                            response.sendRedirect("/p2_web3/welcome.html");
                        }else {
                            response.sendRedirect("/p2_web3/fail.html");
                        }
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
