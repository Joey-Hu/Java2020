package com.hh.www.utils;

import com.sun.deploy.util.SyncAccess;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author: huhao
 * @time: 2020/4/12 15:23
 * @desc:
 */
public class UploadUtils {


    public static String createUniqueFileName(String fileName){

        //1.为每一个上传的文件生成唯一的文件名
        //重名容易发生覆盖！
        return UUID.randomUUID().toString().replaceAll("-", "" ) + "_" + fileName;
    }

    public static String createFilePath(String basePath, String fileName){

        // 2. 为防止一个一个目录下文件过多   hash算法打散
        //1.拿到文件名称的hash码
        int hashCode = fileName.hashCode();
        // 2.哈希码进行&运算，生成二级目录
        int path2 = hashCode&15;
        // 3. hashCode 右移4位生成三级目录
        int path3 = (hashCode>>4)&15;
        // 4.拼接路径
        String newPath = basePath + File.separator +path2 + File.separator + path3;
        File file = new File(newPath);
        if(!file.exists()){
            file.mkdirs();
        }
        return newPath;
    }

    public static void GetFileList(String path, HashMap<String, String> filenames){

        // 1. 路径当成文件对象
        File file = new File(path);
        //获取该目录（upload）下所有内容 包括了文件夹或文件
        File[] files = file.listFiles();
        for (File file1 : files) {
            //如果是文件夹，递归遍历
            if(file1.isDirectory()){
                GetFileList(file1.getPath(), filenames);
            }else{
                String name = file1.getName();
                //拿到UUID和源文件名称拼接的_的下标
                int index = name.indexOf("_");
                // 截取
                String fileName = name.substring(index + 1);
                filenames.put(name, fileName);
            }
        }
    }
}
