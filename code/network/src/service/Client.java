package service;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: huhao
 * @time: 2020/3/12 10:22
 * @desc: 完成注册和登录功能
 */
public class Client {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Socket client = null;
        try {
            // 创建socket并指定端口
            client = new Socket("localhost", 8888);

            // 采集用户信息
            String userInfo = registMenu();

            // 获得请求对象
            PrintWriter request = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
            // 向服务器发送请求数据（整理完毕的用户信息字符串）
            request.println(userInfo);

            // 将缓冲区输出
            request.flush();

            // 获得响应对象
            BufferedReader response = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));

            // 接受服务器端响应结果
            String result = response.readLine();

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 采集用户信息
     * @return 整理后的字符串
     */
    public static String registMenu(){


        System.out.println("请输入学号：");
        String id = input.next();

        System.out.println("请输入密码：");
        String pwd = input.next();

        System.out.println("请输入姓名：");
        String name = input.next();

        System.out.println("请输入性别：");
        String sex = input.next();

        System.out.println("请输入年龄：");
        Integer age = input.nextInt();

        System.out.println("请输入成绩：");
        Double score = input.nextDouble();



        return toJSON(id, pwd, name, sex, age, score);

    }

    /**
     * 将零散用户信息整理成JSON格式
     * @return 格式字符串
     * @param id
     * @param pwd
     * @param name
     * @param sex
     * @param age
     * @param score
     */
    public static String toJSON(String id, String pwd, String name, String sex, Integer age, Double score){
        String json = "{" + "id:"+ id + ",pwd:" + pwd + ",name:" + name +
                ",sex:" + sex + ",age:" + age + ",score:" + score + "}";
        return json;

    }
}
