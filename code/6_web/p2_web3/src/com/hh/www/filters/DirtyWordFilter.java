package com.hh.www.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/6 16:33
 * @desc:
 */
@WebFilter(filterName = "DirtyWordFilter", value = "/dirty")
public class DirtyWordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(new Dirty(request), servletResponse);

    }

    @Override
    public void destroy() {

    }

    static class Dirty extends HttpServletRequestWrapper {

        List<String> dirtywords = new ArrayList<String>();

        public Dirty(HttpServletRequest request)  {
            super(request);

            BufferedReader bufferedReader =null;
            // 加载文件
            try {

                // 获取文件真实路径
                ServletContext servletContext = request.getServletContext();
                String realPath = servletContext.getRealPath("/WEB-INF/classes/DirtyWord.txt");
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(realPath), "UTF-8"));
                String line = null;

                while((line = bufferedReader.readLine())!=null){
                    dirtywords.add(line);
                }
                bufferedReader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
//            dirtywords.add("王八蛋");
//            dirtywords.add("SB");
//            dirtywords.add("sb");
//            dirtywords.add("二狗子");
        }

        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);

            for (String dirtyword : dirtywords) {
                if(value.equals(dirtyword)){
                    value = value.replaceAll(dirtyword, "****");
                }
            }
            return value;
        }
    }
}
