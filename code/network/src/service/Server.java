package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/3/12 10:22
 * @desc:
 */
public class Server {
    public static void main(String[] args) {

        try {
            // 1. 创建ServerSocket并监听指定的端口
            ServerSocket server = new ServerSocket(8888);

            // 2. 使用accept监听来自客户端的连接
            Socket client = server.accept();

            // 3. 获得请求对象
            BufferedReader request = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));

            // 接受客户端请求数据
            String userInfo = request.readLine();

            // 保存用户信息
            String result = saveUserInfo(userInfo);

            // 获得响应对象
            PrintWriter response = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));

            // 将操作结果响应给用户端
            response.println(result);

            response.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将用户数据保存在服务器文件中
     * @param userInfo
     */
    private static String saveUserInfo(String userInfo) {

        try {
            // 创建流，执行一个properties文件
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("info\\userInfo.properties", true), "UTF-8"));

            // 解析出 id 的值作为 "=" 前的 key，直接将 userInfo 代表的字符串作为 "=" 后面的 value
            String id = getId(userInfo);

            // 保存用户信息到文件中
            Properties prop = new Properties();
            prop.setProperty(id, userInfo);

            prop.store(out, "");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "RegisterSuccessful.";
    }

    /**
     * 获取id
     * @param userInfo
     */
    private static String getId(String userInfo) {
        int startIndex = userInfo.indexOf(":") + 1;
        int endIndex = userInfo.indexOf(",");

        return userInfo.substring(startIndex, endIndex);
    }
}
