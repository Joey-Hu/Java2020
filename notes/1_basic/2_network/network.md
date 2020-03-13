## Network

!["OSI.png"]()

### 1. InetAddress 类

表示互联网协议（IP）地址对象，封装了与该IP地址相关的所有信息，并提供获取信息的常用方法。

```java
package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: huhao
 * @time: 2020/3/12 9:52
 * @desc:
 */
public class TestInetAddress {
    public static void main(String[] args) throws UnknownHostException {
// 对于方法返回值类型是对象本身的情况
//        MyClass mc = MyClass.getMyClassInstance();

        // 获得本机ip地址对象
        InetAddress localhost = InetAddress.getLocalHost();

        // 获得任意主机地址对象 InetAddress.getByName()
        InetAddress baidu = InetAddress.getByName("www.baidu.com");

        // 一个服务器可能对应多个ip地址
        InetAddress[] adds = InetAddress.getAllByName("www.baidu.com");

        // 获取ip地址字符串
        System.out.println(localhost.getHostAddress());

        // 获得ip地址对应主机名称
        System.out.println(localhost.getHostName());

        System.out.println(baidu.getHostAddress());

        for(InetAddress address:adds){
            System.out.println(address.getHostAddress());
            System.out.println(address.getHostName());

        }
    }
}

//class MyClass{
//
//    private MyClass(){
//
//    }
//
//    public static MyClass getMyClassInstance(){
//        return new MyClass();
//    }
//}

```
### 2. Socket 编程

!["socket.png"]()

服务器端基本步骤：

1. 创建ServerSocket对象，绑定监听端口。

2. 通过accept()方法监听客户端请求

3. 连接建立后，通过输入流读取客户端的数据

4. 通过输出流，向客户端回应信息

```java
package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/3/12 10:22
 * @desc: Server
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

```

服务器端基本步骤：

1. 创建Socket并指定端口

2. 通过输出流向服务端发送一个请求

3. 等待服务端的回应 获取输入流，读取客户端信息(将字节流转化为字符流)，并保存在缓冲区中

4. 关闭资源
```java
package service;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: huhao
 * @time: 2020/3/12 10:22
 * @desc: client 完成注册功能
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

```