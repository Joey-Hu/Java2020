package com.hh.www.servlet;

import com.hh.www.utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author: huhao
 * @time: 2020/4/13 9:26
 * @desc:
 */
@WebServlet(name = "FileListServlet", value = "/filelist")
public class FileListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 获得下载的目录路径
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        // 2. Map集合   键--UUID   值---原名称
        HashMap<String, String> filenames = new HashMap<String, String>();
        // 3. 调用工具类的方法，把所有文件的名字、文件的UUID后的名字都封装在集合里
        UploadUtils.GetFileList(realPath, filenames);
        // 4. 存在作用域里
        request.setAttribute("fileList", filenames);
        // 5. 转发
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }
}
