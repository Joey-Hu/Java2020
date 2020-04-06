package com.hh.www.filter;

import com.hh.www.entity.User;
import com.hh.www.service.UserService;
import com.hh.www.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author: huhao
 * @time: 2020/4/6 11:52
 * @desc: 动态自动登陆
 */
@WebFilter(filterName = "LoginFilter", value = "/login.html")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //ServletRequest不能获取session、cookie，需要强转为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        //检查session中有没有用户信息
        User user = (User)session.getAttribute("user");
        if(user != null){
            response.sendRedirect("/p2_web3/welcome.html");
        }else {
            //如果session中没有，从Cookie中找autoLogin。可能是会话结束后再次访问，
            // 比如离开网站30min（session默认超时时间）后再次访问、关闭浏览器后重新打开再次访问。
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
                            if(user.getAccess() == 1){
                                // 管理员 进查询所有页面
                                response.sendRedirect("/p2_web3/welcome.html");
                            } else{
                                //用户 只进查询页面
                                response.sendRedirect("/p2_web3/welcome.html");
                            }

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
