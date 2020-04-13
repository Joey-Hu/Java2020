package com.hh.www.servlet;

import com.hh.www.utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author: huhao
 * @time: 2020/4/13 9:53
 * @desc:
 */
@WebServlet(name = "FileDownloadServlet", value = "/download")
public class FileDownloadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        //1.获取源文件带UUID的名称
        String filename = request.getParameter("filename");
        //2.截取出源文件的名称
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");

        int index = filename.indexOf("_");
        String realName = filename.substring(index + 1);

        // 3. 根据文件名再次推出存储路径
        String filePath = UploadUtils.createFilePath(realPath, realName);

        // 4. 根据路径和文件名读取文件
        FileInputStream fis = new FileInputStream(filePath + File.separator + filename);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(realName, "utf-8"));
        byte[] bytes = new byte[1024*1024*100];
        int len = 0;
        while((len = fis.read(bytes))!=-1){
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        outputStream.close();
        fis.close();
    }
}
