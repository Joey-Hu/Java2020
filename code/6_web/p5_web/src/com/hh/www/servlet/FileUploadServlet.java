package com.hh.www.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.PageContext;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author: huhao
 * @time: 2020/4/12 11:42
 * @desc:
 */
@WebServlet(name = "FileUploadServlet", value = "/fus")
@MultipartConfig(maxFileSize = 1024*1024*100, maxRequestSize = 1024*1024*300)
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 请求表达式中有中文
        request.setCharacterEncoding("utf-8");
        // 显示
        response.setContentType("text/html;charset=utf-8");

        // 3. 获取文件上传路径
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(realPath);
        //如果upload不存在
        if(!file.exists()){
            file.mkdirs();
        }

        //文件上传的实现步骤
        //1.通过请求获得多分部的集合
        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            String fileName = part.getSubmittedFileName();
            //2.在part可以获得当前上传的是文件还是普通表单
            if(fileName != null){
                // 上传文件
                //文件----->上传(路径+文件名称)File.separator会自动解析当前JDK运行平台的分割符
                part.write(file + File.separator + fileName);
                //清除临时缓冲区
                part.delete();
            }else{
                //....打印或者后续改成上传数据库  调用service
                String name = part.getName();
                String parameter = request.getParameter(name);
                System.out.println(parameter);
            }
        }

    }
}
